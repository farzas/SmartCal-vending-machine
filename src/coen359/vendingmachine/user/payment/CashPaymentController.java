/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.payment;

import coen359.vendingmachine.user.product.ProductFrame;
import coen359.vendingmachine.user.product.TotalProduct;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class CashPaymentController {

    String amountPaid = "";
    String totalCost = "";
    private CashPaymentGUI gui;
    PayByCash payCash;
    ArrayList<TotalProduct> totalProdPrurch;
   VMandPayMediator mediator;

    public CashPaymentController(CashPaymentGUI cashGui) {
        this.gui = cashGui;
        totalCost = String.valueOf(gui.getCost());
        amountPaid = gui.getjTextCost();
        payCash = new PayByCash();
        totalProdPrurch = gui.toPr;
        mediator = gui.getMediator();
    }

    public Boolean verifySmartCard() {
        boolean verify;
        this.Updategui();
        payCash.setDetails(amountPaid, totalCost, totalProdPrurch,mediator);
        verify = payCash.verify();
        if (!verify) {
            gui.infoBox(payCash.getMessage(), "Failure");
        }
        return verify;

    }

    public void Updategui() {
        Double returnAmt = Double.parseDouble(gui.getjTextCost()) - gui.getCost();
        System.out.println("Return Amount before :" + returnAmt);
        if (returnAmt > 0) {
            System.out.println("Return Amount :" + returnAmt);
            gui.setVisibiltyJlabelReturn();
            gui.setjLabelReturnCash(String.valueOf(returnAmt));
             
        }
    }
}
