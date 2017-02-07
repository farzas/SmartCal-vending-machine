package coen359.vendingmachine.admin.viewallvendingmachines;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import coen359.vendingmachine.user.management.VendingMachineController;
import java.awt.Toolkit;

public class VendingMachineView extends javax.swing.JFrame{
	
private static final long serialVersionUID = 1L;
	
	private JTable jtable1; // the table displayed on the GUI
	private VendingMachineController vendingController; // glue between model and gui

	public VendingMachineView(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(VendingMachineView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setTitle("EXISTING VENDING MACHINES");
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		setSize(1360,760);
		vendingController = new VendingMachineController(this);
		addJTable();
		setDefaultCloseOperation( DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getContentPane());
	    setVisible( true );
	    
	}

	// creates a table and adds it to the GUI. 
	// Attaches the controller as a listener to the table
	public void addJTable() {
		// add the data and column names to a JTable
		jtable1 = new JTable(vendingController.getTableModel());
		jtable1.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
		jtable1.setRowHeight(30);
		jtable1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));

		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(jtable1);
		
		getContentPane().add(scrollpane);

	}

	public void updateTable() {
		jtable1.setModel(vendingController.getTableModel());
	}

}
