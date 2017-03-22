/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataCount;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class TestCount {
    public static void main(String[] args) throws SQLException{
//        int count = dc.count("dormitory","dormName","Cosmo' OR '1=1"); //SQL Injection Statement
        int count = DataCount.count("dormitory","dormName","Hornai");
        int count1 = DataCount.count("user");
//        int count = dc.count("dormitory");
        System.out.println("######## TestCount ########");
        System.out.println(count);
        System.out.println(count1);
        DataCount.disconnect();
    }
}
