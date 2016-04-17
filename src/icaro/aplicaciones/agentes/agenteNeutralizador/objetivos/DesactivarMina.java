package icaro.aplicaciones.agentes.agenteNeutralizador.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class DesactivarMina extends Objetivo {
	
	public DesactivarMina() {
		super.setgoalId("DesactivarMina");
	}
	
	public DesactivarMina(String id) {
		super.setgoalId(id);
	}
}
