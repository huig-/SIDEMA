package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.SwingConstants;

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
				ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/arena_marron.jpg"));
				this.setIcon(arena);
				//this.setBackground(Color.GREEN);
			

		} else {
			ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/arenanegra.jpg"));
			this.setIcon(arena);
			//this.setBackground(Color.GRAY);
		}

	}
	
	public VisualCelda(){
		//ImageIcon arena = new ImageIcon("arena_marron.bmp");
		 ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/arena_marron.jpg"));
		this.setIcon(arena);
	    
		//this.setBackground(Color.YELLOW);
	}
	
	public void setMina(){
		ImageIcon icono1 = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/arena_marron.jpg"));
		ImageIcon icono2 = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/bomba1.png"));
		this.setIcon(new CombinedIcon(icono2, icono1,this.getWidth(), this.getHeight()));
	}
	
	public void minaEncontrada(){
		ImageIcon icono1 = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/explorador.jpg"));
		ImageIcon icono2 = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/bomba1.png"));
		this.setIcon(new CombinedIcon(icono2, icono1,this.getWidth(),this.getHeight()));
		this.repaint();
	}
	
	public void desactivarMina(){
		//CAMBIAR EL DIBUJO
		ImageIcon arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/arena_marron_mina.jpg"));
		this.setIcon(arena);
	}
	
	public synchronized void movimientoExplorador(){
		Icon arena;
		if(this.mina){
			arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/explorador_mina.jpg"));
		}else{
		 arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/explorador.jpg"));
		}this.setIcon(arena);
	}
	
	public synchronized void movimientoNeutralizador(){
		Icon arena;
		if(mina){
		arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/neutralizador-mina.jpg"));
		}else{
		arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/neutralizador.jpg"));
		}
		this.setIcon(arena);
	}
	
	public void abandonarCelda(){
		Icon arena;
		if(mina){
			arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/arena_marron_mina.jpg"));
		}else{
			arena = new ImageIcon(this.getClass().getResource("/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/arena_marron.jpg"));
		}
		this.setIcon(arena);
	}

}
