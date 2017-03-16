/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fluke
 */
public class DataQuery {
    
    DBConnector dbc = new DBConnector();
    Connection connect = dbc.connect();
    Statement s;
    
    public ResultSet query(String tableName){
        ResultSet rec = null;
        String sql = "SELECT * FROM "+tableName;
        try{
            s = connect.createStatement();
            rec = s.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rec;
    }
    
    public ResultSet query(String tableName,String columnName,String columnValue){
        Statement s = null;
        Connection connect = null;
        ResultSet rec = null;
        String sql = "SELECT * FROM "+tableName+" WHERE "+columnName+"="+columnValue;
        try{
            rec = s.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rec;
    }
    
    public void disconnect(){
        dbc.disconnect();
    }
}
