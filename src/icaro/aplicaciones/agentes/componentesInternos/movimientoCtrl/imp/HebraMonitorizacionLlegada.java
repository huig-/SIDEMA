package icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp;


import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.ItfUsoRecursoVisualizadorEntornosSimulacion;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
* Clase interna que se encarga de generar eventos de monitorizacin cada cierto tiempo
*
* @author Carlos Delgado
*/
public class HebraMonitorizacionLlegada extends Thread {

    /**
	 * Milisegundos que esperar antes de lanzar otra monitorizacin
	 * @uml.property  name="milis"
	 */
    protected long milis;

    /**
	 * Indica cundo debe dejar de monitorizar
	 * @uml.property  name="finalizar"
	 */
    protected boolean finalizar = false;

    /**
	 * Agente reactivo al que se pasan los eventos de monitorizacin
	 * @uml.property  name="agente"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
//     protected ItfUsoMovimientoCtrl controladorMovimiento;
    public MaquinaEstadoMovimientoCtrl controladorMovimiento;

     /**
	 * Evento a producir
	 * @uml.property  name="evento"
	 */
     private String identDestino, identRobot;
     private   Coordinate coordActuales;
     private Coordinate coordDestino;
     private Coordinate coordIncremento;
     private double velocidadRobot; // en metros por segundo 
//     protected int intervaloEnvioInformacion = 1000; // por defecto en ms . Deberia ser configurable
     private double pendienteRecta;
     private boolean estamosEnDestino;
     private double espacioRecorrido ;
//     protected double distanciaDestino ;
    private boolean pendienteInfinita = false ;
    private boolean parar = false ;
    private boolean enDestino = false ;
    private float distanciaArecorrer ;
    private float b ; // punto corte recta con eje Y
    private int dirX =0, dirY=0,incrementoDistancia=0;
    private int intervaloEnvioInformesMs ;
    private int distanciaRecorridaEnIntervaloInformes ;
    private long tiempoParaAlcanzarDestino ;
    public ItfUsoRecursoVisualizadorEntornosSimulacion itfusoRecVisSimulador;
//    private int numeroPuntos = 20;
    /**
     * Constructor
     *
     * @param milis Milisegundos a esperar entre cada monitorizacion
     */
    public HebraMonitorizacionLlegada(String idRobot,MaquinaEstadoMovimientoCtrl contrMovimiento,  ItfUsoRecursoVisualizadorEntornosSimulacion itfRecVisSimulador) {
      super("HebraMonitorizacion del controlador de Movimiento");  
      controladorMovimiento =contrMovimiento;
      this.itfusoRecVisSimulador = itfRecVisSimulador;
      identRobot = idRobot;
    }
  public synchronized void inicializarDestino (String idDestino,Coordinate coordRobot,Coordinate coordDest, double velocidad ){    
//      this.finalizar= false;
      coordActuales =coordRobot ;
      coordDestino = coordDest;
      velocidadRobot = velocidad; // 
//      intervaloEnvioInformacion= intervEnvioInformacion; 
      espacioRecorrido = 0;
      identDestino = idDestino;
//      this. pendienteRecta = (float) ((coordDestino.y-coordActuales.y)/(coordDestino.x-coordActuales.x));
      this.setDaemon(true);
//      coordIncremento = this.calcularIncrementosCoordenadasAvelocidadConstante(intervaloEnvioInformacion);
 //     this.evento = notificacionAProducir;
      this.finalizar= false;
//       distanciaDestino = this.distanciaEuclidC1toC2(coordActuales, coordDestino);
       double incrX=(coordDestino.x-coordActuales.x);
       double incrY=(coordDestino.y-coordActuales.y);
       if (incrX>0)dirX=1 ;
               else dirX=-1;
       if (incrY>0)dirY=1 ;
               else dirY=-1;
       if (incrX==0){pendienteInfinita= true;
                     distanciaArecorrer = (float)incrY;
                    }
       else { pendienteRecta = (float) Math.abs(incrY/incrX);
        this.distanciaArecorrer =(float) Math.sqrt(incrX*incrX+incrY*incrY);
        this.b = (float) (coordActuales.y -pendienteRecta * coordActuales.x ) ;
        
       }
//        this.incrementoDistancia= (int)distanciaArecorrer/numeroPuntos;
        tiempoParaAlcanzarDestino = (long)(distanciaArecorrer/velocidadRobot); // en milisegundos
//       int intervaloEnvioInformesMs = (int)tiempoParaAlcanzarDestino/numeroPuntos;
//       intervaloEnvioInformesMs = 40;
//        distanciaRecorridaEnIntervaloInformes = (long)(1+velocidadRobot*intervaloEnvioInformesMs/50);
//       coordIncremento = this.calcularIncrementosCoordenadasAvelocidadConstante(intervaloEnvioInformacion);
        intervaloEnvioInformesMs = (int)velocidadRobot* 12;
        distanciaRecorridaEnIntervaloInformes = 1;
    }


    /**
     * Termina la monitorizacin
     */
    public synchronized void finalizar() {
	this.finalizar= true;
        
    }
    public synchronized void setCoordRobot(Coordinate robotCoord) {
	this.coordActuales= robotCoord;
    }
    public synchronized void setCoordDestino(Coordinate destCoord) {
	this.coordDestino= destCoord;
    }
    public synchronized void setVelocidadRobot(double velRobot) {
	this.velocidadRobot= velRobot;
    }

    @Override
    public void run() {
      
//       double espacioRecorridoEnIntervalo = velocidadRobot*intervaloEnvioInformacion;
       
      while (!this.finalizar && (!enDestino)) {

	  // Duerme lo especificado
	  try {
//	    Thread.sleep(intervaloEnvioInformesMs);
            Thread.sleep(intervaloEnvioInformesMs);
	  } catch (InterruptedException ex) {}

	  // Genera un nuevo evento de input
	//  if (!this.finalizar && !estamosEnDestino )
		try {
                        calcularNuevasCoordenadas (distanciaRecorridaEnIntervaloInformes);                      
//                        distanciaArecorrer = distanciaArecorrer-distanciaRecorridaEnIntervaloInformes;                     
                        
                        if (itfusoRecVisSimulador != null)
                        this.itfusoRecVisSimulador.mostrarPosicionRobot(identRobot, (int)coordActuales.x, (int)coordActuales.y);
                        this.controladorMovimiento.setCoordenadasActuales(coordActuales);
		//	if (estamosEnDestino) this.controladorMovimiento.estamosEnDestino();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      if (enDestino ){
          finalizar = true;
            try {
                Thread.sleep(tiempoParaAlcanzarDestino/2);
                this.controladorMovimiento.estamosEnDestino(identDestino);
            } catch (InterruptedException ex) {
                Logger.getLogger(HebraMonitorizacionLlegada.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
    }

private void calcularNuevasCoordenadas (long incrementoDistancia){
    // suponemos avance en linea recta 
    // formula aplicada x1 = xo + sqrt( espacioRecorrido**2 / (1 + pendienteRecta**2))
    //  y1 = y0 +(x1-x0)*pendienteRecta
    
//  double constIncrX,constIncrY;
        if (pendienteInfinita){
//            constIncrX = 0;
//            constIncrY= incrementoDistancia;
             this.coordActuales.y = coordActuales.y + incrementoDistancia*dirY;
        }
//            this.coordActuales.y = coordActuales.y+this.dirY*incrementoDistancia;
        else {
            // incremmento de x respecto a distancia recorrida 
            this.coordActuales.y = this.coordActuales.y + pendienteRecta*incrementoDistancia*dirY;
            this.coordActuales.x = coordActuales.x + incrementoDistancia*dirX;
            if ((coordActuales.x-coordDestino.x)*dirX>=0 &&(coordActuales.y-coordDestino.y)*dirY>=0)enDestino = true;
//          i
        }
//        distanciaArecorrer = distanciaArecorrer-incrementoDistancia;
}
//private Coordinate calcularIncrementosCoordenadasAvelocidadConstante (int tiempoDeMovimiento){
//     double espacioRecorridoEnIntervalo = velocidadRobot*tiempoDeMovimiento;
//     double incrementoCoordenasX =  Math.sqrt(Math.pow(espacioRecorridoEnIntervalo,2) /(1+Math.pow(pendienteRecta,2) ));
//    Coordinate coordIncremento = new Coordinate (incrementoCoordenasX,incrementoCoordenasX*pendienteRecta, 0);
//return coordIncremento;
//}
//public double distanciaEuclidC1toC2(Coordinate c1, Coordinate c2){
//    	
//    	System.out.println("c1->"+c1);
//    	System.out.println("c2->"+c2);
//    	
//        return Math.sqrt( Math.pow(c1.x - c2.x,2) + 
//     	 	   			  Math.pow(c1.y - c2.y,2) +
//     		   			  Math.pow(c1.z - c2.z,2) 
//                        );       		               	
//    }
}
