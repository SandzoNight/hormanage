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
    public static void add(String dormName,String dormType,String dormAddress,String countRoom,String visitorNo,String User_userId){
        DataQuery dq = new DataQuery();
        String currentDormId = "";
        ResultSet res = dq.query("dormitory");
        try{
            while(res.next()){
                currentDormId = res.getString("dormId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            System.out.println("Exception caught!");
        }
        int nextDormId = Integer.parseInt(currentDormId)+1;
        
        DataInsert di = new DataInsert();
        //Values pattern --> dormId,dormName,dormType,dormAddress,countRoom,facilityDormId,facilityRoomId,visitorNo,User_userId
        di.insert("dormitory", "'"+nextDormId+"','"+dormName+"','"+dormType+"','"+dormAddress+"','"+countRoom+"','"+"0"+nextDormId+"','"+"0"+nextDormId+"','"+visitorNo+"','"+User_userId+"'");
        //di.disconnect();
    }
}
