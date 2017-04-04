/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.home;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.DataQuery;
import view.dormitory.DormSelectDorm;
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
        Text textShow = new Text();
        textShow.setFill(Color.BLACK);
        textShow.setFont(Font.font ("Verdana", 16));
        textShow.setText("Please enter username and password");       
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
                    textShow.setFill(Color.RED);
                    textShow.setText("You can't enter an emtpy field");           
                }else{
                    ResultSet res = DataQuery.queryLogin(user);
                    res.last();
                    int nResult = res.getRow();
                    if(nResult!=0){
                        res.beforeFirst();
                        while(res.next()){
                            if(pass.equals(res.getString("password"))){
                                textShow.setFill(Color.GREEN);
                                textShow.setText("Login Sucessfully !!");
                                window.setScene(DormSelectDorm.open(user));
                            }
                        }
                    }else{
                        textShow.setFill(Color.RED);
                        textShow.setText("Incorrect username or password");
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
        layout.getChildren().addAll(label,userRow,passRow,buttonLayout,textShow);
        Scene scene = new Scene(layout,640,480);
        return scene;
    }
}
