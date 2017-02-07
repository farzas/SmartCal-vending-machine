/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.payment;

import coen359.vendingmachine.user.product.TotalProduct;
import java.util.ArrayList;


/**
 *
 * @author DELL
 */
public class SmartCardController {

    private PayBySmartCard smartCard;
        private SmartCardGUI gui;
        private String month = "";
        private String year = "";
        private String cardNumber = "";
        private String securityCode = "";
        private String type = "";
        private Double cost ;
         private VMandPayMediator mediator;
        private ArrayList<TotalProduct> totProd;
    public SmartCardController(SmartCardGUI vmGui) {
        this.gui = vmGui;
        month = gui.getjComboMonth();
        year = gui.getjComboYear();
        cardNumber = gui.getjTextCardNumb();
        securityCode = gui.getjTextSecurity();
        cost = gui.getCost();
        type = gui.getjRadioButAmerican() + gui.getjRadioButDiscover() + gui.getjRadioButMaster() + gui.getjRadioButVisa();
       totProd=gui.getTotPr();
       smartCard = new PayBySmartCard(type,cardNumber,securityCode,month,year,cost,totProd);
 mediator = gui.getMediator();
    }

    public Boolean verifySmartCard() {
        boolean verify ;
        smartCard.setDetails(mediator);
        verify =smartCard.verify();
        if (!verify){
            gui.infoBox(smartCard.getMessage(),"Failure");
        }
        return verify;

    }
}
