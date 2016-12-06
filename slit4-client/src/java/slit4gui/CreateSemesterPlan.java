/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.ModuleDataModel;
import DataModel.ModulePlanDataModel;
import DataModel.UserDataModel;
import Managers.ModuleManager;
import Managers.SemesterPlanManager;
import java.sql.Date;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class CreateSemesterPlan 
{
    private Stage stage;
    private UserDataModel activeUser;
    private ModuleManager moduleManager;
    private SemesterPlanManager semesterPlanManager;
    
    public CreateSemesterPlan(UserDataModel activeUser)
    {
        this.activeUser = activeUser;
        this.moduleManager = new ModuleManager();
        this.semesterPlanManager = new SemesterPlanManager();
        this.stage = new Stage();
    }
    
    public BorderPane getCreatePane()
    {
        BorderPane bp = new BorderPane();
        VBox left = new VBox();
        bp.setLeft(left);
        
        ArrayList<ModulePlanDataModel> modulePlanList = new ArrayList<>();
        
        for(ModuleDataModel mdm: moduleManager.getModulesList())
        {
            HBox s = new HBox();
            s.setAlignment(Pos.CENTER);
            s.setSpacing(10);
            Label moduleNameLbl = new Label(mdm.getModuleName());
            moduleNameLbl.setPrefWidth(100);
            moduleNameLbl.setStyle("-fx-font-size:14px;");
            
            DatePicker datePicker = new DatePicker();
            
            datePicker.setOnAction(e->
            {
                ModulePlanDataModel mpdm = new ModulePlanDataModel();
                mpdm.setModuleId(mdm);
                Date date = Date.valueOf(datePicker.getValue());
                mpdm.setPlannedDate(new java.sql.Date(date.getTime()));
                
                modulePlanList.add(mpdm);
            });
            
            s.getChildren().addAll(moduleNameLbl, datePicker);
            left.getChildren().add(s);
        }
        
        Button createPlanBtn = new Button("Create");
        createPlanBtn.setOnAction(e->
        {
            if(modulePlanList.size() == moduleManager.getModulesList().size())
            {
                for(ModulePlanDataModel mpdm: modulePlanList)
                {
                    semesterPlanManager.addModulePlan(mpdm, activeUser);
                }
            
                this.stage.close();
            }
            else
            {
                Stage msgStage = new Stage();
                StackPane spane = new StackPane();
                Label msgLbl = new Label("Please choose a date for all the modules.");
                spane.getChildren().add(msgLbl);
                Scene msgScene = new Scene(spane, 300, 90);
                msgStage.setScene(msgScene);
                msgStage.show();
            }
        });
        
        bp.setBottom(createPlanBtn);
        
        return bp;
    }
    
    public void showCreatePane()
    {
        Scene scene = new Scene(this.getCreatePane(), 300, 350);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
