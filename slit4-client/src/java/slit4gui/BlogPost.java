/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.BlogPostDataModel;
import DataModel.UserDataModel;
import Managers.BlogManager;
import java.util.Date;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class BlogPost 
{
    private Stage stage;
    private UserDataModel activeUser;
    
    public BlogPost(UserDataModel activeUser)
    {
        this.stage = new Stage();
        this.activeUser = activeUser;
    }
    
    public GridPane getBlogPostPane()
    {
        BlogManager blogManager = new BlogManager();
        GridPane gp = new GridPane();
        
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextArea contentArea  = new TextArea();
       // contentArea.setPromptText("Content");
        contentArea.setText("Hva har du lært?\n\n\nHva har du gjort?\n\n\nHva var vanskelig?\n\n\n");
        contentArea.setWrapText(true);
        
        Button upload = new Button("Upload");
        upload.setOnAction(e->
        {
            BlogPostDataModel blogPost = new BlogPostDataModel();
            blogPost.setContent(contentArea.getText());
            blogPost.setTitle(titleField.getText());
            Date date = new Date();
            java.sql.Date dateSql = new java.sql.Date(date.getTime());
            blogPost.setDate(dateSql);
            
            blogManager.addBlogPost(blogPost, activeUser);
            
            stage.hide();
        });
        
        gp.add(titleField, 0, 0);
        gp.add(contentArea, 0, 1);
        gp.add(upload, 0, 2);
        
        return gp;
    }
    
    public void showBlogPost()
    {
        Scene scene = new Scene(getBlogPostPane(), 400, 230);
        
        stage.setScene(scene);
        stage.show();
    }
}
