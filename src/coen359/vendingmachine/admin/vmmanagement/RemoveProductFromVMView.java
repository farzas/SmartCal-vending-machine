package coen359.vendingmachine.admin.vmmanagement;


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
import javax.persistence.TypedQuery;
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

import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.vmproduct.*;
import coen359.vendingmachine.product.Product;
import coen359.vendingmachine.statistics.revenue.VendingMachineProduct;

import java.awt.Color;
import javax.swing.ImageIcon;

public class RemoveProductFromVMView extends JFrame {

	private JPanel contentPane;
	private JTextField quantityToBeRemovedTextField;
	private AddProductService addProductService;
	private JTextField quantityAvailableTextField;

	private static String SELECT_MACHINE = "[Select machine]";
	private static String SELECT_TYPE = "[Select type]";
	private static String SELECT_NAME = "[Select name]";


	public RemoveProductFromVMView() {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RemoveProductFromVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RemoveProductFromVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RemoveProductFromVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RemoveProductFromVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		setTitle("REMOVE PRODUCTS FROM VENDING MACHINE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProductToVMView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 761);
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
		btnHome.setBounds(35, 16, 124, 35);
		contentPane.add(btnHome);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);				
			}
		});
		btnLogOut.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnLogOut.setBounds(190, 16, 124, 35);
		contentPane.add(btnLogOut);

		JLabel lblVendingMachine = new JLabel("Vending Machine");
		lblVendingMachine.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblVendingMachine.setBounds(206, 118, 215, 35);
		contentPane.add(lblVendingMachine);

		final JComboBox vendingMachineComboBox = new JComboBox();
		vendingMachineComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_MACHINE}));
		vendingMachineComboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		vendingMachineComboBox.setBounds(539, 119, 251, 34);
		contentPane.add(vendingMachineComboBox);

		final JComboBox productTypeComboBox = new JComboBox();
		productTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_TYPE, "Sandwich", "Candy", "Beverage"}));
		productTypeComboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		productTypeComboBox.setBounds(539, 197, 251, 34);
		contentPane.add(productTypeComboBox);

		final JComboBox productNameComboBox = new JComboBox();
		productNameComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_NAME}));
		productNameComboBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		productNameComboBox.setBounds(539, 279, 251, 34);
		contentPane.add(productNameComboBox);
		
		//displaying the values in vending machine combo box
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		VendingMachineService vendingMachineService = new VendingMachineService(entityManager);

		List<VendingMachine> result = vendingMachineService.readAll();

		DefaultComboBoxModel model = (DefaultComboBoxModel) vendingMachineComboBox.getModel();//new DefaultComboBoxModel( List<String> result);
		for (VendingMachine item : result) {
			model.addElement(item.getId());
		}

		vendingMachineComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				productNameComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_NAME}));
				String vendingMachineId = (String)vendingMachineComboBox.getSelectedItem();

				if (vendingMachineId.equals(SELECT_MACHINE)){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select a vending macine id.");
					quantityToBeRemovedTextField.setText("");
				}
			}
		});

		JLabel lblProductType = new JLabel("Product Type");
		lblProductType.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblProductType.setBounds(206, 196, 215, 35);
		contentPane.add(lblProductType);


		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblProductName.setBounds(206, 279, 215, 35);
		contentPane.add(lblProductName);





		JLabel lblQuantity = new JLabel("Quantity Available");
		lblQuantity.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblQuantity.setBounds(206, 363, 262, 35);
		contentPane.add(lblQuantity);

		quantityToBeRemovedTextField = new JTextField();
		quantityToBeRemovedTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		quantityToBeRemovedTextField.setBounds(539, 438, 251, 35);
		contentPane.add(quantityToBeRemovedTextField);
		quantityToBeRemovedTextField.setColumns(10);

		productTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//displaying products in combo box according to type
				addProductService = new AddProductService();
				String productType = (String)productTypeComboBox.getSelectedItem();

				if (productType.equals(SELECT_TYPE)){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select a product type.");
					quantityToBeRemovedTextField.setText("");
				}
				
				

				VendingMachineProductService vendingMachineProductService = new VendingMachineProductService();

				System.out.println("Product type : " +productType);

				if(vendingMachineProductService.checkProductExistsInVendingMachine(productType) == false){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, productType+ " not available in vending machine " +(String)vendingMachineComboBox.getSelectedItem()+ ". Please make another selection.");
					quantityToBeRemovedTextField.setText("");
					quantityAvailableTextField.setText("");
					productNameComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_NAME}));

				}

				else{					

					quantityAvailableTextField.setText("");
					productNameComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_NAME}));

					List<String> result = vendingMachineProductService.readAll(productType);		

					for(String productName : result)
						System.out.println("Product Name: " + productName);


					DefaultComboBoxModel model = (DefaultComboBoxModel) productNameComboBox.getModel();//new DefaultComboBoxModel( List<String> result);
					for (String item : result) {
						model.addElement(item);
					}

					// setting model with new data
					productNameComboBox.setModel(model);

				}


			}
		});


		productNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//populate the quantity field after selecting the product name
				addProductService = new AddProductService();
				String productName = (String)productNameComboBox.getSelectedItem();
				if (productName.equals(SELECT_NAME)){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select a product name.");
					quantityToBeRemovedTextField.setText("");
				}

				VendingMachineProductService vendingMachineProductService = new VendingMachineProductService();
				String productId = vendingMachineProductService.getProductId(productName, (String)productTypeComboBox.getSelectedItem());

				//vendingMachineProductService.checkProductExistsInVendingMachineName(productName);
				if (!vendingMachineProductService.checkProductExistsInVendingMachineName(productName)){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "This product is not present in the selected vending machine. Please make another selection.");
					quantityAvailableTextField.setText("");
				}

				else {

					Integer quantity = vendingMachineProductService.getVendingMachineProductQuantity((String)vendingMachineComboBox.getSelectedItem(), productId);
					quantityAvailableTextField.setText(quantity.toString());	
				}

				Integer quantity = vendingMachineProductService.getVendingMachineProductQuantity((String)vendingMachineComboBox.getSelectedItem(), productId);
				quantityAvailableTextField.setText(quantity.toString());				
			}
		});


		JButton btnNewButton = new JButton("Remove Product\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String vendingMachineId = (String) vendingMachineComboBox.getSelectedItem();
				String productType = (String) productTypeComboBox.getSelectedItem();
				String productName = (String) productNameComboBox.getSelectedItem();			


				if (vendingMachineId.equals("[null]") || productType.equals("[null]") || productName.equals("[null]" )){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select valid options");
					quantityToBeRemovedTextField.setText("");
				}

				else{

								
					// retrieve the quantity value from vending machine product table
					String productId = new VendingMachineProductService().getProductId(productName, productType);
					Integer availableQuantity = new VendingMachineProductService().getVendingMachineProductQuantity(vendingMachineId, productId);
					
					Integer quantityToBeRemoved = Integer.parseInt(quantityToBeRemovedTextField.getText()) ;
					
					//retrieve the product id from type and name
					//String id = addProductService.getProductId(productName, productType).toString();

					EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
					EntityManager entityManager = entityManagerFactory.createEntityManager();
					VendingMachineProductService vendingMachineProductService = new VendingMachineProductService(entityManager);

					if( quantityToBeRemoved > availableQuantity ){

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, "Please enter a value within the range.");
						quantityToBeRemovedTextField.setText("");
					}
					//check if product quantity is greater than the available product quantity. If not, deduct from vending machine table
					// if product already exists in vending machine, update the table
					else{

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						UIManager.put("Button.font",  new Font("System", Font.PLAIN, 22));
						
						JOptionPane.showConfirmDialog(null,"Are you sure you want to remove product from vending machine? ", "REMOVE PRODUCT?? ", JOptionPane.YES_NO_OPTION);
						int dialogButton = JOptionPane.YES_NO_OPTION;

						final JOptionPane optionPane = new JOptionPane(
								"Are you sure you want to delete?",
								JOptionPane.QUESTION_MESSAGE,
								dialogButton);

						if(dialogButton == 0) {

							vendingMachineProductService.checkProductExistsAndDelete(vendingMachineId, productId, quantityToBeRemoved);			
							quantityAvailableTextField.setText(vendingMachineProductService.getVendingMachineProductQuantity(vendingMachineId, productId).toString());

							UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
							UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
							JOptionPane.showMessageDialog(null, quantityToBeRemoved+ " " +productName+ " removed vending machine " +vendingMachineId+ ".");
							quantityToBeRemovedTextField.setText("");
						} 

						else {
							System.out.println("No Option");

							new RemoveProductFromVMView().setVisible(true);
						} 
						
					}				


				}


			}

		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		btnNewButton.setBounds(377, 557, 223, 40);
		contentPane.add(btnNewButton);

		JLabel lblQuantityRequired = new JLabel("Quantity to be removed\r\n\r\n");
		lblQuantityRequired.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblQuantityRequired.setBounds(206, 438, 318, 35);
		contentPane.add(lblQuantityRequired);

		quantityAvailableTextField = new JTextField();
		quantityAvailableTextField.setEditable(false);
		quantityAvailableTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		quantityAvailableTextField.setColumns(10);
		quantityAvailableTextField.setBounds(539, 363, 251, 35);
		contentPane.add(quantityAvailableTextField);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new VendingMachineManagementView().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnBack.setBounds(344, 16, 124, 35);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RemoveProductFromVMView.class.getResource("/coen359/vendingmachine/resources/remove products.png")));
		lblNewLabel.setBounds(855, 55, 376, 581);
		contentPane.add(lblNewLabel);
	}
}
