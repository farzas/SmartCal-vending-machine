package coen359.vendingmachine.statistics.vmproduct;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

import coen359.vendingmachine.admin.vmproduct.VendingMachineProductService;
import coen359.vendingmachine.statistics.revenue.VendingMachineProduct;

public class VendingMachineProductModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Used in persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;	

	private VendingMachineProductService vendingMachineProductService;

	// stores the model data in a List collection of type Product
	List<VendingMachineProduct> vendingProductList;   

	// This field contains additional information about the results
	int numcols, numrows;
	
	DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

	public VendingMachineProductModel() {

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
		
		/*VendingMachineService vendingService = new VendingMachineService(entityManager);
		vendingService.createVendingMachine("VM10", "Cupertino", "school");
		
		vendingService.createVendingMachine("VM9", "San Jose", "hospital");
		
		ProductService productService = new ProductService(entityManager);
		Date date = new Date();
		productService.createProduct("P10", "Coca Cola", 5.0, date, 5, "Drink", "", 50, 20, 30);
		productService.createProduct("P9", "Twix", 8.0, date, 10, "Choc", "", 100,50, 40);
		
		vendingMachineProductService.createVendingMachineProduct("VM10", "P10", 10);
		vendingMachineProductService.createVendingMachineProduct("VM11", "P9", 9);*/

		vendingMachineProductService = new VendingMachineProductService(entityManager);

		// read all the records from product
		vendingProductList = vendingMachineProductService.readAll();

		// update the number of rows and columns in the model
		numrows = vendingProductList.size();
		numcols = VendingMachineProduct.getNumberOfColumns();
		
	}

	// create a new table model using the existing data in list
	public VendingMachineProductModel(List<VendingMachineProduct> list, EntityManager entityManager)  {

		this.entityManager = entityManager; 
		vendingMachineProductService = new VendingMachineProductService(entityManager);

		vendingProductList = list;

		numrows = vendingProductList.size();
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

	/*public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}*/

	// returns the data at the given row and column number
	public Object getValueAt(int row, int col) {

		try {

			switch (col) {

			case 0: {
				return String.valueOf(vendingProductList.get(row).getVendingMachine().getId());
				}
			case 1: {
				return String.valueOf(vendingProductList.get(row).getProductList().getName());
				
				}
			case 2: return vendingProductList.get(row).getQuantity();
			
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
				
				return VendingMachineProduct.getColumnName(col);
			} 
			catch (Exception err) {
				
				return err.toString();
			}    

	}

	public List<VendingMachineProduct> getList() {
		
		return vendingProductList;
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
		VendingMachineProduct product_row = vendingMachineProductService.createVendingMachineProduct((String) array[0], (String) array[1],(Integer) array[2]);//((String) array[0], (String) array[1], Double.parseDouble((String) array[2]), dateFormatter.parse((String) array[3]), Integer.parseInt((String) array[4]), (String) array[5], (String) array[6], Integer.parseInt((String) array[7]), Integer.parseInt((String) array[8]), Integer.parseInt((String) array[9]));
		userTransaction.commit();

		// set the current row to rowIndex
		vendingProductList.add(product_row);

		int row = vendingProductList.size(); 

		numrows++;

		fireTableRowsInserted(row, row);
	}	

}

