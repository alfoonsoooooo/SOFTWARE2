package co.edu.poli.servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.modelo.Cliente;

public class DaoCliente implements IDao<Cliente> {
	private Connection ConexCl;
	
	public DaoCliente() throws ClassNotFoundException, SQLException {
		this.ConexCl = ConexionBD.getConexion();
	}

	@Override
	public List<Cliente> obtenerTodos() {
		String sentencia = "SELECT id,name FROM clientes";
		List<Cliente> clientes = new ArrayList<>();
		try(PreparedStatement consulta = ConexCl.prepareStatement(sentencia);
			ResultSet resultados = consulta.executeQuery();){
			while(resultados.next()) {
				Cliente registroCliente = new Cliente(resultados.getInt("id"), resultados.getString("name"));
				clientes.add(registroCliente);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public String insertar(Cliente entidad) {
		String sentencia = "INSERT INTO clientes(name) VALUES(?)";
		try(PreparedStatement consulta = ConexCl.prepareStatement(sentencia);){
			consulta.setString(1,entidad.getNombre());
			int resultado = consulta.executeUpdate();
			return (resultado>0)? "Insercion de Cliente Exitosa": "Insercion No Exitosa";	
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Error al ingresar Cliente en la base de datos";
		}
	}

	@Override
	public Cliente obtenerPorId(int id) {
		String sentencia = "SELECT * FROM clientes WHERE id = ?";
		try(PreparedStatement consulta = ConexCl.prepareStatement(sentencia);){
			consulta.setInt(1, id);
			try(ResultSet resultado = consulta.executeQuery();){
				if(resultado.next()){
					return new Cliente(resultado.getInt("id"),resultado.getString("name"));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String eliminar(int id) {
		String sentencia = "DELETE FROM clientes WHERE id = ?";
		try(PreparedStatement consulta = ConexCl.prepareStatement(sentencia);){
			consulta.setInt(1, id);
			int resultado = consulta.executeUpdate();
			return (resultado>0)? "Eliminacion Exitosa": "Eliminacion no Exitosa";
		}
		catch (Exception e) {
			return "Problema con la eliminacion en la Base de Datos";
		}
	}

	@Override
	public String actualizar(Cliente entidad) {
		String sentencia = "UPDATE clientes SET name=? WHERE id = ?";
		try(PreparedStatement consulta = ConexCl.prepareStatement(sentencia);){
			consulta.setString(1,entidad.getNombre());
			consulta.setInt(2, entidad.getId());
			int resultados = consulta.executeUpdate();
			return (resultados>0)? "Actualizacion Exitosa":"Actualizacion no Exitosa";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "No se puede actualizar Cliente en la Base de Datos";
		}
	}

}
