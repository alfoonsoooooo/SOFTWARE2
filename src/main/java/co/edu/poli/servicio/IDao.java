package co.edu.poli.servicio;

import java.util.List;

public interface IDao<T> {
	List<T>obtenerTodos();
	String insertar(T entidad);
	T obtenerPorId(int id);
	String eliminar(int id);
	String actualizar(T entidad);
}
