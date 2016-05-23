package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.util.List;

import org.jgrapht.GraphPath;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.InformarMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class EnviarNeutralizador extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			CentroControl r = (CentroControl)params[0];
			int distancia = Integer.MAX_VALUE;
			int j = 0;
			int elegido = -1;
			Celda mina = (Celda)params[1];  
			GraphPath<Celda,Integer> p;
			j = 0;
			distancia = Integer.MAX_VALUE;
			while(j < r.getEmisorNeutralizador().size()){
				p = r.getMapa().findCompletePath(mina, r.getMsgNeutralizador().get(j));
				List<Integer> path = p.getEdgeList();
				if(path.size() < distancia){
				elegido = j;
				distancia = path.size();
				}
					j++;
			}
			OrdenDesactivar orden = new OrdenDesactivar(r.getId(), mina);
			this.getComunicator().enviarInfoAotroAgente(orden,r.getEmisorNeutralizador().get(elegido));
			r.getNeutralizadores().remove(r.getEmisorNeutralizador().get(elegido));
			r.getMsgNeutralizador().remove(elegido);
			r.getEmisorNeutralizador().remove(elegido);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
