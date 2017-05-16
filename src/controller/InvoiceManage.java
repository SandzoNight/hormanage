/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import model.DataCount;
import model.DataQuery;

/**
 *
 * @author User
 */
public class InvoiceManage {
    public static ResultSet RenterNotPaid(String dormId){
        ResultSet res1 = DataQuery.QueryNotPaidInvoice(dormId);
        return res1;
    }
    
    public static double PriceCalculator(double water, double elec, double room){
        double totalPrice = water+elec+room;
        return totalPrice;
    }
    
    public static int countInvoice(long dormId){
        int count = DataCount.count("invoice", "Dormitory_dormId", dormId+"");
        return count;
    }
    
    public static ResultSet RenterNotPaidInfo(String invoiceId){
        ResultSet res1 = DataQuery.QueryNotPaidInvoiceInfo(invoiceId);
        return res1;
    }
    
    public static void printInvoice(Node node){
        PrinterJob job = PrinterJob.createPrinterJob();
        JobSettings js = job.getJobSettings();
        js.setJobName("Hor Manage Invoice");
        PageLayout pl = js.getPageLayout();
        Printer p = job.getPrinter();
        pl = p.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 1,1,1.5,1.5);
        js.setPageLayout(pl);
        if(job != null && job.showPrintDialog(node.getScene().getWindow())){
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
    }
}
