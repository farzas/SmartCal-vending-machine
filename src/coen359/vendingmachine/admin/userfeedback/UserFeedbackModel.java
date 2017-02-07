package coen359.vendingmachine.admin.userfeedback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

public class UserFeedbackModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Used in persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;	

	private UserFeedbackService userFeedbackService;

	// stores the model data in a List collection of type Product
	List<UserFeedback> feedbackList;   

	// This field contains additional information about the results
	int numcols, numrows;
	
	DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

	UserFeedbackModel() {

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		userFeedbackService = new UserFeedbackService(entityManager);

		// read all the records from product
		feedbackList = userFeedbackService.readAll();

		// update the number of rows and columns in the model
		numrows = feedbackList.size();
		numcols = UserFeedback.getNumberOfColumns();
	}

	// create a new table model using the existing data in list
	public UserFeedbackModel(List<UserFeedback> list, EntityManager entityManager)  {

		this.entityManager = entityManager; 
		userFeedbackService = new UserFeedbackService(entityManager);

		feedbackList = list;

		numrows = feedbackList.size();
		numcols = UserFeedback.getNumberOfColumns();
	}

	// returns a count of the number of rows
	public int getRowCount() {
		return numrows;
	}

	// returns a count of the number of columns
	public int getColumnCount() {
		return numcols;
	}

	// table cells are not editable
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	}

	public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}

	// returns the data at the given row and column number
	public Object getValueAt(int row, int col) {

		try {

			switch (col) {

			case 0: return feedbackList.get(row).getVending_machine_id();
			case 1: return feedbackList.get(row).getDate();
			case 2: return feedbackList.get(row).getFeedback();
			
			}
			return "";
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// returns the name of the column
	public String getColumnName(int col) {	

	
			
			try {
				
				return UserFeedback.getColumnName(col);
			} 
			catch (Exception err) {
				
				return err.toString();
			}    

	}

	public List<UserFeedback> getProductList() {
		
		return feedbackList;
	}

	public EntityManager getEntityManager() {
		
		return entityManager;
	}

	// In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	// The argument to this method is an array containing the data in the textfields of the new row.
	public void addRow(Object[] array) throws NumberFormatException, ParseException {

		// add row to database
		EntityTransaction userTransaction = entityManager.getTransaction();  
		userTransaction.begin();

		UserFeedback feedback_row = userFeedbackService.createUserFeedback((String) array[0], (String) array[1],(String) array[2]);//((String) array[0], (String) array[1], Double.parseDouble((String) array[2]), dateFormatter.parse((String) array[3]), Integer.parseInt((String) array[4]), (String) array[5], (String) array[6], Integer.parseInt((String) array[7]), Integer.parseInt((String) array[8]), Integer.parseInt((String) array[9]));
		userTransaction.commit();

		// set the current row to rowIndex
		feedbackList.add(feedback_row);

		int row = feedbackList.size(); 

		numrows++;

		fireTableRowsInserted(row, row);
	}	

}
