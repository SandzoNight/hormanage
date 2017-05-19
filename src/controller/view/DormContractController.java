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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DormContractController implements Initializable {

     @FXML
    private TextField no;

    @FXML
    private TextArea address;

    @FXML
    private TextField getBail;

    @FXML
    private TextField idCard;

    @FXML
    private DatePicker date2;

    @FXML
    private DatePicker date1;

    @FXML
    private TextField waterBill;

    @FXML
    private TextArea reference;

    @FXML
    private Button confirm;

    @FXML
    private TextField electricityBill;

    @FXML
    private TextField rate;

    @FXML
    private TextField surname;

    @FXML
    private TextField name;

    @FXML
    private Button reset;

    @FXML
    private TextField tel;

    @FXML
    private TextField Bail;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
