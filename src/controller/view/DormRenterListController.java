/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
/**
 * FXML Controller class
 *
 * @author CBC
 */
public class DormRenterListController implements Initializable {




    @FXML
    private Button addguestButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField searchhor;
    @FXML
    private GridPane renterinfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
