package com.example.javafx_employe.Controllers;

import com.example.javafx_employe.Metiers.DaoDepartement;
import com.example.javafx_employe.Metiers.DaoEmployee;
import com.example.javafx_employe.Metiers.Departement;
import com.example.javafx_employe.Metiers.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.example.javafx_employe.Metiers.Employee;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ModifierInfEmpController {

    @FXML
    private Button btnChercher;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnReset;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfNomDept;

    @FXML
    private TextField tfSalaire;

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

    @FXML
    void EffacerChamps(ActionEvent event) {
        tfNom.setText("");
        tfAge.setText("");
        tfSalaire.setText("");
        tfNomDept.setText("");
    }

    @FXML
    void ModifierEmp(ActionEvent event) {
        int id = Integer.parseInt(tfId.getText());
        String nomEmp = tfNom.getText();
        float salaire = Float.parseFloat(tfSalaire.getText());
        int age = Integer.parseInt(tfAge.getText());
        String nomDept = tfNomDept.getText();
        Departement dep;
        DaoDepartement daoDept = new DaoDepartement();
        dep = daoDept.ReadParNom(nomDept);
        if(dep != null){
            Employee Emp = new Employee(0,nomEmp,salaire,age, dep.getIdDept());
            DaoEmployee daoEmp = new DaoEmployee();
            daoEmp.Update(Emp,id);
            String title= "Opération réussie";
            String headerText = "Modification";
            String contentText = "L'employé est modifié avec succès!";
            AlertsController.Information(title,headerText,contentText);
        }else{
            String title= "Opération échoué";
            String headerText = "Modification échoué";
            String contentText = "Vérifier le nom du département!";
            AlertsController.Information(title,headerText,contentText);
        }
    }
    public void showEmploye(Optional<Employee> emp){
        tfNom.setText(emp.get().getNomEmp());
        tfSalaire.setText(String.valueOf(emp.get().getSalaire()));
        tfAge.setText(String.valueOf(emp.get().getAge()));
        Optional<Departement> dep;
        DaoDepartement daoDept = new DaoDepartement();
        dep = daoDept.Read(emp.get().getIdDept());
        if(dep.isPresent()){
            tfNomDept.setText(dep.get().getNomDept());
        }
    }

    @FXML
    void chercherEmp(ActionEvent event) {
        int id = Integer.parseInt(tfId.getText());
        DaoEmployee daoEmp = new DaoEmployee();
        Optional<Employee> emp = daoEmp.Read(id);
        if(emp.isPresent()){
            showEmploye(emp);
        }else{
            String title= "Employé introuvable";
            String headerText = "L'employé désiré n'existe pas!";
            String contentText = "Vérifier l'ID fournie du l'employé";
            AlertsController.Error(title,headerText,contentText);
        }
    }
}
