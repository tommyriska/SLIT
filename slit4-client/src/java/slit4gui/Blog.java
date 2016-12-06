/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.BlogDataModel;
import DataModel.BlogPostDataModel;
import DataModel.UserDataModel;
import Managers.BlogManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class Blog 
{
    private UserDataModel user;
    private BlogDataModel blog;
    private Stage stage;
    
    public Blog(UserDataModel user)
    {
        BlogManager blogManager = new BlogManager();
        this.user = user;
        this.blog = blogManager.getBlogById(user);
        this.stage = new Stage();
    }
    
    public GridPane getBlogPane()
    {
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color:white;");
        
        ScrollPane sp = new ScrollPane();
        VBox content = new VBox();
        sp.setContent(content);
        
        for(BlogPostDataModel bpdm: blog.getBlogPostList())
        {
            HBox postBox = new HBox();
            postBox.setStyle("-fx-background-color:white;");
            
            postBox.setPadding(new Insets(5, 5, 5 ,5));
            Label title = new Label("(" + bpdm.getDate() + ") " + bpdm.getTitle());
            Label contents = new Label(bpdm.getContent());
            contents.setWrapText(true);
            
            postBox.getChildren().addAll(title, contents);
            
            content.getChildren().add(postBox);
        }
        
        TextArea blogText = new TextArea();
        blogText.setPrefSize(400, 400);
        blogText.setEditable(false);
        blogText.setWrapText(true);
        
        for(BlogPostDataModel bpdm: blog.getBlogPostList())
        {
            String date = new java.sql.Date(bpdm.getDate().getTime()).toString();
            blogText.appendText(date + ": " + bpdm.getTitle()  + "\n\n");
            blogText.appendText(bpdm.getContent() + "\n");
            blogText.appendText("\n_________________________________________\n\n");
        }
        
        gp.add(blogText, 0, 0);
        
        return gp;
    }
    
    public void showBlog()
    {
        Scene scene = new Scene(getBlogPane(), 400, 400);
        
        stage.setX(710);
        stage.setY(200);
        
        stage.setTitle("Blog - " + user.getFirstName() + " " + user.getLastName());
        stage.setScene(scene);
        stage.show();
    }
}
