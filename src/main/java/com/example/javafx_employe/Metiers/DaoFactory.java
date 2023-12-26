package com.example.javafx_employe.Metiers;

import java.sql.*;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    private static Connection connection = null;

    private static void initializeConnection() {
        try {
            MysqlDataSource ds = new MysqlDataSource();
            // Initialization of the source
            ds.setURL("jdbc:mysql://localhost:3306/entreprise");
            ds.setUser("root");
            ds.setPassword("");
            connection = ds.getConnection();
            // Retrieving the connection
            System.out.println("Connection established....");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            initializeConnection();
        }
        return connection;
    }

}
