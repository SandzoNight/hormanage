/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 *
 * @author fluke
 */
public class MainMenu extends HorProject{
    public Scene open(){        
        Label label = new Label("Main Menu");
        label.setFont(new Font("Tahoma",24));
        
        HBox buttonLayout = new HBox(50);
        Button loginBtn = new Button("Login");
        Button regisBtn = new Button("Register");
        loginBtn.setOnAction(e -> {
            window.setScene(Login.open(this.open()));
        });
        
        buttonLayout.getChildren().addAll(loginBtn,regisBtn);
        
        FlowPane layout = new FlowPane(10,10);
        layout.setAlignment(Pos.CENTER);
        layout.setOrientation(Orientation.VERTICAL);
        layout.getChildren().addAll(label,buttonLayout);
        
        Scene scene = new Scene(layout,640,480);
        return scene;
    }
}
