package co.edu.poli.modelo;

import java.util.List;

public class Pedido {
	private int numero;
    private String fecha;
    private Cliente cliente;
    private List <String> productos;
	public Pedido(int numero, Cliente cliente, List<String> productos) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.productos = productos;
	}
	public Pedido(Cliente cliente, List<String> productos) {
		super();
		this.cliente = cliente;
		this.productos = productos;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<String> getProductos() {
		return productos;
	}
	public void setProductos(List<String> productos) {
		this.productos = productos;
	}
	@Override
	public String toString() {
		return "Pedido [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", productos=" + productos
				+ "]";
	}
	
}
