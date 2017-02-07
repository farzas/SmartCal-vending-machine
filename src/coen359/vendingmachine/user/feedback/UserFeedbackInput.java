package coen359.vendingmachine.user.feedback;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import coen359.vendingmachine.admin.ManagementView;

public class UserFeedbackInput extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7043546831460168945L;
	// Used in persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;	
	private JPanel inputFeedback;
	//private JTextField feedback;
	private JTextArea feedback;
	private JLabel enterFeedback;
	private String vending_machine_id;
	private JButton submitButton;
	private UserFeedbackService feedbackService;
	
	public UserFeedbackInput(String vending_machine_id){
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
		this.vending_machine_id = vending_machine_id;   //Get vending machine Id from main panel
		inputFeedback = createInputPanel();
		
		BufferedImage myPicture = null;

        	//String filename = "feedback.jpg";
        	String filepath = "C:\\Users\\FarzaShereef\\workspace\\vendingmachine1\\src\\coen359\\vendingmachine\\resources\\feedback.jpg";     
    		try {
    			myPicture = ImageIO.read(new File(filepath));
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.add(picLabel);
		    this.add(inputFeedback);
		
	}
	
	public JPanel createInputPanel(){
		JPanel inputPanel = new JPanel();
		//inputPanel.setLayout(new GridLayout(2,1));
		JPanel feedbackPanel = new JPanel();
		feedbackPanel.setLayout(new GridLayout(2,1));
		feedbackPanel.setPreferredSize(new Dimension(800,500));
		inputPanel.setPreferredSize(new Dimension(800,700));
		inputPanel.setBackground(Color.WHITE);

		
		enterFeedback = new JLabel("PLEASE ENTER YOUR FEEDBACK HERE:");
		enterFeedback.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		enterFeedback.setForeground(Color.BLACK);
		enterFeedback.setBackground(Color.WHITE);

		feedback = new JTextArea(10,20);
		feedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));

		submitButton = new JButton("SUBMIT");
		submitButton.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		feedbackPanel.add(enterFeedback);
		feedbackPanel.add(feedback);
		//inputPanel.add(enterFeedback,BorderLayout.NORTH);
		//inputPanel.add(feedback,BorderLayout.CENTER);
		inputPanel.add(feedbackPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		inputPanel.add(buttonPanel);
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (feedback.getText().equals("")) {
					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 26));
					JOptionPane.showMessageDialog(null, "Please enter user feedback");
					clearButtonActionPerformed(e); 
				}
				
				try {
					addButtonActionPerformed(e);
					//JOptionPane.showInputDialog(frame, "Enter response code: ");
					UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 							
					UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 26));
					JOptionPane.showMessageDialog(null, "Thanks for the feedback!");
					clearButtonActionPerformed(e);
				} catch (NumberFormatException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//clearButtonActionPerformed(e);
			}
		});
		
		this.setBackground(Color.WHITE);
		return inputPanel;
	}
		
	public void clearButtonActionPerformed(java.awt.event.ActionEvent evt){
		feedback.setText("");
	}
	
	public void addButtonActionPerformed(ActionEvent e) throws NumberFormatException, ParseException{
		
		feedbackService = new UserFeedbackService(entityManager);
		
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		
		Date date = new Date();
		String vending_id = vending_machine_id;
		String dateString = dateFormatter.format(date);
		String feedbackText = feedback.getText();
		
		feedbackService.createUserFeedback(vending_id, dateString, feedbackText);

	}
	
}
	




