/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.DormManage;
import controller.RenterManage;
import controller.RoomManage;
import controller.RoomTypeManage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Renter;
import model.RoomType;

/**
 * FXML Controller class
 *
 * @author Pacharapol
 */
public class DormRoomAddController extends DormRoomListController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Label testUserId;
    @FXML
    private TextField roomNo;
    @FXML
    private ComboBox floorNo;
    @FXML
    private ComboBox roomtype;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox renterListBox;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    
    private ArrayList<Renter> renters = new ArrayList<Renter>();
    private ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();
    private static long roomId;
    ArrayList<Renter> searchedRenters = new ArrayList<Renter>(); 
    @FXML
    private Label roomtypeErrorLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(dormId);
        ResultSet rs = RenterManage.getAvailableRenter(dormId);
        try{
            while(rs.next()){
                renters.add(new Renter(rs.getString("renterId"),rs.getString("renterFirstName"),rs.getString("renterLastName")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        for(int i=0;i<renters.size();i++){
            renterListBox.getItems().add(renters.get(i).getFullname());
        }
        
        ResultSet rs1 = RoomTypeManage.list(dormId);
        try{
           while(rs1.next()){
               roomTypes.add(new RoomType(rs1.getLong("typeId"),rs1.getString("typeName"),rs1.getDouble("price")));
           } 
        }catch(SQLException e){
            e.printStackTrace();
        }
        roomtype.getItems().clear();
        for(int i=0;i<roomTypes.size();i++){
            roomtype.getItems().add(roomTypes.get(i));
        }
        
        int dormFloorNo = DormManage.getFloor(dormId);
        for(int i=0;i<dormFloorNo;i++){
            floorNo.getItems().add((i+1)+"");
        }
        floorNo.setValue("1");
        
    }    

    @FXML
    private void gotoHome(ActionEvent event) {
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void edited(KeyEvent event) {
        System.out.println("edited");
        resetBtn.setDisable(false);
    }

    @FXML
    private void confirm(ActionEvent event) {
        if(roomtype.getValue()!=null){
            String renterCheckStr;
            long renterId = 0;
            if(renterListBox.getValue()!=null){
                renterCheckStr = (String)renterListBox.getValue();
                for(int i=0;i<renters.size();i++){
                    System.out.println("Checking contains");
                    if((renters.get(i).getFullname()).contains(renterCheckStr)){
                        System.out.println("Matched if !");
                        renterId = renters.get(i).getRenterId();
                    }
                }
            }else{
                renterCheckStr = "";
            }

            System.out.println("renterCheckStr : "+renterCheckStr);


            RoomType temp2 = (RoomType)roomtype.getValue();
            String roomTypeCheckStr = (String)temp2.getTypeName();
            String roomType = "";
            for(int i=0;i<roomTypes.size();i++){
                if(roomTypes.get(i).getTypeName().contains(roomTypeCheckStr)){
                    roomType = roomTypes.get(i).getTypeId()+"";
                }
            }
            System.out.println("renterId : "+renterId);
            System.out.println("roomtypeId : "+roomType);
            ArrayList<String> data = new ArrayList<String>();
            data.add(roomNo.getText());
            data.add((String)floorNo.getValue());
            data.add(roomType);

            if(renterId==0){
                data.add(null);
            }else{
                data.add(renterId+"");
                RenterManage.updateRoomId(roomId, renterId);
            }

            RoomManage.create(data, dormId);
        }else{
            roomtypeErrorLabel.setVisible(true);
        }
        
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomList.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        resetBtn.setDisable(true);
        roomNo.setText("");
        floorNo.setValue("1");
        
    }

    public static void setRoomId(long roomId) {
        DormRoomAddController.roomId = roomId;
    }

    @FXML
    private void searchRenter() {
        String keyword = searchField.getText();
        System.out.println(keyword);
        System.out.println(renters);
        
        searchedRenters = new ArrayList<Renter>();
        for(int i=0;i<renters.size();i++){
            if(renters.get(i).getRenterName().toLowerCase().contains(keyword) || renters.get(i).getRenterLastName().toLowerCase().contains(keyword)
                    ||renters.get(i).getRenterName().toUpperCase().contains(keyword) || renters.get(i).getRenterLastName().toUpperCase().contains(keyword)){
                searchedRenters.add(renters.get(i));
            }
        }
        
        renterListBox.getItems().clear();
        renterListBox.getItems().addAll(searchedRenters);
        
        renterListBox.show();
        System.out.println(searchedRenters);
        
    }
    
}
