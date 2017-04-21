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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import view.home.HorProject;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormMainController extends HorProject implements Initializable {
    protected static String userId;
    @FXML
    private Hyperlink manageDormBtn;
    @FXML
    private Hyperlink settingBtn;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public static void setUserId(String userIdInput){
        userId = userIdInput;
    }

    @FXML
    private void gotoSelectDorm(ActionEvent event) {
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();
//            DormSelectDormController dormSelectDormController = (DormSelectDormController)loader.getController();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormSelectDorm.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
