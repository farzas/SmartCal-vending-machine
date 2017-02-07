package coen359.vendingmachine.smartcard.display;


import java.awt.Font;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;

public class SmartCardView extends javax.swing.JFrame{

	private static final long serialVersionUID = 1L;

	private JTable jtable1; // the table displayed on the GUI
	private SmartCardController smartCardController; // glue between model and gui

	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;
	
	
	public SmartCardView(){
		setTitle("SMART CARDS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SmartCardView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));

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
			java.util.logging.Logger.getLogger(SmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		setLocationRelativeTo(getContentPane());
		setSize(1360,760);
		
		smartCardController = new SmartCardController(this);
		addJTable();
		//add(backBtn);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getContentPane());;
		setVisible( true );
	}

	// creates a table and adds it to the GUI. 
	// Attaches the controller as a listener to the table
	public void addJTable() {
		// add the data and column names to a JTable
		jtable1 = new JTable(SmartCardController.getTableModel());
		jtable1.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
		jtable1.setRowHeight(30);
		jtable1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));

		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(jtable1);

		getContentPane().add(scrollpane);

	}

	public void updateTable() {
		jtable1.setModel(smartCardController.getTableModel());
	}

}

