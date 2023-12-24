package com.example.javafx_employe.Controllers;

import com.example.javafx_employe.Metiers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatistiquesController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<StatistiquesDepartement, Integer> colNbreEmp;

    @FXML
    private TableColumn<StatistiquesDepartement, String> colNomDept;

    @FXML
    private TableColumn<StatistiquesDepartement, Float> colMasseSal;

    @FXML
    private TableColumn<StatistiquesDepartement, String> colNomDeptMSD;

    @FXML
    private TableView<StatistiquesDepartement> tableMasseSal;

    @FXML
    private Label labelDepAvecPEmps;

    @FXML
    private Label labelNbreEmps;

    @FXML
    private TableView<StatistiquesDepartement> table;

    @FXML
    private Label labelMasseSalEntr;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NbreEmpsParDept();
        setLabelNbreEmps();
        setLabelDepAvecPEmps();
        setLabelMasseSalEntr();
        masseSalarialePourDep();
    }
    public void NbreEmpsParDept(){
        List<StatistiquesDepartement> lsd = DaoStatistiquesDepartement.NbreEmpsParDep();
        afficherDeptEmp(lsd);
    }

    public void afficherDeptEmp(List<StatistiquesDepartement> sd) {
        ObservableList<StatistiquesDepartement> SD = FXCollections.observableArrayList(sd);  // Utilisez observableArrayList au lieu de observableList
        table.setItems(SD);
        colNomDept.setCellValueFactory(new PropertyValueFactory<>("nomDepartement"));
        colNbreEmp.setCellValueFactory(new PropertyValueFactory<>("nombreEmployes"));
    }
    public void setLabelNbreEmps(){
        Long l = DaoStatistiquesDepartement.CountEmps();
        labelNbreEmps.setText(String.valueOf(l));
    }
    public void setLabelDepAvecPEmps(){
        Departement dep = DaoStatistiquesDepartement.DepAvecPEmps();
        if (dep != null) {
            labelDepAvecPEmps.setText(dep.getNomDept());
        } else {
            labelDepAvecPEmps.setText("Département non trouvé");
        }
    }

    public void setLabelMasseSalEntr(){
        labelMasseSalEntr.setText(String.valueOf(DaoStatistiquesDepartement.masseSalarialeEntr()));
    }

    public void masseSalarialePourDep(){
        List<StatistiquesDepartement> MSD = DaoStatistiquesDepartement.MasseSalarialePourDep();
        afficherMasseSalPourDep(MSD);
    }
    public void afficherMasseSalPourDep(List<StatistiquesDepartement> sd) {
        ObservableList<StatistiquesDepartement> SD = FXCollections.observableArrayList(sd);  // Utilisez observableArrayList au lieu de observableList
        tableMasseSal.setItems(SD);
        colNomDeptMSD.setCellValueFactory(new PropertyValueFactory<>("nomDepartement"));
        colMasseSal.setCellValueFactory(new PropertyValueFactory<>("masseSalariale"));
    }
}
