package icaro.aplicaciones.SIDEMA.informacion;

public class CeldaEnergia {
	
	private Celda celda;
	private double movimiento;
	private double desactivacion;
	private double energia;
	
	public CeldaEnergia(Celda celda, double movimiento, double desactivacion, double energia) {
		this.celda = celda;
		this.movimiento = movimiento;
		this.desactivacion = desactivacion;
		this.energia = energia;
	}
	
	public CeldaEnergia() {}
	
	public Celda getCelda() {
		return celda;
	}

	public void setCelda(Celda celda) {
		this.celda = celda;
	}

	public double getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(double movimiento) {
		this.movimiento = movimiento;
	}

	public double getDesactivacion() {
		return desactivacion;
	}

	public void setDesactivacion(double desactivacion) {
		this.desactivacion = desactivacion;
	}

	public double getEnergia() {
		return energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}


}
