package com.example.javafx_employe.Metiers;

import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.*;

public final class Tables {
    private static void CreateTables(Connection myCon){
        try{
            String sql1 = "Create Table if not exists Departement(IdDept int Primary Key AUTO_INCREMENT,NomDept Varchar(30))";
            Statement st1 = myCon.createStatement();
            st1.execute(sql1);
            String sql2 = "Create Table if not exists Employee(IdEmp int Primary Key AUTO_INCREMENT, " +
                    "NomEmp Varchar(30),Salaire float(3),Age int,IdDept int, FOREIGN KEY (IdDept) REFERENCES Departement(IdDept))";
            Statement st2 = myCon.createStatement();
            st2.execute(sql2);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    private static void Peuplement(Connection myCon){
        try{
            String[]  NomDept= {"Informatique","Logistique","RH"};
            String[] NomEmp ={"Chiti","Farissi","Bernoussi"};
            Float[] Salaires ={5000f,8000f,9560f};
            int[] Ages ={22,23,25};
            for(int i=0;i < 3;i++){
                String sql = "Insert Into Departement(IdDept,NomDept) values(?,?)";
                PreparedStatement pst = myCon.prepareStatement(sql);
                pst.setInt(1,i+1);
                pst.setString(2,NomDept[i]);
                int Lig = pst.executeUpdate();
                if(Lig == 1)
                    System.out.println("Departement added with success");
                else
                    System.out.println("Departement added Failed!!");
            }
            for(int i=0;i < 3;i++){
                String sql = "Insert Into Employee(IdEmp,NomEmp,Salaire,Age,IdDept) values(?,?,?,?,?)";
                PreparedStatement pst = myCon.prepareStatement(sql);
                pst.setInt(1,i+1);
                pst.setString(2,NomEmp[i]);
                pst.setFloat(3,Salaires[i]);
                pst.setInt(4,Ages[i]);
                pst.setInt(5,i+1);
                int Lig = pst.executeUpdate();
                if(Lig == 1)
                    System.out.println("Employee added with success");
                else
                    System.out.println("Employee added Failed!!");
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        try {
            Connection myCon = DaoFactory.getConnection();
            Tables.CreateTables(myCon);
            Tables.Peuplement(myCon);
            myCon.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
