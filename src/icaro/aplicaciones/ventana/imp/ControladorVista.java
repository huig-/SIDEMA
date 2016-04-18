package icaro.aplicaciones.ventana.imp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;

public class ControladorVista implements ActionListener{

	private Ventana v;
	private Mapa escLog;
	
	public ControladorVista(Ventana ventana, Mapa escenario){
		this.v = ventana;
		this.v.setControlador(this);
		this.escLog = escenario;
	}
	
	public ControladorVista(Mapa escenario){
		this.v = new Ventana();
		this.escLog = new Mapa();
	}
	
	public ControladorVista(){
		this.v = new Ventana();
		this.escLog = new Mapa();
		this.v.setControlador(this);
	}
	
	public void setEscenario(Ventana ventana){
		this.v = ventana;
	}
	
	public void setMapa(Mapa mapa){
		this.escLog = mapa;	}
	
	public Ventana getVentanaEntorno(){
		return this.v;
	}
	
	public Mapa getMapa(){
		return this.escLog;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub
		
			if ( evento.getActionCommand().equals("Cargar") ) {
				File file = this.v.cargarEscenario();
				//this.modelo.setArchivo(file);
			}
			
			if ( evento.getActionCommand().equals("Terminar") ) {
				this.v.mostrarConfirmacionTerminar();
				//this.modelo.setArchivo(file);
			}
		
	}

}
