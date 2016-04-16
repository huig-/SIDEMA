package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ExplorarTerreno extends TareaSincrona {
	
//	private ArrayList<Celda> celdasAExplorar; v1.0
	private Mapa celdasAExplorar;

	@Override
	public void ejecutar(Object... params) {
		try {
			//celdasAExplorar = (ArrayList<Celda>)params[0]; v1.0
			celdasAExplorar = (Mapa)params[0];
			int num_columns = celdasAExplorar.getColumns();
			int num_rows = celdasAExplorar.getRows();
			for (int i = 0; i < num_rows; i++) {
				for (int j = 0; j < num_columns; j++) {
					for (int k = 0; k < 10000; k++); //Delay 10000
					if (celdasAExplorar.getCelda(i, j).getMina()) {
						//TODO
					}
					//TODO
					//decirle al recurso de visualizacion que hay que acceder a la casilla i,j
				}
			}
		}
		catch (Exception e) {
			   e.printStackTrace();
	       }
	}

}
