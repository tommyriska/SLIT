/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.ModuleDataModel;
import DataModel.ModulePlanDataModel;
import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import Managers.ModuleManager;
import Managers.SemesterPlanManager;
import java.util.Date;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class SemesterPlan 
{
    private UserDataModel user;
    private Stage stage;
    private SemesterPlanDataModel semesterPlan;
    private ModuleManager moduleManager;
    
    public SemesterPlan(UserDataModel userDataModel)
    {
        SemesterPlanManager spm = new SemesterPlanManager();
        
        this.stage = new Stage();
        this.user = userDataModel;
        this.semesterPlan = spm.getSemesterPlanById(user);
        this.moduleManager = new ModuleManager();
    }
    
    public GridPane getSemesterPlanPane()
    {
        GridPane gp = new GridPane();
        
        HBox planBox = new HBox();
        VBox moduleBox = new VBox();
        VBox dateBox = new VBox();
        
        planBox.getChildren().addAll(moduleBox, dateBox);
        
        HBox moduleLabelBox = new HBox();
        Label moduleLabel = new Label("Module");
        moduleLabel.setStyle("-fx-background-color:#cccccc; -fx-font-size:16px; -fx-font-weight:bold;");
        moduleLabel.setMinSize(150, 50);
        moduleLabel.setAlignment(Pos.CENTER);
        moduleLabelBox.getChildren().add(moduleLabel);
        
        HBox dateLabelBox = new HBox();
        Label dateLabel = new Label("Date");
        dateLabel.setStyle("-fx-background-color:#cccccc; -fx-font-size:16px; -fx-font-weight:bold;");
        dateLabel.setMinSize(150, 50);
        dateLabel.setAlignment(Pos.CENTER);
        dateLabelBox.getChildren().add(dateLabel);
        
        dateBox.getChildren().add(dateLabelBox);
        moduleBox.getChildren().add(moduleLabelBox);
        
        for(ModuleDataModel mdm: moduleManager.getModulesList())
        {  
            HBox moduleNameLabelBox = new HBox();
            Label moduleNameLabel = new Label(mdm.getModuleName());
            moduleNameLabel.setStyle("-fx-background-color:#d9d9d9; -fx-font-size:16px;");
            moduleNameLabel.setMinSize(150, 50);
            moduleNameLabel.setAlignment(Pos.CENTER);
            moduleNameLabelBox.getChildren().add(moduleNameLabel);
            
            moduleBox.getChildren().add(moduleNameLabelBox);
            
            for(ModulePlanDataModel mpdm: semesterPlan.getModulePlanList())
            {
                if(Objects.equals(mpdm.getModuleId().getModuleId(), mdm.getModuleId()))
                {
                    String plannedDate = new java.sql.Date(mpdm.getPlannedDate().getTime()).toString();
                    HBox dateValueLabelBox = new HBox();
                    Label dateValueLabel = new Label(plannedDate);
                    dateValueLabel.setStyle("-fx-background-color:#d9d9d9; -fx-font-size:16px;");
                    dateValueLabel.setMinSize(150, 50);
                    dateValueLabel.setAlignment(Pos.CENTER);
                    dateValueLabelBox.getChildren().add(dateValueLabel);

                    dateBox.getChildren().add(dateValueLabelBox);
                    
                    break;
                }
            }
        }
        
        gp.add(moduleBox, 0, 0);
        gp.add(dateBox, 1, 0);
        
        return gp;
    }
    
    public void showSemesterPlan()
    {
        Scene scene = new Scene(this.getSemesterPlanPane(), 300, 450);
        
        stage.setX(390);
        stage.setY(200);
        
        stage.setTitle("Semesterplan - " + user.getFirstName() + " " + user.getLastName());
        stage.setScene(scene);
        stage.show();
    }
}
