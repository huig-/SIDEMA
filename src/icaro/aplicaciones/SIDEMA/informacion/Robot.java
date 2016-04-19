package icaro.aplicaciones.SIDEMA.informacion;

import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;

public class Robot {
	
	private String id;
	private Celda currentPos;
	private int energy;
	private String type;
	private InfoCompMovimiento infoCompMovt;
	
	public Robot(String id, String type, Celda currentPos, int energy) {
		this.id = id;
		this.type = type;
		this.currentPos = currentPos;
		this.energy = energy;
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
		this.energy = energy;
	}

	public InfoCompMovimiento getInfoCompMovt() {
		return infoCompMovt;
	}

	public void setInfoCompMovt(InfoCompMovimiento infoCompMovt) {
		this.infoCompMovt = infoCompMovt;
	}

	public String toString() {
		return "Robot: id->" + this.getId() + 
    			" ; energylevel->" + this.getEnergy() + 
    			" ; type->" + this.getType() + 
    			" ; coordinate->" + this.getCurrentPos(); 
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
