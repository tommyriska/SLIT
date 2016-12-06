package slit4gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DataModel.ModuleDataModel;
import java.util.HashMap;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Adne
 */
public class Main extends Application 
{
    @Override
    public void start(Stage primaryStage) 
    {
        Login login = new Login();
        login.showLoginScreen();
        
        Charts c = new Charts();
        c.showChart();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
