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
        System.out.println("[DormManage] Removing from room");
        DataDelete.delete("room", "Dormitory_dormId", dormId);
        System.out.println("[DormManage] Removing from renter");
        DataDelete.delete("renter", "Dormitory_dormId", dormId);
        System.out.println("[DormManage] Removing from dormitoryfacilitydorm_has_dorm");
        DataDelete.delete("dormitoryfacilitydorm_has_dorm", "Dormitory_dormId", dormId);
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
    
    public static void update(String[] dormInfo,String[] facilityDormId){
        DataUpdate.updateDormDetail(dormInfo,facilityDormId);
        DataUpdate.disconnect();
    }
    
    public static String[] getInfo(long dormId){
        String[] dormInfo = new String[10];
        ResultSet rs = DataQuery.query("dormitory", "dormId", dormId+"");
        try{
            while(rs.next()){
                dormInfo[0] = rs.getString("dormName");
                dormInfo[1] = rs.getString("dormType");
                dormInfo[2] = rs.getString("dormAddr");
                dormInfo[3] = rs.getString("dormCountRoom");
                dormInfo[4] = rs.getString("dormCountFloor");
                dormInfo[5] = rs.getString("dormVisitorNo");
                dormInfo[6] = rs.getString("dormWaterRate");
                dormInfo[7] = rs.getString("dormElecRate");
                dormInfo[8] = rs.getString("Userdormowner_userId");
                dormInfo[9] = rs.getString("dormId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        DataQuery.disconnect();
        return dormInfo;
    }
    
    public static String[] getFacility(long dormId){
        System.out.println("[DormManage]Getting facility list");
        int length = DataCount.count("dormitoryfacilitydorm_has_dorm", "Dormitory_dormId", dormId+"");
        
        String[] facilityId = new String[length];
        ResultSet rs = DataQuery.query("dormitoryfacilitydorm_has_dorm", "Dormitory_dormId", dormId+"");
        try{
            int i = 0;
            while(rs.next()){
                facilityId[i] = rs.getString("Dormitoryfacilitydorm_facilityDormId");
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("[DormManage]Finish getting facility list");
        return facilityId;
    }

    public static void add(String[] CreateInvoice) {
       
    }

}
