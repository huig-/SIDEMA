package icaro.aplicaciones.ventana;

import java.awt.GridLayout;

import javax.swing.JPanel;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
//import javafx.scene.paint.Color;

public class VisualEscenario extends JPanel{

	public VisualEscenario(Mapa escLog){
		this.setLayout(new GridLayout(escLog.getRows(), escLog.getColumns()));
		this.mapaVisual = new CeldaVisual[escLog.getRows()][escLog.getColumns()];
		boolean mina;
		boolean accesible;
		CeldaVisual cel;
		for(int i=0;i<escLog.getRows();i++)
			for(int j=0;j<escLog.getColumns();j++){
				mina = escLog.getCelda(i, j).getMina();
				accesible = escLog.getCelda(i,j).getAccesible();
				cel = new CeldaVisual(mina,accesible);
				this.add(cel);
				this.mapaVisual[i][j]=cel;}	
	}
	
	public VisualEscenario(){
		this.setLayout(new GridLayout(8, 8));
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				this.add(new CeldaVisual());
	}
	
	public void MinaEncontrada(int i,int j){
		this.mapaVisual[i][j].minaEncontrada();
	}
	
	private CeldaVisual[][] mapaVisual;
}
