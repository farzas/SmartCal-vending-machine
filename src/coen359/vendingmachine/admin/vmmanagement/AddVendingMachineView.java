package coen359.vendingmachine.admin.vmmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.admin.LoginView;

import coen359.vendingmachine.admin.vmproduct.VendingMachineService;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AddVendingMachineView extends JFrame {

	private JPanel contentPane;
	private JTextField vendingMachineIdTextField;
	private JTextField locationTextField;

	
	public AddVendingMachineView() {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AddVendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AddVendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AddVendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AddVendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		
		setTitle("ADD NEW VENDING MACHINE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddVendingMachineView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(contentPane);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		btnHome.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnHome.setBounds(38, 25, 124, 35);
		contentPane.add(btnHome);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);
			}
		});
		btnLogOut.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnLogOut.setBounds(190, 25, 124, 35);
		contentPane.add(btnLogOut);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new VendingMachineManagementView().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnBack.setBounds(340, 25, 124, 35);
		contentPane.add(btnBack);

		JLabel lblVendingMachineId = new JLabel("Vending Machine Id\r\n");
		lblVendingMachineId.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblVendingMachineId.setBounds(245, 160, 259, 35);
		contentPane.add(lblVendingMachineId);

		JLabel lblCategory = new JLabel("Category\r\n");
		lblCategory.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblCategory.setBounds(245, 254, 259, 35);
		contentPane.add(lblCategory);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblLocation.setBounds(245, 351, 259, 35);
		contentPane.add(lblLocation);

		vendingMachineIdTextField = new JTextField();
		vendingMachineIdTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		vendingMachineIdTextField.setBounds(519, 163, 247, 32);
		contentPane.add(vendingMachineIdTextField);
		vendingMachineIdTextField.setColumns(10);


		final JComboBox categoryComboBox = new JComboBox();
		categoryComboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT CATEGORY]", "School", "Restaurant", "Hospital"}));
		categoryComboBox.setBounds(519, 256, 247, 34);
		contentPane.add(categoryComboBox);

		locationTextField = new JTextField();
		locationTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		locationTextField.setColumns(10);
		locationTextField.setBounds(519, 354, 247, 32);
		contentPane.add(locationTextField);

		final VendingMachineService vendingMachineService = new VendingMachineService();


		JButton btnNewButton = new JButton("ADD VENDING MACHINE\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String vendingMachineId = vendingMachineIdTextField.getText();
				String category = (String)categoryComboBox.getSelectedItem();
				String location = locationTextField.getText();
				System.out.println("Retrieved value VM"+vendingMachineId);
				
				if (vendingMachineId.equals(null) || category.equals("[SELECT CATEGORY]") || location.equals(null)){

					System.out.println("Vending machine id "+vendingMachineId);
					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please enter all the fields.");
				/*	vendingMachineIdTextField.setText("");
					categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT CATEGORY]", "School", "Restaurant", "Hospital"}));
					locationTextField.setText("");*/


				}
				else if (!vendingMachineService.checkIfVendingMachineExists(vendingMachineId)){

					if (vendingMachineId.equals(null) || category.equals("[SELECT CATEGORY]") || location.equals(null)){

						System.out.println("Vending machine id "+vendingMachineId);
						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, "Please enter all the fields !.");
						vendingMachineIdTextField.setText("");
						categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT CATEGORY]", "School", "Restaurant", "Hospital"}));
						locationTextField.setText("");
					}
					
					else{
						
						vendingMachineService.createVendingMachine(vendingMachineId, category, location);

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, "Vending Machine added successfully");
						vendingMachineIdTextField.setText("");
						categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT CATEGORY]", "School", "Restaurant", "Hospital"}));
						locationTextField.setText("");
					}
				}
				else{

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Vending Machine with the given ID already exists.");
					vendingMachineIdTextField.setText("");
					categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT CATEGORY]", "School", "Restaurant", "Hospital"}));
					locationTextField.setText("");
				}

			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnNewButton.setBounds(340, 478, 366, 42);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AddVendingMachineView.class.getResource("/coen359/vendingmachine/resources/Add vending machine.png")));
		lblNewLabel.setBounds(834, 48, 460, 603);
		contentPane.add(lblNewLabel);
	}
}
