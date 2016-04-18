package icaro.aplicaciones.ventana.imp;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.OrdenMinaEncontrada;

public class VisualizarVentana {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorVista c = new ControladorVista();
		//Ventana v = new Ventana();
		int num_rows = 2;
		int num_columns = 2;
		int ini, end, inc;
		for (int i = 0; i < num_rows; i++) {
			if(i%2==0){
				ini = 0;
				end = num_columns - 1;
				inc = 1;
			}
			else{
				ini = num_columns - 1;
				end = 0;
				inc = -1;
			}
			for (int j = ini; Math.abs(end-ini) >= 0; j = j + inc) {
				c.getVentanaEntorno().getEscenario().movimientoExplorador(i,j);
				for (int k = 0; k < 10000; k++);//Delay 10000
				if (c.getMapa().getCelda(i, j).getMina()) {
					//Si encontramos una mina, se produce un retraso mayor.
					for (int k = 0; k < 50000; k++); //Delay 50000
					c.getVentanaEntorno().getEscenario().minaEncontrada(i, j);
				}
				

			}
		}
		for(int i = 0; i < num_rows; i++){
			c.getVentanaEntorno().getEscenario().movimientoNeutralizador(0,i);
			for (int k = 0; k < 50000; k++); //Delay 50000
		}
	}
}
