/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.DBConnector;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class DormInfo {
    private String dormName;
    private String dormType;
    private String dormAddress;
    private String CountRoom;
    private String facilityDormId;
    private String facilityRoomId;
    private String visitorNo;
    private String User_userId;
    
    public DormInfo(){
        DataQuery dq = new DataQuery();
        ResultSet res = dq.query("dormitory");
        ResultSet rec = null;
        String sql = "SELECT * FROM dormitory";
        try{
            while(res!=null && res.next()){
                dormName = res.getString("dormName");
                dormType = res.getString("dormType");
                dormAddress = res.getString("dormAddress");
                CountRoom = res.getString("CountRoom");
                facilityDormId = res.getString("facilityDormId");
                facilityRoomId = res.getString("facilityRoomId");
                visitorNo = res.getString("visitorNo");
                User_userId = res.getString("User_userId");
                        
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dq.disconnect();
        }
    }

    public String getDormName() {
        return dormName;
    }

    public String getDormType() {
        return dormType;
    }

    public String getDormAddress() {
        return dormAddress;
    }

    public String getCountRoom() {
        return CountRoom;
    }

    public String getFacilityDormId() {
        return facilityDormId;
    }

    public String getFacilityRoomId() {
        return facilityRoomId;
    }

    public String getVisitorNo() {
        return visitorNo;
    }

    public String getUser_userId() {
        return User_userId;
    }
    
    
}
