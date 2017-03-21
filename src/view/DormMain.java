/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
    
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("HOR Manager Application");
        defaultScene = DormSelecDorm.open();
        
        //Call Exit Confirm Box when trying to close the program
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        
        window.setScene(defaultScene);
        window.show();
    }

//    public void closeProgram(){
//        boolean answer = ExitConfirmBox.display();
//        if(answer){
//            window.close();
//        }
//    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
