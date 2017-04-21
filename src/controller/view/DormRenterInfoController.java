/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.RenterManage;
import controller.RoomManage;
import controller.RoomTypeManage;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormRenterInfoController extends DormRenterListController implements Initializable {

    @FXML
    private Label renterName;
    @FXML
    private Label renterGender;
    @FXML
    private Label renterAddr;
    @FXML
    private Label renterTel;
    @FXML
    private Label renterEmail;
    @FXML
    private Label renterRoomNo;
    @FXML
    private Label renterRoomType;

    protected static long renterId;
    protected static Stage stage;
    
    private String[] renterData;
    @FXML
    private Button editBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        renterData = new String[6];
        ResultSet res = RenterManage.getRenterInfo(renterId);
        try{
            while(res.next()){
                renterName.setText(res.getString("renterFirstName")+" "+res.getString("renterLastName"));
                renterData[0] = res.getString("renterFirstName");
                renterData[1] = res.getString("renterLastName");
                if(res.getString("renterGender").equalsIgnoreCase("m")){
                    renterGender.setText("ชาย");
                    renterData[2] = "ชาย";
                }else{
                    renterGender.setText("หญิง");
                    renterData[2] = "หญิง";
                }
                renterAddr.setText(res.getString("renterAddr"));
                renterData[3] = res.getString("renterAddr");
                renterTel.setText(res.getString("renterTel"));
                renterData[4] = res.getString("renterTel");
                renterEmail.setText(res.getString("renterEmail"));
                renterData[5] = res.getString("renterEmail");
                ResultSet roomInfo = RoomManage.getDetail(res.getString("Room_roomId"));
                while(roomInfo.next()){
                    renterRoomNo.setText(roomInfo.getString("roomNo"));
                    ResultSet roomTypeInfo = RoomTypeManage.getDetail(roomInfo.getString("RoomType_typeId"));
                    while(roomTypeInfo.next()){
                        renterRoomType.setText(roomTypeInfo.getString("typeName"));
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }    
    
    public static void setRenterId(long renterIdInput){
        renterId = renterIdInput;
    }
    
    //แก้ปัญหา เวลากดแก้ไข ให้ปิดหน้า RenterInfo ด้วย
//    public static void setOldStage(Stage s){
//        stage = s;
//    }

    @FXML
    private void editRenter(ActionEvent event) {
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();
            DormRenterEditController.setRenterData(renterData);

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRenterEdit.fxml").openStream());
            Scene scene = new Scene(root);
            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
