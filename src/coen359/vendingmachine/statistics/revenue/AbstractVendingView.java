package coen359.vendingmachine.statistics.revenue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public abstract class AbstractVendingView extends JPanel
	implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8214648563869943209L;
	//Vending machine statistics to observe
	private VendingMachineStats vendingStats;

	//Here we use the Null Object pattern
	public AbstractVendingView(VendingMachineStats observableVendingStats) 
			throws NullPointerException {
		
	  //do not allow null vending machine accounts
      if (observableVendingStats == null )
         throw new NullPointerException();
	    
      //update vendingStats data member to new VendingStats
	  this.vendingStats = observableVendingStats;
		
	  //register as an Observer to receive account updates
	  vendingStats.addObserver( this );
	  
	}

	//get Vending Statistics for which this  class is an observer
	public VendingMachineStats getVendingStats() {
		return vendingStats;
	}
	
	//compute statistics for vending machine
	//protected abstract void computeStats();
	
   // update display with statistics
   protected abstract void updateDisplay();
   
   // receive updates from Observable Vending machine statistics
   public void update( Observable observable, Object object )
   {
      updateDisplay();
   }
	
	

}
