/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.management;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import coen359.vendingmachine.admin.viewallvendingmachines.VendingMachineView;
import coen359.vendingmachine.user.payment.VMandPayMediator;
import coen359.vendingmachine.user.product.ProductFrame;
import coen359.vendingmachine.user.product.TotalProduct;

public class VendingMachineController implements TableModelListener {

    private VendingMachineModel vmModel;
    private VendingMachineGUI gui;
    private ProductFrame prgui;
    private VendingMachineModel prModel;
    private VendingMachineGUI guiPr;
    private Object obj;
    ArrayList<ProductFrame> productFrCandy;
    ArrayList<ProductFrame> productFrSand;
    ArrayList<ProductFrame> productFrBev;
    //private VendingMachineModel vendingModel;
    private VendingMachineView vendingView;
    private VMandPayMediator mediator;
    private Double totalcost;

    public VendingMachineController(VendingMachineGUI vmGui) {
        this.gui = vmGui;
        vmModel = new VendingMachineModel();
        mediator = new VMandPayMediator(this);
    }

    public VendingMachineController(ProductFrame prGui) {
        this.prgui = prGui;
        vmModel = new VendingMachineModel();
        //guiPr = new VendingMachinGUI();

    }

    public void addBeveragestoGUI() {
        productFrBev = vmModel.addBeverages();
        mediator.setProductFrBeverages(productFrBev);
        Iterator<ProductFrame> itrtotalProd = productFrBev.iterator();

        while (itrtotalProd.hasNext()) {
            System.out.println("Adding beverage components");
            gui.getjPanelBeverages().add(itrtotalProd.next());
            gui.getjPanelBeverages().validate();
            gui.getjPanelBeverages().repaint();
        }

    }

    public void refreshBeveragestoGUI() {
        //refreshing beverages
       // VendingMachineModel vmModelNew = new VendingMachineModel();
        productFrBev = vmModel.addBeverages();
        mediator.setProductFrBeverages(productFrBev);

        Iterator<ProductFrame> itrBeverage = productFrBev.iterator();

        while (itrBeverage.hasNext()) {
            //System.out.println("Refreshing beverage components");
            gui.getjPanelBeverages().add(itrBeverage.next());
            gui.getjPanelBeverages().validate();
            gui.getjPanelBeverages().repaint();
        }

        // refreshing candy
        productFrCandy = vmModel.addCandy();
        Iterator<ProductFrame> itrCandy = productFrCandy.iterator();
        mediator.setProductFrCandy(productFrCandy);

        while (itrCandy.hasNext()) {
            //System.out.println("Refreshing Candy components");
            gui.getjPanelCandy().add(itrCandy.next());
            gui.getjPanelCandy().validate();
            gui.getjPanelCandy().repaint();
        }

        // Refreshing Sandwiches
        productFrSand = vmModel.addSandwiches();
        Iterator<ProductFrame> itrSand = productFrSand.iterator();
        mediator.setProductFrSandwiches(productFrSand);
        while (itrSand.hasNext()) {
            //System.out.println("Refreshing Sandwiches components");
            gui.getjPanelSandwiches().add(itrSand.next());
            gui.getjPanelSandwiches().validate();
            gui.getjPanelSandwiches().repaint();

        }

        //refreshing other parts of gui 
        this.undo();
        gui.clearTextAreaNutri();

    }

    public void removeBeveragestoGUI(ArrayList<ProductFrame> prfrBev, ArrayList<ProductFrame> prfrCandy, ArrayList<ProductFrame> prfrSandwiches) {
        //removing beverages
        Iterator<ProductFrame> itrprBev = prfrBev.iterator();

        while (itrprBev.hasNext()) {
            //System.out.println("Removing components -Bev");
            gui.getjPanelBeverages().remove(itrprBev.next());
            gui.getjPanelBeverages().validate();
            gui.getjPanelBeverages().repaint(50L);
            vmModel.prFrameObject.clear();
        }

        // removing candy
        Iterator<ProductFrame> itrprCandy = prfrCandy.iterator();

        while (itrprCandy.hasNext()) {
            //System.out.println("Removing components-Candy");
            gui.getjPanelCandy().remove(itrprCandy.next());
            gui.getjPanelCandy().validate();
            gui.getjPanelCandy().repaint(50L);
            vmModel.prFrameObject.clear();
        }

        // removingsandwiches
        Iterator<ProductFrame> itrprSand = prfrSandwiches.iterator();

        while (itrprSand.hasNext()) {
            //System.out.println("Removing components-Sandwiches");
            gui.getjPanelSandwiches().remove(itrprSand.next());
            gui.getjPanelSandwiches().validate();
            gui.getjPanelSandwiches().repaint(50L);
            vmModel.prFrameObject.clear();
        }

    }

    public void addCandytoGUI() {
        productFrCandy = vmModel.addCandy();
        mediator.setProductFrCandy(productFrCandy);

        Iterator<ProductFrame> itr = productFrCandy.iterator();

        while (itr.hasNext()) {
            gui.getjPanelCandy().add(itr.next());

        }
    }

    public void addSandwichestoGUI() {
        productFrSand = vmModel.addSandwiches();
        mediator.setProductFrSandwiches(productFrSand);

        Iterator<ProductFrame> itr = productFrSand.iterator();

        while (itr.hasNext()) {
            gui.getjPanelSandwiches().add(itr.next());

        }

    }

    public void refreshGUI() {
        if ((gui.getjPanelSandwiches().getComponentCount() > 0) || (gui.getjPanelCandy().getComponentCount() > 0) || (gui.getjPanelBeverages().getComponentCount() > 0)) {
            this.getMediator().removeGUIComponents();
            this.getMediator().refreshGUIComponents();
        }
    }

    public ArrayList<ProductFrame> getProductFrCandy() {
        return productFrCandy;
    }

    public ArrayList<ProductFrame> getProductFrSand() {
        return productFrSand;
    }

    public ArrayList<ProductFrame> getProductFrBev() {
        return productFrBev;
    }

    public VMandPayMediator getMediator() {
        return mediator;
    }

    public ArrayList<TotalProduct> GetTotalPriceProduct() {

        return vmModel.getSelectedProject();

    }

    public ArrayList<TotalProduct> getTotalProd() {
        return vmModel.getSelectedProject();

    }

    public void getNutrionalInfo(String prdid, JCheckBox chk, JLabel lbl) {
        //        if (vmModel.getNutrionalInfo().size()>0){
        String nutri = "";
        VendingMachineGUI guivm = null;
        Iterator<Object[]> itr = vmModel.getNutrionalInfo(prdid).iterator();
        Object[] array;

        while (itr.hasNext()) {
            array = itr.next();

            nutri = "Product Name : " + chk.getText() + "\n" + "Price : " + lbl.getText() + "\n" + "Calorie : " + array[0] + "\n" + "Fats : " + array[1] + "\n" + "Sugar : " + array[2];

        }

        System.out.println("Nutri  is : " + nutri);

        //         while (itr.hasNext()) {
        VendingMachineGUI.textAreaNutri.setText(null);
        VendingMachineGUI.textAreaNutri.setText(nutri);
        //    guiPr.settextAreaNutri(nutri);   
        //         }

    }

    public String GetTotalPrice() {
        totalcost = vmModel.calculateTotalPrice();
        return Double.toString(totalcost);

    }

    public void save(VendingMachineModel vmModel) {
        this.obj = vmModel.saveSelectedProduct();
    }

    public void undo() {
        //        vmModel.saveSelectedProduct();
        ArrayList<Object[]> totalProductComp = vmModel.undoToLastSaved(this.vmModel.saveSelectedProduct());

    }

    /////////////method to display vending machines installed - admin and supervisor
    public VendingMachineController(VendingMachineView vendingView) {

        this.vendingView = vendingView;

        // create the tableModel using the data in the cachedRowSet
        vmModel = new VendingMachineModel();
        vmModel.addTableModelListener(this);
    }

    public TableModel getTableModel() {
        return vmModel;
    }

    public void tableChanged(TableModelEvent e) {

        vendingView.updateTable();
    }

    public void addRow(String[] array) throws NumberFormatException, ParseException {

        vmModel.addRow(array);
    }

}
