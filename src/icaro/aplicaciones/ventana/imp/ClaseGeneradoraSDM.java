package icaro.aplicaciones.ventana.imp;

import java.io.File;
import java.rmi.RemoteException;

import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.ControladorVisualizacionSimulRosace;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.NotificadorInfoUsuarioSimulador;
import icaro.aplicaciones.ventana.ItfVentana;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class ClaseGeneradoraSDM extends ImplRecursoSimple implements ItfVentana{

	public ClaseGeneradoraSDM(String idRecurso) throws RemoteException {
		super(idRecurso);
		try {
            trazas.aceptaNuevaTraza(new InfoTraza(idRecurso, "El constructor de la clase generadora del recurso " + idRecurso + " ha completado su ejecucion ....", InfoTraza.NivelTraza.debug));
            //notifEvt = new NotificadorInfoUsuarioSimulador(recursoId, identAgenteaReportar);
            // un agente debe decirle al recurso a quien debe reportar . Se puede poner el agente a reportar fijo
            //controladorIUSimulador = new ControladorVisualizacionSimulRosace(notifEvt);
            
        } catch (Exception e) {
            this.trazas.trazar(this.id, " Se ha producido un error en la creacion del recurso : " + e.getMessage(), InfoTraza.NivelTraza.error);
            this.itfAutomata.transita("error");
            throw e;
        }
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mover(String IdentificadorAgente, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minaEncontrada(int x, int y) {
		this.control.getVentanaEntorno().getEscenario().minaEncontrada(x, y);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarMensaje(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarEntornoSimulacion() {
		this.control = new ControladorVista();
		identFicheroEscenarioSimulacion = this.control.getVentanaEntorno().cargarEscenario();
		//llamar al parser;
		if(identFicheroEscenarioSimulacion != null){
			trazas.aceptaNuevaTraza(new InfoTraza(this.id, "El escenario  : " + identFicheroEscenarioSimulacion.getName() + " se ha cargado correctamente", InfoTraza.NivelTraza.debug));
		}
		else{
			trazas.aceptaNuevaTraza(new InfoTraza(this.id, "El escenario  : " + identFicheroEscenarioSimulacion + " no existe o no se puede abrir ", InfoTraza.NivelTraza.error));
		}
		// TODO Auto-generated method stub
		
	}
	
	public void termina() {
        trazas.aceptaNuevaTraza(new InfoTraza(this.id, "Terminando recurso" + this.id + " ....", InfoTraza.NivelTraza.debug));

        //Si es un recurso de visualizacion es necesaria una llamar a dispose de la ventana de visualizacion. Algo parecido a lo siguiente	
        //this.jvariableLocalReferenciaVisualizador.dispose(); //Destruye los componentes utilizados por este JFrame y devuelve la memoria utilizada al Sistema Operativo 	 

        super.termina();
    }
	
	private ControladorVista control;
	//private String directorioPersistencia .. ;
	private File identFicheroEscenarioSimulacion;
	private NotificadorInfoUsuarioSimulador notifEvt;

}
