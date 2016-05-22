package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenComenzarSimulacion;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenSolicitarEstimacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import java.util.ArrayList;

public class DistribuirTerreno extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			CentroControl cc = (CentroControl)params[0];
			for(String exp : (ArrayList<String>)params[1]){
				OrdenSolicitarEstimacion orden = new OrdenSolicitarEstimacion("agenteCC");
				this.getComunicator().enviarInfoAotroAgente(orden,exp); 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
