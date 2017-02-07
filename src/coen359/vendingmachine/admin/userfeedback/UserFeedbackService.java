package coen359.vendingmachine.admin.userfeedback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class UserFeedbackService {private EntityManager manager;

public UserFeedbackService(EntityManager manager) {
	 this.manager = manager;
}

//method to read a record from user feedback table
public UserFeedback readFeedback(String vending_machine_id) {
	UserFeedback feedback_record = manager.find(UserFeedback.class, vending_machine_id);
	 return feedback_record;   	 
}

//method to create a feedback row in user feedback table
public UserFeedback createUserFeedback(String id, String date, String feedback){
	 
	 EntityTransaction userTransaction = manager.getTransaction();  
	 userTransaction.begin();
		
	 UserFeedback user_feedback = new UserFeedback();
	 user_feedback.setVending_machine_id(id);
	 user_feedback.setDate(date);
	 user_feedback.setFeedback(feedback);
	 
	 manager.persist(user_feedback);
	 userTransaction.commit();
	 return user_feedback;
}

// method to read all records of user feedback table
public List<UserFeedback> readAll() {
	 TypedQuery<UserFeedback> query = manager.createQuery("SELECT e FROM user_feedback e", UserFeedback.class);
	 List<UserFeedback> result =  query.getResultList();

	 return result;   	 
}

// method to update a record
public UserFeedback updateFeedback(String id, String date, String feedback) {
	UserFeedback user_feedback = manager.find(UserFeedback.class, id);
	 if (user_feedback != null) {
		 user_feedback.setVending_machine_id(id);
		 user_feedback.setDate(date);
		 user_feedback.setFeedback(feedback);
	 }
	 return user_feedback;
}
	
	

}
