/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view;

import controller.ContractManage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import controller.ContractManage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import model.DataQuery;

/**
 * FXML Controller class
 *
 * @author CBC
 */
public class DormViewContractController extends DormDashboardController implements Initializable {

    @FXML
    private Label noContract;
    @FXML
    private Label startDate;
    @FXML
    private Label Date;
    @FXML
    private Label address;
    @FXML
    private Label address2;
    @FXML
    private Label address21;
    @FXML
    private Label address3;
    @FXML
    private ImageView print;
    @FXML
    private Button printBtn;
    @FXML
    private Pane printArea;
    
    private static long currentContractNo = 0;
    private LocalDate currentTime;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = new Date();
        currentTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");
        Date.setText(sdf.format(date));
        
        ResultSet contractRes = DataQuery.queryContract(dormId);
        try{
            while(contractRes.next()){
                currentContractNo = contractRes.getLong("contractNo")+1;
            }
            if(currentContractNo == 0){
                currentContractNo = 1;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        noContract.setText("CON-"+currentContractNo);
    }    

    @FXML
    private void print(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        JobSettings js = job.getJobSettings();
        js.setJobName("Hor Manage Invoice");
        PageLayout pl = js.getPageLayout();
        Printer p = job.getPrinter();
        pl = p.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 1,1,1.5,1.5);
        js.setPageLayout(pl);
        if(job != null && job.showPrintDialog(printArea.getScene().getWindow())){
            boolean success = job.printPage(printArea);
            if (success) {
                System.out.println("End Print Job");
                ContractManage.create(currentContractNo,currentTime,dormId);
                job.endJob();
            }
        }
    }
    
}
