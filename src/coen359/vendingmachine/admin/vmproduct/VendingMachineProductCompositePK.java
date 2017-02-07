package coen359.vendingmachine.admin.vmproduct;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VendingMachineProductCompositePK implements Serializable{

	@Column(name = "vending_machine_id")
	private String vendingMachineId;
	
	@Column(name = "product_id")
	private String productId;
	
	public VendingMachineProductCompositePK(){
		
	}

	public String getVendingMachineId() {
		return vendingMachineId;
	}

	public void setVendingMachineId(String vendingMachineId) {
		this.vendingMachineId = vendingMachineId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
