/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.DataCount;
import model.DataQuery;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormSelectDormController extends DormMainController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Button addDormBtn;
    @FXML
    private Label testUserId;
    @FXML
    private VBox dormlist;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        testUserId.setText(userId);
        dormlist.getStylesheets().add("/dist/css/mainstyle.css");
        dormlist.getStylesheets().add("/dist/css/selectdormpage.css");
        
        int totalDorm = DataCount.count("dormitory", "Userdormowner_userId", userId);
        DataCount.disconnect();
        FlowPane[] flowpane = new FlowPane[totalDorm];
        BorderPane[] borderpane = new BorderPane[totalDorm];
        FlowPane[] numberContainer = new FlowPane[totalDorm];
        Label[] number = new Label[totalDorm];
        FlowPane[] infoContainer = new FlowPane[totalDorm];
        BorderPane[] infoBorderPane = new BorderPane[totalDorm];
        VBox[] dormInfoVBox = new VBox[totalDorm];
        Label[] dormName = new Label[totalDorm];
        Label[] dormDesc = new Label[totalDorm];
        FlowPane[] roomInfoContainer = new FlowPane[totalDorm];
        HBox[] roomInfoHBox = new HBox[totalDorm];
        Label[] noOfRoom = new Label[totalDorm];
        Label[] slash = new Label[totalDorm];
        Label[] totalRoom = new Label[totalDorm];
        FlowPane[] buttonContainer = new FlowPane[totalDorm];
        Button[] viewButton = new Button[totalDorm];
        int[] availableRoom = new int[totalDorm];
        
        ResultSet userDorm = DataQuery.query("dormitory", "Userdormowner_userId", userId);
        int i = 0;
        try{
            while(userDorm.next()){
                availableRoom[i] = DataCount.countAvailableRoom(userId);
                flowpane[i] = new FlowPane();
                borderpane[i] = new BorderPane();
                numberContainer[i] = new FlowPane();
                number[i] = new Label(i+1+".");
                infoContainer[i] = new FlowPane();
                infoBorderPane[i] = new BorderPane();
                dormInfoVBox[i] = new VBox();
                dormName[i] = new Label(userDorm.getString("dormName"));
                dormDesc[i] = new Label(userDorm.getString("dormAddr"));
                roomInfoContainer[i] = new FlowPane();
                roomInfoHBox[i] = new HBox();
                noOfRoom[i] = new Label(availableRoom[i]+"");
                slash[i] = new Label(" / ");
                totalRoom[i] = new Label(userDorm.getString("dormCountRoom"));
                buttonContainer[i] = new FlowPane();
                viewButton[i] = new Button(">>");
                viewButton[i].setId(userDorm.getString("dormId"));
                viewButton[i].setOnAction(e ->{
                    gotoDormDashboard(e);
                });
                
                //Setting Containers and Components
                numberContainer[i].getStyleClass().add("dorm-number-container");
                dormInfoVBox[i].getStyleClass().add("dorm-info-container");
                dormName[i].getStyleClass().add("dorm-name");
                dormDesc[i].getStyleClass().add("dorm-addr");
                roomInfoHBox[i].getStyleClass().add("dorm-room-container");
                buttonContainer[i].getStyleClass().add("dorm-button-container");
                roomInfoContainer[i].setStyle("-fx-pref-width:145px");
                
                //Adding components to containers
                numberContainer[i].getChildren().add(number[i]);
                borderpane[i].setLeft(numberContainer[i]);
                
                dormInfoVBox[i].getChildren().addAll(dormName[i],dormDesc[i]);
                infoBorderPane[i].setCenter(dormInfoVBox[i]);
                roomInfoHBox[i].getChildren().addAll(noOfRoom[i],slash[i],totalRoom[i]);
                roomInfoContainer[i].getChildren().add(roomInfoHBox[i]);
                infoBorderPane[i].setRight(roomInfoContainer[i]);
                infoContainer[i].getChildren().add(infoBorderPane[i]);
                borderpane[i].setCenter(infoContainer[i]);
                buttonContainer[i].getChildren().add(viewButton[i]);
                borderpane[i].setRight(buttonContainer[i]);
                flowpane[i].getChildren().add(borderpane[i]);
                
                //add to the row
                dormlist.getChildren().add(flowpane[i]);
                
                //increase i value to set the next row
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }    

    @FXML
    private void gotoAddDorm(ActionEvent event) {
        System.out.println("Go to DormRegister");
        
        
    }
    
    @FXML
    private void gotoDormDashboard(ActionEvent event) {
        System.out.println("Go to DormDashboard");
        long dormId = Long.parseLong(((Button)event.getSource()).getId());
        System.out.println(dormId);
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            DormDashboardController.setUserId(userId);
            DormDashboardController.setDormId(dormId);
            root = loader.load(getClass().getResource("/view/dormitory/DormDashboard.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void gotoHome(ActionEvent event) {
        System.out.println("Go back to Home from DormSelectDorm");
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormMain.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
