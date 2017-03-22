/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fluke
 */
public class DataQuery extends DBConnector{
    static String calling_str,querying_str,queried_str,error_str,disconnect_str;
    private ResultSet rec = null;
    public ResultSet query(String tableName){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        querying_str = "[DataQuery]Querying from "+tableName;
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        String sql = "SELECT * FROM "+tableName;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            System.out.println(querying_str);
            rec = ps.executeQuery();
//            while(rec.next()){
//                System.out.println(rec.getString("dormId"));
//            }
            System.out.println(queried_str);
//            System.out.println("[DataQuery]Disconnect from DB");
//            disconnect();
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
        return rec;
    }
    
    public static ResultSet query(String tableName,String columnName,String columnValue){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
//        querying = "[DataQuery]Querying from "+tableName+" Where "+columnName+"="+columnValue;
        querying_str = "[DataQuery]Querying from "+tableName;
        String sql = "SELECT * FROM "+tableName+" WHERE "+columnName+"='"+columnValue+"'";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            System.out.println(querying_str);
            rec = ps.executeQuery();
            System.out.println(queried_str);
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
        return rec;
    }
    
    public static void disconnect(){
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
}
