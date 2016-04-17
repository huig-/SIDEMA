package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class EnviarNeutralizador extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			Celda c = (Celda)params[0];
			//Elegir...OrdenElegirNeutralizador orden = new OrdenElegirNeutralizador("CC", m);
			OrdenDesactivar orden = new OrdenDesactivar("CC",c);
			this.getComunicator().enviarInfoAotroAgente(orden, "1"); //1 es el identificador del neutralizador
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
