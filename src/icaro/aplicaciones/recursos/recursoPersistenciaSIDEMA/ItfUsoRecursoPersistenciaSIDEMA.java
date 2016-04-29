package icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA;

import java.io.File;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoPersistenciaSIDEMA extends ItfUsoRecursoSimple{
	
	public void parserCSVModelo(File FicheroEscenario) throws Exception;
	public void setEscenario(Mapa mapa) throws Exception;
	public Mapa getEscenario() throws Exception;
	public void setFicheroEscenario(File fichero) throws Exception;
	public File getFicheroEscenario() throws Exception;

}
