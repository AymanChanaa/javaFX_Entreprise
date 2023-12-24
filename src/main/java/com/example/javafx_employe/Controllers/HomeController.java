package com.example.javafx_employe.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btn_ajout_emp;

    @FXML
    private Button btn_emps_par_dep;

    @FXML
    private Button btn_modifier_emp;

    @FXML
    private Button btn_stats;

    @FXML
    private Button btn_supp_emp;

    @FXML
    void ActionAjoutEmp(ActionEvent event) throws IOException {
        // Charger la nouvelle scène depuis le fichier AjoutEmp.fxml
        Parent ajoutEmpParent = FXMLLoader.load(getClass().getResource("/FXML/AjoutEmp.fxml"));
        Scene ajoutEmpScene = new Scene(ajoutEmpParent);
        // Obtenir la référence du stage actuel (la fenêtre principale)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Changer la scène actuelle du stage
        stage.setScene(ajoutEmpScene);
        // Montrer la nouvelle scène
        stage.show();
    }

    @FXML
    void ActionEmpsDep(ActionEvent event) throws IOException {
        // Charger la nouvelle scène depuis le fichier AjoutEmp.fxml
        Parent ajoutEmpParent = FXMLLoader.load(getClass().getResource("/FXML/EmpsParDep.fxml"));
        Scene ajoutEmpScene = new Scene(ajoutEmpParent);
        // Obtenir la référence du stage actuel (la fenêtre principale)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Changer la scène actuelle du stage
        stage.setScene(ajoutEmpScene);
        // Montrer la nouvelle scène
        stage.show();
    }

    @FXML
    void ActionModifierInfEmp(ActionEvent event) throws IOException {
        // Charger la nouvelle scène depuis le fichier AjoutEmp.fxml
        Parent ajoutEmpParent = FXMLLoader.load(getClass().getResource("/FXML/ModifierInfEmp.fxml"));
        Scene ajoutEmpScene = new Scene(ajoutEmpParent);
        // Obtenir la référence du stage actuel (la fenêtre principale)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Changer la scène actuelle du stage
        stage.setScene(ajoutEmpScene);
        // Montrer la nouvelle scène
        stage.show();
    }

    @FXML
    void ActionStatistiques(ActionEvent event) throws IOException {
        // Charger la nouvelle scène depuis le fichier AjoutEmp.fxml
        Parent ajoutEmpParent = FXMLLoader.load(getClass().getResource("/FXML/Statistiques.fxml"));
        Scene ajoutEmpScene = new Scene(ajoutEmpParent);
        // Obtenir la référence du stage actuel (la fenêtre principale)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Changer la scène actuelle du stage
        stage.setScene(ajoutEmpScene);
        // Montrer la nouvelle scène
        stage.show();
    }

    @FXML
    void ActionSuppEmp(ActionEvent event) throws IOException {
        // Charger la nouvelle scène depuis le fichier AjoutEmp.fxml
        Parent ajoutEmpParent = FXMLLoader.load(getClass().getResource("/FXML/SupprimerEmp.fxml"));
        Scene ajoutEmpScene = new Scene(ajoutEmpParent);
        // Obtenir la référence du stage actuel (la fenêtre principale)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Changer la scène actuelle du stage
        stage.setScene(ajoutEmpScene);
        // Montrer la nouvelle scène
        stage.show();
    }

}
