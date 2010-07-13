package com.sample;

public class Trabajo {
	private int id;
	private int cantidadHojas;
	private int cantidadColores;
	
	public void setCantidadHojas(int cantidadHojas) {
		this.cantidadHojas = cantidadHojas;
	}
	public int getCantidadHojas() {
		return cantidadHojas;
	}
	public void setCantidadColores(int cantidadColores) {
		this.cantidadColores = cantidadColores;
	}
	public int getCantidadColores() {
		return cantidadColores;
	}

	public Trabajo(int id,int cantidadHojas, int cantidadColores)
	{
		this.id = id;
		this.cantidadHojas = cantidadHojas;
		this.cantidadColores = cantidadColores;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
