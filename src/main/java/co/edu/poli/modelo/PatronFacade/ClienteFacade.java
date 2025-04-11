package co.edu.poli.modelo.PatronFacade;

public class ClienteFacade {
    private Cliente cliente;
    private MetodoPago metodoPago;
    private Pedidos pedidos;

    public ClienteFacade(Cliente cliente, MetodoPago metodoPago, Pedidos pedidos) {
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.pedidos = pedidos;
    }

    public String actualizarInformacionCliente(String nombre, String email) {
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        return "Datos del cliente actualizados correctamente.";
    }

    public String procesarNuevoPedido(String pedido, String metodoPagoSeleccionado) {
        String resultadoPedido = pedidos.agregar(pedido);
        String resultadoPago = metodoPago.activarMetodo(metodoPagoSeleccionado);
        return resultadoPedido + "\n" + resultadoPago;
    }
    public String obtenerResumenCliente() {
        return "Cliente:\n" + cliente.toString() +
               "\n\nPedidos:\n" + pedidos.mostrarPedido() +
               "\n\nMétodos de pago:\n" + metodoPago.mostrar();
    }


    public String bloquearMetodoDePago(String metodo) {
        return metodoPago.bloquearMetodo(metodo);
    }

    public String activarMetodo(String metodo){
        return metodoPago.activarMetodo(metodo);
    }
}
