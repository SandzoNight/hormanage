/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fluke
 */
public class DataInsert extends DBConnector{
    static String calling_str,inserting_str,disconnect_str;
    
    Statement s;
    public DataInsert(){
        calling_str = "[DataInsert] Calling DBConnector to connect the database";
        inserting_str = "[DataInsert] Inserting data to table";
        disconnect_str = "[DataInsert] Disconnect from DB";
        System.out.println(calling_str);
        connect();
    }
    
    public void insert(String tableName, String columnNames, String values){
        String sql = "INSERT INTO "+tableName+" ("+columnNames+") VALUES ("+values+")";
        try{
            System.out.println(inserting_str);
            System.out.println("Inserting record to '"+tableName+"'");
            s = connection.createStatement();
            s.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    public void insert(String tableName, String values){
        String sql = "INSERT INTO "+tableName+" VALUES ("+values+")";
        try{
            System.out.println(inserting_str);
            System.out.println("Inserting record to '"+tableName+"'");
            s = connection.createStatement();
            s.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    
    public static void disconnect(){
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
}
