/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class RoomDetail {
    private int roomId;
    private String roomNo;
    private int dormId;
    private long userId;
    private int chargeId;
    
    public RoomDetail(int roomId, String roomNo,int dormId, long userId, int chargeId){
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.dormId = dormId;
        this.userId = userId;
        this.chargeId = chargeId;
    }

    public void selectRoom(int roomId){
        ResultSet res = DataQuery.query("room", "roomId", roomId+"");
        try{
            while(res.next()){
                System.out.println("[RoomDetail]Getting room information...");
                this.roomId = res.getInt("roomId");
                this.roomNo = res.getString("roomNo");
                this.dormId = res.getInt("dormId");
                this.userId = res.getLong("userId");
                this.chargeId = res.getInt("chargeId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        DataQuery.disconnect();
    }
    
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getDormId() {
        return dormId;
    }

    public void setDormId(int dormId) {
        this.dormId = dormId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getChargeId() {
        return chargeId;
    }

    public void setChargeId(int chargeId) {
        this.chargeId = chargeId;
    }
    
}
