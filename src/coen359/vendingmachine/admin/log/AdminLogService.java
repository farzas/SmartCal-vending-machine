package coen359.vendingmachine.admin.log;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AdminLogService {
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory entityManagerFactory; 
	private static EntityManager entityManager;
	
	
	private static AdminLogService adminLogService = new AdminLogService(entityManager);

	private AdminLogService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	//Method that returns a Singleton Object
	public static AdminLogService createAdminLogService(){
		
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager(); // is it ok to make manager static?
		return adminLogService;
	}
	
	// method to create a new record
	public void createAdminLog(String adminLogId, String userName, Date date) {
		
		EntityTransaction userTransaction = entityManager.getTransaction();  
		userTransaction.begin();
		
		AdminLog adminLog = new AdminLog();

		adminLog.setId(adminLogId);
		adminLog.setUserName(userName);
		adminLog.setDate(date);

		entityManager.persist(adminLog);
		userTransaction.commit();
		//return adminLog;
	}

	// method to read a record
	public AdminLog readAdminLog(String userName) {
		AdminLog adminLog = entityManager.find(AdminLog.class, userName);
		return adminLog;   	 
	}

	// method to read all records
	public List<AdminLog> readAll() {
		TypedQuery<AdminLog> query = entityManager.createQuery("SELECT e FROM Admin_Log e ORDER BY e.date desc", AdminLog.class);
		List<AdminLog> result =  query.getResultList();

		return result;   	 
	}

}
