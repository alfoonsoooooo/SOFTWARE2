package co.edu.poli.modelo;

import java.util.List;

public interface IEmpresa {
    String getNombre();
    String mostrar(int nivel);
    List<IEmpresa> getMiembros();
}
