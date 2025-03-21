package co.edu.poli.modelo;

public class ProductoElectrico extends Producto {
	private double voltajeEntrada;

	public ProductoElectrico(int id, String descripcion, double voltajeEntrada) {
		super(id, descripcion);
		this.voltajeEntrada = voltajeEntrada;
	}

	public double getVoltajeEntrada() {
		return voltajeEntrada;
	}

	public void setVoltajeEntrada(double voltajeEntrada) {
		this.voltajeEntrada = voltajeEntrada;
	}
	@Override
	public String toString() {
		return "Producto Electrico [ID: " +getId() +", Descripcion: "+getDescripcion()+", Volataje-Entrada= " + voltajeEntrada + "]";
	}

	@Override
	public Producto cloneProducts() {
		return new ProductoElectrico(getId(), getDescripcion(), getVoltajeEntrada());
	}
	
}
