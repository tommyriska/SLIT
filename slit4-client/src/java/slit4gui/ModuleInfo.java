/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.ModuleDataModel;
import DataModel.RequirementDataModel;
import DataModel.ResourceDataModel;
import DataModel.UserDataModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Ådne
 */
public class ModuleInfo 
{
    private Stage stage;
    private ModuleDataModel moduleDataModel;
    private UserDataModel userDataModel;

    public ModuleInfo(ModuleDataModel moduleDataModel, UserDataModel userDataModel)
    {
        this.stage = new Stage();
        this.moduleDataModel = moduleDataModel;
        this.userDataModel = userDataModel;
    }
    
    public GridPane getModuleInfoPane()
    {
        String labelStyle = "-fx-font-size:16px;";
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color:#d9d9d9;");
        
        Label nameLabel = new Label(moduleDataModel.getModuleName());
        nameLabel.setStyle(labelStyle);
        
        ScrollPane descriptionScroll = new ScrollPane();
        TextArea descriptionText = new TextArea(moduleDataModel.getDescription());
        descriptionText.setEditable(false);
        descriptionText.setWrapText(true);
        descriptionScroll.setContent(descriptionText);
        
        ScrollPane requirementScroll = new ScrollPane();
        Label requirementLabel = new Label("Requirements");
        requirementLabel.setPadding(new Insets(20, 0, 0, 0));
        requirementLabel.setStyle(labelStyle);
        TextArea requirementText = new TextArea();
        requirementText.setEditable(false);
        requirementText.setWrapText(true);
        for(RequirementDataModel rdm: moduleDataModel.getRequirementList())
        {
            requirementText.appendText(rdm.getDescription() + "\n\n");
        }
        requirementScroll.setContent(requirementText);
        
        ScrollPane resourceScroll = new ScrollPane();
        Label resourceLabel = new Label("Resources");
        resourceLabel.setPadding(new Insets(20, 0, 0, 0));
        resourceLabel.setStyle(labelStyle);
        TextArea resourceText = new TextArea();
        resourceText.setEditable(false);
        resourceText.setWrapText(true);
        for(ResourceDataModel rdm: moduleDataModel.getResourceList())
        {
            resourceText.appendText(rdm.getDescription() + "\n\n");
        }
        resourceScroll.setContent(resourceText);
        
        Button deliverButton = new Button("New Delivery");
        deliverButton.setOnAction(e->
        {
            AddDelivery addDelivery = new AddDelivery(moduleDataModel, userDataModel);
            addDelivery.showAddDeliveryScreen();
        });
        
        gp.add(nameLabel, 0, 0);
        gp.add(descriptionScroll, 0, 1);
        gp.add(requirementLabel, 0, 2);
        gp.add(requirementScroll, 0, 3);
        gp.add(resourceLabel, 0, 4);
        gp.add(resourceScroll, 0, 5);
        if(userDataModel.getRights() == 0)
        {
            gp.add(deliverButton, 0, 6);
        }
        
        return gp;
    }
    
    public void show()
    {
        Scene scene = new Scene(getModuleInfoPane(), 490, 450);
        stage.setScene(scene);
        stage.setTitle(moduleDataModel.getModuleName());
        stage.show();
    }
}
