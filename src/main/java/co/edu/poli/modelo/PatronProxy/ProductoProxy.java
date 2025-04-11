package co.edu.poli.modelo.PatronProxy;

public class ProductoProxy implements Producto {
    private ProductoModelo producto;
    private Usuario usuario;
    private final int nivelAcceso = 5;
    
    public ProductoProxy(ProductoModelo producto, Usuario usuario){
        this.producto = producto;
        this.usuario = usuario;
    }
    @Override
    public String mostrarDetallesProducto() {
        if (usuario.getRango() >= nivelAcceso) {
            return "Acceso Concedido: " +usuario.getNombre() + "\n"+producto.mostrarDetallesProducto();
        }
        else{
            return "Rango inferior: " + usuario.getRango()+" No Puedes Ver Detalles";
        }
    }


}
