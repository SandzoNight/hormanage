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
import javafx.event.ActionEvent;
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
<<<<<<< HEAD:src/controller/view/DormInvoiceAddController.java
public class DormInvoiceAddController extends DormInvoiceListController implements Initializable {
=======
public class CreateInvoiceController implements Initializable {

    @FXML
    private TextField electricityBill;
>>>>>>> 87d856080ce480a259742b55533adfb2024f310d:src/controller/view/CreateInvoiceController.java

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
<<<<<<< HEAD:src/controller/view/DormInvoiceAddController.java
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
    
=======
    private Hyperlink home;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    

    private void confirm(ActionEvent event) {
        String[] CreateInvoice = new String[10];
        CreateInvoice[0] = invoiceNo.getText();
        CreateInvoice[1] = roomNumber.getText();
        CreateInvoice[2] = invoiceNo.getText();
        CreateInvoice[3] = roomNumber.getText();
        CreateInvoice[4] = name.getText();
        CreateInvoice[5] = surname.getText();
        if(typeroom.getValue().toString() == null){
            CreateInvoice[6] = "";
        }else{
            CreateInvoice[6] = typeroom.getValue().toString();
        }
        CreateInvoice[7] = waterBill.getText();
        CreateInvoice[8] = electricityBill.getText();
        CreateInvoice[9] = roomRates.getText();
        CreateInvoice[10] = total.getText();
        
        
    }
>>>>>>> 87d856080ce480a259742b55533adfb2024f310d:src/controller/view/CreateInvoiceController.java
}
