package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.VocabularioSIDEMA;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class FinalizarSimulacion extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
		CentroControl cc = (CentroControl)params[0];
		this.getComunicator().informaraOtroAgenteReactivo(new InfoContEvtMsgAgteReactivo(VocabularioSIDEMA.informacionFinSimulacion), 
				cc.getIdAgenteReactivo());
		//this.getComunicator().informaraOtroAgenteReactivo(new InfoContEvtMsgAgteReactivo(VocabularioSIDEMA.informacionFinSimulacion), 
		//		"AgenteControladorSimuladorSIDEMA1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
