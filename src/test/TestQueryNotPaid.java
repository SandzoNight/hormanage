/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataQuery;

/**
 *
 * @author User
 */
public class TestQueryNotPaid {
    public static void main(String[] args) throws SQLException{
        ResultSet res1 = DataQuery.QueryNotPaidInvoice("1");
        while(res1.next()){
            System.out.println(res1.getString("Room_roomId"));
            System.out.println(res1.getString("Renter_renterId"));
            
        }
        DataQuery.disconnect();
    }
}
