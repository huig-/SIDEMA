package icaro.aplicaciones.agentes.agenteExplorador.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class EnviarDatosCC extends Objetivo {
	
	public EnviarDatosCC() {
		super.setgoalId("EnviarDatos");
	}
	
	public EnviarDatosCC(String id) {
		super.setgoalId(id);
	}
}
