package icaro.aplicaciones.agentes.agenteNeutralizador.tareas;

import icaro.aplicaciones.SIDEMA.informacion.CeldaEnergia;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.InformarPosicionActual;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class InformarPosicion extends TareaSincrona {
	
	@Override
	public void ejecutar(Object... params) {
		try {
			Neutralizador r = (Neutralizador)params[0];
			InformarPosicionActual orden = new InformarPosicionActual(r.getId(),
					new CeldaEnergia(r.getCurrentPos(),r.getEnergiaMovimiento(),r.getEnergiaDesactivacion(),r.getEnergy()));
			this.getComunicator().enviarInfoAotroAgente(orden,r.getCC()); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}