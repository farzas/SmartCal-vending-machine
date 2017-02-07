/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coen359.vendingmachine.user.payment;

import coen359.vendingmachine.user.management.VendingMachineController;
import coen359.vendingmachine.user.product.ProductFrame;
import coen359.vendingmachine.user.product.TotalProduct;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class VMandPayMediator {
    private ArrayList<ProductFrame> prFrameBeverage ;
    private ArrayList<ProductFrame> prFrameCandy ;
    private ArrayList<ProductFrame> prFrameSandwiches ;
    private VendingMachineController vmController;
    private PayByCash payCash;
    private ArrayList<TotalProduct> totProducts;
    private Double totalCost;
    public VMandPayMediator(VendingMachineController vmCont){
        this.vmController = vmCont;
        
    }
     public VMandPayMediator(PayByCash pCash){
        this.payCash = pCash;
        
    }
    public void setProductFrBeverages(ArrayList<ProductFrame> prFrBev){
        prFrameBeverage =prFrBev ;
           }
    
     public void setProductFrCandy(ArrayList<ProductFrame> prFrCandy){
        prFrameCandy =prFrCandy ;
           }
      public void setProductFrSandwiches(ArrayList<ProductFrame> prFrSan){
        prFrameSandwiches =prFrSan ;
           }
      
//     public ArrayList<ProductFrame> getProductFrBeverages(){
//       return prFrameBeverage ;
//    }
//     
//      public ArrayList<ProductFrame> getProductFrCandy(){
//       return prFrameCandy ;
//    }
//      
//       public ArrayList<ProductFrame> getProductFrSandwiches(){
//       return prFrameSandwiches ;
//    }
    
    public void removeGUIComponents(){
       vmController.removeBeveragestoGUI(prFrameBeverage,prFrameCandy,prFrameSandwiches);
    }
    public void refreshGUIComponents(){
        vmController.refreshBeveragestoGUI();
    }
    
//    public void setTotalProducts(ArrayList<TotalProduct> toPr){
//       totProducts= toPr;
//    }
    public ArrayList<TotalProduct> getTotalProducts(){
      totProducts=  vmController.getTotalProd();
        return totProducts;
    }
    public void getCost(){
        totalCost= Double.parseDouble(vmController.GetTotalPrice());
    }
    
}
