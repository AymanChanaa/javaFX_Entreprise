package com.example.javafx_employe.Metiers;

public class StatistiquesDepartement {
    private String nomDepartement;
    private int nombreEmployes;
    private float masseSalariale;

    public StatistiquesDepartement(String nomDepartement, int nombreEmployes) {
        this.nomDepartement = nomDepartement;
        this.nombreEmployes = nombreEmployes;
    }
    public StatistiquesDepartement(String nomDepartement,float masseSalariale){
        this.nomDepartement = nomDepartement;
        this.masseSalariale = masseSalariale;
    }
    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public int getNombreEmployes() {
        return nombreEmployes;
    }

    public void setNombreEmployes(int nombreEmployes) {
        this.nombreEmployes = nombreEmployes;
    }

    public float getMasseSalariale() {
        return masseSalariale;
    }

    public void setMasseSalariale(float masseSalariale) {
        this.masseSalariale = masseSalariale;
    }
}
