/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.DormManage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.DataCount;
import model.DataQuery;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormRegisterController extends DormSelectDormController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private TextArea dormAddress;
    @FXML
    private TextField dormName;
    @FXML
    private TextField countfloor;
    @FXML
    private ComboBox dormType;
    @FXML
    private TextField waterRate;
    @FXML
    private TextField elecRate;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    
    private CheckBox[] facility;
    private int facilityCount = 0;
    @FXML
    private ListView facilityList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dormType.getItems().addAll("หอรวม","หอชาย","หอหญิง");
        dormType.setValue("หอรวม");
        int facilityTotal = DataCount.count("dormitoryfacilitydorm");
        int objIndex = 0;
        facility = new CheckBox[facilityTotal];
        ResultSet rs = DataQuery.query("dormitoryfacilitydorm");
        try{
            while(rs.next()){
                facility[objIndex] = new CheckBox();
                facility[objIndex].setText(rs.getString("facilityName"));
                facility[objIndex].setId(rs.getString("facilityDormId"));
                facility[objIndex].setOnAction(e -> {
                    choiceBoxSelected(e);
                });
                facilityList.getItems().addAll(facility[objIndex]);
                objIndex++;
                facilityCount++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        resetBtn.setDisable(true);
    }

    @FXML
    private void gotoHome(ActionEvent event) {
        System.out.println("Go back to Home from DormRenterList");
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
        String[] dormInfo = new String[6];
        dormInfo[0] = dormName.getText();
        dormInfo[1] = dormAddress.getText();
        if(dormType.getValue().toString() == null){
            dormInfo[2] = "";
        }else{
            dormInfo[2] = dormType.getValue().toString();
        }
        dormInfo[3] = countfloor.getText();
        dormInfo[4] = waterRate.getText();
        dormInfo[5] = elecRate.getText();
        
        int dormFacilitySize = 0;
        for(int i=0;i<facility.length;i++){
            if(facility[i].isSelected()){
                dormFacilitySize++;
            }
        }
        String[] dormFacilityId = new String[dormFacilitySize];
        int dormFacilityIndex = 0;
        for(int i=0;i<facility.length;i++){
            if(facility[i].isSelected()){
                dormFacilityId[dormFacilityIndex] = facility[i].getId();
                dormFacilityIndex++;
            }
        }
        DormManage.add(dormInfo, dormFacilityId, userId);
        
        for(int i=0;i<dormInfo.length;i++){
            System.out.println(i +" " +dormInfo[i]);
        }
        
        //Prepare needed parameters for the new page
        FXMLLoader loader = new FXMLLoader();
        try{
            root = loader.load(getClass().getResource("/view/dormitory/DormSelectDorm.fxml").openStream());
            Scene scene = new Scene(root);
            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        } 
    }

    @FXML
    private void resetField(ActionEvent event) {
        dormName.setText("");
        dormType.setValue("หอรวม");
        dormAddress.setText("");
        countfloor.setText("1");
        elecRate.setText("0.0");
        waterRate.setText("0.0");
        for(int i=0;i<facility.length;i++){
            facility[i].setSelected(false);
        }
    }

    @FXML
    private void textTyped(KeyEvent event) {
        edited();
    }
    
    @FXML
    private void comboBoxSelected(ActionEvent event) {
        edited();
    }
    private void choiceBoxSelected(ActionEvent event) {
        edited();
    }
    
    private void edited(){
        resetBtn.setDisable(false);
    }
    
}
