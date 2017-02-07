package coen359.vendingmachine.supervisor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import coen359.vendingmachine.admin.vmproduct.VendingMachine;
import coen359.vendingmachine.admin.vmproduct.VendingMachineService;

public class VendingMachineModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Used in persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;	

	private VendingMachineService vendingMachineService;

	// stores the model data in a List collection of type Product
	List<VendingMachine> vendingList;   

	// This field contains additional information about the results
	int numcols, numrows;
	
	DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

	VendingMachineModel() {

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		vendingMachineService = new VendingMachineService(entityManager);

		// read all the records from product
		vendingList = vendingMachineService.readAll();

		// update the number of rows and columns in the model
		numrows = vendingList.size();
		numcols = VendingMachine.getNumberOfColumns();
	}

	// create a new table model using the existing data in list
	public VendingMachineModel(List<VendingMachine> list, EntityManager entityManager)  {

		this.entityManager = entityManager; 
		vendingMachineService = new VendingMachineService(entityManager);

		vendingList = list;

		numrows = vendingList.size();
		numcols = VendingMachine.getNumberOfColumns();
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

			case 0: return vendingList.get(row).getId();
			case 1: return vendingList.get(row).getLocation();
			case 2: return vendingList.get(row).getCategory();
			
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
				
				return VendingMachine.getColumnName(col);
			} 
			catch (Exception err) {
				
				return err.toString();
			}    

	}

	public List<VendingMachine> getProductList() {
		
		return vendingList;
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
		VendingMachine feedback_row = vendingMachineService.createVendingMachine((String) array[0], (String) array[1],(String) array[2]);//((String) array[0], (String) array[1], Double.parseDouble((String) array[2]), dateFormatter.parse((String) array[3]), Integer.parseInt((String) array[4]), (String) array[5], (String) array[6], Integer.parseInt((String) array[7]), Integer.parseInt((String) array[8]), Integer.parseInt((String) array[9]));
		userTransaction.commit();

		// set the current row to rowIndex
		vendingList.add(feedback_row);

		int row = vendingList.size(); 

		numrows++;

		fireTableRowsInserted(row, row);
	}	

}
