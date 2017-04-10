/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.RoomManage;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.DataQuery;

/**
 *
 * @author fluke
 */
public class TestQueryRoom {
    public static void main(String[] args) throws SQLException{
        ResultSet res1 = RoomManage.list("1","1");
        System.out.println("######## TestQueryRoom ########");
        while(res1.next()){
            System.out.println(res1.getString("roomNo"));
            System.out.println(res1.getString("roomStatus"));
        }
        DataQuery.disconnect();
    }
}
