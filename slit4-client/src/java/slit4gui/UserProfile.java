/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.BlogPostDataModel;
import DataModel.UserDataModel;
import Managers.BlogManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class UserProfile 
{
    private Stage stage;
    private UserDataModel user;
    
    public UserProfile(UserDataModel user)
    {
        this.stage = new Stage();
        this.user = user;
    }
    
    public GridPane getUserProfilePane()
    {
        BlogManager bm = new BlogManager();
        
        SemesterPlan semesterPlan = new SemesterPlan(user);
        GridPane semesterPlanPane = semesterPlan.getSemesterPlanPane();
        
        Blog blog = new Blog(user);
        GridPane blogPane = blog.getBlogPane();
        
        GridPane gp = new GridPane();
        
        VBox blogPostBox = new VBox();
        TextField blogTitleField = new TextField();
        blogTitleField.setPromptText("Title");
        TextArea blogContentArea = new TextArea();
        blogContentArea.setPromptText("Content");
        Button uploadBlog = new Button("Upload");
        uploadBlog.setOnAction(e->
        {
            BlogPostDataModel bpdm = new BlogPostDataModel();
        });
        
        blogPostBox.getChildren().addAll(blogPane, blogTitleField, blogContentArea, uploadBlog);
        
        gp.add(blogPostBox, 0, 0);
        
        return gp;
    }
    
    public void showProfile()
    {
        Scene scene = new Scene(getUserProfilePane(), 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}
