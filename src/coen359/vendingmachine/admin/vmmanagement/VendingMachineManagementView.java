package coen359.vendingmachine.admin.vmmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.admin.moneymanagement.AdminMoneyRefillView;
import coen359.vendingmachine.admin.userfeedback.UserFeedbackView;
import coen359.vendingmachine.admin.viewallvendingmachines.VendingMachineView;


import coen359.vendingmachine.statistics.revenue.VendingStatisticsManager;
import coen359.vendingmachine.statistics.vmproduct.VendingMachineProductView;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

public class VendingMachineManagementView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VendingMachineManagementView() {
		
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(UserFeedbackView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UserFeedbackView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UserFeedbackView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UserFeedbackView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception  e) {
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		
		
		
		setTitle("VENDING MACHINE MANAGEMENT\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VendingMachineManagementView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
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
		btnHome.setBounds(15, 16, 124, 35);
		contentPane.add(btnHome);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				LoginView.main(null);
			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		btnNewButton.setBounds(181, 16, 134, 35);
		contentPane.add(btnNewButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		backButton.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		backButton.setBounds(353, 16, 134, 35);
		contentPane.add(backButton);
		
		JButton button = new JButton("VIEW EXISTING VENDING MACHINES");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new VendingMachineView().setVisible(true);
			}
		});
		button.setBackground(new Color(255, 255, 255));
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button.setBounds(402, 90, 525, 53);
		contentPane.add(button);
		
		JButton button_1 = new JButton("ADD NEW VENDING MACHINE\r\n");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new AddVendingMachineView().setVisible(true);
			}
		});
		button_1.setBackground(new Color(255, 255, 255));
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_1.setBounds(402, 179, 525, 53);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("ADD PRODUCTS TO VENDING MACHINE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new AddProductToVMView().setVisible(true);
			}
		});
		button_2.setBackground(new Color(255, 255, 255));
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_2.setBounds(402, 272, 525, 53);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("REMOVE PRODUCT FROM VENDING MACHINE");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new RemoveProductFromVMView().setVisible(true);
			}
		});
		button_3.setBackground(new Color(255, 255, 255));
		button_3.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_3.setBounds(402, 362, 525, 53);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("PRODUCT STATUS IN VENDING MACHINES");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				new VendingMachineProductView().setVisible(true);
			}
		});
		button_4.setBackground(new Color(255, 255, 255));
		button_4.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_4.setBounds(402, 458, 525, 53);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("REFILL MONEY IN VENDING MACHINES");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new AdminMoneyRefillView().setVisible(true);
			}
		});
		button_5.setBackground(new Color(255, 255, 255));
		button_5.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_5.setBounds(402, 552, 525, 53);
		contentPane.add(button_5);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VendingMachineManagementView.class.getResource("/coen359/vendingmachine/resources/Management1.png")));
		lblNewLabel.setBounds(0, 0, 1338, 704);
		contentPane.add(lblNewLabel);
	}
}
