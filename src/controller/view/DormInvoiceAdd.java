/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.DormManage;
import controller.FieldFormat;
import controller.InvoiceManage;
import controller.RoomTypeManage;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import model.Dormitory;
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
    private ComboBox typeroom;
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
    private TextField surname;
    @FXML
    private DatePicker dueDate;
    @FXML
    private Button confirm;
    @FXML
    private Button reset;
    
    private ArrayList<RoomType> roomTypeArr = new ArrayList<RoomType>();
    private Dormitory dorm;
    @FXML
    private TextField waterUnit;
    @FXML
    private TextField electricityUnit;

    private double[] rate = new double[2];
    private long invoiceNumber;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        typeroom.getItems().add("เลือกประเภทห้อง");
        typeroom.setValue("เลือกประเภทห้อง");
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
    

    private void confirm(ActionEvent event) {
        LocalDate dueDateDate = dueDate.getValue();
        Date input = new Date();
        LocalDate startDate = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ArrayList<String> data = new ArrayList<String>();
        data.add(roomNumber.getText()); //0
        data.add(name.getText()); //1
        data.add(surname.getText()); //2
        data.add((String)typeroom.getValue()); //3
        data.add(waterUnit.getText()); //4
        data.add(electricityUnit.getText()); //5
        data.add(rate[0]*Double.parseDouble(waterUnit.getText())+""); //6
        data.add(rate[1]*Double.parseDouble(electricityUnit.getText())+""); //7
        data.add(((RoomType)typeroom.getValue()).getPrice()+""); //8
    }

    @FXML
    private void roomtypeSelected(ActionEvent event) {
        double[] unit = {Double.parseDouble(waterUnit.getText()),Double.parseDouble(electricityUnit.getText())};
        if(!typeroom.getValue().equals("เลือกประเภทห้อง")){
            for(int i=0;i<roomTypeArr.size();i++){
                if(typeroom.getValue().equals(roomTypeArr.get(i))){
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
                if(typeroom.getValue().equals(roomTypeArr.get(i))){
                    total.setText(InvoiceManage.priceCalculator(rate,unit,roomTypeArr.get(i).getPrice())+"");
                }
            }
        }
    }
}
