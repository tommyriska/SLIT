/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.DeliveryDataModel;
import DataModel.UserDataModel;
import Managers.DeliveryManager;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class MyDeliveries 
{
    private Stage stage;
    private UserDataModel activeUser;
    
    public MyDeliveries(UserDataModel activeUser)
    {
        this.activeUser = activeUser;
        this.stage = new Stage();
    }
    
    public GridPane getDeliveriesPane()
    {
        GridPane gp = new GridPane();
        VBox content = new VBox();
        //content.setPadding(new Insets(0, 8, 8, 8));
        content.setSpacing(5);
        gp.add(content, 0, 0);
        
        HBox names = new HBox();
        names.setSpacing(5);
        names.setStyle("-fx-background-color:#999999; -fx-border-color:black;");
        Label modulName = new Label("Module");
        modulName.setPrefWidth(80);
        Label status = new Label("Status");
        status.setPrefWidth(130);
        Label dateDelivered = new Label("Date delivered");
        dateDelivered.setPrefWidth(120);
        Label dateApproved = new Label("Date approved");
        dateApproved.setPrefWidth(120);
        Label comment = new Label("Comment");
        comment.setPrefWidth(100);
        
        names.getChildren().addAll(modulName, status, dateDelivered, dateApproved, comment);
        
        content.getChildren().add(names);
        
        DeliveryManager dm = new DeliveryManager();
        List<DeliveryDataModel> deliveryList = dm.getDeliveriesByName(activeUser);
        
        for(DeliveryDataModel ddm: deliveryList)
        {
            HBox ddmInfo = new HBox();
            ddmInfo.setSpacing(5);
            
            Label mName = new Label(ddm.getModuleID().getModuleName());
            mName.setPrefWidth(80);
            Label s = new Label();
            s.setPrefWidth(130);
            Label dd = new Label(new java.sql.Date(ddm.getDateDelivered().getTime()).toString());
            dd.setPrefWidth(120);
            Label da = new Label();
            da.setPrefWidth(120);
            Label c = new Label();
            c.setPrefWidth(100);
            
            if(ddm.getStatus() == 0)
            {
                s.setText("Not evaluated");
            }
            else if(ddm.getStatus() == 1)
            {
                s.setText("Approved");
                
                da.setText(new java.sql.Date(ddm.getDateApproved().getTime()).toString());
            }
            else
            {
                s.setText("Not approved");
            }
            
            if(!Objects.equals(ddm.getrComment(), null))
            {
                c.setText("Read comment");
                c.setStyle("-fx-underline:true;");
                
                c.setOnMouseClicked(e->
                {
                    this.showCommentStage(ddm.getrComment());
                });
            }

            ddmInfo.getChildren().addAll(mName, s, dd, da, c);
            content.getChildren().add(ddmInfo);
        }
        
        return gp;
    }
    
    public void showMyDeliveries()
    {
        Scene scene = new Scene(this.getDeliveriesPane(), 560, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showCommentStage(String comment)
    {
        StackPane pane = new StackPane();
        TextArea commentArea = new TextArea(comment);
        commentArea.setWrapText(true);
        commentArea.setEditable(false);
        pane.getChildren().add(commentArea);
        
        Scene scene = new Scene(pane, 300, 300);
        
        Stage stage2 = new Stage();
        stage2.setScene(scene);
        stage2.show();
    }
}
