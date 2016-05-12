package icaro.aplicaciones.SIDEMA.informacion;

public class CeldaCandidata {
	
	private Celda celda;
	private double coste;
	private double ganancia;
	
	public CeldaCandidata(Celda celda, double coste, double ganancia) {
		this.celda = celda;
		this.coste = coste;
		this.ganancia = ganancia;
	}
	
	public CeldaCandidata() {}
	
	public Celda getCelda() {
		return celda;
	}

	public void setCelda(Celda celda) {
		this.celda = celda;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}
	
	public double getExpectedValue() {
		return this.ganancia - this.coste;
	}
}
