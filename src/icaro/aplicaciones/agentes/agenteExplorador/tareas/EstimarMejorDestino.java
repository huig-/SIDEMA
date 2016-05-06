package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Explorador;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

public class EstimarMejorDestino extends TareaSincrona {
	
	private Mapa mapa;
	private Explorador robot;

	@Override
	public void ejecutar(Object... params) {
		
		this.mapa = (Mapa)params[0];
		this.robot = (Explorador)params[1];
		//Elaboramos una lista con los adyacentes a los exploradores y el coste del camino
		List<Entry<Celda, Integer>> candidatos = this.mapa.getCosteAdyacentes(this.robot.getCurrentPos());
		//Calculamos el valor ganado de explorar los candidatos
		for (ListIterator<Entry<Celda, Integer>> it = candidatos.listIterator(); it.hasNext();) {
			int cont = 0;
			Entry<Celda, Integer> entry = it.next();
			Celda c = entry.getKey();
			double x = c.getX(); double y = c.getY();
			if (x > 0) { //casillas inferiores
				if (this.mapa.haSidoExplorada(this.mapa.getCelda((int)x-1, (int)y))) //inferior
					cont++;
				if (y > 0 
						&& this.mapa.haSidoExplorada(this.mapa.getCelda((int)x-1, (int)y-1))) //inferior izquierda
					cont++;
				if (y < this.mapa.getColumns() - 1 && 
						this.mapa.haSidoExplorada(this.mapa.getCelda((int)x-1, (int)y+1))) //inferior derecha
					cont++;
			}
			if (x < this.mapa.getRows() - 1) { //casillas superiores
				if (this.mapa.haSidoExplorada(this.mapa.getCelda((int)x+1, (int)y))) //inferior
					cont++;
				if (y > 0 
						&& this.mapa.haSidoExplorada(this.mapa.getCelda((int)x+1, (int)y-1))) //inferior izquierda
					cont++;
				if (y < this.mapa.getColumns() - 1 && 
						this.mapa.haSidoExplorada(this.mapa.getCelda((int)x+1, (int)y+1))) //inferior derecha
					cont++;
			}
			if (y > 0) { //casillas izquierda
				
			}
			if (y < this.mapa.getColumns() - 1) { //casillas derecha
				
			}
		}
		
	}
	
	

}
