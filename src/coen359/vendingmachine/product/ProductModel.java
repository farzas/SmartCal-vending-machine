package coen359.vendingmachine.product;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

import coen359.vendingmachine.product.nutritionalinfo.NutritionalInfo;

public class ProductModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Used in persistence.xml
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private EntityManager entityManager;	

	private ProductService productService;

	// stores the model data in a List collection of type Product
	List<Product> productList;   

	// This field contains additional information about the results
	int numcols, numrows;
	
	DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

	ProductModel() {

		entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit" );
		entityManager = entityManagerFactory.createEntityManager();

		productService = new ProductService(entityManager);

		// read all the records from product
		productList = productService.readAll();

		// update the number of rows and columns in the model
		numrows = productList.size();
		numcols = Product.getNumberOfColumns() + NutritionalInfo.getNumberOfColumns() - 1;
	}

	// create a new table model using the existing data in list
	public ProductModel(List<Product> list, EntityManager entityManager)  {

		this.entityManager = entityManager; 
		productService = new ProductService(entityManager);

		productList = list;

		numrows = productList.size();
		numcols = Product.getNumberOfColumns() + NutritionalInfo.getNumberOfColumns() - 1;
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

			case 0: return productList.get(row).getId();
			case 1: return productList.get(row).getName();
			case 2: return Double.toString(productList.get(row).getPrice());
			case 3: return productList.get(row).getExpiryDate().toString();
			case 4: return String.valueOf(productList.get(row).getQuantity());
			case 5: return productList.get(row).getType();
			case 6: return productList.get(row).getImageLink();
			case 7: return String.valueOf(productList.get(row).getNutritionalInfo().getCalorie());
			case 8: return String.valueOf(productList.get(row).getNutritionalInfo().getFat());
			case 9: return String.valueOf(productList.get(row).getNutritionalInfo().getSugar());

			}
			return "";
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// update the data in the given row and column to aValue
	public void setValueAt(Object aValue, int row, int col) {

		try {
			
			fireTableCellUpdated(row, col);
		} 
		catch (Exception err) {
			
			err.toString();
		}
	}

	// returns the name of the column
	public String getColumnName(int col) {	

		if (col <= 6) {
			
			try {
				
				return Product.getColumnName(col);
			} 
			catch (Exception err) {
				
				return err.toString();
			}    
		}
		else {

			try {
				
				return NutritionalInfo.getColumnName(col);		
			} 
			catch (Exception err) {
				
				return err.toString();
			}
		}
	}

	public List<Product> getProductList() {
		
		return productList;
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

		// while creating product, nutritionaInfo will also be persisted
		Product product = productService.createProduct((String) array[0], (String) array[1], Double.parseDouble((String) array[2]), dateFormatter.parse((String) array[3]), Integer.parseInt((String) array[4]), (String) array[5], (String) array[6], Integer.parseInt((String) array[7]), Integer.parseInt((String) array[8]), Integer.parseInt((String) array[9]));
		userTransaction.commit();

		// set the current row to rowIndex
		productList.add(product);

		int row = productList.size(); 

		numrows++;

		fireTableRowsInserted(row, row);
	}	

	public void updateRow(Object[] array) throws NumberFormatException, ParseException {

		Product oldProduct = productService.readProduct((String) array[0]);	

		// add row to database
		EntityTransaction userTransaction = entityManager.getTransaction();  
		userTransaction.begin();

		// while creating product nutritionaInfo will also be persisted
		Product newProduct = productService.updateProduct((String) array[0], (String) array[1], Double.parseDouble((String) array[2]), dateFormatter.parse((String) array[3]), Integer.parseInt((String) array[4]), (String) array[5], (String) array[6], Integer.parseInt((String) array[7]), Integer.parseInt((String) array[8]), Integer.parseInt((String) array[9])  );
		userTransaction.commit();

		// set the current row to rowIndex
		productList.remove(oldProduct);
		productList.add(newProduct);

		fireTableDataChanged();
	}	       

	public void deleteRow(Object[] array) {

		Product product = productService.readProduct((String) array[0]);	

		// delete row to database
		EntityTransaction userTransaction = entityManager.getTransaction();

		userTransaction.begin();
		productService.deleteProduct((String) array[0]);

		userTransaction.commit();
		productList.remove(product);

		fireTableDataChanged();
	}
}	
