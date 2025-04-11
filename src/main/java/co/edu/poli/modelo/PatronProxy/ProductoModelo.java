package co.edu.poli.modelo.PatronProxy;

public class ProductoModelo implements Producto{
    private String nombre;
    private String descripcion;
    public ProductoModelo(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    @Override
    public String mostrarDetallesProducto() {
        return "Producto - [Nombre: " + nombre + ", Descripcion: " + descripcion + "]";
    }
    

}
