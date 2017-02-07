package coen359.vendingmachine.admin.vmproduct;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coen359.vendingmachine.statistics.revenue.*;



/**
 * @author lakshmi
 *
 */
public class VendingMachineService {
	
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private static EntityManager manager;
	 
	 public VendingMachineService(EntityManager manager) {
		 this.manager = manager;
	 }
	 
	 public VendingMachineService(){
		 
		 entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
			manager = entityManagerFactory.createEntityManager(); 
	 }
	 
	 //method to read a record from vending machine table
	 public VendingMachine readMachine(String id) {
		 VendingMachine machine = manager.find(VendingMachine.class, id);
    	 return machine;   	 
     }
	 
	 //method to create a vending machine
	 public VendingMachine createVendingMachine(String id, String location, String category){
		 
		 EntityTransaction userTransaction = manager.getTransaction();  
		 userTransaction.begin();
		 
		 VendingMachine vendingMachine = new VendingMachine();
		 vendingMachine.setId(id);
		 vendingMachine.setLocation(location);
		 vendingMachine.setCategory(category);
		 
		 manager.persist(vendingMachine);
		 userTransaction.commit();
		 return vendingMachine;
	 }
	 
     // method to read all records of vending machines table
     public List<VendingMachine> readAll() {
    	 TypedQuery<VendingMachine> query = manager.createQuery("SELECT e FROM vending_machines e", VendingMachine.class);
    	 List<VendingMachine> result =  query.getResultList();

    	 return result;   	 
     }
     
     // method to update a record
     public VendingMachine updateMachine(String id, String location, String category) {
    	 
    	 EntityTransaction userTransaction = manager.getTransaction();  
		 userTransaction.begin();
		 
    	 VendingMachine machine = manager.find(VendingMachine.class, id);
    	 if (machine != null) {
    		 machine.setId(id);
    		 machine.setLocation(location);
    		 machine.setCategory(category);
    	 }
    	 userTransaction.commit();
    	 return machine;   	 
    	 
     }
     
     public Boolean checkIfVendingMachineExists(String id){
    	 
    	 Boolean machineExists = false;
    	 
    	 Query query = manager.createQuery("SELECT vm FROM vending_machines vm WHERE vm.id = :id");
    	 query.setParameter("id", id);
    	 /*System.out.println("Checking if vending machine already exists  : " +query.getSingleResult());
 		List resultId = query.getResultList(); */
 		
 		if (!query.getResultList().isEmpty()){
 			
 			machineExists = true;
 			System.out.println("Vending machine already exists");
 		}
 		
 		return machineExists;
     }
     
     public List<Object[]> readAllSoldProducts() {
         String queryStr = "select  p.name ,vmp.quantity from vending_machine_product vmp,Product p where vmp.productList.id == p.id";
         Query query = manager.createQuery(queryStr, VendingMachineProduct.class);
    //List<Object[]> result = query.getResultList();
    System.out.println("Read all project name :"+query.getResultList().toString());
    return query.getResultList();
              
     }

}
