package coen359.vendingmachine.admin.vmproduct;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="vending_machines")
public class VendingMachine implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 5523071088487541080L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.TABLE)
	  @Column
	  private String id;
	  
	  @Column
	  private String location;
	  
	  @Column
	  private String category;
	
	   public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
	
		public String getLocation() {
			return location;
		}
	
		public void setLocation(String location) {
			this.location = location;
		}
	
		public String getCategory() {
			return category;
		}
	
		public void setCategory(String category) {
			this.category = category;
		}

	   // return number of columns in the table
	   public static int getNumberOfColumns() {
		   return 3;
	   }
   
   // return the data in column i
   public String getColumnData(int i) throws Exception {
	   if (i == 0)
		   return getId();
	   else if (i == 1)
		   return getLocation();
	   else if (i == 2) 
		   return getCategory();
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
   

	@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((category == null) ? 0 : category.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((location == null) ? 0 : location.hashCode());
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
	VendingMachine other = (VendingMachine) obj;
	if (category == null) {
		if (other.category != null)
			return false;
	} else if (!category.equals(other.category))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (location == null) {
		if (other.location != null)
			return false;
	} else if (!location.equals(other.location))
		return false;
	return true;
}

//return the name of column i
		public static String getColumnName(int i) throws Exception {

			String colName = null;
			if (i == 0) 
				colName = "Vending Machine ID";
			else if (i == 1)
				colName = "Category" ;
			else if (i == 2)
				colName = "Location";
			else 
				throw new Exception("Access to invalid column number in vending machines table");

			return colName;
		}
		
		

	@Override
	public String toString() {
		return "VendingMachine [id=" + id + ", location=" + location
				+ ", category=" + category + "]";
	}

}
