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
import coen359.vendingmachine.admin.userfeedback.UserFeedbackView;

import coen359.vendingmachine.admin.vmproduct.*;
import coen359.vendingmachine.product.Product;
import coen359.vendingmachine.statistics.revenue.VendingMachineProduct;

import java.awt.Color;
import javax.swing.ImageIcon;

public class AddProductToVMView extends JFrame {

	private JPanel contentPane;
	private JTextField quantityTextField;
	private AddProductService addProductService;
	private JTextField quantityAvailableTextField;
	
	private static String SELECT_MACHINE = "[Select machine]";
	private static String SELECT_TYPE = "[Select type]";
	private static String SELECT_NAME = "[Select name]";

	
	public AddProductToVMView() {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AddProductToVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AddProductToVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AddProductToVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AddProductToVMView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		
		setResizable(false);
		setTitle("REFILL VENDING MACHINE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProductToVMView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		//setRootPane(Toolkit.getDefaultToolkit().getImage(AddProductToVMView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
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
		vendingMachineComboBox.setBounds(483, 118, 251, 34);
		contentPane.add(vendingMachineComboBox);

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

				String vendingMachineId = (String)vendingMachineComboBox.getSelectedItem();

				if (vendingMachineId.equals(SELECT_MACHINE)){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select a vending machine id.");
					quantityTextField.setText("");
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



		final JComboBox productTypeComboBox = new JComboBox();
		productTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_TYPE, "Sandwich", "Candy", "Beverage"}));
		productTypeComboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		productTypeComboBox.setBounds(483, 200, 251, 34);
		contentPane.add(productTypeComboBox);

		final JComboBox productNameComboBox = new JComboBox();
		productNameComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_NAME}));
		productNameComboBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		productNameComboBox.setBounds(483, 279, 251, 34);
		contentPane.add(productNameComboBox);


		JLabel lblQuantity = new JLabel("Quantity Available");
		lblQuantity.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblQuantity.setBounds(206, 363, 262, 35);
		contentPane.add(lblQuantity);

		quantityTextField = new JTextField();
		quantityTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		quantityTextField.setBounds(483, 438, 251, 35);
		contentPane.add(quantityTextField);
		quantityTextField.setColumns(10);

		productTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//displaying products in combo box according to type
				addProductService = new AddProductService();
				String productType = (String)productTypeComboBox.getSelectedItem();
				
				
				if (productType.equals(SELECT_TYPE)){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select a product type.");
					quantityTextField.setText("");
				}		
				
				else {
					
					//check if product of selected type is available in products. Else, prompt to choose another type.
					 AddProductService addProductService = new AddProductService();
					 
					if (addProductService.checkProductExistsInProductWarehouse(productType) == true) {
						
						productNameComboBox.setModel(new DefaultComboBoxModel(new String[] {SELECT_NAME}));
						
						//set names to null values after product type is chosen and then populate
						System.out.println("Product type : " +productType);
						List<String> result = addProductService.readAll(productType);

						for(String productName : result)
							System.out.println("Product Name: " + productName);

						//set Product names according to product type chosen
						DefaultComboBoxModel model1 = (DefaultComboBoxModel) productNameComboBox.getModel();
						
						for (String item : result) {
							model1.addElement(item);
						}

						// setting model with new data
						productNameComboBox.setModel(model1);
						
					}
					
					else {
						
						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, productType+ " out of stock. Please select another item. ");
						quantityTextField.setText("");
					}
					
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
					quantityTextField.setText("");
				}

				Object quantity = addProductService.readProductQuantity(productName, (String)productTypeComboBox.getSelectedItem());
				quantityAvailableTextField.setText(quantity.toString());				
			}
		});


		JButton btnNewButton = new JButton("Add Product\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//add product to vending machine table
				//subtract qty from product table

				String vendingMachineId = (String) vendingMachineComboBox.getSelectedItem();
				String productType = (String) productTypeComboBox.getSelectedItem();
				String productName = (String) productNameComboBox.getSelectedItem();				
				//Integer requiredQuantity = Integer.parseInt(quantityTextField.getText());


				if (vendingMachineId.equals(SELECT_MACHINE) || productType.equals(SELECT_TYPE) || productName.equals(SELECT_NAME) || quantityTextField.getText().equals(null)){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select valid options");
					quantityTextField.setText("");
				}

				else{

					Integer availableQuantity = Integer.parseInt(addProductService.readProductQuantity(productName, productType).toString());
					Integer requiredQuantity = Integer.parseInt(quantityTextField.getText()) ;
					//retrieve the product id from type and name
					String id = addProductService.getProductId(productName, productType).toString();

					EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
					EntityManager entityManager = entityManagerFactory.createEntityManager();
					VendingMachineProductService vendingMachineProductService = new VendingMachineProductService(entityManager);

					if( requiredQuantity > availableQuantity ){

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, "Entered quantity not available. Please enter a value within the available range.");
						quantityTextField.setText("");
					}
					//check if product quantity is greater than available product quantity. If not, deduct and store
					// if product already exists in vending machine, update the table
					else{

						vendingMachineProductService.checkProductExistsAndAddProduct(vendingMachineId, id, requiredQuantity);
						
						quantityAvailableTextField.setText(vendingMachineProductService.getUpdatedQuantity(id).toString());

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null,requiredQuantity+ " " +productName+ " added to vending machine " +vendingMachineId+ ".");
						quantityTextField.setText("");
					}				

					//delete the quantity from product table and store value. Also populate the quantity available field

				}


			}

		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		btnNewButton.setBounds(377, 557, 223, 40);
		contentPane.add(btnNewButton);

		JLabel lblQuantityRequired = new JLabel("Quantity Required\r\n");
		lblQuantityRequired.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblQuantityRequired.setBounds(206, 438, 243, 35);
		contentPane.add(lblQuantityRequired);

		quantityAvailableTextField = new JTextField();
		quantityAvailableTextField.setEditable(false);
		quantityAvailableTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		quantityAvailableTextField.setColumns(10);
		quantityAvailableTextField.setBounds(483, 363, 251, 35);
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
		lblNewLabel.setIcon(new ImageIcon(AddProductToVMView.class.getResource("/coen359/vendingmachine/resources/Products to VM.png")));
		lblNewLabel.setBounds(807, 52, 479, 607);
		contentPane.add(lblNewLabel);
	}
}
