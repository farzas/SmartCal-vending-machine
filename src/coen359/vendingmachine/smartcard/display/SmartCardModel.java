package coen359.vendingmachine.smartcard.display;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

import coen359.vendingmachine.smartcard.SmartCard;
import coen359.vendingmachine.smartcard.SmartCardService;
import coen359.vendingmachine.statistics.revenue.VendingMachineProduct;



public class SmartCardModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Used in persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;	

	private SmartCardService smartCardService;

	// stores the model data in a List collection of type Product
	List<SmartCard> smartCardList;   

	// This field contains additional information about the results
	int numcols, numrows;



	public SmartCardModel() {

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		smartCardService = new SmartCardService();

		// read all the records from product
		smartCardList = smartCardService.readAll();

		// update the number of rows and columns in the model
		numrows = smartCardList.size();
		numcols = SmartCard.getNumberOfColumns();

	}

	// create a new table model using the existing data in list
	public SmartCardModel(List<SmartCard> list, EntityManager entityManager)  {

		this.entityManager = entityManager; 
		smartCardService = new SmartCardService();

		smartCardList = list;

		numrows = smartCardList.size();
		numcols = VendingMachineProduct.getNumberOfColumns();
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

	// returns the data at the given row and column number
	public Object getValueAt(int row, int col) {

		try {

			switch (col) {

			case 0: {
				return String.valueOf(smartCardList.get(row).getId());
			}
			case 1: {
				return String.valueOf(smartCardList.get(row).getType());

			}
			case 2: return smartCardList.get(row).getMonth();
			case 3: return smartCardList.get(row).getYear();
			case 4: return smartCardList.get(row).getBalance();

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

			return SmartCard.getColumnName(col);
		} 
		catch (Exception err) {

			return err.toString();
		}    

	}

	public List<SmartCard> getList() {

		return smartCardList;
	}

	public EntityManager getEntityManager() {

		return entityManager;
	}

	// In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	// The argument to this method is an array containing the data in the textfields of the new row.
	public void addRow(Object[] array) throws NumberFormatException, ParseException {

		//public void addRow(Object[] array){

		// add row to database
		EntityTransaction userTransaction = entityManager.getTransaction();  
		userTransaction.begin();

		// while creating product, nutritionaInfo will also be persisted
		//id, type, month, year, balance
		SmartCard product_row = smartCardService.createSmartCard((String) array[0], (String) array[1], (Integer) array[2], (Integer) array[3], (Integer) array[4]); 
		userTransaction.commit();

		// set the current row to rowIndex
		smartCardList.add(product_row);

		int row = smartCardList.size(); 

		numrows++;

		fireTableRowsInserted(row, row);
	}	

}


