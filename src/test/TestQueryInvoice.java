/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.InvoiceManage;
import java.sql.*;
import model.*;

/**
 *
 * @author User
 */
public class TestQueryInvoice {
    public static void main(String[] args){
        ResultSet res1 = InvoiceManage.getUnpaidInvoice(1);
            try{
                while(res1.next()){
                System.out.println(res1.getString("Room_roomId"));
                //ResultSet res2 = DataQuery.query("renter","renterId",res1.getString("Renter_renterId"));
                System.out.println(res1.getString("renterFirstName"));
                System.out.println(res1.getString("renterLastName"));
                double totalPrice = PriceCalculator(res1.getFloat("waterTotalPrice"),res1.getFloat("elecTotalPrice"),res1.getFloat("roomPrice"));
                System.out.println(totalPrice);
                }
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
    }
     public static double PriceCalculator(double water, double elec, double room){
        double totalPrice = water+elec+room;
        return totalPrice;
    }
}
