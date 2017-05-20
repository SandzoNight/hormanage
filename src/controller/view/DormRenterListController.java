/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.RenterManage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DataCount;
import model.DataQuery;
/**
 * FXML Controller class
 *
 * @author CBC
 */
public class DormRenterListController extends DormDashboardController implements Initializable {




    @FXML
    private Button addguestButton;
    
    @FXML
    private GridPane renterinfo;

    
    private Label[] roomNo;
    private Label[] floorNo;
    private Label[] roomType;
    private Label[] renterName;
    private Label[] tel;
    private Button[] viewBtn;
    @FXML
    private TextField searchBox;
    @FXML
    private Button searchBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Hyperlink backBtn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiateRenterList();  
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
    
    private void initiateRenterList(){
        renterinfo.getChildren().clear();
        int totalRenter = RenterManage.countRenter(dormId);
        
        roomNo = new Label[totalRenter];
        floorNo = new Label[totalRenter];
        roomType = new Label[totalRenter];
        renterName = new Label[totalRenter];
        tel = new Label[totalRenter];
        viewBtn = new Button[totalRenter];
        
        int objIndex = 0;
        ResultSet renterList = RenterManage.showCustomerList(dormId);
        try{
            while(renterList.next()){
                ResultSet roomInfo = DataQuery.query("room", "roomId", renterList.getString("Room_roomId"));
                if(roomInfo.next()){
                    roomNo[objIndex] = new Label(roomInfo.getString("roomNo"));
                    floorNo[objIndex] = new Label(roomInfo.getString("roomFloorNumber"));
                    ResultSet roomTypeInfo = DataQuery.query("roomtype","typeId",roomInfo.getString("Roomtype_typeId"));
                    if(roomTypeInfo.next()){
                        roomType[objIndex] = new Label(roomTypeInfo.getString("typeName"));
                    }
                }else{
                    roomNo[objIndex] = new Label("-");
                    floorNo[objIndex] = new Label("-");
                    roomType[objIndex] = new Label("-");
                }
                
                renterName[objIndex] = new Label(renterList.getString("renterFirstName")+" "+renterList.getString("renterLastName"));
                tel[objIndex] = new Label(renterList.getString("renterTel"));
                viewBtn[objIndex] = new Button(" >> ");
                viewBtn[objIndex].setId(renterList.getString("renterId"));
                viewBtn[objIndex].setOnAction(e -> {
                    viewRenterInfo(e);
                });
                
                renterinfo.add(roomNo[objIndex],0,objIndex);
                renterinfo.add(floorNo[objIndex],1,objIndex);
                renterinfo.add(roomType[objIndex],2,objIndex);
                renterinfo.add(renterName[objIndex],3,objIndex);
                renterinfo.add(tel[objIndex],4,objIndex);
                renterinfo.add(viewBtn[objIndex],5,objIndex);
                
                objIndex++;
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void searchRenter(ActionEvent event) {
        renterinfo.getChildren().clear();
        String keyword = searchBox.getText();
        
        int objIndex = 0;
        ResultSet renterList = RenterManage.searchRenterList(keyword, dormId+"");
        try{
            while(renterList.next()){
                ResultSet roomInfo = DataQuery.query("room", "roomId", renterList.getString("Room_roomId"));
                if(roomInfo.next()){
                    roomNo[objIndex] = new Label(roomInfo.getString("roomNo"));
                    floorNo[objIndex] = new Label(roomInfo.getString("roomFloorNumber"));
                    ResultSet roomTypeInfo = DataQuery.query("roomtype","typeId",roomInfo.getString("Roomtype_typeId"));
                    if(roomTypeInfo.next()){
                        roomType[objIndex] = new Label(roomTypeInfo.getString("typeName"));
                    }
                }else{
                    roomNo[objIndex] = new Label("-");
                    floorNo[objIndex] = new Label("-");
                    roomType[objIndex] = new Label("-");
                }
                
                renterName[objIndex] = new Label(renterList.getString("renterFirstName")+" "+renterList.getString("renterLastName"));
                tel[objIndex] = new Label(renterList.getString("renterTel"));
                viewBtn[objIndex].setId(renterList.getString("renterId"));
                
                renterinfo.add(roomNo[objIndex],0,objIndex);
                renterinfo.add(floorNo[objIndex],1,objIndex);
                renterinfo.add(roomType[objIndex],2,objIndex);
                renterinfo.add(renterName[objIndex],3,objIndex);
                renterinfo.add(tel[objIndex],4,objIndex);
                renterinfo.add(viewBtn[objIndex],5,objIndex);
                
                objIndex++;
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        resetBtn.setDisable(false);
    }

    @FXML
    private void resetRenter(ActionEvent event) {
        resetBtn.setDisable(true);
        searchBox.setText("");
        initiateRenterList();
    }
    
    private void viewRenterInfo(ActionEvent event) {
        Button temp = (Button)event.getSource();
        System.out.println("Go to DormRenterInfo page where renterId = "+temp.getId());
        
        try{
            FXMLLoader loader = new FXMLLoader();
            DormRenterInfoController.setRenterId(Long.parseLong(temp.getId()));
            
            Parent root = loader.load(getClass().getResource("/view/dormitory/DormRenterInfo.fxml").openStream());
            Stage newWindow = new Stage();
            newWindow.setTitle("ข้อมูลผู้เข้าพัก | HOR Manager Application");
            //แก้ปัญหา เวลากดแก้ไข ให้ปิดหน้า RenterInfo ด้วย
//            DormRenterInfoController.setOldStage(newWindow);
            Scene scene = new Scene(root);
            newWindow.setResizable(false);
            newWindow.setScene(scene);
            newWindow.show();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    @FXML
    private void addNewRenter(ActionEvent event) {
        System.out.println("Go to DormRenterAdd");
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRenterAdd.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
