package co.edu.poli.controlador;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import co.edu.poli.modelo.Cliente;
import co.edu.poli.modelo.Departamento;
import co.edu.poli.modelo.Empleado;
import co.edu.poli.modelo.Producto;
import co.edu.poli.modelo.ProductoAlimenticio;
import co.edu.poli.modelo.ProductoElectrico;
import co.edu.poli.servicio.DaoCliente;
import co.edu.poli.servicio.DaoProductoAlimenticio;
import co.edu.poli.servicio.DaoProductoElectrico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControladorCRUD {

    private DaoCliente metodosCliente;
    private DaoProductoAlimenticio metodosProductalimento;
    private DaoProductoElectrico metodosProductelectrico;


    public ControladorCRUD() throws ClassNotFoundException, SQLException{
        this.metodosCliente = new DaoCliente();
        this.metodosProductalimento = new DaoProductoAlimenticio();
        this.metodosProductelectrico = new DaoProductoElectrico();
    }

    @FXML
    private TextField actualizarNombre;

    @FXML
    private TextField actualizarid;

    @FXML
    private Button btt1;

    @FXML
    private Button btt2;

    @FXML
    private Button btt3;

    @FXML
    private Button btt4;

    @FXML
    private Button btt5;

    @FXML
    private Button btt6;

    @FXML
    private Button bttActualizar, bttComposite;

    @FXML
    private TextField consultarId;

    @FXML
    private TextField eliminarId;

    @FXML
    private TextField insertarNombre;

    @FXML
    private TextArea textAreaClientes, mostrarJerarquia;

    @FXML
    void clonarAlimento(ActionEvent event) {
        Producto mango = new ProductoAlimenticio(1, "Mango", 567);
        Producto mangoClonado = mango.cloneProducts();
        metodosProductalimento.insertar(mangoClonado);
        JOptionPane.showMessageDialog(null, "Producto Mango, Clonado!!" + mangoClonado.toString());
    }

    @FXML
    void clonarElectrico(ActionEvent event) {
        Producto bateria = new ProductoElectrico(1, "Bateria", 15);
        Producto bateriaClonada = bateria.cloneProducts();
        metodosProductelectrico.insertar(bateriaClonada);
        JOptionPane.showMessageDialog(null, "Producto Bateria, Clonado!!" + bateriaClonada.toString());
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
        String id= eliminarId.getText();
        if(id == ""){
            JOptionPane.showMessageDialog(null, "No puedes Eliminar un Cliente sin su ID");
        }
        else{
            int id_numero = Integer.parseInt(id);
            try {
                JOptionPane.showMessageDialog(null, "Cliente Eliminado: "  + metodosCliente.eliminar(id_numero));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error en la Eliminacion");
            }
        }
    }

    @FXML
    void ingresar(ActionEvent event) {
        String nombre= insertarNombre.getText();
        if(nombre == ""){
            JOptionPane.showMessageDialog(null, "No puedes Ingresar un Cliente sin Nombre");
        }
        else{
            try {
                Cliente cliente = new Cliente(0,insertarNombre.getText());
                JOptionPane.showMessageDialog(null, "Cliente Ingresado: "  + metodosCliente.insertar(cliente));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error en la Insercion");
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
        int id = Integer.parseInt(actualizarid.getText());
        String nombre = actualizarNombre.getText();
        try {
            Cliente cliente = new Cliente(id, nombre);
            JOptionPane.showMessageDialog(null, "Cliente Actualizado: " + metodosCliente.actualizar(cliente));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar el cliente.");
        }
    }
    @FXML
    void Adaptar(ActionEvent event) {

    }
    @FXML
    void clickBuildProveedor(ActionEvent event) {

    }

    @FXML
    void clickMedio(ActionEvent event) {

    }
    @FXML
    void ClickComposite(ActionEvent event) {
        Empleado emp1 = new Empleado("Juan Pérez", "Desarrollador");
        Empleado emp2 = new Empleado("María López", "Diseñadora");
        Empleado emp3 = new Empleado("Carlos Gómez", "Gerente");
        Empleado emp4 = new Empleado("Ana Martínez", "Directora");
        
        Departamento deptDesarrollo = new Departamento("Desarrollo");
        Departamento deptDiseno = new Departamento("Diseño");
        Departamento deptTecnologia = new Departamento("Tecnología");
        Departamento deptEmpresa = new Departamento("Empresa");
        
        deptDesarrollo.agregarMiembro(emp1);
        deptDiseno.agregarMiembro(emp2);
        
        deptTecnologia.agregarMiembro(deptDesarrollo);
        deptTecnologia.agregarMiembro(deptDiseno);
        deptTecnologia.agregarMiembro(emp3);
        
        deptEmpresa.agregarMiembro(deptTecnologia);
        deptEmpresa.agregarMiembro(emp4);
        
        mostrarJerarquia.setText(deptEmpresa.mostrar(0));
    }

}
