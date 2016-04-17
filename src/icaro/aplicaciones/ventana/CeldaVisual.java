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
				ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
				this.setIcon(arena);
				//this.setBackground(Color.RED);
			} else {
				ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron.jpg"));
				this.setIcon(arena);
				//this.setBackground(Color.GREEN);
			}

		} else {
			ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arenanegra.jpg"));
			this.setIcon(arena);
			//this.setBackground(Color.GRAY);
		}

	}
	
	public CeldaVisual(){
		//ImageIcon arena = new ImageIcon("arena_marron.bmp");
		 ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron.jpg"));
		this.setIcon(arena);
	    
		//this.setBackground(Color.YELLOW);
	}
	
	public void setMina(){
		ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
		this.setIcon(arena);
	}
	
	public void minaEncontrada(){
		ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
		this.setIcon(arena);
	}
	
	public void desactivarMina(){
		//CAMBIAR EL DIBUJO
		ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
		this.setIcon(arena);
	}

}
