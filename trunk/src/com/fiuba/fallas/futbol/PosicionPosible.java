


public class PosicionPosible {
	private int idMaquina;
	private String estado;
	
	public int getIdMaquina() {
		return idMaquina;
	}
	
	public void setIdMaquina(int idMaquina) {
		this.idMaquina = idMaquina;
	}
	
	public String getPosicion() {
		return estado;
	}
	
	public void setPosicion(String estado) {
		this.estado = estado;
	}
	
	public PosicionPosible(int idMaquina, String estado) {
		this.idMaquina = idMaquina;
		this.estado = estado;
	}
}
