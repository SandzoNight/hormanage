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
public class DormEditController extends DormDashboardController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private TextField dormName;
    @FXML
    private ComboBox dormTypeCombobox;
    @FXML
    private TextArea dormAddress;
    @FXML
    private TextField countfloor;
    @FXML
    private TextField elecRate;
    @FXML
    private TextField waterRate;
    @FXML
    private ListView facilityList;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    
    private int facilityCount = 0;
    
    private static String[] info;
    
    private static String[] facility;
    
    private CheckBox[] facilityChoicebox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("[DormEditController]Initializing page..");
        dormName.setText(info[0]);
        dormTypeCombobox.getItems().addAll("หอรวม","หอชาย","หอหญิง");
        dormTypeCombobox.setValue(info[1]);
        dormAddress.setText(info[2]);
        countfloor.setText(info[3]);
        elecRate.setText(info[7]);
        waterRate.setText(info[6]);
        
        int facilityTotal = DataCount.count("dormitoryfacilitydorm");
        facilityChoicebox = new CheckBox[facilityTotal];
        int objIndex = 0;
        ResultSet rs = DataQuery.query("dormitoryfacilitydorm");
        System.out.println("[DormEditController]Creating choicebox..");
        try{
            while(rs.next()){
                facilityChoicebox[objIndex] = new CheckBox();
                facilityChoicebox[objIndex].setText(rs.getString("facilityName"));
                facilityChoicebox[objIndex].setId(rs.getString("facilityDormId"));
                facilityChoicebox[objIndex].setOnAction(e -> {
                    editedListener(e);
                });
                facilityList.getItems().addAll(facilityChoicebox[objIndex]);
                objIndex++;
                facilityCount++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("[DormEditController]Selecting facility..");
        for(int i=0;i<facilityChoicebox.length;i++){
            for(int j=0;j<facility.length;j++){
                if(facilityChoicebox[i].getId().toString().equals(facility[j])){
                    facilityChoicebox[i].setSelected(true);
                }
            }
        }
    }    

    @FXML
    private void gotoHome(ActionEvent event) {
        System.out.println("Go back to Home from DormRenterList");
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormDashboard.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    private void editedListener(ActionEvent event) {
        edited();
    }

    @FXML
    private void confirm(ActionEvent event) {
        for(int i=0;i<info.length;i++){
            System.out.println(info[i]);
        }
        System.out.println("===Facility===");
        for(int i=0;i<facility.length;i++){
            System.out.println(facility[i]);
        }
        System.out.println("================");
        
        info[0] = dormName.getText();
        info[1] = dormTypeCombobox.getValue().toString();
        info[2] = dormAddress.getText();
        info[4] = countfloor.getText();
        info[5] = elecRate.getText();
        info[6] = waterRate.getText();
        
        int size = 0;
        for(int i=0;i<facilityChoicebox.length;i++){
            if(facilityChoicebox[i].isSelected()){
                size++;
            }
        }
        String[] facilityIds = new String[size];
        int facIndex = 0;
        for(int i=0;i<facilityChoicebox.length;i++){
            if(facilityChoicebox[i].isSelected()){
                facilityIds[facIndex] = facilityChoicebox[i].getId();
                facIndex++;
            }
        }
        
        for(int i=0;i<info.length;i++){
            System.out.println(info[i]);
        }
        System.out.println("===Facility===");
        for(int i=0;i<facilityIds.length;i++){
            System.out.println(facilityIds[i]);
        }
        DormManage.update(info, facilityIds);
    }

    @FXML
    private void resetField(ActionEvent event) {
        resetBtn.setDisable(true);
        dormName.setText(info[0]);
        dormTypeCombobox.setValue(info[1]);
        dormAddress.setText(info[2]);
        countfloor.setText(info[3]);
        elecRate.setText(info[7]);
        waterRate.setText(info[6]);
        for(int i=0;i<facilityChoicebox.length;i++){
            facilityChoicebox[i].setSelected(false);
        }
        for(int i=0;i<facilityChoicebox.length;i++){
            for(int j=0;j<facility.length;j++){
                if(facilityChoicebox[i].getId().toString().equals(facility[j])){
                    facilityChoicebox[i].setSelected(true);
                }
            }
        }
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

    @FXML
    private void keyTyped(KeyEvent event) {
        edited();
    }
}
