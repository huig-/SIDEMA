package icaro.aplicaciones.SIDEMA.informacion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Candidatos { 

	private String identAgte;
	private List<SimpleEntry<Celda, Double>> candidatos;
	
	public Candidatos(String identAgte, List<SimpleEntry<Celda, Double>> candidatos) {
		this.identAgte = identAgte;
		this.candidatos = candidatos;
	}

	public String getIdentAgte() {
		return identAgte;
	}

	public void setIdentAgte(String identAgte) {
		this.identAgte = identAgte;
	}

	public List<SimpleEntry<Celda, Double>> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<SimpleEntry<Celda, Double>> candidatos) {
		this.candidatos = candidatos;
	}
	
	public void sort() {
		Collections.sort(this.candidatos, new Comparator<SimpleEntry<Celda, Double>>() { //decreciente

			@Override
			public int compare(SimpleEntry<Celda, Double> o1,
					SimpleEntry<Celda, Double> o2) {
				return o2.getValue().compareTo(o1.getValue()); 
			}
			
		});
	}
	
	public SimpleEntry<Celda, Double> max() {
		return Collections.max(this.candidatos, new Comparator<SimpleEntry<Celda, Double>>() { //decreciente

			@Override
			public int compare(SimpleEntry<Celda, Double> o1,
					SimpleEntry<Celda, Double> o2) {
				return o2.getValue().compareTo(o1.getValue()); 
			}
			
		});
	}
}
