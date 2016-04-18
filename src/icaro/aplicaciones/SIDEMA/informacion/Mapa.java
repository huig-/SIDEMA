package icaro.aplicaciones.SIDEMA.informacion;

public class Mapa {

	public Mapa(int rows, int column){
		this.mapa = new Celda[rows][column];
		this.rows = rows;
		this.columns = column;
	}
	
	public Mapa(Celda[][] mapa){
		this.mapa = mapa;
		this.rows = mapa.length;
		this.columns = mapa.length;
	}
	
	//Constructor por defecto cableado con el mapa
	public Mapa() {
		this.mapa = new Celda[2][2];
		this.mapa[0][0] = new Celda(0,0,true,false);
		this.mapa[0][1] = new Celda(0,1,true,false);
		this.mapa[1][0] = new Celda(1,0,true,true);
		this.mapa[1][1] = new Celda(1,1,true,false);
		this.rows = 2;
		this.columns = 2;
	}
	
	public synchronized boolean tieneMina(int row, int column){
		return mapa[row][column].getMina();
	}
	
	public synchronized boolean esAccesible(int row, int column){
		return mapa[row][column].getAccesible();
	}
	
	public synchronized Celda[][] getMapa(){
		return this.mapa;
	}
	
	public synchronized void setMapa(Celda[][] mapa){
		this.mapa= mapa;
		this.getSize();
	}
	
	public synchronized void setMina(int row, int column){
		this.mapa[row][column].setMina(true);
	}
	
	public synchronized void setInaccesible(int row, int column){
		this.mapa[row][column].setAccesible(false);
	}
	
	public synchronized Celda getCelda(int row, int column){
		return this.mapa[row][column];
	}
	
	private synchronized void getSize(){
		this.rows= this.mapa.length;
		this.columns=this.mapa[0].length;
	}
	public synchronized int getRows(){
		return this.rows;
	}
	
	public synchronized int getColumns(){
		return this.columns;
	}
	
	private Celda[][] mapa ;
	private int rows;
	private int columns;
}
