package coen359.vendingmachine.product;

import java.text.ParseException;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.event.*;


/**
 * Glue between the view  and the model . 
 * No database specific code here. Contains a reference to both the TableModel and the graphical user interface.
 **/

public class ProductController implements ListSelectionListener, TableModelListener {
	
	private ProductModel productModel;
	private ProductView productView;

	public ProductController(ProductView productView) {
		
		this.productView = productView;   
		
		// create the tableModel using the data in the cachedRowSet
		productModel = new ProductModel(); 
		productModel.addTableModelListener(this);
	}

	public TableModel getTableModel() {
		return productModel;
	}

	public void tableChanged(TableModelEvent e) {
			
		productView.updateTable();
	}

	public void valueChanged(ListSelectionEvent e) {
		
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
		
		if (firstIndex >= 0) {
			
			productView.setTypeComboBox(productModel.getProductList().get(firstIndex).getType());
			productView.setIdTextField(productModel.getProductList().get(firstIndex).getId());
			productView.setNameTextField(productModel.getProductList().get(firstIndex).getName());
			productView.setPriceTextField(Double.toString(productModel.getProductList().get(firstIndex).getPrice()));
			productView.setExpiryDateTextField(productModel.getProductList().get(firstIndex).getExpiryDate().toString());
			productView.setQuantityTextField(String.valueOf(productModel.getProductList().get(firstIndex).getQuantity()));
			productView.setImageLinkTextField(productModel.getProductList().get(firstIndex).getImageLink());
			productView.setCalorieTextField(String.valueOf(productModel.getProductList().get(firstIndex).getNutritionalInfo().getCalorie()));
			productView.setFatTextField(String.valueOf(productModel.getProductList().get(firstIndex).getNutritionalInfo().getFat()));
			productView.setSugarTextField(String.valueOf(productModel.getProductList().get(firstIndex).getNutritionalInfo().getSugar()));
		}
	}

	public void addRow(String[] array) throws NumberFormatException, ParseException {
		
		productModel.addRow(array);			
	}

	public void updateRow(String[] array) throws NumberFormatException, ParseException {
		
		productModel.updateRow(array);		
	}
	
	public void deleteRow(String[] array) {
		
		productModel.deleteRow(array);			
	}
}
