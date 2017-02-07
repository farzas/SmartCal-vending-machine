package coen359.vendingmachine.statistics.revenue;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import coen359.vendingmachine.admin.vmproduct.VendingMachine;
import coen359.vendingmachine.admin.vmproduct.VendingMachineProductCompositePK;
import coen359.vendingmachine.product.Product;

@Entity(name = "vending_machine_product")
public class VendingMachineProduct implements Serializable {

	@EmbeddedId
	private VendingMachineProductCompositePK compositeKey;

	@MapsId("vendingMachineId")
	@JoinColumn(name = "vending_machine_id", referencedColumnName = "id")
	@ManyToOne
	private VendingMachine vendingMachine;

	@MapsId("productId")
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@ManyToOne
	private Product productList;

	@Column(name="quantity")
	private Integer quantity;

	
	public Product getProductList() {
		return productList;
	}

	public void setProductList(Product productList) {
		this.productList = productList;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public VendingMachineProductCompositePK getCompositeKey() {
		return compositeKey;
	}

	public void setCompositeKey(VendingMachineProductCompositePK compositeKey) {
		this.compositeKey = compositeKey;
	}

	public VendingMachine getVendingMachine() {
		return vendingMachine;
	}

	public void setVendingMachine(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((compositeKey == null) ? 0 : compositeKey.hashCode());
		result = prime * result
				+ ((productList == null) ? 0 : productList.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((vendingMachine == null) ? 0 : vendingMachine.hashCode());
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
		VendingMachineProduct other = (VendingMachineProduct) obj;
		if (compositeKey == null) {
			if (other.compositeKey != null)
				return false;
		} else if (!compositeKey.equals(other.compositeKey))
			return false;
		if (productList == null) {
			if (other.productList != null)
				return false;
		} else if (!productList.equals(other.productList))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (vendingMachine == null) {
			if (other.vendingMachine != null)
				return false;
		} else if (!vendingMachine.equals(other.vendingMachine))
			return false;
		return true;
	}

	public static int getNumberOfColumns() {
		
		return 3;
	}

	public static String getColumnName(int i) throws Exception {
	

			String colName = null;
			if (i == 0) 
				colName = "Vending Machine Id";
			else if (i == 1)
				colName = "Product Name" ;
			else if (i == 2)
				colName = "Quantity";
			else
				throw new Exception("Access to invalid column number in vending machine product table");

			return colName;
		
	
	}



}

