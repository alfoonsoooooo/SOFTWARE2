package co.edu.poli.modelo;

public class ProductoAlimentoFactory implements FactoryProducto <Producto>{
	@Override
	public Producto crearTipoProducto(int id, String descripcion, double calorias) {
		return new ProductoAlimenticio(id, descripcion, calorias);
	}

}
