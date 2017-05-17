/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DataCount;
import model.DataDelete;
import model.DataInsert;
import model.DataQuery;
import model.DataUpdate;

/**
 *
 * @author fluke
 */
public class RoomTypeManage {
    public static void create(ArrayList<String> info, long Dormitory_dormId){
        System.out.println("[RoomTypeManage]Getting next typeId...");
        ResultSet res = DataQuery.query("nextrecordId");
        long nextRoomId = 0;
        try{
            while(res.next()){
                nextRoomId = res.getLong("nextroomTypeId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        DataQuery.disconnect();
        System.out.println("[RoomTypeManage]Inserting new Roomtype...");
        DataInsert di = new DataInsert();
        di.insertRoomType(nextRoomId, info.get(0), Float.parseFloat(info.get(1)),Dormitory_dormId);
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
    
    public static ArrayList<String> getDetail(long typeId){
        ResultSet rec;
        rec = DataQuery.query("roomtype","typeId",typeId+"");
        ArrayList<String> info = new ArrayList<String>();
        try{
            while(rec.next()){
                info.add(rec.getString("typeName"));
                info.add(rec.getString("price"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return info;
    }
    
    public static int update(ArrayList<String> data,long typeId){
        return DataUpdate.updateRoomTypeInfo(data, typeId);
    }
    
}
