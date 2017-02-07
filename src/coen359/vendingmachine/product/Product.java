package coen359.vendingmachine.product;

import coen359.vendingmachine.product.nutritionalinfo.NutritionalInfo;
import coen359.vendingmachine.statistics.revenue.VendingMachineProduct;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name="Product") // The primary table is product to which the other tables will be mapped.
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")

public class Product implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name; 

	@Column(name = "price")
	private Double price;  

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "type")
	private String type; 

	@Column(name = "image_link")
	private String imageLink;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Double getPrice() {

		return price;
	}

	public void setPrice(Double price) {

		this.price = price;
	}

	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {

		this.quantity = quantity;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getImageLink() {

		return imageLink;
	}

	public void setImageLink(String imageLink) {

		this.imageLink = imageLink;
	}

	public NutritionalInfo getNutritionalInfo() {

		return nutritionalInfo;
	}

	public void addNutritionalInfo(NutritionalInfo nutritionalInfo) {

		this.nutritionalInfo = nutritionalInfo;
		nutritionalInfo.setProduct(this);
	}

	// return the data in column i
	public String getColumnData(int i) throws Exception {

		if (i == 0)
			return getId();
		else if (i == 1)
			return getName();
		else if (i == 2) 
			return Double.toString(getPrice());
		else if (i == 3)
			return getExpiryDate().toString();	
		else if( i == 4)
			return Integer.toString(getQuantity());
		else if ( i == 5)
			return getType();
		else if ( i == 6)
			return getImageLink();
		else
			throw new Exception("Error: invalid column index in product table");    
	}


	// return the name of column i
	public static String getColumnName(int i) throws Exception {

		String colName = null;
		if (i == 0) 
			colName = "ID";
		else if (i == 1)
			colName = "Name" ;
		else if (i == 2)
			colName = "Price";
		else if (i == 3)
			colName = "Expiry date";
		else if (i == 4)
			colName = "Quantity" ;
		else if (i == 5)
			colName = "Type";
		else if (i == 6)
			colName = "Image Link";
		else 
			throw new Exception("Access to invalid column number in product table");

		return colName;
	}


	// set data column i to value
	public void setColumnData(int i, Object value) throws Exception {

		if (i == 0) 
			id = (String) value;
		else if (i == 1) 
			name = (String) value;
		else if (i == 2) 
			price = (Double) value;
		else if( i == 3)
			expiryDate = (Date) value;
		else if (i == 4)
			quantity = (Integer) value;
		else if ( i == 5)
			type = (String) value;
		else if ( i == 6)
			imageLink = (String) value;
		else
			throw new Exception("Error: invalid column index in product table");    
	}


	public static int getNumberOfColumns() {

		return 7;
	}

//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", name=" + name + ", price=" + price
//				+ ", expiryDate=" + expiryDate + ", quantity=" + quantity
//				+ ", type=" + type + ", imageLink=" + imageLink
//				+ ", nutritionalInfo=" + nutritionalInfo.toString() + "]";
//	}
}
