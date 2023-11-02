package application;

import javax.swing.JOptionPane;

import gui.MyTrayIcon;

public class Application {

	public static void main(String[] args) {
		
		MyTrayIcon myTrayIcon = new MyTrayIcon();
		try {
			myTrayIcon.createTrayIcon("./img/menu.png", "Meu TrayIcon");
			myTrayIcon.setupConfig();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Erro no TrayIcon: \n\n" + e.getMessage());
		}
	}
}