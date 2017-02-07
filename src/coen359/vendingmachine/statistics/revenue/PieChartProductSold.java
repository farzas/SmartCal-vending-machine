/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coen359.vendingmachine.statistics.revenue;

import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import coen359.vendingmachine.admin.vmproduct.VendingMachineService;

public class PieChartProductSold extends Application {

    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
    private static EntityManagerFactory factory;  // JPA  
    private EntityManager manager;
    private VendingMachineService vmService;
    String name = "";
    Integer quant;
    //PIE CHART DATA
    private ObservableList data;

    //MAIN EXECUTOR
    public static void main(String[] args) {
       // Application.launch(PieChartProductSold.class, args);
        launch(args);
    }

    //CONNECTION DATABASE SAVING DATA
    public void buildData() {

        data = FXCollections.observableArrayList();

        vmService = new VendingMachineService(manager);
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        List<Object[]> productSold;
        productSold = vmService.readAllSoldProducts();
        Iterator<Object[]> itr = productSold.iterator();
 
        while (itr.hasNext()) {
            Object[] objProduct = itr.next();
            name = (String) objProduct[0];
            quant = (Integer) objProduct[1];

            //adding data on piechart data
            data.add(new PieChart.Data(name, quant));
        }
        

    }

    @Override
    public void start(Stage stage) throws Exception {
        //PIE CHART
        PieChart pieChart = new PieChart();
        buildData();
        pieChart.getData().addAll(data);

        //Main Scene
        Scene scene = new Scene(pieChart);

        stage.setScene(scene);
        //stage.set;
         stage.show();
    }
}
