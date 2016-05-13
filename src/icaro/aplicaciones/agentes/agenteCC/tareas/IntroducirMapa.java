package icaro.aplicaciones.agentes.agenteCC.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenComenzarSimulacion;
import icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.ItfUsoRecursoPersistenciaSIDEMA;
import icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.ItfUsoRecursoVisualizacionSIDEMA;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class IntroducirMapa extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {

			OrdenComenzarSimulacion ordenI = (OrdenComenzarSimulacion)params[0];
			this.getEnvioHechos().eliminarHechoWithoutFireRules(ordenI);
			 ItfUsoRecursoPersistenciaSIDEMA itfUsoRecursoPersistenciaSIDEMA = (ItfUsoRecursoPersistenciaSIDEMA) this.repoInterfaces.obtenerInterfaz(NombresPredefinidos.ITF_USO + "RecursoPersistenciaSIDEMA1");
			this.itfProcObjetivos.eliminarHecho(itfUsoRecursoPersistenciaSIDEMA.getEscenario());
			this.itfProcObjetivos.insertarHecho(itfUsoRecursoPersistenciaSIDEMA.getEscenario());
		
		     
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
