package co.edu.poli.controlador;

import co.edu.poli.modelo.PatronFacade.Cliente;
import co.edu.poli.modelo.PatronFacade.ClienteFacade;
import co.edu.poli.modelo.PatronFacade.MetodoPago;
import co.edu.poli.modelo.PatronFacade.Pedidos;
import co.edu.poli.modelo.PatronFlyWeight.Productos;
import co.edu.poli.modelo.PatronFlyWeight.Proveedor;
import co.edu.poli.modelo.PatronFlyWeight.ProveedorFactory;
import co.edu.poli.modelo.PatronProxy.Producto;
import co.edu.poli.modelo.PatronProxy.ProductoModelo;
import co.edu.poli.modelo.PatronProxy.ProductoProxy;
import co.edu.poli.modelo.PatronProxy.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class controladorFx {
    private Cliente clientex = new Cliente("Roberto", "roberto@email.com");
    private MetodoPago metodo = new MetodoPago();
    private Pedidos pedido = new Pedidos();
    private ClienteFacade cliente = new ClienteFacade(clientex, metodo, pedido);

    @FXML
    private Button bttActivarMetodo, bttActualizarCliente, bttBloquearMetodo, bttMostrarCliente,
            bttMostrarMetodo, bttMostrarPedidos, bttMostrarProducto, bttNose, bttRealizarPedido, bttPepsi, bttCoca;

    @FXML
    private TextField emailCliente, metodosCliente, nombreCliente, nombreUsuario, pedidoCliente, rangoUsuario;

    @FXML
    private TextArea mostrarProductos;

    private void mostrarAlerta(String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(tipo == AlertType.ERROR ? "Error" : "Mensaje");
        alerta.showAndWait();
    }

    @FXML
    void actulizarCliente(ActionEvent event) {
        if (!nombreCliente.getText().isEmpty() && !emailCliente.getText().isEmpty()) {
            mostrarAlerta(cliente.actualizarInformacionCliente(nombreCliente.getText(), emailCliente.getText()), AlertType.INFORMATION);
            nombreCliente.setPromptText("Nombre: " + nombreCliente.getText());
            emailCliente.setPromptText("Email: " + emailCliente.getText());
            nombreCliente.clear();
            emailCliente.clear();
        } else {
            mostrarAlerta("Ingresa un Nombre y/o Email Válido", AlertType.ERROR);
        }
    }

    @FXML
    void mostrarCliente(ActionEvent event) {
        mostrarAlerta(cliente.obtenerResumenCliente(), AlertType.INFORMATION);
    }

    @FXML
    void mostrarPedidos(ActionEvent event) {
        mostrarAlerta(cliente.obtenerResumenCliente(), AlertType.INFORMATION);
    }

    @FXML
    void realizarPedido(ActionEvent event) {
        if (!pedidoCliente.getText().isEmpty()) {
            mostrarAlerta(cliente.procesarNuevoPedido(pedidoCliente.getText(), "nequi"), AlertType.INFORMATION);
            pedidoCliente.clear();
        } else {
            mostrarAlerta("Ingresa un Producto Válido", AlertType.ERROR);
        }
    }

    @FXML
    void activarMetodo(ActionEvent event) {
        if (!metodosCliente.getText().isEmpty()) {
            mostrarAlerta(cliente.activarMetodo(metodosCliente.getText()), AlertType.INFORMATION);
            metodosCliente.clear();
        } else {
            mostrarAlerta("Ingresa un Método Válido", AlertType.ERROR);
        }
    }

    @FXML
    void bloquearMetodo(ActionEvent event) {
        if (!metodosCliente.getText().isEmpty()) {
            mostrarAlerta(cliente.bloquearMetodoDePago(metodosCliente.getText()), AlertType.INFORMATION);
            metodosCliente.clear();
        } else {
            mostrarAlerta("Ingresa un Método Válido", AlertType.ERROR);
        }
    }

    @FXML
    void mostrarMetodo(ActionEvent event) {
        mostrarAlerta(cliente.obtenerResumenCliente(), AlertType.INFORMATION);
    }

    @FXML
    void mostrarProducto(ActionEvent event) {
        if (!nombreUsuario.getText().isEmpty() && !rangoUsuario.getText().isEmpty()) {
            int rangoCAT = Integer.parseInt(rangoUsuario.getText());
            Usuario usuario = new Usuario(nombreUsuario.getText(), rangoCAT);
            ProductoModelo productoTanque = new ProductoModelo("Computador", "ASUS Vivobook 2025");
            Producto proxyProducto = new ProductoProxy(productoTanque, usuario);
            mostrarAlerta(proxyProducto.mostrarDetallesProducto(), AlertType.INFORMATION);
        } else {
            mostrarAlerta("Ingresa un Usuario y/o Rango Válido", AlertType.ERROR);
        }
    }

    @FXML
    void mostrarPepsi(ActionEvent event) {
        mostrarProductos.clear();
        Proveedor pepsico = ProveedorFactory.obtenerProveedor("Pepsico");
        Productos pro1 = new Productos("Refresco Pepsi", 2000, pepsico);
        Productos pro2 = new Productos("Refresco Gatorade", 3000, pepsico);
        Productos pro3 = new Productos("Paquete Detodito", 1000, pepsico);
        Productos pro4 = new Productos("Galletas Quacker", 500, pepsico);
        Productos pro5 = new Productos("Paquete Margarita", 2000, pepsico);
        boolean proveedorCompartido = pro1.getProveedor() == pro2.getProveedor()
                && pro2.getProveedor() == pro3.getProveedor()
                && pro3.getProveedor() == pro4.getProveedor()
                && pro4.getProveedor() == pro5.getProveedor();
        String todo = pro1.toString() + "\n" + pro2.toString() + "\n" + pro3.toString() + "\n" + pro4.toString() + "\n"
                + pro5.toString() + "\nTodos Comparten Proveedor?: " + proveedorCompartido;
        mostrarProductos.setText(todo);
    }

    @FXML
    void mostrarCoca(ActionEvent event) {
        mostrarProductos.clear();
        Proveedor cocacola = ProveedorFactory.obtenerProveedor("Coca - Cola");
        Productos pro1 = new Productos("Refresco Coca - Cola", 2500, cocacola);
        Productos pro2 = new Productos("Energizante Monster", 8000, cocacola);
        Productos pro3 = new Productos("Agua Cielo", 1000, cocacola);
        Productos pro4 = new Productos("Hidratante PowerAde", 3500, cocacola);
        Productos pro5 = new Productos("Jugo del Valle", 2000, cocacola);
        boolean proveedorCompartido = pro1.getProveedor() == pro2.getProveedor()
                && pro2.getProveedor() == pro3.getProveedor()
                && pro3.getProveedor() == pro4.getProveedor()
                && pro4.getProveedor() == pro5.getProveedor();
        String todo = pro1.toString() + "\n" + pro2.toString() + "\n" + pro3.toString() + "\n" + pro4.toString() + "\n"
                + pro5.toString() + "\nTodos Comparten Proveedor?: " + proveedorCompartido;
        mostrarProductos.setText(todo);
    }
}
