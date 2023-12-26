module com.example.javafx_employe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires java.naming;

    opens com.example.javafx_employe.Controllers;
    opens com.example.javafx_employe to javafx.fxml;
    opens com.example.javafx_employe.Metiers to javafx.base;

    exports com.example.javafx_employe;
    exports com.example.javafx_employe.Controllers;
    exports com.example.javafx_employe.Metiers;
}