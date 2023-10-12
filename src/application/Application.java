package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import infra.ManageFile;
import others.FilesPath;

public class Application {

	public static void main(String[] args) {
		ManageFile _ManageFile = new ManageFile();
		try {
			_ManageFile.SetupConfig(FilesPath.DefaultConfigFilePath);
			_ManageFile.SetupRoutes(FilesPath.DefaultRoutesFilesPath);
		} catch (IOException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}