package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.PuntoEstadistica;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.recursos.recursoEstadistica.imp.visualizacionEstadisticas.VisualizacionJfreechart;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornosSimulacion;
import icaro.infraestructura.entidadesBasicas.InfoTraza.NivelTraza;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;

//Other imports used by this Resource
//#start_nodespecialImports:specialImports <--specialImports-- DO NOT REMOVE THIS
//#end_nodespecialImports:specialImports <--specialImports-- DO NOT REMOVE THIS
public class ClaseGeneradoraRecursoVisualizadorEntornosSimulacion extends ImplRecursoSimple implements ItfUsoRecursoVisualizadorEntornosSimulacion {

    private ControlCenterGUI4 ventanaControlCenterGUI;
    private VisorEscenariosRosace visorEscenarios;
    private VisualizacionJfreechart visualizadorJFchart;
    private NotificacionEventosVisualizadorEntornosSimulacion notifEvt;
    private String recursoId;
    private String identAgenteaReportar;
    private Map<String,HebraMovimiento> tablaHebrasMov;
    private int coordX = 40;
    private int coordY = 40;  // valores iniciales 
//   private int coordX, coordY ; // coordenadas de visualizacion  se le dan valores iniciales y se incrementan para que las ventanas no coincidan

    public ClaseGeneradoraRecursoVisualizadorEntornosSimulacion(String idRecurso) throws Exception {
        //#start_nodeconstructorMethod:constructorMethod <--constructorMethod-- DO NOT REMOVE THIS
        super(idRecurso);
        recursoId = idRecurso;
        try {
            trazas.aceptaNuevaTraza(new InfoTraza(idRecurso, "El constructor de la clase generadora del recurso " + idRecurso + " ha completado su ejecucion ....", InfoTraza.NivelTraza.debug));
            notifEvt = new NotificacionEventosVisualizadorEntornosSimulacion(recursoId, identAgenteaReportar);
            // un agente debe decirle al recurso a quien debe reportar . Se puede poner el agente a reportar fijo
            visorEscenarios = new VisorEscenariosRosace();
            ventanaControlCenterGUI = new ControlCenterGUI4(notifEvt);
        } catch (Exception e) {
            this.trazas.trazar(recursoId, " Se ha producido un error en la creación del recurso : " + e.getMessage(), InfoTraza.NivelTraza.error);
            this.itfAutomata.transita("error");
            throw e;
        }
    }

    @Override
    public void mostrarVentanaControlSimulador()throws Exception{
    ventanaControlCenterGUI.setVisible(true);
}
    @Override
    public void crearEInicializarVisorGraficaEstadisticas(String tituloVentanaVisor,
            String tituloLocalGrafico,
            String tituloEjeX,
            String tituloEjeY) throws Exception {
        PlotOrientation orientacionPlot = PlotOrientation.VERTICAL;
        boolean incluyeLeyenda = true;
        boolean incluyeTooltips = true;

        Color colorChartBackgroundPaint = Color.white;
        Color colorChartPlotBackgroundPaint = Color.lightGray;
        Color colorChartPlotDomainGridlinePaint = Color.white;
        Color colorChartPlotRangeGridlinePaint = Color.white;
//		VisualizacionJfreechart visualizadorJFchart = new VisualizacionJfreechart("tituloVenanaVisor");
        visualizadorJFchart = new VisualizacionJfreechart(tituloVentanaVisor);
        visualizadorJFchart.inicializacionJFreeChart(tituloLocalGrafico, tituloEjeX, tituloEjeY, orientacionPlot, incluyeLeyenda, incluyeTooltips, false);
        visualizadorJFchart.setColorChartBackgroundPaint(colorChartBackgroundPaint);
        visualizadorJFchart.setColorChartPlotBackgroundPaint(colorChartPlotBackgroundPaint);
        visualizadorJFchart.setColorChartPlotDomainGridlinePaint(colorChartPlotDomainGridlinePaint);
        visualizadorJFchart.setColorChartPlotRangeGridlinePaint(colorChartPlotRangeGridlinePaint);

    }

    ;
    @Override
    public void crearVisorGraficasLlegadaYasignacionVictimas(int numeroRobotsSimulacion, int numeroVictimasDiferentesSimulacion, int intervaloSecuencia, String identificadorEquipo) throws Exception {
        String tituloVentanaVisor = "Simulacion: " + numeroRobotsSimulacion + " R; " + numeroVictimasDiferentesSimulacion + " Vic; " + intervaloSecuencia + " mseg ; " + " tipo simulacion->" + identificadorEquipo;
        String tituloLocalGrafico = "Victim's Notification and Assignment to Team members ";
        String tituloEjeX = "Number of Victim's Notifications";
        String tituloEjeY = "Time in miliseconds";
        crearEInicializarVisorGraficaEstadisticas(tituloVentanaVisor, tituloLocalGrafico, tituloEjeX, tituloEjeY);
        mostrarVisorGraficaEstadisticas();
    }

    @Override
    public void crearVisorGraficasTiempoAsignacionVictimas(int numeroRobotsSimulacion, int numeroVictimasDiferentesSimulacion, int intervaloSecuencia, String identificadorEquipo) throws Exception {
        String tituloVentanaVisor = "Simulacion: " + numeroRobotsSimulacion + " R; " + numeroVictimasDiferentesSimulacion + " Vic; " + intervaloSecuencia + " mseg ; " + " tipo simulacion->" + identificadorEquipo;
        String tituloLocalGrafico = "Elapsed Time to Assign a New Victim";
        String tituloEjeX = "Number of Victim's Notifications";
        String tituloEjeY = "Time in miliseconds";
        crearEInicializarVisorGraficaEstadisticas(tituloVentanaVisor, tituloLocalGrafico, tituloEjeX, tituloEjeY);
        mostrarVisorGraficaEstadisticas();
    }

    @Override
    public void visualizarLlegadaYasignacionVictimas(ArrayList<PuntoEstadistica> llegada, ArrayList<PuntoEstadistica> asignacion) throws Exception {
        String tituloSerieLlegadaVictimas = "Notification Time";
        int indexSerieLlegadaVictimas = 1;
        String tituloSerieasignacionVictimas = "Assignment Time";
        int indexSerieasignacionVictimas = 2;
        aniadirSerieAVisorGraficaEstadisticas(tituloSerieLlegadaVictimas, indexSerieLlegadaVictimas, llegada);
        aniadirSerieAVisorGraficaEstadisticas(tituloSerieasignacionVictimas, indexSerieasignacionVictimas, asignacion);
    }

    @Override
    public void visualizarTiempoAsignacionVictimas(ArrayList<PuntoEstadistica> elapsed) throws Exception {
        String tituloSerieElapasedVictimas = "Elapsed Time";
        int indexSerieElapasedVictimas = 1;
        aniadirSerieAVisorGraficaEstadisticas(tituloSerieElapasedVictimas, indexSerieElapasedVictimas, elapsed);

    }

    @Override
    public void mostrarVisorGraficaEstadisticas() throws Exception {
        visualizadorJFchart.showJFreeChart(coordX, coordY);
        coordX = 10 * coordX; // coordY= 5*coordY;
    }
	
    @Override
    public void aniadirSerieAVisorGraficaEstadisticas(String tituloSerie, int indexSerie,
            ArrayList<PuntoEstadistica> puntosEstadistica) throws Exception {

        XYSeries serie;

        serie = new XYSeries(tituloSerie);

        for (int i = 0; i < puntosEstadistica.size(); i++) {

            serie.add(puntosEstadistica.get(i).getX(), puntosEstadistica.get(i).getY());
        }

        //serie.add(1.0, 1.5);

        visualizadorJFchart.addSerie(indexSerie, Color.green, serie);

    }

    //End methods that implement VisualizadorEstadisticas resource use interface


//#start_nodeterminaMethod:terminaMethod <--terminaMethod-- DO NOT REMOVE THIS        

    @Override
    public void termina() {
        trazas.aceptaNuevaTraza(new InfoTraza(this.id, "Terminando recurso" + this.id + " ....", InfoTraza.NivelTraza.debug));

        //Si es un recurso de visualizacion es necesaria una llamar a dispose de la ventana de visualizacion. Algo parecido a lo siguiente	
        //this.jvariableLocalReferenciaVisualizador.dispose(); //Destruye los componentes utilizados por este JFrame y devuelve la memoria utilizada al Sistema Operativo 	 

        super.termina();
    }

    //Fragmento de codigo para mostrar por la ventana de trazas de este recurso un mensaje	
    //trazas.aceptaNuevaTraza(new InfoTraza(this.idRecurso,"Mensaje mostrado en la ventana de trazas del recurso ....",InfoTraza.NivelTraza.debug));
    @Override
    public void mostrarEscenarioSimulador(String rutaEscenario) {
        //   throw new UnsupportedOperationException("Not supported yet.");
        // verificar que el agente a reportar esta definido , si no lo esta los eventos no se envian a nadie
        if (visorEscenarios == null) {
            try {
                visorEscenarios = new VisorEscenariosRosace (rutaEscenario);
            } catch (Exception ex) {
                Logger.getLogger(ClaseGeneradoraRecursoVisualizadorEntornosSimulacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        visorEscenarios.setVisible(true);
//        else {
//            trazas.trazar(this.id, "El identificador del agente controlador no esta definido. El agente controlador debe definirlo"
//                    + "o definir el identificador del agente en esta clase", InfoTraza.NivelTraza.error);
//        }
    }

    @Override
    public void setIdentAgenteAReportar(String identAgenteAReportar) {
        super.setIdentAgenteAReportar(identAgenteAReportar);
        notifEvt.setIdentAgenteAReportar(identAgenteAReportar);
    }

    @Override
    public void mostrarEscenario() throws Exception {
         if (visorEscenarios == null) {
            try {
                visorEscenarios = new VisorEscenariosRosace ();
            } catch (Exception ex) {
                Logger.getLogger(ClaseGeneradoraRecursoVisualizadorEntornosSimulacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        visorEscenarios.setVisible(true);
    }

    @Override
    public void mostrarResultadosFinSimulacion() throws Exception {
        String directorioTrabajo = System.getProperty("user.dir");  //Obtener directorio de trabajo 
        String nombreFicheroAsignVictim = "asigVictimasObjetos";
        String directorioPersistencia = VocabularioRosace.IdentDirectorioPersistenciaSimulacion + "/";
        String identFicheroInfoAsigVictimasObj = directorioPersistencia + nombreFicheroAsignVictim + ".tmp";
        //  String identFicheroInfoAsigVictimasXML = directorioPersistencia + nombreFicheroAsignVictim + ".xml";
        String identFicheroInfoAsigVictimasXML = directorioPersistencia + VocabularioRosace.NombreFicheroSerieInfoAsignacionVictimas + "<TimeTag>.xml";
        String identFicheroSerieAsigVictimasXML = directorioPersistencia + VocabularioRosace.NombreFicheroSerieAsignacion + "<TimeTag>.xml";
        String identFicheroLlegyAsigVictimasXML = directorioPersistencia + VocabularioRosace.NombreFicheroSerieLlegadaYasignacion + "<TimeTag>.xml";
        String msg = "FIN DE LA SIMULACION !!!.\n";
        msg = msg + "Se ha completado la captura de todas las estadisticas para la simulacion actual.\n";
        msg = msg + "Los ficheros de estadisticas se encuentran en el directorio " + directorioTrabajo + "/" + directorioPersistencia + "\n";
        msg = msg + "Los ficheros de estadisticas son los siguientes:\n";
        msg = msg + directorioTrabajo + "/" + identFicheroInfoAsigVictimasXML + "\n";
        msg = msg + directorioTrabajo + "/" + identFicheroSerieAsigVictimasXML + "\n";
        msg = msg + directorioTrabajo + "/" + identFicheroLlegyAsigVictimasXML + "\n";
        //      msg = msg + directorioTrabajo + "/" + ConstantesRutasEstadisticas.rutaficheroXMLEstadisticasLlegadaYAsignacionVictimas + "\n";
        //      msg = msg + directorioTrabajo + "/estadisticas/" + "EstIntLlegadaYAsignacionVictims" + "FECHA.xml" + "\n";
        JOptionPane.showMessageDialog(null, msg);
    }
    @Override
    public synchronized void mostrarPosicionRobot(String identRobot, int coordX, int coordY)throws Exception{
        visorEscenarios.setVisible(true);
        visorEscenarios.cambiarPosicionRobot(identRobot, coordX, coordY);
    }
//    @Override
//    public synchronized void mostrarPosicionActualRobot(String identRobot)throws Exception{
//        visorEscenarios.setVisible(true);
//        
//        visorEscenarios.cambiarPosicionRobot(identRobot, coordX, coordY);
//    }
    @Override
    public synchronized void mostrarVictimaRescatada(String identVictima)throws Exception{
        visorEscenarios.setVisible(true);
        visorEscenarios.cambiarIconoVictimaARescatada(identVictima);
    }
    @Override
     public  void inicializarDestinoRobot(String idRobot,Coordinate coordInicial,Coordinate coordDestino, float velocidadInicial){
//        if (idRobot != null )  this.getInstanciaHebraMvto(idRobot).inicializarDestino(idRobot, coordInicial, coordDestino, velocidadInicial);
    } 
    @Override
    public synchronized void mostrarMovimientoAdestino(String idRobot,String identDest,Coordinate coordDestino, float velocidadCrucero) {
           if (idRobot != null ){
               this.visorEscenarios.setVisible(true);
               this.visorEscenarios.cambiarPosicionRobot(idRobot, coordX, coordX);
           }
    }
//        public synchronized void mostrarMovimientoAdestino(String idRobot,String identDest,Coordinate coordDestino, float velocidadCrucero) {
//            if (idRobot != null ){
//                this.visorEscenarios.setVisible(true);
//           HebraMovimiento hebraMov =  this.getInstanciaHebraMvto(idRobot);
//           hebraMov.finalizar();
//           hebraMov.inicializarDestino(identDest, hebraMov.getCoordRobot(), coordDestino, velocidadCrucero);
//           hebraMov.run();
//            }
//         //   identDestino = identDest;
//        }
////    private HebraMovimiento getInstanciaHebraMvto(String identRobot) {
////         
////         if(identRobot != null){
////             HebraMovimiento hebramov;
////             if (tablaHebrasMov == null ){
////                 tablaHebrasMov = new HashMap();
////                 hebramov = new HebraMovimiento (identRobot,notifEvt,visorEscenarios);
////                 tablaHebrasMov.put(identRobot, hebramov);
////                 return hebramov;
////             }else{
////                 hebramov = tablaHebrasMov.get(identRobot);
////                 if (hebramov == null){
////                      hebramov = new HebraMovimiento (identRobot,notifEvt,visorEscenarios);
////                      tablaHebrasMov.put(identRobot, hebramov);                  
////                 }
////                 
////                 trazas.trazar(identRobot, " se crea el monitor de movimiento del robot ", InfoTraza.NivelTraza.debug);
////                 return hebramov;
////             }
//         }
//         trazas.trazar(identAgenteAReportar, " el identificador del monitor de movimiento del robot debe ser distinto de null ", InfoTraza.NivelTraza.error);
//         return null;
//         
//     }
    @Override
    public void mostrarIdentsEquipoRobots(ArrayList identList){
        this.ventanaControlCenterGUI.visualizarIdentsEquipoRobot(identList);
    }
}
