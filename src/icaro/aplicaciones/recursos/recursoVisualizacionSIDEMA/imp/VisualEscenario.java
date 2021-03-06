package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;

public class VisualEscenario extends JPanel{

	public VisualEscenario(Mapa escLog){
		this.setLayout(new GridLayout(escLog.getRows(), escLog.getColumns()));
		this.mapaVisual = new VisualCelda[escLog.getRows()][escLog.getColumns()];
		boolean mina;
		boolean accesible;
		VisualCelda cel;
		for(int i=0;i<escLog.getRows();i++)
			for(int j=0;j<escLog.getColumns();j++){
				mina = escLog.getCelda(i, j).getMina();
				accesible = escLog.getCelda(i,j).getAccesible();
				cel = new VisualCelda(mina,accesible);
				this.add(cel);
				this.mapaVisual[i][j]=cel;}	
	}
	
	public VisualEscenario(){
		this.setLayout(new GridLayout(8, 8));
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				this.add(new VisualCelda());
	}
	
	public void minaEncontrada(int i,int j){
		this.mapaVisual[i][j].minaEncontrada();
	}
	
	public void abandonarCeldaExplorador(int i, int j){
		this.mapaVisual[i][j].abandonarCeldaExplorador();
	}
	
	public void abandonarCeldaNeutralizador(int i, int j){
		this.mapaVisual[i][j].abandonarCeldaNeutralizador();
	}
	
	public void movimientoExplorador(int i, int j){
		this.mapaVisual[i][j].movimientoExplorador();
	}
	
	public void movimientoNeutralizador(int i, int j){
		this.mapaVisual[i][j].movimientoNeutralizador();
	}
	
	public void desactivarMina(int x, int y){
		this.mapaVisual[x][y].desactivarMina();
	}
	
	
	private VisualCelda[][] mapaVisual;
	//private ArrayList<Type> agentes;
}
