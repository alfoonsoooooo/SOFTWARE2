package co.edu.poli.servicio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Pedido;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;
import co.edu.poli.modelo.ProductoElectrico;

public class DaoPedido implements IDao<Pedido> {
    private Connection conexPed;
    private DaoCliente daoCliente;
    private DaoProductoAlimenticio daoAlimenticio;
    private DaoProductoElectrico daoElectrico;

    public DaoPedido() throws ClassNotFoundException, SQLException {
        this.conexPed = ConexionBD.getConexion();
        this.daoCliente = new DaoCliente();
        this.daoAlimenticio = new DaoProductoAlimenticio();
        this.daoElectrico = new DaoProductoElectrico();
    }

    @Override
    public List<Pedido> obtenerTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sqlPedidos = "SELECT * FROM pedidos";
        try (PreparedStatement stmt = conexPed.prepareStatement(sqlPedidos); ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                int numeroPedido = rs.getInt("numero");
				int id = rs.getInt("cliente_id");
				Cliente clienteTodo = daoCliente.obtenerPorId(id);
				String productosStr = rs.getString("productos");
				List<String> productos = Arrays.asList(productosStr.split(","));
                Pedido pedio = new Pedido(numeroPedido, clienteTodo, productos);
				pedidos.add(pedio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pedidos;
    }

    @Override
    public Pedido obtenerPorId(int numero) {
		Pedido pedido = null;
        String sqlPedido = "SELECT * FROM pedidos WHERE numero = ?";
        try (PreparedStatement stmt = conexPed.prepareStatement(sqlPedido)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int clienteId = rs.getInt("cliente_id");
                    Cliente cliente = daoCliente.obtenerPorId(clienteId);
					String productosStr = rs.getString("productos");
					List<String> productos = Arrays.asList(productosStr.split(","));
                    pedido = new Pedido(numero,cliente, productos);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pedido;
    }
    

    @Override
    public String insertar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (cliente_id, productos) VALUES (?, ?)";

        try (PreparedStatement stmt = conexPed.prepareStatement(sql)) {
                stmt.setInt(1, pedido.getCliente().getId());
				String productosStr = String.join(",", pedido.getProductos());
                stmt.setString(2,productosStr);
                int filas = stmt.executeUpdate();
				return (filas>0)? "Insercion Exitosa": "Insercion No Exitosa";
            }
        	catch (SQLException e) {
            	e.printStackTrace();
            	return "Error al insertar pedido: " + e.getMessage();
        	}
    }

    @Override
    public String eliminar(int numero) {
        String sql = "DELETE FROM pedidos WHERE numero = ?";
        
        try (PreparedStatement stmt = conexPed.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            int rowsAffected = stmt.executeUpdate();
            
            return (rowsAffected > 0) ? "Pedido eliminado con éxito." : "No se encontró el pedido.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al eliminar pedido: " + e.getMessage();
        }
    }

    @Override
    public String actualizar(Pedido pedido) {
		String actualizar = "UPDATE pedidos SET cliente_id = ?, productos = ? WHERE numero = ?";
		try(PreparedStatement actualizare = conexPed.prepareStatement(actualizar);){
			actualizare.setInt(1,pedido.getCliente().getId());
			String pedidosLista = String.join(",", pedido.getProductos());
			actualizare.setString(2, pedidosLista);
			actualizare.setInt(3, pedido.getNumero());
			int actualizarColumna = actualizare.executeUpdate();
			return (actualizarColumna>0)? "Actualizacion Exitosa": "Actualizacion No Exitosa";
		}catch(SQLException e){
			return "Error al Actualizar en la base de datos";
		}
    }
}