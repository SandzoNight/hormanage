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
public class TestQueryLogin {
    public static void main(String[] args) throws SQLException{
        ResultSet res1 = DataQuery.queryLogin("123456789");
        System.out.println("######## TestQueryLogin ########");
        while(res1.next()){
            System.out.println(res1.getString("password"));
        }
        DataQuery.disconnect();
    }
}
