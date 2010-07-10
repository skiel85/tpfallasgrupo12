package com.fiuba.fallas.Cotizador;


public class Maquina {
	private int id;
	private String nombre;
	private int costos;
	private int velocidad;
	private int demoraImpresion;
	private int costoMO;
	private int disponibilidad;
	private int duracion;
	private int cantidadColores;
	// indica de 1 a 10 cuan eficiente es el trabajo en esa maquina
	private int eficacia;
	//indica si la maquina esta en condiciones o fuera de servicio, por ejemplo reparacion.
	private int mantenimiento;
	private int experiencia;
	private int MaxHojasImp;
	//dice si esta ocupada o desocupada ( no es tan real pero al menos son estados posibles.
	private String estado;
	//beneficio total de cada maquina.
	private int beneficios;
	
	public Maquina(int id, String nombre, int costoMP, int velocidad, int demoraImpresion, 
			int disponibilidad, int duracion, int cantidadColores, int mantenimiento,
			int experiencia,int MaxHojasImp ) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costos = costoMP;
		this.velocidad = velocidad;
		this.demoraImpresion = demoraImpresion;
		this.disponibilidad = disponibilidad;
		this.duracion = duracion;
		this.cantidadColores = cantidadColores;
		this.mantenimiento = mantenimiento;
		this.experiencia =experiencia;
		this.estado=null;
		this.beneficios=0;
		this.MaxHojasImp=MaxHojasImp;
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

	public int getdemoraImpresion() {
		return demoraImpresion;
	}

	public void setdemoraImpresion(int demoraImpresion) {
		this.demoraImpresion = demoraImpresion;
	}
	public int getdisponibilidad() {
		return disponibilidad;
	}

	public void setdisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	//horas operativa de cada una de las maquinas.
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

	public int getcantidadColores() {
		return cantidadColores;
	}

	public void setcantidadColores(int cantidadColores) {
		this.cantidadColores = cantidadColores;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	//usar si es necesario creo qeu en la idea de berta esto eera importante, sino no usar!
	public void seteficacia(int eficacia) {
		this.eficacia = eficacia;
	}

	public int geteficacia() {
		return eficacia;
	}
	
	public void setMaxHojasImp(int MaxHojasImp) {
		this.MaxHojasImp = MaxHojasImp;
	}

	public int getMaxHojasImp() {
		return this.MaxHojasImp;
	}
	public String getestado() {
		return estado;
	}

	public void setestado(String estado) {
		this.estado = estado;
	}
	public int getPrecioPapel(){
		return 100;	
	}
	public int getbeneficios() {
		return this.beneficios=getPrecioPapel()+ getdemoraImpresion() + (getdisponibilidad()*getcosto()) ;
	}
}
