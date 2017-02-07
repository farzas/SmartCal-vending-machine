package coen359.vendingmachine.admin;


import javax.persistence.*;

import java.util.*;


public class AdminService {

	private EntityManager manager;

	public AdminService(){
		EntityManagerFactory factory;
		factory = Persistence.createEntityManagerFactory("PersistenceUnit");
		manager = factory.createEntityManager();
	}

	public AdminService(EntityManager manager) {
		this.manager = manager;
	}

	// method to create a new record
	public Admin createAdmin(String userName, String password) {
		Admin admin = new Admin();

		admin.setUserName(userName);
		admin.setPassword(password);

		manager.persist(admin);
		return admin;
	}

	// method to read a record
	public Admin readAdmin(String userName) {
		Admin admin = manager.find(Admin.class, userName);
		return admin;   	 
	}

	// method to read all records
	public List<Admin> readAll() {
		TypedQuery<Admin> query = manager.createQuery("SELECT e FROM Admin e", Admin.class);
		List<Admin> result =  query.getResultList();

		return result;   	 
	}

	// method to update a record
	public Admin updateAdmin(String userName, String password)  {
		Admin admin = manager.find(Admin.class, userName);
		if (admin != null) {

			admin.setUserName(userName);
			admin.setPassword(password);
		}
		return admin;
	}

	// method to delete a record
	public void deleteAdmin(String userName) {
		Admin admin = manager.find(Admin.class, userName);
		if (admin != null) {
			manager.remove(admin);
		}
	}
}
