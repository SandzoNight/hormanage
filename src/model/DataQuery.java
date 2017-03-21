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
public class DataQuery extends DBConnector{
    
    public ResultSet query(String tableName){
        String sql = "SELECT * FROM "+tableName;
        ResultSet rec = null;
        try{
            DBConnector dbc = new DBConnector();
            Connection connect = dbc.connect();
            Statement s;
            s = connect.createStatement();
            rec = s.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            disconnect();
        }
        return rec;
    }
    
    public ResultSet query(String tableName,String columnName,String columnValue){
        String sql = "SELECT * FROM "+tableName+" WHERE "+columnName+"='"+columnValue+"'";
        ResultSet rec = null;
        try{
            DBConnector dbc = new DBConnector();
            Connection connect = dbc.connect();
            Statement s;
            s = connect.createStatement();
            rec = s.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            disconnect();
        }
        return rec;
    }
}
