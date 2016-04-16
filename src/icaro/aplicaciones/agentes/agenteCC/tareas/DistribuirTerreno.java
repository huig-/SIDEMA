package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DistribuirTerreno extends TareaSincrona {
	
	private Mapa m;

	@Override
	public void ejecutar(Object... params) {
		m = (Mapa)params[0];
	}

}
