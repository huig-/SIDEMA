package icaro.aplicaciones.agentes.agenteNeutralizador.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.aplicaciones.agentes.agenteCC.tareas.EnviarNeutralizador;
import icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.ItfUsoRecursoVisualizacionSIDEMA;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DesactivarMina extends TareaSincrona {
	
//	private ArrayList<Celda> celdasAExplorar; v1.0

	@Override
	public void ejecutar(Object... params) {
		try {
			Celda c = (Celda)params[0];
			Robot r = (Robot)params[1];
			//INCLUIR MOVIMIENTO DEL NEUTRALIZADOR.
			ItfUsoRecursoVisualizacionSIDEMA visualizador = (ItfUsoRecursoVisualizacionSIDEMA)
					NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
							NombresPredefinidos.ITF_USO + "RecursoVisualizacionSIDEMA1");
			for(int j = (int)r.getCurrentPos().getX(); j <= c.getY();j++)
				if (visualizador != null) {
					try {
					    Thread.sleep(1000);                 //1000 milliseconds is one second.
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					visualizador.mover("neutralizador",(int)r.getCurrentPos().getX(),j);
					r.setCurrentPos(new Celda((int)r.getCurrentPos().getX(),j));
					c.desactivarMina();
				}
			for(int i = (int)r.getCurrentPos().getY(); i <= c.getX(); i++)
					if (visualizador != null) {
						try {
						    Thread.sleep(1000);                 //1000 milliseconds is one second.
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
						visualizador.mover("neutralizador",i,(int)c.getY());
						r.setCurrentPos(new Celda(i,(int)c.getY()));
						c.desactivarMina();
					}
		}
		catch (Exception e) {
			   e.printStackTrace();
	       }
	}

}