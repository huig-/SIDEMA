package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

public class Celda implements Serializable {
	
	private int x, y;
	private boolean accesible;
	private boolean mina;
	  
	  public Celda(int x, int y,boolean accesible, boolean mina) {
	    this.x = x;
	    this.y = y;
	    this.accesible = accesible;
	    this.mina = mina;
	  }
	  
	  public Celda(int x, int y,boolean mina){
		  this.x = x;
		  this.y = y;
		  this.mina = mina;
	  }
	  
	  public Celda(int x, int y){
		  this.x = x;
		  this.y = y;
		  this.mina = false;
		  this.accesible = true;
	  }
	  
	  public Celda(){
		  this.mina = false;
		  this.accesible=true;
	  }
	  public synchronized void setX (int XCoord){
	      this.x=XCoord;
	  }
	  public synchronized int getX (){
	      return this.x;
	  }
	  public synchronized void setY (int YCoord){
	      this.y=YCoord;
	  }
	  public synchronized int getY (){
	      return this.y;
	  }
	  
	  public synchronized boolean getMina(){
		  return this.mina;
	  }
	  
	  public synchronized boolean getAccesible(){
		  return this.accesible;
	  }
	  
	  public synchronized void setMina(boolean mina){
		  this.mina = mina;
	  }
	  
	  public synchronized void setAccesible(boolean accesible){
		  this.accesible = accesible;
	  } 
	  
	  public synchronized void desactivarMina(){
		  this.mina = false;
		  //Activar el action listener correspondiente
	  }
	  
	  
	  @Override
	  public String toString() {
	    return "Coordinate: (" + x + "," + y + ")";
	  }

}
