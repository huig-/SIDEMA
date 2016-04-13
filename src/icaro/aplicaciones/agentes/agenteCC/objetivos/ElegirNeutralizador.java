package icaro.aplicaciones.agentes.agenteCC.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class ElegirNeutralizador extends Objetivo {
	
	public ElegirNeutralizador() {
		super.setgoalId("ElegirNeutralizador");
	}
	
	public ElegirNeutralizador(String id) {
		super.setgoalId(id);
	}
}
