package coen359.vendingmachine.user.payment;


import coen359.vendingmachine.smartcard.SmartCard;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class SmartCardService {

	private EntityManager manager;

	public SmartCardService(EntityManager manager) {
		this.manager = manager;
	}

	// method to create a new record
	public SmartCard createSmartCard(String id, Integer month,Integer year, Integer balance) {

		SmartCard smartCard = new SmartCard();

		smartCard.setId(id);
		smartCard.setMonth(month);
                smartCard.setYear(year);
		smartCard.setBalance(balance);

		manager.persist(smartCard);
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
	public SmartCard updateSmartCard(String id,Integer month,Integer year,  Integer balance) {

		SmartCard smartCard = manager.find(SmartCard.class, id);

		if (smartCard != null) {

			smartCard.setId(id);
			smartCard.setMonth(month);
                        smartCard.setYear(year);
			smartCard.setBalance(balance);
		}
		return smartCard;
	}

	// method to delete a record
	public void deleteSmartCard(String id) {
		
		SmartCard smartCard = manager.find(SmartCard.class, id);
		
		if (smartCard != null) {
			manager.remove(smartCard);
		}
	}
      


}