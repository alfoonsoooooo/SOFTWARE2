package co.edu.poli.servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexionBD {
    private static Connection conexion = null;
    private static final ResourceBundle recursos = ResourceBundle.getBundle("config");
    
    private ConexionBD() {}
    
    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        if (conexion == null) {
            Class.forName("org.postgresql.Driver");
            String url = recursos.getString("db.url");
            String baseDatos = recursos.getString("db.database");
            String usuario = recursos.getString("db.user");
            String password = recursos.getString("db.password");
            conexion = DriverManager.getConnection(url + baseDatos, usuario, password);
        }
        return conexion;
    }
}
