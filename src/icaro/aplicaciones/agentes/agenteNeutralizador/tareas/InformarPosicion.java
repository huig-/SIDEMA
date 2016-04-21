package icaro.aplicaciones.agentes.agenteNeutralizador.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.OrdenInformarPosicionActual;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InformarPosicion extends TareaSincrona {
	
	@Override
	public void ejecutar(Object... params) {
		try {
			Neutralizador r = (Neutralizador)params[0];
			OrdenInformarPosicionActual orden = new OrdenInformarPosicionActual(r.getId(),r.getCurrentPos());
			this.getComunicator().enviarInfoAotroAgente(orden,r.getCC()); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}