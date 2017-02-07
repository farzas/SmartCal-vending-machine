/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.payment;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author DELL
 */
public class VMCurrencyService {

    private EntityManager manager;

    public VMCurrencyService(EntityManager manager) {
        this.manager = manager;
    }

    // method to create a new record
    public VMCurrency createVMCurrency(String trasId, String vmid, Double denomination, Double Count) {
        VMCurrency vmCurrency = new VMCurrency();
        //vmCurrency.setId(trasId);
        vmCurrency.setVending_machine_id(vmid);
        vmCurrency.setDenomination(denomination);
        vmCurrency.setCount(Count);
        manager.persist(vmCurrency);
        return vmCurrency;
    }

    // method to read a record
    public VMCurrency readvmCard(String id) {

        VMCurrency vmCurr = manager.find(VMCurrency.class, id);
        return vmCurr;
    }

    // method to read all records
    public List<VMCurrency> readAll() {

        TypedQuery<VMCurrency> query = manager.createQuery("SELECT e FROM vending_machine_currency e", VMCurrency.class);
        List<VMCurrency> result = query.getResultList();

        return result;
    }

    // method to update a record
    public VMCurrency updatevmCurrency(String trasId, String vmid, Double denomination, Double Count) {

        VMCurrency vmCurr = manager.find(VMCurrency.class, trasId);

        if (vmCurr != null) {
            vmCurr.setId(trasId);
            vmCurr.setVending_machine_id(vmid);
            vmCurr.setDenomination(denomination);
            vmCurr.setCount(Count);
        }
        return vmCurr;
    }

    // method to delete a record
    public void deleteSmartCard(String id) {

        VMCurrency vmCurr = manager.find(VMCurrency.class, id);

        if (vmCurr != null) {
            manager.remove(vmCurr);
        }
    }
    
    public List<Object> readLargestId() {

       String queryStr = "SELECT e.transaction_id FROM vending_machine_currency e order by e.transaction_id desc ";
        Query query = manager.createQuery(queryStr, VMCurrency.class); 
       List<Object> vmCurr =   query.setMaxResults(1).getResultList();

        return vmCurr;
    }
}
