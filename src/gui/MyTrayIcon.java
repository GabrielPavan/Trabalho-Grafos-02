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

import javax.swing.JOptionPane;

import infra.ManagerFile;

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
					
				}
			});
			
			viewItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
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
			if (managerFile.ValidadeExecutionFile()) {
				ConfigForm configForm = new ConfigForm("","","");
				managerFile.UpdateExecutionFile(configForm.getMainFolder(), configForm.getSucessFolder(), configForm.getFailFolder());
			}
			managerFile.closeFile();
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Erro ao validar o arquivo de execução!!");
		}
	}
	private void openConfigForm() {
		ManagerFile managerFile = new ManagerFile();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}