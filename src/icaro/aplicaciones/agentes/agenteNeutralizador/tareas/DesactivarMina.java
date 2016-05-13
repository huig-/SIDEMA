package icaro.aplicaciones.agentes.agenteNeutralizador.tareas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jgrapht.GraphPath;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.InformarNeutralizadorLibre;
import icaro.aplicaciones.SIDEMA.informacion.InformarMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.aplicaciones.agentes.agenteCC.tareas.EnviarNeutralizador;
import icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.ItfUsoRecursoVisualizacionSIDEMA;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DesactivarMina extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			Celda c = (Celda)params[0];
			Neutralizador r = (Neutralizador)params[1];
			Mapa m = Mapa.instance;
			//INCLUIR MOVIMIENTO DEL NEUTRALIZADOR.
			ItfUsoRecursoVisualizacionSIDEMA visualizador = (ItfUsoRecursoVisualizacionSIDEMA)
					NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
							NombresPredefinidos.ITF_USO + "RecursoVisualizacionSIDEMA1");
			GraphPath<Celda,Integer> p = m.findPath(r.getCurrentPos(), c);
			List<Integer> path = p.getEdgeList();
			ListIterator<Integer> it = path.listIterator();
			Celda pos;
			if(path.size() > 0){
				do{
					int edge = it.next();
					if(p.getGraph().getEdgeTarget(edge).equals(r.getCurrentPos())){ pos = p.getGraph().getEdgeSource(edge);}
					else pos = p.getGraph().getEdgeTarget(edge);
					if (visualizador != null) {					
						try {
						    Thread.sleep(r.getTiempoMovimiento());   
						    r.setEnergy(r.getEnergy()-r.getEnergiaMovimiento());
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
						visualizador.mover("neutralizador",(int)pos.getX(),(int)pos.getY());
						r.setCurrentPos(pos);	
					}
				}while(it.hasNext());
			}
			try {
				visualizador.mover("neutralizador",(int)c.getX(),(int)c.getY());
			    Thread.sleep(r.getTiempoDesactivacion());
			    r.setEnergy(r.getEnergy()-r.getEnergiaDesactivacion());             
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			c.desactivarMina();
			InformarNeutralizadorLibre orden = new InformarNeutralizadorLibre(r.getId());
			this.getComunicator().enviarInfoAotroAgente(orden,r.getCC());
		}
		catch (Exception e) {
			   e.printStackTrace();
	       }
	}

}