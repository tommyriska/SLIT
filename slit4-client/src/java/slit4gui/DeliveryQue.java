/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.DeliveryDataModel;
import Managers.DeliveryManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class DeliveryQue 
{
    private Stage stage;
    private DeliveryManager deliveryManager;
    
    public DeliveryQue()
    {
        this.stage = new Stage();
        this.deliveryManager = new DeliveryManager();
    }
    
    public GridPane getDeliveryQuePane()
    {
        GridPane gp = new GridPane();
        
        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(350, 400);
        VBox content = new VBox();
        
        HBox nameLabels = new HBox();
        Label name = new Label("Name");
        name.setPrefSize(150, 30);
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-background-color:#999999");
        Label module = new Label("Module");
        module.setPrefSize(80, 30);
        module.setAlignment(Pos.CENTER);
        module.setStyle("-fx-background-color:#999999;");
        Label queNumber = new Label("Que");
        queNumber.setPrefSize(40, 30);
        queNumber.setAlignment(Pos.CENTER);
        queNumber.setStyle("-fx-background-color:#999999;");
        Label date = new Label("Date");
        date.setPrefSize(80, 30);
        date.setAlignment(Pos.CENTER);
        date.setStyle("-fx-background-color:#999999;");
        
        nameLabels.getChildren().addAll(name, module, queNumber, date);
        content.getChildren().add(nameLabels);
        
        sp.setContent(content);
        
        int que = 1;
        
        for(DeliveryDataModel ddm: deliveryManager.getAllDeliveries())
        {
            if(ddm.getStatus() == 0)
            {
                HBox delivery = new HBox();

                Label namelbl = new Label(ddm.getUserDataModel().getFirstName() + " " + ddm.getUserDataModel().getLastName());
                namelbl.setPrefSize(150, 30);
                namelbl.setStyle("-fx-background-color:white;");
                namelbl.setOnMouseEntered(e->
                {
                    namelbl.setStyle("-fx-background-color:#bfbfbf;");
                });
                namelbl.setOnMouseExited(e->
                {
                    namelbl.setStyle("-fx-background-color:white;");
                });
                
                namelbl.setOnMouseClicked(e->
                {
                    Delivery d = new Delivery(ddm);
                    d.showDeliveryGUI();
                    stage.close();
                });

                Label modulelbl = new Label(ddm.getModuleID().getModuleName());
                modulelbl.setPrefSize(80, 30);
                modulelbl.setStyle("-fx-background-color:white;");
                modulelbl.setAlignment(Pos.CENTER);
                Label quelbl = new Label(Integer.toString(que));
                quelbl.setPrefSize(40, 30);
                quelbl.setStyle("-fx-background-color:white;");
                quelbl.setAlignment(Pos.CENTER);
                String dateString = new java.sql.Date(ddm.getDateDelivered().getTime()).toString();
                Label datelbl = new Label(dateString);
                datelbl.setPrefSize(80, 30);
                datelbl.setStyle("-fx-background-color:white;");
                datelbl.setAlignment(Pos.CENTER);

                delivery.getChildren().addAll(namelbl, modulelbl, quelbl, datelbl);

                content.getChildren().add(delivery);
                
                que++;
            }
        }
        
        gp.add(sp, 0, 0);
        return gp;
    }
    
    public void showDeliveryQue()
    {
        Scene scene = new Scene(getDeliveryQuePane(), 350, 400);
        stage.setScene(scene);
        stage.setTitle("Delivery Que");
        stage.setResizable(false);
        stage.show();
    }
}
