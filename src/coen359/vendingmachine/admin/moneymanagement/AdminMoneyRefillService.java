package coen359.vendingmachine.admin.moneymanagement;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AdminMoneyRefillService {
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private static EntityManager manager;

	public AdminMoneyRefillService() {
		entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		manager = entityManagerFactory.createEntityManager(); 
	}

	// method to create a new record
	public void createAdminCurrency(String vendingMachineId, Double amount) {

		AdminMoneyRefill adminMoneyRefill = new AdminMoneyRefill();

		/*EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();*/

		adminMoneyRefill.setAmount(amount);
		adminMoneyRefill.setDate(new Date());
		adminMoneyRefill.setVendingMachineId(vendingMachineId);

		manager.persist(adminMoneyRefill);

		//userTransaction.commit();

	}

	// method to read a record
	public AdminMoneyRefill readAdminCurrency(String vendingMachineId) {

		AdminMoneyRefill adminMoneyRefill = manager.find(AdminMoneyRefill.class, vendingMachineId);
		return adminMoneyRefill;
	}

	// method to read all records
	public List<AdminMoneyRefill> readAll() {

		TypedQuery<AdminMoneyRefill> query = manager.createQuery("SELECT e FROM admin_currency e", AdminMoneyRefill.class);
		List<AdminMoneyRefill> result = query.getResultList();

		return result;
	}


	public List<Double> readLastAmountRefilled(String vendingMachineId) {

		String queryStr = "SELECT e.amount FROM admin_money_refill e WHERE e.vendingMachineId = :vendingMachineId";
		Query query = manager.createQuery(queryStr, Double.class); 
		query.setParameter("vendingMachineId", vendingMachineId);
		List<Double> lastAmountRefilled =   query.getResultList();

		return lastAmountRefilled;
	}

	// method to update a record
	public void updateAdminMoneyRefill(String vendingMachineId, Double refillAmount) {

		entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		manager = entityManagerFactory.createEntityManager(); 

		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();

		System.out.println("Vending Machine Id : " +vendingMachineId);
		Query query = manager.createQuery("Select amr.amount FROM admin_money_refill amr WHERE amr.vendingMachineId = :vendingMachineId", AdminMoneyRefill.class);
		query.setParameter("vendingMachineId", vendingMachineId);


		if ( !query.getResultList().isEmpty()){

			Double previousRefillAmount = Double.parseDouble(query.getResultList().get(0).toString());

			System.out.println("Previous amount in vending machine : " +previousRefillAmount);
			Double newAmount = previousRefillAmount + refillAmount;
			System.out.println("New Amount : " +newAmount);

			Query queryUpdate = manager.createQuery("UPDATE admin_money_refill amr SET amr.amount = " + newAmount + " WHERE amr.vendingMachineId = :vendingMachineId");
			queryUpdate.setParameter("vendingMachineId", vendingMachineId );
			queryUpdate.executeUpdate();
		}

		else {

			createAdminCurrency(vendingMachineId, refillAmount);
		}

		userTransaction.commit();
	}
	
	public List<String> lessBalanceVendingMachines(){
		
		Query query = manager.createQuery("SELECT amr.vendingMachineId FROM admin_money_refill amr WHERE amr.amount <= 100", String.class );
			
		return query.getResultList();
	}

	////// To update the table when the user makes a transaction, use the same method as above. Else, uncomment the below method

	public void updateAdminMoneyCollect (String vendingMachineId, Double amountRetrieved) {

		entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		manager = entityManagerFactory.createEntityManager(); 

		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();

		System.out.println("Vending Machine Id : " +vendingMachineId);
		Query query = manager.createQuery("Select amr.amount FROM admin_money_refill amr WHERE amr.vendingMachineId = :vendingMachineId", AdminMoneyRefill.class);
		query.setParameter("vendingMachineId", vendingMachineId);


		if ( !query.getResultList().isEmpty()){

			Double previousRefillAmount = Double.parseDouble(query.getResultList().get(0).toString());

			System.out.println("Previous amount in vending machine : " +previousRefillAmount);
			Double newAmount = previousRefillAmount - amountRetrieved;
			System.out.println("New Amount : " +newAmount);

			Query queryUpdate = manager.createQuery("UPDATE admin_money_refill amr SET amr.amount = " + newAmount + " WHERE amr.vendingMachineId = :vendingMachineId");
			queryUpdate.setParameter("vendingMachineId", vendingMachineId );
			queryUpdate.executeUpdate();
		}


	}
}
