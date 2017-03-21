package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataCount extends DBConnector{
    
    DBConnector dbc = new DBConnector();
    PreparedStatement ps;
    ResultSet rec = null;
    String calling_str,counting_str,counted_str;
    
    public int count(String tableName){
        calling_str = "[DataCount]Calling DBConnector to connect the database";
        counting_str = "[DataCount]Counting from "+tableName;
        counted_str = "[DataCount]Counting successful!";
        String sql = "SELECT COUNT(*) AS count FROM "+tableName;
        int count = 0;
        try{
            System.out.println(calling_str);
            connect();
            ps = connection.prepareStatement(sql);
            System.out.println(counting_str);
            rec = ps.executeQuery(sql);
            while(rec.next() && rec != null){
                count = rec.getInt("count");
            }
            System.out.println(counted_str);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    
    public int count(String tableName,String columnName,String columnValue){
        calling_str = "[DataCount]Calling DBConnector to connect the database";
        counting_str = "[DataCount]Counting from "+tableName;
        counted_str = "[DataCount]Counting successful!";
        String sql = "SELECT COUNT(*) AS count FROM "+tableName+" WHERE ? = ?";
        int count = 0;
        try{
            System.out.println(calling_str);
            connect();
            ps = connection.prepareStatement(sql);
            ps.setString(1, columnName);
            ps.setString(2, columnValue);
            System.out.println(counting_str);
            rec = ps.executeQuery();
            while(rec.next()){
                count = rec.getInt("count");
            }
            System.out.println(counted_str);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    
    public void disconnect(){
        dbc.disconnect();
    }
}
