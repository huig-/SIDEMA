package icaro.aplicaciones.ventana.imp;

import java.rmi.RemoteException;

import icaro.aplicaciones.ventana.ItfVentana;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

public class ClaseGeneradoraSDM extends ImplRecursoSimple implements ItfVentana{

	public ClaseGeneradoraSDM(String idRecurso) throws RemoteException {
		super(idRecurso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mover(String IdentificadorAgente, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minaEncontrada(int x, int y) {
		this.control.getVentanaEntorno().getEscenario().minaEncontrada(x, y);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarMensaje(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarEntornoSimulacion() {
		this.control = new ControladorVista();
		// TODO Auto-generated method stub
		
	}
	
	ControladorVista control;

}
