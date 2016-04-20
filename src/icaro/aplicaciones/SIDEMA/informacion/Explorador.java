/**
 * 
 */
package icaro.aplicaciones.SIDEMA.informacion;

/**
 * @author Mike
 *
 */
public class Explorador extends Robot {

	/**
	 * @param id
	 * @param currentPos
	 * @param energy
	 * @param leader
	 */

	private int tiempoMovimiento;
	private int energiaMovimiento;
	private int tiempoExploracion;
	private int energiaExploracion;
	
	public Explorador(String id,Celda currentPos, int energy,
			String leader,int timeMov,int enMov, int timeExp, int enExp) {
		super(id,currentPos, energy, leader);
		this.tiempoExploracion = timeExp;
		this.tiempoMovimiento = timeMov;
		this.energiaMovimiento = enMov;
		this.energiaExploracion = enExp;
		
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
	public int getTiempoExploracion() {
		return tiempoExploracion;
	}
	public void setTiempoExploracion(int tiempoExploracion) {
		this.tiempoExploracion = tiempoExploracion;
	}
	public int getEnergiaExploracion() {
		return energiaExploracion;
	}
	public void setEnergiaExploracion(int energiaExploracion) {
		this.energiaExploracion = energiaExploracion;
	}
	
	@Override
	public String toString() {
		return "Explorador [tiempoMovimiento=" + tiempoMovimiento
				+ ", energiaMovimiento=" + energiaMovimiento
				+ ", tiempoExploracion=" + tiempoExploracion
				+ ", energiaExploracion=" + energiaExploracion + ", getCC()="
				+ getCC() + ", getId()=" + getId() + ", getCurrentPos()="
				+ getCurrentPos() + ", getEnergy()=" + getEnergy()
				+ ", getInfoCompMovt()=" + getInfoCompMovt() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
