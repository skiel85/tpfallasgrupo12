
public class Maquina {
	private int id;
	private String nombre;
	
	private int costos;
	private int velocidad;
	private int resistencia;
	private int costoMO;
	private int amortizacion;
	private int duracion;
	private int capacitacion;
	private int mantenimiento;
	private int experiencia;
	
	private String estado;
	
	public Maquina(int id, String nombre, int costoMP, int velocidad, int resistencia,
			int CostoMO, int amortizacion, int duracion, int capacitacion, int mantenimiento,
			int experiencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costos = costoMP;
		this.velocidad = velocidad;
		this.resistencia = resistencia;
		this.costoMO= costoMO;
		this.amortizacion = amortizacion;
		this.duracion = duracion;
		this.capacitacion = capacitacion;
		this.mantenimiento = mantenimiento;
		this.experiencia = experiencia;
		this.estado ="No Definida";
	}

	public int getcosto() {
		return costos;
	}

	public void setcosto(int costo) {
		this.costos = costo;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getResistencia() {
		return resistencia;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public int getcostoMO() {
		return costoMO;
	}

	public void setcostoMO(int costoMO) {
		this.costoMO = costoMO;
	}

	public int getamortizacion() {
		return amortizacion;
	}

	public void setamortizacion(int amortizacion) {
		this.amortizacion = amortizacion;
	}

	public int getduracion() {
		return duracion;
	}

	public void setduracion(int duracion) {
		this.duracion = duracion;
	}

	public int getmantenimiento() {
		return mantenimiento;
	}

	public void setmantenimiento(int mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getcapacitacion() {
		return capacitacion;
	}

	public void setcapacitacion(int capacitacion) {
		this.capacitacion = capacitacion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getestado() {
		return estado;
	}

	public void setestado(String estado) {
		this.estado = estado;
	}
}
