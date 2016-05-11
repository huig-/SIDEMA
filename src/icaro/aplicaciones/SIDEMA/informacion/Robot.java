package icaro.aplicaciones.SIDEMA.informacion;

import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;

public abstract class Robot {
	
	private String id;
	private Celda currentPos;
	private int energy;
	private InfoCompMovimiento infoCompMovt;
	private String nameCC;
	
	public Robot(String id, Celda currentPos, int energy, String leader) {
		this.id = id;
		this.currentPos = currentPos;
		this.energy = energy;
		this.nameCC = leader;
	}
	
	public String getCC(){
		return this.nameCC;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public synchronized Celda getCurrentPos() {
		return currentPos;
	}

	public synchronized void setCurrentPos(Celda currentPos) {
		this.currentPos = currentPos;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		if(energy > 0) this.energy = energy;
		else this.energy = 0;
	}

	public InfoCompMovimiento getInfoCompMovt() {
		return infoCompMovt;
	}

	public void setInfoCompMovt(InfoCompMovimiento infoCompMovt) {
		this.infoCompMovt = infoCompMovt;
	}

	@Override
	public String toString() {
		return "Robot [id=" + id + ", currentPos=" + currentPos + ", energy="
				+ energy + ", infoCompMovt=" + infoCompMovt + ", nameCC="
				+ nameCC + "]";
	}

}
