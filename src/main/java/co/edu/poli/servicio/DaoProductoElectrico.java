package co.edu.poli.servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.modelo.*;

public class DaoProductoElectrico implements IDaoProducto<Producto> {
    private Connection ConexPe;

    public DaoProductoElectrico() throws ClassNotFoundException, SQLException {
        this.ConexPe = ConexionBD.getConexion();
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productosElectricos = new ArrayList<>();
        String sentencia = "SELECT * FROM productoelectrico";
        try (PreparedStatement consulta = ConexPe.prepareStatement(sentencia);
             ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                ProductoElectrico registroProducto = new ProductoElectrico(resultados.getInt("id"),resultados.getString("descripcion"),resultados.getDouble("voltaje_entrada"));
                productosElectricos.add(registroProducto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productosElectricos;
    }

    @Override
    public String insertar(Producto entidad) {
        String sentencia = "INSERT INTO productoelectrico(descripcion, voltaje_entrada)" + " VALUES(?, ?)";
        try (PreparedStatement consulta = ConexPe.prepareStatement(sentencia)) {
            consulta.setString(1, entidad.getDescripcion());
            consulta.setDouble(2, ((ProductoElectrico) entidad).getVoltajeEntrada());
            int resultado = consulta.executeUpdate();
            return (resultado > 0) ? "Inserción de Producto Exitosa" : "Inserción No Exitosa";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al ingresar Producto en la base de datos";
        }
    }

    @Override
    public Producto obtenerPorId(int id) {
        String sentencia = "SELECT id, descripcion, voltaje_entrada FROM productoelectrico WHERE id = ?";
        try (PreparedStatement consulta = ConexPe.prepareStatement(sentencia)) {
            consulta.setInt(1, id);
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    return new ProductoElectrico(
                            resultado.getInt("id"),
                            resultado.getString("descripcion"),
                            resultado.getDouble("voltaje_entrada")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String eliminar(int id) {
        String sentencia = "DELETE FROM productoelectrico WHERE id = ?";
        try (PreparedStatement consulta = ConexPe.prepareStatement(sentencia)) {
            consulta.setInt(1, id);
            int resultado = consulta.executeUpdate();
            return (resultado > 0) ? "Eliminación Exitosa" : "Eliminación No Exitosa";
        } catch (Exception e) {
            return "Problema con la eliminación en la Base de Datos";
        }
    }

    @Override
    public String actualizar(Producto entidad) {
        entidad = (ProductoElectrico) entidad;
        String sentencia = "UPDATE productoelectrico SET descripcion = ?, voltaje_entrada = ? WHERE id = ?";
        try (PreparedStatement consulta = ConexPe.prepareStatement(sentencia)) {
            consulta.setString(1, entidad.getDescripcion());
            consulta.setDouble(2, ((ProductoElectrico) entidad).getVoltajeEntrada());
            consulta.setInt(3, entidad.getId());
            int resultados = consulta.executeUpdate();
            return (resultados > 0) ? "Actualización Exitosa" : "Actualización No Exitosa";
        } catch (Exception e) {
            return "No se puede actualizar Producto en la Base de Datos";
        }
    }

    @Override
    public List<Producto> consultaDetallada(Producto producto) {
        return null;
    }
}
