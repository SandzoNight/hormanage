package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dominatories {
    private String dormID;
    private String dormName;
    private String dormType;
    private String dormAddress;
    private int countRoom;
    private String facilityDormId;
    private String facilityRoomId;
    private int visitorNo;

    
   public static void main(String[] args) {
        try {
            Connection con
                    = DriverManager.getConnection("jdbc:mysql://ap-cdbr-azure-southeast-b.cloudapp.net:3306/managehor_db", "b1b89ef60c23ca", "596f01df");
           PreparedStatement pstm = con.prepareStatement("select * from dormitory");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {                
               System.out.println("Dorm ID : "+rs.getString("dormID"));
               System.out.println("Dorm Name : "+rs.getString("dormName"));
               System.out.println("Dorm Type : "+rs.getString("dormType"));
               System.out.println("Dorm Address : "+rs.getString("dormAddress"));
               System.out.println("Count Room : "+rs.getInt("countRoom"));
               System.out.println("Facility Dorm Id : "+rs.getString("facilityDormId"));
               System.out.println("Facility Room Id : "+rs.getString("facilityRoomId"));
               System.out.println("=================================");
            }
           
        } catch (SQLException se) {
            System.out.println(se);
        }
   }
}
    
 
            
        

