package coen359.vendingmachine.supervisor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.statistics.vmproduct.VendingMachineProductView;

public class VendingMachineView extends javax.swing.JFrame{
	
private static final long serialVersionUID = 1L;
	
	private JTable jtable1; // the table displayed on the GUI
	private VendingMachineController vendingController; // glue between model and gui
	private JPanel panel1,panel2;
	private JButton homeBtn, logOutBtn, backBtn;
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;

	public VendingMachineView(){
		setTitle("INSTALLED VENDING MACHINES");
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
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(VendingMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		setLocationRelativeTo(getContentPane());

		//setSize(1360,760);
		vendingController = new VendingMachineController(this);
		
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
		
		// add the data and column names to a JTable
				jtable1 = new JTable(vendingController.getTableModel());
				jtable1.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
				jtable1.setRowHeight(30);
				jtable1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));

				// add the table to a scrollpane
				JScrollPane scrollpane = new JScrollPane(jtable1);
		panel2 = new JPanel();
		panel2.add(scrollpane,BorderLayout.CENTER);
		//contentPane = new JPanel();
		//initComponents();
		//addJTable();
		//this.add(contentPane);
		getContentPane().add(panel1,BorderLayout.NORTH);
		getContentPane().add(panel2,BorderLayout.CENTER);
		setDefaultCloseOperation( EXIT_ON_CLOSE );
	    setVisible( true );
	}

	public void updateTable() {
		jtable1.setModel(vendingController.getTableModel());
	}
	
	//Launch the application
	/*public static void main(String[] args) {
		VendingMachineView manager = new VendingMachineView();
		//To set the frame in the center of window
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - manager.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - manager.getHeight()) / 2);
		manager.setLocation(x, y);


		//manager.setDefaultCloseOperation( EXIT_ON_CLOSE );
		manager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		manager.setVisible( true );
	}*/

}

