/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.RoomTypeManage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import model.RoomType;

/**
 * FXML Controller class
 *
 * @author Pacharapol
 */
public class DormInvoiceAddController extends DormInvoiceListController implements Initializable {

    @FXML
    private Hyperlink home;
    @FXML
    private ComboBox typeroom;
    @FXML
    private TextField waterBill;
    @FXML
    private TextField electricityBill;
    @FXML
    private TextField roomRates;
    @FXML
    private TextField total;
    @FXML
    private TextField invoiceNo;
    @FXML
    private TextField roomNumber;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private Button comfirm;
    @FXML
    private Button reset;
    
    ArrayList<RoomType> roomTypeArr = new ArrayList<RoomType>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResultSet roomtypeRes = RoomTypeManage.list(dormId);
        try{
            while(roomtypeRes.next()){
                roomTypeArr.add(new RoomType(roomtypeRes.getLong("typeId"),roomtypeRes.getString("typeName"),roomtypeRes.getDouble("price")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        typeroom.getItems().add("เลือกประเภทห้อง");
        typeroom.setValue("เลือกประเภทห้อง");
        for(int i=0;i<roomTypeArr.size();i++){
            typeroom.getItems().add(roomTypeArr.get(i));
        }
        
        
    }    
    
}
