package coen359.vendingmachine.statistics.revenue;

import java.io.Serializable;

import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "vending_machine_product_purchased")
public class VendingMachineProductPurchased implements Serializable {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column
	private String vending_machine_id;
	
	@Column
	private Double purchase;

	public String getVending_machine_id() {
		return vending_machine_id;
	}

	public void setVending_machine_id(String vending_machine_id) {
		this.vending_machine_id = vending_machine_id;
	}

	public Double getPurchase() {
		return purchase;
	}

	public void setPurchase(Double purchase) {
		this.purchase = purchase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((purchase == null) ? 0 : purchase.hashCode());
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
		VendingMachineProductPurchased other = (VendingMachineProductPurchased) obj;
		if (purchase == null) {
			if (other.purchase != null)
				return false;
		} else if (!purchase.equals(other.purchase))
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
		return "VendingMachineProductPurchased [vending_machine_id="
				+ vending_machine_id + ", purchase=" + purchase + "]";
	}
	
	



}