package coen359.vendingmachine.admin.userfeedback;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user_feedback")
public class UserFeedback {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column 
	  private int id;
	  
	  @Column
	  private String vending_machine_id;
	  
	  @Column
	  private String date;
	  
	  @Column 
	  private String feedback;

	public String getVending_machine_id() {
		return vending_machine_id;
	}

	public void setVending_machine_id(String vending_machine_id) {
		this.vending_machine_id = vending_machine_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public static int getNumberOfColumns() {

		return 3;
	}
	
	// return the name of column i
		public static String getColumnName(int i) throws Exception {

			String colName = null;
			if (i == 0) 
				colName = "Vending machine ID";
			else if (i == 1)
				colName = "Date" ;
			else if (i == 2)
				colName = "Feedback";
			else 
				throw new Exception("Access to invalid column number in product table");

			return colName;
		}


		// set data column i to value
		public void setColumnData(int i, Object value) throws Exception {

			if (i == 0) 
				vending_machine_id = (String) value;
			else if (i == 1) 
				date = (String) value;
			else if (i == 2) 
				feedback = (String) value;
			else
				throw new Exception("Error: invalid column index in product table");    
		}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((feedback == null) ? 0 : feedback.hashCode());
		result = prime
				* result
				+ ((vending_machine_id == null) ? 0 : vending_machine_id
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFeedback other = (UserFeedback) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (vending_machine_id == null) {
			if (other.vending_machine_id != null)
				return false;
		} else if (!vending_machine_id.equals(other.vending_machine_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserFeedback [vending_machine_id=" + vending_machine_id
				+ ", date=" + date + ", feedback=" + feedback + "]";
	}
	  
	

}
