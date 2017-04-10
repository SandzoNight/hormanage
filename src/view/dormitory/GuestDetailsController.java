/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dormitory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class GuestDetailsController implements Initializable{
    
    
    
    ObservableList<String> typeList = FXCollections.observableArrayList("หอหญิง","หอชาย","หอรวม");

     @FXML
    private CheckBox Table;

    @FXML
    private CheckBox Chair;

    @FXML
    private CheckBox SwimmingPool;

    @FXML
    private TextField dormName;

    @FXML
    private CheckBox AirCondition;

    @FXML
    private ImageView hor;

    @FXML
    private CheckBox Furniture;

    @FXML
    private CheckBox Security;

    @FXML
    private Label label1;

    @FXML
    private CheckBox CarPark;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private CheckBox Park;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private CheckBox Fan;

    @FXML
    private ChoiceBox dormType;

    @FXML
    private TextField dormAddress;

    @FXML
    private TextField countRoom;

    @FXML
    private Button registerBt;

    @FXML
    private Pane register;
  
    private void initialize() {
        dormType.setItems(typeList);
        dormType.setValue("typeList");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
 

    }
    


