/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.DataQuery;
import view.home.HorProject;

/**
 * FXML Controller class
 *
 * @author fluke
 */
public class LoginController extends HorProject implements Initializable {

    @FXML
    private Button loginBtn;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private VBox loginPage;
    @FXML
    private Label errorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void login(ActionEvent event){
        try{
            if(emailField.getText().length()==0 || passwordField.getText().length()==0){
                errorMessage.setText("You can't enter an emtpy field");           
            }else{
                System.out.println("Logging in..");
                ResultSet res = DataQuery.queryLogin(emailField.getText());                
                res.last();
                int nResult = res.getRow();
                if(nResult!=0){
                    res.beforeFirst();
                    while(res.next()){
                        if(passwordField.getText().equals(res.getString("userPassword"))){
                            String userId = res.getString("userId");
                            errorMessage.setTextFill(Color.GREEN);
                            System.out.println("Login Successfully !!");
                            errorMessage.setText("Login Sucessfully !!"); 
                            errorMessage.setTextFill(Color.RED);
                            
                            //Prepare needed parameters for the new page
                            FXMLLoader loader = new FXMLLoader();
//                            DormMainController dormMainController = (DormMainController)loader.getController();
                            DormMainController.setUserId(userId);
                            
                            //Prepare new page
                            root = loader.load(getClass().getResource("/view/dormitory/DormMain.fxml").openStream());
                            Scene scene = new Scene(root);
                            
                            //Change to new page
                            window.setScene(scene);
                        }else{
                            errorMessage.setText("Incorrect username or password");
                            passwordField.clear();
                        }
                    }
                }else{
                    
                    errorMessage.setText("Incorrect username or password");
                    passwordField.clear();
                }
            }
        }catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
