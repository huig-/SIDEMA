package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
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
			CentroControl cc = (CentroControl)params[1];
			cc.setIdAgenteReactivo(ordenI.getIdentEmisor());
			ArrayList<ArrayList<String>> robots = (ArrayList<ArrayList<String>>)ordenI.getJustificacion();
			cc.getExploradoresLibres().addAll(robots.get(0));
			cc.setNumExploradoresActivos(robots.get(0).size());
			cc.getNeutralizadores().addAll(robots.get(1));
			ItfUsoRecursoPersistenciaSIDEMA itfUsoRecursoPersistenciaSIDEMA = (ItfUsoRecursoPersistenciaSIDEMA) this.repoInterfaces.obtenerInterfaz(NombresPredefinidos.ITF_USO + "RecursoPersistenciaSIDEMA1");
			this.itfProcObjetivos.eliminarHecho(ordenI);
			this.itfProcObjetivos.eliminarHecho(itfUsoRecursoPersistenciaSIDEMA.getEscenario());
			this.itfProcObjetivos.insertarHecho(itfUsoRecursoPersistenciaSIDEMA.getEscenario());
			Mapa m = itfUsoRecursoPersistenciaSIDEMA.getEscenario();
			for(String neut : cc.getNeutralizadores()){
				OrdenComenzarSimulacion orden = new OrdenComenzarSimulacion(cc.getId(),m);
				this.getComunicator().enviarInfoAotroAgente(orden, neut);
			}
			for(String neut : cc.getExploradoresLibres()){
				OrdenComenzarSimulacion orden = new OrdenComenzarSimulacion(cc.getId(),itfUsoRecursoPersistenciaSIDEMA.getEscenario());
				this.getComunicator().enviarInfoAotroAgente(orden, neut);
			}
		    cc.setMapa(itfUsoRecursoPersistenciaSIDEMA.getEscenario());
		    this.getEnvioHechos().actualizarHecho(cc);
		    cc.getMapa().accesibilidad(cc.getCurrentPos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
