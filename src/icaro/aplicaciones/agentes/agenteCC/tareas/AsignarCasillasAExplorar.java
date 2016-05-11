package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.TreeMap;

import icaro.aplicaciones.SIDEMA.informacion.Candidatos;
import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class AsignarCasillasAExplorar extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			Candidatos []c = new Candidatos[params.length];
			double []ganancias = new double[params.length];
			TreeMap<Double, Integer> map = new TreeMap<Double, Integer>(); //para ordenar con indices
			for (int i = 0; i < params.length; i++) {
				c[i] = (Candidatos)params[i];
				c[i].sort();
				ganancias[i] = c[i].getCandidatos().get(0).getValue();
				map.put(ganancias[i], i); 
			}
			//1 paso, elegimos al mejor
			int index = map.lastEntry().getValue();
			//TODO enviar un mensaje al candidato[index] con su mejor celda a explorar
			
			//2 paso, descuentos
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
