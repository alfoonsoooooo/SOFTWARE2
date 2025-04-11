module co.edu.poli {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;

    opens co.edu.poli.controlador to javafx.fxml;
    opens co.edu.poli.modelo.PatronFacade to javafx.base;
    opens co.edu.poli.modelo.PatronFlyWeight to javafx.base;
    opens co.edu.poli.modelo.PatronProxy to javafx.base;
    opens co.edu.poli.vista to javafx.fxml;

    exports co.edu.poli.controlador;
    exports co.edu.poli.modelo.PatronFacade;
    exports co.edu.poli.modelo.PatronFlyWeight;
    exports co.edu.poli.modelo.PatronProxy;
    exports co.edu.poli.vista;
}
