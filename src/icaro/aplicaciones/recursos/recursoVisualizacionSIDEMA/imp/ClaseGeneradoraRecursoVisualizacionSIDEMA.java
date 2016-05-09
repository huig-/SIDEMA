package icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.imp;

import java.io.File;
import java.rmi.RemoteException;

import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.VocabularioSIDEMA;
import icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.ItfUsoRecursoVisualizacionSIDEMA;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.ControladorVisualizacionSimulRosace;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.NotificadorInfoUsuarioSimulador;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class ClaseGeneradoraRecursoVisualizacionSIDEMA extends ImplRecursoSimple implements ItfUsoRecursoVisualizacionSIDEMA{
	private static final long serialVersionUID = 1L;
	
	public ClaseGeneradoraRecursoVisualizacionSIDEMA(String idRecurso) throws Exception {
		super(idRecurso);
		try {
            trazas.aceptaNuevaTraza(new InfoTraza(idRecurso, "El constructor de la clase generadora del recurso " + idRecurso + " ha completado su ejecucion ....", InfoTraza.NivelTraza.debug));
            notifEvt = new NotificadorInfoUsuarioSimulador(super.id, identAgenteaReportar);
            // un agente debe decirle al recurso a quien debe reportar . Se puede poner el agente a reportar fijo
            //controladorIUSimulador = new ControladorVisualizacionSimulRosace(notifEvt);
            this.mostrarEntornoSimulacion();
            
        } catch (Exception e) {
            this.trazas.trazar(this.id, " Se ha producido un error en la creacion del recurso : " + e.getMessage(), InfoTraza.NivelTraza.error);
            this.itfAutomata.transita("error");
            throw e;
        }
		
	}

	@Override
	public void mover(String identAgente, int x, int y) throws Exception {
		if(identAgente.equals("explorador")){
			this.control.getVentanaEntorno().getEscenario().movimientoExplorador(x, y);
		}else{
			this.control.getVentanaEntorno().getEscenario().movimientoNeutralizador(x, y);
		}		
	}

	@Override
	public void minaEncontrada(int x, int y) throws Exception{
		this.control.getVentanaEntorno().getEscenario().minaEncontrada(x, y);
		
		
	}

	@Override
	public void mostrarMensaje(String msg) throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	public Mapa getMapa()throws Exception{
		return this.control.getMapa();
	}

	@Override
	public void mostrarEntornoSimulacion() throws Exception{
		this.control = new ControladorVista();
		/*identFicheroEscenarioSimulacion = this.control.getVentanaEntorno().cargarEscenario();
		//llamar al parser;
		if(identFicheroEscenarioSimulacion != null){
			trazas.aceptaNuevaTraza(new InfoTraza(this.id, "El escenario  : " + identFicheroEscenarioSimulacion.getName() + " se ha cargado correctamente", InfoTraza.NivelTraza.debug));
		}
		else{
			trazas.aceptaNuevaTraza(new InfoTraza(this.id, "El escenario  : " + identFicheroEscenarioSimulacion + " no existe o no se puede abrir ", InfoTraza.NivelTraza.error));
		}
		
		*/
	}
	
	public void cargarEscenario() throws Exception{
		identFicheroEscenarioSimulacion = this.control.getVentanaEntorno().cargarEscenario();
		if(this.identFicheroEscenarioSimulacion!=null){
			notifEvt.informaraOtroAgenteReactivo(new InfoContEvtMsgAgteReactivo(VocabularioSIDEMA.informarEscenarioSeleccionado), identAgenteaReportar);
		}
	}
	
	public File getFicheroEscenario() throws Exception{
		return this.identFicheroEscenarioSimulacion;
	}
	
	public void setMapa(Mapa m) throws Exception {
		this.control.setMapa(m);
		this.pintado = true;
	}
	
	public void termina(){
        trazas.aceptaNuevaTraza(new InfoTraza(this.id, "Terminando recurso" + this.id + " ....", InfoTraza.NivelTraza.debug));

        //Si es un recurso de visualizacion es necesaria una llamar a dispose de la ventana de visualizacion. Algo parecido a lo siguiente	
        //this.jvariableLocalReferenciaVisualizador.dispose(); //Destruye los componentes utilizados por este JFrame y devuelve la memoria utilizada al Sistema Operativo 	 

        super.termina();
    }
	
	public boolean getMapaPintado() throws Exception{
		return this.pintado;
	}
	
	public void pintarEscenario()throws Exception{
		this.control.pintar();
	}
	
	private boolean pintado;
	private ControladorVista control;
	//private String directorioPersistencia .. ;
	private File identFicheroEscenarioSimulacion;
	private NotificadorInfoUsuarioSimulador notifEvt;
	private String identAgenteaReportar;

}
