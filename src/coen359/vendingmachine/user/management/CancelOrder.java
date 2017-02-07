/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coen359.vendingmachine.user.management;

import java.util.ArrayList;

import coen359.vendingmachine.user.product.TotalProduct;

/**
 *
 * @author DELL
 */
public class CancelOrder {
    ArrayList<TotalProduct> totalProduct;
    public CancelOrder(ArrayList<TotalProduct> totpr){
        this.totalProduct =totpr ;
    }
    
}
