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
public abstract class DataUpdate extends DBConnector{
    static String calling_str,updating_str,updated_str,error_str,disconnect_str;
    private static int updated = 0;
    public static int updateRoomDetail(String roomNo, long userId, int chargeId){
        String tableName = "room";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        updating_str = "[DataUpdate]Updating room record..";
        String sql = "UPDATE "+tableName+" SET roomNo=?,Dormitory_User_userId=?,charges_chargeId=? WHERE roomId=?";
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            if(roomNo==null)
            System.out.println(updating_str);
            updated = ps.executeUpdate();
            System.out.println(updated_str+" "+updated+" record(s)!");
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
        return updated;
    }
    
    public static int updateDormDetail(String roomNo, long userId, int chargeId){
        String tableName = "room";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        updating_str = "[DataUpdate]Updating room record..";
        String sql = "UPDATE "+tableName+" SET roomNo=?,Dormitory_User_userId=?,charges_chargeId=? WHERE roomId=?";
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            if(roomNo==null)
            System.out.println(updating_str);
            updated = ps.executeUpdate();
            System.out.println(updated_str+" "+updated+" record(s)!");
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
        return updated;
    }
    
    public static void disconnect(){
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
}
