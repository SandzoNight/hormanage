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
public class RoomTypeManage {
    public static void create(String typeName, float price, long Dormitory_dormId){
        System.out.println("[RoomTypeManage]Getting next typeId...");
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
        System.out.println("[RoomTypeManage]Inserting new Roomtype...");
        DataInsert di = new DataInsert();
        di.insertRoomType(nextRoomId, typeName, price,Dormitory_dormId);
        di.disconnect();
        System.out.println("[RoomTypeManage]Roomtype Inserted!");
    }
    
    public static void delete(String typeId){
        System.out.println("[RoomTypeManage]Deleteing roomtype...");
        DataDelete.delete("roomtype", "typeId", typeId);
        DataDelete.disconnect();
        System.out.println("[RoomTypeManage]Roomtype deleted!");
    }
    
    public static ResultSet list(long dormId){
        ResultSet rec;
        rec = DataQuery.query("roomtype","Dormitory_dormId",dormId+"");
        return rec;
    }
    
    public static ResultSet getDetail(String typeId){
        ResultSet rec;
        rec = DataQuery.query("roomtype","typeId",typeId);
        return rec;
    }
    
}
