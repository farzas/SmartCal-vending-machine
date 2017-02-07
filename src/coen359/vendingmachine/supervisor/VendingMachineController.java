package coen359.vendingmachine.supervisor;

import java.text.ParseException;

import javax.swing.table.TableModel;
import javax.swing.event.*;


/**
 * Glue between the view  and the model . 
 * No database specific code here. Contains a reference to both the TableModel and the graphical user interface.
 **/

public class VendingMachineController implements TableModelListener {
	
	private VendingMachineModel vendingModel;
	private VendingMachineView vendingView;

	public VendingMachineController(VendingMachineView vendingView) {
		
		this.vendingView = vendingView;   
		
		// create the tableModel using the data in the cachedRowSet
		vendingModel = new VendingMachineModel(); 
		vendingModel.addTableModelListener(this);
	}

	public TableModel getTableModel() {
		return vendingModel;
	}

	public void tableChanged(TableModelEvent e) {
			
		vendingView.updateTable();
	}

	public void addRow(String[] array) throws NumberFormatException, ParseException {
		
		vendingModel.addRow(array);			
	}

}

