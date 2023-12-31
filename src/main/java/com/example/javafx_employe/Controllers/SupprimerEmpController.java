package com.example.javafx_employe.Controllers;

import com.example.javafx_employe.Metiers.DaoDepartement;
import com.example.javafx_employe.Metiers.DaoEmployee;
import com.example.javafx_employe.Metiers.Departement;
import com.example.javafx_employe.Metiers.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupprimerEmpController implements Initializable {

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

    private DaoEmployee daoEmp = new DaoEmployee();
    private DaoDepartement daoDep = new DaoDepartement();
    private boolean isEmployeeFound = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Employee> emp = daoEmp.All();
        showEmployes(emp);
        btnSupprimer.setDisable(true);
    }

    @FXML
    void ActionRetourEnArriere(ActionEvent event) throws IOException {
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Home.fxml"));
        Parent root = loader.load();
        Scene homeScene = new Scene(root);
        Stage homeStage = new Stage();
        homeStage.setScene(homeScene);
        homeStage.show();
    }

    @FXML
    void chercherEmp(ActionEvent event) {
        int id = Integer.parseInt(tfId.getText());
        DaoEmployee daoEmp = new DaoEmployee();
        Optional<Employee> emp = daoEmp.Read(id);
        if (emp.isPresent()) {
            Employee E = new Employee(emp.get().getIdEmp(), emp.get().getNomEmp(), emp.get().getSalaire(),
                    emp.get().getAge(), emp.get().getIdDept());
            showEmploye(E);
            btnSupprimer.setDisable(false);
            isEmployeeFound = true;
        } else {
            String title = "Employé introuvable";
            String headerText = "L'employé désiré n'existe pas!";
            String contentText = "Vérifier l'ID fournie du l'employé";
            AlertsController.Error(title, headerText, contentText);
            btnSupprimer.setDisable(true);
            isEmployeeFound = false;
        }
    }

    public void showEmploye(Employee emp) {
        ObservableList<Employee> Emp = FXCollections.observableArrayList(emp);
        table.setItems(Emp);
        tvId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("IdEmp"));
        tvNom.setCellValueFactory(new PropertyValueFactory<Employee, String>("NomEmp"));
        tvAge.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Age"));
        tvSalaire.setCellValueFactory(new PropertyValueFactory<Employee, Float>("Salaire"));
    }

    public void showEmployes(ObservableList<Employee> E) {
        ObservableList<Employee> emps = E;
        table.setItems(emps);
        tvId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("IdEmp"));
        tvNom.setCellValueFactory(new PropertyValueFactory<Employee, String>("NomEmp"));
        tvAge.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Age"));
        tvSalaire.setCellValueFactory(new PropertyValueFactory<Employee, Float>("Salaire"));
        tvDept.setCellValueFactory(cellData -> new SimpleStringProperty(getDepartementName(cellData.getValue().getIdDept())));
    }

    private String getDepartementName(Integer idDept) {
        DaoDepartement daoDepartement = new DaoDepartement();
        Optional<Departement> optionalDepartement = daoDepartement.Read(idDept);
        if (optionalDepartement.isPresent()) {
            Departement departement = optionalDepartement.get();
            return departement.getNomDept();
        } else {
            return "Département inconnu";
        }
    }


    @FXML
    void effacertfId(ActionEvent event) {
        tfId.setText("");
        ObservableList<Employee> emp = daoEmp.All();
        showEmployes(emp);
        btnSupprimer.setDisable(true);
    }

    @FXML
    void supprimerEmp(ActionEvent event) {
        if (isEmployeeFound) {
            DaoEmployee daoEmp = new DaoEmployee();
            if (tfId != null) {
                daoEmp.Delete(Integer.parseInt(tfId.getText()));
                String title = "Opération réussie";
                String headerText = "Suppression Employé";
                String contentText = "Employé supprimé avec succès";
                AlertsController.Information(title, headerText, contentText);
                effacertfId(null);
            } else {
                String title = "Pas d'Employé";
                String headerText = "ID employé";
                String contentText = "Vérifier l'ID fournie du l'employé";
                AlertsController.Error(title, headerText, contentText);
            }
        } else {
            String title = "Pas d'Employé";
            String headerText = "ID employé";
            String contentText = "Vérifier l'ID fournie du l'employé";
            AlertsController.Error(title, headerText, contentText);
        }
    }


}
