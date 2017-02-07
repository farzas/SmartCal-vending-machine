package coen359.vendingmachine.admin.log;

import static javax.persistence.TemporalType.DATE;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity(name="Admin_Log")
public class AdminLog {

	@Id

	@GeneratedValue(strategy=GenerationType.TABLE)
	private String id;	

	@Column(name = "user_name")
	private String userName;

	@Column
	@Temporal(DATE)
	private Date date;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		return "\t" +userName + "\t\t" + sm.format(date);
	}
}
