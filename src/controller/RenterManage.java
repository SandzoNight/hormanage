/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import model.DataCount;
import model.DataQuery;

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
}
