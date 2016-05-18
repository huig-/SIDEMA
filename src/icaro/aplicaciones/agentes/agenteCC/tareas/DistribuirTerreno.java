package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenComenzarSimulacion;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DistribuirTerreno extends TareaSincrona {
	
	private Mapa m;

	@Override
	public void ejecutar(Object... params) {
		try {
			m = (Mapa)params[0];
			OrdenComenzarSimulacion ordenI = (OrdenComenzarSimulacion)params[1];
			this.getEnvioHechos().eliminarHechoWithoutFireRules(ordenI);

			OrdenExplorar orden = new OrdenExplorar("agenteCC", m);
			this.getComunicator().enviarInfoAotroAgente(orden, "agenteExplorador0"); //0 es el identificador del explorador
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
