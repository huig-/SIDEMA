package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.util.List;

import org.jgrapht.GraphPath;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.InformarNeutralizadorLibre;
import icaro.aplicaciones.SIDEMA.informacion.InformarPosicionActual;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.InformarMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ActualizarMensajesNeutralizadores extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			CentroControl cc = (CentroControl)params[0];
			Celda celda = (Celda)params[1];
			InformarPosicionActual n = (InformarPosicionActual)params[2];
			
			cc.actualizarMsg((Celda)celda,n.getIdentEmisor());
			this.getEnvioHechos().eliminarHechoWithoutFireRules(n);
			this.getEnvioHechos().actualizarHecho(cc);
			if(cc.getMinasPendientes().size() > 0){
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
