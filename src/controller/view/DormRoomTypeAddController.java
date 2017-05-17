/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.FieldFormat;
import controller.RoomTypeManage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Pacharapol
 */
public class DormRoomTypeAddController extends DormRoomTypeListController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Label testUserId;
    @FXML
    private TextField typeName;
    @FXML
    private TextField price;
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
        price.setTextFormatter(new TextFormatter(FieldFormat.doubleFormat()));
    }    

    @FXML
    private void gotoHome(ActionEvent event) {
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomTypeList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void edited(KeyEvent event) {
        resetBtn.setDisable(false);
    }

    @FXML
    private void confirm(ActionEvent event) {
        if(typeName.getText().length()>0){
            if(price.getText().length()>0){
                ArrayList<String> info = new ArrayList<String>();
                info.add(typeName.getText());
                info.add(price.getText());
                RoomTypeManage.create(info,dormId);
                try{
                    FXMLLoader loader = new FXMLLoader();
                    //Prepare new page
                    root = loader.load(getClass().getResource("/view/dormitory/DormRoomTypeList.fxml").openStream());
                    Scene scene = new Scene(root);
                    //Change to new page
                    window.setScene(scene);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }else{
                price.setStyle("-fx-border-color:red");
            }
        }else{
            typeName.setStyle("-fx-border-color:red");
        }
        
    }

    @FXML
    private void reset(ActionEvent event) {
        typeName.setText("");
        price.setText("");
        resetBtn.setDisable(true);
    }
    
}
