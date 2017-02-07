package coen359.vendingmachine.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import coen359.vendingmachine.admin.log.AdminLogFileWriter;
import coen359.vendingmachine.admin.log.AdminLogView;
import coen359.vendingmachine.admin.moneymanagement.AdminMoneyCollectView;
import coen359.vendingmachine.admin.userfeedback.UserFeedbackMain;
import coen359.vendingmachine.admin.vmmanagement.VendingMachineManagementView;
import coen359.vendingmachine.product.ProductView;
import coen359.vendingmachine.smartcard.SmartCardManagementView;
import coen359.vendingmachine.statistics.revenue.VendingStatisticsManager;

import java.awt.Color;
import java.io.IOException;

public class ManagementView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	ProductView productView = new ProductView();
	AdminLogView adminLogView;

	
	public ManagementView() {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		

		setTitle("ADMINISTRATOR MANAGEMENT\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagementView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setLocationRelativeTo(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton productManagementButton = new JButton("PRODUCT MANAGEMENT");
		//productManagementButton.setForeground(new Color(0, 0, 0));
		//productManagementButton.setBackground(new Color(255, 255, 255));
		productManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//productManagementButtonActionPerformed(e);
				setVisible(false);
				productView.setVisible(true);

			}
		});


		productManagementButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		productManagementButton.setBounds(350, 16, 664, 53);
		panel.add(productManagementButton);

		JButton vendingMachineManagementButton = new JButton("VENDING MACHINE MANAGEMENT");
		//vendingMachineManagementButton.setForeground(new Color(0, 0, 0));
		//vendingMachineManagementButton.setBackground(new Color(255, 255, 255));
		vendingMachineManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new VendingMachineManagementView().setVisible(true);;
			}
		});
		vendingMachineManagementButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		vendingMachineManagementButton.setBounds(350, 106, 664, 53);
		panel.add(vendingMachineManagementButton);

		JButton smartCardButton = new JButton("SMART CARD MANAGEMENT");
		//smartCardButton.setForeground(new Color(0, 0, 0));
		//smartCardButton.setBackground(new Color(255, 255, 255));
		smartCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new SmartCardManagementView().setVisible(true);
			}
		});
		smartCardButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		smartCardButton.setBounds(350, 401, 664, 53);
		panel.add(smartCardButton);

		JButton performanceButton = new JButton("SALES STATISTICS");
		//performanceButton.setForeground(new Color(0, 0, 0));
		//performanceButton.setBackground(new Color(255, 255, 255));
		performanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new VendingStatisticsManager().setVisible(true);
			}
		});
		performanceButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		performanceButton.setBounds(350, 490, 664, 53);
		panel.add(performanceButton);

		JButton userFeedbackButton = new JButton("COLLECT USER FEEDBACK");
		//userFeedbackButton.setForeground(new Color(0, 0, 0));
		//userFeedbackButton.setBackground(new Color(255, 255, 255));
		userFeedbackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserFeedbackMain.main(null);
			}
		});
		userFeedbackButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		userFeedbackButton.setBounds(350, 575, 664, 53);
		panel.add(userFeedbackButton);

		JButton logDetailsButton = new JButton("LOG DETAILS");
		//logDetailsButton.setForeground(new Color(0, 0, 0));
		//logDetailsButton.setBackground(new Color(255, 255, 255));
		logDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					loadFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		logDetailsButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		logDetailsButton.setBounds(350, 302, 664, 53);
		panel.add(logDetailsButton);

		JButton btnLogOut = new JButton("Log Out");
		//btnLogOut.setForeground(new Color(0, 0, 0));
		//btnLogOut.setBackground(new Color(255, 255, 255));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);
			}
		});
		btnLogOut.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnLogOut.setBounds(15, 16, 115, 29);
		panel.add(btnLogOut);

		JButton collectMoneyButton = new JButton("COLLECT MONEY\r\n");
		//collectMoneyButton.setForeground(new Color(0, 0, 0));
		//collectMoneyButton.setBackground(new Color(255, 255, 255));
		collectMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new AdminMoneyCollectView().setVisible(true);
			}
		});
		collectMoneyButton.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		collectMoneyButton.setBounds(350, 204, 664, 53);
		panel.add(collectMoneyButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ManagementView.class.getResource("/coen359/vendingmachine/resources/Management.png")));
		lblNewLabel.setBounds(0, 0, 1328, 694);
		panel.add(lblNewLabel);
	}


	protected void loadFile() throws IOException {

		setVisible(false);
		AdminLogFileWriter.main(null);
		adminLogView = new AdminLogView();
		adminLogView.setVisible(true); // null pointer exception

	}


}
