package com.example.javafx_employe.Controllers;

import com.example.javafx_employe.Metiers.DaoEmployee;
import com.example.javafx_employe.Metiers.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SupprimerEmpController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnchercher;

    @FXML
    private TextField tfId;

    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, Integer> tvAge;

    @FXML
    private TableColumn<Employee, String> tvDept;

    @FXML
    private TableColumn<Employee, Integer> tvId;

    @FXML
    private TableColumn<Employee, String> tvNom;

    @FXML
    private TableColumn<Employee, Float> tvSalaire;

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
    void chercherEmp(ActionEvent event) {
        int id = Integer.parseInt(tfId.getText());
        DaoEmployee daoEmp = new DaoEmployee();
        Optional<Employee> emp = daoEmp.Read(id);
        if(emp.isPresent()){
            Employee E = new Employee(emp.get().getIdEmp(),emp.get().getNomEmp(),emp.get().getSalaire(),
                    emp.get().getAge(),emp.get().getIdDept());
            showEmploye(E);
        }else{
            String title= "Employé introuvable";
            String headerText = "L'employé désiré n'existe pas!";
            String contentText = "Vérifier l'ID fournie du l'employé";
            AlertsController.Error(title,headerText,contentText);
        }
    }
    public void showEmploye(Employee emp){
        ObservableList<Employee> Emp = FXCollections.observableArrayList(emp);
        table.setItems(Emp);
        tvId.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("IdEmp"));
        tvNom.setCellValueFactory(new PropertyValueFactory<Employee,String>("NomEmp"));
        tvAge.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("Age"));
        tvSalaire.setCellValueFactory(new PropertyValueFactory<Employee,Float>("Salaire"));
    }

    @FXML
    void effacertfId(ActionEvent event) {
        tfId.setText("");
        table.getItems().clear();
    }

    @FXML
    void supprimerEmp(ActionEvent event) {
        DaoEmployee daoEmp = new DaoEmployee();
        if(tfId != null){
            daoEmp.Delete(Integer.parseInt(tfId.getText()));
            String title= "Opération réussie";
            String headerText = "Suppression Employé";
            String contentText = "Employé supprimé avec succès";
            AlertsController.Information(title,headerText,contentText);
            effacertfId(null);
        }else{
            String title= "Pas d'Employé";
            String headerText = "ID employé";
            String contentText = "Vérifier l'ID fournie du l'employé";
            AlertsController.Error(title,headerText,contentText);
        }

    }
}
