package coen359.vendingmachine.statistics.vmproduct;
	import java.text.ParseException;

	import javax.swing.table.TableModel;
	import javax.swing.event.*;


	/**
	 * Glue between the view  and the model . 
	 * No database specific code here. Contains a reference to both the TableModel and the graphical user interface.
	 **/

	public class VendingMachineProductController implements TableModelListener {
		
		private VendingMachineProductModel vendingProductModel;
		private VendingMachineProductView vendingProductView;

		public VendingMachineProductController(VendingMachineProductView vendingProductView) {
			
			this.vendingProductView = vendingProductView;   
			
			// create the tableModel using the data in the cachedRowSet
			vendingProductModel = new VendingMachineProductModel(); 
			vendingProductModel.addTableModelListener(this);
		}

		public TableModel getTableModel() {
			return vendingProductModel;
		}

		public void tableChanged(TableModelEvent e) {
				
			vendingProductView.updateTable();
		}


		public void addRow(String[] array) throws NumberFormatException, ParseException {
			
			vendingProductModel.addRow(array);			
		}

}
