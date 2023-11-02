package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import others.FilesPath;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ConfigForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FolderDefaultPathField;
	private JTextField SucessPathField;
	private JTextField FailPathField;
	
	private String MainFolder, SucessFolder, FailFolder;
	private JCheckBox AutomaticRouteCheckBox;

	public ConfigForm(String pMainFolder, String pSucessFolder, String pFailFolder, String pAutomaticRoute) {
		setModal(true);
		setTitle("Configurações");
		setResizable(false);
		setBounds(100, 100, 260, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		FolderDefaultPathField = new JTextField();
		FolderDefaultPathField.setBounds(69, 11, 165, 20);
		contentPane.add(FolderDefaultPathField);
		FolderDefaultPathField.setColumns(10);
		
		SucessPathField = new JTextField();
		SucessPathField.setColumns(10);
		SucessPathField.setBounds(69, 42, 165, 20);
		contentPane.add(SucessPathField);
		
		FailPathField = new JTextField();
		FailPathField.setColumns(10);
		FailPathField.setBounds(69, 73, 165, 20);
		contentPane.add(FailPathField);
		
		JLabel FolderLabel = new JLabel("Pasta:");
		FolderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		FolderLabel.setBounds(10, 14, 55, 14);
		contentPane.add(FolderLabel);
		
		JLabel SucessLabel = new JLabel("Sucesso:");
		SucessLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		SucessLabel.setBounds(10, 45, 55, 14);
		contentPane.add(SucessLabel);
		
		JLabel FailLabel = new JLabel("Erro:");
		FailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		FailLabel.setBounds(10, 76, 55, 14);
		contentPane.add(FailLabel);
		
		AutomaticRouteCheckBox = new JCheckBox("Rota automática");
		AutomaticRouteCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				FolderDefaultPathField.setEnabled(!AutomaticRouteCheckBox.isSelected());
				SucessPathField.setEnabled(!AutomaticRouteCheckBox.isSelected());
				FailPathField.setEnabled(!AutomaticRouteCheckBox.isSelected());
			}
		});
		AutomaticRouteCheckBox.setBounds(62, 100, 120, 23);
		contentPane.add(AutomaticRouteCheckBox);
		
		JButton SaveButton = new JButton("Salvar");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AutomaticRouteCheckBox.isSelected()) {
					MainFolder = FilesPath.DefaultRoutesFilesPath;
					SucessFolder = FilesPath.DefaultPathForProcessedFiles;
					FailFolder = FilesPath.DefaultPathForUnProcessedFiles;
				} else {
					MainFolder = FolderDefaultPathField.getText();
					SucessFolder = SucessPathField.getText();
					FailFolder = FailPathField.getText();
					if(MainFolder.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "O caminho para Pasta não pode ser vazio!");
						return;
					}
					if(SucessFolder.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "O caminho para arquivos como sucesso não pode ser vazio!");
						return;
					}
					if(FailFolder.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "O caminho para arquivos sem sucesso não pode ser vazio!");
						return;
					}
					MainFolder = FolderDefaultPathField.getText();
					SucessFolder = SucessPathField.getText();
					FailFolder = FailPathField.getText();
				}
				dispose();
			}
		});
		SaveButton.setBounds(145, 130, 89, 23);
		contentPane.add(SaveButton);
		
		boolean CheckBoxStatus = Boolean.parseBoolean(pAutomaticRoute);
		
		if (CheckBoxStatus) {
			AutomaticRouteCheckBox.setSelected(CheckBoxStatus);
			FolderDefaultPathField.setEnabled(!CheckBoxStatus);
			SucessPathField.setEnabled(!CheckBoxStatus);
			FailPathField.setEnabled(!CheckBoxStatus);
		} else {
			AutomaticRouteCheckBox.setSelected(false);
			FolderDefaultPathField.setText(pMainFolder);
			SucessPathField.setText(pSucessFolder);
			FailPathField.setText(pFailFolder);
		}
	
		setVisible(true);
	}
	
	public String getMainFolder() {
		return MainFolder;
	}
	public String getSucessFolder() {
		return SucessFolder;
	}
	public String getFailFolder() {
		return FailFolder;
	}
	public boolean getAutomaticRouteCheckBox() {
		return AutomaticRouteCheckBox.isSelected();
	}
}