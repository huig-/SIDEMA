package icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.imp;

import java.rmi.RemoteException;

import icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.ItfUsoRecursoPersistenciaSIDEMA;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

public class ClaseGeneradoraRecursoPersistenciaSIDEMA extends ImplRecursoSimple implements ItfUsoRecursoPersistenciaSIDEMA{

	public ClaseGeneradoraRecursoPersistenciaSIDEMA(String idRecurso) throws RemoteException {
		super(idRecurso);
		// TODO Auto-generated constructor stub
	}
	
	public void parserXMLModelo() throws Exception {
		
	}

}
