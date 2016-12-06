/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.DeliveryDataModel;
import DataModel.ModuleDataModel;
import Managers.DeliveryManager;
import Managers.ModuleManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class Charts 
{
    public HashMap<String, Integer> getHashMap()
    {
        LinkedHashMap<String, Integer> approvals = new LinkedHashMap<>();
        DeliveryManager dm = new DeliveryManager();
        ModuleManager mm = new ModuleManager();
        
        for(ModuleDataModel mdm: mm.getModulesList())
        {
            approvals.put(mdm.getModuleName(), 0);
        }
        
        for(DeliveryDataModel ddm: dm.getAllDeliveries())
        {
            if(ddm.getStatus() == 1)
            {
                int count = approvals.get(ddm.getModuleID().getModuleName());
                count+=1;
                //approvals.remove(ddm.getModuleID().getModuleName());
                approvals.put(ddm.getModuleID().getModuleName(), count);
            }
        }
        
        return approvals;
    }
    
    public BarChart getChart()
    {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        
        bc.setTitle("Approved deliveries");
        xAxis.setLabel("Module");
        yAxis.setLabel("Approved amount");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Approved amount");
        
        HashMap<String, Integer> approvals = this.getHashMap();
        
        for(String moduleName: approvals.keySet())
        {
            series.getData().add(new XYChart.Data(moduleName, approvals.get(moduleName)));
        }
        
        bc.getData().add(series);
        return bc;
    }
    
    public void showChart()
    {
        Stage stage = new Stage();
        Scene scene = new Scene(this.getChart(), 800, 800);
        stage.setScene(scene);
        stage.show();
    }
            
}
