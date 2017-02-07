/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.foodsuggestion;

//import Product.*;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.StyleConstants;

/**
 *
 * @author DELL
 */
/**
 *
 * @author DELL
 */
public class FoodSuggestionModel extends AbstractTableModel  {

    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
    private static EntityManagerFactory factory;  // JPA  
    private EntityManager manager;				// JPA 
    private List<Object[]> foodList;
    private List<Object[]> searchFoodList;
    private FoodSuggestionService foodservice;
   

    // This field contains additional information about the results   
    int numcols, numrows;           // number of rows and columns
    /////////////////////////////////////*********************NEW MODIFICATION************************////////////////////////////////////

    public FoodSuggestionModel() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
      
        foodservice = new FoodSuggestionService(manager);
        //foodList = foodservice.readAll();
        foodList=foodservice.readAllwithImage(foodservice.readAll());
        // update the number of rows and columns in the model
        numrows = foodList.size();
        System.out.println("Number of rows fetched in table model:" + numrows);
        //  this.getRowsCols();
    }

    public FoodSuggestionModel(List<Object[]> list, EntityManager em) {
        foodList = list;
        numrows = foodList.size();
       
        numcols = this.numcols;
        manager = em;
        foodservice = new FoodSuggestionService(manager);
    }

    public void updateSearchLisResultList(Integer calto, Integer calFrom, Integer fatto, Integer fatfrom, Integer sugarto, Integer sugarFrom, String ptype, String pid, String pname) {
        foodList.clear();
        foodList = foodservice.readSearchedProd(calto, calFrom, fatto, fatfrom, sugarto, sugarFrom, ptype, pid, pname);
         foodList=foodservice.readAllwithImage(foodList);
        numrows = foodList.size();
        System.out.println("Number of rows fetched:" + numrows);
        fireTableDataChanged();
    }
    // returns a count of the number of rows

    public int getRowCount() {
        return foodList.size();
    }

    public List<Object[]> getProjectName() {

        return foodList;
    }

    // returns a count of the number of columns
    public int getColumnCount() {
        return 9;
    }

    // returns the data at the given row and column number
    public Object getValueAt(int row, int col) {
        try {
            //System.out.println("Get value at  at row col : " + SearchListResultList.get(row)[col]);
            return foodList.get(row)[col];
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
 @Override
    public void setValueAt(Object aValue, int row, int col) {
        //data[rowIndex][columnIndex] = (String) aValue;
        try {

            Object element = foodList.get(row)[col];
            //  System.out.println("Set value at " + element.toString());
          
            element = aValue;
            System.out.println("Element value at " + col + "is " + aValue);
            fireTableCellUpdated(row, col);
        } catch (Exception err) {
            err.toString();
        }
    }
    // table cells are not editable
    public boolean isCellEditable(int rowIndex, int colIndex) {
        return colIndex==9;
    }

    public Class<?> getColumnClass(int col) {

        if (col == 0) {
            return String.class;
        }
        if (col == 1) {
            return String.class;
        }
        if (col == 2) {
            return String.class;
        }
        if (col == 3) {
            return Icon.class;
        }
        if (col == 4) {
            return Integer.class;
        }
        if (col == 5) {
            return Integer.class;
        }
        if (col == 6) {
            return Integer.class;
        }
        if (col == 7) {
            return Double.class;
        }
        if (col == 8) {
            return Integer.class;
        }
            else {
            return Object.class;
        }

    }


    public String getColumnName(int i) {

        try {
            
             
	   String colName = null;
//           if (i == 0) 
//		   colName = "Select";
	   if (i == 0) 
		   colName = "Product ID";
	   else if (i == 1)
		   colName = "Name";
	   else if (i == 2)
		   colName = "Type";
	   else if (i == 3)
		   colName = "Image";
	   else if (i == 4)
		   colName = "Calorie";
            else if (i == 5)
		   colName = "Fats";
            else if (i == 6)
		   colName = "Sugar";
            else if (i == 7)
		   colName = "Price";
            else if (i == 8)
		   colName = "Quantity";
            else if (i == 9)
		   colName = "Select";
	   else 
		   throw new Exception("Access to invalid column number in Task table");
	   
	       
           return colName;
}
        catch (Exception err) {
            return err.toString();
        
    }
    }

    // update the data in the given row and column to aValue
   
 
    
    public List<Object[]> getList() {
        return foodList;
    }

    public EntityManager getEntityManager() {
        return manager;
    }

    // create a new table model using the existing data in list
    // create a new table model using the existing data in list
    public void FoodSuggestionModelModel(List<Object[]> list, EntityManager em) {
        foodList = list;
        numrows = foodList.size();


        numcols = this.getColumnCount();
        manager = em;
        foodservice = new FoodSuggestionService(manager);

    }

    public void clearRow(Object[] array) {
       

    }
}
