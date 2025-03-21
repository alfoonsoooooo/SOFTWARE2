package co.edu.poli.modelo;

public class Cliente {
	private int id;
	private String name;
	public Cliente(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return name;
	}
	public void setNombre(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + name + "]";
	}
	
}
