package coen359.vendingmachine.admin.moneymanagement;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.admin.vmmanagement.VendingMachineManagementView;

import coen359.vendingmachine.admin.vmproduct.VendingMachine;
import coen359.vendingmachine.admin.vmproduct.VendingMachineService;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

import java.awt.Rectangle;
import java.awt.ComponentOrientation;

import javax.swing.ImageIcon;

import java.awt.Color;

public class AdminMoneyRefillView extends JFrame {

	private JPanel contentPane;
	private JTextField amountRefilledTextField;
	private JTextField balanceTextField;


	public AdminMoneyRefillView() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyRefillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyRefillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyRefillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyRefillView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		setBackground(new Color(176, 224, 230));
		setBounds(new Rectangle(0, 0, 1360, 760));
		setTitle("REFILL MONEY IN VENDING MACHINES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMoneyRefillView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		setLocationRelativeTo(contentPane);
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button.setBounds(28, 47, 124, 35);
		contentPane.add(button);

		JButton button_1 = new JButton("Log Out");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);
			}
		});
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_1.setBounds(326, 47, 124, 35);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new VendingMachineManagementView().setVisible(true);
			}
		});
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_2.setBounds(184, 47, 124, 35);
		contentPane.add(button_2);

		amountRefilledTextField = new JTextField();
		amountRefilledTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		amountRefilledTextField.setColumns(10);
		amountRefilledTextField.setBounds(427, 465, 193, 35);
		contentPane.add(amountRefilledTextField);

		JLabel lblNewLabel = new JLabel("Vending Machine ID");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel.setBounds(168, 299, 244, 41);
		contentPane.add(lblNewLabel);

		JLabel lblAmount = new JLabel("Balance ");
		lblAmount.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblAmount.setBounds(168, 383, 141, 41);
		contentPane.add(lblAmount);



		balanceTextField = new JTextField();
		balanceTextField.setEditable(false);
		balanceTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		balanceTextField.setColumns(10);
		balanceTextField.setBounds(427, 386, 193, 35);
		contentPane.add(balanceTextField);

		JLabel lblRefillAmount = new JLabel("Refill Amount");
		lblRefillAmount.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblRefillAmount.setBounds(168, 457, 141, 41);
		contentPane.add(lblRefillAmount);

		JLabel lblNewLabel_1 = new JLabel("$");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel_1.setBounds(404, 387, 20, 32);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel("$");
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		label.setBounds(404, 466, 20, 32);
		contentPane.add(label);

		final JComboBox vendingMachineIdComboBox = new JComboBox();
		vendingMachineIdComboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		vendingMachineIdComboBox.setModel(new DefaultComboBoxModel(new String[] {"[Select Id]"}));
		vendingMachineIdComboBox.setBounds(427, 303, 193, 32);
		contentPane.add(vendingMachineIdComboBox);
	
	/*	
		JTextArea warningMessageArea = new JTextArea();
		warningMessageArea.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		warningMessageArea.setBounds(169, 98, 451, 55);
		contentPane.add(warningMessageArea);		
	*/
		
		

		final JTextArea warningMessageArea = new JTextArea();
		warningMessageArea.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		warningMessageArea.setEditable(false);
		warningMessageArea.setRows(2);
		warningMessageArea.setText("***********MESSAGE**********\n");
		warningMessageArea.setBounds(168, 98, 452, 55);
		contentPane.add(warningMessageArea);
		
		JScrollPane scrollPane = new JScrollPane(warningMessageArea);
		scrollPane.setBounds(621, 154, -13, -60);
		contentPane.add(scrollPane);

		List<String> zeroBalanceMachines = new AdminMoneyRefillService().lessBalanceVendingMachines();
		warningMessageArea.removeAll();

		if (zeroBalanceMachines.size() != 0) {

			warningMessageArea.append("Please refill the following machines immediately!!!\n");
			
			for (String zeroBalanceVM : zeroBalanceMachines){

				warningMessageArea.append(zeroBalanceVM);
				warningMessageArea.append("\n");
			}
		}

		else {
			
			warningMessageArea.append("All vending machines are in good shape.");
		}


		JScrollPane scrollBar = new JScrollPane(warningMessageArea);
		scrollBar.setBounds(165, 108, 484, 132);
		contentPane.add(scrollBar);

		//displaying the values in vending machine combo box
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		VendingMachineService vendingMachineService = new VendingMachineService(entityManager);

		List<VendingMachine> result = vendingMachineService.readAll();

		DefaultComboBoxModel model = (DefaultComboBoxModel) vendingMachineIdComboBox.getModel();//new DefaultComboBoxModel( List<String> result);
		for (VendingMachine item : result) {
			model.addElement(item.getId());
		}

		final AdminMoneyRefillService adminMoneyRefillService = new AdminMoneyRefillService();
		vendingMachineIdComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String vendingMachineId = (String)vendingMachineIdComboBox.getSelectedItem();
				if (vendingMachineId.equals("[Select Id]")){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select a vending machine id.");
				}
				else {

					//retrieve the value from admin_money_refill and update this field. retrieve the latest value
					List <Double> result1 = adminMoneyRefillService.readLastAmountRefilled((String) vendingMachineIdComboBox.getSelectedItem());
					if (!result1.isEmpty()){

						System.out.println(result1.get(0).toString());
						Double balanceAmount = Double.parseDouble(result1.get(0).toString());
						System.out.println("Balance amount in vending machine" +balanceAmount);
						balanceTextField.setText(balanceAmount.toString());
					}
					else {
						balanceTextField.setText("0");
					}
				}
			}
		});



		JButton btnNewButton = new JButton("REFILL MONEY");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// validate if amount entered <=0 or null

				if ( amountRefilledTextField.getText().equals("") || vendingMachineIdComboBox.getSelectedItem().equals("[Select Id]") ){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					UIManager.put("Button.font",  new Font("System", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(null, "Please enter all the fields");
				}

				else {

					Double refillAmount = Double.parseDouble(amountRefilledTextField.getText());

					if ( refillAmount <= 0){

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						UIManager.put("Button.font",  new Font("System", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(null, "Please enter valid amount.");
						amountRefilledTextField.setText("");

					}

					else {

						// if id already existing, update table. Else create new entry.
						//AdminMoneyRefillService adminMoneyRefillService = new AdminMoneyRefillService();
						adminMoneyRefillService.updateAdminMoneyRefill((String)vendingMachineIdComboBox.getSelectedItem(), refillAmount);

						List<Double> result2 = adminMoneyRefillService.readLastAmountRefilled((String) vendingMachineIdComboBox.getSelectedItem());

						if (!result2.isEmpty()){

							System.out.println(result2.getClass());
							System.out.println( Double.valueOf(result2.get(0).toString()));
							Double balanceAmount = Double.parseDouble(result2.get(0).toString());
							System.out.println("Balance amount in vending machine in else of refill money" +balanceAmount);
							balanceTextField.setText(balanceAmount.toString());
						}


						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						UIManager.put("Button.font",  new Font("System", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(null, "$" + amountRefilledTextField.getText() + " added to " + (String)vendingMachineIdComboBox.getSelectedItem() + " successfully.");
						amountRefilledTextField.setText("");
						//update the text area  as well
						warningMessageArea.removeAll();
												
						List<String> zeroBalanceMachines = new AdminMoneyRefillService().lessBalanceVendingMachines();
						warningMessageArea.removeAll();

						if (zeroBalanceMachines.size() != 0) {

							warningMessageArea.append("Please refill the following machines immediately!!!\n");
							
							for (String zeroBalanceVM : zeroBalanceMachines){

								warningMessageArea.append(zeroBalanceVM);
								warningMessageArea.append("\n");
							}
						}

						else {
							/*warningMessageArea.repaint();
							warningMessageArea.removeAll();	*/
							setVisible(false);
							new AdminMoneyRefillView().setVisible(true);
							warningMessageArea.append("All vending machines are in good shape.");
						}
						
					}
				}



			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnNewButton.setBounds(326, 575, 203, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(AdminMoneyRefillView.class.getResource("/coen359/vendingmachine/resources/MoneyRefill.png")));
		lblNewLabel_2.setBounds(749, 47, 556, 624);
		contentPane.add(lblNewLabel_2);
		
		

		




	}
}
