package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entities.grafo.Grafo;
import entities.route.Route;
import entities.route.RouteConnections;
import entities.route.RouteWeights;
import factories.GrafoFactory;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class MainForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFind;
	private JTable table;
	private JComboBox<String> comboBoxOriginName, comboBoxOriginCode, comboBoxDestinyName, comboBoxDestinyCode;
	private JFormattedTextField formattedTextFieldCost;

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public MainForm() {
		setTitle("Dijkstra - Busca por menor caminho");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 545);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldFind = new JTextField();
		textFieldFind.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldFind.setBounds(87, 11, 400, 30);
		contentPane.add(textFieldFind);
		textFieldFind.setColumns(10);
		
		JButton btnFind = new JButton("Buscar");
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFind.setBounds(497, 11, 105, 30);
		contentPane.add(btnFind);
		
		JLabel lblFind = new JLabel("Buscar");
		lblFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFind.setHorizontalAlignment(SwingConstants.CENTER);
		lblFind.setBounds(10, 11, 67, 30);
		contentPane.add(lblFind);
		
		comboBoxOriginName = new JComboBox();
		comboBoxOriginName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxOriginName.getSelectedIndex() != comboBoxOriginCode.getSelectedIndex())
					comboBoxOriginCode.setSelectedIndex(comboBoxOriginName.getSelectedIndex());
			}
		});
		comboBoxOriginName.setModel(new DefaultComboBoxModel(new String[] {"Criciúma", "Urussanga", "Orleans", "São Bonifácio", "Tubarão", "Imbituba", "Palhoça", "Florianópolis"}));
		comboBoxOriginName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxOriginName.setBounds(241, 52, 246, 30);
		contentPane.add(comboBoxOriginName);
		
		comboBoxOriginCode = new JComboBox();
		comboBoxOriginCode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxOriginCode.getSelectedIndex() != comboBoxOriginName.getSelectedIndex())
					comboBoxOriginName.setSelectedIndex(comboBoxOriginCode.getSelectedIndex());
			}
		});
		comboBoxOriginCode.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7"}));
		comboBoxOriginCode.setToolTipText("");
		comboBoxOriginCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxOriginCode.setBounds(87, 52, 60, 30);
		contentPane.add(comboBoxOriginCode);
		
		JLabel lblOriginCode = new JLabel("Código:");
		lblOriginCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOriginCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOriginCode.setBounds(10, 52, 67, 30);
		contentPane.add(lblOriginCode);
		
		JLabel lblDestinyCode = new JLabel("Código:");
		lblDestinyCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestinyCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestinyCode.setBounds(10, 93, 67, 30);
		contentPane.add(lblDestinyCode);
		
		comboBoxDestinyName = new JComboBox();
		comboBoxDestinyName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxDestinyName.getSelectedIndex() != comboBoxDestinyCode.getSelectedIndex())
					comboBoxDestinyCode.setSelectedIndex(comboBoxDestinyName.getSelectedIndex());
			}
		});
		comboBoxDestinyName.setModel(new DefaultComboBoxModel(new String[] {"Criciúma", "Urussanga", "Orleans", "São Bonifácio", "Tubarão", "Imbituba", "Palhoça", "Florianópolis"}));
		comboBoxDestinyName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxDestinyName.setBounds(241, 93, 246, 30);
		contentPane.add(comboBoxDestinyName);
		
		comboBoxDestinyCode = new JComboBox();
		comboBoxDestinyCode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxDestinyCode.getSelectedIndex() != comboBoxDestinyName.getSelectedIndex())
					comboBoxDestinyName.setSelectedIndex(comboBoxDestinyCode.getSelectedIndex());
			}
		});
		comboBoxDestinyCode.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7"}));
		comboBoxDestinyCode.setToolTipText("");
		comboBoxDestinyCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxDestinyCode.setBounds(87, 93, 60, 30);
		contentPane.add(comboBoxDestinyCode);
		
		JLabel lblOriginName = new JLabel("Cidade:");
		lblOriginName.setHorizontalAlignment(SwingConstants.CENTER);
		lblOriginName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOriginName.setBounds(157, 52, 80, 30);
		contentPane.add(lblOriginName);
		
		JLabel lblDestinyName = new JLabel("Cidade:");
		lblDestinyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestinyName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestinyName.setBounds(151, 93, 80, 30);
		contentPane.add(lblDestinyName);
		
		JLabel lblCost = new JLabel("KM:");
		lblCost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCost.setBounds(10, 134, 67, 30);
		contentPane.add(lblCost);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 743, 285);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo Origem", "Cidade Origem", "C\u00F3digo Destino", "Cidade Destino", "Dist\u00E2ncia"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		scrollPane.setViewportView(table);
		
		JButton btnAddRoute = new JButton("+");
		btnAddRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxOriginCode.getSelectedIndex() == comboBoxDestinyCode.getSelectedIndex()) {
					JOptionPane.showMessageDialog(null, "A origem e destino devem ser diferentes.");
					return;
				}
				if (!formattedTextFieldCost.getText().matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "A distância deve ser valida.");
					return;
				}
				
				DefaultTableModel tableMode = (DefaultTableModel) table.getModel();	
				tableMode.addRow(new Object[]{
						comboBoxOriginCode.getSelectedItem(),
						comboBoxOriginName.getSelectedItem(),
						comboBoxDestinyCode.getSelectedItem(),
						comboBoxDestinyName.getSelectedItem(),
						(Object) formattedTextFieldCost.getText()
				});
			}
		});
		btnAddRoute.setBounds(693, 134, 60, 30);
		contentPane.add(btnAddRoute);
		
		JButton btnProcess = new JButton("Processar");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Route route = new Route();
				for(int i = 0; i < table.getRowCount(); i++) {
					RouteConnections routeconn = new RouteConnections();
					RouteWeights routeweigts = new RouteWeights();
					
					int originNode = Integer.parseInt(table.getValueAt(i, 0).toString());
					int	destinyNode = Integer.parseInt(table.getValueAt(i, 2).toString());
					int weight = Integer.parseInt(table.getValueAt(i, 4).toString());
					
					routeconn.setOriginNode(originNode);
					routeconn.setDestinyNode(destinyNode);
					
					routeweigts.setOriginNode(originNode);
					routeweigts.setDestinyNode(destinyNode);
					routeweigts.setWeight(weight);
					
					route.getConnections().add(routeconn);
					route.getWeights().add(routeweigts);
				}
				
				Grafo grafo = new GrafoFactory(route).getGrafo();
				System.out.println(grafo.caminhoMinimo(grafo.getSmallestNode(), grafo.getBiggestNode()));
			}
		});
		btnProcess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProcess.setBounds(648, 465, 105, 30);
		contentPane.add(btnProcess);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(533, 465, 105, 30);
		contentPane.add(btnSave);
		
		formattedTextFieldCost = new JFormattedTextField();
		formattedTextFieldCost.setBounds(87, 136, 60, 30);
		contentPane.add(formattedTextFieldCost);
		
		setVisible(true);
	}
}