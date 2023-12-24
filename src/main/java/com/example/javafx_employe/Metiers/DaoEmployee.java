package com.example.javafx_employe.Metiers;

import com.example.javafx_employe.Controllers.EmpsParDepController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoEmployee implements CRUD<Employee,Integer>{
    private Connection myCon = DaoFactory.getConnection();
    @Override
    public boolean Create(Employee object) {
        String sql = "Insert Into Employee(IdEmp,NomEmp,Salaire,Age,IdDept) values(?,?,?,?,?);";
        PreparedStatement pst;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setInt(1,object.getIdEmp());
            pst.setString(2,object.getNomEmp());
            pst.setFloat(3,object.getSalaire());
            pst.setInt(4,object.getAge());
            pst.setInt(5,object.getIdDept());

            int lig = pst.executeUpdate();
            if (lig == 1){
                System.out.println("Employee added succefully!\n");
                return true;
            }else{
                System.out.println("Error! adding Employee failed\n");
                return false;
            }

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public ObservableList<Employee> All() {
        ObservableList<Employee> emps = FXCollections.observableArrayList();
        try {
            String sql = "Select * from Employee;";
            Statement st = myCon.createStatement();
            ResultSet res = st.executeQuery(sql);
            while (res.next()){
                emps.add(new Employee(res.getInt(1),res.getString(2),res.getFloat(3),
                        res.getInt(4),res.getInt(5)));
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return emps;
    }

    @Override
    public Optional<Employee> Read(Integer id) {
        String sql = "Select * from Employee where IdEmp =?;";
        PreparedStatement pst;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet res = pst.executeQuery();
            while (res.next()){
                System.out.println("Employee Found");
                Employee emp = new Employee(res.getInt(1),res.getString(2),res.getFloat(3),
                        res.getInt(4),res.getInt(5));
                return Optional.of(emp);
            }

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean Update(Employee object, Integer id) {
        String sql ="Update Employee set NomEmp=?, Salaire=?, Age=?,IdDept=? where IdEmp=?";
        PreparedStatement pst;
        try{
            pst = myCon.prepareStatement(sql);
            pst.setString(1,object.getNomEmp());
            pst.setFloat(2,object.getSalaire());
            pst.setInt(3,object.getAge());
            pst.setInt(4,object.getIdDept());
            pst.setInt(5,id);
            int lig = pst.executeUpdate();
            if(lig == 1){
                System.out.println("Employee Updated succefully!");
                return true;
            }else{
                System.out.println("Error!Employee Update Failed");
                return false;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(Integer id) {
        String sql = "Delete from Employee where IdEmp=?;";
        PreparedStatement pst;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setInt(1,id);
            int lig = pst.executeUpdate();
            if(lig == 1){
                System.out.println("Employee deleted succefully");
                return true;
            }else{
                System.out.println("Error!Employee delete failed");
                return false;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Long Count() {
        String sql = "select count(*) from Employee;";
        Statement st;
        try {
            st = myCon.createStatement();
            ResultSet resultset = st.executeQuery(sql);
            if(resultset.next()) return resultset.getLong(1);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0L;
    }
    public ObservableList<Employee> EmpsParDep(int IdDept){
        String sql = "Select * from Employee where IdDept=?;";
        ObservableList<Employee> emps = FXCollections.observableArrayList();
        PreparedStatement pst;
        try {
            pst = myCon.prepareStatement(sql);
            pst.setInt(1,IdDept);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee(rs.getInt(1), rs.getString(2),
                        rs.getFloat(3), rs.getInt(4), rs.getInt(5));
                emps.add(emp);
            }
            return emps;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
