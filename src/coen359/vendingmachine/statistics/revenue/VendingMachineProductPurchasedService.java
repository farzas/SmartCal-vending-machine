package coen359.vendingmachine.statistics.revenue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author lakshmi
 * Null object pattern is used in the update method in this class
 */
public class VendingMachineProductPurchasedService {

	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private static EntityManager manager;


	public VendingMachineProductPurchasedService(EntityManager manager){
		this.manager = manager;
	}

	 //method to read a record from vending machine table
	 public VendingMachineProductPurchasedService readMachine(String id) {
		 VendingMachineProductPurchasedService machine = manager.find(VendingMachineProductPurchasedService.class, id);
   	 return machine;   	 
    }
	 
	 //method to create a vending machine
	 public VendingMachineProductPurchased createVendingMachinePurchase(String id, double purchase){
		 
		 EntityTransaction userTransaction = manager.getTransaction();  
		 userTransaction.begin();
			
		 VendingMachineProductPurchased vendingMachinePurchase = new VendingMachineProductPurchased();
		 vendingMachinePurchase.setVending_machine_id(id);
		 vendingMachinePurchase.setPurchase(purchase);
		 
		 manager.persist(vendingMachinePurchase);
		 userTransaction.commit();
		 return vendingMachinePurchase;
	 }
	 
    // method to read all records of vending machines table
    public List<VendingMachineProductPurchased> readAll() {
   	 TypedQuery<VendingMachineProductPurchased> query = manager.createQuery("SELECT e FROM vending_machines_product_purchased e", VendingMachineProductPurchased.class);
   	 List<VendingMachineProductPurchased> result =  query.getResultList();

   	 return result;   	 
    }
    
    // method to update a record - Null object pattern is used here
    public VendingMachineProductPurchased updateMachinePurchase(String id, double purchase) {
    	
    	 //EntityTransaction userTransaction = manager.getTransaction();  
		 //userTransaction.begin();
		 
    	VendingMachineProductPurchased machinePurchase = manager.find(VendingMachineProductPurchased.class, id);
    	
    	//if it already exists, update purchase amount
   	 if (machinePurchase != null) {
   		EntityTransaction userTransaction = manager.getTransaction();  
		 userTransaction.begin();
   		 machinePurchase.setVending_machine_id(id);
   		 
   		 double purchaseAmount = machinePurchase.getPurchase() + purchase;
   		 machinePurchase.setPurchase(purchaseAmount);
   	//Persist the update to database
	   	 manager.persist(machinePurchase);
		 userTransaction.commit();
   	 }
   	 
   	 else{
   		createVendingMachinePurchase(id, purchase);
   	 }
   	 
   	 
   	 //Persist the update to database
	   	 //manager.persist(machinePurchase);
		 //userTransaction.commit();
	   	 return machinePurchase;
    }
}
