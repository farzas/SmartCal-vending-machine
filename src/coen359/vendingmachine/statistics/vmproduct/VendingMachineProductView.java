package coen359.vendingmachine.statistics.vmproduct;


import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.vmproduct.VendingMachineProductService;
import coen359.vendingmachine.supervisor.SupervisorView;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachineProductView extends javax.swing.JFrame{
	
private static final long serialVersionUID = 1L;
	
	private JTable jtable1; // the table displayed on the GUI
	private VendingMachineProductController vendingTableController; // glue between model and gui
	private JButton homeBtn, logOutBtn, backBtn;
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;
	private JPanel panel1,panel2,panel3;

	public VendingMachineProductView(){
		setTitle("PRODUCT STATUS IN VENDING MACHINES");
		setSize(1360,760);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VendingMachineProductView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(VendingMachineProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(VendingMachineProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(VendingMachineProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(VendingMachineProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		setLocationRelativeTo(getContentPane());
	    
	    //setSize(1360,760);
		vendingTableController = new VendingMachineProductController(this);
		
		homeBtn = new JButton("Home");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new SupervisorView().setVisible(true);
			}
		});
		homeBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		
		logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				LoginView.main(null);	
				
			}
		});
		logOutBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new SupervisorView().setVisible(true);
			}
		});
		backBtn.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		panel1 = new JPanel();
		panel1.add(homeBtn);
		panel1.add(logOutBtn);
		panel1.add(backBtn);
		
		panel2 = new JPanel();
		
		JTextArea warningMessageArea = new JTextArea();
		warningMessageArea.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		warningMessageArea.setEditable(false);
		warningMessageArea.setRows(6);
		warningMessageArea.setText("***********MESSAGE**********\n");
		//warningMessageArea.setBounds(168, 98, 452, 255);
		warningMessageArea.setSize(600,  500);
		panel2.add(warningMessageArea);
		
		JScrollPane scrollPane = new JScrollPane(warningMessageArea);
		scrollPane.setBounds(621, 154, -13, -60);
		panel2.add(scrollPane);

		ArrayList<String> restockProducts = new VendingMachineProductService().restockProducts();

		if (restockProducts.size() != 0) {

			warningMessageArea.append("Please restock the following products in these machines!!!\n");
			
			for (String restockProductVM : restockProducts){

				warningMessageArea.append(restockProductVM + "  ");
				warningMessageArea.append("\n");
			}
		}

		else {
			
			warningMessageArea.append("All vending machines have required stock of products.");
		}


		JScrollPane scrollBar = new JScrollPane(warningMessageArea);
		//scrollBar.setBounds(165, 108, 484, 132);
		panel2.add(scrollBar,BorderLayout.CENTER);
		
		// add the data and column names to a JTable
				jtable1 = new JTable(vendingTableController.getTableModel());
				jtable1.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
				jtable1.setRowHeight(30);
				jtable1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));

				// add the table to a scrollpane
				JScrollPane scrollpane = new JScrollPane(jtable1);
				panel3 = new JPanel();
				panel3.add(scrollpane,BorderLayout.CENTER);
		
		//initComponents();
		//addJTable();
		//add(backBtn);
		getContentPane().add(panel1,BorderLayout.NORTH);
		getContentPane().add(panel2,BorderLayout.CENTER);
		getContentPane().add(panel3,BorderLayout.SOUTH);
		//setDefaultCloseOperation( DISPOSE_ON_CLOSE);
	    setVisible( true );
	}

	// creates a table and adds it to the GUI. 
	// Attaches the controller as a listener to the table
	public void addJTable() {
		// add the data and column names to a JTable
		jtable1 = new JTable(vendingTableController.getTableModel());
		jtable1.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
		jtable1.setRowHeight(30);
		jtable1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));

		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(jtable1);
		
		getContentPane().add(scrollpane);

	}

	public void updateTable() {
		jtable1.setModel(vendingTableController.getTableModel());
	}

}
