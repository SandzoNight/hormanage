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
public class DataInsert extends DBConnector{
    static String calling_str,inserting_str,disconnect_str;
    
    PreparedStatement ps;
    public DataInsert(){
        calling_str = "[DataInsert]Calling DBConnector to connect the database";
        inserting_str = "[DataInsert]Inserting data to table";
        disconnect_str = "[DataInsert]Disconnect from DB";
        System.out.println(calling_str);
        connect();
    }
    
    public void insert(String tableName, String columnNames, String values){
//        String sql = "INSERT INTO "+tableName+" ("+columnNames+") VALUES (?)";
        String sql = "INSERT INTO "+tableName+" ("+columnNames+") VALUES ("+values+")";
        try{
            System.out.println(inserting_str);
            ps = connection.prepareStatement(sql);
//            ps.setString(1, values);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    public void insert(String tableName, String values){
//        String sql = "INSERT INTO "+tableName+" VALUES (?)";
        String sql = "INSERT INTO "+tableName+" VALUES ("+values+")";
        try{
            System.out.println(inserting_str);
            ps = connection.prepareStatement(sql);
//            ps.setString(1, values);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    public void insertRoom(int nextRoomId, String dormId, String roomNo, String userId, String chargeId){
        try{
            System.out.println(inserting_str);
            String sql = "INSERT INTO room VALUES (?,?,?,?,?)";            
            ps = connection.prepareStatement(sql);
            ps.setInt(1, nextRoomId);
            ps.setString(2, roomNo);
            ps.setString(3, dormId);
            ps.setString(4, userId);
            ps.setString(5, chargeId);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public static void disconnect(){
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
}
