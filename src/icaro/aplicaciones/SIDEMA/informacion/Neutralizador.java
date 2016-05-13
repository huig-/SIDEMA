package icaro.aplicaciones.SIDEMA.informacion;

public class Neutralizador extends Robot {

	/**
	 * @param id
	 * @param currentPos
	 * @param energy
	 * @param leader
	 */

	private int tiempoMovimiento;
	private int energiaMovimiento;
	private int tiempoDesactivacion;
	private int energiaDesactivacion;
	
	public Neutralizador(String id, Celda currentPos, int energy,
			String leader, int tiempoMovimiento, int energiaMovimiento,
			int tiempoDesactivacion, int energiaDesactivacion) {
		super(id, currentPos, energy, leader);
		this.tiempoMovimiento = tiempoMovimiento;
		this.energiaMovimiento = energiaMovimiento;
		this.tiempoDesactivacion = tiempoDesactivacion;
		this.energiaDesactivacion = energiaDesactivacion;
	}
	public Neutralizador(String id, int i, int j, int energy,
			String leader, int tiempoMovimiento, int energiaMovimiento,
			int tiempoDesactivacion, int energiaDesactivacion) {
		super(id,i,j, energy, leader);
		this.tiempoMovimiento = tiempoMovimiento;
		this.energiaMovimiento = energiaMovimiento;
		this.tiempoDesactivacion = tiempoDesactivacion;
		this.energiaDesactivacion = energiaDesactivacion;
	}

	public int getTiempoMovimiento() {
		return tiempoMovimiento;
	}

	public void setTiempoMovimiento(int tiempoMovimiento) {
		this.tiempoMovimiento = tiempoMovimiento;
	}

	public int getEnergiaMovimiento() {
		return energiaMovimiento;
	}

	public void setEnergiaMovimiento(int energiaMovimiento) {
		this.energiaMovimiento = energiaMovimiento;
	}

	public int getTiempoDesactivacion() {
		return tiempoDesactivacion;
	}

	public void setTiempoDesactivacion(int tiempoDesactivacion) {
		this.tiempoDesactivacion = tiempoDesactivacion;
	}

	public int getEnergiaDesactivacion() {
		return energiaDesactivacion;
	}

	public void setEnergiaDesactivacion(int energiaDesactivacion) {
		this.energiaDesactivacion = energiaDesactivacion;
	}

	@Override
	public String toString() {
		return "Neutralizador [tiempoMovimiento=" + tiempoMovimiento
				+ ", energiaMovimiento=" + energiaMovimiento
				+ ", tiempoDesactivacion=" + tiempoDesactivacion
				+ ", energiaDesactivacion=" + energiaDesactivacion
				+ ", getCC()=" + getCC() + ", getId()=" + getId()
				+ ", getCurrentPos()=" + getCurrentPos() + ", getEnergy()="
				+ getEnergy() + ", getInfoCompMovt()=" + getInfoCompMovt()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	

}
