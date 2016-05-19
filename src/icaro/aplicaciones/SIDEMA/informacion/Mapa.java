package icaro.aplicaciones.SIDEMA.informacion;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.alg.BellmanFordShortestPath;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;

public class Mapa {

	private Celda[][] mapa;
	private int rows;
	private int columns;
	private int numExploradas = 0;
	private int numCompletas = 0;
	private WeightedMultigraph<Celda, Integer> ExploredGraph;
	private WeightedMultigraph<Celda, Integer> completeGraph;
	public static Mapa instance;

	@SuppressWarnings("unchecked")
	public Mapa(Mapa other) { // deep clone
		this.rows = other.getRows();
		this.columns = other.getColumns();
		this.numExploradas = other.getNumExploradas();
		this.numCompletas = other.getNumCompletas();
		this.mapa = new Celda[this.rows][this.columns];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				Celda aux = other.getMapa()[i][j];
				this.mapa[i][j] = new Celda(aux.getX(), aux.getY(),
						aux.getAccesible(), aux.getMina());
			}
		}
		this.ExploredGraph = (WeightedMultigraph<Celda, Integer>) other
				.getExploredGraph().clone();
		this.completeGraph = (WeightedMultigraph<Celda, Integer>) other
				.getCompleteGraph().clone();
	}

	public WeightedMultigraph<Celda, Integer> getExploredGraph() {
		return ExploredGraph;
	}

	public void setExploredGraph(
			WeightedMultigraph<Celda, Integer> exploredGraph) {
		ExploredGraph = exploredGraph;
	}

	public WeightedMultigraph<Celda, Integer> getCompleteGraph() {
		return completeGraph;
	}

	public void setCompleteGraph(
			WeightedMultigraph<Celda, Integer> completeGraph) {
		this.completeGraph = completeGraph;
	}

	public Mapa(int rows, int column) {
		this.mapa = new Celda[rows][column];
		this.rows = rows;
		this.columns = column;
		for (int i = 0; i < this.rows; i++)
			for (int j = 0; j < this.columns; j++)
				this.mapa[i][j] = new Celda(i, j, true, false);
		this.ExploredGraph = new WeightedMultigraph<Celda, Integer>(
				Integer.class);
		this.completeGraph = new WeightedMultigraph<Celda, Integer>(
				Integer.class);
	}

	public Mapa(Celda[][] mapa) {
		this.mapa = mapa;
		this.rows = mapa.length;
		this.columns = mapa.length;
		this.ExploredGraph = new WeightedMultigraph<Celda, Integer>(
				Integer.class);
		this.completeGraph = new WeightedMultigraph<Celda, Integer>(
				Integer.class);
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
			this.mapa[2][1].setMina(true);
			this.ExploredGraph = new WeightedMultigraph<Celda, Integer>(
					Integer.class);
			this.completeGraph = new WeightedMultigraph<Celda, Integer>(
					Integer.class);
			instance = this;
		}
	}

	public Mapa getInstance() {
		return this.instance;
	}

	public synchronized int getNumExploradas() {
		return numExploradas;
	}

	public synchronized void setNumExploradas(int numExploradas) {
		this.numExploradas = numExploradas;
	}

	public synchronized int getNumCompletas() {
		return numCompletas;
	}

	public synchronized void setNumCompletas(int numCompletas) {
		this.numCompletas = numCompletas;
	}

	public synchronized boolean tieneMina(int row, int column) {
		if (!mapa[row][column].getMina())
			this.updateGrafo(row, column);
		return mapa[row][column].getMina();

	}

	public synchronized boolean haSidoExplorada(Celda c) {
		return this.ExploredGraph.containsVertex(c);
	}

	public synchronized boolean existeCamino(Celda fin, Celda ini) {
		try {
			return this.findPath(fin, ini) != null;
		} catch (Exception e) {
			return false;
		}
	}

	public synchronized GraphPath<Celda, Integer> findPath(Celda fin, Celda ini) {
		WeightedMultigraph<Celda, Integer> aux = new WeightedMultigraph<Celda, Integer>(
				Integer.class);
		for (Celda c : this.ExploredGraph.vertexSet())
			aux.addVertex(c);
		for (Integer p : this.ExploredGraph.edgeSet())
			aux.addEdge(this.ExploredGraph.getEdgeSource(p),
					this.ExploredGraph.getEdgeTarget(p), p);
		if (!aux.containsVertex(fin)) {
			aux.addVertex(fin);
			int r = (int) fin.getX();
			int c = (int) fin.getY();
			int n = this.numExploradas;
			if (r > 0 && this.mapa[r - 1][c].getAccesible()) {
				if (aux.containsVertex(this.mapa[r - 1][c])) {
					aux.addEdge(fin, this.mapa[r - 1][c], n);
					n++;
				}
			}
			if (r < this.rows - 1 && this.mapa[r + 1][c].getAccesible()) {
				if (aux.containsVertex(this.mapa[r + 1][c])) {
					aux.addEdge(fin, this.mapa[r + 1][c], n);
					n++;
				}
			}
			if (c > 0 && this.mapa[r][c - 1].getAccesible()) {
				if (aux.containsVertex(this.mapa[r][c - 1])) {
					aux.addEdge(fin, this.mapa[r][c - 1], n);
					n++;
				}
			}
			if (c < this.columns - 1 && this.mapa[r][c + 1].getAccesible()) {
				if (aux.containsVertex(this.mapa[r][c + 1])) {
					aux.addEdge(fin, this.mapa[r][c + 1], n);
					n++;
				}
			}
		}
		DijkstraShortestPath<Celda, Integer> path = new DijkstraShortestPath<Celda, Integer>(
				aux, ini, fin);
		return path.getPath();
	}

	public synchronized void updateGrafo(int r, int c) {
		Celda celda = this.mapa[r][c];
		if (!this.ExploredGraph.containsVertex(celda))
			this.ExploredGraph.addVertex(celda);
		if (!this.completeGraph.containsVertex(celda))
			this.completeGraph.addVertex(celda);
		if (r > 0 && this.mapa[r - 1][c].getAccesible()) {
			if (this.ExploredGraph.containsVertex(this.mapa[r - 1][c])) {
				this.ExploredGraph.addEdge(celda, this.mapa[r - 1][c],
						this.numExploradas);
				this.numExploradas++;
			} else {
				this.completeGraph.addVertex(this.mapa[r - 1][c]);
				this.completeGraph.addEdge(celda, this.mapa[r - 1][c],
						this.numCompletas);
				this.numCompletas++;
			}
		}
		if (r < this.rows - 1 && this.mapa[r + 1][c].getAccesible()) {
			if (this.ExploredGraph.containsVertex(this.mapa[r + 1][c])) {
				this.ExploredGraph.addEdge(celda, this.mapa[r + 1][c],
						this.numExploradas);
				this.numExploradas++;
			} else {
				this.completeGraph.addVertex(this.mapa[r + 1][c]);
				this.completeGraph.addEdge(celda, this.mapa[r + 1][c],
						this.numCompletas);
				this.numCompletas++;
			}
		}
		if (c > 0 && this.mapa[r][c - 1].getAccesible()) {
			if (this.ExploredGraph.containsVertex(this.mapa[r][c - 1])) {
				this.ExploredGraph.addEdge(celda, this.mapa[r][c - 1],
						this.numExploradas);
				this.numExploradas++;
			} else {
				this.completeGraph.addVertex(this.mapa[r][c - 1]);
				this.completeGraph.addEdge(celda, this.mapa[r][c - 1],
						this.numCompletas);
				this.numCompletas++;
			}
		}
		if (c < this.columns - 1 && this.mapa[r][c + 1].getAccesible()) {
			if (this.ExploredGraph.containsVertex(this.mapa[r][c + 1])) {
				this.ExploredGraph.addEdge(celda, this.mapa[r][c + 1],
						this.numExploradas);
				this.numExploradas++;
			} else {
				this.completeGraph.addVertex(this.mapa[r][c + 1]);
				this.completeGraph.addEdge(celda, this.mapa[r][c + 1],
						this.numCompletas);
				this.numCompletas++;
			}
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
		ArrayList<Celda> adys = new ArrayList<Celda>();
		for (Celda c : this.completeGraph.vertexSet())
			if (!this.ExploredGraph.containsVertex(c))
				adys.add(c);
		return adys;
	}

	public List<Celda> getAdyacentes(Celda c) {
		ArrayList<Celda> adys = new ArrayList<Celda>();
		double x = c.getX();
		double y = c.getY();
		if (x > 0) {
			if (!this.ExploredGraph.containsVertex(this.getCelda((int) x - 1,
					(int) y)))
				adys.add(this.getCelda((int) x - 1, (int) y));
			if (y > 0
					&& !this.ExploredGraph.containsVertex(this.getCelda(
							(int) x - 1, (int) y - 1)))
				adys.add(this.getCelda((int) x - 1, (int) y - 1));
			if (y < this.getColumns() - 1
					&& this.ExploredGraph.containsVertex(this.getCelda(
							(int) x - 1, (int) y + 1)))
				adys.add(this.getCelda((int) x - 1, (int) y + 1));
		}
		if (x < this.getRows() - 1) {
			if (!this.ExploredGraph.containsVertex(this.getCelda((int) x + 1,
					(int) y)))
				adys.add(this.getCelda((int) x + 1, (int) y));
			if (y > 0
					&& !this.ExploredGraph.containsVertex(this.getCelda(
							(int) x + 1, (int) y - 1)))
				adys.add(this.getCelda((int) x + 1, (int) y - 1));
			if (y < this.getColumns() - 1
					&& this.ExploredGraph.containsVertex(this.getCelda(
							(int) x + 1, (int) y + 1)))
				adys.add(this.getCelda((int) x + 1, (int) y + 1));
		}
		if (y > 0
				&& !this.ExploredGraph.containsVertex(this.getCelda((int) x,
						(int) y - 1)))
			adys.add(this.getCelda((int) x, (int) y - 1));
		if (y < this.getColumns() - 1
				&& !this.ExploredGraph.containsVertex(this.getCelda((int) x,
						(int) y + 1)))
			adys.add(this.getCelda((int) x, (int) y + 1));
		return adys;
	}

	public List<CeldaCandidata> getCosteAdyacentes(Celda posicionActual) {
		List<CeldaCandidata> lista = new ArrayList<CeldaCandidata>();
		if (this.completeGraph.containsVertex(posicionActual)) {
			BellmanFordShortestPath<Celda, Integer> path = new BellmanFordShortestPath<Celda, Integer>(
					this.completeGraph, posicionActual);
			for (Celda c : this.getAdyacentes()) {
				CeldaCandidata cell = new CeldaCandidata();
				cell.setCelda(c);
				cell.setCoste(path.getCost(c));
				lista.add(cell);
			}
		}
		else{
			CeldaCandidata cell = new CeldaCandidata();
			cell.setCelda(posicionActual);
			cell.setCoste(1);
			lista.add(cell);
		}
		return lista;
	}

	public void desactivarMina(int x, int y) {
		this.mapa[x][y].desactivarMina();
		this.updateGrafo(x, y);
	}
}
