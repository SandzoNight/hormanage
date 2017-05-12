/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.RoomTypeManage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        ArrayList typeNameLabel = new ArrayList();
        ArrayList button = new ArrayList();
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
                
                roomListGridPane.add(root, index, index);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }    

    @FXML
    private void gotoHome(ActionEvent event) {
    }
    
    private void gotoEditRoomType(ActionEvent event){
        System.out.println("Go to edit page..");
    }
    
}
