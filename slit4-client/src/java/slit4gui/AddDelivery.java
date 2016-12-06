
package slit4gui;

import DataModel.DeliveryDataModel;
import DataModel.FileDataModel;
import DataModel.ModuleDataModel;
import DataModel.UserDataModel;
import Managers.DeliveryManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author torar_000
 */
public class AddDelivery {
    
    private DeliveryManager deliveryManager;
    private Stage stage;
    private ModuleDataModel mdm;
    private UserDataModel udm;
    
    
    public AddDelivery(ModuleDataModel mdm, UserDataModel udm)
    {
        deliveryManager = new DeliveryManager();
        stage = new Stage();
        this.mdm = mdm;
        this.udm = udm;
    }
    
    public void showAddDeliveryScreen()
    {
        
        Scene scene = new Scene(getAddDeliveryPage(), 300, 250);
        stage.setTitle("Slit - add delivery");
        stage.setScene(scene);
        stage.show();
    }
    
    public void hideAddDeliveryScreen(){
        stage.hide();
    }
    
    private BorderPane getAddDeliveryPage(){ // sette activeUser som parameter
        
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(8, 8, 8, 8));
        bp.setPrefSize(450, 500);
        VBox vbCenter = new VBox();
        VBox vbTop = new VBox();
        VBox vbBottom = new VBox();
        Button submitButton = new Button("Submit");
        Button fileButton = new Button("Choose file");
        TextArea textArea = new TextArea();
        textArea.setPromptText("Add comment ...");
        TextField fileInfo = new TextField();
        fileInfo.setPromptText("Chosen file(s)");
        fileInfo.setEditable(false);
       
        
        bp.setTop(vbTop); // file chooser
        vbTop.setAlignment(Pos.TOP_LEFT);
        vbTop.setSpacing(10);
     
        bp.setCenter(vbCenter);
        vbCenter.setAlignment(Pos.CENTER_LEFT);
        vbCenter.setSpacing(10);
        vbCenter.setMaxHeight(100);
        vbCenter.getChildren().add(textArea);
        
        bp.setBottom(vbBottom);
        vbBottom.setAlignment(Pos.BOTTOM_LEFT);
        vbBottom.setSpacing(10);
        
        List<FileDataModel> fileList = new ArrayList<>();
        DeliveryDataModel ddm = new DeliveryDataModel();
        
        /* 
        bruker fileChooser til aa aapne filer, konverterer og legger til i fileList
        */
        fileButton.setOnAction(e->
        {
            FileChooser fileChooser = new FileChooser();
            List<File> list = fileChooser.showOpenMultipleDialog(stage);
         
            String infoString = "";
            
            if(list != null)
            {
                for(File file : list)
                {
                    FileDataModel dfdm = new FileDataModel();
                    byte [] files;
                    dfdm.setFilename(file.getName());
                    
                    int id = file.getName().indexOf('.');
                    dfdm.setFiletype(file.getName().substring(id));
                    
                    try
                    {
                        Path path = Paths.get(file.getPath()); 
                        files = Files.readAllBytes(path);
                        dfdm.setContent(files);
                    } 
                    catch (IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                    
                    fileList.add(dfdm);
                    
                    infoString += dfdm.getFilename() +  ", ";
                }
            }
            
            fileInfo.setText(infoString);
        });
        
        vbTop.getChildren().addAll(fileButton, fileInfo);

        
        submitButton.setOnAction(e->
        {
            Date date = new java.util.Date();
            java.sql.Date dateSql = new java.sql.Date(date.getTime());
            System.out.println(dateSql);
            ddm.setFileList(fileList);
            ddm.setDateDelivered(dateSql);
            ddm.setModuleID(mdm);
            ddm.setdComment(textArea.getText()); 
            deliveryManager.addDelivery(ddm, udm);
            stage.hide();
        });
        
        vbBottom.getChildren().add(submitButton);
        
        return bp;
    }
    
    /*public void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                Main.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    
    }*/    
    
}
