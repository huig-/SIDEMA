package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenComenzarSimulacion;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenSolicitarEstimacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DistribuirTerreno extends TareaSincrona {
	
	private Mapa m;

	@Override
	public void ejecutar(Object... params) {
		try {
			m = (Mapa)params[0];
			CentroControl cc = (CentroControl)params[1];
			//OrdenComenzarSimulacion ordenI = (OrdenComenzarSimulacion)params[1];
			//this.getEnvioHechos().eliminarHechoWithoutFireRules(ordenI);
			for(String exp : cc.getExploradoresLibres()){
				OrdenSolicitarEstimacion orden = new OrdenSolicitarEstimacion("agenteCC", m);
				this.getComunicator().enviarInfoAotroAgente(orden,exp); 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
