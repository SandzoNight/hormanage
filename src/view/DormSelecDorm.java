/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DormAdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.DataInsert;
import model.DataQuery;
import static view.HorProject.window;

/**
 *
 * @author fluke
 */
public class DormSelecDorm {
    public static Scene open(){
        Label label = new Label("Select Your Dorm");
        label.setFont(new Font("Tahoma",24));
        
        HBox buttonLayout = new HBox(50);
        Button loginBtn = new Button("Login");
        Button regisBtn = new Button("Register");
        
        buttonLayout.getChildren().addAll(loginBtn,regisBtn);
        
        FlowPane layout = new FlowPane(10,10);
        layout.setAlignment(Pos.CENTER);
        layout.setOrientation(Orientation.VERTICAL);
        layout.getChildren().addAll(label,buttonLayout);
        
        Scene scene = new Scene(layout,640,480);
        
        //Test Insert into DB
        ResultSet res = DataQuery.query("dormitory");
        DataInsert di = new DataInsert();
        //Values pattern --> dormName,dormType,dormAddress,countRoom,visitorNo,User_userId
        //di.insert("dormitory", "dormId,dormName,dormType,dormAddress,countRoom,facilityDormId,facilityRoomId,visitorNo,User_userId", "'"+nextDormId+"', 'Hornai', 'Female', 'KMUTT', '100', '0"+nextDormId+"', '0"+nextDormId+"', '0', '123456789'");
        
        return scene;
    }
}
