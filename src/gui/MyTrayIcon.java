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
import others.FilesPath;

public class MyTrayIcon {
	private TrayIcon trayIcon;
	
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
					openConfigForm();
				}
			});
			
			viewItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			exitItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Programa encerrado!");
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
		ManagerFile managerFile = new ManagerFile();
		try {
			if (!managerFile.ExecutionFileExist()) {
				ConfigForm configForm = new ConfigForm("","","","");
				managerFile.UpdateExecutionFile(configForm.getMainFolder(), 
												configForm.getSucessFolder(), 
												configForm.getFailFolder(),
												configForm.getAutomaticRouteCheckBox());
			}
			managerFile.ExecExecutionFile();
			managerFile.closeFile();
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao validar o arquivo de execução!!");
		}
	}
	private void openConfigForm() {
		ManagerFile managerFile = new ManagerFile();
		try {
			List<String> DataList = managerFile.ReadExecutionFile();
			
			String MailFolder = null, SucessFolder = null, FailFolder = null;
			String AutomaticRoute = null;
			
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
					AutomaticRoute = ParameterAndValue[ConfigParameter.GetValue];
					break;
				default:
					throw new IOException("Parametro não definido nas configurações!!");
				}
			}
			ConfigForm configForm = new ConfigForm(MailFolder,SucessFolder,FailFolder, AutomaticRoute);
			managerFile.UpdateExecutionFile(configForm.getMainFolder(), 
					configForm.getSucessFolder(), 
					configForm.getFailFolder(),
					configForm.getAutomaticRouteCheckBox());
			managerFile.UpdateExecutionFile(configForm.getMainFolder(), 
					configForm.getSucessFolder(), 
					configForm.getFailFolder(),
					configForm.getAutomaticRouteCheckBox());
			
			managerFile.closeFile();
		} catch (Exception e ) {
			JOptionPane.showConfirmDialog(null, "Erro ao ler o arquivo de execução!! \n\n" + e.getMessage() );
		}
	}
}