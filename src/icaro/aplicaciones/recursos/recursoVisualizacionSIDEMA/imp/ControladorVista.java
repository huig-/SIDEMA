package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.NotificadorInfoUsuarioSimulador;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;

public class ControladorVista implements ActionListener{

	private Ventana v;
	private Mapa escLog;
	private NotificadorInfoUsuarioSimulador notifEvt;
	
	public ControladorVista(Ventana ventana, Mapa escenario){
		this.v = ventana;
		this.v.setControlador(this);
		this.escLog = escenario;
	}
	
	public ControladorVista(NotificadorInfoUsuarioSimulador notifEvt){
		this.notifEvt = notifEvt;
	}
	
	public ControladorVista(Mapa escenario){
		this.v = new Ventana();
		this.escLog = new Mapa();
	}
	
	public ControladorVista(){
		this.v = new Ventana();
		//this.escLog = new Mapa();
		//this.escLog= Mapa.instance;
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
	
	public void pintar(){
		this.v.repaint();
	}
	
	public void setModelo(){
		this.v.setModelo(escLog);
	}
	
	public void setModelo(Mapa m){
		this.escLog = m;
		this.v.setModelo(escLog);
		this.v.repaint();
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
			if ( evento.getActionCommand().equals("Simular")){
				this.notifEvt.sendPeticionArranqueSimulacion();
			}
		
	}

}
