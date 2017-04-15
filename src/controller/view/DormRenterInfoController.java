/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.RenterManage;
import controller.RoomManage;
import controller.RoomTypeManage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormRenterInfoController implements Initializable {

    @FXML
    private Hyperlink backBtn;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResultSet res = RenterManage.getRenterInfo(renterId);
        try{
            while(res.next()){
                renterName.setText(res.getString("renterFirstName")+" "+res.getString("renterLastName"));
                if(res.getString("renterGender").equalsIgnoreCase("m")){
                    renterGender.setText("ชาย");
                }else{
                    renterGender.setText("หญิง");
                }
                renterAddr.setText(res.getString("renterAddr"));
                renterTel.setText(res.getString("renterTel"));
                renterEmail.setText(res.getString("renterEmail"));
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

    @FXML
    private void gotoHome(ActionEvent event) {
    }
    
    public static void setRenterId(long renterIdInput){
        renterId = renterIdInput;
    }
}
