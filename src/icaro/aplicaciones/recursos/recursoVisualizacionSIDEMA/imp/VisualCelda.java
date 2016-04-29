package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VisualCelda extends JButton {

	private boolean mina;
	final private String path = "/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/";

	public VisualCelda(boolean mina, boolean accesible) {
		this.setBorder(null);
		this.mina = mina;
		if (accesible) {
			/*
			 * if (mina) { ImageIcon arena = new
			 * ImageIcon(this.getClass().getResource(
			 * "/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
			 * this.setIcon(arena); //this.setBackground(Color.RED); } else {
			 */
			ImageIcon arena = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
			//ImageIcon icono = this.resizeImage(arena);
			this.setIcon(arena);
			// this.setBackground(Color.GREEN);

		} else {
			ImageIcon arena = new ImageIcon(this.getClass().getResource(path + "arenanegra.jpg"));
			this.setIcon(arena);
			// this.setBackground(Color.GRAY);
		}

	}

	public VisualCelda() {
		// ImageIcon arena = new ImageIcon("arena_marron.bmp");
		ImageIcon arena = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
		this.setIcon(arena);

		// this.setBackground(Color.YELLOW);
	}

	public void setMina() {
		ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
		ImageIcon icono2 = new ImageIcon(this.getClass().getResource(path + "mina1.png"));
		this.setIcon(new CombinedIcon(icono2, icono1, this.getWidth(), this.getHeight()));
		this.repaint();
	}

	public void minaEncontrada() {
		ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "explorador.jpg"));
		ImageIcon icono2 = new ImageIcon(this.getClass().getResource(path + "mina1.png"));
		this.setIcon(new CombinedIcon(icono2, icono1, this.getWidth(), this.getHeight()));
		this.repaint();
	}

	public void desactivarMina() {
		// CAMBIAR EL DIBUJO
		ImageIcon arena = new ImageIcon(this.getClass().getResource(path + "arena_marron_mina.jpg"));
		this.setIcon(arena);
		this.repaint();
	}

	public synchronized void movimientoExplorador() {
		ImageIcon arena;
		CombinedIcon arenaC;
		if (this.mina) {
			ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "explorador.jpg"));
			ImageIcon icono2 = new ImageIcon(this.getClass().getResource(path + "mina1.png"));
			ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "robotExplorador.png"));
			arenaC = new CombinedIcon(icono2, icono1, this.getWidth(), this.getHeight());
			arenaC.add(icono3);
			this.setIcon(arenaC);
			
		} else {
			
			ImageIcon icono = new ImageIcon(this.getClass().getResource(path + "explorador.jpg"));
			ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "robotExplorador.png"));
			arenaC = new CombinedIcon(icono3, icono, this.getWidth(),this.getHeight());
			this.setIcon(arenaC);

		}
		
		this.repaint();
	}

	public synchronized void movimientoNeutralizador() {
		CombinedIcon arenaC;
		ImageIcon arena;
		if (mina) {
			ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "neutralizador.jpg"));
			ImageIcon icono2 = new ImageIcon(this.getClass().getResource(path + "mina1.png"));
			arenaC = new CombinedIcon(icono2, icono1, this.getWidth(), this.getHeight());
			this.setIcon(arenaC);
			
		} else {
			ImageIcon icono = new ImageIcon(this.getClass().getResource(path + "neutralizador.jpg"));
			arena = this.resizeImage(icono);
			this.setIcon(arena);

		}

		this.repaint();
	}

	public void abandonarCelda() {
		Icon arena;
		if (mina) {
			arena = new ImageIcon(this.getClass().getResource(path + "arena_marron_mina.jpg"));
		} else {
			arena = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
		}
		this.setIcon(arena);
		this.repaint();
	}

	public ImageIcon resizeImage(ImageIcon img) {
		ImageIcon newImg;
		newImg = new ImageIcon(img.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
		return newImg;
	}
}
