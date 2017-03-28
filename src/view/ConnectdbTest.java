package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.DataQuery;

public class ConnectdbTest {
    public static void main(String[] args) {
        DataQuery dq = new DataQuery() {};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Registed");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://ap-cdbr-azure-southeast-b.cloudapp.net:3306/managehor_db","b1b89ef60c23ca","596f01df");
            System.out.println("Connected");
            
            Statement sta = con.createStatement();
            
            ResultSet res = dq.query("user");
            
            while(res.next()){
                //System.out.println("Dorm Name : " + res.getString("dormName"));
                System.out.println("Userid : " + res.getLong("userId"));
                System.out.println("UserPassword : " + res.getString("password"));
            }
        }
        
        catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            dq.disconnect();
            System.out.println("Disconnected");
        }
        
        
    }
}
