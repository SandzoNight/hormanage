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
        DataCount dc = new DataCount();
        int count = dc.count("dormitory","dormName","Hornai");
//        int count = dc.count("dormitory");
        System.out.println("######## TestQuery ########");
        System.out.println(count);
    }
}
