package coen359.vendingmachine.user.smartcard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import coen359.vendingmachine.smartcard.SmartCardService;

public class UserAddSmartCard extends JPanel {
	private JTextField monthTextField;
	private JTextField yearTextField;
	private JTextField smartCardTypeTextField;
	private JTextField smartCardIdTextField;
	private JTextField monthDisplayTextField;
	private JTextField smartIdDisplayTextField;
	private JTextField yearDisplayTextField;
	private JTextField valueDisplayTextField;
	private JComboBox smartCardValueComboBox;

	/**
	 * Create the panel.
	 */
	public UserAddSmartCard() {
		setBackground(new Color(255, 255, 255));

		setSize(1260, 700);
		setVisible(true);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(666, 5, 10, 10);
		add(panel);

		JLabel lblRequestForSmart = new JLabel("REQUEST FOR SMART CARD");
		lblRequestForSmart.setBounds(331, 48, 420, 49);
		lblRequestForSmart.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		add(lblRequestForSmart);

		JLabel lblSmartCardId = new JLabel("Smart Card ID");
		lblSmartCardId.setBounds(52, 278, 215, 36);
		lblSmartCardId.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(lblSmartCardId);

		smartCardIdTextField = new JTextField();
		smartCardIdTextField.setBounds(297, 280, 265, 33);
		smartCardIdTextField.setEditable(false);
		smartCardIdTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(smartCardIdTextField);
		smartCardIdTextField.setColumns(10);

		/*smartCardIdTextField.setText(new SmartCardService().getIncrementedSmartCardId());*/

		JLabel lblSmartCardName = new JLabel("Smart Card Type\r\n");
		lblSmartCardName.setBounds(52, 377, 215, 36);
		lblSmartCardName.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(lblSmartCardName);

		smartCardTypeTextField = new JTextField();
		smartCardTypeTextField.setBounds(297, 379, 265, 33);
		smartCardTypeTextField.setEditable(false);
		smartCardTypeTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		smartCardTypeTextField.setColumns(10);
		add(smartCardTypeTextField);

		//smartCardTypeTextField.setText("Smart Cal Card");

		JLabel lblSmartCardValue = new JLabel("Smart Card Value\r\n");
		lblSmartCardValue.setBounds(52, 175, 215, 36);
		lblSmartCardValue.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(lblSmartCardValue);

		final JComboBox smartCardValueComboBox = new JComboBox();
		smartCardValueComboBox.setBounds(297, 177, 265, 33);
		smartCardValueComboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		smartCardValueComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT AMOUNT]", "10", "20", "50"}));
		add(smartCardValueComboBox);


		//smartCardIdTextField.setText(new SmartCardService().getIncrementedSmartCardId());

		JLabel label = new JLabel("");
		label.setBounds(76, 490, 69, 20);
		add(label);

		JLabel lblExpiryDate = new JLabel("Expiry Date\r\n");
		lblExpiryDate.setBounds(52, 474, 215, 36);
		lblExpiryDate.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(lblExpiryDate);

		monthTextField = new JTextField();
		monthTextField.setBounds(367, 477, 37, 33);
		monthTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		monthTextField.setEditable(false);
		add(monthTextField);
		monthTextField.setColumns(10);

		final DateFormat dateFormat = new SimpleDateFormat("MM");
		final Date date = new Date();
		//System.out.println(dateFormat.format(date));
		//monthTextField.setText(dateFormat.format(date));

		JLabel lblNewLabel = new JLabel("Month\r\n");
		lblNewLabel.setBounds(297, 490, 69, 20);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		add(lblNewLabel);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(419, 490, 50, 20);
		lblYear.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		add(lblYear);

		yearTextField = new JTextField();
		yearTextField.setBounds(472, 476, 94, 33);
		yearTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		yearTextField.setEditable(false);
		yearTextField.setColumns(10);
		add(yearTextField);

		//add 2 years to the current date and set the expiry year
		final Calendar date1 = Calendar.getInstance();
		date1.setTime(new Date());

		final Format f = new SimpleDateFormat("yyyy");
		date1.add(Calendar.YEAR,2);

		//yearTextField.setText(f.format(date1.getTime()));

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				smartCardIdTextField.setText(new SmartCardService().getIncrementedSmartCardId());
				smartCardTypeTextField.setText("Smart Cal Card");
				monthTextField.setText(dateFormat.format(date));
				yearTextField.setText(f.format(date1.getTime()));


			}
		};
		smartCardValueComboBox.addActionListener(actionListener);


		JButton btnNewButton = new JButton("COLLECT CARD");
		btnNewButton.setBounds(52, 570, 283, 49);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (smartCardValueComboBox.getSelectedItem().equals("[SELECT AMOUNT]")){

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please select desired amount for smart card.");
				}

				else {
					Integer smartCardAmount = Integer.parseInt((String)smartCardValueComboBox.getSelectedItem());

					new SmartCardService().createSmartCard(smartCardIdTextField.getText(), "Smart Cal", Integer.parseInt(monthTextField.getText()), Integer.parseInt(yearTextField.getText()), smartCardAmount);

					smartIdDisplayTextField.setText(smartCardIdTextField.getText());
					valueDisplayTextField.setText("$ " +smartCardAmount.toString());
					monthDisplayTextField.setText(monthTextField.getText());
					yearDisplayTextField.setText(yearTextField.getText());

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please save the smart card details for future use. Press DONE after the card details has been collected. ");

					smartCardIdTextField.setText("");
					smartCardTypeTextField.setText("");
					smartCardValueComboBox.setModel(new DefaultComboBoxModel(new String[] {"[SELECT AMOUNT]", "10", "20", "50"}));
					monthTextField.setText("");
					yearTextField.setText("");


				}
			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("$");
		lblNewLabel_1.setBounds(282, 178, 13, 30);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(lblNewLabel_1);

		smartIdDisplayTextField = new JTextField();
		smartIdDisplayTextField.setBounds(649, 514, 180, 38);
		smartIdDisplayTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		smartIdDisplayTextField.setForeground(new Color(255, 255, 255));
		smartIdDisplayTextField.setBackground(new Color(0, 0, 128));
		add(smartIdDisplayTextField);
		smartIdDisplayTextField.setColumns(10);

		monthDisplayTextField = new JTextField();
		monthDisplayTextField.setBounds(921, 514, 50, 38);
		monthDisplayTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		monthDisplayTextField.setForeground(new Color(255, 255, 255));
		monthDisplayTextField.setBackground(new Color(0, 0, 128));
		add(monthDisplayTextField);
		monthDisplayTextField.setColumns(10);

		yearDisplayTextField = new JTextField();
		yearDisplayTextField.setBounds(1063, 514, 103, 38);
		yearDisplayTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		yearDisplayTextField.setForeground(new Color(255, 255, 255));
		yearDisplayTextField.setColumns(10);
		yearDisplayTextField.setBackground(new Color(0, 0, 128));
		add(yearDisplayTextField);

		JLabel lblNewLabel_2 = new JLabel("Month\r\n");
		lblNewLabel_2.setBounds(864, 517, 50, 36);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		add(lblNewLabel_2);

		JLabel lblYear_1 = new JLabel("Year");
		lblYear_1.setBounds(1003, 517, 45, 36);
		lblYear_1.setForeground(new Color(255, 255, 255));
		add(lblYear_1);

		valueDisplayTextField = new JTextField();
		valueDisplayTextField.setBounds(1072, 346, 127, 85);
		valueDisplayTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		valueDisplayTextField.setForeground(new Color(255, 255, 255));
		valueDisplayTextField.setBackground(new Color(0, 191, 255));
		add(valueDisplayTextField);
		valueDisplayTextField.setColumns(10);

		JLabel L = new JLabel("");
		L.setBounds(577, 185, 746, 398);
		L.setIcon(new ImageIcon(UserAddSmartCard.class.getResource("/coen359/vendingmachine/resources/User Smart Cal Card1.png")));
		add(L);

		JButton btnNewButton_1 = new JButton("DONE");
		btnNewButton_1.setBounds(367, 570, 199, 49);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				monthDisplayTextField.setText("");
				yearDisplayTextField.setText("");
				smartIdDisplayTextField.setText("");
				valueDisplayTextField.setText("");
				setVisible(false);
				new UserAddSmartCard().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		add(btnNewButton_1);


	}
}
