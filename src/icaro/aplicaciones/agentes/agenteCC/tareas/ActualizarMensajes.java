package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.util.List;

import org.jgrapht.GraphPath;

import icaro.aplicaciones.SIDEMA.informacion.Candidatos;
import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.InformacionAgente;
import icaro.aplicaciones.SIDEMA.informacion.InformarNeutralizadorLibre;
import icaro.aplicaciones.SIDEMA.informacion.InformarPosicionActual;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.InformarMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.aplicaciones.SIDEMA.informacion.VocabularioSIDEMA;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ActualizarMensajes extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			CentroControl cc = (CentroControl)params[0];
			InformacionAgente n = (InformacionAgente)params[1];	
			cc.actualizarMsg(params[2], n.getIdentEmisor());
			this.getEnvioHechos().eliminarHechoWithoutFireRules(n);
			this.getEnvioHechos().actualizarHecho(cc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
