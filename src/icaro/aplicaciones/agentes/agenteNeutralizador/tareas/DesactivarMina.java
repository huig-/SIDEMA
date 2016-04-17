package icaro.aplicaciones.agentes.agenteNeutralizador.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenMinaEncontrada;
import icaro.aplicaciones.agentes.agenteCC.tareas.EnviarNeutralizador;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class DesactivarMina extends TareaSincrona {
	
//	private ArrayList<Celda> celdasAExplorar; v1.0

	@Override
	public void ejecutar(Object... params) {
		try {
			Celda c = (Celda)params[0];
			//INCLUIR MOVIMIENTO DEL NEUTRALIZADOR.

			for (int k = 0; k < 50000; k++); //Delay 50000
			c.desactivarMina();
		}
		catch (Exception e) {
			   e.printStackTrace();
	       }
	}

}