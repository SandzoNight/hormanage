/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataCount;
import model.DataDelete;
import model.DataInsert;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class RoomManage {
    public static void create(String roomNo, int roomFloorNumber, long Roomtype_typeId, long Dormitory_dormId){
        System.out.println("[RoomManage]Getting next roomId...");
        ResultSet res = DataQuery.query("nextrecordId");
        long nextRoomId = 0;
        try{
            while(res.next()){
                nextRoomId = res.getLong("nextroomId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        DataQuery.disconnect();
        System.out.println("[RoomManage]Inserting new room...");
        DataInsert di = new DataInsert();
        di.insertRoom(nextRoomId, roomNo, roomFloorNumber, Roomtype_typeId,Dormitory_dormId);
        di.disconnect();
        System.out.println("[RoomManage]Room Inserted!");
    }
    
    public static void delete(String roomId){
        System.out.println("[RoomManage]Deleteing room...");
        DataDelete.delete("room", "roomId", roomId);
        DataDelete.disconnect();
        System.out.println("[RoomManage]Room deleted!");
    }
    
    public static ResultSet list(String dormId,String floor){
        ResultSet rec;
        rec = DataQuery.queryRoomList(dormId,floor);
        return rec;
    }
    
    public static int totalRoomByFloor(String dormId,String floor){
        int num = DataCount.countRoomByFloor(dormId, floor);
        return num;
    }
    
    public static ResultSet getDetail(String roomId){
        ResultSet rec;
        rec = DataQuery.query("room","roomId",roomId);
        return rec;
    }
    
    public static int countEmptyRoom(String dormId){
        int count = DataCount.countAvailableRoom(dormId);
        DataCount.disconnect();
        return count;
    }
}
