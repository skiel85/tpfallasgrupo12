package com.fiuba.fallas.futbol;

public class PosicionPosible {
	private int idJugador;
	private String posicion;
	
	public int getIdJugador() {
		return idJugador;
	}
	
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	
	public String getPosicion() {
		return posicion;
	}
	
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	public PosicionPosible(int idJugador, String posicion) {
		this.idJugador = idJugador;
		this.posicion = posicion;
	}
}
