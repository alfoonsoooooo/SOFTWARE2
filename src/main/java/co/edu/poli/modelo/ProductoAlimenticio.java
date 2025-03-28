package co.edu.poli.modelo;

public class ProductoAlimenticio extends Producto {
	private double aporteCalorico;

	public ProductoAlimenticio(int id, String descripcion, double aporteCalorico) {
		super(id, descripcion);
		this.aporteCalorico = aporteCalorico;
	}

	public double getAporteCalorico() {
		return aporteCalorico;
	}

	public void setAporteCalorico(double aporteCalorico) {
		this.aporteCalorico = aporteCalorico;
	}

	@Override
	public String toString() {
		return "ProductoAlimenticio [ID: " +getId() +",Descripcion: "+getDescripcion()+", Aporte-Calorico= " + aporteCalorico + "]";
	}

	@Override
	public Producto cloneProducts() {
		return new ProductoAlimenticio(getId(), getDescripcion(), getAporteCalorico());
	}
	
	

}
