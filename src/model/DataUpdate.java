/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author fluke
 */
public abstract class DataUpdate extends DBConnector{
    static String calling_str,updating_str,updating2_str,updated_str,error_str,disconnect_str;
    private static int updated = 0;
    
    public static int updateRoomDetail(ArrayList<String> data,long roomId){
        String tableName = "room";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        updating_str = "[DataUpdate]Updating room record..";
        String sql = "UPDATE "+tableName+" SET roomNo=?,roomFloorNumber=?,RoomType_typeId=?,Renter_renterId=?,roomStatus=? WHERE roomId="+roomId;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1,data.get(0));
            ps.setString(2,data.get(1));
            ps.setString(3,data.get(2));
            ps.setString(4,data.get(3));
            if(data.get(3)!=null){
                ps.setInt(5,1);
            }else{
                ps.setInt(5,0);
            }
            
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
    
    public static int updateDormDetail(String[] dormInfo,String[] facilityDormId){
        String tableName = "dormitory";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        updating2_str = "[DataUpdate]Updating dormitoryfacility_has_dorm records...";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        updating_str = "[DataUpdate]Updating dormitory record..";
        String sql = "UPDATE "+tableName+" SET dormName=?,dormType=?,dormAddr=?,dormCountFloor=?,dormWaterRate=?,dormElecRate=? WHERE dormId="+dormInfo[9];
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1, dormInfo[0]);
            ps.setString(2, dormInfo[1]);
            ps.setString(3, dormInfo[2]);
            ps.setInt(4, Integer.parseInt(dormInfo[4]));
            ps.setFloat(5, Float.parseFloat(dormInfo[6]));
            ps.setFloat(6, Float.parseFloat(dormInfo[7]));
            System.out.println(updating_str);
            updated = ps.executeUpdate();
            System.out.println(updated_str+" "+updated+" record(s)!");
            disconnect();
            
            System.out.println(updating2_str);
            //ลบของเก่าทิ้ง
            DataDelete.delete("dormitoryfacilitydorm_has_dorm", "Dormitory_dormId", dormInfo[9]);
            int numOfRecordDormFacility = facilityDormId.length;
            //เพิ่มของใหม่
            String sql2 ="INSERT INTO dormitoryfacilitydorm_has_dorm (Dormitoryfacilitydorm_facilityDormId,Dormitory_dormId) VALUES (?,?)";
            ps = connection.prepareStatement(sql2);
            for(int i=0;i<numOfRecordDormFacility;i++){
                ps.setString(1, facilityDormId[i]);
                ps.setString(2, dormInfo[9]);
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
    
    public static int updateRoomTypeInfo(ArrayList<String> data,long typeId){
        String tableName = "roomtype";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        updating_str = "[DataUpdate]Updating roomtype record...";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        System.out.println(typeId);
        String sql = "UPDATE "+tableName+" SET typeName=?,price=? WHERE typeId="+typeId;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1, data.get(0));
            ps.setString(2, data.get(1));
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
    
    public static int updateRenterRoomId(long roomId,long renterId){
        String tableName = "renter";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        updating_str = "[DataUpdate]Updating renter record...";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        String sql = "UPDATE "+tableName+" SET Room_roomId=? WHERE renterId="+renterId;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setLong(1, roomId);
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
    
    public static int invoiceCancel(long invoiceId){
        String tableName = "invoice";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        updating_str = "[DataUpdate]Updating renter record...";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        String sql = "UPDATE "+tableName+" SET cancelStatus=1 WHERE invoiceId="+invoiceId;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
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
    
    public static int invoicePaid(long invoiceId){
        String tableName = "invoice";
        calling_str = "[DataUpdate]Calling DBConnector to connect the database";
        updated_str = "[DataUpdate]Updated";
        updating_str = "[DataUpdate]Updating renter record...";
        error_str = "[DataUpdate]Error occured! Disconnecting from DB";
        disconnect_str = "[DataUpdate]Disconnect from DB";
        String sql = "UPDATE "+tableName+" SET paidStatus=1,paidDate=? WHERE invoiceId="+invoiceId;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            System.out.println(updating_str);
            Date date = new Date();
            ps.setDate(1, new java.sql.Date(date.getTime()));
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
