package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class CombineIcon implements Icon {
		/**
		 * Lista de Iconos 
		 */
	    private Vector<ImageIcon> list;
	    
	    /**
	     * Lista de IDs de los Iconos
	     */
	    private Vector<String> listId;
	    
	    /**
	     * Ultimo size de las imagenes
	     */
	    private Dimension lastSize;

	    /**
	     * Constructora del Icono Combinado
	     */
	    public CombineIcon(){
	    	list = new Vector<ImageIcon>();
	    	listId = new Vector<String>();
	    	lastSize = new Dimension(50,50);
	    }
	    
	    /**
	     * Constructora del Icono Combinado
	     * @param id nombre del id, "background" en general.
	     * @param ic Icono inicial
	     */
	    public CombineIcon(String id, ImageIcon ic) {
	    	this();
	    	addIcon(id,ic);
	    }
	    
	    /**
	     * Reescala todas las imagenes del Icono actual
	     * @param wd Anchura de la imagen
	     * @param hg Altura de la imagen
	     */
	    public void rescale(int wd, int hg){
	    	Image im;
	    	int w=wd;
	    	try{
	    		im = list.get(0).getImage().getScaledInstance(wd,hg,Image.SCALE_FAST);
	    		list.set(0, new ImageIcon(im));

		    	for(int i = 1; i < list.size(); i++){
		    		im = list.get(i).getImage().getScaledInstance(w,hg,Image.SCALE_FAST);
		    		list.set(i, new ImageIcon(im));
		    	}
		    	lastSize = new Dimension(wd,hg);
	    	}catch(ArrayIndexOutOfBoundsException e){
	    		System.err.println("Error: Intento de solucion ejecutado");
	    		ImageIcon bg = list.get(0);
	    		list = new Vector<ImageIcon>();
	    		list.add(bg);
	    		listId = new Vector<String>();
	    		listId.add("backgroud");
	    		rescale(wd,hg);
	    	}
	    	
	    }
	    
	    /**
	     * Incorpora un nuevo Icono
	     * @param id nombre del Icono
	     * @param ic Icono a incorporar
	     */
	    public void addIcon(String id,ImageIcon ic){
	    	Image im = ic.getImage().getScaledInstance(lastSize.width,lastSize.height,Image.SCALE_FAST);
	    	list.addElement(new ImageIcon(im));
	    	listId.addElement(id);
	    }
	    
	    /**
	     * Elimina un icono segun su posicion
	     * @param idx posicion del icono a eliminar
	     */
	    public void removeIcon(int idx){
	    	try{
	    		list.remove(idx);
	    		listId.remove(idx);
	    	}catch(Exception e){
	    		
	    	}
	    }
	    
	    /**
	     * Elimina un icono segun su nombre
	     * @param id nombre del icono
	     */
	    public void removeIcon(String id){
	    	Iterator<String> it = listId.iterator();
	    	int i = 0;
	    	while(it.hasNext()){
	    		String s = it.next();
	    		if(s.equals(id)){
	    			removeIcon(i);
	    			break;
	    		}
	    		i++;
	    	}
	    }
	    
	    @Override
	    public int getIconHeight() {
	    	int max = 0;
	    	int h;
	    	for(int i = 0; i < list.size(); i++){
	    		h = list.get(i).getIconHeight();
	    		if(h > max){
	    			max = h;
	    		}
	    	}
	        return max;
	    }

	    @Override
	    public int getIconWidth() {
	    	int max = 0;
	    	int h;
	    	for(int i = 0; i < list.size(); i++){
	    		h = list.get(i).getIconWidth();
	    		if(h > max){
	    			max = h;
	    		}
	    	}
	        return max;
	    }
	    
	    @Override
	    public void paintIcon(Component c, Graphics g, int x, int y) {
	    	list.get(0).paintIcon(c,g,x,y);
	    	int inc = 0;
	    	for(int i = 1; i < list.size(); i++){
	    		list.get(i).paintIcon(c,g,inc,y);
	    		inc += 10;
	    	}
	    }	
	}
	

