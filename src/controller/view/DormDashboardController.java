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
    private Label testDormId;
    @FXML
    private Label testUserId;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        testDormId.setText(dormId+"");
        testUserId.setText(userId);
        ResultSet dormInfo = DataQuery.query("dormitory", "dormId", dormId+"");
        try{
            while(dormInfo.next()){
                dormName.setText(dormInfo.getString("dormName"));
                dormType.setText(dormInfo.getString("dormType"));
                dormAddr.setText(dormInfo.getString("dormAddr"));
                dormWaterRate.setText(dormInfo.getString("dormWaterRate"));
                dormElecRate.setText(dormInfo.getString("dormElecRate"));
            }
            DataQuery.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
                    
        }
    }
    
    public static void setDormId(long dormId_input){
        dormId = dormId_input;
    }

    @FXML
    private void gotoHome(ActionEvent event) {
        System.out.println("Home btn clicked");
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

    @FXML
    private void gotoRoomManage(ActionEvent event) {
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            ///////////////// ชั่วคราว (ข้ามมาหน้าแสดงรายการห้อง) จริงๆต้องเป็น RoomMain
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
