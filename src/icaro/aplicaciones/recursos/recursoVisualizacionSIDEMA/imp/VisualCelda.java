package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VisualCelda extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean mina;
	final private String path = "/icaro/aplicaciones/recursos/recursoVisualizacionSIDEMA/dibujos/";
	private CombineIcon ci;
	private boolean exp = false;
	private boolean neut = false;

	public VisualCelda(boolean mina, boolean accesible) {
		super();
		this.mina = mina;
		this.exp = false;
		this.neut = false;
		if (accesible) {
			/*
			 * if (mina) { ImageIcon arena = new
			 * ImageIcon(this.getClass().getResource(
			 * "/icaro/aplicaciones/ventana/dibujos/arena_marron_mina.jpg"));
			 * this.setIcon(arena); //this.setBackground(Color.RED); } else {
			 */
			ImageIcon arena = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
			// ImageIcon icono = this.resizeImage(arena);
			this.ci = new CombineIcon("background", arena);
			this.setIcon(arena);
			// this.setBackground(Color.GREEN);

		} else {
			ImageIcon arena = new ImageIcon(this.getClass().getResource(path + "arenanegra.jpg"));
			this.setIcon(arena);
			// this.setBackground(Color.GRAY);
		}
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				VisualCelda btn = (VisualCelda) e.getComponent();
				Dimension size = btn.getSize();
				btn.rescale(size.width, size.height);
			}

		});
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
		ci = new CombineIcon("background", icono1);
		this.setIcon(ci);
		ci.rescale(this.getSize().width, this.getSize().height);
		ci.addIcon("mina", icono2);
		ci.rescale(ci.getIconWidth(), ci.getIconHeight());
		this.repaint();
	}

	public void minaEncontrada() {
		ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "explorador.jpg"));
		ImageIcon icono2 = new ImageIcon(this.getClass().getResource(path + "mina1.png"));
		ci = new CombineIcon("background", icono1);
		this.setIcon(ci);
		ci.rescale(this.getSize().width, this.getSize().height);
		ci.addIcon("mina", icono2);
		ci.rescale(ci.getIconWidth(), ci.getIconHeight());
		this.repaint();
	}

	public void desactivarMina() {
		// CAMBIAR EL DIBUJO
		ImageIcon arena = new ImageIcon(this.getClass().getResource(path + "arena_marron_mina.jpg"));
		this.setIcon(arena);
		this.repaint();
	}

	public synchronized void movimientoExplorador() {
		if (this.mina) {
			ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
			ImageIcon icono2 = new ImageIcon(this.getClass().getResource(path + "mina1.png"));
			ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "wall-e.png"));
			ImageIcon icono4 = new ImageIcon(this.getClass().getResource(path + "ambos.png"));
			ci = new CombineIcon("background", icono1);
			this.setIcon(ci);
			ci.rescale(this.getSize().width, this.getSize().height);
			ci.addIcon("mina", icono2);
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
			this.repaint();
			if (this.neut) {
				ci.addIcon("ambos", icono4);
			} else {
				ci.addIcon("explorador", icono3);
			}
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
			this.repaint();

		} else {

			ImageIcon icono = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
			ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "wall-e.png"));
			ImageIcon icono4 = new ImageIcon(this.getClass().getResource(path + "ambos.png"));
			ci = new CombineIcon("background", icono);
			this.setIcon(ci);
			ci.rescale(this.getSize().width, this.getSize().height);
			if (this.neut) {
				ci.addIcon("ambos", icono4);
			} else {
				ci.addIcon("explorador", icono3);
			}
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
			this.repaint();
		}
		this.exp = true;
		
	}

	public synchronized void movimientoNeutralizador() {

		if (mina) {
			ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
			ImageIcon icono2 = new ImageIcon(this.getClass().getResource(path + "mina1.png"));
			ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "marvin.png"));
			ImageIcon icono4 = new ImageIcon(this.getClass().getResource(path + "ambos.png"));
			ci = new CombineIcon("background", icono1);
			this.setIcon(ci);
			ci.rescale(this.getSize().width, this.getSize().height);
			ci.addIcon("mina", icono2);
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
			this.repaint();
			if (this.exp) {
				ci.addIcon("ambos", icono4);
			} else {
				ci.addIcon("neutralizador", icono3);
			}
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
			this.repaint();

		} else {
			ImageIcon icono1 = new ImageIcon(this.getClass().getResource(path + "arena_marron.jpg"));
			ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "marvin.png"));
			ImageIcon icono4 = new ImageIcon(this.getClass().getResource(path + "ambos.png"));
			
			ci = new CombineIcon("background", icono1); this.setIcon(ci);
			ci.rescale(this.getSize().width, this.getSize().height);
			if(this.exp){ 
			ci.addIcon("ambos", icono4);}
			else{
				ci.addIcon("neutralizador", icono3);
			}
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
			this.repaint();

		}
		this.neut = true;

	}

	public synchronized void abandonarCeldaExplorador() {
		ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "marvin.png"));
		if (this.neut) {
			ci.removeIcon("ambos");
			ci.addIcon("neutralizador", icono3);
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
		} else {
			ci.removeIcon("explorador");

		}
		this.exp = false;
		this.repaint();
	}

	public void abandonarCeldaNeutralizador() {
		ImageIcon icono3 = new ImageIcon(this.getClass().getResource(path + "wall-e.png"));
		if (this.exp) {
			ci.removeIcon("ambos");
			ci.addIcon("explorador", icono3);
			ci.rescale(ci.getIconWidth(), ci.getIconHeight());
		} else {
			ci.removeIcon("neutralizador");
		}

		this.neut = false;
		this.repaint();
	}

	public ImageIcon resizeImage(ImageIcon img) {
		ImageIcon newImg;
		newImg = new ImageIcon(img.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
		return newImg;
	}

	public void rescale(int w, int h) {
		ci.rescale(w, h);
		repaint();
	}
}
