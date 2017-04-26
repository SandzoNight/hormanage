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
import model.DataUpdate;

/**
 *
 * @author CBC
 */
public class DormManage {
    //This method will get the next value of dormId for you
    public static void add(String[] dormInfo,String[] facilityDormId,long User_userId){
        long nextDormId = 0;
        ResultSet res = DataQuery.query("nextrecordId");
        try{
            while(res.next()){
                nextDormId = res.getLong("nextDormId");
            }
            DataQuery.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        DataInsert di = new DataInsert();
        //Values pattern --> dormId,dormName,dormType,dormAddress,countRoom,facilityDormId,User_userId
        di.insertDorm(nextDormId+"",dormInfo,facilityDormId,User_userId);
        di.disconnect();
    }
    
    public static void remove(String dormId,long User_userId){
        System.out.println("[DormManage] Removing from dormitoryfacilitydorm_has_dorm");
        DataDelete.delete("dormitoryfacilitydorm_has_dormitory", "Dormitory_dormId", dormId);
        System.out.println("[DormManage] Removing dormitory");
        DataDelete.delete("dormitory", "dormId", dormId);
        System.out.println("[DormManage] Removing successful !");
        
    }
    
    public static int getFloor(long dormId){
        System.out.println("[DormManage] Getting floors");
        int floor = 0;
        ResultSet dormInfo = DataQuery.query("dormitory", "dormId", dormId+"");
        try{
            while(dormInfo.next()){
                floor = dormInfo.getInt("dormCountFloor");
            }
            DataQuery.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return floor;
    }
    
    public static void update(String dormName,String dormType,String dormAddress,int countFloor,float waterRate,float elecRate,String[] facilityDormId,String dormId){
        DataUpdate.updateDormDetail(dormName, dormType, dormAddress, countFloor, waterRate, elecRate, facilityDormId, dormId);
        DataUpdate.disconnect();
    }
}
