package icaro.aplicaciones.agentes.agenteExplorador.tareas;

import icaro.aplicaciones.SIDEMA.informacion.Candidatos;
import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CeldaCandidata;
import icaro.aplicaciones.SIDEMA.informacion.Explorador;
import icaro.aplicaciones.SIDEMA.informacion.InformarCandidatosAExplorar;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class EstimarMejorDestino extends TareaSincrona {
	
	private Mapa mapa;
	private Explorador robot;

	@Override
	public void ejecutar(Object... params) {
		try {
			this.mapa = (Mapa)params[0];
			this.robot = (Explorador)params[1];
			this.robot.setMapa(this.mapa);
			//Elaboramos una lista con los adyacentes a los exploradores y el coste del camino
			Candidatos candidatos = new Candidatos(robot.getId());
			candidatos.setCeldas(this.mapa.getCosteAdyacentes(this.robot.getCurrentPos()));
			//Calculamos el valor ganado de explorar los candidatos
			for (int index = 0; index < candidatos.getCeldas().size(); index++) {
				int cont = 0;
				CeldaCandidata celdaCandidata = candidatos.getCeldas().get(index);
				Celda c = celdaCandidata.getCelda();
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
				if (y > 0 && this.mapa.haSidoExplorada(this.mapa.getCelda((int)x, (int)y-1))) { //casilla izquierda
					cont++;
				}
				if (y < this.mapa.getColumns() - 1 && this.mapa.haSidoExplorada(this.mapa.getCelda((int)x, (int)y+1))) { //casilla derecha
					cont++;
				}
				celdaCandidata.setGanancia(cont);
				candidatos.getCeldas().set(index, celdaCandidata);
			}
			InformarCandidatosAExplorar infor = new InformarCandidatosAExplorar(robot.getId(), candidatos);
			this.getComunicator().enviarInfoAotroAgente(infor, robot.getCC());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
