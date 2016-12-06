/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.UserDataModel;
import Managers.UserManager;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class Login
{
    private UserManager userManager;
    private UserDataModel activeUser;
    private Stage stage;
    
    public Login()
    {
        userManager = new UserManager();
        activeUser = new UserDataModel();
        stage = new Stage();
    }
    
    public void showLoginScreen()
    {
        Scene scene = new Scene(getUserListPane(), 300, 250);
        
        stage.setTitle("Slit - login");
        stage.setScene(scene);
        stage.show();
    }
    
    public void hideLoginScreen()
    {
        stage.hide();
    }
    
    private BorderPane getUserListPane()
    {
        BorderPane bp = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox();
        HBox bottom = new HBox();
        Label bottomLbl = new Label("Active user: ");
        
        bp.setCenter(scrollPane);
        scrollPane.setContent(vbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        bottom.setAlignment(Pos.CENTER_LEFT);
        bottom.setSpacing(10);
        bottom.getChildren().add(bottomLbl);
        bp.setBottom(bottom);
        
        List<UserDataModel> userList = userManager.getUsersList();
        
        for(UserDataModel udm: userList)
        {
            HBox hbox = new HBox();
            Label label = new Label();
            label.setPadding(new Insets(12, 12, 12, 12));
            
            String fullName = udm.getFirstName() + " " + udm.getLastName();
            
            label.setText("[" + udm.getRights() + "] " + fullName);
            
            hbox.getChildren().add(label);
            hbox.setOnMouseClicked(e->
            {
                bottomLbl.setText("Active user: " + fullName);
                activeUser = udm;
            });
            
            vbox.getChildren().add(hbox);
        }
        
        Button btn = new Button("Sign in");
        btn.setOnAction(e->
        {
            MainStage mainStage = new MainStage(activeUser);
            mainStage.showMainStage();
            this.hideLoginScreen();
        });
        
        bottom.getChildren().add(btn);
        
        return bp;
    }
}
