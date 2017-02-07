package coen359.vendingmachine.admin.userfeedback;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.ManagementView;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFeedbackView extends javax.swing.JFrame{
	
private static final long serialVersionUID = 1L;
	
	private JTable jtable1; // the table displayed on the GUI
	private UserFeedbackController feedbackTableController; // glue between model and gui

	public UserFeedbackView(){
		setTitle("USER FEEDBACK");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserFeedbackView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));

		setSize(1360,760);
		feedbackTableController = new UserFeedbackController(this);
		addJTable();
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	    setVisible( true );
	    setLocationRelativeTo(getContentPane());
		

	}

	// creates a table and adds it to the GUI. 
	// Attaches the controller as a listener to the table
	public void addJTable() {
		// add the data and column names to a JTable
		jtable1 = new JTable(feedbackTableController.getTableModel());
		jtable1.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
		jtable1.setRowHeight(30);
		jtable1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));

		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(jtable1);
		
		getContentPane().add(scrollpane);

	}
	
	

	public void updateTable() {
		jtable1.setModel(feedbackTableController.getTableModel());
	}


}
