package com.example.javafx_employe.Controllers;

import com.example.javafx_employe.Metiers.DaoDepartement;
import com.example.javafx_employe.Metiers.DaoEmployee;
import com.example.javafx_employe.Metiers.Departement;
import com.example.javafx_employe.Metiers.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.javafx_employe.Controllers.AlertsController;

import java.io.IOException;

public class AjoutEmpController {
    @FXML
    private Button btnAjoutEmp;

    @FXML
    private Button btnBack;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfNomDep;

    @FXML
    private TextField tfSalaire;

    @FXML
    void ajoutEmp(ActionEvent event) {
        DaoDepartement daoDep = new DaoDepartement();
        Departement dep = daoDep.ReadParNom(tfNomDep.getText());
        if (dep != null){
            Employee emp = new Employee(0,tfNom.getText(),Float.parseFloat(tfSalaire.getText()),
                    Integer.parseInt(tfAge.getText()),dep.getIdDept());
            DaoEmployee daoEmp = new DaoEmployee();
            daoEmp.Create(emp);
            String title= "Opération réussie";
            String headerText = "Ajout Employé";
            String contentText = "Employé ajouté avec succès";
            AlertsController.Information(title,headerText,contentText);
        }
        else{
            String title= "Ajout Employé échoué";
            String headerText = "Domaine du Departement";
            String contentText = "Le nom de département entré est introuvable! Vérifier de nouveau le département";
            AlertsController.Error(title,headerText,contentText);
        }
    }
    @FXML
    void ActionRetourEnArriere(ActionEvent event) throws IOException {
        // Obtenez la référence à la scène actuelle
        Scene currentScene = ((Node) event.getSource()).getScene();
        // Obtenez la référence à la fenêtre actuelle (Stage)
        Stage currentStage = (Stage) currentScene.getWindow();
        // Fermez la fenêtre actuelle (scène)
        currentStage.close();
        // Chargez le fichier FXML de la scène principale (Stage Home)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Home.fxml"));
        Parent root = loader.load();
        // Créez une nouvelle scène avec le contenu de Home.fxml
        Scene homeScene = new Scene(root);
        // Créez une nouvelle fenêtre (Stage) pour la scène principale (Stage Home)
        Stage homeStage = new Stage();
        // Définissez la scène principale (Stage Home) avec le contenu de Home.fxml
        homeStage.setScene(homeScene);
        // Montrez la scène principale (Stage Home)
        homeStage.show();
    }


}
