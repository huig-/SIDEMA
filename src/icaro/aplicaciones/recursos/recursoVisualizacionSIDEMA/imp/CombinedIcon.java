package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CombinedIcon implements Icon {
	private ArrayList<ImageIcon> top;
	private ImageIcon bottom;
	private int w;
	private int h;

	public CombinedIcon(ImageIcon top, ImageIcon bottom, int w, int h) {
		this.top = new ArrayList<ImageIcon>();
		this.top.add(top);
		this.bottom = bottom;
		this.w = w;
		this.h= h;
		this.resizeBottom();
		
	}

	public void add(ImageIcon img) {
		this.top.add(img);
	}

	public int getIconHeight() {
		int max = Integer.MIN_VALUE;
		for (ImageIcon i : top) {
			if (max < Math.max(i.getIconHeight(), bottom.getIconHeight())) {
				max = Math.max(i.getIconHeight(), bottom.getIconHeight());
			}
		}
		return max;
	}

	public int getIconWidth() {
		int max = Integer.MIN_VALUE;
		for (ImageIcon i : top) {
			if (max < Math.max(i.getIconWidth(), bottom.getIconWidth())) {
				max = Math.max(i.getIconWidth(), bottom.getIconWidth());
			}
		}
		return max;

	}

	public ImageIcon resizeTop(int id) {
		ImageIcon imgAux = this.top.get(id);
		this.top.remove(id);
		ImageIcon newImg = new ImageIcon(imgAux.getImage().getScaledInstance(this.bottom.getIconWidth() / 2,
				this.bottom.getIconHeight() / 2, Image.SCALE_SMOOTH));
		this.top.add(id, newImg);
		
		return newImg;
	}
	
	public void resizeBottom(){
		this.bottom = new ImageIcon(this.bottom.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		ImageIcon newImg;
		bottom.paintIcon(c, g, x, y);
		for (ImageIcon img : top) {
			newImg = this.resizeTop(top.indexOf(img));
			newImg.paintIcon(c, g, x + this.bottom.getIconWidth() / 4, y + this.bottom.getIconHeight() / 4);
		}
	}

}