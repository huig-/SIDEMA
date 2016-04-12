package icaro.aplicaciones.agentes.agenteExplorador.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class ExplorarTerreno extends Objetivo {
	
	public ExplorarTerreno() {
		super.setgoalId("ExplorarTerreno");
	}
	
	public ExplorarTerreno(String id) {
		super.setgoalId(id);
	}

}
