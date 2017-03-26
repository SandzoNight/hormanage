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
import javafx.scene.Node;
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
import javafx.scene.layout.VBox;
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

        Text facilityDormLabel = new Text("สิ่งอำนวยความสะดวกในหอ");

        CheckBox javaCheckBox1 = new CheckBox("Security");
        javaCheckBox1.setIndeterminate(false);
        CheckBox box2 = new CheckBox("Car Park");
        javaCheckBox1.setIndeterminate(false);
        CheckBox box3 = new CheckBox("Swimming Pool");
        javaCheckBox1.setIndeterminate(false);
        CheckBox box4 = new CheckBox("Park");
        javaCheckBox1.setIndeterminate(false);
        
        Text facilityRoomLabel = new Text("สิ่งอำนวยความสะดวกในห้อง");

        CheckBox javaCheckBox2 = new CheckBox("Furniture");
       javaCheckBox2.setIndeterminate(false);
        CheckBox box5 = new CheckBox("Air Condition");
        javaCheckBox2.setIndeterminate(false);
        CheckBox box6 = new CheckBox("Fan");
        javaCheckBox2.setIndeterminate(false);
        CheckBox box7 = new CheckBox("Table");
        javaCheckBox2.setIndeterminate(false);
         CheckBox box8 = new CheckBox("Chair");
        javaCheckBox2.setIndeterminate(false);




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

        gridPane.add(facilityDormLabel, 0, 3);
        
        gridPane.add(javaCheckBox1, 0, 4);
        gridPane.add(box2, 1, 4);
        gridPane.add(box3, 2, 4);
        gridPane.add(box4, 3, 4);
        
        gridPane.add(facilityRoomLabel, 0, 5);
        
        gridPane.add(javaCheckBox2, 0, 6);
        gridPane.add(box5, 1, 6);
        gridPane.add(box6, 2, 6);
        gridPane.add(box7, 3, 6);
        gridPane.add(box8, 4, 6);


        gridPane.add(countRoomLabel, 0, 7);
        gridPane.add(countRoomText, 1, 7);
        
        gridPane.add(buttonRegister, 0, 8);

        buttonRegister.setStyle(
                "-fx-background-color: white; -fx-textfill: white;");

        nameLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        typeLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        addressLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        countRoomLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
        facilityDormLabel.setStyle("-fx-font: normal bold 15px 'serif' ");

        
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
