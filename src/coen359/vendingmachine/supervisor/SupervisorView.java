package coen359.vendingmachine.supervisor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.admin.log.AdminLogFileWriter;
import coen359.vendingmachine.admin.log.AdminLogService;
import coen359.vendingmachine.admin.log.AdminLogView;
import coen359.vendingmachine.admin.userfeedback.UserFeedbackMain;
import coen359.vendingmachine.statistics.revenue.VendingStatisticsManager;
import coen359.vendingmachine.statistics.vmproduct.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SupervisorView extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	//ProductView productView = new ProductView();
	//AdminLogView adminLogView = new AdminLogView();


	/**
	 * Create the frame.
	 */
	
	public SupervisorView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SupervisorView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SupervisorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SupervisorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SupervisorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SupervisorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		setTitle("SUPERVISOR MANAGEMENT\r\n");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(SupervisorView.class.getResource("/coen359/vendingmachine/resources/statistics.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(200, 200, 1099, 963);
		
		
		setLocation(250, 150);
		
		
		setSize(1360,760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		//panel.setBackground(SystemColor.control);
		panel.setBackground(new Color(245,245,220));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton installedMachinesButton = new JButton("INSTALLED VENDING MACHINES");
		installedMachinesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//productManagementButtonActionPerformed(e);
				setVisible(false);
				//productView.setVisible(true);
				new VendingMachineView().setVisible(true);

			}
		});


		installedMachinesButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		installedMachinesButton.setBounds(275, 82, 664, 53);
		panel.add(installedMachinesButton);

		JButton vendingMachineSalesButton = new JButton("VENDING MACHINES SALES STATISTICS");
		vendingMachineSalesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new VendingStatisticsManager().setVisible(true);
			}
		});
		vendingMachineSalesButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		vendingMachineSalesButton.setBounds(275, 219, 664, 53);
		panel.add(vendingMachineSalesButton);

		JButton smartCardButton = new JButton("PRODUCT STATUS IN VENDING MACHINES");
		smartCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				//VendingMachineProductView.main(null);
				new VendingMachineProductView().setVisible(true);
			}
		});
		smartCardButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		smartCardButton.setBounds(275, 367, 664, 53);
		panel.add(smartCardButton);

		JButton userFeedbackButton = new JButton("COLLECT USER FEEDBACK");
		userFeedbackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserFeedbackMain.main(null);
			}
		});
		userFeedbackButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		userFeedbackButton.setBounds(275, 496, 664, 53);
		panel.add(userFeedbackButton);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);
			}
		});
		btnLogOut.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnLogOut.setBounds(15, 16, 115, 29);
		panel.add(btnLogOut);
		
		JButton btnViewLogDetails = new JButton("VIEW LOG DETAILS");
		btnViewLogDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		btnViewLogDetails.setBounds(275, 625, 664, 53);
		panel.add(btnViewLogDetails);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\FarzaShereef\\Desktop\\supervisor.png"));
		lblNewLabel.setBounds(0, 0, 1328, 694);
		panel.add(lblNewLabel);
		
		
		/*JButton collectMoneyButton = new JButton("COLLECT MONEY\r\n");
		collectMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				AdminMoneyCollectView.main(null);
			}
		});
		collectMoneyButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		collectMoneyButton.setBounds(275, 817, 664, 53);
		panel.add(collectMoneyButton);*/
		
		//this.getContentPane().setBackground(new Color(245,245,200));
	}
}
