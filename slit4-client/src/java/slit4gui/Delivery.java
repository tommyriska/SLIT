package slit4gui;

import DataModel.BlogDataModel;
import DataModel.DeliveryDataModel;
import DataModel.FileDataModel;
import DataModel.RequirementDataModel;
import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import Managers.DeliveryManager;
import Managers.ModuleManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author tommy
 */
public class Delivery {
    
    private Stage stage;
    private DeliveryManager dm;
    private ModuleManager mm;
    private DeliveryDataModel ddm;
    private SemesterPlanDataModel spdm;
    private SemesterPlan semp;
    private UserDataModel user;
    private BlogDataModel blogdm;
    private Blog blog;
    private Charts charts;
    
    
    
    /**
     *
     * @param delivery Takes in a deliveryDataModel object.
     * 
     */
    public Delivery(DeliveryDataModel delivery)
    {
        stage = new Stage();
        dm = new DeliveryManager();
        mm = new ModuleManager();
        ddm = delivery;
        user = delivery.getUserDataModel();
        spdm = user.getSemesterPlanDataModel();
        semp = new SemesterPlan(user);
        blogdm = user.getBlogDataModel();
        blog = new Blog(user);
        charts = new Charts();
    }
    
    /**
     * @return BorderPane Returns the BorderPane created inside the method.
     * This method will be used while creating a new scene in showDeliveryGUI method.
     */
    

    private BorderPane getDeliveryReviewGUI()
    {
        BorderPane bp = new BorderPane();
        
        //BORDERPANE LEFT
        ScrollPane sp = new ScrollPane();
        VBox lvbox = new VBox();
        lvbox.setPrefWidth(150);
        lvbox.setPadding(new Insets(8, 8, 8, 8));
        
        for(RequirementDataModel rdm: ddm.getModuleID().getRequirementList())
        {
            HBox rdmhbox = new HBox();
            Label lbl = new Label(rdm.getDescription());
            lbl.setWrapText(true);
            CheckBox cbx = new CheckBox();
            cbx.setSelected(false);
            rdmhbox.getChildren().addAll(cbx, lbl);
            rdmhbox.setPadding(new Insets(8,8,8,8));
            rdmhbox.setMaxWidth(150);
            
            lvbox.getChildren().addAll(rdmhbox);
        }
        
        sp.setContent(lvbox);
        
        //BORDERPANE CENTER
            //Contains .
            VBox cvbox = new VBox();
            cvbox.setPadding(new Insets(8,8,8,8));
            
            //TextArea infotext displays the module info.
            //This textarea is not editable.
            HBox mi = new HBox();
            mi.setPadding(new Insets(8,8,8,8));
            Label moduleinfo = new Label("Module information:");
            mi.getChildren().add(moduleinfo);
            mi.setAlignment(Pos.CENTER);
            TextArea moduleinfotxt = new TextArea(ddm.getModuleID().getDescription());
            moduleinfotxt.setEditable(false);
            moduleinfotxt.setWrapText(true);
            
            //TextArea deliverycomment displays the student`s comment on the delivery.
            HBox dc = new HBox();
            dc.setPadding(new Insets(8,8,8,8));
            Label dcomment = new Label("Student comment:");
            dc.getChildren().add(dcomment);
            dc.setAlignment(Pos.CENTER);
            TextArea deliverycomment = new TextArea(ddm.getdComment());
            deliverycomment.setEditable(false);
            deliverycomment.setWrapText(true);
            
            //TextArea commenttxt is for the lecturer to comment a delivery.
            //This textarea is editable.
            HBox lc = new HBox();
            lc.setPadding(new Insets(8,8,8,8));
            Label lcomment = new Label("Lecturer comment:");
            lc.getChildren().add(lcomment);
            lc.setAlignment(Pos.CENTER);
            TextArea commenttxt = new TextArea();
            commenttxt.isEditable();
            commenttxt.setWrapText(true);
            
            cvbox.getChildren().addAll(mi, moduleinfotxt, dc, deliverycomment, lc, commenttxt);
            
        //BORDERPANE RIGHT
            //rvbox contains buttons: Semester plan, Blog, Confirmation/ok.
            VBox rvbox = new VBox();
            //Semesterplan button shows student`s semester plan.
            Button splanbtn = new Button("Semester\nPlan");
                splanbtn.setTextAlignment(TextAlignment.CENTER);
                splanbtn.setPrefSize(85, 45);
                splanbtn.setOnAction(e -> 
                {
                    semp.showSemesterPlan();
                });
            //Blog button
            Button blogbtn = new Button("Blog");
                blogbtn.setPrefSize(85, 45);
                blogbtn.setOnAction(e ->
                {
                    blog.showBlog();
                });
            
            //Files
            Button filesbtn = new Button("Download\nfile(s)");
            filesbtn.setTextAlignment(TextAlignment.CENTER);
            filesbtn.setPrefSize(85, 45);
            filesbtn.setOnAction(e->
            {
                
                for(FileDataModel df : ddm.getFileList())
                {
                    FileSaver f = new FileSaver();
                    f.saveFile(df);
                }
                
                Stage confstage = new Stage();
                BorderPane confbp = new BorderPane();
                
                if(ddm.getFileList() != null)
                {
                    VBox confvbox = new VBox();
                    Label conflbl = new Label("Download finished");
                    Button okbutton = new Button("OK");
                    okbutton.setOnAction(ex->
                    {
                        confstage.close();
                    });
                    confvbox.getChildren().addAll(conflbl, okbutton);
                    
                    conflbl.setPadding(new Insets(8,8,8,8));
                    okbutton.setPadding(new Insets(8,8,8,8));
                    conflbl.setAlignment(Pos.CENTER);
                    okbutton.setAlignment(Pos.CENTER);
                    confvbox.setAlignment(Pos.CENTER);
                    
                    confbp.setCenter(confvbox);
                }
                else
                {
                    
                }
                
                Scene confscene = new Scene(confbp, 250, 250);
                confstage.initModality(Modality.APPLICATION_MODAL);
                confstage.setScene(confscene);
                confstage.setResizable(false);
                confstage.setTitle("File(s) downloaded!");
                confstage.show();
                
            }
            );
                
            //Confirmation button
            Button donebtn = new Button("Evaluate");
                donebtn.setPrefSize(85, 45);
                donebtn.setOnAction(e -> 
                {
                    Stage stageApprove = new Stage();
                    BorderPane bp1 = new BorderPane();
                    Button godkjent = new Button("Approved");
                    godkjent.setOnAction(eg -> 
                    {
                        dm.changeStatus(ddm, 1);
                        dm.addReviewComment(ddm, commenttxt.getText());
                        stageApprove.close();
                        stage.close();
                    });
                    Button ikkegodkjent = new Button("Not approved");
                    ikkegodkjent.setOnAction(ei -> 
                    {
                        dm.changeStatus(ddm, 2);
                        dm.addReviewComment(ddm, commenttxt.getText());
                        stageApprove.close();
                        stage.close();
                    });
                    
                    VBox vboxbtn = new VBox();
                    vboxbtn.getChildren().addAll(godkjent, ikkegodkjent);
                    
                    godkjent.setPrefSize(110, 25);
                    ikkegodkjent.setPrefSize(110, 25);
                    
                    vboxbtn.setAlignment(Pos.CENTER);
                    vboxbtn.setSpacing(8);
                    bp1.setCenter(vboxbtn);
                    
                    Scene scene = new Scene(bp1, 150, 150);
                    stageApprove.initModality(Modality.APPLICATION_MODAL);
                    stageApprove.setScene(scene);
                    stageApprove.setTitle("Evaluate");
                    stageApprove.show();
                });
            
            rvbox.getChildren().addAll(splanbtn, blogbtn, filesbtn, donebtn);
            rvbox.setAlignment(Pos.BOTTOM_CENTER);
            rvbox.setPadding(new Insets(8, 8, 8, 8));
            rvbox.setSpacing(8);
            
            
            bp.setLeft(sp);
            bp.setCenter(cvbox);
            bp.setRight(rvbox);
            
            
        return bp;
    }

    /**
     * A method to change the scene.
     * Scene takes in a method which returns any JavaFX pane, the width and
     * the height of the specific scene.
     */
    public void showDeliveryGUI()
    {
        Scene scene = new Scene(getDeliveryReviewGUI(), 750, 600);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("SLIT - Evaluation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    
}