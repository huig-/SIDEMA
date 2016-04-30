package icaro.aplicaciones.agentes.agenteCC.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

public class ConseguirMapa extends Objetivo{

	public ConseguirMapa(){
		super.setgoalId("Conseguir Mapa");
	}
	
	public ConseguirMapa(String id){
		super.setgoalId(id);
	}
	
}
