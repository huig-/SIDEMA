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
	private ArrayList<Celda> msgNeutralizador;
	private ArrayList<String> emisorNeutralizador;
	private ArrayList<Celda> minasPendientes;
	
	public ArrayList<Celda> getMinasPendientes() {
		return minasPendientes;
	}

	public void setMinasPendientes(ArrayList<Celda> minasPendientes) {
		this.minasPendientes = minasPendientes;
	}

	public CentroControl(String id, Celda currentPos, int energy, String leader) {
		super(id, currentPos, energy, leader);
		this.exploradores = new ArrayList<String>();
		this.neutralizadores = new ArrayList<String>();
		this.msgNeutralizador = new ArrayList<Celda>();
		this.minasPendientes = new ArrayList<Celda>();
		this.emisorNeutralizador = new ArrayList<String>();
		this.neutralizadores.add("agenteNeutralizador0");
		this.neutralizadores.add("agenteNeutralizador1");
	}
	
	public ArrayList<Celda> getMsgNeutralizador() {
		return msgNeutralizador;
	}
	
	public void actualizarMsg(Celda msg, String neut){
		if(!this.emisorNeutralizador.contains(neut)){
			this.msgNeutralizador.add(msg);
			this.emisorNeutralizador.add(neut);
		}
	}

	public void setMsgNeutralizador(ArrayList<Celda> msgNeutralizador) {
		this.msgNeutralizador = msgNeutralizador;
	}

	public ArrayList<String> getEmisorNeutralizador() {
		return emisorNeutralizador;
	}

	public void setEmisorNeutralizador(ArrayList<String> emisorNeutralizador) {
		this.emisorNeutralizador = emisorNeutralizador;
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
	
	public boolean recibidosNeutralizadores(){
		return this.getMsgNeutralizador().size() == this.getNeutralizadores().size();
	}
}
