/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dormitory;

import view.home.HorProject;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.application.Application;

/**
 *
 * @author fluke
 */
public class DormMain extends HorProject{
    static String userId;
    
    public void start(Stage primaryStage,String userId) {
        this.userId = userId;
        window = primaryStage;
        window.setTitle("HOR Manager Application");
        defaultScene = DormSelectDorm.open(userId);
        
        //Call Exit Confirm Box when trying to close the program
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        
        window.setScene(defaultScene);
        window.show();
    }
}
