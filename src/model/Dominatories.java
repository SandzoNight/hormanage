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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CBC
 */
public class Dominatories {
    private String dormID;
    private String dormName;
    private String dormType;
    private String dormAddress;
    private int countRoom;
    private String facilityDormId;
    private String facilityRoomId;
    private int visitorNo;

    public String getDormID() {
        return dormID;
    }

    public void setDormID(String dormID) {
        this.dormID = dormID;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getDormType() {
        return dormType;
    }

    public void setDormType(String dormType) {
        this.dormType = dormType;
    }

    public String getDormAddress() {
        return dormAddress;
    }

    public void setDormAddress(String dormAddress) {
        this.dormAddress = dormAddress;
    }

    public int getCountRoom() {
        return countRoom;
    }

    public void setCountRoom(int countRoom) {
        this.countRoom = countRoom;
    }

    public String getFacilityDormId() {
        return facilityDormId;
    }

    public void setFacilityDormId(String facilityDormId) {
        this.facilityDormId = facilityDormId;
    }

    public String getFacilityRoomId() {
        return facilityRoomId;
    }

    public void setFacilityRoomId(String facilityRoomId) {
        this.facilityRoomId = facilityRoomId;
    }

    public int getVisitorNo() {
        return visitorNo;
    }

    public void setVisitorNo(int visitorNo) {
        this.visitorNo = visitorNo;
    }
    
    public static ArrayList<Dominatories> domListing(){
        ArrayList<Dominatories> info = new ArrayList<>();
        Dominatories dorm;
        try {
            Connection con = ConnectionBuilder.getConnection();
            PreparedStatement pstm = con.prepareStatement("select * from dormitory");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {                
                dorm = new Dominatories();
                dorm.dormID = rs.getString("dormId");
                dorm.dormName = rs.getString("dormName");
                dorm.dormType =rs.getString("dormType");
                dorm.dormAddress = rs.getString("dormAddress");
                dorm.countRoom =rs.getInt("countRoom");
                dorm.facilityDormId = rs.getString("facilityDormId");
                dorm.facilityRoomId = rs.getString("facilityRoomId");
                info.add(dorm);
            }
            con.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dominatories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }
    
    public static void main(String[] args) {
        ArrayList<Dominatories> d = Dominatories.domListing();
        for (Dominatories dominatories : d) {
            System.out.println(dominatories.getDormID()+" : "+dominatories.getDormName());
            System.out.println(dominatories.getDormAddress());
            
        }
    }
}
