package icaro.aplicaciones.ventana.imp;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton; 

public class VisualCelda extends JButton {

	private boolean mina;
	
	public VisualCelda(boolean mina, boolean accesible) {
		this.setBorder(null);
		this.mina = mina;
		if (accesible) {
			/*if (mina) {
				ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
				this.setIcon(arena);
				//this.setBackground(Color.RED);
			} else {*/
				ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron.jpg"));
				this.setIcon(arena);
				//this.setBackground(Color.GREEN);
			

		} else {
			ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arenanegra.jpg"));
			this.setIcon(arena);
			//this.setBackground(Color.GRAY);
		}

	}
	
	public VisualCelda(){
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
		ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/explorador_mina.jpg"));
		this.setIcon(arena);
	}
	
	public void desactivarMina(){
		//CAMBIAR EL DIBUJO
		ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
		this.setIcon(arena);
	}
	
	public void movimientoExplorador(){
		Icon arena;
		if(this.mina){
			arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/explorador_mina.jpg"));
		}else{
		 arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/explorador.jpg"));
		}this.setIcon(arena);
	}
	
	public void movimientoNeutralizador(){
		Icon arena;
		if(mina){
		arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/neutralizador-mina.jpg"));
		}else{
		arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/neutralizador.jpg"));
		}
		this.setIcon(arena);
	}
	
	public void abandonarCelda(){
		Icon arena;
		if(mina){
			arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
		}else{
			arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/ventana/dibujos/arena_marron.jpg"));
		}
		this.setIcon(arena);
	}

}
