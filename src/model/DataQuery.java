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
    String calling,querying,queried;
    private ResultSet rec = null;
    public ResultSet query(String tableName){
        calling = "[DataQuery]Calling DBConnector to connect the database";
        querying = "[DataQuery]Querying from "+tableName;
        queried = "[DataQuery]Querying successful!";
        String sql = "SELECT * FROM "+tableName;
        try{
            System.out.println(calling);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            System.out.println(querying);
            rec = ps.executeQuery(sql);
//            while(rec.next()){
//                System.out.println(rec.getString("dormId"));
//            }
            System.out.println(queried);
            System.out.println("[DataQuery]Disconnect from DB");
            disconnect();
        }catch(SQLException e){
            System.out.println("[DataQuery]Disconnect from DB");
            disconnect();
            e.printStackTrace();
        }
        return rec;
    }
    
    public ResultSet query(String tableName,String columnName,String columnValue){
        calling = "[DataQuery]Calling DBConnector to connect the database";
//        querying = "[DataQuery]Querying from "+tableName+" Where "+columnName+"="+columnValue;
        querying = "[DataQuery]Querying from "+tableName;
        String sql = "SELECT * FROM "+tableName+" WHERE "+columnName+"='"+columnValue+"'";
        ResultSet rec = null;
        try{
            System.out.println(calling);
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            System.out.println(querying);
            rec = ps.executeQuery(sql);
            System.out.println(queried);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("[DataQuery]Disconnect from DB");
            disconnect();
        }
        return rec;
    }
}
