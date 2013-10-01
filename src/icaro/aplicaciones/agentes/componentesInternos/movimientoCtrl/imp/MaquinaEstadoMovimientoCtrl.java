/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornosSimulacion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Informe;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza.NivelTraza;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author FGarijo
 */
public class MaquinaEstadoMovimientoCtrl {
    private static EstadoAbstractoMovRobot instance;
    private String identEstadoActual;
    private String identComponente;
    public static  enum EstadoMovimientoRobot {Indefinido,RobotParado, RobotEnMovimiento, RobotBloqueado,RobotavanceImposible,enDestino,  error}
    //Nombres de las clases que implementan estados del recurso interno
    public static  enum EvalEnergiaRobot {sinEnergia,energiaSuficiente,EnergiaJusta, EnergiaInsuficiente }
    public EstadoAbstractoMovRobot estadoActual;
    public RobotParado estadoRobotParado;
    public String identAgente;
//    public RobotEnMovimiento estadoMovimiento;
    public ItfUsoRecursoTrazas trazas;
    private  Map<EstadoMovimientoRobot, EstadoAbstractoMovRobot> estadosCreados;
    public Coordinate robotposicionActual;
    public Coordinate destinoCoord;
    public double distanciaDestino ;
    protected Integer velocidadCrucero;
    public ItfProcesadorObjetivos itfProcObjetivos;
    protected HebraMonitorizacionLlegada monitorizacionLlegadaDestino;
    ItfUsoRecursoVisualizadorEntornosSimulacion itfUsoRecVisEntornosSimul;
    
    public  MaquinaEstadoMovimientoCtrl (){
        estadosCreados = new EnumMap<EstadoMovimientoRobot, EstadoAbstractoMovRobot>(EstadoMovimientoRobot.class) ;
        
    }
    
    public EstadoAbstractoMovRobot getEstadoActual (){
        return  estadoActual ;
    }
    public void SetComponentId (String idComp){
        identComponente = idComp;
    }
     public void SetItfUsoRecursoVisualizadorEntornosSimulacion (ItfUsoRecursoVisualizadorEntornosSimulacion itfVisualEntSim){
        itfUsoRecVisEntornosSimul = itfVisualEntSim;
    }
    public void SetInfoContexto (ItfProcesadorObjetivos itfProcObj){
        identAgente = itfProcObj.getAgentId();
        itfProcObjetivos = itfProcObj;
   //     identComponente = identAgente+"."+this.getClass().getSimpleName();
        if (identComponente ==null) identComponente = identComponente = identAgente+"."+this.getClass().getSimpleName();
 //       estadoRobotParado =new RobotParado(agenteId);
 //      estadoActual= estadoRobotParado;
        trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
    }
     public synchronized EstadoAbstractoMovRobot  cambiarEstado (EstadoMovimientoRobot nuevoEstadoId){
      if (!nuevoEstadoId.name().equals(identEstadoActual)) {
        trazas.trazar(identComponente, " se cambia el estado: "+ identEstadoActual+  " al estado : " + nuevoEstadoId , NivelTraza.debug);
        estadoActual = estadosCreados.get(nuevoEstadoId);
        if ( estadoActual != null  ) {}
         else estadoActual =  crearInstanciaEstado2(nuevoEstadoId);       
    }
      return estadoActual; 
    }
     		               	
    private  EstadoAbstractoMovRobot crearInstanciaEstado1(EstadoMovimientoRobot estadoId) {
        EstadoAbstractoMovRobot objRobotEstado;
        try {
            String identClase = this.getClass().getSimpleName();
            String rutaClase = this.getClass().getName().replace(identClase,estadoId.name() );
            Class claseRobotEstado = Class.forName(rutaClase);
            try {
                objRobotEstado = (EstadoAbstractoMovRobot) claseRobotEstado.newInstance();
            } catch (Exception ex) {
                Logger.getLogger(MaquinaEstadoMovimientoCtrl.class.getName()).log(Level.SEVERE, null, ex);
                 return null;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaquinaEstadoMovimientoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
              
        return objRobotEstado;
    }
     private EstadoAbstractoMovRobot crearInstanciaEstado2(EstadoMovimientoRobot estadoId) {
         
         if(estadoId.equals(EstadoMovimientoRobot.RobotBloqueado))estadoActual = new RobotBloqueado(this);
         else if(estadoId.equals(EstadoMovimientoRobot.RobotParado))estadoActual = new RobotParado(this);
            else estadoActual = new RobotEnMovimiento(this);
         estadoActual.Inicializar(itfProcObjetivos, itfUsoRecVisEntornosSimul);
         identEstadoActual = estadoId.name();
         estadosCreados.put(estadoId,estadoActual);
         trazas.trazar(identComponente, " se crea el estado: "+ identEstadoActual+  " y se pone la maquina de estados  en este estado  " , NivelTraza.debug);
         
         return estadoActual;
         
     }

    public  void inicializarInfoMovimiento(icaro.aplicaciones.Rosace.informacion.Coordinate coordInicial, float velocidadInicial){
//        robotposicionActual =coordInicial;
//        velocidadCrucero = velocidadInicial;
        this.estadoActual.inicializarInfoMovimiento(coordInicial,velocidadInicial);
        this.robotposicionActual = coordInicial;
    } 
 
        public synchronized void moverAdestino(String identDest,Coordinate coordDestino, float velocidadCrucero) {
            
//            this.estadoActual.identDestino = identDest;
            
            this.destinoCoord = coordDestino;
            trazas.trazar(identAgente, "Se recibe una  orden de mover a destino."+ identDest + " El robot esta en el estado :"+ identEstadoActual
                + " CoordActuales =  "+this.robotposicionActual.toString() + " CoordDestino =  " +this.destinoCoord.toString(), InfoTraza.NivelTraza.debug);
           estadoActual.moverAdestino(identDest,coordDestino, velocidadCrucero);
        }
  
        public void cambiaVelocidad( float nuevaVelocidadCrucero) {
            estadoActual.cambiaVelocidad(nuevaVelocidadCrucero);
        }
  
        public synchronized void cambiaDestino(String identDest,icaro.aplicaciones.Rosace.informacion.Coordinate coordDestino) {
            estadoActual.cambiaDestino(identDest,coordDestino);
            this.destinoCoord = coordDestino;
            trazas.trazar(identAgente, "Se recibe una  orden de cambiar  a destino. El robot esta en el estado :"+ identEstadoActual
                + " CoordActuales =  "+this.robotposicionActual.toString() + " CoordDestino =  " +this.destinoCoord.toString(), InfoTraza.NivelTraza.debug);
//            this.estadoActual.identDestino = identDest;
         //   identDestino = identDest;
        }   

        public synchronized void parar(){
          estadoActual.parar(); 
        }

        public void continuar(){
            estadoActual.continuar();
        }

    public synchronized void estamosEnDestino(String identDest){
        // se informa al control de que estamos en el destino. Se cambia el estado a parar
//        estadoActual = this.cambiarEstado(MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot.RobotParado);
//        this.estadoActual.identDestino = identDest;
        Informe informeLlegada = new Informe (identComponente,identDest, VocabularioRosace.MsgeLlegadaDestino);
        this.itfProcObjetivos.insertarHecho(informeLlegada);
        trazas.trazar(identAgente, "Se informa de llegada al  destino: " +informeLlegada + " El robot esta en el estado :"+ identEstadoActual
                + " CoordActuales =  "+this.robotposicionActual.toString() , InfoTraza.NivelTraza.debug);
        estadoActual = this.cambiarEstado(EstadoMovimientoRobot.RobotParado);
        this.estadoActual.identDestino = identDest;
        
  
    }

    public synchronized void imposibleAvanzarADestino(){
       estadoActual = this.cambiarEstado(EstadoMovimientoRobot.RobotBloqueado);
    }
    

    public synchronized Coordinate getCoordenadasActuales() {
     return this.robotposicionActual;
    }

    public synchronized void setCoordenadasActuales(Coordinate nuevasCoordenadas) {
        if (nuevasCoordenadas != null){
            this.robotposicionActual = nuevasCoordenadas;
//       this.estadoActual.  robotposicionActual.x = nuevasCoordenadas.x;
//        this.estadoActual. robotposicionActual.y = nuevasCoordenadas.y;
//        this.estadoActual. robotposicionActual.z = nuevasCoordenadas.z;
        }
    }
//    public  EstadoAbstractoMovRobot getEstadoActual (){
//        return this.estadoActual;
//    }
     public void  setEstadoActual (EstadoAbstractoMovRobot estadoRobot){
         this.estadoActual = estadoRobot;
    }
}
