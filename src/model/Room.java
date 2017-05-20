/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pacharapol
 */
public class Room {
    private long roomId;
    private String roomNo;
    private long Renter_renterId;
    private int roomStatus;
    private long RoomType_typeId;
    
    public Room(long roomId,String roomNo,long Renter_renterId,int roomStatus,long RoomType_typeId){
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.Renter_renterId = Renter_renterId;
        this.roomStatus = roomStatus;
        this.RoomType_typeId = RoomType_typeId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public long getRenter_renterId() {
        return Renter_renterId;
    }

    public void setRenter_renterId(long Renter_renterId) {
        this.Renter_renterId = Renter_renterId;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public long getRoomType_typeId() {
        return RoomType_typeId;
    }

    public void setRoomType_typeId(long RoomType_typeId) {
        this.RoomType_typeId = RoomType_typeId;
    }
    
    public String toString(){
        return roomNo;
    }
    
}
