/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.DormManage;
import controller.FieldFormat;
import controller.InvoiceManage;
import controller.RenterManage;
import controller.RoomManage;
import controller.RoomTypeManage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import model.Dormitory;
import model.Room;
import model.RoomType;

/**
 * FXML Controller class
 *
 * @author Pacharapol
 */
public class DormInvoiceAdd extends DormInvoiceListController implements Initializable {


    @FXML
    private Hyperlink home;
    @FXML
    private ComboBox<RoomType> typeroom;
    private TextField waterBill;
    private TextField electricityBill;
    @FXML
    private TextField roomRates;
    @FXML
    private TextField total;
    @FXML
    private TextField invoiceNo;
    @FXML
    private ComboBox roomNumber;
    @FXML
    private TextField name;
    @FXML
    private DatePicker dueDate;
    
    private ArrayList<RoomType> roomTypeArr = new ArrayList<RoomType>();
    private Dormitory dorm;
    @FXML
    private TextField waterUnit;
    @FXML
    private TextField electricityUnit;

    private double[] rate = new double[2];
    private long invoiceNumber;
    private ArrayList<Room> roomArr = new ArrayList<Room>();
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet roomRes = RoomManage.listAll(dormId);
        try{
            while(roomRes.next()){
                roomArr.add(new Room(roomRes.getLong("roomId"),roomRes.getString("roomNo"),roomRes.getLong("Renter_renterId"),roomRes.getInt("roomStatus"),roomRes.getLong("RoomType_typeId")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        for(int i=0;i<roomArr.size();i++){
            roomNumber.getItems().add(roomArr.get(i));
        }
        
        String[] dormData = DormManage.getInfo(dormId);
        dorm = new Dormitory(Long.parseLong(dormData[9]),dormData[0],Double.parseDouble(dormData[6]),Double.parseDouble(dormData[7]));
        rate[0] = dorm.getWaterRate();
        rate[1] = dorm.getElecRate();
        waterUnit.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue){
                    System.out.println("Textfield on focus");
                    unitTyped();
                }else{
                    System.out.println("Textfield out focus");
                    unitTyped();
                }
            }
        });
        electricityUnit.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue){
                    System.out.println("Textfield on focus");
                    unitTyped();
                }
                else{
                    System.out.println("Textfield out focus");
                    unitTyped();
                }
            }
        });
        waterUnit.setText("0");
        electricityUnit.setText("0");
        
        waterUnit.setTextFormatter(new TextFormatter(FieldFormat.doubleFormat()));
        electricityUnit.setTextFormatter(new TextFormatter(FieldFormat.doubleFormat()));
        ResultSet roomtypeRes = RoomTypeManage.list(dormId);
        try{
            while(roomtypeRes.next()){
                roomTypeArr.add(new RoomType(roomtypeRes.getLong("typeId"),roomtypeRes.getString("typeName"),roomtypeRes.getDouble("price")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
//        typeroom.getItems().add("เลือกประเภทห้อง");
        typeroom.setPromptText("เลือกประเภทห้อง");
        for(int i=0;i<roomTypeArr.size();i++){
            typeroom.getItems().add(roomTypeArr.get(i));
        }
        
        ResultSet invoiceRes = InvoiceManage.getAllInvoice(dormId);
        try{
            while(invoiceRes.next()){
                invoiceNumber = invoiceRes.getInt("invoiceNo")+1;
                invoiceNo.setText("INV-"+invoiceNumber);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    

    @FXML
    private void confirm(ActionEvent event) {
        LocalDate dueDateDate = dueDate.getValue();
        Date input = new Date();
        LocalDate startDate = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ArrayList<String> data = new ArrayList<String>();
        data.add(invoiceNumber+""); //0
        data.add(((Room)roomNumber.getValue()).getRenter_renterId()+""); //1
        data.add(""); //2
        data.add(typeroom.getValue().getTypeName()); //3
        data.add(waterUnit.getText()); //4
        data.add(electricityUnit.getText()); //5
        data.add(rate[0]*Double.parseDouble(waterUnit.getText())+""); //6
        data.add(rate[1]*Double.parseDouble(electricityUnit.getText())+""); //7
        data.add(typeroom.getValue().getPrice()+""); //8
        data.add(((Room)roomNumber.getValue()).getRoomId()+""); //9
        
        InvoiceManage.addInvoice(startDate, dueDateDate, data, dormId);
        
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormInvoiceList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void roomtypeSelected(ActionEvent event) {
        double[] unit = {Double.parseDouble(waterUnit.getText()),Double.parseDouble(electricityUnit.getText())};
        if(!typeroom.getValue().equals("เลือกประเภทห้อง")){
            System.out.println("in if");
            for(int i=0;i<roomTypeArr.size();i++){
                if(typeroom.getValue().getTypeName().equals(roomTypeArr.get(i).getTypeName())){
                    System.out.println("in if 2");
                    total.setText(InvoiceManage.priceCalculator(rate,unit,roomTypeArr.get(i).getPrice())+"");
                    roomRates.setText(roomTypeArr.get(i).getPrice()+"");
                }
            }
        }
    }

    private void unitTyped() {
        double[] unit = {Double.parseDouble(waterUnit.getText()),Double.parseDouble(electricityUnit.getText())};
        if(!typeroom.getValue().equals("เลือกประเภทห้อง") && !waterUnit.getText().equals("") && !electricityUnit.getText().equals("")){
            for(int i=0;i<roomTypeArr.size();i++){
                if(typeroom.getValue().getTypeName().equals(roomTypeArr.get(i).getTypeName())){
                    total.setText(InvoiceManage.priceCalculator(rate,unit,roomTypeArr.get(i).getPrice())+"");
                }
            }
        }
    }

    @FXML
    private void roomSelected(Event event) {
        if(roomNumber.getValue()!=null){
            long renterId = ((Room)roomNumber.getValue()).getRenter_renterId();
            ResultSet renter = RenterManage.getRenterInfo(renterId);
            try{
                if(renter.next()){
                    name.setText(renter.getString("renterFirstName")+" "+renter.getString("renterLastName"));
                    for(int i=0;i<roomTypeArr.size();i++){
                        if(roomTypeArr.get(i).getTypeId()==((Room)roomNumber.getValue()).getRoomType_typeId()){
                            typeroom.setValue(roomTypeArr.get(i));
                        }
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void reset(ActionEvent event) {
    }
}
