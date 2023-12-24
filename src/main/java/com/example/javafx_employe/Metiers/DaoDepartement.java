package com.example.javafx_employe.Metiers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

public class DaoDepartement implements CRUD<Departement,Integer>{
    private Connection myCon = DaoFactory.getConnection();

    @Override
    public boolean Create(Departement object) {
        String sql = "Insert Into Departement(IdDept,NomDept) values(?,?);";
        PreparedStatement pst;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setInt(1,object.getIdDept());
            pst.setString(2,object.getNomDept());
            int lig = pst.executeUpdate();
            if (lig == 1){
                System.out.println("Departement added succefully!\n");
                return true;
            }else{
                System.out.println("Error! adding departement failed\n");
                return false;
            }

        }
        catch (SQLException ex){
            System.out.println("Create departement" + ex.getMessage());
            return false;
        }
    }

    @Override
    public ObservableList<Departement> All() {
        ObservableList<Departement> deps = FXCollections.observableArrayList();
        try {
            String sql = "Select * from Departement;";
            Statement st = myCon.createStatement();
            ResultSet res = st.executeQuery(sql);
            while (res.next()){
                deps.add(new Departement(res.getInt(1),res.getString(2)));
            }
        }
        catch (SQLException ex){
            System.out.println("All departement" + ex.getMessage());
        }
        return deps;
    }

    @Override
    public Optional<Departement> Read(Integer id) {
        String sql = "Select * from Departement where IdDept =?;";
        PreparedStatement pst;
        Departement dep = null;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                System.out.println("Departement Found");
                dep = new Departement(rs.getInt(1),rs.getString(2));
            }
            if (dep != null) {
                return Optional.of(dep);
            } else {
                return Optional.empty();
            }
        }
        catch (SQLException ex){
            System.out.println("Read departement" + ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean Update(Departement object, Integer id) {
        String sql ="Update Departement set NomDept=? where IdDept=?";
        PreparedStatement pst;
        try{
            pst = myCon.prepareStatement(sql);
            pst.setString(1,object.getNomDept());
            pst.setInt(2,id);
            int lig = pst.executeUpdate();
            if(lig == 1){
                System.out.println("Departement Updated succefully!");
                return true;
            }else{
                System.out.println("Error!Departement Update Failed");
                return false;
            }
        }
        catch (SQLException ex){
            System.out.println("Update departement " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(Integer id) {
        String sql = "Delete from Departement where IdDept=?;";
        PreparedStatement pst;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setInt(1,id);
            int lig = pst.executeUpdate();
            if(lig == 1){
                System.out.println("Departement deleted succefully");
                return true;
            }else{
                System.out.println("Error!Departement delete failed");
                return false;
            }
        }
        catch (SQLException ex){
            System.out.println("Delete departement " + ex.getMessage());
            return false;
        }
    }
    public Long Count(){
        String sql = "select count(*) from Departement;";
        Statement st;
        try {
            st = myCon.createStatement();
            ResultSet resultset = st.executeQuery(sql);
            if(resultset.next()) return resultset.getLong(1);
        }
        catch (SQLException ex){
            System.out.println("Count departement " + ex.getMessage());
        }
        return 0L;
    }
    public Departement ReadParNom(String nom){
        String sql ="Select * from Departement where NomDept=?;";
        PreparedStatement pst;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setString(1,nom);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                Departement dep = new Departement(rs.getInt(1),rs.getString(2));
                return dep;
            }else{
                return null;
            }
        }
        catch (SQLException ex){
            System.out.println("ReadParNom " + ex.getMessage());
            return null;
        }
    }
}
