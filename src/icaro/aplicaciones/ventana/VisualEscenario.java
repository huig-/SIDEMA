package icaro.aplicaciones.ventana;

import java.awt.GridLayout;

import javax.swing.JPanel;

import javafx.scene.paint.Color;

public class VisualEscenario extends JPanel{

	/*public VisualEscenario(Escenario escLog){
		this.setLayout(new GridLayout(escLog.getRows(), escLog.getCol()));
		
		for(int i=0;i<escLog.getRows();i++)
			for(int j=0;j<escLog.getCol();j++)
				this.add(new Celda());
	}*/
	public VisualEscenario(){
		this.setLayout(new GridLayout(8, 8));
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				this.add(new Celda());
	}
	
}
