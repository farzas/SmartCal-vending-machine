package coen359.vendingmachine.admin;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import coen359.vendingmachine.admin.authentication.AuthenticateStrategy;
import coen359.vendingmachine.admin.authentication.PasswordStrategy;
import coen359.vendingmachine.admin.log.AdminLogService;
import coen359.vendingmachine.admin.userfeedback.UserFeedbackView;
import coen359.vendingmachine.product.ProductView;
import coen359.vendingmachine.supervisor.SupervisorView;
import coen359.vendingmachine.user.management.Index;
import coen359.vendingmachine.user.payment.VMandPayMediator;

import javax.swing.ImageIcon;

import java.awt.Color;

public class LoginView extends JFrame {

	private AuthenticateStrategy authenticateStrategy;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel cards;
	private CardLayout cardLayout = new CardLayout();


	ProductView productView = new ProductView();
	ManagementView adminManagementView = new ManagementView();
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;

	VMandPayMediator mediator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {


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
				try {
					LoginView frame = new LoginView();

					//frame.setSize(1934, 1048);

					//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

					//To set the frame in the center of window
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
					int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
					frame.setLocation(x, y);
					frame.setVisible(true);



				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
/*
	public LoginView(VMandPayMediator mediator){

		this.mediator = mediator;
		mediator.removeBeverages();
		mediator.addBeverages();
	}*/


	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/coen359/vendingmachine/resources/Vending Machine.png")));	

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		
		setTitle("ADMINISTRATOR/SUPERVISOR LOGIN");
		setBounds(100, 100, 1360, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			

		cards = new JPanel(cardLayout);
		cards.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cards);
		cards.setLayout(cardLayout);
		cardLayout.addLayoutComponent(cards, "ADMINISTRAT");


		//cards.add(productView);


		JPanel card1 = new JPanel();
		card1.setBackground(new Color(255, 255, 255));
		cards.add(card1);
		card1.setLayout(null);
		card1.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginView.class.getResource("/coen359/vendingmachine/resources/Login.png")));
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		label.setBounds(62, -13, 1251, 392);
		card1.add(label);

		JLabel label_1 = new JLabel();
		label_1.setText("Username");
		label_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		label_1.setBounds(608, 395, 164, 42);
		card1.add(label_1);

		userNameTextField = new JTextField();
		userNameTextField.setText("admin");
		userNameTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		userNameTextField.setColumns(1);
		userNameTextField.setBounds(799, 397, 209, 42);
		card1.add(userNameTextField);

		passwordTextField = new JPasswordField();
		passwordTextField.setText("admin");
		passwordTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		passwordTextField.setBounds(799, 481, 209, 41);
		card1.add(passwordTextField);

		JLabel label_2 = new JLabel();
		label_2.setText("Password");
		label_2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		label_2.setBounds(620, 481, 164, 53);
		card1.add(label_2);

		JButton button = new JButton("Login");
		button.setIcon(new ImageIcon(LoginView.class.getResource("/coen359/vendingmachine/resources/Untitled.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//create a separate class to perform the validations in order to implement strategy pattern
				String userName = userNameTextField.getText();
				String password = passwordTextField.getText();
				
				authenticateStrategy = new PasswordStrategy(password);

				Admin result = new AdminService().readAdmin(userName);
				System.out.println("Admin : " +result);

				if(result!= null){      

					if ( userName.equals("admin") && authenticateStrategy.authenticateLogin(userName)){

						createAdminLog(userName);
						loginButtonActionPerformed(e);	
					}

					else if (userName.equals("supervisor") && authenticateStrategy.authenticateLogin(userName)) {

						createAdminLog(userName);
						setVisible(false);
						// SupervisorView.main (null); ----> if Jframe
						new SupervisorView().setVisible(true); //---> If panel and all the components are intialized in panel.
					}


					else{
						UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
						UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
						JOptionPane.showMessageDialog(null, "Incorrect password. Please enter valid credentials.");
						clearButtonActionPerformed(e); 
					}

				}

				else{

					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
					JOptionPane.showMessageDialog(null, "Please enter valid credentials.");
					clearButtonActionPerformed(e); 
				}

			}

			private void clearButtonActionPerformed(ActionEvent e) {
				userNameTextField.setText("");
				passwordTextField.setText("");			}
		});
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		button.setBounds(644, 538, 338, 118);
		card1.add(button);

		JButton btnNewButton = new JButton("HEALTHY VEND");
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
		btnNewButton.setBounds(1117, 41, 184, 42);
		card1.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new Index().setVisible(true);
				CardLayout cl = (CardLayout) (Index.getDynamicPanel().getLayout());
		        cl.show(Index.getDynamicPanel(), Index.Vending_MachineView);
			}
		});

	}

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {

		setVisible(false);
		adminManagementView.setVisible(true);


	}

	private void createAdminLog(String userName){		

		String adminLogId = UUID.randomUUID().toString();
		AdminLogService.createAdminLogService().createAdminLog(adminLogId, userName, new Date());	


	}

}
