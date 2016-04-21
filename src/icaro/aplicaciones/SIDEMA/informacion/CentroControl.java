/**
 * 
 */
package icaro.aplicaciones.SIDEMA.informacion;

import java.util.ArrayList;

/**
 * @author Mike
 *
 */
public class CentroControl extends Robot {

	/**
	 * @param id
	 * @param currentPos
	 * @param energy
	 * @param leader
	 */
	private ArrayList<String> exploradores;
	private ArrayList<String> neutralizadores;
	public CentroControl(String id, Celda currentPos, int energy, String leader) {
		super(id, currentPos, energy, leader);
		this.exploradores = new ArrayList<String>();
		this.neutralizadores = new ArrayList<String>();
		this.neutralizadores.add("agenteNeutralizador0");
		this.neutralizadores.add("agenteNeutralizador1");
	}
	
	public ArrayList<String> getExploradores() {
		return exploradores;
	}
	public void setExploradores(ArrayList<String> exploradores) {
		this.exploradores = exploradores;
	}
	public ArrayList<String> getNeutralizadores() {
		return neutralizadores;
	}
	public void setNeutralizadores(ArrayList<String> neutralizadores) {
		this.neutralizadores = neutralizadores;
	}

}
