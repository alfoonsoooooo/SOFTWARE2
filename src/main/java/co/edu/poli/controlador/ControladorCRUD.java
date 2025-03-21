package co.edu.poli.controlador;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

import co.edu.poli.modelo.AdaptadorNequi;
import co.edu.poli.modelo.AdaptadorPayPal;
import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.MetodoPago;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;
import co.edu.poli.modelo.ProductoElectrico;

import co.edu.poli.servicio.DaoCliente;
import co.edu.poli.servicio.DaoProductoAlimenticio;
import co.edu.poli.servicio.DaoProductoElectrico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControladorCRUD {

    private DaoCliente metodosCliente;
    private DaoProductoAlimenticio metodosProductalimento;
    private DaoProductoElectrico metodosProductelectrico;

    public ControladorCRUD() throws ClassNotFoundException, SQLException {
        this.metodosCliente = new DaoCliente();
        this.metodosProductalimento = new DaoProductoAlimenticio();
        this.metodosProductelectrico = new DaoProductoElectrico();
    }

    @FXML
    private Button btt1, btt2, btt3, btt4, btt5, btt6, bttActualizar, bttAdapatar,  bttBuilder;;

    @FXML
    private ComboBox<String> bttMedio;

    @FXML
    private TextField consultarId, eliminarId, insertarNombre, productoAlimento, productoElectrico, valorPago, nombreProveedor, actualizarid, actualizarNombre;

    @FXML
    private TextArea textAreaClientes;

    @FXML
    public void initialize() {
        bttMedio.getItems().addAll("Nequi", "PayPal");
    }
    @FXML
    void Adaptar(ActionEvent event) {
        MetodoPago metodoPago;
        String opcion = bttMedio.getValue();
        if(opcion != null && !valorPago.getText().isEmpty()) {
            switch (opcion) {
                case "Nequi":
                    metodoPago = new AdaptadorNequi();
                    break;
                case "PayPal":
                    metodoPago = new AdaptadorPayPal();
                    break;
                default:
                    metodoPago = null;
                    break;
            }
            int valor = Integer.parseInt(valorPago.getText());
            valorPago.clear();
            String mensaje = metodoPago.realizarPago(valor);
            bttMedio.setValue(null);
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    @FXML
    void clickMedio(ActionEvent event) {
        String opcion = bttMedio.getValue();
        if(opcion != null) {
            switch (opcion) {
                case "Nequi":
                    valorPago.setPromptText("Valor a pagar con Nequi");
                    break;
                case "PayPal":
                    break;
                default:
                    valorPago.setPromptText("Valor a pagar");
                    break;
            }
        }
    }


    @FXML
    void clonarAlimento(ActionEvent event) {
        Producto mango = new ProductoAlimenticio(1, "Mango", 567);
        Producto mangoClonado = mango.cloneProducts();
        metodosProductalimento.insertar(mangoClonado);
        JOptionPane.showMessageDialog(null, "Producto Mango, Clonado!! " + mangoClonado.toString());
    }

    @FXML
    void clonarElectrico(ActionEvent event) {
        Producto bateria = new ProductoElectrico(1, "Bateria", 15);
        Producto bateriaClonada = bateria.cloneProducts();
        metodosProductelectrico.insertar(bateriaClonada);
        JOptionPane.showMessageDialog(null, "Producto Bateria, Clonado!! " + bateriaClonada.toString());
    }

    @FXML
    void consulta(ActionEvent event) {
        try {
            List<Cliente> clientes = metodosCliente.obtenerTodos();
            textAreaClientes.clear();
            if (clientes.isEmpty()) {
                textAreaClientes.appendText("No se encontraron clientes.");
            } else {
                for (Cliente cliente : clientes) {
                    textAreaClientes.appendText(cliente.toString() + "\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al consultar los clientes.");
        }
    }

    @FXML
    void eliminar(ActionEvent event) {
        String id = eliminarId.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puedes eliminar un Cliente sin su ID");
        } else {
            try {
                int id_numero = Integer.parseInt(id);
                JOptionPane.showMessageDialog(null, "Cliente Eliminado: " + metodosCliente.eliminar(id_numero));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en la eliminación");
            }
        }
    }

    @FXML
    void ingresar(ActionEvent event) {
        String nombre = insertarNombre.getText();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puedes ingresar un Cliente sin Nombre");
        } else {
            try {
                Cliente cliente = new Cliente(0, nombre);
                JOptionPane.showMessageDialog(null, "Cliente Ingresado: " + metodosCliente.insertar(cliente));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en la inserción");
            }
        }
    }

    @FXML
    void unaConsulta(ActionEvent event) {
        try {
            int id = Integer.parseInt(consultarId.getText());
            Cliente cliente = metodosCliente.obtenerPorId(id);
            JOptionPane.showMessageDialog(null, "Cliente encontrado con ID: " + id + " " + cliente.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número");
        }
    }

    @FXML
    void actulizarCliente(ActionEvent event) {
        try {
            int id = Integer.parseInt(actualizarid.getText());
            String nombre = actualizarNombre.getText();
            Cliente cliente = new Cliente(id, nombre);
            JOptionPane.showMessageDialog(null, "Cliente Actualizado: " + metodosCliente.actualizar(cliente));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar el cliente.");
        }
    }
}
