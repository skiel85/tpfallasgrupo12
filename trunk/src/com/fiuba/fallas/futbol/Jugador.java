package com.fiuba.fallas.futbol;
public class Jugador {
	private int id;
	private String nombre;
	
	private int atajando;
	private int velocidad;
	private int resistencia;
	private int quite;
	private int pases;
	private int centros;
	private int cabeza;
	private int definicion;
	private int experiencia;
	
	private String posicion;
	
	public Jugador(int id, String nombre, int atajando, int velocidad, int resistencia,
			int quite, int pases, int centros, int cabeza, int definicion,
			int experiencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.atajando = atajando;
		this.velocidad = velocidad;
		this.resistencia = resistencia;
		this.quite = quite;
		this.pases = pases;
		this.centros = centros;
		this.cabeza = cabeza;
		this.definicion = definicion;
		this.experiencia = experiencia;
		this.posicion ="No Definida";
	}

	public int getAtajando() {
		return atajando;
	}

	public void setAtajando(int atajando) {
		this.atajando = atajando;
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

	public int getQuite() {
		return quite;
	}

	public void setQuite(int quite) {
		this.quite = quite;
	}

	public int getPases() {
		return pases;
	}

	public void setPases(int pases) {
		this.pases = pases;
	}

	public int getCentros() {
		return centros;
	}

	public void setCentros(int centros) {
		this.centros = centros;
	}

	public int getDefinicion() {
		return definicion;
	}

	public void setDefinicion(int definicion) {
		this.definicion = definicion;
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

	public int getCabeza() {
		return cabeza;
	}

	public void setCabeza(int cabeza) {
		this.cabeza = cabeza;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
}
