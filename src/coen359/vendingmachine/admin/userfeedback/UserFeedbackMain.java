package coen359.vendingmachine.admin.userfeedback;

import java.awt.Dimension;
import java.awt.Toolkit;

public class UserFeedbackMain {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

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

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				UserFeedbackView frame = new UserFeedbackView();
				
				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
				int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
				frame.setLocation(x, y);
				
				frame.setVisible(true);
			}
		});     
	}
}

