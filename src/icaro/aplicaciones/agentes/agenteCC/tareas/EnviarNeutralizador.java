package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class EnviarNeutralizador extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			Celda c = (Celda)params[0];
			CentroControl r = (CentroControl)params[1];
			//Elegir...OrdenElegirNeutralizador orden = new OrdenElegirNeutralizador("CC", m);
			OrdenDesactivar orden = new OrdenDesactivar("agenteCC",c);
			this.getComunicator().enviarInfoAotroAgente(orden,r.getNeutralizadores().get(0)); //1 es el identificador del neutralizador
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
