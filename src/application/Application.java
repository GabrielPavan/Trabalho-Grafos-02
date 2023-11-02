package application;

import java.io.IOException;
import javax.swing.JOptionPane;

import gui.MyTrayIcon;
import infra.ManagerFile;
import others.FilesPath;

public class Application {

	public static void main(String[] args) {
		
		MyTrayIcon myTrayIcon = new MyTrayIcon();
		ManagerFile managerFile = new ManagerFile();
		try {
			myTrayIcon.createTrayIcon("./img/menu.png", "Meu TrayIcon");
			myTrayIcon.setupConfig();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Erro no TrayIcon: \n\n" + e.getMessage());
		}
		
		
		try {
			managerFile.MonitorRoutes(FilesPath.DefaultRoutesFilesPath);
		} catch (IOException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}