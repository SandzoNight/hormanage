/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author CBC
 */
public class DormRegisterController implements Initializable {

    
    @FXML
    private TextField countfloor;

    @FXML
    private CheckBox security;

    @FXML
    private TextField dormName;

    @FXML
    private CheckBox carpark;

    @FXML
    private TextArea dormAddress;

    @FXML
    private ComboBox dormType;

    @FXML
    private CheckBox swimmingpool;

    @FXML
    private CheckBox park;

    @FXML
    private Button register;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
