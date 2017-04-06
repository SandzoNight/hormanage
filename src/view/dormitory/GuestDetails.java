/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dormitory;

import javafx.application.Application;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author CBC
 */
public class GuestDetails extends Application {

    private TableView table = new TableView();

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");

        Label label = new Label("รายชื่อผู้เข้าพัก");
        

        ChoiceBox cb = new ChoiceBox();
        cb.getItems().addAll("ทั้งหมด", "เลขที่ใบแจ้งหนี้", "วันที่ออกใบ", "วันครบกำหนด", "เลขที่ห้อง", "ชื่อผู้เช่าห้อง", "ค่าห้อง", "ค่าน้ำ", "ค่าไฟ", "ค่าปรับ");
        
        TextField sc = new TextField("ค้นหา..."); 
        
        Button bt = new Button("ค้นหา");
        
        table.setEditable(true);

        TableColumn noCol = new TableColumn("เลขที่ห้อง");
        TableColumn floorCol = new TableColumn("ชั้น");
        TableColumn typeRoomCol = new TableColumn("ประเภทห้อง");
        TableColumn nameandsurnameCol = new TableColumn("ชื่อ-นามสกุล");
        TableColumn telNumberCal = new TableColumn("เบอร์โทรศัพท์");

        table.getColumns().addAll(noCol, floorCol, typeRoomCol, nameandsurnameCol, telNumberCal);
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        
        gridPane.setMinSize(500, 500);
        
        gridPane.add(label ,0,0);
        
        gridPane.add(cb ,0,2);
        gridPane.add(sc ,1,2);
        gridPane.add(bt ,2,2);
        
     
        
        gridPane.add(table ,0,4);

        Scene gp = new Scene(gridPane);

        Stage thisstage = new Stage();
        thisstage.setScene(gp);
        thisstage.show();
    }
}
