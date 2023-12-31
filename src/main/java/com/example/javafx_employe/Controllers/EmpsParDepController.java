package com.example.javafx_employe.Controllers;

import com.example.javafx_employe.Metiers.DaoDepartement;
import com.example.javafx_employe.Metiers.DaoEmployee;
import com.example.javafx_employe.Metiers.Departement;
import com.example.javafx_employe.Metiers.Employee;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmpsParDepController implements Initializable {

    @FXML
    private Label LabelEmpsParDep;

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<Employee,Integer> colAge;

    @FXML
    private TableColumn<Employee,Integer> colId;

    @FXML
    private TableColumn<Employee,String> colNom;

    @FXML
    private TableColumn<Employee,Float> colSalaire;

    @FXML
    private TableView<Employee> table;

    @FXML
    private TextField tfIdDep;

    @FXML
    private Button btnEmpsParDep;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DaoEmployee daoEmp = new DaoEmployee();
        showEmployes(daoEmp.All());
    }

    public void showEmployes(ObservableList<Employee> E){
        ObservableList<Employee> emps = E;
        table.setItems(emps);
        colId.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("IdEmp"));
        colNom.setCellValueFactory(new PropertyValueFactory<Employee,String>("NomEmp"));
        colAge.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("Age"));
        colSalaire.setCellValueFactory(new PropertyValueFactory<Employee,Float>("Salaire"));
    }

    @FXML
    void showEmpsParDep(ActionEvent event) {
        try {
            int idDept = Integer.parseInt(tfIdDep.getText());
            DaoEmployee daoEmp = new DaoEmployee();
            DaoDepartement daoDep = new DaoDepartement();
            Optional<Departement> dep = daoDep.Read(idDept);
            if (dep.isPresent()) {
                // Modifier le texte du Label avec le nom du département
                LabelEmpsParDep.setText(dep.get().getNomDept());
                showEmployes(daoEmp.EmpsParDep(idDept));
            } else {
                LabelEmpsParDep.setText("Département non trouvé.");
                String title= "Opération échoué";
                String headerText = "Recherche échoué";
                String contentText = "Le nom du département ne se trouve pas, Vérifier l'ID entré!";
                AlertsController.Warning(title,headerText,contentText);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}