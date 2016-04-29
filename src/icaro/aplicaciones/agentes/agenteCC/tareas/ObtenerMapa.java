package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.io.File;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.ItfUsoRecursoPersistenciaSIDEMA;
import icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.ItfUsoRecursoVisualizacionSIDEMA;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ObtenerMapa extends TareaSincrona{

	@Override
	public void ejecutar(Object... params) {
		// TODO Auto-generated method stub
		File fichero;
		Mapa m;
		try {
			ItfUsoRecursoVisualizacionSIDEMA visualizador = (ItfUsoRecursoVisualizacionSIDEMA)
					NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
							NombresPredefinidos.ITF_USO + "RecursoVisualizacionSIDEMA1");
			
			ItfUsoRecursoPersistenciaSIDEMA persistencia = (ItfUsoRecursoPersistenciaSIDEMA)
					NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
							NombresPredefinidos.ITF_USO + "RecursoPersistenciaSIDEMA1");
			
			if(!visualizador.getMapaPintado()){
				visualizador.mostrarEntornoSimulacion();
				fichero = visualizador.getFicheroEscenario();
				persistencia.parserCSVModelo(fichero);
				m = persistencia.getEscenario();
				visualizador.setMapa(m);
				visualizador.pintarEscenario();
				
				
			}
			else{
				m = visualizador.getMapa();
				persistencia.setEscenario(m);
			}
		} catch (Exception e) {
			 this.trazas.mostrarMensajeError("Error en la tarea ObtenerMapa. Mensaje: "+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
