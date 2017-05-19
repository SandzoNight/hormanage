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
import static controller.view.DormDashboardController.dormId;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Renter;
import model.RoomType;
import view.dialog.DeleteBox;

/**
 * FXML Controller class
 *
 * @author Pacharapol
 */
public class DormRoomEditController extends DormRoomListController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private Label testUserId;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button resetBtn;
    
    private ArrayList<Renter> renters = new ArrayList<Renter>();
    private ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();
    private ArrayList<Renter> searchedRenters = new ArrayList<Renter>(); 
    @FXML
    private ComboBox roomtype;
    
    private static long roomId;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox renterListBox;
    @FXML
    private TextField searchField;
    @FXML
    private TextField roomNo;
    @FXML
    private ChoiceBox floorNo;
    @FXML
    private Label roomtypeErrorLabel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs = RenterManage.getAvailableRenter(dormId);
        try{
            while(rs.next()){
                renters.add(new Renter(rs.getString("renterId"),rs.getString("renterFirstName"),rs.getString("renterLastName")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        renterListBox.getItems().add("");
        for(int i=0;i<renters.size();i++){
            renterListBox.getItems().add(renters.get(i).getRenterName()+" "+renters.get(i).getRenterLastName());
        }
        
        int dormFloorNo = DormManage.getFloor(dormId);
        for(int i=0;i<dormFloorNo;i++){
            floorNo.getItems().add((i+1)+"");
        }
        
        ResultSet rs1 = RoomTypeManage.list(dormId);
        try{
           while(rs1.next()){
               roomTypes.add(new RoomType(rs1.getLong("typeId"),rs1.getString("typeName")));
           } 
        }catch(SQLException e){
            e.printStackTrace();
        }
        roomtype.getItems().clear();
        for(int i=0;i<roomTypes.size();i++){
            roomtype.getItems().add(roomTypes.get(i));
        }
        
        ResultSet rs2 = RoomManage.getDetail(roomId+"");
        try{
            while(rs2.next()){
                roomNo.setText(rs2.getString("roomNo"));
                floorNo.setValue(rs2.getString("roomFloorNumber"));
                ArrayList<String> roomTypeDetail = RoomTypeManage.getDetail(rs2.getLong("RoomType_typeId"));
                roomtype.setValue(roomTypeDetail.get(0));
                if(rs2.getString("Renter_renterId")!=null){
                    ResultSet rs3 = RenterManage.getRenterInfo(rs2.getLong("Renter_renterId"));
                    if(rs3.next()){
                        renterListBox.setValue(rs3.getString("renterFirstName")+" "+rs3.getString("renterLastName"));
                    }
                }else{
                    renterListBox.setValue("");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
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
    }

    @FXML
    private void confirm(ActionEvent event) {
        long renterId = 0;
        String renterCheckStr;
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
//        RoomType temp = (RoomType)roomtype.getValue();
        String roomTypeCheckStr = (String)roomtype.getValue();
        String roomType = "";
            for(int i=0;i<roomTypes.size();i++){
                if(roomTypes.get(i).getTypeName().contains(roomTypeCheckStr)){
                    roomType = roomTypes.get(i).getTypeId()+"";
                }
            }
        
        
        ArrayList<String> data = new ArrayList<String>();
        data.add(roomNo.getText());
        if(floorNo.getValue()!=null){
            data.add((String)floorNo.getValue());
        }else{
            data.add(null);
        }
        data.add(roomType);
        data.add(renterId+"");
        RoomManage.update(data, roomId);
        
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
    }

    public static void setRoomId(long roomId) {
        DormRoomEditController.roomId = roomId;
    }

    @FXML
    private void searchRenter() {
        String keyword = searchField.getText();
        System.out.println(keyword);
        System.out.println(renters);
        
        ArrayList<Renter> searchedRenters = new ArrayList<Renter>();
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

    @FXML
    private void deleteRoom(ActionEvent event) {
        boolean answer = DeleteBox.display(this);
        if(answer){
            DeleteBox.answer = false;
            System.out.println("[DormRoomEditController]Deleting room");
//            window.close();
            RoomManage.delete(roomId+"");
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
        
    }

    
}
