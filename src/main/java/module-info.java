module co.edu.poli.IngSoftware {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.web;
    requires javafx.media;
    requires java.sql;
	requires java.desktop;

    opens co.edu.poli.controlador to javafx.fxml;
    opens co.edu.poli.modelo to javafx.base;
    opens co.edu.poli.vista to javafx.fxml;

    exports co.edu.poli.controlador;
    exports co.edu.poli.modelo;
    exports co.edu.poli.servicio;
    exports co.edu.poli.vista;
}

