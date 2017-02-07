/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.payment;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author DELL
 */
@Entity(name = "vending_machine_currency")
public class VMCurrency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private String transaction_id;

    @Column
    private String vending_machine_id;
//   @ManyToOne(cascade=CascadeType.MERGE)
//    @JoinColumn(name="id", insertable = false, updatable=false)
//    private VendingMachine vm ;

    @Column
    private Double denomination;

    @Column
    private Double count;

    public String getId() {
        return transaction_id;
    }

    public void setId(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getVending_machine_id() {
        return vending_machine_id;
    }

    public void setVending_machine_id(String vending_machine_id) {
        this.vending_machine_id = vending_machine_id;
    }

    public Double getDenomination() {
        return denomination;
    }

    public void setDenomination(Double denomination) {
        this.denomination = denomination;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (transaction_id != null ? transaction_id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof VMCurrency)) {
//            return false;
//        }
//        VMCurrency other = (VMCurrency) object;
//        if ((this.transaction_id == null && other.transaction_id != null) || (this.transaction_id != null && !this.transaction_id.equals(other.transaction_id))) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public String toString() {
        return "VMCurrency{" + "transaction_id=" + transaction_id + ", vending_machine_id=" + vending_machine_id + ", denomination=" + denomination + ", count=" + count + '}';
    }

}
