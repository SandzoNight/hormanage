/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.RoomTypeManage;
import controller.FieldFormat;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
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
public class DormRoomTypeEditController extends DormRoomTypeListController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Label testUserId;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    
    private static long typeId;
    private ArrayList<String> info = RoomTypeManage.getDetail(typeId);
    @FXML
    private TextField typeName;
    @FXML
    private TextField price;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeName.setText(info.get(0));
        price.setText(info.get(1));
        price.setTextFormatter(new TextFormatter<>(FieldFormat.doubleFormat()));
    }

    @FXML
    private void gotoHome(ActionEvent event) {
    }

    @FXML
    private void edited(KeyEvent event) {
        resetBtn.setDisable(false);
    }

    @FXML
    private void confirm(ActionEvent event) {
        ArrayList<String> info = new ArrayList<String>();
        info.add(typeName.getText());
        info.add(price.getText());
        RoomTypeManage.update(info, typeId);
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
        
    }

    @FXML
    private void reset(ActionEvent event) {
        typeName.setText(info.get(0));
        price.setText(info.get(1));
        resetBtn.setDisable(true);
    }
    
    public static void setTypeId(long typeId_in){
        typeId = typeId_in;
    }
    
}
