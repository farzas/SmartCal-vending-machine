/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coen359.vendingmachine.user.management;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coen359.vendingmachine.admin.vmproduct.VendingMachine;
import coen359.vendingmachine.statistics.revenue.VendingMachineProduct;

public class VendingMachineService {
	
	private EntityManager manager;

	//  Set<UserBudgetLis User = null ;

	public VendingMachineService(EntityManager manager) {
		this.manager = manager;
	}

	public List<Object[]> readBeverageID (String vmid) {
		
		String queryStr = "SELECT p.name, p.price, vp.quantity, p.imageLink , vp.productList.id FROM vending_machine_product vp, Product p\n" +
				"WHERE  vp.productList.id  = p.id and vp.vendingMachine.id =:vmid and p.type =:beveid ";
		
		Query query = manager.createQuery(queryStr, VendingMachineProduct.class);
		query.setParameter("vmid", vmid);
		query.setParameter("beveid", "Beverage");
		//List<Object[]> result = query.getResultList();
		System.out.println("Read all project name :"+query.getResultList().toString());
		return query.getResultList();
	}  

	public List<Object[]> readCandyID (String vmid) {

		String queryStr = "SELECT p.name, p.price, vp.quantity, p.imageLink ,vp.productList.id FROM vending_machine_product vp, Product p\n" +
				"WHERE  vp.productList.id = p.id and vp.vendingMachine.id =:vmid and p.type = :candyid ";

		Query query = manager.createQuery(queryStr, VendingMachineProduct.class);
		query.setParameter("vmid", vmid);
		query.setParameter("candyid", "Candy");

		//List<Object[]> result = query.getResultList();
		System.out.println("Read all project name :"+query.getResultList().toString());
		return query.getResultList();
	}  

	public List<Object[]> readSandwichesID(String vmid) {

		String queryStr = "SELECT p.name, p.price, vp.quantity, p.imageLink , vp.productList.id FROM vending_machine_product vp, Product p\n" +
				"WHERE  vp.productList.id = p.id and vp.vendingMachine.id =:vmid and p.type =:sandwid ";
		
		Query query = manager.createQuery(queryStr, VendingMachineProduct.class);
		query.setParameter("vmid", vmid);
		query.setParameter("sandwid", "Sandwich");
		//List<Object[]> result = query.getResultList();
		System.out.println("Read all project name :"+query.getResultList().toString());
		return query.getResultList();
	}  

	public List<Object[]> readNutrionalInfo(String prid) {
		//String queryStr = "SELECT b.Budget_ID, b.Task_ID,p.project_name,b.Description,b.Basic_Budget,b.Projected_budget,b.Actual_budget FROM budget b ,task t,project p where b.Task_ID=t.Task_ID and b.Project_ID=p.project_id ";
		String queryStr = "SELECT nf.calorie,nf.fat,nf.sugar FROM Nutritional_Info nf where nf.id =:prid";
		Query query = manager.createQuery(queryStr, VendingMachineProduct.class);
		query.setParameter("prid", prid);

		//List<Object[]> result = query.getResultList();
		System.out.println("Read all project name :"+query.getResultList().toString());
		return query.getResultList();
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
   	 VendingMachine machine = manager.find(VendingMachine.class, id);
   	 if (machine != null) {
   		 machine.setId(id);
   		 machine.setLocation(location);
   		 machine.setCategory(category);
   	 }
   	 return machine;
    }

}
