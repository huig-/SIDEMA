package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA;

import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoVisualizacionSIDEMA extends ItfUsoRecursoSimple{

	public void mover(String IdentificadorAgente, int x, int y);
	public void minaEncontrada(int x,int y);
	public void mostrarMensaje(String msg);
	public void mostrarEntornoSimulacion();
	public void termina();
	
	
}
