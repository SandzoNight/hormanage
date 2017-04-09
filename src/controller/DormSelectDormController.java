/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import view.home.HorProject;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormSelectDormController extends DormMainController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Button addDormBtn;
    @FXML
    private Label testUserId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        testUserId.setText(userId);
    }    

    @FXML
    private void gotoAddDorm(ActionEvent event) {
        System.out.println("Going to another page..");
        
        
    }
    
    @FXML
    private void gotoHome(ActionEvent event) {
        System.out.println("Home btn clicked");
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
}
