package icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA;

import java.io.File;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoPersistenciaSIDEMA extends ItfUsoRecursoSimple{
	
	public void parserCSVModelo() throws Exception;
	public Mapa proporcionarEscenario() throws Exception;
	public void obtenerFicheroEscenario(File fichero) throws Exception;

}
