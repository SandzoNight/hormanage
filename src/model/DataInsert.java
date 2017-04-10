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
    public void insertDorm(String dormID,String dormName,String dormType,String dormAddress,int dormCountFloor,float waterRate,float elecRate,String[] facilityDormId,long User_userId){
        try{
            System.out.println(inserting_str);
            String sql ="INSERT INTO dormitory VALUES(?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, dormID);
            ps.setString(2, dormName);
            ps.setString(3, dormType);
            ps.setString(4, dormAddress);
            ps.setInt(5, 0);
            ps.setInt(6, dormCountFloor);
            ps.setInt(7, 0);
            ps.setFloat(8, waterRate);
            ps.setFloat(9, elecRate);
            ps.setLong(10, User_userId);
            ps.executeUpdate();
            
            int numOfRecordDormFacility = facilityDormId.length;
            String sql2 ="INSERT INTO dormitoryfacilitydorm_has_dorm (Dormitoryfacilitydorm_facilityDormId,Dormitory_dormId) VALUES (?,?)";
            ps = connection.prepareStatement(sql2);
            for(int i=0;i<numOfRecordDormFacility;i++){
                ps.setString(1, facilityDormId[i]);
                ps.setString(2, dormID);
                ps.executeUpdate();
            }
            
            updateId("dormitory",dormID);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void updateId(String table,String id){
        long nextId = Long.parseLong(id);
        switch(table){
            case "dormitory" :{
                DataUpdate.updateNextRecordId("nextDormid", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "room" :{
                DataUpdate.updateNextRecordId("nextRoomid", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "dormitoryfacilitydorm" :{
                DataUpdate.updateNextRecordId("nextFacilityDormId", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "roomtype" :{
                DataUpdate.updateNextRecordId("nextRoomTypeId", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "renter" :{
                DataUpdate.updateNextRecordId("nextRenterId", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "invoice" :{
                DataUpdate.updateNextRecordId("nextInvoiceId", nextId);
                DataUpdate.disconnect();
                break;
            }
        }
    }
    
    public static void disconnect(){
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
}
