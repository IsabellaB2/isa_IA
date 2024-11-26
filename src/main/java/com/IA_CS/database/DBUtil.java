/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IA_CS.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
public class DBUtil extends DBConnection {

     

    public DBUtil () {
    }


    public int isAdmin ( int ID ) throws SQLException , ClassNotFoundException {
        Statement statement = getConnection ().createStatement ();
        ResultSet resultSet = statement.executeQuery ( "SELECT type FROM Users WHERE ID = " + ID );
        int isAdmin = 3;
        while ( resultSet.next () ) {
            isAdmin = resultSet.getInt ( "type" );
        }
        closeConnection ();
        return isAdmin;
    }

    public int getUserIDByLoginCredentials ( String name , String password ) throws SQLException , ClassNotFoundException {
        Statement statement = getConnection ().createStatement ();

        ResultSet resultSet = statement.executeQuery ( "SELECT ID FROM Users WHERE username = '" + name + "' AND password = '" + password + "'" );
        int id = -1;
        while ( resultSet.next () ) {
            id = resultSet.getInt ( "ID" );
        }
        closeConnection ();
        return id;
    }


    public void createEvent ( String startTime , String endTime  ) throws SQLException , ClassNotFoundException {
        String insertQuery = "INSERT INTO Events (Start, End) VALUES (?, ?)";
        try ( PreparedStatement pstmt = getConnection ().prepareStatement ( insertQuery ) ) {
            pstmt.setString ( 1 , startTime );
            pstmt.setString ( 2 , endTime );
            pstmt.executeUpdate ();
        }
        finally {
            closeConnection ();
        }
    }

//    public void updateNote ( int id , String note ) throws SQLException , ClassNotFoundException {
//        String updateQuery = "UPDATE Notes SET Note = ? WHERE ID = ?";
//        try ( PreparedStatement pstmt = getConnection ().prepareStatement ( updateQuery ) ) {
//            pstmt.setString ( 1 , note );
//            pstmt.setInt ( 2 , id );
//            pstmt.executeUpdate ();
//        }
//        finally {
//            closeConnection ();
//        }
//    }

//
//    public void createUsersTable () throws ClassNotFoundException , SQLException {
//        Statement statement = getConnection ().createStatement ();
//        statement.executeUpdate ( "CREATE TABLE Users (\n"
//                + "    ID       INTEGER   PRIMARY KEY AUTOINCREMENT,\n"
//                + "    Name     TEXT (50),\n"
//                + "    Pass     TEXT (50),\n"
//                + "    Nickname TEXT (30),\n"
//                + "    isAdmin  INTEGER,\n"
//                + "    PIN      TEXT (1000)\n"
//                + "); " );
//        closeConnection ();
//    }


}

