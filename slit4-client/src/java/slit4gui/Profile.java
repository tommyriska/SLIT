/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import Managers.SemesterPlanManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class Profile 
{
    private UserDataModel user;
    private Stage stage;
    
    public Profile(UserDataModel user)
    {
        this.user = user;
        this.stage = new Stage();
    }
    
    public GridPane getProfileGridPane()
    {        
        GridPane gp = new GridPane();
        
        Button openSemesterPlan = new Button("My Semester Plan");
        openSemesterPlan.setOnAction(e->
        {
            SemesterPlan semesterPlanGui = new SemesterPlan(user);
            semesterPlanGui.showSemesterPlan();
        });
        
        Button openBlog = new Button("My Blog");
        openBlog.setOnAction(e->
        {
            Blog blogGui = new Blog(user);
            blogGui.showBlog();
        });
        
        Button newBlogPost = new Button("New Blog Post");
        newBlogPost.setOnAction(e->
        {
            BlogPost blogPost = new BlogPost(user);
            blogPost.showBlogPost();
        });
        
        Button createSemesterPlan = new Button("Create Semester Plan");
        createSemesterPlan.setOnAction(e->
        {
            SemesterPlanManager spm= new SemesterPlanManager();
            SemesterPlanDataModel spdm = spm.getSemesterPlanById(user);
            
            if(spdm.getModulePlanList().size() == 0)
            {
                CreateSemesterPlan csp = new CreateSemesterPlan(user);
                csp.showCreatePane();
            }
            else
            {
                Stage msgStage = new Stage();
                StackPane spane = new StackPane();
                Label msgLbl = new Label("You have already created a semester plan.");
                spane.getChildren().add(msgLbl);
                Scene msgScene = new Scene(spane, 350, 100);
                msgStage.setScene(msgScene);
                msgStage.show();
            }
        });
        
        Button myDeliveries = new Button("My deliveries");
        myDeliveries.setOnAction(e->
        {
            MyDeliveries md = new MyDeliveries(user);
            md.showMyDeliveries();
        });
        
        VBox infoBox = new VBox();
        infoBox.setSpacing(6);
        infoBox.setPadding(new Insets(8, 8, 8, 8));
        infoBox.setStyle("-fx-background-color:#999999;");
        Label name = new Label(user.getFirstName() + " " + user.getLastName());
        Label email = new Label(user.getEmail());
        Label number = new Label(user.getPhoneNumber().toString());
        infoBox.getChildren().addAll(name, email, number);
        
        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-background-color:#999999;;");
        buttonBox.setSpacing(6);
        buttonBox.setPadding(new Insets(8, 8, 8, 8));
        buttonBox.getChildren().addAll(myDeliveries, openSemesterPlan, createSemesterPlan, openBlog, newBlogPost);
        
        gp.add(infoBox, 0, 0);
        gp.add(buttonBox, 0, 1);
        
        return gp;
    }
    
    public void showProfile()
    {
        Scene scene = new Scene(getProfileGridPane(), 595, 120);
        stage.setScene(scene);
        stage.setTitle(user.getFirstName() + " " + user.getLastName());
        stage.show();
    }
}
