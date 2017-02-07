package coen359.vendingmachine.smartcard;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;



import coen359.vendingmachine.admin.LoginView;
import coen359.vendingmachine.admin.ManagementView;
import java.awt.Color;
import javax.swing.ImageIcon;

public class RemoveSmartCardView extends JFrame {

	private JPanel contentPane;
	private JTextField smartCardIdTextField;
	private JTextField smartCardTypeTextField;

	
	public RemoveSmartCardView() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RemoveSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RemoveSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RemoveSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RemoveSmartCardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		setTitle("REMOVE SMART CARD\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddSmartCardView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(contentPane);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new SmartCardManagementView().setVisible(true);
			}
		});
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button.setBounds(343, 29, 124, 35);
		contentPane.add(button);

		JButton button_1 = new JButton("Home");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new ManagementView().setVisible(true);
			}
		});
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_1.setBounds(41, 29, 124, 35);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Log Out");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				LoginView.main(null);
			}
		});
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button_2.setBounds(193, 29, 124, 35);
		contentPane.add(button_2);

		JLabel lblSmartCardId = new JLabel("Smart Card Id\r\n");
		lblSmartCardId.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblSmartCardId.setBounds(193, 130, 209, 35);
		contentPane.add(lblSmartCardId);

		smartCardIdTextField = new JTextField();
		smartCardIdTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		smartCardIdTextField.setBounds(456, 132, 315, 34);
		contentPane.add(smartCardIdTextField);
		smartCardIdTextField.setColumns(10);

		JLabel lblSmartCardType = new JLabel("Smart Card Type\r\n\r\n");
		lblSmartCardType.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblSmartCardType.setBounds(193, 232, 209, 35);
		contentPane.add(lblSmartCardType);


		smartCardTypeTextField = new JTextField();
		smartCardTypeTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		smartCardTypeTextField.setColumns(10);
		smartCardTypeTextField.setBounds(456, 234, 315, 34);
		contentPane.add(smartCardTypeTextField);

		JButton removeSmartCardButton = new JButton("REMOVE SMART CARD");
		removeSmartCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				SmartCardService smartCardService = new SmartCardService();
				
				

				if ( smartCardIdTextField.getText().equals("") || smartCardTypeTextField.getText().equals("")){
					
					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					UIManager.put("Button.font",  new Font("System", Font.PLAIN, 22));

					JOptionPane.showMessageDialog(null, "Please enter values in all the fields.");
									
					
				}
				
				
				// check if the entered values are valid
				else if (!smartCardService.checkIfSmartCardExists(smartCardIdTextField.getText())) {
					
									
					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					UIManager.put("Button.font",  new Font("System", Font.PLAIN, 20));

					JOptionPane.showMessageDialog(null, "Smart Card with given id does not exist. Please verify the entered value. ");
					smartCardIdTextField.setText("");
					smartCardTypeTextField.setText("");
					
				}
				
				else if (smartCardService.checkIfSmartCardExists(smartCardIdTextField.getText())){
					
					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					UIManager.put("Button.font",  new Font("System", Font.PLAIN, 22));
					
					JOptionPane.showConfirmDialog(null,"Are you sure you want to delete? ", "DELETE SMARTCARD?? ", JOptionPane.YES_NO_OPTION);
					int dialogButton = JOptionPane.YES_NO_OPTION;

					final JOptionPane optionPane = new JOptionPane(
							"Are you sure you want to delete?",
							JOptionPane.QUESTION_MESSAGE,
							dialogButton);

					if(dialogButton == 0) {

						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						UIManager.put("Button.font",  new Font("System", Font.PLAIN, 20));

						JOptionPane.showMessageDialog(null, "Smart Card deleted.");
						
						String id = smartCardIdTextField.getText();
						System.out.println("Smart Card Id" +id);
						
						smartCardService.deleteSmartCard(id);
						
						smartCardIdTextField.setText("");
						smartCardTypeTextField.setText("");
						
					} 

					else {
						System.out.println("No Option");

						smartCardIdTextField.setText("");
						smartCardTypeTextField.setText("");

						new RemoveSmartCardView().setVisible(true);
					} 
				}
				
			

			}

		});
		removeSmartCardButton.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		removeSmartCardButton.setBounds(346, 344, 259, 45);
		contentPane.add(removeSmartCardButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RemoveSmartCardView.class.getResource("/coen359/vendingmachine/resources/remove smartcard.png")));
		lblNewLabel.setBounds(852, 76, 471, 443);
		contentPane.add(lblNewLabel);

	}
}
