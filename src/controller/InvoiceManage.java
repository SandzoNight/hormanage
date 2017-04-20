/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataQuery;

/**
 *
 * @author User
 */
public class InvoiceManage {
    public static ResultSet RenterNotPaid(String dormId){
        ResultSet res1 = DataQuery.QueryNotPaidInvoice(dormId);
        try{
            while(res1.next()){
                System.out.println(res1.getString("Room_roomId"));
                ResultSet res2 = DataQuery.query("renter","renterId",res1.getString("Renter_renterId"));
                while(res2.next()){
                    System.out.println(res2.getString("renterFirstName"));
                    System.out.println(res2.getString("renterLastName"));
                }
                double totalPrice = PriceCalculator(res1.getFloat("waterTotalPrice"),res1.getFloat("elecTotalPrice"),res1.getFloat("roomPrice"));
                System.out.println(totalPrice);
            }
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return res1;
    }
    
    public static double PriceCalculator(double water, double elec, double room){
        double totalPrice = water+elec+room;
        return totalPrice;
    }
}
