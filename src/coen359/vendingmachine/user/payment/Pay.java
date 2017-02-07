/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coen359.vendingmachine.user.payment;

/**
 *
 * @author DELL
 */
public interface Pay {
    public void insert();
    public Boolean verify();
    public void returnItem();
    
    
}
