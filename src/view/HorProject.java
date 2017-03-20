/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DBConnector;

/**
 *
 * @author fluke
 */
public class HorProject extends Application {
    
    static Stage window;
    static Scene defaultScene;
    
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("HOR Manager Application");
        defaultScene = new MainMenu().open();
        
        //Call Exit Confirm Box when trying to close the program
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        
        window.setScene(defaultScene);
        window.show();
    }

    public void closeProgram(){
        boolean answer = ExitConfirmBox.display();
        if(answer){
            DBConnector dbc = new DBConnector();
            dbc.disconnect();
            window.close();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
