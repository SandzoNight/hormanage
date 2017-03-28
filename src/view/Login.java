/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import model.DataQuery;
/**
 *
 * @author fluke
 */
public class Login extends HorProject{
    public static Scene open(Scene s){ 
        Label label = new Label("Manager Login");
        label.setFont(new Font("Tahoma",24));
        Label userLabel = new Label("Username:");
        Label passLabel = new Label(" Password:");
        TextField username = new TextField();
        
        PasswordField password = new PasswordField();
        
        HBox userRow = new HBox(10);
        userRow.getChildren().addAll(userLabel,username);
        HBox passRow = new HBox(10);
        passRow.getChildren().addAll(passLabel,password);
        HBox buttonLayout = new HBox(50);
        Button loginBtn = new Button("Login");
        Button backBtn = new Button("Return to main menu");
        backBtn.setOnAction(e -> window.setScene(s));
        loginBtn.setOnAction(e -> {
            String user = username.getText();
            String pass = password.getText();
            try{ 
                if(user.length()==0 || pass.length()==0){
                    //ให้ขึ้นข้อความว่าใส่ข้อมูลให้ครบทุกช่อง
                    System.out.println("user or pass has emtpy field");
                }
                else{
                    ResultSet res = DataQuery.queryLogin(user);
                    while(res.next()){
                        if(pass.equals(res.getString("password"))&&(res!=null)){
                            //เหลือเปลี่ยนไปที่หน้า DormMain
                            System.out.println("goto DormMain Page");
                        }
                        else{
                            System.out.println("Incorect Username or Password");
                        //ให้ขึ้นข้อความว่า Incorrect Username or Password
                        }
                    }
                }
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        });
        buttonLayout.getChildren().addAll(loginBtn,backBtn);
        FlowPane layout = new FlowPane(10,10);
        layout.setAlignment(Pos.CENTER);
        layout.setOrientation(Orientation.VERTICAL);
        layout.getChildren().addAll(label,userRow,passRow,buttonLayout);
        Scene scene = new Scene(layout,640,480);
        return scene;
    }
    
}
