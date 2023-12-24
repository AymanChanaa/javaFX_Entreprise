package com.example.javafx_employe.Metiers;

import java.sql.*;
public class DaoFactory {
    private static Connection connection = null;
    private static void toConnect(){
        try{
            System.out.println("Driver Download ...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Downloaded succefully!");
            String url = "jdbc:mysql://localhost:3306/entreprise";
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("Success Connection");
        }
        catch (ClassNotFoundException ex){
            System.out.println("Download Driver Failed!!");
        }
        catch (SQLException ex){
            System.out.println("Connection failed, Pb login,user,passeord "+ ex.getMessage());
        }
    }
    public static Connection getConnection(){
        if(connection == null){
            toConnect();
        }
        return connection;
    }
}
