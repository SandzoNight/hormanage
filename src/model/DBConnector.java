/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author fluke
 */
public abstract class DBConnector {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://ap-cdbr-azure-southeast-b.cloudapp.net:3306/managehor_db";
    private static final String DB_USERNAME = "b1b89ef60c23ca";
    private static final String DB_PASS = "596f01df";
    private static final String DB_MAX_POOL = "250";
    
    private static Properties properties;
    protected static Connection connection;
    
    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", DB_USERNAME);
            properties.setProperty("password", DB_PASS);
            properties.setProperty("MaxPooledStatements", DB_MAX_POOL);
        }
        return properties;
    }
    
    public static void connect(){
        if(connection == null){
            System.out.println("[DBConnector]Connecting to database...");
            try{
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, getProperties());
                System.out.println("[DBConnector]Connection successful!");
            }catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("[DBConnector]Disconnected form database!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("[DBConnector]Connection was Empty!");
        }
    }
}
