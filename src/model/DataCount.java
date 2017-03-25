package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DataCount extends DBConnector{
    
    static PreparedStatement ps;
    static String calling_str,counting_str,counted_str;
    
    public static int count(String tableName){
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
            ResultSet rec = ps.executeQuery();
            while(rec.next() && rec != null){
                count = rec.getInt("count");
            }
            System.out.println(counted_str);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    
    public static int count(String tableName,String columnName,String columnValue){
        calling_str = "[DataCount]Calling DBConnector to connect the database";
        counting_str = "[DataCount]Counting from "+tableName;
        counted_str = "[DataCount]Counting successful!";
        String sql = "SELECT COUNT(*) AS count FROM "+tableName+" WHERE "+columnName+" = ?";
//        String sql = "SELECT COUNT(*) AS count FROM "+tableName+" WHERE "+columnName+" = '"+columnValue+"'";
        int count = 0;
        try{
            System.out.println(calling_str);
            connect();
            ps = connection.prepareStatement(sql);
            ps.setString(1, columnValue);
            System.out.println(counting_str);
            ResultSet rec = ps.executeQuery();
//            Statement s = connection.createStatement();
//            ResultSet rec = s.executeQuery(sql);
            while(rec.next()){
                count = rec.getInt("count");
            }
            System.out.println(counted_str);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    
    public static void disconnect(){
        DBConnector.disconnect();
    }
}
