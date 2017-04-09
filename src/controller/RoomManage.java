/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataDelete;
import model.DataInsert;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class RoomManage {
    public static void create(String dormId, String roomNo, String userId, String chargeId){
        System.out.println("[RoomManage]Getting next roomId...");
        ResultSet res = DataQuery.query("room");
        int nextRoomId = 0;
        try{
            while(res.next()){
                nextRoomId = res.getInt("roomId")+1;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        DataQuery.disconnect();
        System.out.println("[RoomManage]Inserting new room...");
        DataInsert di = new DataInsert();
        di.insertRoom(nextRoomId, dormId, roomNo, userId, chargeId);
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
        rec = DataQuery.queryRoom(dormId,floor);
        return rec;
    }
}
