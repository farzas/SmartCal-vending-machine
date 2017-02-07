package coen359.vendingmachine.smartcard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JButton;

import coen359.vendingmachine.admin.ManagementView;
import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.smartcard.display.SmartCardView;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.ImageIcon;

public class SmartCardManagementView extends JFrame {

	private JPanel contentPane;

	
	public SmartCardManagementView() {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SmartCardManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SmartCardManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SmartCardManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SmartCardManagementView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		setTitle("SMART CARD MANAGEMENT\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SmartCardManagementView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(contentPane);

		JButton button = new JButton("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button.setBounds(27, 35, 124, 35);
		contentPane.add(button);

		JButton button_1 = new JButton("Log Out");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);
			}
		});
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_1.setBounds(179, 35, 124, 35);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new ManagementView().setVisible(true);
				}
		});
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_2.setBounds(329, 35, 124, 35);
		contentPane.add(button_2);
		
		JButton btnNewButton = new JButton("ADD SMART CARD\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new AddSmartCardView().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		btnNewButton.setBounds(209, 167, 412, 49);
		contentPane.add(btnNewButton);
		
		JButton btnRemoveSmartCard = new JButton("REMOVE SMART CARD\r\n");
		btnRemoveSmartCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new RemoveSmartCardView().setVisible(true);
			}
		});
		btnRemoveSmartCard.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		btnRemoveSmartCard.setBounds(209, 267, 412, 49);
		contentPane.add(btnRemoveSmartCard);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SmartCardManagementView.class.getResource("/coen359/vendingmachine/resources/addremove smart card.png")));
		lblNewLabel.setBounds(666, 62, 623, 464);
		contentPane.add(lblNewLabel);
		
		JButton button_3 = new JButton("VIEW SMART CARD LIST\r\n");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new SmartCardView().setVisible(true);
			}
		});
		button_3.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_3.setBounds(209, 368, 412, 49);
		contentPane.add(button_3);
	}

}
