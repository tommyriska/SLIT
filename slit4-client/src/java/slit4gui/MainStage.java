/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.DeliveryDataModel;
import DataModel.ModuleDataModel;
import DataModel.UserDataModel;
import Managers.ModuleManager;
import Managers.UserManager;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class MainStage 
{
    private UserDataModel activeUser;
    private Stage stage;
    private UserManager userManager;
    private ModuleManager moduleManager;
    private ComboBox sortBox;
    
    public MainStage(UserDataModel activeUser)
    {
        this.activeUser = activeUser;
        stage = new Stage();
        userManager = new UserManager();
        moduleManager = new ModuleManager();
        
        this.sortBox = new ComboBox();
        this.sortBox.getItems().addAll("approved modules");
        this.sortBox.setOnAction(e->
        {
            if(sortBox.getValue().equals("approved modules"))
            {
                this.sortPane();
            }
        });
    }
    
    public void showMainStage()
    {
        Scene scene = new Scene(getMainBorderPane(userManager.getUsersList()), 750, 600);
        stage.setScene(scene);
        stage.setTitle("Slit");
        
        stage.show();
    }
    
    public void searchManePane(String searchWord)
    {
        Scene searchScene = new Scene(getMainBorderPane(userManager.getUsersByNameSearch(searchWord)), 750, 600);
        stage.setScene(searchScene);
    }
    
    public void homePane()
    {
        sortBox.setValue("");
        Scene homeScene = new Scene(getMainBorderPane(userManager.getUsersList()), 750, 600);
        stage.setScene(homeScene);
    }
     
    public void sortPane()
    {
        Scene sortScene = new Scene(getMainBorderPane(userManager.getUsersListSortedByDeliveries()), 750, 600);
        stage.setScene(sortScene);
    }
    
    public BorderPane getMainBorderPane(List<UserDataModel> usersList)
    {
        BorderPane bp = new BorderPane();
        ScrollPane sp = new ScrollPane();
        bp.setCenter(sp);
        
        HBox top = new HBox();
        top.setStyle("-fx-background-color:#999999;");
        top.setSpacing(10);
        top.setAlignment(Pos.CENTER_LEFT);
        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e->
        {
            if(searchField != null)
            {
                this.searchManePane(searchField.getText());
            }
        });
        
        Button fileButton = new Button();
        Image image = new Image(getClass().getResourceAsStream("slitLogo2.png"));
        ImageView iv = new ImageView(image);

        fileButton.setGraphic(iv);
        fileButton.setOnAction(e->
        {
            this.homePane();
        });
        fileButton.setStyle("-fx-background-color:transparent;");
        
        Label sortLbl = new Label("Sort by:");
       
        top.getChildren().addAll( fileButton, sortLbl, sortBox, searchField, searchButton);
        bp.setTop(top);
        
        HBox bottomTeacher = new HBox();
        bottomTeacher.setPadding(new Insets(8, 8, 8, 8));
        bottomTeacher.setSpacing(8);
        bottomTeacher.setStyle("-fx-background-color:#999999;");
        bottomTeacher.setAlignment(Pos.CENTER_LEFT);
        Button newModuleButton = new Button("New Module");
        newModuleButton.setOnAction(e->
        {
            NewModule newModule = new NewModule();
            newModule.showNewModuleStage();
        });
        
        Button getDeliveryButton = new Button("Get Delivery");
        getDeliveryButton.setOnAction(e->
        {
            DeliveryQue deliveryQue = new DeliveryQue();
            deliveryQue.showDeliveryQue();
        });
        
        bottomTeacher.getChildren().addAll(newModuleButton, getDeliveryButton);
        
        HBox bottomStudent = new HBox();
        bottomStudent.setPadding(new Insets(8, 8, 8, 8));
        bottomStudent.setSpacing(8);
        bottomStudent.setStyle("-fx-background-color:#999999;");
        bottomStudent.setAlignment(Pos.CENTER_LEFT);
        Button profileButton = new Button();
        
        Image image2 = new Image(getClass().getResourceAsStream("profileIcon2.png"));
        ImageView iv2 = new ImageView(image2);
        iv2.setFitHeight(30);
        iv2.setFitWidth(30);
        iv2.setPreserveRatio(true);
        profileButton.setGraphic(iv2);
        profileButton.setStyle("-fx-background-color:transparent;");
        profileButton.setOnAction(e->
        {
            Profile profile = new Profile(activeUser);
            profile.showProfile();
        });
        bottomStudent.getChildren().addAll(profileButton);
        
        if(activeUser.getRights() > 0)
        {
            bp.setBottom(bottomTeacher);
        }
        else
        {
            bp.setBottom(bottomStudent);
        }
        
        HBox scrollPaneContent = new HBox();
        sp.setContent(scrollPaneContent);
        
        VBox users = new VBox();
        scrollPaneContent.getChildren().add(users);

        List<ModuleDataModel> moduleList = moduleManager.getModulesList();
        
        HashMap<Integer, VBox> moduleBoxes = new HashMap<Integer, VBox>();
        
        
        for(ModuleDataModel mdm: moduleList)
        {
            VBox moduleBox = new VBox();
            HBox name = new HBox();
            
            Label test = new Label( mdm.getModuleName());
            test.setPadding(new Insets(5, 5, 5, 5));
            test.setStyle("-fx-background-color:#cccccc;");
            test.setMinWidth(100);
            test.setAlignment(Pos.CENTER);
            name.getChildren().add(test);
            moduleBox.getChildren().add(name);
            
            name.setOnMouseClicked(e->
            {
                ModuleInfo moduleInfo = new ModuleInfo(mdm, activeUser);
                moduleInfo.show();
            });
            
            name.setOnMouseEntered(e->
            {
                test.setStyle("-fx-background-color:#bfbfbf;");
            });
            
            name.setOnMouseExited(e->
            {
                test.setStyle("-fx-background-color:#cccccc");
            });
            
            moduleBoxes.put(mdm.getModuleId(), moduleBox);
            scrollPaneContent.getChildren().add(moduleBox);
        }
        
        HBox name1 = new HBox();
        Label namelbl1 = new Label("");
        namelbl1.setPadding(new Insets(5, 5, 5, 5));
        namelbl1.setStyle("-fx-background-color:#cccccc;");
        namelbl1.setMinWidth(140);
        name1.getChildren().add(namelbl1);
        users.getChildren().add(name1);
        
        int rowCount = 0;
        
        for(UserDataModel udm: usersList)
        {
            if(udm.getRights() == 0)
            {
                rowCount++;
                HBox name = new HBox();
                Label namelbl = new Label(udm.getFirstName() + " " + udm.getLastName());
                namelbl.setPadding(new Insets(5, 5, 5, 5));
                
                if((rowCount%2)==0)
                {
                    namelbl.setStyle("-fx-background-color:#ffffff;");
                    namelbl.setOnMouseExited(e->
                    {
                        namelbl.setStyle("-fx-background-color:#ffffff;");
                    });
                    
                }
                else
                {
                    namelbl.setStyle("-fx-background-color:#d9d9d9;");
                    namelbl.setOnMouseExited(e->
                    {
                        namelbl.setStyle("-fx-background-color:#d9d9d9;");                   
                    });
                }
                
                namelbl.setOnMouseEntered(e->
                {
                    namelbl.setStyle("-fx-background-color:#cccccc;");
                });
                
                namelbl.setOnMouseClicked(e->
                {
                    SemesterPlan semesterPlan = new SemesterPlan(udm);
                    semesterPlan.showSemesterPlan();
                    
                    Blog blog = new Blog(udm);
                    blog.showBlog();
                });
                
                namelbl.setMinWidth(140);
                name.getChildren().add(namelbl);
                users.getChildren().add(name);

                for(ModuleDataModel mdm: moduleList)
                {
                    HBox dbox = new HBox();
                    Label dlabel = new Label(" ");
                    dlabel.setPadding(new Insets(5, 5, 5, 5));
                    dlabel.setMinWidth(100);
                    dlabel.setAlignment(Pos.CENTER);
                    
                    if((rowCount%2==0))
                    {
                        dlabel.setStyle("-fx-background-color:#ffffff;");
                    }
                    else
                    {
                        dlabel.setStyle("-fx-background-color:#d9d9d9;");
                    }
                    
                    for(DeliveryDataModel ddm: udm.getDeliveryList())
                    {
                        if(Objects.equals(ddm.getModuleID().getModuleId(), mdm.getModuleId()) && Objects.equals(ddm.getStatus(), 1))
                        {
                            String date = new java.sql.Date(ddm.getDateApproved().getTime()).toString();
                            dlabel.setText(date);
                        }
                    }
                    
                    dbox.getChildren().add(dlabel);
                    moduleBoxes.get(mdm.getModuleId()).getChildren().add(dbox);
                }
            }
        }
        
        return bp;
    }
}
