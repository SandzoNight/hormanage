/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import java.net.URL;
import java.util.ResourceBundle;
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
        // TODO
    }    
    
}


