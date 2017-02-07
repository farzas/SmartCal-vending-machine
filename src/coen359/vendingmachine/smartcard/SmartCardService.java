package coen359.vendingmachine.smartcard;


import coen359.vendingmachine.smartcard.SmartCard;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JTextField;
//import javax.transaction.UserTransaction;



public class SmartCardService {

	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private static EntityManager manager;

	public SmartCardService(){

		entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		manager = entityManagerFactory.createEntityManager(); 
	}

	/*public SmartCardService(EntityManager manager) {
		this.manager = manager;
	}*/

	// method to create a new record
	public SmartCard createSmartCard(String id, String type, Integer month,Integer year, Integer balance) {

		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();

		SmartCard smartCard = new SmartCard();

		smartCard.setId(id);
		smartCard.setType(type);
		smartCard.setMonth(month);
		smartCard.setYear(year);
		smartCard.setBalance(balance);

		manager.persist(smartCard);

		userTransaction.commit();
		return smartCard;
	}

	// method to read a record
	public SmartCard readSmartCard(String id) {

		SmartCard smartCard = manager.find(SmartCard.class, id);
		return smartCard;   	 
	}

	// method to read all records
	public List<SmartCard> readAll() {

		TypedQuery<SmartCard> query = manager.createQuery("SELECT e FROM Smart_Card e", SmartCard.class);
		List<SmartCard> result =  query.getResultList();

		return result;   	 
	}


	// method to update a record
	public SmartCard updateSmartCard(String id, String type, Integer month,Integer year,  Integer balance) {

		SmartCard smartCard = manager.find(SmartCard.class, id);

		if (smartCard != null) {

			smartCard.setId(id);
			smartCard.setType(type);
			smartCard.setMonth(month);
			smartCard.setYear(year);
			smartCard.setBalance(balance);
		}
		return smartCard;
	}

	public Boolean checkIfSmartCardExists(String smartCardId) {

		Boolean smartCardExists = false;

		Query query = manager.createQuery("SELECT sc FROM Smart_Card sc WHERE sc.id = :smartCardId");
		query.setParameter("smartCardId", smartCardId);   	 

		if (!query.getResultList().isEmpty()){

			smartCardExists = true;
			System.out.println("Smart Card already exists");
		}
		return smartCardExists;
	}

	public void deleteSmartCard(String smartCardId) {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		manager = entityManagerFactory.createEntityManager(); 
		
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
		
		/*Query query = manager.createQuery("DELETE FROM Smart_Card sc WHERE sc.id = :smartCardId");
		query.setParameter("smartCardId", smartCardId);  
		query.executeUpdate();*/
		
		SmartCard smartCard = manager.find(SmartCard.class, smartCardId);
		
		if (smartCard != null)
			manager.remove(smartCard);		
		
		userTransaction.commit();
		
		
	}
        
        public String getIncrementedSmartCardId() {
		
	    Random rand = new Random();	   
	   int randomNum = rand.nextInt((10000000 - 1000000) + 1) + 1000000;
	   String smartCardId = "SC " + String.valueOf(randomNum);
	   
	   return smartCardId;
		
	}


}