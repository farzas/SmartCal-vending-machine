package coen359.vendingmachine.admin.log;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.ManagementView;

public class AdminLogView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	ManagementView adminManagementView;


	public AdminLogView() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminLogView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setTitle("LOG INFORMATION");

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AdminLogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AdminLogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AdminLogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AdminLogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(contentPane);

		JTextPane txtpnLogDetails = new JTextPane();
		txtpnLogDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 27));
		txtpnLogDetails.setText("Log Details");
		txtpnLogDetails.setBounds(140, 121, 597, 690);
		contentPane.add(txtpnLogDetails);



		JScrollPane scrollPane = new JScrollPane(txtpnLogDetails);
		scrollPane.setBounds(75, 121, 662, 690);
		contentPane.add(scrollPane);

		//try { 
			
		/*	List<AdminLog> adminLogs = AdminLogService.createAdminLogService().readAll();
			
			StringBuilder stringBuilder = new StringBuilder();
			for (AdminLog adminLog : adminLogs) {
				
				stringBuilder.append(adminLog.toString() + "\n");
			}
			
			txtpnLogDetails.setText(stringBuilder.toString());*/
			
			String readString = ""; 
			String mergeString = ""; 
			String path = "C:\\Users\\FarzaShereef\\workspace\\vendingmachine1\\src\\coen359\\vendingmachine\\resources\\LogFile.txt";
			File file = new File(path);
			FileReader fileReader = new FileReader(file); 

			BufferedReader in = new BufferedReader(fileReader); 
			while ((readString = in.readLine()) != null) { 
				mergeString = mergeString + readString + "\n"; 
			} 

			txtpnLogDetails.setText(mergeString);

			JButton btnBack = new JButton("BACK");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					setVisible(false);
					new ManagementView().setVisible(true);
				}
			});

			JLabel lblLogInfo = new JLabel("LOG INFO");
			lblLogInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
			lblLogInfo.setBounds(361, 16, 312, 42);
			contentPane.add(lblLogInfo);

			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
			lblUsername.setBounds(140, 74, 115, 36);
			contentPane.add(lblUsername);

			JLabel lblDateTime = new JLabel("Date");
			lblDateTime.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
			lblDateTime.setBounds(399, 74, 205, 36);
			contentPane.add(lblDateTime);

			JButton btnBack_1 = new JButton("BACK");
			btnBack_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
			btnBack_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					setVisible(false);
					new ManagementView().setVisible(true);
				}
			});
			btnBack_1.setBounds(1120, 28, 158, 42);
			contentPane.add(btnBack_1);
			
			JButton button = new JButton("LOG OUT");
			button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					setVisible(false);
					LoginView.main(null);
				}
			});
			button.setBounds(1120, 98, 158, 42);
			contentPane.add(button);
			
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(AdminLogView.class.getResource("/coen359/vendingmachine/resources/new admin log detaails.png")));
			lblNewLabel.setBounds(805, 176, 461, 486);
			contentPane.add(lblNewLabel);

		/*} catch (FileNotFoundException ex) { 
			ex.printStackTrace(); 
		}catch (IOException e){ 
			e.printStackTrace(); 
		}*/

	}
}
