package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import java.util.ArrayList;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenDesactivar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.aplicaciones.SIDEMA.informacion.OrdenMinaEncontrada;
import icaro.aplicaciones.agentes.agenteCC.tareas.EnviarNeutralizador;
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
			int ini;
			int end;
			int inc;
			for (int i = 0; i < num_rows; i++) {
				if(i%2==0){
					ini = 0;
					end = num_columns;
					inc = 1;
				}
				else{
					ini = num_columns - 1;
					end = -1;
					inc = -1;
				}
				int j = ini;
				while(Math.abs(end-j) > 0){
					try {
					    Thread.sleep(500);                 //1000 milliseconds is one second.
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					if (celdasAExplorar.getCelda(i, j).getMina()) {
						//Si encontramos una mina, se produce un retraso mayor.
						Celda c = celdasAExplorar.getCelda(i,j);
						OrdenMinaEncontrada orden = new OrdenMinaEncontrada("agenteExplorador0", c);
						this.getComunicator().enviarInfoAotroAgente(orden, "agenteCC"); //0 es el identificador del explorador
						try {
						    Thread.sleep(1000);                 //1000 milliseconds is one second.
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
					}
					
				j = j + inc;
				}
			}
			for (int i = 0; i < num_rows; i++) {
				if(i%2==0){
					ini = 0;
					end = num_columns;
					inc = 1;
				}
				else{
					ini = num_columns - 1;
					end = -1;
					inc = -1;
				}
			}
		}
		catch (Exception e) {
			   e.printStackTrace();
	       }
	}

}