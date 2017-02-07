package coen359.vendingmachine.admin.vmproduct;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VendingMachineProductKeys {
	
	@Column
	private String vending_machine_id;
	
	@Column
	private String product_id;

}
