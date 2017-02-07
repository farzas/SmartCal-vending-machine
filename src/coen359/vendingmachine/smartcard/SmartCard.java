package coen359.vendingmachine.smartcard;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Smart_Card")
public class SmartCard implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column
	private String id;

	@Column(name="type")
	private String type;
	
	@Column(name="month")
	private Integer month;

	@Column(name="year")
	private Integer year;

	@Column
	private Integer balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getBalance() {
		return balance;
	}


	public void setBalance(Integer balance) {
		this.balance = balance;
	}


	// return the data in column i
			public String getColumnData(int i) throws Exception {

				if (i == 0)
					return getId();
				else if (i == 1)
					return getType();
				else if (i == 2) 
					return getMonth().toString();
				else if( i == 3)
					return getYear().toString();
				else if (i == 4)
					return getBalance().toString();
				else
					throw new Exception("Error: invalid column index in smart_card table");   

			}

			// return the name of column i
			public static String getColumnName(int i) throws Exception {

				String colName = null;

				if (i == 0) 
					colName = "ID";
				else if (i == 1)
					colName = "Card Type";
				else if (i == 2) 
					colName = "Month of expiry";
				else if( i == 3)
					colName = "Year of expiry";
				else if (i == 4)
					colName = "Balance Amount";
				else 
					throw new Exception("Access to invalid column number in smart card table");

				return colName;
			}


			// set data column i to value
			public void setColumnData(int i, Object value) throws Exception {

				if (i == 0) 
					id = (String) value;
				
				else if (i == 1)
					type = (String) value;
				
				else if (i == 2) 
					month = (Integer) value;
				
				else if( i == 3)
					year = (Integer) value;
				
				else if (i == 4)
					balance = (Integer) value;
				else
					throw new Exception("Error: invalid column index in smart_card table");    
			}

			@Override
			public String toString() {
				return "SmartCard [id=" + id + ", type=" + type + ", month="
						+ month + ", year=" + year + ", balance=" + balance
						+ ", getId()=" + getId() + ", getType()=" + getType()
						+ ", getMonth()=" + getMonth() + ", getYear()="
						+ getYear() + ", getBalance()=" + getBalance()
						+ ", getClass()=" + getClass() + ", hashCode()="
						+ hashCode() + ", toString()=" + super.toString() + "]";
			}

			public static int getNumberOfColumns() {
				return 5;
			}


			
}

