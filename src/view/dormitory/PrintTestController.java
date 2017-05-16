/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dormitory;

import java.net.URL;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Pacharapol
 */
public class PrintTestController implements Initializable {

    @FXML
    private Hyperlink home;
    @FXML
    private Button printBtn;
    @FXML
    private Pane invoiceInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void printInvoice(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        JobSettings js = job.getJobSettings();
        js.setJobName("Hor Manage Invoice");
        PageLayout pl = js.getPageLayout();
        Printer p = job.getPrinter();
        pl = p.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 1,1,1.5,1.5);
        js.setPageLayout(pl);
        if(job != null && job.showPrintDialog(invoiceInfo.getScene().getWindow())){
            boolean success = job.printPage(invoiceInfo);
            if (success) {
                job.endJob();
            }
        }
                
    }
    
}
