/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.DormManage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.DataCount;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormEditController extends DormDashboardController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private TextField dormName;
    @FXML
    private ComboBox dormType;
    @FXML
    private TextArea dormAddress;
    @FXML
    private TextField countfloor;
    @FXML
    private TextField elecRate;
    @FXML
    private TextField waterRate;
    @FXML
    private ListView<?> facilityList;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    
    private static String[] info;
    
    private static String[] facility;
    
    private CheckBox[] facilityChoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dormName.setText(info[0]);
        dormType.setValue(info[1]);
        dormAddress.setText(info[2]);
        countfloor.setText(info[3]);
        elecRate.setText(info[4]);
        waterRate.setText(info[5]);
        
        int facilityTotal = DataCount.count("dormitoryfacilitydorm");
        facilityChoice = new CheckBox[facilityTotal];
        try{
            DormManage.getFacility(facilityTotal);
        }catch(SQLException e){
            e.printStackTrace();
        }
        int dormFacilitySize = 0;
        String[] dormFacilityId = new String[dormFacilitySize];
        int dormFacilityIndex = 0;
        for(int i=0;i<facilityChoice.length;i++){
            if(facilityChoice[i].isSelected()){
                dormFacilityId[dormFacilityIndex] = facilityChoice[i].getId();
                dormFacilityIndex++;
            }
        }
    }    

    @FXML
    private void gotoHome(ActionEvent event) {
        
    }

    @FXML
    private void editedListener(KeyEvent event) {
        
    }

    @FXML
    private void editedListener(ActionEvent event) {
    }

    @FXML
    private void confirm(ActionEvent event) {
    }

    @FXML
    private void resetField(ActionEvent event) {
        resetBtn.setDisable(true);
    }
    
    private void edited(){
        resetBtn.setDisable(false);
    }
    
    public static void setDormInfo(String[] dormInfo){
        info = dormInfo;
    }
    
    public static void setFacilityId(String[] facilityId){
        facility = facilityId;
    }
}
