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
			this.robot = (Explorador)params[0];
			this.mapa = robot.getMapa();
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
					if (!this.mapa.haSidoExplorada(this.mapa.getCelda((int)x-1, (int)y))
							&& this.mapa.getCelda((int)x-1, (int)y).getAccesible()) //inferior
						cont++;
					if (y > 0 
							&& !this.mapa.haSidoExplorada(this.mapa.getCelda((int)x-1, (int)y-1))
							&& this.mapa.getCelda((int)x-1, (int)y-1).getAccesible()) //inferior izquierda
						cont++;
					if (y < this.mapa.getColumns() - 1 && 
							!this.mapa.haSidoExplorada(this.mapa.getCelda((int)x-1, (int)y+1))
							&& this.mapa.getCelda((int)x-1, (int)y+1).getAccesible()) //inferior derecha
						cont++;
				}
				if (x < this.mapa.getRows() - 1) { //casillas superiores
					if (!this.mapa.haSidoExplorada(this.mapa.getCelda((int)x+1, (int)y))
							&& this.mapa.getCelda((int)x+1, (int)y).getAccesible()) //inferior
						cont++;
					if (y > 0 
							&& !this.mapa.haSidoExplorada(this.mapa.getCelda((int)x+1, (int)y-1))
							&& this.mapa.getCelda((int)x+1, (int)y-1).getAccesible()) //inferior izquierda
						cont++;
					if (y < this.mapa.getColumns() - 1 && 
							!this.mapa.haSidoExplorada(this.mapa.getCelda((int)x+1, (int)y+1))
							&& this.mapa.getCelda((int)x+1, (int)y+1).getAccesible()) //inferior derecha
						cont++;
				}
				if (y > 0 && !this.mapa.haSidoExplorada(this.mapa.getCelda((int)x, (int)y-1))
						&& this.mapa.getCelda((int)x, (int)y-1).getAccesible()) { //casilla izquierda
					cont++;
				}
				if (y < this.mapa.getColumns() - 1 && !this.mapa.haSidoExplorada(this.mapa.getCelda((int)x, (int)y+1))
						&& this.mapa.getCelda((int)x, (int)y+1).getAccesible()) { //casilla derecha
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
