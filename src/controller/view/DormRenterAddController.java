/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormRenterAddController extends DormRenterInfoController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Label testUserId;
    @FXML
    private TextField renterFirstNameField;
    @FXML
    private TextField renterGenderField;
    @FXML
    private TextField renterTelField;
    @FXML
    private TextField renterEmailField;
    @FXML
    private TextArea renterAddrField;
    @FXML
    private TextField renterLastNameField;
    @FXML
    private Button confirmBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoHome(ActionEvent event) {
        System.out.println("Go back to Home from DormSelectDorm");
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormMain.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void confirm(ActionEvent event) {
        System.out.println("DormId = "+dormId);
    }

    
}
