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

//ที่ต้อง extends เพราะว่ามีการใช้ attribute userId ร่วมกับคลาส DormMainController
public class DormSelectDormController extends DormMainController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Button addDormBtn;
    @FXML
    private VBox dormlist;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //สร้างรายการหอพัก (บรรทัดที่ 49 - 142)
        dormlist.getStylesheets().add("/dist/css/mainstyle.css");
        dormlist.getStylesheets().add("/dist/css/selectdormpage.css");
        
        //นับจำนวนหอพักของผู้ใช้ และเตรียม component ต่างๆตามจำนวนที่ได้จากการนับเพื่อที่จะวางลงในหน้า
        int totalDorm = DataCount.count("dormitory", "Userdormowner_userId", userId+"");
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
        ////////////////////////////////////////////////////////////////////////////
        
        //เริ่ม query ข้อมูลหอพักของผู้ใช้
        ResultSet userDorm = DataQuery.query("dormitory", "Userdormowner_userId", userId+"");
        int i = 0;
        try{
            while(userDorm.next()){
                //นับจำนวนห้องพักที่ว่างอยู่
                availableRoom[i] = DataCount.countAvailableRoom(userDorm.getString("dormId"));
                ///////////////////
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
                //เก็บ dormId ลงไปที่ ID ของ component เพื่อใช้ประโยชน์ต่อไป
                viewButton[i].setId(userDorm.getString("dormId"));
                //////////////////////////////////////////////////
                //ตั้ง action ของปุ่ม เมื่อกดให้ไปที่หน้าจัดการหอพักนั้นๆ
                viewButton[i].setOnAction(e ->{
                    gotoDormDashboard(e);
                });
                //////////////////////////////////////////
                
                //ตั้งค่า CSS ให้กับ component ต่างๆ
                numberContainer[i].getStyleClass().add("dorm-number-container");
                dormInfoVBox[i].getStyleClass().add("dorm-info-container");
                dormName[i].getStyleClass().add("dorm-name");
                dormDesc[i].getStyleClass().add("dorm-addr");
                roomInfoHBox[i].getStyleClass().add("dorm-room-container");
                buttonContainer[i].getStyleClass().add("dorm-button-container");
                roomInfoContainer[i].setStyle("-fx-pref-width:145px");
                /////////////////////////////
                
                //เพิ่ม component เข้าไปยัง container
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
                /////////////////////////////////
                
                //เพิ่มรายการหอพักเข้าไปยังแถวแรก
                dormlist.getChildren().add(flowpane[i]);
                //////////////////////////
                
                //เพิ่มค่า i เพื่อทำแถวต่อไป
                i++;
                /////////////////////
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoAddDorm(ActionEvent event) {
        System.out.println("Go to DormRegister");
        try{
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(getClass().getResource("/view/dormitory/DormRegister.fxml").openStream());
            Scene scene = new Scene(root);

            //เปลี่ยนหน้า
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void gotoDormDashboard(ActionEvent event) {
        System.out.println("Go to DormDashboard");
        //รับ dormId จาก event ที่รับมาจาก parameter
        long dormId = Long.parseLong(((Button)event.getSource()).getId());
        System.out.println(dormId);
        try{
            FXMLLoader loader = new FXMLLoader();

            //เซ็ตค่าให้กับ controller class ของหน้าดังกล่าว
            DormDashboardController.setUserId(userId);
            DormDashboardController.setDormId(dormId);
            ////////////////////////////////////////
            root = loader.load(getClass().getResource("/view/dormitory/DormDashboard.fxml").openStream());
            Scene scene = new Scene(root);

            //เปลี่ยนหน้า
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    //method เพื่อกลับไปยังหน้าหลัก
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
