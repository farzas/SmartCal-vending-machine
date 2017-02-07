package coen359.vendingmachine.statistics.revenue;

//import java.util.Arrays;
import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author lakshmi
 *
 */
public class VendingMachineStats extends Observable {

	private String vendingID;
	private EntityManager manager;
	private Double revenue;

	public VendingMachineStats(String vendingID, EntityManager manager) {

		this.vendingID = vendingID;
		this.manager = manager;
		computeStatistics(vendingID);
	}

	public String getVendingID() {
		return vendingID;
	}

	public void setVendingID(String vendingID) {
		this.vendingID = vendingID;
	}
    
    public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	//set vending machine ID and its total revenue and notify observers of change
    public void setData(String vendingID, double revenue){
    	this.vendingID = vendingID;
    	this.revenue = revenue;
    	// must call setChanged before notifyObservers to
        // indicate model has changed
        setChanged();
        // notify Observers that model has changed
        notifyObservers();
    }
    
    public void computeStatistics(String vendingID){
    	
    	double totalRevenue =0;
    			   	
    	String query1 = "SELECT vp.purchase FROM vending_machine_product_purchased vp\n"
    			+ "WHERE vp.vending_machine_id = :vending";
    	
    	Query query = manager.createQuery(query1, VendingMachineProduct.class);
        query.setParameter("vending", vendingID);
        
        totalRevenue = (Double)query.getSingleResult();    	
    	        
        setData(vendingID, totalRevenue);
    	
    }

	@Override
	public String toString() {
		return "VendingMachineStats [vendingID=" + vendingID + ", manager="
				+ manager + ", revenue=" + revenue + "]";
	}
    
    


  
    
    

}
