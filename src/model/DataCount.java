package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataCount {
    
    DBConnector dbc = new DBConnector();
    Connection connect = dbc.connect();
    Statement s;
    ResultSet rec = null;
    
    public int count(String tableName){
        String sql = "SELECT COUNT(*) FROM "+tableName;
        int count = 0;
        try{
            s = connect.createStatement();
            rec = s.executeQuery(sql);
            while(rec.next() && rec != null){
                count = rec.getInt("*");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    
    public int count(String tableName,String columnName,String columnValue){
        String sql = "SELECT COUNT("+columnName+") AS count FROM "+tableName+" WHERE "+columnName+"='"+columnValue+"'";
        int count = 0;
        try{
            s = connect.createStatement();
            rec = s.executeQuery(sql);
            while(rec.next() && rec != null){
                count = rec.getInt("count");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    
    public void disconnect(){
        dbc.disconnect();
    }
}
