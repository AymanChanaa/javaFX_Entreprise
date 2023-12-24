package com.example.javafx_employe.Metiers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoStatistiquesDepartement {

    public static List<StatistiquesDepartement> NbreEmpsParDep(){
        Connection myCon = DaoFactory.getConnection();
        String sql = "Select IdDept,NomDept from Departement;";
        List<StatistiquesDepartement> lsd = new ArrayList<>();
        Statement stm;
        try {
            stm = myCon.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int idDept = rs.getInt(1);
                String nomDept = rs.getString(2);

                String sql2 ="Select count(*) from Employee where idDept=?;";
                PreparedStatement pst = myCon.prepareStatement(sql2);
                pst.setInt(1,idDept);
                ResultSet rs2 = pst.executeQuery();
                while (rs2.next()){
                    int nombreEmps = rs2.getInt(1);
                    lsd.add(new StatistiquesDepartement(nomDept,nombreEmps));
                }
            }
            return lsd;
        }
        catch (SQLException ex){
            System.out.println("NbreEmpsParDept " + ex.getMessage());
        }
        return null;
    }
    public static Long CountEmps(){
        Connection myCon = DaoFactory.getConnection();
        String sql ="Select count(*) from Employee;";
        Statement st;
        try {
            st = myCon.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                return Long.valueOf(rs.getInt(1));
        }
        catch (SQLException ex){
            System.out.println("CountEmps " + ex.getMessage());
        }
        return 0L;
    }
    public static Departement DepAvecPEmps(){
        Connection myCon = DaoFactory.getConnection();
        String sql ="SELECT D.IdDept, D.NomDept, COUNT(E.IdEmp) AS NombreEmployes\n" +
                "FROM Departement D\n" +
                "JOIN Employee E ON D.IdDept = E.IdDept\n" +
                "GROUP BY D.IdDept, D.NomDept\n" +
                "ORDER BY NombreEmployes DESC\n" +
                "LIMIT 1;\n";
        Statement st;
        try{
            st = myCon.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                Departement dep = new Departement(rs.getInt(1),rs.getString(2));
                return dep;
            }
        }catch (SQLException ex){
            System.out.println("DepAvecPEmps" + ex.getMessage());
        }

        return null;
    }
    public static float masseSalarialeEntr(){
        Connection myCon = DaoFactory.getConnection();
        String sql ="Select sum(salaire) from Employee;";
        Statement st;
        try {
            st = myCon.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getFloat(1);
            }
        }
        catch (SQLException ex){
            System.out.println("masseSalarialeEntr " + ex.getMessage());
        }
        return 0f;
    }
    public static List<StatistiquesDepartement> MasseSalarialePourDep(){
        Connection myCon = DaoFactory.getConnection();
        String sql = "Select IdDept,NomDept from Departement;";
        List<StatistiquesDepartement> lsd = new ArrayList<>();
        Statement stm;
        try {
            stm = myCon.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                int idDept = rs.getInt(1);
                String nomDept = rs.getString(2);

                String sql2 ="Select sum(salaire) from Employee where idDept=?;";
                PreparedStatement pst = myCon.prepareStatement(sql2);
                pst.setInt(1,idDept);
                ResultSet rs2 = pst.executeQuery();
                while (rs2.next()){
                    float masseSalariale = rs2.getFloat(1);
                    lsd.add(new StatistiquesDepartement(nomDept,masseSalariale));
                }
            }
            return lsd;
        }
        catch (SQLException ex){
            System.out.println("MasseSalarialePourDep " + ex.getMessage());
        }
        return null;
    }
}
