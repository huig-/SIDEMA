package icaro.aplicaciones.SIDEMA.informacion;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.alg.DijkstraShortestPath;
import java.util.List;
import java.util.Map.Entry;

public class Mapa {

	private Celda[][] mapa;
	private int rows;
	private int columns;
	private int numExploradas = 0;
	private WeightedMultigraph<Celda, Integer> ExploredGraph;
	private WeightedMultigraph<Celda, Integer> completeGraph;
	public static Mapa instance;

	public Mapa(int rows, int column) {
		this.mapa = new Celda[rows][column];
		this.rows = rows;
		this.columns = column;
		this.ExploredGraph = new WeightedMultigraph<Celda, Integer>(Integer.class);
	}

	public Mapa(Celda[][] mapa) {
		this.mapa = mapa;
		this.rows = mapa.length;
		this.columns = mapa.length;
		this.ExploredGraph = new WeightedMultigraph<Celda, Integer>(Integer.class);
	}

	// Constructor por defecto cableado con el mapa
	public Mapa() {
		if (this.instance == null) {
			this.rows = 3;
			this.columns = 3;
			this.mapa = new Celda[this.rows][this.columns];
			for (int i = 0; i < this.rows; i++)
				for (int j = 0; j < this.columns; j++)
					this.mapa[i][j] = new Celda(i, j, true, false);
			this.mapa[0][0].setMina(true);
			this.mapa[0][2].setMina(true);
			this.mapa[0][1].setMina(true);
			this.mapa[2][0].setMina(true);
			this.mapa[2][2].setMina(true);
			this.ExploredGraph = new WeightedMultigraph<Celda, Integer>(Integer.class);
			instance = this;
		}
	}

	public Mapa getInstance() {
		return this.instance;
	}

	public synchronized boolean tieneMina(int row, int column) {
		this.updateGrafo(row, column);
		return mapa[row][column].getMina();

	}

	public synchronized GraphPath<Celda, Integer> findPath(Celda ini, Celda fin) {
		DijkstraShortestPath<Celda, Integer> path = new DijkstraShortestPath<Celda, Integer>(this.ExploredGraph, ini, fin);
		return path.getPath();
	}

	public synchronized void updateGrafo(int r, int c) {
		Celda celda = this.mapa[r][c];
		if (!this.ExploredGraph.containsVertex(celda))
			this.ExploredGraph.addVertex(celda);
		if (r > 0 && this.ExploredGraph.containsVertex(this.mapa[r - 1][c])) {
			this.ExploredGraph.addEdge(celda, this.mapa[r - 1][c], this.numExploradas);
			this.numExploradas++;
		}
		if (r < this.rows - 1 && this.ExploredGraph.containsVertex(this.mapa[r + 1][c])) {
			this.ExploredGraph.addEdge(celda, this.mapa[r + 1][c], this.numExploradas);
			this.numExploradas++;
		}
		if (c > 0 && this.ExploredGraph.containsVertex(this.mapa[r][c - 1])) {
			this.ExploredGraph.addEdge(celda, this.mapa[r][c - 1], this.numExploradas);
			this.numExploradas++;
		}
		if (c < this.columns - 1 && this.ExploredGraph.containsVertex(this.mapa[r][c + 1])) {
			this.ExploredGraph.addEdge(celda, this.mapa[r][c + 1], this.numExploradas);
			this.numExploradas++;
		}
	}

	public synchronized boolean esAccesible(int row, int column) {
		return mapa[row][column].getAccesible();
	}

	public synchronized Celda[][] getMapa() {
		return this.mapa;
	}

	public synchronized void setMapa(Celda[][] mapa) {
		this.mapa = mapa;
		this.getSize();
	}

	public synchronized void setMina(int row, int column) {
		this.mapa[row][column].setMina(true);
	}

	public synchronized void setInaccesible(int row, int column) {
		this.mapa[row][column].setAccesible(false);
	}

	public synchronized Celda getCelda(int row, int column) {
		return this.mapa[row][column];
	}

	private synchronized void getSize() {
		this.rows = this.mapa.length;
		this.columns = this.mapa[0].length;
	}

	public synchronized int getRows() {
		return this.rows;
	}

	public synchronized int getColumns() {
		return this.columns;
	}
	
	public List<Celda> getAdyacentes() {
		return null;
	}
	
	public List<Entry<Celda, Integer>> getCosteAdyacentes() {
		return null;
	}
}
