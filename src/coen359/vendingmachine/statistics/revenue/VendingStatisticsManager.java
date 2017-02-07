package coen359.vendingmachine.statistics.revenue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.supervisor.SupervisorView;

public class VendingStatisticsManager extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3633586330919860172L;
	// Used in persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;
	private JButton backButton;
	private JPanel backPanel;

	public VendingStatisticsManager(){
		
		
		super("Vending Machine Statistics");
		
		setSize(1360, 760);
		setTitle("VENDING MACHINE STATISTICS");
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(VendingStatisticsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(VendingStatisticsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(VendingStatisticsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(VendingStatisticsManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		setLocationRelativeTo(getContentPane());
		
		//setBackground(new Color(245, 245, 220));
		this.setBackground(new Color(245, 245, 220));
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		String queryId = "SELECT vp.vending_machine_id FROM vending_machine_product_purchased vp";

		Query query = entityManager.createQuery(queryId, VendingMachineProductPurchased.class);

		List<String> totalVendingIDs = query.getResultList();

		int countVending = totalVendingIDs.size();

		VendingMachineStats stats[] = new VendingMachineStats[countVending];

		for(int i=0;i<countVending;i++){
			stats[i] = new VendingMachineStats(totalVendingIDs.get(i),entityManager);
		}

		JPanel panels[] = new JPanel[countVending];

		for(int i=0;i<countVending;i++){
			panels[i] = createVendingPanel(stats[i]);
		}

		// create VendingPieChartView to show Account pie chart
		VendingPieChartView pieChartView =
				new VendingPieChartView();

		for(int i=0;i<countVending;i++){
			pieChartView.addVending(stats[i]);;
		}

		// create JPanel for VendingPieChartView
		JPanel pieChartPanel = new JPanel();
		
		TitledBorder pieTitle = new TitledBorder("Vending Machine Revenues");
		pieTitle.setTitleFont(new Font("times new roman",Font.PLAIN,32));

		//pieChartPanel.setBorder(
				//new TitledBorder("Vending Machine Revenues") );
		pieChartPanel.setBorder(pieTitle);
		//pieChartPanel.setPreferredSize(pieChartView.getPreferredSize());
		pieChartPanel.setPreferredSize(new Dimension(600,500));

		pieChartPanel.add( pieChartView );

		// lay out vending machines and pie chart components
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		for(int i=0;i<countVending;i++){
			contentPane.add(panels[i]);
		}

		contentPane.add(pieChartPanel);

		backPanel = new JPanel();
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				setVisible(false);
				new SupervisorView().setVisible(true);

			}
		});
		// backPanel.add(backButton);
		contentPane.add(backButton,BorderLayout.SOUTH);
		setSize( 1360, 760 );	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}	

	public EntityManager getEntityManager() {

		return entityManager;
	}  

	public JPanel createVendingPanel(VendingMachineStats statistics){
		JPanel vendingPanel = new JPanel();
		// set JPanel's border to show Vending machine Id
		vendingPanel.setFont(new Font("times new roman",Font.PLAIN,52));
		TitledBorder title = new TitledBorder(statistics.getVendingID());
		title.setTitleFont(new Font("times new roman",Font.PLAIN,32));
		//vendingPanel.setBorder(
				//new TitledBorder(statistics.getVendingID()));
		vendingPanel.setBorder(title);
		

		// create VendingTextView for Vending machine
		VendingTextView vendingTextView =
				new VendingTextView(statistics);

		vendingPanel.add(vendingTextView);

		return vendingPanel;

	}



}
