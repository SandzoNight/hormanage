/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import model.DataCount;
import model.DataInsert;
import model.DataQuery;
import model.DataUpdate;

/**
 *
 * @author User
 */
public class InvoiceManage {
    public static ResultSet getUnpaidInvoice(long dormId){
        ResultSet res1 = DataQuery.queryUnpaidInvoice(dormId);
        return res1;
    }
    
    public static ResultSet getAllInvoice(long dormId){
        ResultSet res1 = DataQuery.queryAllInvoice(dormId);
        return res1;
    }
    
    public static ResultSet getInvoiceDetail(long invoiceId){
        ResultSet res1 = DataQuery.queryInvoiceDetail(invoiceId);
        return res1;
    }
    
    public static double priceCalculator(double[] rate, double[] unit, double room){
        double totalPrice = (rate[0]*unit[0])+(rate[1]*unit[1])+room;
        return totalPrice;
    }
    
    public static double getTotalPrice(double water, double elec, double room){
        double totalPrice = water+elec+room;
        return totalPrice;
    }
    
    
    
    public static int countInvoice(long dormId){
        int count = DataCount.count("invoice", "Dormitory_dormId", dormId+"");
        return count;
    }
    
//    public static ResultSet RenterNotPaidInfo(String invoiceId){
//        ResultSet res1 = DataQuery.QueryNotPaidInvoiceInfo(invoiceId);
//        return res1;
//    }
    
    public static int addInvoice(LocalDate startDate, LocalDate dueDate, ArrayList<String> data, long dormId){
        long nextInvoiceId = 0;
        ResultSet res = DataQuery.query("nextrecordId");
        try{
            while(res.next()){
                nextInvoiceId = res.getLong("nextInvoiceId");
            }
            DataQuery.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        DataInsert di = new DataInsert();
        int inserted = di.insertInvoice(nextInvoiceId,startDate,dueDate,data,dormId);
        di.disconnect();
        return inserted;
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
    
    public static void cancel(long invoiceId){
        System.out.println("Canceling invoice..");
        DataUpdate.invoiceCancel(invoiceId);
    }
    
    public static void paid(long invoiceId){
        System.out.println("Updating invoice..");
        DataUpdate.invoicePaid(invoiceId);
    }
}
