/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.payment;

import coen359.vendingmachine.admin.moneymanagement.AdminMoneyRefillService;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import coen359.vendingmachine.smartcard.SmartCard;
import coen359.vendingmachine.statistics.revenue.VendingMachineProductPurchasedService;
import coen359.vendingmachine.admin.vmproduct.VendingMachineProductService;
import coen359.vendingmachine.user.product.TotalProduct;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author DELL
 */
public class PayBySmartCard implements Pay {

    private String month = "";
    private String year = "";
    private String cardNumber = "";
    private String securityCode = "";
    private String type = "";
    private SmartCardService smartService;
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
    private static EntityManagerFactory factory;  // JPA  
    private EntityManager manager;
    SmartCard smartCard;
    Double cost;
    VMCurrencyService vmCurrService;
    VendingMachineProductPurchasedService vmProdPurchServices;
    String vmId = "VM1";
    Calendar now = Calendar.getInstance();
    VendingMachineProductService vmProdServices;
    private AdminMoneyRefillService adminMoneyRefillService;
    ArrayList<TotalProduct> totalProd;
    VMandPayMediator mediator;

    public PayBySmartCard(String type, String cardnum, String secuCode, String month, String year, Double co, ArrayList<TotalProduct> toPr) {
        this.type = type;
        this.cardNumber = cardnum;
        this.securityCode = secuCode;
        this.month = month;
        this.year = year;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        smartService = new SmartCardService(manager);
        this.cost = co;
        vmCurrService = new VMCurrencyService(manager);
        vmProdPurchServices = new VendingMachineProductPurchasedService(manager);
        vmProdServices = new VendingMachineProductService();
        totalProd = toPr;
        adminMoneyRefillService = new AdminMoneyRefillService();

    }

    public void setDetails(VMandPayMediator medi) {

        mediator = medi;
    }

    @Override
    public void insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean verify() {
        Boolean verify;
        verify = false;

        smartCard = smartService.readSmartCard(cardNumber);
        System.out.println("Cost : " + this.cost);
        if ((((now.get(Calendar.YEAR)) % 100) <= smartCard.getYear()) && (smartCard.getBalance() >= this.cost)) {

            System.out.println("true for if1");
            verify = true;

        } else if ((((now.get(Calendar.YEAR)) % 100) == smartCard.getYear()) && ((now.get(Calendar.MONTH)) <= smartCard.getMonth())) {

            JOptionPane.showMessageDialog(null, "Your card has expired. ", "Payment: " + "Failure", JOptionPane.INFORMATION_MESSAGE);
            verify = true;

        } else {
            verify = false;

        }
        if (verify) {
            List<Object> vmCurr = vmCurrService.readLargestId();
            //System.out.println("Trans ID : " + vmCurr.get(0));
            String traID = (String) vmCurr.get(0);
            String id = String.valueOf(Integer.parseInt(traID) + 1);
            //System.out.println("Trans ID : " + id);
            adminMoneyRefillService.updateAdminMoneyRefill(vmId, cost);
            EntityTransaction userTransaction = manager.getTransaction();
            userTransaction.begin();
            vmCurrService.createVMCurrency(id, vmId, 0.0, cost);
            userTransaction.commit();

          //code added to update vm Product Purchased
            vmProdPurchServices.updateMachinePurchase(vmId, cost);

            Iterator<TotalProduct> itr = totalProd.iterator();

            while (itr.hasNext()) {
                TotalProduct toPr = itr.next();
                toPr.getProductID();
                toPr.getQuantity();
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
        smartCard = smartService.readSmartCard(cardNumber);
        if ((smartCard.getBalance() < this.cost)) {
            msg = "Your card doesn't have enough balance ";
        } else if ((((now.get(Calendar.YEAR)) % 100) == smartCard.getYear()) && ((now.get(Calendar.MONTH)) <= smartCard.getMonth())) {

            msg = "Your card has expired. ";

        }
        return msg;
    }

    @Override
    public void returnItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}
