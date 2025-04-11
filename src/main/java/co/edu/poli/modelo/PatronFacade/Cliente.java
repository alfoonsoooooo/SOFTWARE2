package co.edu.poli.modelo.PatronFacade;

public class Cliente {
    private String nombre;
    private String email;
    
    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", email=" + email + "]";
    }

    
}
