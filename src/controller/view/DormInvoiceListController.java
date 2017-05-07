/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.InvoiceManage;
import static controller.InvoiceManage.PriceCalculator;
import controller.RenterManage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DormInvoiceListController extends DormDashboardController implements Initializable {


    @FXML
    private GridPane listNotPaid;
    
    private Label roomNo;
    private Label renterName;
    private Label renterSurname;
    private Label totalPrice;
    private Button infoButton;
    @FXML
    private Hyperlink home;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("dfgbhbjk,jb");
        listNotPaid.getChildren().clear();            
        /*int totalRenter = InvoiceManage.countInvoice(dormId);
        roomNo = new Label[totalRenter];
        renterName = new Label[totalRenter];
        renterSurname = new Label[totalRenter];
        totalPrice = new Label[totalRenter];
        */
        ResultSet res = InvoiceManage.RenterNotPaid(1+"");
        int index = 0;
<<<<<<< HEAD

=======
        
>>>>>>> 44e5cb52e42295ab97e4c3eabcc0ffc8405f09f0
        ArrayList<Label> InvoiceNo = new ArrayList<Label>();
        ArrayList<Label> roomNo = new ArrayList<Label>();
        ArrayList<Label> renterFirstName = new ArrayList<Label>();
        ArrayList<Label> renterLastName = new ArrayList<Label>();
        ArrayList<Label> totalPrice = new ArrayList<Label>();
<<<<<<< HEAD
        ArrayList<Button> infoButton = new ArrayList<Button>();
        
        
        try{
            while(res.next()){
                
                
                Label InvoiceNoLabel = new Label(res.getString("InvoiceNo"));
                InvoiceNo.add(InvoiceNoLabel);
                
                
                Label roomNoLabel = new Label(res.getString("Room_roomId"));
                roomNo.add(roomNoLabel);
                
                
                Label renterFirstNameLabel = new Label(res.getString("renterFirstName"));
                renterFirstName.add(renterFirstNameLabel);
                
                
                Label renterLastNameLabel = new Label(res.getString("renterLastName"));
                renterLastName.add(renterLastNameLabel);
                
                
=======
        
        try{
            while(res.next()){
                Label InvoiceNoLabel = new Label(res.getString("InvoiceNo"));
                InvoiceNo.add(InvoiceNoLabel);
                Label roomNoLabel = new Label(res.getString("Room_roomId"));
                roomNo.add(roomNoLabel);
                Label renterFirstNameLabel = new Label(res.getString("renterFirstName"));
                renterFirstName.add(renterFirstNameLabel);
                Label renterLastNameLabel = new Label(res.getString("renterLastName"));
                renterLastName.add(renterLastNameLabel);
>>>>>>> 44e5cb52e42295ab97e4c3eabcc0ffc8405f09f0
                double total = PriceCalculator(res.getFloat("waterTotalPrice"),res.getFloat("elecTotalPrice"),res.getFloat("roomPrice"));
                Label totalPriceLabel = new Label(total+"");
                totalPrice.add(totalPriceLabel);
                
                Button infoButtonB = new Button();
                infoButtonB.setMaxWidth(50);
                infoButtonB.setMaxHeight(200);
                infoButton.add(infoButtonB);

                
                listNotPaid.add(InvoiceNo.get(index),0,index);
                listNotPaid.add(roomNo.get(index),1,index);
                listNotPaid.add(renterFirstName.get(index),2,index);
                listNotPaid.add(renterLastName.get(index),3,index);
                listNotPaid.add(totalPrice.get(index),4,index);
                listNotPaid.add(infoButton.get(index),5,index);
                index++;
              

            }
        }    
        catch(SQLException sqle){
            sqle.printStackTrace();
        }   
    }    
    
}
