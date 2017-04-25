/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
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

    public void insertRoom(long nextRoomId, String roomNo, int roomFloorNumber, long Roomtype_typeId, long Dormitory_dormId) {
        try {
            System.out.println(inserting_str);
            String sql = "INSERT INTO room VALUES (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, nextRoomId);
            ps.setString(2, roomNo);
            ps.setInt(3, roomFloorNumber);
            ps.setLong(4, Roomtype_typeId);
            ps.setLong(5, Dormitory_dormId);
            ps.setString(6, null);
            ps.setInt(7, 0);
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

    public static void disconnect() {
        System.out.println(disconnect_str);
        DBConnector.disconnect();
    }
}
