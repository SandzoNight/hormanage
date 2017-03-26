/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataInsert;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class RoomCreateRoom {
    public static void create(String dormId, String roomNo, String userId, String chargeId){
        System.out.println("[RoomCreateRoom]Getting next roomId...");
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
        System.out.println("[RoomCreateRoom]Inserting new room...");
        DataInsert di = new DataInsert();
        di.insertRoom(nextRoomId, dormId, roomNo, userId, chargeId);
        di.disconnect();
        System.out.println("[RoomCreateRoom]Room Inserted!");
        
    }
}
