package coen359.vendingmachine.admin.userfeedback;
	import java.text.ParseException;

	import javax.swing.table.TableModel;
	import javax.swing.event.*;


	/**
	 * Glue between the view  and the model . 
	 * No database specific code here. Contains a reference to both the TableModel and the graphical user interface.
	 **/

	public class UserFeedbackController implements TableModelListener {
		
		private UserFeedbackModel feedbackModel;
		private UserFeedbackView feedbackView;

		public UserFeedbackController(UserFeedbackView feedbackView) {
			
			this.feedbackView = feedbackView;   
			
			// create the tableModel using the data in the cachedRowSet
			feedbackModel = new UserFeedbackModel(); 
			feedbackModel.addTableModelListener(this);
		}

		public TableModel getTableModel() {
			return feedbackModel;
		}

		public void tableChanged(TableModelEvent e) {
				
			feedbackView.updateTable();
		}

		/*public void valueChanged(ListSelectionEvent e) {
			
			ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
			int firstIndex = selectModel.getMinSelectionIndex();
			
			if (firstIndex >= 0) {
				
				feedbackView.setIdTextField(feedbackModel.getProductList().get(firstIndex).getId());
				feedbackView.setNameTextField(feedbackModel.getProductList().get(firstIndex).getName());
				feedbackView.setPriceTextField(Double.toString(productModel.getProductList().get(firstIndex).getPrice()));
				productView.setExpiryDateTextField(productModel.getProductList().get(firstIndex).getExpiryDate().toString());
				productView.setQuantityTextField(String.valueOf(productModel.getProductList().get(firstIndex).getQuantity()));
				productView.setImageLinkTextField(productModel.getProductList().get(firstIndex).getImageLink());
				productView.setCalorieTextField(String.valueOf(productModel.getProductList().get(firstIndex).getNutritionalInfo().getCalorie()));
				productView.setFatTextField(String.valueOf(productModel.getProductList().get(firstIndex).getNutritionalInfo().getFat()));
				productView.setSugarTextField(String.valueOf(productModel.getProductList().get(firstIndex).getNutritionalInfo().getSugar()));
			}
		}*/

		public void addRow(String[] array) throws NumberFormatException, ParseException {
			
			feedbackModel.addRow(array);			
		}

}
