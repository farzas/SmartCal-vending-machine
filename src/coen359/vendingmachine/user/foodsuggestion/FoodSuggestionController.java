/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.user.foodsuggestion;

import java.sql.Date;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author DELL
 */
public class FoodSuggestionController implements ListSelectionListener, TableModelListener {

    /**
     * Glue between the view (CourseListGUI) and the model
     * (BudgetListTableModel). No database specific code here. Contains a
     * reference to both the TableModel and the graphical user interface.
     *
     * @author rgrover
     */

    private FoodSuggestionModel foodModel;
    //private SearchListModel foodModelNew;
    private FoodSuggestionGUI gui;
    public String role;
    public String user;
   // List<Object[]> TaskListRelatedtoPrject;

    public FoodSuggestionController(FoodSuggestionGUI gui) {
        this.gui = gui;
        // create the foodModel using the data in the cachedRowSet

        this.foodModel = new FoodSuggestionModel();
        this.foodModel.addTableModelListener(this);

    }

//    public void addingProject() {
//        List<Object> proj = foodModel.getProjectName();
//        for (Object object : proj) {
//            gui.addProject(object);
//            System.out.println("Project added " + proj);
//        }
//        projectUserAddedFlag = true;
//    }

    

    public TableModel getTableModel() {
        return foodModel;
    }

//    public void tableChanged(TableModelEvent e) {
//        try {
//            // get the index of the inserted row
//            //foodModel.getRowSet().moveToCurrentRow();
//            int firstIndex = e.getFirstRow();
//
//            // create a new table model with the new data
//            foodModel = new SearchListModel(foodModel.getList(), foodModel.getEntityManager());
//            foodModel.addTableModelListener(this);
//            // update the JTable with the data
//            gui.updateTable();
//
//        } catch (Exception exp) {
//            exp.getMessage();
//            exp.printStackTrace();
//        }
//    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
      	ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
               
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        try {
            int firstIndex = e.getFirstRow();
            // create a new table model with the new data
            foodModel = new FoodSuggestionModel(foodModel.getList(), foodModel.getEntityManager());
            foodModel.addTableModelListener(this);
            // update the JTable with the data
            gui.updateTable();

        } catch (Exception exp) {
            exp.getMessage();
            exp.printStackTrace();
        }
    }
    
    public void searchProdct(Integer calto, Integer calFrom, Integer fatto, Integer fatfrom, Integer sugarto, Integer sugarFrom, String ptype, String pid, String pname){
        
        foodModel.updateSearchLisResultList(calto,calFrom,fatto,fatfrom,sugarto,sugarFrom,ptype,pid,pname);
        foodModel.addTableModelListener(this);
    }

}
