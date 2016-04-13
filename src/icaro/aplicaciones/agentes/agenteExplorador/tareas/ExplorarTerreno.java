package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ExplorarTerreno extends TareaSincrona {
	
	private ArrayList<Celda> celdasAExplorar;

	@Override
	public void ejecutar(Object... params) {
		try {
			 Objetivo objetivoEjecutantedeTarea = (Objetivo)params[0];
			 celdasAExplorar = (ArrayList<Celda>)params[1];
		}
		catch (Exception e) {
			   e.printStackTrace();
	       }
	}

}
