package icaro.aplicaciones.ventana;

import java.awt.Color;

import javax.swing.JButton;

public class CeldaVisual extends JButton {

	public CeldaVisual(boolean mina, boolean accesible) {
		if (accesible) {
			if (mina) {
				this.setBackground(Color.RED);
			} else {
				this.setBackground(Color.GREEN);
			}

		} else {
			this.setBackground(Color.GRAY);
		}

	}
	
	public CeldaVisual(){
		this.setBackground(Color.YELLOW);
	}

}
