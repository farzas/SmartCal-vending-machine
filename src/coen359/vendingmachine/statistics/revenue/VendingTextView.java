package coen359.vendingmachine.statistics.revenue;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class VendingTextView extends AbstractVendingView{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3613799300175946546L;
	
   // JTextField for displaying Vending machine revenue
   private JTextField vm1TextField = new JTextField( 10 );

   // NumberFormat for US dollars
   private NumberFormat moneyFormat =
      NumberFormat.getCurrencyInstance( Locale.US );

	public VendingTextView(VendingMachineStats observableVendingStats)
			throws NullPointerException {
		
		super(observableVendingStats);
		
		JLabel revenueLabel = new JLabel("Total revenue of Vending machine: ");
		revenueLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		
	  // make vending machine total revenue fields read only
	  vm1TextField.setEditable( false );

      // lay out components
      add(revenueLabel);
      add(vm1TextField);

      updateDisplay();
	}

	
	protected void updateDisplay() {

			vm1TextField.setText(moneyFormat.format(getVendingStats().getRevenue() ) );

		
	}	
}
