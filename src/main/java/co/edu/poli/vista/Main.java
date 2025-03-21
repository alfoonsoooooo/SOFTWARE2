package co.edu.poli.vista;

import co.edu.poli.controlador.ControladorCRUD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/formularioCRUD.fxml"));
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}
