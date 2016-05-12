package icaro.aplicaciones.agentes.agenteCC.tareas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import icaro.aplicaciones.SIDEMA.informacion.Candidatos;
import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CeldaCandidata;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenExplorar;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class AsignarCasillasAExplorar extends TareaSincrona {
	
	private Mapa mapa;

	@Override
	public void ejecutar(Object... params) {
		try {
			CentroControl r = (CentroControl)params[0];
			mapa = (Mapa)params[1]; 
			Candidatos []c = new Candidatos[params.length-2]; //celdas candidatas de cada explorador
			double []ganancias = new double[params.length-2]; //mejor ganancia de cada explorador
			Set<Celda> celdasYaConsideradas = new HashSet<Celda>(); //para el descuento: IGR en el articulo
			boolean []exploradoresAsignados = new boolean[params.length-2];
			for (int i = 2; i < params.length; i++) c[i] = (Candidatos)params[i];
			
			for (int i = 0; i < c.length; i++) {
				TreeMap<Double, Integer> map  = new TreeMap<Double, Integer>();; //para ordenar con indices
				for (int index = 0; index < c.length && !exploradoresAsignados[index]; index++) {
					c[index].sort();
					ganancias[index] = c[index].max().getExpectedValue();
					map.put(ganancias[index], index); 
				}
				
				//1 paso, elegimos al mejor
				int best_index = map.lastEntry().getValue();
				exploradoresAsignados[best_index] = true;
				//Avisamos al explorador
				OrdenExplorar orden = new OrdenExplorar(r.getId(), c[best_index].max().getCelda());
				this.getComunicator().enviarInfoAotroAgente(orden, c[best_index].getIdentAgte());
				
				//Actualizamos celdas consideradas
				for (Celda celda : this.mapa.getAdyacentes(c[best_index].max().getCelda())) 
					celdasYaConsideradas.add(celda);
				//2 paso, realizamos el descuento
				for (int j = 0; j < c.length && !exploradoresAsignados[j]; j++) {
					List<CeldaCandidata> candidatas = c[j].getCeldas();
					for (CeldaCandidata cc : candidatas) {
						Set<Celda> adyacentes = new HashSet<Celda>(this.mapa.getAdyacentes(cc.getCelda()));
						Set<Celda> yaConsideradas = getIntersection(adyacentes, celdasYaConsideradas);
						double dj = yaConsideradas.size() / adyacentes.size(); 
						double ganancia = (1 - dj) * cc.getGanancia();
						cc.setGanancia(ganancia);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Set<Celda> getIntersection(Set<Celda> set1, Set<Celda> set2) {
		boolean set1IsLarger = set1.size() > set2.size();
		Set<Celda> cloneSet = new HashSet<Celda>(set1IsLarger ? set2 : set1);
		cloneSet.retainAll(set1IsLarger ? set1 : set2);
		return cloneSet;
	}
}