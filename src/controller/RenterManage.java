/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataCount;
import model.DataInsert;
import model.DataQuery;
import model.DataUpdate;

/**
 *
 * @author fluke
 */
public class RenterManage {
    public static ResultSet getRenterInfo(long renterId){
        ResultSet res = DataQuery.query("renter", "renterId", renterId+"");
        return res;
    }
    
    public static ResultSet showCustomerList(long dormId){
        ResultSet res = DataQuery.query("renter", "Dormitory_dormId", dormId+"");
        return res;    
    }
    
    public static int countRenter(long dormId){
        int count = DataCount.count("renter", "Dormitory_dormId", dormId+"");
        return count;
    }
    
    public static ResultSet searchRenterList(String keyword, String dormId){
        ResultSet res = DataQuery.querySearchRenter("renter", dormId, keyword+"%");
        return res;
    }
    
    public static int update(String[] data, long renterId){
        return DataUpdate.updateRenterInfo(data, renterId);
    }
    
    public static int add(String[] data, long dormId){
        long nextRenterId = 0;
        ResultSet res = DataQuery.query("nextrecordId");
        try{
            while(res.next()){
                nextRenterId = res.getLong("nextRenterId");
            }
            DataQuery.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        DataInsert di = new DataInsert();
        int inserted = di.insertRenter(nextRenterId,dormId,data);
        di.disconnect();
        return inserted;
    }
}
