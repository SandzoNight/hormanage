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
public abstract class DataQuery extends DBConnector{
    static String calling_str,querying_str,queried_str,error_str,disconnect_str;
    private ResultSet rec = null;
    public static ResultSet query(String tableName){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from "+tableName;
        String sql = "SELECT * FROM "+tableName;
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
    
    public static ResultSet query(String tableName,String columnName,String columnValue){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
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
    
    public static ResultSet queryLogin(String username){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from user";
        String sql = "SELECT * FROM userdormowner WHERE userEmail = ?;";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
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
    
    public static ResultSet queryRoomList(String dormId,String floor){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from user";
        String sql = "SELECT * FROM room WHERE Dormitory_dormId = ? AND roomFloorNumber = ?";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1,dormId);
            ps.setString(2,floor);
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
    
    public static ResultSet queryRoomListAll(long dormId){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from user";
        String sql = "SELECT * FROM room WHERE Dormitory_dormId = ?";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1,dormId+"");
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
    
    public static ResultSet querySearchRenter(String table,String objectId,String keyword){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from user";
        String sql = "SELECT * FROM renter WHERE ((renterFirstName LIKE ?) OR (renterLastName LIKE ?) OR (renterTel LIKE ?)) AND (Dormitory_dormId=?)";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1,keyword);
            ps.setString(2,keyword);
            ps.setString(3,keyword);
            ps.setString(4,objectId);
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
    
    public static ResultSet queryUnpaidInvoice(long dormId){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from invoice";
        String sql = "SELECT * FROM invoice JOIN renter ON invoice.Renter_renterId = renter.renterId "
                + "JOIN room ON invoice.Room_roomId = room.roomId "
                + "WHERE paidStatus = 0 AND invoice.Dormitory_dormId = ? AND invoice.cancelStatus=0 ORDER BY invoice.invoiceNo DESC";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setLong(1,dormId);
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
    
    public static ResultSet queryAllInvoice(long dormId){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from invoice";
        String sql = "SELECT * FROM invoice JOIN renter ON invoice.Renter_renterId = renter.renterId "
                + "JOIN room ON invoice.Room_roomId = room.roomId "
                + "WHERE invoice.Dormitory_dormId = ? ORDER BY invoice.invoiceNo DESC";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setLong(1,dormId);
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
    
    public static ResultSet queryAvailableRenter(long dormId){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from user";
        String sql = "SELECT * FROM renter WHERE Dormitory_dormId=? AND Room_roomId IS NULL";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1,dormId+"");
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
    
    public static ResultSet queryRoomListRented(long dormId){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from user";
        String sql = "SELECT * FROM room WHERE Renter_renterId IS NOT NULL AND Dormitory_dormId=? ORDER BY roomNo ";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1,dormId+"");
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
    
    public static ResultSet queryInvoiceDetail(long invoiceId){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from invoice";
        String sql = "SELECT * FROM invoice INNER JOIN renter ON invoice.Renter_renterId=renter.renterId "
                + "JOIN room ON invoice.Room_roomId=room.roomId "
                + "JOIN roomType ON room.RoomType_typeId=roomtype.typeid "
                + "JOIN dormitory ON invoice.Dormitory_dormId=dormitory.dormId "
                + "WHERE invoiceId=?";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setLong(1,invoiceId);
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
    
    public static ResultSet queryContract(long dormId){
        calling_str = "[DataQuery]Calling DBConnector to connect the database";
        queried_str = "[DataQuery]Querying successful!";
        error_str = "[DataQuery]Error occured! Disconnecting from DB";
        disconnect_str = "[DataQuery]Disconnect from DB";
        querying_str = "[DataQuery]Querying from contract";
        String sql = "SELECT * FROM contract WHERE Dormitory_dormId=?";
        ResultSet rec = null;
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setLong(1,dormId);
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
