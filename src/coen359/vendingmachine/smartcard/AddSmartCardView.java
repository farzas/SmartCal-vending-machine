package coen359.vendingmachine.smartcard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JButton;

import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.vmmanagement.VendingMachineManagementView;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AddSmartCardView extends JFrame {

	private JPanel contentPane;
	private JTextField smartCardIdTextField;
	private JTextField codeTextField;
	private JTextField monthTextField;
	private JTextField yearTextField;

	
	public AddSmartCardView() {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AddSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AddSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AddSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AddSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		
		setTitle("ADD NEW SMART CARD");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddSmartCardView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(contentPane);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new SmartCardManagementView().setVisible(true);
			}
		});
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button.setBounds(343, 29, 124, 35);
		contentPane.add(button);

		JButton button_1 = new JButton("Home");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_1.setBounds(41, 29, 124, 35);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Log Out");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);
			}
		});
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_2.setBounds(193, 29, 124, 35);
		contentPane.add(button_2);

		JLabel lblSmartCardId = new JLabel("Smart Card Id\r\n");
		lblSmartCardId.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblSmartCardId.setBounds(208, 101, 209, 35);
		contentPane.add(lblSmartCardId);

		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblExpiryDate.setBounds(208, 257, 259, 35);
		contentPane.add(lblExpiryDate);


		monthTextField = new JTextField();
		monthTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		monthTextField.setBounds(537, 257, 68, 34);
		contentPane.add(monthTextField);
		monthTextField.setColumns(10);

		yearTextField = new JTextField();
		yearTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		yearTextField.setBounds(703, 257, 68, 34);
		contentPane.add(yearTextField);
		yearTextField.setColumns(10);

		JLabel lblAmount = new JLabel("Amount Limit");
		lblAmount.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblAmount.setBounds(208, 339, 209, 35);
		contentPane.add(lblAmount);

		smartCardIdTextField = new JTextField();
		smartCardIdTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		smartCardIdTextField.setBounds(456, 101, 315, 34);
		contentPane.add(smartCardIdTextField);
		smartCardIdTextField.setColumns(10);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblMonth.setBounds(461, 261, 61, 31);
		contentPane.add(lblMonth);

		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblYear.setBounds(634, 262, 61, 31);
		contentPane.add(lblYear);

		JLabel lblSmartCardType = new JLabel("Smart Card Type\r\n\r\n");
		lblSmartCardType.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblSmartCardType.setBounds(208, 182, 209, 35);
		contentPane.add(lblSmartCardType);
		
		final JComboBox smartCardTypeComboBox = new JComboBox();
		smartCardTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT TYPE]", "Visa", "Master Card", "American Express", "Discover"}));
		smartCardTypeComboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		smartCardTypeComboBox.setBounds(456, 182, 315, 34);
		contentPane.add(smartCardTypeComboBox);

		codeTextField = new JTextField();
		codeTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		codeTextField.setColumns(10);
		codeTextField.setBounds(456, 340, 315, 34);
		contentPane.add(codeTextField);

		JButton addSmartCardButton = new JButton("ADD SMART CARD");
		addSmartCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					

				SmartCardService smartCardService = new SmartCardService();	

				if (monthTextField.getText().equals("") || yearTextField.getText().equals("") || codeTextField.getText().equals("") || smartCardIdTextField.getText().equals("") || smartCardTypeComboBox.getSelectedItem().equals("[SELECT TYPE]")){

					System.out.println("Smart Card id " + smartCardIdTextField.getText());
					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please enter all the fields.");
				}

				else if (smartCardService.checkIfSmartCardExists(smartCardIdTextField.getText()) == false){

					String smartCardId = smartCardIdTextField.getText();
					Integer expiryMonth = Integer.parseInt(monthTextField.getText().toString());
					Integer expiryYear = Integer.parseInt(yearTextField.getText().toString());
					Integer cvv = Integer.parseInt(codeTextField.getText().toString());
					String type = (String)smartCardTypeComboBox.getSelectedItem();
					int expiryYearDigits = yearTextField.getText().length();		
					
					if ( expiryMonth >= 13 || expiryMonth <= 0|| expiryYearDigits > 4 || expiryYearDigits < 4  ){

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, "Please enter valid month and year.");
						monthTextField.setText("");
						yearTextField.setText("");
					}

					else{
						smartCardService.createSmartCard(smartCardId, type, expiryMonth, expiryYear, cvv);

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, "Smart Card added successfully");
						smartCardIdTextField.setText("");
						monthTextField.setText("");
						yearTextField.setText("");
						codeTextField.setText("");
						smartCardTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT TYPE]", "Visa", "Master Card", "American Express", "Discover"}));

					}


				}

				else if (new SmartCardService().checkIfSmartCardExists(smartCardIdTextField.getText()) == true){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Smart Card with the given ID already exists.");
					smartCardIdTextField.setText("");
					monthTextField.setText("");
					yearTextField.setText("");
					codeTextField.setText("");
					smartCardTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT TYPE]", "Visa", "Master Card", "American Express", "Discover"}));
				}
			}

		});
		addSmartCardButton.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		addSmartCardButton.setBounds(326, 489, 259, 45);
		contentPane.add(addSmartCardButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AddSmartCardView.class.getResource("/coen359/vendingmachine/resources/Add Smart Card.png")));
		lblNewLabel.setBounds(853, 99, 448, 339);
		contentPane.add(lblNewLabel);
		
		

	}
}
