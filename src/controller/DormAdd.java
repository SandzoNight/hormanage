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
public class DormAdd {
    //This method will get the next value of dormId for you
    public static void add(String dormName,String dormType,String dormAddress,int countRoom
            ,String facilityDormId,String facilityRoomId,int visitorNo,long User_userId){
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
        di.insertDorm(nextDormId+"",dormName,dormType,dormAddress,countRoom,facilityDormId,facilityRoomId,visitorNo,User_userId);
        di.disconnect();
    }
}
