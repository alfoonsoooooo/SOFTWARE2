package co.edu.poli.modelo;

public interface FactoryProducto <Producto>{
	Producto crearTipoProducto(int id, String descripcion, double medida);
}
