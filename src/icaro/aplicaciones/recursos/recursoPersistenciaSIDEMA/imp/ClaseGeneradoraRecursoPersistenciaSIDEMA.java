package icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.imp;

//TODO Despues de crear el mapa, que hacemos con el?

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.Vector;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.VocabularioSIDEMA;
import icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.ItfUsoRecursoPersistenciaSIDEMA;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.NotificadorInfoUsuarioSimulador;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

public class ClaseGeneradoraRecursoPersistenciaSIDEMA extends ImplRecursoSimple implements ItfUsoRecursoPersistenciaSIDEMA{
	
	
	public ClaseGeneradoraRecursoPersistenciaSIDEMA(String idRecurso) throws RemoteException {
		super(idRecurso);
		// TODO Auto-generated constructor stub
		try {
			 notifEvt = new NotificadorInfoUsuarioSimulador(super.id, identAgenteAReportar);
	           
			//parserCSVModelo();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException(); //seguro?
		}
	}
	
	
	
	public void parserCSVModelo(File ficheroEscenario) throws Exception {
		@SuppressWarnings("resource") //que es esto?
		BufferedReader br = new BufferedReader(new FileReader(ficheroEscenario)); //TODO csvFile
		this.ficheroEscenario = ficheroEscenario;
		Vector<String> lines = new Vector<String>();
		String line;
		while ((line = br.readLine()) != null){
			lines.add(line);
		}
		int i = 0;
		String []cell = lines.get(0).split(VocabularioSIDEMA.CSV_SplitBy);
		m = new Mapa(lines.size(), cell.length);
		int cont_inaccesibles = 0;
		for (String line2 : lines){
			String []cells = line2.split(VocabularioSIDEMA.CSV_SplitBy);
			int j = 0;
			for (String c : cells) {
				if (c.equals(VocabularioSIDEMA.CSV_ObstacleCell)) {
					m.setInaccesible(i, j);
					cont_inaccesibles++;
				}
				else if (c.equals(VocabularioSIDEMA.CSV_EmptyCell));
				else if (c.equals(VocabularioSIDEMA.CSV_BombCell)) m.setMina(i, j);
				else throw new Exception("Type of cell not valid");
				j++;
			}
			i++;
		}
		m.setNumInaccesibles(cont_inaccesibles);
		br.close();
		this.notifEvt.informaraOtroAgenteReactivo(new InfoContEvtMsgAgteReactivo(VocabularioSIDEMA.informarEscenarioValido), identAgenteAReportar);
		
	}
	
	public void setFicheroEscenario(File fichero) throws Exception{
		this.ficheroEscenario = fichero;
		this.parserCSVModelo(fichero);
	}
	
	public Mapa getEscenario() throws Exception{
		return this.m;
	}
	
	public void setEscenario(Mapa mapa) throws Exception{
		this.m = mapa;
	}
	
	public File getFicheroEscenario() throws Exception{
		return this.ficheroEscenario;
	}
	

	
	private Mapa m;
	private File ficheroEscenario;
	private NotificadorInfoUsuarioSimulador notifEvt;

}


