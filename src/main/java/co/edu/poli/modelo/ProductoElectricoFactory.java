package co.edu.poli.modelo;

public class ProductoElectricoFactory implements FactoryProducto{

	@Override
	public Producto crearTipoProducto(int id, String descripcion, double voltaje) {
		return new ProductoElectrico(id, descripcion,voltaje);
	}

}
