/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class DormRoomTypeListController extends DormRoomListController implements Initializable {

    @FXML
    private Hyperlink backBtn;
    @FXML
    private GridPane roomListGridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int index = 0;
        ResultSet roomtypeList = RoomTypeManage.list(dormId);
        ArrayList<Node> typeNameLabel = new ArrayList<Node>();
        ArrayList<Node> priceLabel = new ArrayList<Node>();
        ArrayList<Node> button = new ArrayList<Node>();
        try{
            while(roomtypeList.next()){
                Button btn = new Button(" >> ");
                btn.setId(roomtypeList.getString("typeId"));
                btn.setOnAction(e->{
                    gotoEditRoomType(e);
                });
                button.add(btn);
                Label typeName = new Label(roomtypeList.getString("typeName"));
                typeNameLabel.add(typeName);
                Label price = new Label(roomtypeList.getString("price"));
                priceLabel.add(price);
            }
            for(int i=0;i<typeNameLabel.size();i++){
                roomListGridPane.add(typeNameLabel.get(i), 0, i);
                roomListGridPane.add(priceLabel.get(i),1,i);
                roomListGridPane.add(button.get(i),2,i);
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
    private void gotoAddRoomType(ActionEvent event){
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();

            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomTypeAdd.fxml").openStream());
            Scene scene = new Scene(root);
            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void gotoEditRoomType(ActionEvent event){
        System.out.println("Go to edit page..");
        Button temp = (Button)event.getSource();
        try{
            //Prepare needed parameters for the new page
            FXMLLoader loader = new FXMLLoader();
            DormRoomTypeEditController.setTypeId(Long.parseLong(temp.getId()));
            
            //Prepare new page
            root = loader.load(getClass().getResource("/view/dormitory/DormRoomTypeEdit.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
