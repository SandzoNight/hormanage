/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.InvoiceManage;
import controller.RenterManage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
    @FXML
    private GridPane listAll;
    @FXML
    private Button gotoAddInvoice;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listNotPaid.getChildren().clear();
        listAll.getChildren().clear();
        ResultSet res = InvoiceManage.getUnpaidInvoice(dormId);
        int index = 0;
        ArrayList<Label> InvoiceNo = new ArrayList<Label>();
        ArrayList<Label> roomNo = new ArrayList<Label>();
        ArrayList<Label> renterFirstName = new ArrayList<Label>();
        ArrayList<Label> renterLastName = new ArrayList<Label>();
        ArrayList<Label> totalPrice = new ArrayList<Label>();
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
                double total = InvoiceManage.priceCalculator(res.getFloat("waterTotalPrice"),res.getFloat("elecTotalPrice"),res.getFloat("roomPrice"));
                Label totalPriceLabel = new Label(total+"");
                totalPrice.add(totalPriceLabel);
                Button infoButtonB = new Button();
                infoButtonB.setId(res.getInt("InvoiceId")+""); 
                infoButtonB.setText(" >> ");
                infoButtonB.setOnAction(e -> {
                    gotoInvoiceInfoPage(e);
                });
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
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        
        //LIST ALL
        ResultSet res2 = InvoiceManage.getAllInvoice(dormId);
        int index2 = 0;
        ArrayList<Label> InvoiceNo2 = new ArrayList<Label>();
        ArrayList<Label> roomNo2 = new ArrayList<Label>();
        ArrayList<Label> renterFirstName2 = new ArrayList<Label>();
        ArrayList<Label> renterLastName2 = new ArrayList<Label>();
        ArrayList<Label> totalPrice2 = new ArrayList<Label>();
        ArrayList<Button> infoButton2 = new ArrayList<Button>();
        try{
            while(res2.next()){
                Label InvoiceNoLabel = new Label(res2.getString("InvoiceNo"));
                InvoiceNo2.add(InvoiceNoLabel);
                Label roomNoLabel = new Label(res2.getString("Room_roomId"));
                roomNo2.add(roomNoLabel);
                Label renterFirstNameLabel = new Label(res2.getString("renterFirstName"));
                renterFirstName2.add(renterFirstNameLabel);
                Label renterLastNameLabel = new Label(res2.getString("renterLastName"));
                renterLastName2.add(renterLastNameLabel);
                double total = InvoiceManage.priceCalculator(res2.getFloat("waterTotalPrice"),res2.getFloat("elecTotalPrice"),res2.getFloat("roomPrice"));
                Label totalPriceLabel = new Label(total+"");
                totalPrice2.add(totalPriceLabel);
                Button infoButtonB = new Button();
                infoButtonB.setId(res2.getInt("InvoiceId")+""); 
                infoButtonB.setText(" >> ");
                infoButtonB.setOnAction(e -> {
                    gotoInvoiceInfoPage(e);
                });
                infoButtonB.setMaxWidth(50);
                infoButtonB.setMaxHeight(200);
                infoButton2.add(infoButtonB);
                listAll.add(InvoiceNo2.get(index2),0,index2);
                listAll.add(roomNo2.get(index2),1,index2);
                listAll.add(renterFirstName2.get(index2),2,index2);
                listAll.add(renterLastName2.get(index2),3,index2);
                listAll.add(totalPrice2.get(index2),4,index2);
                listAll.add(infoButton2.get(index2),5,index2);
                index2++;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
    private void gotoInvoiceInfoPage(ActionEvent e){
        long invoiceId = Long.parseLong(((Button)e.getSource()).getId());
        try{
            FXMLLoader loader = new FXMLLoader();
            DormInvoiceListInfoController.setInvoiceId(invoiceId);
            
            //Prepare new page
            System.out.println("[DormInvoiceListController]Loading new page..");
            root = loader.load(getClass().getResource("/view/dormitory/DormInvoiceListInfo.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            System.out.println("[DormInvoiceListController]Changing scene..");
            window.setScene(scene);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void gotoAddInvoicePage(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            
            //Prepare new page
            System.out.println("[DormInvoiceListController]Loading new page..");
            root = loader.load(getClass().getResource("/view/dormitory/DormInvoiceAdd.fxml").openStream());
            Scene scene = new Scene(root);

            //Change to new page
            System.out.println("[DormInvoiceListController]Changing scene..");
            window.setScene(scene);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}
