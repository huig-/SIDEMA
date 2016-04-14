package icaro.aplicaciones.ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;

public class ControladorVista implements ActionListener{

	private Ventana v;
	private Mapa escLog;
	
	public ControladorVista(Ventana ventana, Mapa escenario){
		this.v = ventana;
		this.escLog = escenario;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
