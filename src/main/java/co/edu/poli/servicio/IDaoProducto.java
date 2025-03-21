package co.edu.poli.servicio;

import java.util.List;

import co.edu.poli.modelo.Producto;

public interface IDaoProducto<Producto> extends IDao<Producto> {
	List<Producto> consultaDetallada(Producto producto);
}
