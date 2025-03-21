package co.edu.poli.servicio;
import co.edu.poli.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProductoAlimenticio implements IDaoProducto<Producto> {
	private Connection ConexPa;
	
	public DaoProductoAlimenticio() throws ClassNotFoundException, SQLException {
		this.ConexPa = ConexionBD.getConexion();
	}

	@Override
	public List<Producto> obtenerTodos() {
		List<Producto>productoAlimento = new ArrayList<Producto>();
		String sentencia = "SELECT * FROM productoAlimento";
		try(PreparedStatement consulta = ConexPa.prepareStatement(sentencia);
			ResultSet resultados = consulta.executeQuery()){
			while(resultados.next()) {
				ProductoAlimenticio registroProducto = new ProductoAlimenticio(resultados.getInt("id"), resultados.getString("descripcion"), resultados.getDouble("aporte"));
				productoAlimento.add(registroProducto);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return productoAlimento;
	}

	@Override
	public String insertar(Producto entidad) {
	    String sentencia = "INSERT INTO productoAlimento(descripcion, aporte) VALUES(?, ?)";
	    try (PreparedStatement consulta = ConexPa.prepareStatement(sentencia)) {
	        consulta.setString(1, entidad.getDescripcion());
	        consulta.setDouble(2, ((ProductoAlimenticio)entidad).getAporteCalorico());
	        int resultado = consulta.executeUpdate();
	        return (resultado > 0) ? "Insercion de Producto Exitosa" : "Insercion No Exitosa";
	    } catch (Exception e) {
	        return "Error al ingresar Producto en la base de datos";
	    }
	}

	@Override
	public Producto obtenerPorId(int id) {
	    String sentencia = "SELECT id, descripcion, aporte FROM productoAlimento WHERE id = ?";
	    try (PreparedStatement consulta = ConexPa.prepareStatement(sentencia)) {
	        consulta.setInt(1, id);
	        try (ResultSet resultado = consulta.executeQuery()) {
	            if (resultado.next()) {
	                return new ProductoAlimenticio(resultado.getInt("id"), resultado.getString("descripcion"), resultado.getDouble("aporte"));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public String eliminar(int id) {
	    String sentencia = "DELETE FROM productoAlimento WHERE id = ?";
	    try (PreparedStatement consulta = ConexPa.prepareStatement(sentencia)) {
	        consulta.setInt(1, id);
	        int resultado = consulta.executeUpdate();
	        return (resultado > 0) ? "Eliminacion Exitosa" : "Eliminacion no Exitosa";
	    } catch (Exception e) {
	        return "Problema con la eliminacion en la Base de Datos";
	    }
	}

	@Override
	public String actualizar(Producto entidad) {
		entidad = (ProductoAlimenticio) entidad;
	    String sentencia = "UPDATE productoAlimento SET descripcion = ?, aporte = ? WHERE id = ?";
	    try (PreparedStatement consulta = ConexPa.prepareStatement(sentencia)) {
	        consulta.setString(1, entidad.getDescripcion());
	        consulta.setDouble(2, ((ProductoAlimenticio) entidad).getAporteCalorico());
	        consulta.setInt(3, entidad.getId());
	        int resultados = consulta.executeUpdate();
	        return (resultados > 0) ? "Actualizacion Exitosa" : "Actualizacion no Exitosa";
	    } 
	    catch (Exception e) {
	        return "No se puede actualizar Producto en la Base de Datos";
	    }
	}

	@Override
	public List<Producto> consultaDetallada(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}


}
