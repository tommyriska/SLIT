/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.ModuleDataModel;
import DataModel.RequirementDataModel;
import DataModel.ResourceDataModel;
import Managers.ModuleManager;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ådne
 */
public class NewModule 
{
    private ModuleDataModel moduleDataModel;
    private ArrayList<ResourceDataModel> resourceList;
    private ArrayList<RequirementDataModel> requirementList;
    private ModuleManager moduleManager;
    private Stage stage;
    
    public NewModule()
    {
        this.moduleDataModel = new ModuleDataModel();
        this.resourceList = new ArrayList<ResourceDataModel>();
        this.requirementList = new ArrayList<RequirementDataModel>();
        this.moduleManager = new ModuleManager();
        this.stage = new Stage();
    }
    
    public void showNewModuleStage()
    {
        Scene scene = new Scene(getModulePane(), 450, 450);
        stage.setTitle("New Module");
        stage.setScene(scene);
        stage.show();
    }
    
    public GridPane getModulePane()
    {
        GridPane gp = new GridPane();
        
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        
        TextArea description = new TextArea();
        description.setPromptText("Description");
        description.setWrapText(true);
        
        ScrollPane resourceScroll = new ScrollPane();
        resourceScroll.setMaxHeight(100);
        resourceScroll.setMinHeight(100);
        VBox resScrollContent = new VBox();
        resourceScroll.setContent(resScrollContent);
        Button addResource = new Button("New Resource");
        addResource.setOnAction(e->
        {
            HBox resource = new HBox();
            TextField resourceField = new TextField();
            resourceField.requestFocus();
            resourceField.setMinWidth(400);
            Button deleteButton = new Button("-");
            deleteButton.setOnAction(e2->
            {
                resScrollContent.getChildren().remove(resource);
            });
            resource.getChildren().addAll(resourceField, deleteButton);
            resScrollContent.getChildren().add(resource);
            resourceScroll.setVvalue(1.0);
        });
        
        ScrollPane requirementScroll = new ScrollPane();
        requirementScroll.setMaxHeight(100);
        requirementScroll.setMinHeight(100);
        VBox reqScrollContent = new VBox();
        requirementScroll.setContent(reqScrollContent);
        Button addRequirement = new Button("New Requirement");
        addRequirement.setOnAction(e3->
        {
            HBox requirement = new HBox();
            TextField requirementField = new TextField();
            requirementField.requestFocus();
            requirementField.setMinWidth(400);
            Button deleteButton = new Button("-");
            deleteButton.setOnAction(e4->
            {
                reqScrollContent.getChildren().remove(requirement);
            });
            requirement.getChildren().addAll(requirementField, deleteButton);
            reqScrollContent.getChildren().add(requirement);
            requirementScroll.setVvalue(1.0);
        });
        
        ComboBox rights = new ComboBox();
        rights.getItems().addAll("Teacher", "Tutor", "Student");
        rights.setValue("Teacher");
        Label rightsLabel = new Label("Can be evaluated by: ");
        
        Button addModule = new Button("Create Module");
        addModule.setOnAction(e5->
        {
            for(Node resource: resScrollContent.getChildren())
            {
                HBox hbox = (HBox) resource;
                
                for(Node textField: hbox.getChildren())
                {
                    if(textField.getClass() == TextField.class)
                    {
                        TextField text = (TextField) textField;
                        ResourceDataModel resourceDataModel = new ResourceDataModel();
                        resourceDataModel.setDescription(text.getText());
                        resourceList.add(resourceDataModel);
                    }
                }
            }
            
            for(Node requirement: reqScrollContent.getChildren())
            {
                HBox hbox = (HBox) requirement;
                
                for(Node textField: hbox.getChildren())
                {
                    if(textField.getClass() == TextField.class)
                    {
                        TextField text = (TextField) textField;
                        
                        RequirementDataModel requirementDataModel = new RequirementDataModel();
                        requirementDataModel.setDescription(text.getText());
                        requirementList.add(requirementDataModel);
                    }
                }
            }
            
            moduleDataModel.setModuleName(nameField.getText());
            moduleDataModel.setDescription(description.getText());
            moduleDataModel.setResourceList(resourceList);
            moduleDataModel.setRequirementList(requirementList);
            if(rights.getValue().equals("Teacher"))
            {
                moduleDataModel.setRights(2);
            }
            else if(rights.getValue().equals("Tutor"))
            {
                moduleDataModel.setRights(1);
            }
            else
            {
                moduleDataModel.setRights(0);
            }
            moduleManager.addModule(moduleDataModel);
            stage.hide();
        });
        
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(6);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().addAll(rightsLabel, rights, addModule);
        
        gp.add(nameField, 0, 0);
        gp.add(description, 0, 1);
        gp.add(resourceScroll, 0, 2);
        gp.add(addResource, 0, 3);
        gp.add(requirementScroll, 0, 4);
        gp.add(addRequirement, 0, 5);
        gp.add(buttonBox, 0, 6);
        
        addModule.requestFocus();
        
        return gp;
    }
}
