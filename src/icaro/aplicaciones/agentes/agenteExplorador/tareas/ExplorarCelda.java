package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import org.jgrapht.GraphPath;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Explorador;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ExplorarCelda extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		Explorador robot = (Explorador)params[0];
		Celda celdaAExplorar = (Celda)params[1]; 
		Mapa mapa = (Mapa)params[2];
		mapa.updateGrafo((int)celdaAExplorar.getX(), (int)celdaAExplorar.getY());
		GraphPath<Celda, Integer> path = mapa.findPath(robot.getCurrentPos(), celdaAExplorar);
	}
}
