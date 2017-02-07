package coen359.vendingmachine.admin.moneymanagement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;


@Entity(name = "admin_money_refill")
public class AdminMoneyRefill implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "vending_machine_id")
	private String vendingMachineId;

	@Column
        @Temporal(DATE)
	private Date date;


	@Column(name = "amount")
	private Double amount;


	public String getVendingMachineId() {
		return vendingMachineId;
	}


	public void setVendingMachineId(String vendingMachineId) {
		this.vendingMachineId = vendingMachineId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "AdminMoneyRefill [vendingMachineId=" + vendingMachineId
				+ ", date=" + date + ", amount=" + amount
				+ ", getVendingMachineId()=" + getVendingMachineId()
				+ ", getDate()=" + getDate() + ", getAmount()=" + getAmount()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
