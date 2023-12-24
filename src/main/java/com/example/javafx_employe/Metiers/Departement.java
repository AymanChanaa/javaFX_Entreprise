package com.example.javafx_employe.Metiers;

public class Departement {
    private int IdDept;
    private String NomDept;

    public Departement(int idDept, String nomDept) {
        IdDept = idDept;
        NomDept = nomDept;
    }

    public int getIdDept() {
        return IdDept;
    }

    public String getNomDept() {
        return NomDept;
    }

    public void setIdDept(int idDept) {
        IdDept = idDept;
    }

    public void setNomDept(String nomDept) {
        NomDept = nomDept;
    }

    @Override
    public String toString() {
        return "Id departement: " + IdDept + "\nNom Departement:" + NomDept +"\n";
    }
}
