package co.edu.poli.modelo;

import java.util.ArrayList;
import java.util.List;

public class Departamento implements IEmpresa{
    private String nombre;
    private List<IEmpresa> miembros;

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
    }
    public void agregarMiembro(IEmpresa miembro){
        this.miembros.add(miembro);
    }
    public void eliminarMiembro(IEmpresa miembro){
        this.miembros.remove(miembro);
    }


    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String mostrar(int nivel) {
        String resultado = "\t".repeat(nivel) + "Departamento: " + nombre + "{\n";

        for (IEmpresa unidad : miembros) {
            resultado += unidad.mostrar(nivel + 1) + "\n";
        }
    
        return resultado + "\t".repeat(nivel) + "}";
    }
    @Override
    public List<IEmpresa> getMiembros() {
        return miembros;
    }

    
}
