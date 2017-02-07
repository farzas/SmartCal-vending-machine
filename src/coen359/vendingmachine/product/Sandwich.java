package coen359.vendingmachine.product;

import static javax.persistence.TemporalType.DATE;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import coen359.vendingmachine.product.nutritionalinfo.NutritionalInfo;

@Entity
public class Sandwich extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "expiry_date")
	@Temporal(DATE)
	private java.util.Date expiryDate;

	@OneToOne(mappedBy = "product", cascade=CascadeType.ALL, orphanRemoval = true) 
	private NutritionalInfo nutritionalInfo;
	
	public java.util.Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public NutritionalInfo getNutritionalInfo() {
		return nutritionalInfo;
	}

	public void addNutritionalInfo(NutritionalInfo nutritionalInfo) {

		this.nutritionalInfo = nutritionalInfo;
		nutritionalInfo.setProduct(this);
	}
	
	
}
