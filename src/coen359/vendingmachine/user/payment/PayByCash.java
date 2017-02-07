/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.payment;

import coen359.vendingmachine.admin.moneymanagement.AdminMoneyRefillService;
import coen359.vendingmachine.admin.vmproduct.VendingMachineProductService;
import coen359.vendingmachine.statistics.revenue.VendingMachineProductPurchasedService;
import coen359.vendingmachine.user.product.TotalProduct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import coen359.vendingmachine.user.management.*;
import coen359.vendingmachine.user.product.ProductFrame;
import java.awt.CardLayout;

/**
 *
 * @author DELL
 */
public class PayByCash implements Pay {

    Double amountPais;
    Double totalCost;
    ArrayList<TotalProduct> totalProd;
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
    private static EntityManagerFactory factory;  // JPA  
    private EntityManager manager;
    VMCurrencyService vmCurrService;
    VendingMachineProductService vmProdServices;
    VendingMachineProductPurchasedService vmProdPurchServices;
    VendingMachineGUI vmGUI;
    String vmId = "VM1";
    private AdminMoneyRefillService adminMoneyRefillService;
    VMandPayMediator mediator;

    public PayByCash() {
        mediator = new VMandPayMediator(this);
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        vmCurrService = new VMCurrencyService(manager);
        vmProdPurchServices = new VendingMachineProductPurchasedService(manager);
        vmProdServices = new VendingMachineProductService();
        adminMoneyRefillService = new AdminMoneyRefillService();

    }

    @Override
    public void insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDetails(String paidAmt, String total, ArrayList<TotalProduct> toPr, VMandPayMediator medi) {
        amountPais = Double.parseDouble(paidAmt);
        totalCost = Double.parseDouble(total);
        totalProd = toPr;
        mediator = medi;
    }

    @Override
    public Boolean verify() {
        Boolean verify = false;

        if (totalCost <= amountPais) {
            verify = true;
        }
        if (verify) {
//            List<Object> vmCurr = vmCurrService.readLargestId();
//            //System.out.println("Trans ID : " + vmCurr.get(0));
//            String traID = (String) vmCurr.get(0);
//            String id = String.valueOf(Integer.parseInt(traID) + 1);
            //System.out.println("Trans ID : " + id);
            adminMoneyRefillService.updateAdminMoneyRefill(vmId, totalCost);
            EntityTransaction userTransaction = manager.getTransaction();
            userTransaction.begin();
            vmCurrService.createVMCurrency("1", vmId, totalCost - amountPais, totalCost);
            userTransaction.commit();

            vmProdPurchServices.updateMachinePurchase(vmId, totalCost);

            Iterator<TotalProduct> itr = totalProd.iterator();

            while (itr.hasNext()) {
                TotalProduct toPr = itr.next();
                toPr.getProductID();
                toPr.getQuantity();
                System.out.println("Quantity  :"+toPr.getUpdatedQuantity()+"of id :"+ toPr.getProductID());
                toPr.getProductNamePrice();
                Integer quant = vmProdServices.getVendingMachineProductQuantity(vmId, toPr.getProductID());
                vmProdServices.updateVendingMachineProduct(vmId, toPr.getProductID(), quant - toPr.getQuantity());
            }
            mediator.removeGUIComponents();
           mediator.refreshGUIComponents();
        }
        return verify;
    }

    public String getMessage() {
        String msg = "";

        if (totalCost > amountPais) {
            msg = "Amount paid is less than Total Cost ";
        }
        return msg;
    }

    @Override
    public void returnItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
