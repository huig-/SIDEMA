package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenSolicitarPosicion;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class SolicitarInformacionNeutralizador extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			CentroControl r = (CentroControl)params[0];
			Celda c = (Celda)params[1];
			r.getMinasPendientes().add(c);
			for(int i = 0; i < r.getNeutralizadores().size(); i++){
				OrdenSolicitarPosicion orden = new OrdenSolicitarPosicion("agenteCC",null);
				this.getComunicator().enviarInfoAotroAgente(orden,r.getNeutralizadores().get(i)); //1 es el identificador del neutralizador
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
