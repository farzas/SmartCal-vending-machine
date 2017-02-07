/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.management;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import coen359.vendingmachine.admin.vmproduct.VendingMachine;
import coen359.vendingmachine.admin.vmproduct.VendingMachineProductService;
import coen359.vendingmachine.user.product.ProductFrame;
import coen359.vendingmachine.user.product.TotalProduct;
import coen359.vendingmachine.statistics.revenue.VendingMachineProduct;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class VendingMachineModel extends AbstractTableModel {

    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
    private static EntityManagerFactory factory;  // JPA  
    private EntityManager manager;

    private ArrayList<ProductFrame> productFrBeverages;
    private ArrayList<ProductFrame> productFrCandy ;
    private ArrayList<ProductFrame> productFrSandwiches;

    ArrayList<TotalProduct> totalProductPrice;
    VendingMachineService vmService;

    ArrayList<Object[]> prFrameObject ;
    TotalProduct totalperProduct;

    ArrayList<String> prIdAddedTotal = new ArrayList<String>();
    ArrayList<Object[]> totprCompo ;

	//to display vending machines
    // stores the model data in a List collection of type Product
    List<VendingMachine> vendingList;

    // This field contains additional information about the results
    int numcols, numrows;

    //DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
//Bypassed by Namrata as it is showing error 
    String vmID = "VM1";
    VendingMachineProductService vmProdServices;

    public VendingMachineModel() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();

        vmService = new VendingMachineService(manager);

		// to display the vending machines 
		//vendingMachineService = new VendingMachineService(manager);
        // read all the records from product
       
        prFrameObject = new ArrayList<Object[]>();
         prFrameObject.clear();
        
         
        vendingList = vmService.readAll();
        vmProdServices = new VendingMachineProductService();
        // update the number of rows and columns in the model
        numrows = vendingList.size();
        numcols = VendingMachine.getNumberOfColumns();
    }
 
    public ArrayList<ProductFrame> addBeverages() {
        List<Object[]> productListBeverage;
        Object productDetails[];
        String imageLink = "";
        Double price;
        Integer quantity;
        String name = "";
        ProductFrame prFrBev;
        String productID = "";
       
         productFrBeverages = new ArrayList<ProductFrame>();
        productListBeverage = vmService.readBeverageID(vmID);
        Iterator<Object[]> itr = productListBeverage.iterator();

        while (itr.hasNext()) {

            Object[] objProduct = itr.next();
            name = (String) objProduct[0];
            price = (Double) objProduct[1];
            quantity = (Integer) objProduct[2];
            imageLink = (String) objProduct[3];
            productID = (String) objProduct[4];
            prFrBev = new ProductFrame(imageLink, name, price.toString(), productID, quantity);
            productFrBeverages.add(prFrBev);
            prFrameObject.add(prFrBev.getProductObject());
            System.out.println("Product Frame object size : " + prFrameObject.size());
        }
        return productFrBeverages;

    }

    public ArrayList<ProductFrame> addCandy() {
        List<Object[]> productListCandy;
        Object productDetails[];
        String imageLink = "";
        Double price;
        Integer quantity;
        String name = "";
        ProductFrame prFrCandy;
        String productID = "";
        
        productFrCandy = new ArrayList<ProductFrame>();
        productListCandy = vmService.readCandyID(vmID);
        Iterator<Object[]> itr = productListCandy.iterator();

        while (itr.hasNext()) {

            Object[] objProduct = itr.next();
            name = (String) objProduct[0];
            price = (Double) objProduct[1];
            quantity = (Integer) objProduct[2];
            imageLink = (String) objProduct[3];
            productID = (String) objProduct[4];
            prFrCandy = new ProductFrame(imageLink, name, price.toString(), productID, quantity);
            productFrCandy.add(prFrCandy);
            prFrameObject.add(prFrCandy.getProductObject());
            System.out.println("Product Frame object size : " + prFrameObject.size());
        }
        return productFrCandy;

    }

    public ArrayList<ProductFrame> addSandwiches() {
        List<Object[]> productListSandwiches;
        Object productDetails[];
        String imageLink = "";
        Double price;
        Integer quantity;
        String name = "";
        String productID = "";
        productFrSandwiches = new ArrayList<ProductFrame>();
        ProductFrame prFrSandwiches;
        productListSandwiches = vmService.readSandwichesID(vmID);
        Iterator<Object[]> itr = productListSandwiches.iterator();

        while (itr.hasNext()) {

            Object[] objProduct = itr.next();
            name = (String) objProduct[0];
            price = (Double) objProduct[1];
            quantity = (Integer) objProduct[2];
            imageLink = (String) objProduct[3];
            productID = (String) objProduct[4];
            prFrSandwiches = new ProductFrame(imageLink, name, price.toString(), productID, quantity);
            productFrSandwiches.add(prFrSandwiches);
            prFrameObject.add(prFrSandwiches.getProductObject());
            System.out.println("Product Frame object size : " + prFrameObject.size());
        }
        return productFrSandwiches;

    }

    public List<Object[]> getPrice() {

        return prFrameObject;

    }

    public ArrayList<TotalProduct> getSelectedProject() {
        JCheckBox chkBoxProductSelected = new JCheckBox("");
        JLabel lblProductNamePrice = new JLabel();
        String selectedProductPrice = "";
        String productID = "";
        String productName = "";
        totprCompo = new ArrayList<Object[]>();
        totalProductPrice = new ArrayList<TotalProduct>();
        Iterator<Object[]> itr = prFrameObject.iterator();
        while (itr.hasNext()) {
            Object[] objProduct = itr.next();
            chkBoxProductSelected = (JCheckBox) objProduct[1];
            if (chkBoxProductSelected.isSelected()) {
                lblProductNamePrice = (JLabel) objProduct[2];
                productID = (String) objProduct[4];
                productName = chkBoxProductSelected.getText();
                selectedProductPrice = lblProductNamePrice.getText();
                              //  System.out.println("Quantity: "+(String) objProduct[0]);
                // if neccessary use this code to avoid double adding to totalProducts
                //                prIdAddedTotal.add(productID);
                //                 if (prIdAddedTotal.contains(productID)) {
                //    System.out.println("Product allready added to cart");
                //} else {
                //    System.out.println("Product added to total Products ");
                //}
                totalperProduct = new TotalProduct(Double.parseDouble(selectedProductPrice.substring(selectedProductPrice.indexOf("$") + 1)), productName, productID);
                totalProductPrice.add(totalperProduct);
                System.out.println("Selected Produts : " + selectedProductPrice);
                System.out.println("Selected ProdutsID : " + productID);
                System.out.println("Selected ProdutsName : " + productName);

                totprCompo.add(totalperProduct.getTotalProductComponent());
                this.saveSelectedProduct();
            }
        }
        return totalProductPrice;
    }

    public CancelOrder saveSelectedProduct() {

        return new CancelOrder(totalProductPrice, totprCompo);
    }

    public ArrayList<Object[]> undoToLastSaved(Object canOrder) {
        ArrayList<TotalProduct> totpr;
        ArrayList<Object[]> totPrComp;
        CancelOrder cancelOrderUndo = (CancelOrder) canOrder;
        totpr = cancelOrderUndo.totalProduct;
        totPrComp = cancelOrderUndo.totalProductCompo;

        return totPrComp;
    }

    public List<Object[]> getNutrionalInfo(String prid) {

        List<Object[]> nutrionalInfo = null;
        nutrionalInfo = vmService.readNutrionalInfo(prid);
        return nutrionalInfo;
    }

    public Double calculateTotalPrice() {
        String price = "";
        Integer pr;
        Double totPerProduct = 0.0;
        Iterator<TotalProduct> itr = totalProductPrice.iterator();
        TotalProduct totPr;
        String prodID;
        while (itr.hasNext()) {
            totPr = itr.next();
            price = (totPr.getProductNamePrice()).substring(totPr.getProductNamePrice().indexOf("$") + 1);
            System.out.println("Price :" + price);
            System.out.println("Quantity :" + totPr.getQuantity());
            totPr.setQuantity(totPr.getQuantity().toString());
            prodID = totPr.getProductID();
            Integer quant = vmProdServices.getVendingMachineProductQuantity(vmID, prodID);
            if (quant < totPr.getQuantity()) {
                Integer quanttoCheck = quant+1;
                String infoMessage = "Please select the quantity for product: " + totPr.getProductName().trim()+ " less than " + quanttoCheck;
                JOptionPane.showMessageDialog(null, infoMessage, "Payment: " + "Quantity", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Please select the quantity for product: " + totPr.getProductName() + " less than " + quant + 1);
                break;
            }
            totPerProduct += Double.parseDouble(price) * totPr.getQuantity();
        }
        return totPerProduct;
    }

    private class CancelOrder {

        ArrayList<TotalProduct> totalProduct;
        ArrayList<Object[]> totalProductCompo;

        public CancelOrder(ArrayList<TotalProduct> totpr, ArrayList<Object[]> totPrCompo) {
            this.totalProduct = totpr;
            this.totalProductCompo = totPrCompo;
        }

    }

	///methods to display the vending machines - admin and supervisor
    // create a new table model using the existing data in list
    public VendingMachineModel(List<VendingMachine> list, EntityManager entityManager) {

        this.manager = entityManager;
        vmService = new VendingMachineService(entityManager);

        vendingList = list;

        numrows = vendingList.size();
        numcols = VendingMachine.getNumberOfColumns();
    }

    // returns a count of the number of rows
    public int getRowCount() {
        return numrows;
    }

    // returns a count of the number of columns
    public int getColumnCount() {
        return numcols;
    }

    // table cells are not editable
    public boolean isCellEditable(int rowIndex, int colIndex) {
        return false;
    }

    public Class<?> getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

    // returns the data at the given row and column number
    public Object getValueAt(int row, int col) {

        try {

            switch (col) {

                case 0:
                    return vendingList.get(row).getId();
                case 1:
                    return vendingList.get(row).getLocation();
                case 2:
                    return vendingList.get(row).getCategory();

            }
            return "";
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    // returns the name of the column
    public String getColumnName(int col) {

        try {

            return VendingMachine.getColumnName(col);
        } catch (Exception err) {

            return err.toString();
        }

    }

    public List<VendingMachine> getProductList() {

        return vendingList;
    }

    public EntityManager getEntityManager() {

        return manager;
    }

		// In this method, a newly inserted row in the GUI is added to the table model as well as the database table
    // The argument to this method is an array containing the data in the textfields of the new row.
    public void addRow(Object[] array) throws NumberFormatException, ParseException {

		//public void addRow(Object[] array){
        // add row to database
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();

        // while creating product, nutritionaInfo will also be persisted
        VendingMachine feedback_row = vmService.createVendingMachine((String) array[0], (String) array[1], (String) array[2]);//((String) array[0], (String) array[1], Double.parseDouble((String) array[2]), dateFormatter.parse((String) array[3]), Integer.parseInt((String) array[4]), (String) array[5], (String) array[6], Integer.parseInt((String) array[7]), Integer.parseInt((String) array[8]), Integer.parseInt((String) array[9]));
        userTransaction.commit();

        // set the current row to rowIndex
        vendingList.add(feedback_row);

        int row = vendingList.size();

        numrows++;

        fireTableRowsInserted(row, row);
    }

}
