package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA;

import java.io.File;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoVisualizacionSIDEMA extends ItfUsoRecursoSimple{

	public void mover(String identAgente, int x, int y)throws Exception;
	public void minaEncontrada(int x,int y)throws Exception;
	public void mostrarMensaje(String msg)throws Exception;
	public void mostrarEntornoSimulacion()throws Exception;
	public void termina()throws Exception;
	public File proporcionarFicheroEscenario()throws Exception;
	public void obtenerMapa(Mapa m) throws Exception;
	
	
}
