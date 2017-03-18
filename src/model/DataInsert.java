/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fluke
 */
public class DataInsert {
    DBConnector dbc = new DBConnector();
    Connection connect = dbc.connect();
    Statement s;
    
    public void insert(String tableName, String columnNames, String values){
        String sql = "INSERT INTO "+tableName+" ("+columnNames+") VALUES ("+values+")";
        try{
            System.out.println("Inserting record to '"+tableName+"'");
            s = connect.createStatement();
            s.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    public void insert(String tableName, String values){
        String sql = "INSERT INTO "+tableName+" VALUES ("+values+")";
        try{
            System.out.println("Inserting record to '"+tableName+"'");
            s = connect.createStatement();
            s.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    public void disconnect(){
        dbc.disconnect();
    }
}
