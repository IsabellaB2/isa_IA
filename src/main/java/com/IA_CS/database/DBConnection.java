/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IA_CS.database;


import com.IA_CS.config.Settings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
public abstract class DBConnection {
    

    private final String url = "jdbc:sqlite:" +Settings.USER_PATH+"/.IA_CS/config/config.db";
    private Connection connection;
    
    public DBConnection ()  {  }

    protected Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url);
        return connection;
    }
    
    protected void closeConnection() throws SQLException{
        connection.close ();
    }
      
}
