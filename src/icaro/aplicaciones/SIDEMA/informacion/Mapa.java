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
		this.rows = 3;
		this.columns = 3;
		this.mapa = new Celda[this.rows][this.columns];
		for(int i = 0; i < this.rows; i++)
			for(int j = 0; j < this.columns; j++)
				this.mapa[i][j] = new Celda(i,j,true,false);
		this.mapa[1][2].setMina(true);
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
