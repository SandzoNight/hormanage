/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 *
 * @author CBC
 */
public class detailHorController extends Application {

    @Override
    public void start(Stage stage) {

        Text nameLabel = new Text("ชื่อหอ");

        TextField nameText = new TextField();

        Text typeLabel = new Text("ชนิดของหอ");
        ChoiceBox typechoiceBox = new ChoiceBox();
        typechoiceBox.getItems().addAll("หอชาย", "หอหญิง", "หอรวม");

        Text addressLabel = new Text("ที่อยู่");
        TextField addressText = new TextField();

        Text countRoomLabel = new Text("จำนวนห้อง");
        TextField countRoomText = new TextField();

        Text technologiesLabel = new Text("Technologies Known");

        CheckBox javaCheckBox = new CheckBox("Java");
        javaCheckBox.setIndeterminate(false);

        CheckBox dotnetCheckBox = new CheckBox("DotNet");
        javaCheckBox.setIndeterminate(false);

        Text facilityDormLabel = new Text("สิ่งอำนวยความสะดวกในหอ");

        ObservableList<String> dorm = FXCollections.observableArrayList(
                "Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
        ListView<String> facilityDormView = new ListView<>(dorm);
        
        Text facilityRoomLabel = new Text("สิ่งอำนวยความสะดวกในหอ");

        ObservableList<String> room = FXCollections.observableArrayList(
                "Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
        ListView<String> facilityRoomView = new ListView<>(room);

        Button buttonRegister = new Button("Register");

        GridPane gridPane = new GridPane();

        gridPane.setMinSize(500, 500);

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameText, 1, 0);

        gridPane.add(typeLabel, 0, 1);
        gridPane.add(typechoiceBox, 1, 1);
        
        gridPane.add(addressLabel, 0, 2);
        gridPane.add(addressText, 1, 2);
      
        gridPane.add(countRoomLabel, 0, 3);
        gridPane.add(countRoomText, 1, 3);
        
        gridPane.add(facilityDormLabel, 0, 4) ; 
        gridPane.add(facilityDormView, 1, 4) ;   

        
        gridPane.add(facilityRoomLabel, 0, 5) ; 
        gridPane.add(facilityRoomView, 1, 5) ;   

        gridPane.add(buttonRegister, 2, 8);

        buttonRegister.setStyle(
                "-fx-background-color: darkslateblue; -fx-textfill: white;");

        nameLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        typeLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        addressLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        countRoomLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        facilityDormLabel.setStyle("-fx-font: normal bold 15px 'serif' ") ; 
        facilityRoomLabel.setStyle("-fx-font: normal bold 15px 'serif' ") ; 

        gridPane.setStyle("-fx-background-color: BEIGE;");

        Scene scene = new Scene(gridPane);

        stage.setTitle("Registration Form");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
