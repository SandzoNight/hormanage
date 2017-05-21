/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * 
 *
 * @author fluke
 */
public class DataInsert extends DBConnector {

    static String calling_str, inserting_str, disconnect_str;

    PreparedStatement ps;

    public DataInsert() {
        calling_str = "[DataInsert]Calling DBConnector to connect the database";
        inserting_str = "[DataInsert]Inserting data to table";
        disconnect_str = "[DataInsert]Disconnect from DB";
        System.out.println(calling_str);
        connect();
    }

    public void insert(String tableName, String columnNames, String values) {
//        String sql = "INSERT INTO "+tableName+" ("+columnNames+") VALUES (?)";
        String sql = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + values + ")";
        try {
            System.out.println(inserting_str);
            ps = connection.prepareStatement(sql);
//            ps.setString(1, values);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String tableName, String values) {
//        String sql = "INSERT INTO "+tableName+" VALUES (?)";
        String sql = "INSERT INTO " + tableName + " VALUES (" + values + ")";
        try {
            System.out.println(inserting_str);
            ps = connection.prepareStatement(sql);
//            ps.setString(1, values);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRoom(long nextRoomId,ArrayList<String> data, long Dormitory_dormId) {
        System.out.println(Dormitory_dormId);
        try {
            System.out.println(inserting_str);
            String sql = "INSERT INTO room VALUES (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, nextRoomId);  //roomId
            ps.setString(2, data.get(0));   //roomNo
            ps.setInt(3, Integer.parseInt(data.get(1)));    //roomFloorNumber
            ps.setLong(4, Long.parseLong(data.get(2))); //RoomType_typeId
            ps.setLong(5, Dormitory_dormId);    //Dormitory_dormId
            ps.setString(6, data.get(3));  //Renter_renterId
            if(data.get(3)!=null){
                ps.setInt(7, 1);    //roomStatus
            }else{
                ps.setInt(7, 0);    //roomStatus
            }
            
            ps.executeUpdate();
            
            updateId("room", nextRoomId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRoomType(long nextRoomTypeId, String typeName, float price, long Dormitory_dormId) {
        try {
            System.out.println(inserting_str);
            String sql = "INSERT INTO roomtype VALUES (?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, nextRoomTypeId);
            ps.setString(2, typeName);
            ps.setFloat(3, price);
            ps.setLong(4, Dormitory_dormId);
            ps.executeUpdate();
            
            updateId("roomtype", nextRoomTypeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDorm(String dormID, String[] dormInfo, String[] facilityDormId, long User_userId) {
        try {
            System.out.println(inserting_str);
            String sql = "INSERT INTO dormitory VALUES(?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, dormID);
            ps.setString(2, dormInfo[0]);          //dormName
            ps.setString(3, dormInfo[2]);          //dormType
            ps.setString(4, dormInfo[1]);       //dormAddress
            ps.setInt(5, 0);                    //dormCountRoom
            ps.setInt(6, Integer.parseInt(dormInfo[3]));       //dormCountFloor
            ps.setInt(7, 0);                    //dormVisitorNo
            ps.setFloat(8, Float.parseFloat(dormInfo[4]));          //dormWaterRate
            ps.setFloat(9, Float.parseFloat(dormInfo[5]));           //dormElecRate
            ps.setLong(10, User_userId);
            ps.executeUpdate();

            int numOfRecordDormFacility = facilityDormId.length;
            String sql2 = "INSERT INTO dormitoryfacilitydorm_has_dorm (Dormitoryfacilitydorm_facilityDormId,Dormitory_dormId) VALUES (?,?)";
            ps = connection.prepareStatement(sql2);
            for (int i = 0; i < numOfRecordDormFacility; i++) {
                ps.setString(1, facilityDormId[i]);
                ps.setString(2, dormID);
                ps.executeUpdate();
            }

            updateId("dormitory", Long.parseLong(dormID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int insertInvoice(long invoiceId,LocalDate startDate,LocalDate dueDate,ArrayList<String> data,long dormId){
        
        try {
            System.out.println(inserting_str);
            String sql = "INSERT INTO invoice VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, invoiceId);   //invoiceId
            ps.setString(2, data.get(0));          //invoiceNo
            ps.setDate(3, java.sql.Date.valueOf(startDate));          //startDate
            ps.setDate(4, java.sql.Date.valueOf(dueDate));       //dueDate
            ps.setString(5, null);                    //paidDate
            ps.setDouble(6, Double.parseDouble(data.get(4)));       //waterUsage
            ps.setDouble(7, Double.parseDouble(data.get(5)));                    //elecUsage
            ps.setDouble(8, Double.parseDouble(data.get(6)));          //waterTotalPrice
            ps.setDouble(9, Double.parseDouble(data.get(7)));           //elecTotalPrice
            ps.setDouble(10, Double.parseDouble(data.get(8)));    //roomPrice
            ps.setString(11, data.get(3));    //roomTypeName
            ps.setLong(12, Long.parseLong(data.get(9)));    //Room_roomId
            ps.setLong(13, Long.parseLong(data.get(1)));    //Renter_renterId
            ps.setLong(14, dormId);    //Dormitory_dormId
            ps.setInt(15, 0);    //paidStatus
            ps.setInt(16,0);    //cancelStatus
            int updated = ps.executeUpdate();
            updateId("invoice",invoiceId);
            return updated;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void updateId(String table, long currentId) {
        long nextId = currentId+1;
        switch (table) {
            case "dormitory": {
                DataUpdate.updateNextRecordId("nextDormid", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "room": {
                DataUpdate.updateNextRecordId("nextRoomid", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "dormitoryfacilitydorm": {
                DataUpdate.updateNextRecordId("nextFacilityDormId", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "roomtype": {
                DataUpdate.updateNextRecordId("nextRoomTypeId", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "renter": {
                DataUpdate.updateNextRecordId("nextRenterId", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "invoice": {
                DataUpdate.updateNextRecordId("nextInvoiceId", nextId);
                DataUpdate.disconnect();
                break;
            }
            case "contract": {
                DataUpdate.updateNextRecordId("nextContractId", nextId);
                DataUpdate.disconnect();
                break;
            }
        }
    }
    
    public int insertRenter(long renterID, long dormID, String[] data) {
        int inserted = 0;
        try {
            System.out.println(inserting_str);
            String sql = "INSERT INTO renter VALUES(?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, renterID); 
            ps.setString(2, data[0]); //First name
            ps.setString(3, data[1]); //Last Name
            ps.setString(4, data[2]); //Gender
            ps.setString(5, data[3]); //Address
            ps.setString(6, data[4]); //Tel
            ps.setString(7, data[5]); //Email
            ps.setString(8, null); //roomId
            ps.setString(9, null); //userId
            ps.setLong(10, dormID);
            inserted = ps.executeUpdate();

            updateId("renter", renterID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return inserted;
    }
    public int insertContract(long contractId,long contractNo,LocalDate createdDate,long dormId){
        int inserted = 0;
        try {
            System.out.println(inserting_str);
            String sql = "INSERT INTO contract VALUES(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, contractId);
            ps.setLong(2, contractNo);
            ps.setDate(3, Date.valueOf(createdDate));
            ps.setLong(4, dormId);
            inserted = ps.executeUpdate();
            
            updateId("contract", contractId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }
    
    public static void disconnect() {
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
}
