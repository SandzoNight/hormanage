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
    static String calling_str,updating_str,updating2_str,updated_str,error_str,disconnect_str;
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
    
    public static int updateDormDetail(String dormName,String dormType,String dormAddress,int dormCountFloor,float waterRate,float elecRate,String[] facilityDormId,String dormID){
        String tableName = "dormitory";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        updating2_str = "[DataUpdate]Updating dormitoryfacility_has_dorm records...";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        updating_str = "[DataUpdate]Updating dormitory record..";
        String sql = "UPDATE "+tableName+" SET dormName=?,dormType=?,dormAddress=?,dormCountFloor=?,waterRate=?,elecRate=? WHERE dormId="+dormID;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1, dormName);
            ps.setString(2, dormType);
            ps.setString(3, dormAddress);
            ps.setInt(4, dormCountFloor);
            ps.setFloat(5, waterRate);
            ps.setFloat(6, elecRate);
            System.out.println(updating_str);
            updated = ps.executeUpdate();
            System.out.println(updated_str+" "+updated+" record(s)!");
            disconnect();
            
            System.out.println(updating2_str);
            //ลบของเก่าทิ้ง
            DataDelete.delete("dormitoryfacilitydorm_has_dorm", "Dormitory_dormId", dormID);
            int numOfRecordDormFacility = facilityDormId.length;
            //เพิ่มของใหม่
            String sql2 ="INSERT INTO dormitoryfacilitydorm_has_dorm (Dormitoryfacilitydorm_facilityDormId,Dormitory_dormId) VALUES (?,?)";
            ps = connection.prepareStatement(sql2);
            for(int i=0;i<numOfRecordDormFacility;i++){
                ps.setString(1, facilityDormId[i]);
                ps.setString(2, dormID);
                ps.executeUpdate();
            }
            disconnect();
            
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
        return updated;
    }
    
    public static void updateNextRecordId(String column,long nextId){
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        updating_str = "[DataUpdate]Updating nextRecordId..";
        String sql = "UPDATE nextRecordId SET "+column+"=?";
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setLong(1, nextId);
            System.out.println(updating_str);
            updated = ps.executeUpdate();
            System.out.println(updated_str+" "+updated+" record(s)!");
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
    }
    
    public static void disconnect(){
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
    
    public static int updateRenterInfo(String[] data,long renterId){
        String tableName = "renter";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        updating_str = "[DataUpdate]Updating renter record...";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        System.out.println(renterId);
        String sql = "UPDATE "+tableName+" SET renterFirstName=?,renterLastName=?,renterGender=?,renterAddr=?,renterTel=?,renterEmail=? WHERE renterId="+renterId;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1, data[0]);
            ps.setString(2, data[1]);
            if(data[2].equals("ชาย")){
                ps.setString(3, "m");
            }else{
                ps.setString(3, "f");
            }
            
            ps.setString(4, data[3]);
            ps.setString(5, data[4]);
            ps.setString(6, data[5]);
            System.out.println(updating_str);
            updated = ps.executeUpdate();
            System.out.println(updated_str+" "+updated+" record(s)!");
            disconnect();
            
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
        return updated;
    }
}
