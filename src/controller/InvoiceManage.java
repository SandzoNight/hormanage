/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
