package co.edu.poli.modelo.PatronFlyWeight;

public class Proveedor {
    private String nombre;
    private String email;
    
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    protected Proveedor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Proveedor [nombre=" + nombre + ", email=" + email + "]";
    }
    
}
