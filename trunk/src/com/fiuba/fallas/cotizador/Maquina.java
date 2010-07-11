
public class Maquina {
	private int id;
	private String nombre;
	private int costo;
	private int velocidad;
	private double disponibilidad;
	private int colores;
	private boolean anulada;
	
	public Maquina(int id, String nombre, int velocidad, 
			int colores,double disponibilidad) {
		this.id = id;
		this.nombre = nombre;
		this.velocidad = velocidad;
		this.disponibilidad = disponibilidad;
		this.setColores(colores);
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public double getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}

	public boolean isAnulada() {
		return anulada;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getCosto() {
		return costo;
	}

	public void setColores(int colores) {
		this.colores = colores;
	}

	public int getColores() {
		return colores;
	}
}
