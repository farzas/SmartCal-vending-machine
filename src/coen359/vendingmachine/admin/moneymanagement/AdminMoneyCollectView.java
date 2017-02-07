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
import coen359.vendingmachine.admin.vmproduct.VendingMachine;
import coen359.vendingmachine.admin.vmproduct.VendingMachineService;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminMoneyCollectView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField amountTextField;
	private JTextField balanceTextField;

	
	/**
	 * Create the frame.
	 */
	public AdminMoneyCollectView() {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyCollectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyCollectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyCollectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AdminMoneyCollectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		
		setTitle("COLLECT MONEY FROM VENDING MACHINES\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMoneyCollectView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(contentPane);

		amountTextField = new JTextField();
		amountTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		amountTextField.setBounds(430, 315, 190, 38);
		contentPane.add(amountTextField);
		amountTextField.setColumns(10);

		final JComboBox vendingMachineIdComboBox = new JComboBox();
		vendingMachineIdComboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		vendingMachineIdComboBox.setBounds(430, 151, 190, 32);
		vendingMachineIdComboBox.setModel(new DefaultComboBoxModel(new String[] {"[Select Id]"}));
		contentPane.add(vendingMachineIdComboBox);

		JLabel lblNewLabel = new JLabel("Vending Machine Id");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel.setBounds(168, 151, 218, 32);
		contentPane.add(lblNewLabel);

		JLabel lblAmount = new JLabel("Retrieve Amount");
		lblAmount.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblAmount.setBounds(168, 318, 203, 32);
		contentPane.add(lblAmount);

		balanceTextField = new JTextField();
		balanceTextField.setEditable(false);
		balanceTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		balanceTextField.setColumns(10);
		balanceTextField.setBounds(430, 232, 190, 38);
		contentPane.add(balanceTextField);

		JLabel lblBalanceAvailable = new JLabel("Balance Available");
		lblBalanceAvailable.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblBalanceAvailable.setBounds(168, 234, 218, 32);
		contentPane.add(lblBalanceAvailable);

		JLabel label = new JLabel("$");
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		label.setBounds(410, 235, 20, 32);
		contentPane.add(label);

		JLabel label_1 = new JLabel("$");
		label_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		label_1.setBounds(410, 318, 20, 32);
		contentPane.add(label_1);

		//displaying the values in vending machine combo box
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		VendingMachineService vendingMachineService = new VendingMachineService(entityManager);

		List<VendingMachine> result = vendingMachineService.readAll();

		DefaultComboBoxModel model = (DefaultComboBoxModel) vendingMachineIdComboBox.getModel();//new DefaultComboBoxModel( List<String> result);
		for (VendingMachine item : result) {
			model.addElement(item.getId());}


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
		
		
		JButton btnCollectMoney = new JButton("COLLECT MONEY\r\n");
		btnCollectMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// validate if amount entered <=0 or null

				if ( amountTextField.getText().equals("") || vendingMachineIdComboBox.getSelectedItem().equals("[Select Id]") ){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					UIManager.put("Button.font",  new Font("System", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(null, "Please enter all the fields");
				}

				else {

					Double amountCollected = Double.parseDouble(amountTextField.getText());
					Double balance = Double.parseDouble(balanceTextField.getText());

					if ( amountCollected > balance || amountCollected == 0){

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						UIManager.put("Button.font",  new Font("System", Font.PLAIN, 20));
						JOptionPane.showMessageDialog(null, "Please enter valid amount.");
						amountTextField.setText("");

					}

					else {

						// if id already existing, update table. Else create new entry.
						//AdminMoneyRefillService adminMoneyRefillService = new AdminMoneyRefillService();
						
						adminMoneyRefillService.updateAdminMoneyCollect((String)vendingMachineIdComboBox.getSelectedItem(), amountCollected);

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
						JOptionPane.showMessageDialog(null, "$" + amountTextField.getText() + " collected from " + (String)vendingMachineIdComboBox.getSelectedItem() + " successfully.");
						amountTextField.setText("");}
				}



			}
	});
		btnCollectMoney.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnCollectMoney.setBounds(300, 454, 239, 46);
		contentPane.add(btnCollectMoney);
		
		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button.setBounds(28, 37, 124, 35);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_1.setBounds(184, 37, 124, 35);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Log Out");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				setVisible(false);
				LoginView.main(null);
			}
		});
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_2.setBounds(326, 37, 124, 35);
		contentPane.add(button_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(AdminMoneyCollectView.class.getResource("/coen359/vendingmachine/resources/Collect Money.png")));
		lblNewLabel_1.setBounds(728, 37, 580, 635);
		contentPane.add(lblNewLabel_1);


	}





}
