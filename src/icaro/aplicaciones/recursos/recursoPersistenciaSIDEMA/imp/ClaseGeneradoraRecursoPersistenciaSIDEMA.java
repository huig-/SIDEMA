package icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.imp;

//TODO Despues de crear el mapa, que hacemos con el?

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.VocabularioSIDEMA;
import icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.ItfUsoRecursoPersistenciaSIDEMA;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

public class ClaseGeneradoraRecursoPersistenciaSIDEMA extends ImplRecursoSimple implements ItfUsoRecursoPersistenciaSIDEMA{
	
	private int numExplorers = 0;
	private int numNeutralizers = 0;
	private Mapa m;

	public ClaseGeneradoraRecursoPersistenciaSIDEMA(String idRecurso) throws RemoteException {
		super(idRecurso);
		// TODO Auto-generated constructor stub
		try {
			parserCSVModelo();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException(); //seguro?
		}
	}
	
	public void parserCSVModelo() throws Exception {
		@SuppressWarnings("resource") //que es esto?
		BufferedReader br = new BufferedReader(new FileReader("csvFile")); //TODO csvFile
		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			String []cells = line.split(VocabularioSIDEMA.CSV_SplitBy);
			m = new Mapa(cells.length, cells.length);
			int j = 0;
			for (String c : cells) {
				if (c.equals(VocabularioSIDEMA.CSV_ObstacleCell)) m.setInaccesible(i, j);
				else if (c.equals(VocabularioSIDEMA.CSV_EmptyCell));
				else if (c.equals(VocabularioSIDEMA.CSV_BombCell)) m.setMina(i, j);
				else throw new Exception("Type of cell not valid");
				j++;
			}
			i++;
		}
		br.close();
	}
}
