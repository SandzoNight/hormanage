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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormRenterEditController extends DormRenterInfoController implements Initializable {

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
    
    private static String[] renterData;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        renterFirstNameField.setText(renterData[0]);
        renterLastNameField.setText(renterData[1]);
        renterGenderField.setText(renterData[2]);
        renterTelField.setText(renterData[4]);
        renterEmailField.setText(renterData[5]);
        renterAddrField.setText(renterData[3]);
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
    
    public static void setRenterData(String[] data){
        renterData = data;
    }

    @FXML
    private void confirm(ActionEvent event) {
        System.out.println("[DormRenterEditController]Updating renter information..");
        System.out.println("DormId="+dormId);
    }

    @FXML
    private void reset(ActionEvent event) {
        System.out.println("[DormRenterEditController]Resetting text fields..");
        renterFirstNameField.setText(renterData[0]);
        renterLastNameField.setText(renterData[1]);
        renterGenderField.setText(renterData[2]);
        renterTelField.setText(renterData[4]);
        renterEmailField.setText(renterData[5]);
        renterAddrField.setText(renterData[3]);
    }

    @FXML
    private void edited(KeyEvent event) {
        resetBtn.setDisable(false);
    }
    
}
