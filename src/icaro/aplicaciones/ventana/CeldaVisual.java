package icaro.aplicaciones.ventana;

import java.awt.Color;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import icaro.aplicaciones.ventana.dibujos.*; 

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
		//ImageIcon arena = new ImageIcon("arena_marron.bmp");
		 ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron.jpg"));
		this.setIcon(arena);
	    
		//this.setBackground(Color.YELLOW);
	}

}
