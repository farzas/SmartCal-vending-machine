package coen359.vendingmachine.smartcard.display;
	import java.text.ParseException;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import coen359.vendingmachine.statistics.vmproduct.*;

import coen359.vendingmachine.statistics.vmproduct.VendingMachineProductModel;


	/**
	 * Glue between the view  and the model . 
	 * No database specific code here. Contains a reference to both the TableModel and the graphical user interface.
	 **/

	public class SmartCardController implements TableModelListener {
		
		private static SmartCardModel smartCardModel;
		private SmartCardView smartCardView;

		public SmartCardController(SmartCardView smartCardView) {
			
			this.smartCardView = smartCardView;   
			
			// create the tableModel using the data in the cachedRowSet
			smartCardModel = new SmartCardModel(); 
			smartCardModel.addTableModelListener(this);
		}

		public static TableModel getTableModel() {
			return smartCardModel;
		}

		public void tableChanged(TableModelEvent e) {
				
			smartCardView.updateTable();
		}


		public void addRow(String[] array) throws NumberFormatException, ParseException {
			
			smartCardModel.addRow(array);			
		}

}
