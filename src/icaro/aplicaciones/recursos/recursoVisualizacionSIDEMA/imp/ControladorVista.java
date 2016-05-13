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
		this.v = new Ventana();
		this.v.setControlador(this);
	}
	
	public ControladorVista(Mapa escenario){
		this.v = new Ventana();
		this.escLog = new Mapa();
		this.v.setModelo(escenario);
		this.v.setControlador(this);
	}
	
	public ControladorVista(){
		this.v = new Ventana();
		this.v.setControlador(this);
	}
	
	public void setEscenario(Ventana ventana){
		this.v = ventana;
	}
	
	public void setModelo(Mapa mapa){
		this.escLog = mapa;
		this.v.setModelo(escLog);
		}
	
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
	
	public void actualizarPantalla(){
		this.v.actualizarPantalla();
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
