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
 * @author fluke
 */
public class TestQuery {
    public static void main(String[] args) throws SQLException{
        ResultSet res1 = DataQuery.query("dormitory","dormId","3");
        System.out.println("######## TestQuery ########");
        while(res1.next()){
            System.out.println(res1.getString("dormName"));
        }
        ResultSet res2 = DataQuery.query("user");
        while(res2.next()){
            System.out.println(res2.getString("userEmail"));
        }
        DataQuery.disconnect();
    }
}
