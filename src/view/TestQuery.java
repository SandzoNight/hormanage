/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class TestQuery {
    public static void main(String[] args) throws SQLException{
        DataQuery dq = new DataQuery();
        ResultSet res = dq.query("dormitory");
        System.out.println("######## TestQuery ########");
        while(res.next()){
            System.out.println(res.getString("dormId"));
        }
    }
}
