package icaro.aplicaciones.agentes.agenteCC.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class EnviarNeutralizador extends Objetivo {

	public EnviarNeutralizador() {
		super.setgoalId("EnviarNeutralizador");
	}
	
	public EnviarNeutralizador(String id) {
		super.setgoalId(id);
	}
}
