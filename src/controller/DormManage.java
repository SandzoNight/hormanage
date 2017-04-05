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
 * @author CBC
 */
public class DormManage {
    //This method will get the next value of dormId for you
    public static void add(String dormName,String dormType,String dormAddress,int countFloor,String[] facilityDormId,String[] facilityRoomId,long User_userId){
        int currentDormId = 0;
        ResultSet res = DataQuery.query("dormitory");
        try{
            while(res.next()){
                currentDormId = res.getInt("dormId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        int nextDormId = currentDormId+1;
        
        DataInsert di = new DataInsert();
        //Values pattern --> dormId,dormName,dormType,dormAddress,countRoom,facilityDormId,facilityRoomId,visitorNo,User_userId
        di.insertDorm(nextDormId+"",dormName,dormType,dormAddress,countFloor,facilityDormId,facilityRoomId,User_userId);
        di.disconnect();
    }
    
    public static void remove(String dormId,long User_userId){
        
        System.out.println("[DormManage] Removing from dormitoryfacilitydorm_has_dormitory");
        DataDelete.delete("dormitoryfacilitydorm_has_dormitory", "Dormitory_dormId", dormId);
        System.out.println("[DormManage] Removing from dormitoryfacilityroom_has_dormitory");
        DataDelete.delete("dormitoryfacilityroom_has_dormitory", "Dormitory_dormId", dormId);
        System.out.println("[DormManage] Removing dormitory");
        DataDelete.delete("dormitory", "dormId", dormId);
        System.out.println("[DormManage] Removing successful !");
        
    }
    
    
    public static void update(String dormName,String dormType,String dormAddress,int countRoom,String[] facilityDormId,String[] facilityRoomId, String dormId){
        ResultSet oldDormData = DataQuery.query("dormitory", "dormId", dormId);
        ResultSet oldFacilityDormData = DataQuery.query("dormitoryfacilitydorm_has_dormitory", "Dormitory_dormId", dormId);
        ResultSet oldFacilityRoomData = DataQuery.query("dormitoryfacilityroom_has_dormitory", "Dormitory_dormId", dormId);
    }
}
