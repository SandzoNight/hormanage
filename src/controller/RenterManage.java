/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
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
}
