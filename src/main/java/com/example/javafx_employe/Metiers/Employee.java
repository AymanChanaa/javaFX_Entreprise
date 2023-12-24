package com.example.javafx_employe.Metiers;

public class Employee {
    private int IdEmp;
    private String NomEmp;
    private float Salaire;
    private int Age;
    private int IdDept;


    public Employee(int idEmp, String nomEmp, float salaire, int age, int idDept) {
        IdEmp = idEmp;
        NomEmp = nomEmp;
        Salaire = salaire;
        Age = age;
        IdDept = idDept;
    }

    public int getIdEmp() {
        return IdEmp;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public float getSalaire() {
        return Salaire;
    }

    public int getAge() {
        return Age;
    }

    public void setIdEmp(int idEmp) {
        IdEmp = idEmp;
    }

    public void setNomEmp(String nomEmp) {
        NomEmp = nomEmp;
    }

    public void setSalaire(float salaire) {
        Salaire = salaire;
    }

    public void setAge(int age) {
        Age = age;
    }
    public int getIdDept() {
        return IdDept;
    }

    public void setIdDept(int idDept) {
        IdDept = idDept;
    }
}
