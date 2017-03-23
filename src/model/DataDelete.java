/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static model.DBConnector.connect;
import static model.DBConnector.connection;
import static model.DataQuery.disconnect;

/**
 *
 * @author fluke
 */
public class DataDelete extends DBConnector{
    static String calling_str,delete_str,deleted_str,error_str,disconnect_str,nodeleted_str;
    private static int updated = 0;
    public static int delete(String tableName,String columnName,String columnValue){
        calling_str = "[DataDelete]Calling DBConnector to connect the database";
        delete_str = "[DataDelete]Deleting table "+tableName;
        deleted_str = "[DataDelete]Deleted ";
        nodeleted_str = "[DataDelete]Nothing deleted from the table";
        error_str = "[DataDelete]Error occured! Disconnecting from DB";
        disconnect_str = "[DataDelete]Disconnect from DB";
        String sql = "DELETE FROM "+tableName+" WHERE "+columnName+" = ?";
        try{
            System.out.println(calling_str);
            connect();
            PreparedStatement ps;
            ps = connection.prepareStatement(sql);
            ps.setString(1, columnValue);
            System.out.println(delete_str);
            updated = ps.executeUpdate();
            System.out.println(deleted_str+updated+" record(s)");
        }catch(SQLException e){
            System.out.println(error_str);
            disconnect();
            e.printStackTrace();
        }
        return updated;
    }
}
