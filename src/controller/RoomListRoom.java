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
public class RoomListRoom {
    public static ResultSet list(String dormId,String floor){
        ResultSet rec;
        String floorSQLWildcard = floor+"%";
        rec = DataQuery.queryRoom(dormId,floorSQLWildcard);
        return rec;
    }
}
