/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fluke
 */
public class ManageHor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnector dbc = new DBConnector();
        
        String sql = "SELECT * FROM problem";
        Statement s = null;
        Connection connect = null;
        try {
            connect = dbc.connect();
            s = connect.createStatement();
            ResultSet rec = s.executeQuery(sql);
            while(rec != null && rec.next()){
                System.out.print(rec.getString("problemId")+" ");
                System.out.println(rec.getString("problemName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbc.disconnect();
        }
    }
    
}
