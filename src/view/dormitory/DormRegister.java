package view.dormitory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DormRegister extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("DormRegister.fxml"));
           
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
     
    }

    public static void main(String[] args) {
        launch(args);
    }

}
