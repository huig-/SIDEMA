package icaro.aplicaciones.SIDEMA.informacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Candidatos { 

	private String identAgte;
	private List<CeldaCandidata> celdas;
	
	public Candidatos(String identAgte, List<CeldaCandidata> candidatos) {
		this.identAgte = identAgte;
		this.celdas = candidatos;
	}

	public Candidatos() {
		this.celdas = new ArrayList<CeldaCandidata>();
	}
	
	public Candidatos(String identAgte) {
		this.identAgte = identAgte;
		this.celdas = new ArrayList<CeldaCandidata>();
	}

	public String getIdentAgte() {
		return identAgte;
	}

	public void setIdentAgte(String identAgte) {
		this.identAgte = identAgte;
	}

	public List<CeldaCandidata> getCeldas() {
		return celdas;
	}

	public void setCeldas(List<CeldaCandidata> celdas) {
		this.celdas = celdas;
	}
	
	public void sort() {
		Collections.sort(this.celdas, Collections.reverseOrder(new Comparator<CeldaCandidata>() {

			@Override
			public int compare(CeldaCandidata o1, CeldaCandidata o2) {
				if (o1.getExpectedValue() > o2.getExpectedValue()) return 1;
				else if (o1.getExpectedValue() < o2.getExpectedValue()) return -1;
				else return 0;
			}
		}));
	}
	
	public CeldaCandidata max() {
		return Collections.max(this.celdas, new Comparator<CeldaCandidata>() {

			@Override
			public int compare(CeldaCandidata o1, CeldaCandidata o2) {
				if (o1.getExpectedValue() > o2.getExpectedValue()) return 1;
				else if (o1.getExpectedValue() < o2.getExpectedValue()) return -1;
				else return 0;
			}
		});
	}
}
