package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class EstimarMejorDestino extends TareaSincrona {
	
	private Mapa mapa;

	@Override
	public void ejecutar(Object... params) {
		
		this.mapa = (Mapa)params[0];
		
	}
	
	

}
