package co.edu.poli.modelo.PatronProxy;

public class Usuario {
    private String nombre;
    private int rango;
    
    public Usuario(String nombre, int rango) {
        this.nombre = nombre;
        this.rango = rango;
    }
    public String getNombre() {
        return nombre;
    }
    public int getRango() {
        return rango;
    }
    
}
