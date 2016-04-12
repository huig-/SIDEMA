package icaro.aplicaciones.agentes.agenteExplorador.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class BuscarMina extends Objetivo {
	
	public BuscarMina() {
		super.setgoalId("BuscarMina");
	}
	
	public BuscarMina(String id) {
		super.setgoalId(id);
	}

}
