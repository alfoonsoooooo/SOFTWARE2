package co.edu.poli.modelo.PatronFlyWeight;

public class Productos {
    private String nombre;
    private int precio;
    private Proveedor proveedor;
    
    @Override
    public String toString() {
        return "Productos [nombre=" + nombre + ", precio=" + precio + ", proveedor=" + proveedor.toString() + "]";
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Productos (String nombre, int precio, Proveedor proveedor){
        this.nombre = nombre;
        this.precio = precio;
        this.proveedor = proveedor;
    }
    

}
