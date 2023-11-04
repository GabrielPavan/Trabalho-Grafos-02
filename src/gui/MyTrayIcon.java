package gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import infra.ManagerFile;
import others.ConfigParameter;

public class MyTrayIcon {
	private TrayIcon trayIcon;
	private ManagerFile managerFile = new ManagerFile();
	
	public void createTrayIcon(String iconImagePath, String toolTip) throws Exception {
		if(SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(iconImagePath);
			
			PopupMenu popup = new PopupMenu();
			
			MenuItem configItem = new MenuItem("Configurações");
			MenuItem viewItem = new MenuItem("Visivel");
			MenuItem exitItem = new MenuItem("Sair");
			
			configItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					openAndConfigForm(new ConfigForm(null, null, null, false, false));
				}
			});
			
			viewItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					openAndConfigForm(new MainForm());
				}
			});
			
			exitItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			popup.add(configItem);
			popup.add(viewItem);
			popup.add(exitItem);
			
			trayIcon = new TrayIcon(image, toolTip, popup);
			trayIcon.setImageAutoSize(true);
			
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				throw new AWTException("Erro ao adicionar o TrayIcon ao sistema!");
			}
		} else {
			throw new Exception("O Sistema nao permite usar um TrayIcon!!\n");
		}
	}
	
	public void setupConfig() {
		if(!managerFile.ExecutionFileExist()) {
			openAndConfigForm(new ConfigForm(null, null, null, false, false));
		} else {
			openAndConfigForm(new MainForm());
		}
	}
	
	private void openAndConfigForm(Object Form) {
		
		String MailFolder = null, SucessFolder = null, FailFolder = null;
		boolean AutomaticRoute = false, BackgroundExecution = false, executionFileExist = false;
		
		try {
			executionFileExist = managerFile.ExecutionFileExist();
			
			if(executionFileExist) {
				List<String> DataList = managerFile.ReadExecutionFile();
				
				for (int i = 0; i < DataList.size(); i++) {
					if (!DataList.get(i).contains(ConfigParameter.DefaultSpliter)) {
						throw new IOException("O arquivo de configuração esta incorreto!");
					}
					
					String[] ParameterAndValue = DataList.get(i).split(ConfigParameter.DefaultSpliter);
					switch (ParameterAndValue[ConfigParameter.GetParameter]) {
					case ConfigParameter.MainFolder:
						MailFolder = ParameterAndValue[ConfigParameter.GetValue];
						break;
					case ConfigParameter.SucessFolder:
						SucessFolder = ParameterAndValue[ConfigParameter.GetValue];
						break;
					case ConfigParameter.FailFolder:
						FailFolder = ParameterAndValue[ConfigParameter.GetValue];
						break;
					case ConfigParameter.AutomaticRoute:
						AutomaticRoute = Boolean.valueOf(ParameterAndValue[ConfigParameter.GetValue]);
						break;
					case ConfigParameter.BackgroundExecution:
						BackgroundExecution = Boolean.valueOf(ParameterAndValue[ConfigParameter.GetValue]);
						break;
					default:
						throw new IOException("Parametro não definido nas configurações!!");
					}
				}
			}
			
			if(Form instanceof ConfigForm) {
				ConfigForm configForm = (ConfigForm) Form;
				if (executionFileExist) {
					configForm.dispose();
					configForm = new ConfigForm(MailFolder, SucessFolder, FailFolder, AutomaticRoute, BackgroundExecution);
				}
				configForm.setVisible(true);
				managerFile.UpdateExecutionFile(configForm.getMainFolder(), configForm.getSucessFolder(), configForm.getFailFolder(), configForm.getAutomaticRouteCheckBox(), configForm.getBackgroundExecutionCheckBox());
				managerFile.closeFile();
				
				if (!configForm.getBackgroundExecutionCheckBox()) {
					managerFile.DisableMonitorRoutes();
					openAndConfigForm(new MainForm());
				} else {
					managerFile.EnableMonitorRoutes(configForm.getMainFolder(), configForm.getSucessFolder(), configForm.getFailFolder());
				}
			} 
			
			if(Form instanceof MainForm) {
				MainForm mainForm  = (MainForm) Form;
				if(!BackgroundExecution) {
					mainForm.setVisible(true);
				} else {
					mainForm.dispose();
					managerFile.EnableMonitorRoutes(MailFolder, SucessFolder, FailFolder);
					JOptionPane.showMessageDialog(null, "O sistema esta definido para excutar em segundo plano\ncaso queira alterar acesse as configurações");
				}
			}
			
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de execução!! \n\n" + e.getMessage() );
		}
	}
}