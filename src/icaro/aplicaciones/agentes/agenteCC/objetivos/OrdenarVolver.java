package icaro.aplicaciones.agentes.agenteCC.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class OrdenarVolver extends Objetivo {

	public OrdenarVolver() {
		super.setgoalId("OrdenarVolar");
	}
	
	public OrdenarVolver(String id) {
		super.setgoalId(id);
	}
}
