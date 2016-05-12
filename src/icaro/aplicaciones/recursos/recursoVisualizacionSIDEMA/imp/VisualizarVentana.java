package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.InformarMinaEncontrada;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;

public class VisualizarVentana {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorVista c = new ControladorVista();
		c.setModelo(new Mapa());
		// Ventana v = new Ventana();
		int num_rows = 2;
		int num_columns = 2;
		int ini, end, inc;
		try {
			Thread.sleep(5000); // 1000 milliseconds is one second.
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		for (int i = 0; i < num_rows; i++) {
			if (i % 2 == 0) {
				ini = 0;
				end = num_columns;
				inc = 1;
			} else {
				ini = num_columns - 1;
				end = -1;
				inc = -1;
			}
			int j = ini;
			/*
			 * while(Math.abs(end-j) > 0){
			 * c.getVentanaEntorno().getEscenario().movimientoExplorador(i,j);
			 * try { Thread.sleep(1000); //1000 milliseconds is one second. }
			 * catch(InterruptedException ex) {
			 * Thread.currentThread().interrupt(); } if (c.getMapa().getCelda(i,
			 * j).getMina()) { //Si encontramos una mina, se produce un retraso
			 * mayor. try { Thread.sleep(2000); //1000 milliseconds is one
			 * second. } catch(InterruptedException ex) {
			 * Thread.currentThread().interrupt(); }
			 * c.getVentanaEntorno().getEscenario().minaEncontrada(i, j); }
			 * 
			 * j = j + inc; } } for(int i = 0; i < num_rows; i++){
			 * c.getVentanaEntorno().getEscenario().movimientoNeutralizador(i,0)
			 * ; try { Thread.sleep(2000); //1000 milliseconds is one second. }
			 * catch(InterruptedException ex) {
			 * Thread.currentThread().interrupt(); } for (int k = 0; k <
			 * 5000000; k++); //Delay 50000 } }
			 */
		}
	}
}
