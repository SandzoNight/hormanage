/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.DormManage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DBConnector;
import model.DataQuery;
import view.dialog.DeleteBox;
import view.dialog.ExitConfirmBox;

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
    private Label dormNameLabel;
    @FXML
    private Button toRoomManageBtn;
    @FXML
    private Button toRenterListBtn;
    @FXML
    private Button deleteBtn;
    
    private String[] info;
    @FXML
    private Button editBtn;
    @FXML
    private Button toInvoiceListBtn;
    @FXML
    private Button printContractBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    //เมื่อมีการเรียกไฟล์ DormDashboard.fxml method นี้จะทำงาน
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //เริ่มดึงข้อมูลของหอพักดังกล่าวมาแสดง โดยอิงจาก id ของหอพักในตารางฐานข้อมูล
        info = DormManage.getInfo(dormId);
        //เซ็ต Label ชื่อหอพัก
        dormNameLabel.setText(info[0]);
        //เซ็ต Label ประเภทหอพัก
        dormType.setText(info[1]);
        //เซ็ต Label ที่อยู่ของหอพัก
        dormAddr.setText(info[2]);
        //เซ็ต Label เรทค่าน้ำ
        dormWaterRate.setText(info[6]);
        //เซ็ต Label เรทค่าไฟ
        dormElecRate.setText(info[7]);
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
            root = loader.load(getClass().getResource("/view/dormitory/DormSelectDorm.fxml").openStream());
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

    @FXML
    private void delete(ActionEvent event) {
        boolean answer = DeleteBox.display(this);
        if(answer){
            DeleteBox.answer = false;
            System.out.println("[DormDashboardController]Deleting dormitory");
//            window.close();
            DormManage.remove(dormId+"", userId);
            try{
                FXMLLoader loader = new FXMLLoader();

                //Prepare new page
                root = loader.load(getClass().getResource("/view/dormitory/DormSelectDorm.fxml").openStream());
                Scene scene = new Scene(root);

                //Change to new page
                window.setScene(scene);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void edit(ActionEvent event) {
        System.out.println("Go to Editpage");
        String[] dormInfo = DormManage.getInfo(dormId);
        String[] dormFacilityId = DormManage.getFacility(dormId);
        
        try{
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            System.out.println("[DormDashboardController]Setting parameter for new page..");
            DormEditController.setDormInfo(dormInfo);
            DormEditController.setFacilityId(dormFacilityId);
            System.out.println("[DormDashboardController]Loading new page..");
            root = loader.load(getClass().getResource("/view/dormitory/DormEdit.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            System.out.println("[DormDashboardController]Changing scene..");
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoInvoiceList(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            System.out.println("[DormDashboardController]Loading new page..");
            root = loader.load(getClass().getResource("/view/dormitory/DormInvoiceList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            System.out.println("[DormDashboardController]Changing scene..");
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoViewContract(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/view/dormitory/DormViewContract.fxml").openStream());
            Stage newWindow = new Stage();
            newWindow.setTitle("พิมพ์สัญญา | HOR Manager Application");
            Scene scene = new Scene(root);
            newWindow.setResizable(false);
            newWindow.setScene(scene);
            newWindow.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
