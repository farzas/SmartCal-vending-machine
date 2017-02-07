package coen359.vendingmachine.admin.vmproduct;

import coen359.vendingmachine.product.Product;
import coen359.vendingmachine.product.nutritionalinfo.NutritionalInfo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coen359.vendingmachine.statistics.revenue.*;

public class VendingMachineProductService {

	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private static EntityManager manager;


	public VendingMachineProductService(EntityManager manager){
		this.manager = manager;
	}

	public VendingMachineProductService(){

		entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		manager = entityManagerFactory.createEntityManager(); 
	}

	public VendingMachineProduct createVendingMachineProduct(String vendingId,String productId, int quantity){

		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();			

		TypedQuery<Product> query = manager.createQuery("SELECT p FROM Product p WHERE p.id = :productId", Product.class);
		query.setParameter("productId", productId);
		System.out.println("Id : " +query.getSingleResult());
		List resultId = query.getResultList(); 

		TypedQuery<VendingMachine> query1 = manager.createQuery("SELECT vm FROM vending_machines vm WHERE vm.id = :vendingId", VendingMachine.class);
		query1.setParameter("vendingId", vendingId);
		System.out.println("Id : " +query.getSingleResult());
		List resultId1 = query1.getResultList(); 

		VendingMachineProduct vendingProduct = new VendingMachineProduct();
		vendingProduct.setProductList((Product)resultId.get(0));
		vendingProduct.setVendingMachine((VendingMachine)resultId1.get(0));
		vendingProduct.setQuantity(quantity);

		manager.persist(vendingProduct);
		userTransaction.commit();
		return vendingProduct;
	}
	
	
		public Boolean checkProductExistsInVendingMachine(String productType) {

			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			manager = entityManagerFactory.createEntityManager();

			TypedQuery<Product> query = manager.createQuery("SELECT p.id FROM Product p WHERE p.type = :type", Product.class);
			query.setParameter("type", productType);
			
			Boolean productExists = false;
			
			List<String> result = (List)query.getResultList();
			 for (String id : result){
				 
				 System.out.println("Product Id from product table - of the product types selected "+id);
				 Query query1 = manager.createQuery("SELECT vmp.quantity FROM vending_machine_product vmp WHERE vmp.productList.id = :id");
				 query1.setParameter("id", id);
				 
				 if(!query1.getResultList().isEmpty()){					 
					 productExists = true;
				 }
					 
			 }
			 
			 return productExists;
		 
		}
		
		public Boolean checkProductExistsInVendingMachineName(String productName) {
			
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			manager = entityManagerFactory.createEntityManager();

			TypedQuery<Product> query = manager.createQuery("SELECT p.id FROM Product p WHERE p.name = :productName", Product.class);
			query.setParameter("productName", productName);
			
			Boolean productExists = false;
			
			List<String> result = (List)query.getResultList();
			 for (String id : result){
				 
				 System.out.println("Product Id from product table - of the product types selected "+id);
				 Query query1 = manager.createQuery("SELECT vmp.quantity FROM vending_machine_product vmp WHERE vmp.productList.id = :id");
				 query1.setParameter("id", id);
				 
				 if(!query1.getResultList().isEmpty()){					 
					 productExists = true;
				 }
					 
			 }
			 
			 return productExists;
			
		}

	public void checkProductExistsAndAddProduct(String vendingId, String productId, Integer quantity ) {

		Query vendingMachineProductQuery = manager.createQuery("SELECT vm.quantity FROM vending_machine_product vm WHERE vm.vendingMachine.id = :vendingId AND vm.productList.id = :productId", VendingMachineProduct.class);
		vendingMachineProductQuery.setParameter("vendingId", vendingId);
		vendingMachineProductQuery.setParameter("productId", productId);

		if (!vendingMachineProductQuery.getResultList().isEmpty()){

			System.out.println("Product exists");

			Integer previousQuantityInVendingMachine = Integer.valueOf(vendingMachineProductQuery.getResultList().get(0).toString());

			//Subtract quantity required from previous quantity and store in product table
			updateProductQuantityAvailable(quantity, productId);

			 //updatedQuantity to be inserted in vending machine
			quantity = quantity + previousQuantityInVendingMachine;
			System.out.println("Quantity : " + quantity);
			
			//add the entered quantity to vending machine and update vending machine product
			updateVendingMachineProduct(vendingId, productId, quantity);


		}			

		if (vendingMachineProductQuery.getResultList().isEmpty())
		{
			createVendingMachineProduct(vendingId, productId, quantity);

			//Subtract quantity required from previous quantity and store in product table
			updateProductQuantityAvailable(quantity, productId);
		}
	}

	public void checkProductExistsAndDelete(String vendingId, String productId, Integer quantity ) {

		Query vendingMachineProductQuery = manager.createQuery("SELECT vm.quantity FROM vending_machine_product vm WHERE vm.vendingMachine.id = :vendingId AND vm.productList.id = :productId", VendingMachineProduct.class);
		vendingMachineProductQuery.setParameter("vendingId", vendingId);
		vendingMachineProductQuery.setParameter("productId", productId);

		if (!vendingMachineProductQuery.getResultList().isEmpty()){

			System.out.println("Product available to be deleted");

			Integer previousQuantityInVendingMachine = Integer.valueOf(vendingMachineProductQuery.getResultList().get(0).toString());
			
			System.out.println("Quantity available previously in vending machine : " +previousQuantityInVendingMachine);

			//Subtract quantity required from previous quantity and store in product table
			//-- This need not be done since product was subtracted while adding to vending machine
			//updateProductQuantityAvailable(quantity, productId);

			//updatedQuantity to be entered into vending machine
			quantity = previousQuantityInVendingMachine - quantity;
			System.out.println("Quantity : " + quantity);
			updateVendingMachineProduct(vendingId, productId, quantity);


		}			

		else{

			//no such product in vending machine//////////
		}
	}



	private void updateProductQuantityAvailable(Integer quantity, String productId) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); 

		Query productQuantityQuery = manager.createQuery("SELECT p.quantity FROM Product P WHERE p.id = :productId");
		productQuantityQuery.setParameter("productId", productId);

		Integer oldQuantity = Integer.valueOf(productQuantityQuery.getResultList().get(0).toString());		
		Integer newQuantity = oldQuantity - quantity;
		System.out.println("New Quantity in Product Table: "+newQuantity);

		EntityTransaction userTransaction = manager.getTransaction();
		userTransaction.begin();

		Query productQuantityUpdateQuery = manager.createQuery("UPDATE Product P SET p.quantity = " + newQuantity + " WHERE p.id = :productId");
		productQuantityUpdateQuery.setParameter("productId", productId);
		productQuantityUpdateQuery.executeUpdate();

		userTransaction.commit();

	}

	public void updateVendingMachineProduct(String vendingMachineId, String productId, Integer quantity){

		System.out.println("produt id" +productId);

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = entityManagerFactory.createEntityManager(); 

		EntityTransaction userTransaction = entityManager.getTransaction();  
		userTransaction.begin();

		//Query query = entityManager.createQuery("DELETE FROM vending_machine_product vm WHERE vm.vendingMachine.id = :vendingMachineId AND vm.productList.id = :productId");//, VendingMachineProduct.class);
		Query query = entityManager.createQuery("UPDATE vending_machine_product vmp SET vmp.quantity = " + quantity + " WHERE vmp.vendingMachine.id = :vendingMachineId AND vmp.productList.id = :productId");
		
		query.setParameter("productId", productId);
		query.setParameter("vendingMachineId", vendingMachineId);
		query.executeUpdate();		

		userTransaction.commit();

	}
	
	//Get list of products in vending machines that need to be restocked
	public ArrayList<String> restockProducts(){
		
		ArrayList<String> restock = new ArrayList<String>();
		
		Query query = manager.createQuery("SELECT vmp.vendingMachine.id, vmp.productList.name FROM vending_machine_product vmp WHERE vmp.quantity <= 5", VendingMachineProduct.class );
		
		List<Object []> results = query.getResultList();
		for(Object[] result : results){
			restock.add("Vending Machine ID: " + (String)result[0] + ",  Product: " + (String)result[1]);
		}
		return restock;
	}

	public Integer getUpdatedQuantity(String productId){

		//call this method only after update vending machine
		Query vendingMachineProductQuery = manager.createQuery("SELECT p.quantity FROM Product p WHERE p.id = :productId", Product.class);
		vendingMachineProductQuery.setParameter("productId", productId);

		Integer updatedQuantity = Integer.valueOf(vendingMachineProductQuery.getResultList().get(0).toString());
		return updatedQuantity;
	}

	public String getProductId(String productName, String productType){

		String productId;
		Query vendingMachineProductQuery = manager.createQuery("SELECT p.id FROM Product p WHERE p.name = :productName AND p.type = :productType", Product.class);
		vendingMachineProductQuery.setParameter("productName", productName);
		vendingMachineProductQuery.setParameter("productType", productType);

		if(!vendingMachineProductQuery.getResultList().isEmpty()) {
			
			productId = vendingMachineProductQuery.getResultList().get(0).toString();
			System.out.println(" Product ID for " +productName + " and " +productType+ " is " +productId);
		}
		else {
			
			System.out.println("Product id could not be found for the particular product");
			productId = null;
		}
			
		return productId;
	}

	public Integer getVendingMachineProductQuantity(String vendingId, String productId){

		Query vendingMachineProductQuery = manager.createQuery("SELECT vm.quantity FROM vending_machine_product vm WHERE vm.vendingMachine.id = :vendingId AND vm.productList.id = :productId", VendingMachineProduct.class);
		vendingMachineProductQuery.setParameter("vendingId", vendingId);
		vendingMachineProductQuery.setParameter("productId", productId);

		if (!vendingMachineProductQuery.getResultList().isEmpty()){

			System.out.println("Product available to be deleted from vending machine");

			Integer quantityInVendingMachine = Integer.valueOf(vendingMachineProductQuery.getResultList().get(0).toString());
			return quantityInVendingMachine;
		}

		else{

			return 0;
		}
	}

	public List<String> readAll(String productType) {
		
		//return all product names of the given type present in vending machine
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		manager = entityManagerFactory.createEntityManager();

		TypedQuery<Product> query = manager.createQuery("SELECT p.name FROM Product p WHERE p.type = :type", Product.class);
		query.setParameter("type", productType);

		List<String> result = (List)query.getResultList();

		return result;   
		
	}
	
	// method to read all records of vending machine product table
		public List<VendingMachineProduct> readAll() {
		
			
			TypedQuery<VendingMachineProduct> query = manager.createQuery("SELECT e FROM vending_machine_product e", VendingMachineProduct.class);
			 List<VendingMachineProduct> result =  query.getResultList();

			 return result;   	 
		}

	
}
