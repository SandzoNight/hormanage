/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.InvoiceManage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DormInvoiceListInfoController extends DormInvoiceListController implements Initializable {
    @FXML
    private Label infoIdL;
    @FXML
    private Label infoNoL;
    @FXML
    private Label infoNameL;
    @FXML
    private Label infoWaterL;
    @FXML
    private Label infoElecL;
    @FXML
    private Label infoWaterUL;
    @FXML
    private Label infoElecUL;
    @FXML
    private Label infoRoomL;
    @FXML
    private Label infoTotalL;
    
    private static long invoiceId;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ResultSet res = InvoiceManage.RenterNotPaidInfo(1+""); 
//        try{
//            while(res.next()){
//                infoIdL.setText(res.getString("invoiceId"));
//                infoNoL.setText(res.getString("Room_roomId"));
//                infoNameL.setText(res.getString("renterFirstName") + "              " + res.getString("renterLastName"));
//                infoWaterL.setText(res.getString("waterUsage"));
//                infoElecL.setText(res.getString("elecUsage"));
//                infoWaterUL.setText(res.getString("dormWaterRate"));
//                infoElecUL.setText(res.getString("dormElecRate"));
//                infoRoomL.setText(res.getString("roomPrice"));
//                double total = PriceCalculator(res.getFloat("waterTotalPrice"),res.getFloat("elecTotalPrice"),res.getFloat("roomPrice"));
//                infoTotalL.setText(total+"");  
//            }
//        }
//        catch(SQLException sqle){
//            sqle.getStackTrace();
//        }
    }

    public static long getInvoiceId() {
        return invoiceId;
    }

    public static void setInvoiceId(long invoiceId) {
        DormInvoiceListInfoController.invoiceId = invoiceId;
    }
    
    
    
}
