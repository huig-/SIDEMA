package icaro.aplicaciones.agentes.agenteAplicacionAgteControladorSimuladorSIDEMA.comportamiento;

import icaro.aplicaciones.Rosace.informacion.*;
import icaro.aplicaciones.Rosace.utils.ConstantesRutasEstadisticas;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.OrdenComenzarSimulacion;
import icaro.aplicaciones.SIDEMA.informacion.VocabularioSIDEMA;
import icaro.aplicaciones.recursos.recursoCreacionEntornosSimulacion.ItfUsoRecursoCreacionEntornosSimulacion;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.ItfUsoRecursoPersistenciaEntornosSimulacion;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp.ReadXMLTestSequence;
import icaro.aplicaciones.recursos.recursoPersistenciaSIDEMA.ItfUsoRecursoPersistenciaSIDEMA;
import icaro.aplicaciones.recursos.recursoVisualizacionSIDEMA.ItfUsoRecursoVisualizacionSIDEMA;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornosSimulacion;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.EscenarioSimulacionRobtsVictms;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.patronAgenteReactivo.control.acciones.AccionesSemanticasAgenteReactivo;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza.NivelTraza;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.openide.util.Exceptions;
import org.w3c.dom.NodeList;
//This agent class need the next imports in order to use resources

//Other imports used by this Agent
//#start_nodespecialImports:specialImports <--specialImports-- DO NOT REMOVE THIS
//#end_nodespecialImports:specialImports <--specialImports-- DO NOT REMOVE THIS
public class AccionesSemanticasAgenteAplicacionAgteControladorSimuladorSIDEMA  extends AccionesSemanticasAgenteReactivo {

    // Include in this section global variables used in this Agent
    // #start_nodeglobalVariables:globalVariables <--globalVariables-- DO NOT
    // REMOVE THIS
    private ReadXMLTestSequence rXMLTSeq; // para leer del fichero de xml de victimas
    private NodeList nodeLst;      // estructura en memoria con todos los nodos de
    // las victimas que hay en el fichero xml
    private int numMensajesEnviar; // numero total de nodos que hay en nodeLst
    private ItfUsoRecursoVisualizacionSIDEMA itfUsoRecursoVisualizacionSIDEMA;   //Para visualizar graficas de estadisticas
    private InfoEquipo equipo;
    private String identificadorEquipo;
    private String modeloOrganizativo;
    private ArrayList identsAgtesEquipo;
//	private ComunicacionAgentes comunicacion;
    private boolean stop = false; // Parar la simulacion
//	private ItfUsoRepositorioInterfaces itfUsoRepositorioInterfaces;
    private ItfUsoConfiguracion itfconfig;
    private String rutaFicheroVictimasTest;
    private String rutaFicheroRobotsTest;
    private long tiempoInicialDeLaSimulacion = 0;      //Variable para obtener el tiempo al iniciar la simulacion
    private int numeroVictimasEntorno = 0;            //Numero de victimas actuales que se han introducido en el entorno    
    private int numeroVictimasAsignadas = 0;          //Numero de victimas asignadas a robots
    private int numeroVictimasDiferentesSimulacion; //Numero de victimas diferentes que van a intervenir en el proceso de simulacion
    private int numeroRobotsSimulacion = 0;         //Numero de robots diferentes que van a intervenir en el proceso de simulacion
    private int intervaloSecuencia;                 //Intervalo uniforme de envio de la secuencia de victimas
    private ArrayList<Victim> victimasDefinidas;     //Victimas definidas en la simulacion 
    private Map<String, Victim> victims2Rescue ;      //Victimas que han sido enviadas al equipo   
    private Map<String, String> victimasAsignadas = new HashMap<String, String>();   //Victimas que han sido asignadas a algun robot, es decir, algun robot se ha hecho responsable para salvarla
    private Map<String, InfoAsignacionVictima> infoVictimasAsignadas;
    private ItfUsoRecursoCreacionEntornosSimulacion itfUsoRecursoCreacionEntornosSimulacion = null;
    private ItfUsoRecursoPersistenciaSIDEMA itfUsoRecursoPersistenciaSIDEMA;
    private InfoSerieResultadosSimulacion infoContxVict;
//    private InfoAsignacionVictima infoAsigVictima;
    private InfoCasoSimulacion infoCasoSimul;
    private InfoEntornoCasoSimulacion infoEntornoCaso;
    private int indexvictimasAsignadasEstadistica = 0;
    private int contadorRobotsQueContestanFinsimulacion = 0;
    final int nMM = this.numMensajesEnviar; // numeroMaximoDeMensajes a  enviar										
    final int interv = intervaloSecuencia;
    // #end_nodeglobalVariables:globalVariables <--globalVariables-- DO NOT
    // REMOVE THIS
    private boolean peticionTerminacionSimulacionUsuario = false;
    private boolean robotEstatusEquipoInicializado = false;
    private Mapa escenarioActual;
    private File identFicheroEscenario;

    // AccionA is the action initial executed when agent manager sends the comenzar event
    public void AccionComenzar() {
        //Inicializar las interfaces a los recursos que se van a utilizar
        //----------------------------------------------------------------------------------------------------------------
        // INICIALIZACION DE VARIABLES RELACIONADAS CON LA SIMULACION
        //----------------------------------------------------------------------------------------------------------------			
        // Lectura del fichero de victimas
        // Recuperar la ruta del fichero de victimas del escenario
        //itfUsoRepositorioInterfaces = ClaseGeneradoraRepositorioInterfaces.instance();


        //----------------------------------------------------------------------------------------------------------------
        // Inicializar variables para la comunicacion, el identificadorEquipo,
        // los identificadores de los agentes del equipo
        //----------------------------------------------------------------------------------------------------------------	
        try {
            itfconfig = (ItfUsoConfiguracion) this.itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.NOMBRE_ITF_USO_CONFIGURACION);
            itfUsoRecursoPersistenciaSIDEMA = (ItfUsoRecursoPersistenciaSIDEMA) this.itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + "RecursoPersistenciaSIDEMA1");
            itfUsoRecursoVisualizacionSIDEMA = (ItfUsoRecursoVisualizacionSIDEMA) this.itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + "RecursoVisualizacionSIDEMA1");
            itfUsoRecursoVisualizacionSIDEMA.setIdentAgenteAReportar(this.nombreAgente);
            itfUsoRecursoPersistenciaSIDEMA.setIdentAgenteAReportar(this.nombreAgente);
       
            //identFicheroEscenario = itfconfig.getValorPropiedadGlobal(VocabularioRosace.NombreFicheroEscenarioSimulacion);
            identificadorEquipo = itfconfig.getValorPropiedadGlobal(VocabularioRosace.NombrePropiedadGlobalIdentEquipo);
            equipo = new InfoEquipo(this.nombreAgente, identificadorEquipo);
            identsAgtesEquipo = equipo.getTeamMemberIDs(); // Se obtienen de la configuracion
            comunicator = this.getComunicator();
            this.obtenerEscenarioSimulacion();
//            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        trazas.trazar(this.nombreAgente, "Accion AccionComenzar completada ....", NivelTraza.debug);
    }
   public void obtenerEscenarioSimulacion(){
       // Se intenta primero obtenerlo de  la descripcion de la orgaizacion y de la persistencia 
       // Si no se obtiene nada se le pide a la interfaz de usuario
       try {
        if (identFicheroEscenario != null){
                escenarioActual = itfUsoRecursoPersistenciaSIDEMA.getEscenario();
               
            }else{
        	itfUsoRecursoVisualizacionSIDEMA.cargarEscenario();
        	this.identFicheroEscenario = itfUsoRecursoVisualizacionSIDEMA.getFicheroEscenario();
        	//this.informaraMiAutomata("escenarioSeleccionadoUsuario", null);
        	
        }
            /*if (escenarioActual != null){
                escenarioActual.renombrarIdentRobts(identsAgtesEquipo);
                itfUsoRecursoVisualizacionSIDEMA.mostrarVentanaControlSimulador(escenarioActual);
                this.inicializarEstatusRobotsEquipo();
                victims2Rescue = escenarioActual.getVictims();
                numeroVictimasDiferentesSimulacion = victims2Rescue.size();
                this.informaraMiAutomata("escenarioDefinidoValido", null);
            }
            else{
                modeloOrganizativo = itfconfig.getValorPropiedadGlobal(VocabularioRosace.NOMBRE_PROPIEDAD_GLOBAL_MODELO_ORGANIZATIVO);
                this.numeroRobotsSimulacion = identsAgtesEquipo.size();
                itfUsoRecursoVisualizacionSIDEMA.obtenerEscenarioSimulacion(modeloOrganizativo, numeroRobotsSimulacion);
            }*/
            } catch (Exception e) {
            e.printStackTrace();
        }
       trazas.trazar(this.nombreAgente, "Accion obtenerEscenarioSimulacion completada ....", NivelTraza.debug);
   }
   
   public void  validarEscenarioRecibido (){
        try {
          itfUsoRecursoPersistenciaSIDEMA.parserCSVModelo(identFicheroEscenario);
//          this.informaraMiAutomata("escenarioDefinidoValido", null);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        trazas.trazar(this.nombreAgente, "Accion validarEscenarioRecibido completada ....", NivelTraza.debug);
   }
   
   public void mostrarEscenarioSimulacion(){
	   try {
		itfUsoRecursoVisualizacionSIDEMA.mostrarEntornoSimulacion();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   trazas.trazar(this.nombreAgente, "Accion mostrarEscenarioSimulacion completada ....", NivelTraza.debug);
   }
   
   
  
   public void comenzarSimulacion(){
	   Thread t = new Thread() {

           @Override
           public void run() {
               while ((stop == false)) {
                   //      victima = createNewVictim(rXMLTSeq, nodeLst, i);
                   OrdenComenzarSimulacion ccOrder = new OrdenComenzarSimulacion("ControlCenter");
                   // Escribir nueva linea de estadistica en el fichero de llegada de victimas					
                   try {
                	   
                       comunicator.enviarInfoAotroAgente(ccOrder, VocabularioSIDEMA.IdentAgteDistribuidorTareas);
                   }
               catch (Exception e) {
                   e.printStackTrace();
               }
                   try {
                       this.sleep(interv);
                   } catch (InterruptedException ex) {
                       ex.printStackTrace();
                   }
               }// fin del while

               // Se han enviado todas las victimas
               // Cerrar el fichero de estadistica en el fichero de llegada de victimas

           }
       };
       t.start();
   }  
   
   

    //Esta accion semantica se ejecuta cuando se envia el input "sendSequenceOfSimulatedVictimsToRobotTeam" en el 
    //metodo sendSequenceOfSimulatedVictimsToRobotTeam de la clase NotificacionEventosRecursoGUI3	
 /*   public void sendSequenceOfSimulatedVictimsToRobotTeam(Integer intervaloSecuencia) {
        this.intervaloSecuencia = intervaloSecuencia;
        final int interv = intervaloSecuencia;
        trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente, "Accion SendSequenceOfSimulatedVictimsToRobotTeam  ...."
                + intervaloSecuencia, InfoTraza.NivelTraza.debug));
        //--------------------------------------------------------------------------------------------------------------------
        // Inicializar y recuperar la referencia al recurso de estadisticas y el visualizador de estadisticas
        // Inicializar el numero de victimas diferentes que hay en la simulacion en el recurso PersistenciaEntornosSimulacion
        //--------------------------------------------------------------------------------------------------------------------
        long tiempoActual = 0;

        try {
            //    itfUsoRecursoPersistenciaEntornosSimulacion.setNumeroVictimasDiferentesSimulacion(rXMLTSeq);
            //	this.numeroVictimasDiferentesSimulacion = itfUsoRecursoPersistenciaEntornosSimulacion.getNumeroVictimasDiferentesSimulacion();
//            victimasDefinidas = this.itfUsoRecursoPersistenciaEntornosSimulacion.getVictimasArescatar();
//            numeroVictimasDiferentesSimulacion = victimasDefinidas.size();
            infoVictimasAsignadas = new HashMap<String, InfoAsignacionVictima>();
            trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                    "Accion SendSequenceOfSimulatedVictimsToRobotTeam  .... "
                    + "Simulacion de tipo " + identificadorEquipo + " ; "
                    + numeroRobotsSimulacion + " robots en la simulacion ; "
                    + numeroVictimasDiferentesSimulacion + " Victimas Diferentes Simulacion "
                    + " (numero Mensajes a Enviar " + this.numMensajesEnviar + ") "
                    + " ; frecuencia de envio " + intervaloSecuencia + " milisegundos", InfoTraza.NivelTraza.debug));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //----------------------------------------------------------------------------------------------------------------	
        // Inicializar fichero Estadistica de llegada de victimas y asignacion
        // de victimas
        //----------------------------------------------------------------------------------------------------------------	
        try {
            tiempoActual = System.currentTimeMillis();
            tiempoInicialDeLaSimulacion = tiempoActual;
            String identCaso = identificadorEquipo + tiempoActual;

            //     infoContxVict = new InfoContextoAsignacionVictima(identificadorEquipo, numeroVictimasDiferentesSimulacion, numeroRobotsSimulacion,  this.intervaloSecuencia); 
            infoEntornoCaso = new InfoEntornoCasoSimulacion(identificadorEquipo, numeroRobotsSimulacion, numeroVictimasDiferentesSimulacion, intervaloSecuencia);
            infoEntornoCaso.setTiempoInicioSimulacion(tiempoInicialDeLaSimulacion);
            infoCasoSimul = new InfoCasoSimulacion(identCaso);
            infoCasoSimul.setInfoCasoSimulacion(infoEntornoCaso);
            infoCasoSimul.setTiempoInicioEnvioPeticiones(tiempoInicialDeLaSimulacion);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //----------------------------------------------------------------------------------------------------------------	
        // Crear hilo responsable de realizar el envio de la secuencia de victimas a intervalos regulares de tiempo
        //----------------------------------------------------------------------------------------------------------------	

        //final ReadXMLTestSequence rNXM = rXMLTSeq;
        //final NodeList nl = nodeLst;
        Thread t = new Thread() {

            @Override
            public void run() {
                int i = 0;
                Victim victima;
                 Object[] victimas= victims2Rescue.values().toArray();
                while ((i < numeroVictimasDiferentesSimulacion) && (stop == false)) {
                    //      victima = createNewVictim(rXMLTSeq, nodeLst, i);
                    victima = (Victim)victimas[i];
                    OrdenCentroControl ccOrder = new OrdenCentroControl("ControlCenter", VocabularioRosace.MsgOrdenCCAyudarVictima, victima);
                    // Escribir nueva linea de estadistica en el fichero de llegada de victimas					
                    try {
                        long tActual = System.currentTimeMillis();

                        //Lo siguiente se hacia en escribeEstadisticaFicheroXMLTRealLlegadaVictimas //EN EL FUTURO HABRIA QUE INTENTAR QUITARLO DE ESE METODO
                        Victim valor = victims2Rescue.put(victima.getName(), victima);
                        if (valor == null) //no estaba insertado
                        {
                            incrementarNumeroVictimasActuales();
                        }
                        //Actualiza el fichero EstadisticasLlegadaVictimas.xml
//                        itfUsoRecursoPersistenciaEntornosSimulacion.escribeEstadisticaFicheroXMLTRealLlegadaVictimas(tActual, victima);
                        //Anotar informacion en el buffer de estadisticas de victimas que llegan al entorno
                        InfoAsignacionVictima infoAsigVictima = new InfoAsignacionVictima();
                        infoAsigVictima.setNrovictimasenentorno(numeroVictimasEntorno);
                        infoAsigVictima.setTiempoPeticion(tActual - tiempoInicialDeLaSimulacion);
                        infoAsigVictima.setVictima(victima);
                        infoCasoSimul.addEnvioVictima(infoAsigVictima);
                        ///////
                        //        VictimaLlegadaEstadistica victLlegEst = new VictimaLlegadaEstadistica();
                        //        victLlegEst.setVictima(victima);
                        //        victLlegEst.setTiempoActual(tActual);
                        //        victLlegEst.setNrovictimas(numeroVictimasEntorno);
                        //	victimasLlegadaEstadistica.add(i-1, victLlegEst);  //el buffer empieza en la posicion 0
                        //       victimasLlegadaEstadistica.add(i, victLlegEst);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (identificadorEquipo.equals("robotSubordinado")) //VocabularioRosace.IdentEquipoJerarquico
                    {
                        comunicator.enviarInfoAotroAgente(ccOrder, VocabularioRosace.IdentAgteDistribuidorTareas);
                    } else {
                        comunicator.informaraGrupoAgentes(ccOrder, identsAgtesEquipo);
                    }

                    i++;
                    try {
                        this.sleep(interv);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }// fin del while

                // Se han enviado todas las victimas
                // Cerrar el fichero de estadistica en el fichero de llegada de victimas

            }
        };
        t.start();
    }
    */
   
/*public void sendSimulatedVictimToRobotTeam(String idVictima) {
    trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente, "Accion SendVictimToRobotTeam  .... "
                + idVictima, InfoTraza.NivelTraza.debug));
    
    if(victims2Rescue == null)victims2Rescue =escenarioActual.getVictims();
    Victim victima = victims2Rescue.get(idVictima);
    OrdenCentroControl ccOrder = new OrdenCentroControl("ControlCenter", VocabularioRosace.MsgOrdenCCAyudarVictima, victima);
    comunicator.informaraGrupoAgentes(ccOrder, identsAgtesEquipo);
}*/
   /*
    //Esta accion semantica se ejecuta cuando se envia el input "victimaAsignadaARobot" en la  
    //tarea sincrona GeneraryEncolarObjetivoActualizarFoco del agente Subordinado
    //Esta accion semantica se ejecuta cuando se envia el input "victimaAsignadaARobot" en la  
    //tarea sincrona EncolarObjetivoActualizarFoco del agente Igualitario (robotMasterIA)	
    public void VictimaAsignadaARobot(Long tiempoReportado, String refVictima, String nombreAgenteEmisor, Integer miEvaluacion) {
        try {
            trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                    "Accion VictimaAsignadaARobot  ... " + "tiempoActual->" + tiempoReportado + " ; refVictima->"
                    + refVictima + " ; nombreAgenteEmisor->" + nombreAgenteEmisor + " ; miEvaluacion->" + miEvaluacion, InfoTraza.NivelTraza.debug));
            itfUsoRecursoVisualizacionSIDEMA.mostrarVictimaRescatada(refVictima);
            if (infoCasoSimul!=null){
            InfoAsignacionVictima infoAsigVictima = infoCasoSimul.getInfoAsignacionVictima(refVictima);
            infoAsigVictima.setEvaluacion(miEvaluacion);
            infoAsigVictima.setRobotId(nombreAgenteEmisor);
            infoAsigVictima.setTiempoAsignacion(tiempoReportado - tiempoInicialDeLaSimulacion); // tiempo reportado
            infoAsigVictima.setNrovictimastotalasignadas(infoCasoSimul.getnumeroVictimasAsignadas());
            infoAsigVictima.setNrovictimasenentorno(infoCasoSimul.getnumeroVictimasEntorno());
            itfUsoRecursoPersistenciaSIDEMA.guardarInfoAsignacionVictima(infoAsigVictima);
            itfUsoRecursoVisualizacionSIDEMA.mostrarVictimaRescatada(refVictima);
            infoCasoSimul.addAsignacionVictima(infoAsigVictima);
            itfUsoRecursoVisualizacionSIDEMA.mostrarVictimaRescatada(refVictima);
            if (infoCasoSimul.todasLasVictimasAsgnadas()) {
//                notificarFinSimulacion();
                visualizarYguardarResultadosCaso();
            }
            }
        } catch (Exception ex) {
            Logger.getLogger(AccionesSemanticasAgenteAplicacionAgteControladorSimuladorRosace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    private void notificarFinSimulacion() {

        try {
            FinSimulacion finalSimulacion = new FinSimulacion();
            comunicator.informaraGrupoAgentes(finalSimulacion, identsAgtesEquipo);
            trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                    "Se notifica el fin de la simulacion a los agentes del Equipo:identsAgtesEquipo->" + identsAgtesEquipo,
                    InfoTraza.NivelTraza.info));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    private void visualizarYguardarResultadosCaso() {
        try {
            // ArrayList<PuntoEstadistica> llegada = new ArrayList();
            // ArrayList<PuntoEstadistica> asignacion = new ArrayList();
            // Pasarle al visualizador infoSerie y que saque los valores
            itfUsoRecursoVisualizacionSIDEMA.crearVisorGraficasLlegadaYasignacionVictimas(this.numeroRobotsSimulacion, this.numeroVictimasDiferentesSimulacion, this.intervaloSecuencia, this.identificadorEquipo); // parametros definicion titulos		                                                                        
            ArrayList<PuntoEstadistica> llegada = infoCasoSimul.getSerieLlegadaPeticiones().getserieResultadosSimulacion();
            ArrayList<PuntoEstadistica> asignacion = infoCasoSimul.getSerieAsignacion().getserieResultadosSimulacion();
            itfUsoRecursoVisualizacionSIDEMA.visualizarLlegadaYasignacionVictimas(llegada, asignacion);
            itfUsoRecursoVisualizacionSIDEMA.crearVisorGraficasTiempoAsignacionVictimas(this.numeroRobotsSimulacion, this.numeroVictimasDiferentesSimulacion, this.intervaloSecuencia, this.identificadorEquipo); // parametros definicion titulos		                                                                        
            ArrayList<PuntoEstadistica> elapsed = infoCasoSimul.getSerieElapsed().getserieResultadosSimulacion();
            itfUsoRecursoVisualizacionSIDEMA.visualizarTiempoAsignacionVictimas(elapsed);
            itfUsoRecursoPersistenciaSIDEMA.guardarSerieResultadosSimulacion(infoCasoSimul.getSerieAsignacion());
            itfUsoRecursoPersistenciaSIDEMA.guardarSerieResultadosSimulacion(infoCasoSimul.getSerieElapsed());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void procesarInfoAsignacionVictima(Long tiempoAsignacion, String refVictima, String nombreAgenteEmisor, Integer miEvaluacion) {
        // el robot que se ha quedado con la victima informa sobre los detalles de la asingnacion
        // este agente incorpora el contexto de asigancion de la victima
        trazas.trazar(this.nombreAgente,
                "Info Asigancion Victima  ... " + "tiempoActual->" + tiempoAsignacion + " ; refVictima->"
                + refVictima + " ; nombreAgenteEmisor->" + nombreAgenteEmisor + " ; miEvaluacion->" + miEvaluacion, InfoTraza.NivelTraza.debug);
        InfoAsignacionVictima infoAsigVictima = infoCasoSimul.getInfoAsignacionVictima(refVictima);
        infoAsigVictima.setEvaluacion(miEvaluacion);
        infoAsigVictima.setRobotId(nombreAgenteEmisor);
        infoAsigVictima.setTiempoAsignacion(tiempoAsignacion);
        infoAsigVictima.setNrovictimastotalasignadas(infoCasoSimul.getnumeroVictimasAsignadas());
        infoAsigVictima.setNrovictimasenentorno(infoCasoSimul.getnumeroVictimasEntorno());
        infoCasoSimul.addAsignacionVictima(infoAsigVictima);
        if (infoCasoSimul.todasLasVictimasAsgnadas()) {
            this.informaraMiAutomata(VocabularioRosace.informacionFinSimulacion, null);
        }

    }
    //Esta accion semantica se ejecuta cuando se envia el input "finSimulacion" en la  
    //tarea sincrona FinalizarSimulacion del agente Subordinado y el igualitario
    //Nos permite generar un fichero EstadisticaFinalSimulacionAsignacionMisionV2.xml que resume que victimas han sido asignadas a cada robot.
*/
    public void FinSimulacion(String robot, ArrayList idsVictimasFinalesAsignadas, Double tiempoTotalCompletarMisionAtenderVictimasFinalesAsignadas) {

        trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente, "Accion FinSimulacion  .... "
                + "robot->" + robot + " ; idsVictimasFinalesAsignadas->" + idsVictimasFinalesAsignadas
                + " ; tiempoTotalCompletarMisionAtenderVictimasFinalesAsignadas->" + tiempoTotalCompletarMisionAtenderVictimasFinalesAsignadas, InfoTraza.NivelTraza.debug));
        try {
            ArrayList<InfoAsignacionVictima> infoAsignVictms = new ArrayList();
          //  infoAsignVictms = itfUsoRecursoPersistenciaSIDEMA.obtenerInfoAsignacionVictimas();
            contadorRobotsQueContestanFinsimulacion++;
            if (contadorRobotsQueContestanFinsimulacion == identsAgtesEquipo.size())
                this.itfUsoRecursoVisualizacionSIDEMA.mostrarResultadosFinSimulacion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  /*  public void crearTempSolicitarDefinicionEscenario(){
        int  tiempoEsperaDefinicionEscenario = 10000;
        this.generarTimeOutInterno(tiempoEsperaDefinicionEscenario, "timeoutEsperaDefinicionEscenario", nombreAgente, this.itfUsoPropiadeEsteAgente);
        
    }*/
    
   /* public void solicitarDefinicionEscenario(){
        try {
            itfUsoRecursoVisualizacionSIDEMA.notificarRecomendacion ("Sin escenario Definido", "No se puede iniciar la simulacion sin definir un escenario",
                    "Abrir un escenario existente o crear uno nuevo");
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
    }*/

    public void mostrarEscenarioActualSimulado() {

        trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente, "Accion MostrarEscenarioActualSimulado  ....", InfoTraza.NivelTraza.debug));
        try {
//            itfUsoRecursoCreacionEntornosSimulacion.MostrarEscenarioActualSimulado();
            itfUsoRecursoVisualizacionSIDEMA.pintarEscenario();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }/*
   public void  mostrarRobotsActivos (){
        try {
            itfUsoRecursoVisualizacionSIDEMA.mostrarIdentsEquipoRobots(this.identsAgtesEquipo);
        } catch (Exception ex) {
            Logger.getLogger(AccionesSemanticasAgenteAplicacionAgteControladorSimuladorRosace.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    public void  PararRobot (String idRobot){
        OrdenParada orden = new OrdenParada(nombreAgente);
//        orden.setMensajePeticion(VocabularioRosace.MsgePeticionParar);
       this.comunicator.enviarInfoAotroAgente(orden, idRobot);
   }
    
    public void  inicializarEstatusRobotsEquipo (){
        // se envia a cada robot su estatus particular en el escenario : situacion, energia, rol y demas
         String identRobot; 
         RobotStatus1 estatusRobot;
        for (Object identsAgtesEquipo1 : identsAgtesEquipo) {
            identRobot = (String) identsAgtesEquipo1;
            estatusRobot = (RobotStatus1)escenarioActual.getRobotInfo(identRobot).clone();
//            RobotStatus estatusRobotCopia = (RobotStatus)estatusRobot.clone();
            comunicator.enviarInfoAotroAgente(estatusRobot, identRobot);
        }
        robotEstatusEquipoInicializado = true;
    }*/

    // Include in this section other (private) methods used in this agent
    // #start_nodelocalMethods:localMethods <--localMethods-- DO NOT REMOVE THIS
    /**
     * El metodo clasificaError es necesario declararlo, aunque no exista una
     * accion semantica explicita para realizar el tratamiento de errores
     */
    @Override
    public void clasificaError() {
        // TODO Auto-generated method stub
    }

    // ---------------------------------------------------
    // ----------- Metodos auxiliares -----------
    // ---------------------------------------------------
    private void incrementarNumeroVictimasActuales() {
        this.numeroVictimasEntorno++;
    }

    private void incrementarNumeroVictimasAsignadas() {
        this.numeroVictimasAsignadas++;
    }

    private void mostrarVentanaAlertaFinSimulacion() {

        String directorioTrabajo = System.getProperty("user.dir");  //Obtener directorio de trabajo      		

        String msg = "FIN DE LA SIMULACION !!!.\n";
        msg = msg + "Se ha completado la captura de todas las estadisticas para la simulacion actual.\n";
        msg = msg + "Los ficheros de estadisticas se encuentran en el directorio " + directorioTrabajo + "/" + ConstantesRutasEstadisticas.rutaDirectorioEstadisticas + "\n";
        msg = msg + "Los ficheros de estadisticas son los siguientes:\n";
        msg = msg + directorioTrabajo + "/" + ConstantesRutasEstadisticas.rutaficheroXMLEstadisticasLlegadaVictimas + "\n";
        msg = msg + directorioTrabajo + "/" + ConstantesRutasEstadisticas.rutaficheroXMLEstadisticasAsignacionVictimas + "\n";
        msg = msg + directorioTrabajo + "/" + ConstantesRutasEstadisticas.rutaficheroXMLRepartoTareasRobotsYTiempoCompletarlasV2 + "\n";
        msg = msg + directorioTrabajo + "/" + ConstantesRutasEstadisticas.rutaficheroXMLEstadisticasLlegadaYAsignacionVictimas + "\n";
        msg = msg + directorioTrabajo + "/estadisticas/" + "EstIntLlegadaYAsignacionVictims" + "FECHA.xml" + "\n";

        JOptionPane.showMessageDialog(null, msg);
    }
/*
    private void informarResultadosSimulacion() {
        try {
            // visualizamos los resultados
            this.itfUsoRecursoVisualizacionSIDEMA.visualizarLlegadaYasignacionVictimas(identsAgtesEquipo, identsAgtesEquipo);
            this.itfUsoRecursoVisualizacionSIDEMA.visualizarTiempoAsignacionVictimas(identsAgtesEquipo);
            // guardamos los resultados para poder consultarlos

        } catch (Exception ex) {
            Logger.getLogger(AccionesSemanticasAgenteAplicacionAgteControladorSimuladorRosace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
