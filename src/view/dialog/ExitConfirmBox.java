/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author fluke
 */
public class ExitConfirmBox {
    static boolean answer = false;
    public static Boolean display(){
        
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm");
        window.setMinWidth(300);
        window.setMinHeight(200);
        
        Label label = new Label();
        label.setText("Are you sure you want to exit the program?");
        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        
        yesBtn.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noBtn.setOnAction(e ->{
           window.close(); 
        });
        
        VBox layout = new VBox(30);
        layout.getChildren().addAll(label,yesBtn,noBtn);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
}
