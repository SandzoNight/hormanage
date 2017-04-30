/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.InvoiceManage;
import static controller.InvoiceManage.PriceCalculator;
import controller.RenterManage;
import static controller.view.DormDashboardController.dormId;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DormInvoiceListController implements Initializable {


    @FXML
    private GridPane listNotPaid;
    
    private Label[] roomNo;
    private Label[] renterName;
    private Label[] renterSurname;
    private Label[] totalPrice;
    @FXML
    private Hyperlink home;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("dfgbhbjk,jb");
        listNotPaid.getChildren().clear();

        int totalRenter = InvoiceManage.countInvoice(dormId);
        roomNo = new Label[totalRenter];
        renterName = new Label[totalRenter];
        renterSurname = new Label[totalRenter];
        totalPrice = new Label[totalRenter];
        
        ResultSet res = InvoiceManage.RenterNotPaid("1");
        int index = 0;

        try{
            while(res.next()){
                //System.out.println("ytfugihjkl;jhkgftrytufgyihjok");
                //System.out.println(totalRenter);
                System.out.println(res.getString("Room_roomId"));
                roomNo[index].setText(res.getString("Room_roomId"));
                renterName[index].setText(res.getString("reterFirstName"));
                renterSurname[index].setText(res.getString("reterLastName"));
                double total = PriceCalculator(res.getFloat("waterTotalPrice"),res.getFloat("elecTotalPrice"),res.getFloat("roomPrice"));
                totalPrice[index].setText(res.getString(total+""));
                
                listNotPaid.add(roomNo[index],0,index);
                listNotPaid.add(renterName[index],1,index);
                listNotPaid.add(renterSurname[index],2,index);
                listNotPaid.add(totalPrice[index],3,index);
                index++;
            }
        }    
        catch(SQLException sqle){
            sqle.printStackTrace();
        }   
    }    
    
}
