/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.DormManage;
import controller.RoomManage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.DataCount;
import model.DataQuery;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormRoomListController extends DormDashboardController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private ComboBox floorList;
    
    private Button[] viewBtn;
    private Label[] roomNo;
    private Label[] roomStatus;
    
    @FXML
    private GridPane roomListGridPane;
    @FXML
    private Button floorSelectBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int totalFloor = DormManage.getFloor(dormId);
        for(int i=0;i<totalFloor;i++){
            floorList.getItems().add((i+1)+"");
        }
        floorList.setValue("1");
        
        int countRoom = RoomManage.totalRoomByFloor(dormId+"","1");
        System.out.println("CountRoom="+countRoom);
        viewBtn = new Button[countRoom];
        roomNo = new Label[countRoom];
        roomStatus = new Label[countRoom];
        
        int objIndex = 0;
        ResultSet roomList = RoomManage.list(dormId+"", "1");
        try {
            while(roomList.next()){
                roomNo[objIndex] = new Label(roomList.getString("roomNo"));
                roomStatus[objIndex] = new Label(roomList.getString("roomStatus"));
                viewBtn[objIndex] = new Button(" >> ");
                viewBtn[objIndex].setId(roomList.getString("roomId"));
                viewBtn[objIndex].setOnAction(e -> {
                    gotoRoomEditPage(e);
                });
                roomListGridPane.add(roomNo[objIndex], 0, objIndex);
                roomListGridPane.add(roomStatus[objIndex], 1, objIndex);
                roomListGridPane.add(viewBtn[objIndex], 2, objIndex);
                objIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @FXML
    private void floorSelected(ActionEvent event) {
        System.out.println(floorList.getValue());
        int countRoom = RoomManage.totalRoomByFloor(dormId+"", (String)floorList.getValue());
        System.out.println("CountRoom="+countRoom);
        viewBtn = new Button[countRoom];
        roomNo = new Label[countRoom];
        roomStatus = new Label[countRoom];
        roomListGridPane.getChildren().clear();
        
        int objIndex = 0;
        ResultSet roomList = RoomManage.list(dormId+"", (String)floorList.getValue());
        System.out.println("changing floor to "+(String)floorList.getValue());
        try {
            while(roomList.next()){
                roomNo[objIndex] = new Label(roomList.getString("roomNo"));
                System.out.println(roomList.getString("roomNo"));
                roomStatus[objIndex] = new Label(roomList.getString("roomStatus"));
                viewBtn[objIndex] = new Button(" >> ");
                viewBtn[objIndex].setId(roomList.getString("roomId"));
                viewBtn[objIndex].setOnAction(e -> {
                    gotoRoomEditPage(e);
                });
                roomListGridPane.add(roomNo[objIndex], 0, objIndex);
                roomListGridPane.add(roomStatus[objIndex], 1, objIndex);
                roomListGridPane.add(viewBtn[objIndex], 2, objIndex);
                objIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoRoomTypeList(ActionEvent event) {
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
    
    private void gotoRoomEditPage(ActionEvent event){
        Button temp = (Button)event.getSource();
        long roomId = Long.parseLong(temp.getId());
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();
            DormRoomEditController.setRoomId(roomId);
            
            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomEdit.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoAddRoom(ActionEvent event) {
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();
            
            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomAdd.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
