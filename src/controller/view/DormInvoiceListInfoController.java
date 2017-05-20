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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Label infoElecL;
    @FXML
    private Label infoElecUL;
    @FXML
    private Label infoRoomL;
    @FXML
    private Label infoTotalL;
    
    private static long invoiceId;
    @FXML
    private Button infoPrintL;
    @FXML
    private Label WaterL;
    @FXML
    private Label WaterUL;
    @FXML
    private Label statusLabel;
    @FXML
    private Button paidBtn;
    @FXML
    private VBox printArea;
    
    private static Stage window;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet inv = InvoiceManage.getInvoiceDetail(invoiceId);
        try{
            while(inv.next()){
                infoIdL.setText("INV-"+inv.getString("invoiceNo"));
                infoNameL.setText(inv.getString("renterFirstName")+" "+inv.getString("renterLastName"));
                infoNoL.setText(inv.getString("roomNo"));
                if(inv.getString("paidStatus").equals("0")){
                    statusLabel.setText("ยังไม่จ่าย");
                }else{
                    statusLabel.setText("จ่ายแล้ว");
                    paidBtn.setDisable(true);
                }
                WaterL.setText(inv.getString("waterUsage"));
                infoElecL.setText(inv.getString("elecUsage"));
                WaterUL.setText(inv.getString("dormWaterRate"));
                infoElecUL.setText(inv.getString("dormElecRate"));
                infoRoomL.setText(inv.getString("roomPrice"));
                infoTotalL.setText(""+(inv.getDouble("roomPrice")+inv.getDouble("waterTotalPrice")+inv.getDouble("elecTotalPrice")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static long getInvoiceId() {
        return invoiceId;
    }

    public static void setInvoiceId(long invoiceId) {
        DormInvoiceListInfoController.invoiceId = invoiceId;
    }

    @FXML
    private void printInv(ActionEvent event) {
        InvoiceManage.printInvoice(printArea);
    }

    @FXML
    private void cancelInv(ActionEvent event) {
        InvoiceManage.cancel(invoiceId);
        window.close();
    }

    @FXML
    private void invoicePaid(ActionEvent event) {
        InvoiceManage.paid(invoiceId);
        window.close();
        
    }
    
    public static void setNode(Stage node){
        DormInvoiceListInfoController.window = node;
    }
    
    
    
}
