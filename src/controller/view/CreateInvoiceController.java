/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author CBC
 */
public class CreateInvoiceController implements Initializable {

    @FXML
    private TextField electricityBill;

    @FXML
    private TextField total;

    @FXML
    private TextField roomNumber;

    @FXML
    private TextField surname;

    @FXML
    private ComboBox<?> typeroom;

    @FXML
    private TextField name;

    @FXML
    private TextField roomRates;

    @FXML
    private Button reset;

    @FXML
    private TextField invoiceNo;

    @FXML
    private TextField waterBill;

    @FXML
    private Button comfirm;

    @FXML
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
}
