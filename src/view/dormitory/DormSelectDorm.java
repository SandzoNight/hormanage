/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dormitory;

import view.dormitory.DormMain;
import controller.DormAdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.DataCount;
import model.DataInsert;
import model.DataQuery;
import view.home.HorProject;

/**
 *
 * @author fluke
 */
public class DormSelectDorm extends DormMain{
    public static Scene open(String userId){
        Label label = new Label("Select Your Dorm");
        label.setFont(new Font("Tahoma",24));
        
        GridPane dormlist = new GridPane();
        dormlist.setHgap(100);
        dormlist.setVgap(20);
        dormlist.alignmentProperty();
        int totalDorm = DataCount.count("dormitory", "User_userId", userId);
        Label[] lbDormNumber = new Label[totalDorm];
        Label[] lbDormName = new Label[totalDorm];
        Button[] btnView = new Button[totalDorm];
        ResultSet userDorm = DataQuery.query("dormitory", "User_userId", userId);
        int i=0;
        try{
            while(userDorm.next() && i<5){
                lbDormNumber[i] = new Label((i+1)+".");
                lbDormName[i] = new Label(userDorm.getString("dormName"));
                btnView[i] = new Button("View");
                dormlist.add(lbDormNumber[i], 0, i);
                dormlist.add(lbDormName[i], 1, i);
                dormlist.add(btnView[i], 2, i);
                dormlist.setHalignment(lbDormNumber[i], HPos.CENTER);
                dormlist.setHalignment(lbDormName[i], HPos.CENTER);
                dormlist.setHalignment(btnView[i], HPos.CENTER);
                
                i++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        ScrollPane dormlistContainer = new ScrollPane();
        dormlistContainer.setContent(dormlist);
        
        StackPane centerContainer = new StackPane();
        centerContainer.getChildren().add(dormlistContainer);
        
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,centerContainer);
        
        Scene scene = new Scene(layout,640,480);
        return scene;
    }
}
