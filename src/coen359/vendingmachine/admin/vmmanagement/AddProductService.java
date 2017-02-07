package coen359.vendingmachine.admin.vmmanagement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coen359.vendingmachine.product.*;


public class AddProductService {

	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private static EntityManager entityManager;
	//private static AdminLogService adminLogService = new AdminLogService(entityManager);

	public AddProductService(){		

		/*entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			entityManager = entityManagerFactory.createEntityManager();
			//adminLogService = new AdminLogService(manager);
		 */
	}

	public AddProductService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	// method to read all records
	public static List<String> readAll(String productType) {

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		TypedQuery<Product> query = entityManager.createQuery("SELECT p.name FROM Product p WHERE p.type = :type", Product.class);
		query.setParameter("type", productType);

		List<String> result = (List)query.getResultList();

		return result;   	 
	}


	public Object readProductQuantity(String productName, String productType){

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		TypedQuery<Product> query = entityManager.createQuery("SELECT p.quantity FROM Product p WHERE p.name = :name AND p.type = :type", Product.class);
		query.setParameter("name", productName);
		query.setParameter("type", productType);

		System.out.println("Quantity : " +query.getSingleResult()); // validation required!!!!!!!!
		//query.getMaxResults();
		List result = query.getResultList(); // return type?
		/*	if (result.isEmpty()) 
			return null;
        else if (result.size() == 1) */
		return result.get(0);
	}


	public Object getProductId(String productName, String productType) {

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		TypedQuery<Product> query = entityManager.createQuery("SELECT p.id FROM Product p WHERE p.name = :name AND p.type = :type", Product.class);
		query.setParameter("name", productName);
		query.setParameter("type", productType);

		System.out.println("Id : " +query.getSingleResult());
		List resultId = query.getResultList(); 
		return resultId.get(0);
		//return null;
	}
	
	public Boolean checkProductExistsInProductWarehouse(String productType) {
		
		Boolean productsExistsInWarehouse = false;

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery("SELECT p.id FROM Product p WHERE p.type = :type");
		query.setParameter("type", productType);
		
		if (!query.getResultList().isEmpty())
			
			productsExistsInWarehouse = true;
		
		
		return productsExistsInWarehouse;
	}

}

