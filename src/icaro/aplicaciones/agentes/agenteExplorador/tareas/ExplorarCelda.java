package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import java.util.List;
import java.util.ListIterator;

import org.jgrapht.GraphPath;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Explorador;
import icaro.aplicaciones.SIDEMA.informacion.InformarExploradorLibre;
import icaro.aplicaciones.SIDEMA.informacion.InformarMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.ItfUsoRecursoVisualizacionSIDEMA;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ExplorarCelda extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			//Recurso de visualizacion
			ItfUsoRecursoVisualizacionSIDEMA visualizador = (ItfUsoRecursoVisualizacionSIDEMA)
					NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
							NombresPredefinidos.ITF_USO + "RecursoVisualizacionSIDEMA1");
			//Cogemos datos
			Explorador explorador = (Explorador)params[0];
			Celda celdaAExplorar = (Celda)params[1]; 
			Mapa mapa = (Mapa)params[2];
			Mapa copia_mapa = new Mapa(mapa);
			copia_mapa.updateGrafo((int)celdaAExplorar.getX(), (int)celdaAExplorar.getY());
			//Calculamos la ruta
			GraphPath<Celda, Integer> path = copia_mapa.findPath(explorador.getCurrentPos(), celdaAExplorar);
			List<Integer> edge_path = path.getEdgeList();
			ListIterator<Integer> it = edge_path.listIterator();
			Celda pos;
			if(edge_path.size() > 0) {
				do{
					int edge = it.next();
					if(path.getGraph().getEdgeTarget(edge).equals(explorador.getCurrentPos()))
						pos = path.getGraph().getEdgeSource(edge);
					else pos = path.getGraph().getEdgeTarget(edge);
					if (visualizador != null) {					
						try {
						    Thread.sleep(explorador.getTiempoMovimiento());   
						    explorador.setEnergy(explorador.getEnergy()-explorador.getEnergiaMovimiento());
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
						visualizador.mover("explorador",(int)pos.getX(),(int)pos.getY());
						explorador.setCurrentPos(pos);	
					}
				} while(it.hasNext());
			}
			visualizador.mover("explorador",(int)celdaAExplorar.getX(),(int)celdaAExplorar.getY());
			try {
				Thread.sleep(explorador.getTiempoExploracion());
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		    explorador.setEnergy(explorador.getEnergy()-explorador.getEnergiaExploracion()); 
		    boolean hayMina = celdaAExplorar.getMina();
		    if (hayMina) {
		    	InformarMinaEncontrada informarMina = new InformarMinaEncontrada(explorador.getId(), celdaAExplorar);
		    	this.getComunicator().enviarInfoAotroAgente(informarMina, explorador.getCC());
		    }
		    InformarExploradorLibre informarLibre = new InformarExploradorLibre(explorador.getId(), null);
		    this.getComunicator().enviarInfoAotroAgente(informarLibre, explorador.getCC());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
