package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.util.List;

import org.jgrapht.GraphPath;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.InformacionAgente;
import icaro.aplicaciones.SIDEMA.informacion.InformarNeutralizadorLibre;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.InformarMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class GuardarRobotLibre extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			String tipo = (String)params[0];
			CentroControl cc = (CentroControl)params[1];
			Robot n = (Robot)params[2];
			InformacionAgente o = (InformacionAgente)params[3];
			if(tipo.equalsIgnoreCase("neutralizador"))
				cc.getNeutralizadores().add(n.getId());
			else
				cc.getExploradores().add(n.getId());
			this.getEnvioHechos().actualizarHecho(n);
			this.getEnvioHechos().eliminarHechoWithoutFireRules(o);
			this.getEnvioHechos().actualizarHecho(cc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
