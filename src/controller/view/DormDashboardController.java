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
import model.DataQuery;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormDashboardController extends DormMainController implements Initializable {
    
    protected static long dormId;
    @FXML
    private Hyperlink backBtn;
    @FXML
    private Label dormType;
    @FXML
    private Label dormAddr;
    @FXML
    private Label dormWaterRate;
    @FXML
    private Label dormElecRate;
    @FXML
    private Label dormType1;
    @FXML
    private Label dormAddr1;
    @FXML
    private Label dormWaterRate1;
    @FXML
    private Label dormElecRate1;
    @FXML
    private Label dormName;
    @FXML
    private Button toRoomManageBtn;
    @FXML
    private Button toRenterListBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    //เมื่อมีการเรียกไฟล์ DormDashboard.fxml method นี้จะทำงาน
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //เริ่มดึงข้อมูลของหอพักดังกล่าวมาแสดง โดยอิงจาก id ของหอพักในตารางฐานข้อมูล
        ResultSet dormInfo = DataQuery.query("dormitory", "dormId", dormId+"");
        try{
            while(dormInfo.next()){
                //เซ็ต Label ชื่อหอพัก
                dormName.setText(dormInfo.getString("dormName"));
                //เซ็ต Label ประเภทหอพัก
                dormType.setText(dormInfo.getString("dormType"));
                //เซ็ต Label ที่อยู่ของหอพัก
                dormAddr.setText(dormInfo.getString("dormAddr"));
                //เซ็ต Label เรทค่าน้ำ
                dormWaterRate.setText(dormInfo.getString("dormWaterRate"));
                //เซ็ต Label เรทค่าไฟ
                dormElecRate.setText(dormInfo.getString("dormElecRate"));
            }
            DataQuery.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
                    
        }
    }
    
    //method เพื่อ set dormId ของคลาสนี้ โดยจะต้องเรียก method นี้ก่อนที่จะเรียก
    //หน้า fxml ของคลาสนี้ เพื่อกำหนดค่าให้ attribute ก่อน เพื่อที่จะแสดงผลได้ถูกต้อง
    public static void setDormId(long dormId_input){
        dormId = dormId_input;
    }

    @FXML
    //method เพื่อกลับไปยังหน้าหลัก
    private void gotoHome(ActionEvent event) {
        System.out.println("Home btn clicked");
        try{
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

    @FXML
    //method นี้จะเปลี่ยนหน้าไปยังหน้ารายการห้องพัก ถูกเรียกโดยปุ่ม จัดการห้องพัก
    private void gotoRoomManage(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    //method นี้จะเปลี่ยนหน้าไปยังหน้ารายการผู้เข้าพัก ถูกเรียกโดยปุ่ม จัดการผู้เข้าพัก
    private void gotoRenterList(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRenterList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
