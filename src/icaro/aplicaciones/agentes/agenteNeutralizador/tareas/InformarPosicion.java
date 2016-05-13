package icaro.aplicaciones.agentes.agenteNeutralizador.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.InformarPosicionActual;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InformarPosicion extends TareaSincrona {
	
	@Override
	public void ejecutar(Object... params) {
		try {
			Neutralizador r = (Neutralizador)params[0];
			r.setMapa((Mapa)params[1]);
			InformarPosicionActual orden = new InformarPosicionActual(r.getId(),r.getCurrentPos());
			this.getComunicator().enviarInfoAotroAgente(orden,r.getCC()); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}