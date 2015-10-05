/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.tareas;

import icaro.aplicaciones.Rosace.informacion.Victim;
import icaro.aplicaciones.Rosace.informacion.VictimsToRescue;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.informacion.InfoParaDecidirQuienVa;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp.MaquinaEstadoMovimientoCtrl;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.imp.MaquinaEstadoMovimientoCtrl.EstadoMovimientoRobot;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author FGarijo
 * rule "Encolar el objetivo Ayudar nueva victima despues de obtener el acuerdo de todos"
***when
    victimaCC:Victim(idVict:name)
    misObjs : MisObjetivos()
    infoComMov :InfoCompMovimiento ()
    victims2R:VictimsToRescue(victims2Rescue!=null)
    obj1 :AyudarVictima(state == Objetivo.PENDING, victimId == idVict)   
    obj2: DecidirQuienVa (state == Objetivo.SOLVED,objectDecisionId == idVict )
    obj:ConfirmacionParaIrYo(state==Objetivo.SOLVED,objectConfirmationId==idVict)
    infoDecision: InfoParaDecidirQuienVa(idElementoDecision == idVict)
    focoActual:Focus(foco == obj)
 then
 */
public class EncolarObjetivoActualizarFocoIGN1 extends TareaSincrona {
private  enum EstadoMovimientoRobot {Indefinido,RobotParado, RobotEnMovimiento, RobotBloqueado,RobotavanceImposible,enDestino,  error}
    @Override
    public void ejecutar(Object... params) {
        
        //    ItfUsoRecursoEstadistica itfUsoRecursoEstadistica=null;
        int velocidadCruceroPordefecto = 1;// metros por segundo
        //Para recoger estadisticas del instante de envio de victimas desde el centro de control

        try {
//             trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
            MisObjetivos misObjs = (MisObjetivos) params[0];
            Objetivo obj1 = (Objetivo) params[1];// AyudarVictima .pending
            InfoParaDecidirQuienVa infoDecision = (InfoParaDecidirQuienVa) params[2];
            Focus focoActual = (Focus) params[3];
            InfoCompMovimiento infoComMov = (InfoCompMovimiento) params[4];
            Victim victima = (Victim) params[5];
            VictimsToRescue victimas =(VictimsToRescue) params[6];
            String nombreAgenteEmisor = this.getIdentAgente();

            //Para anotar en el fichero cual es mi coste
            int coste = 0;   //El coste se define como el MAYOR ENTERO - VALOR DE LA FUNCION DE EVALUACION
            //El que menor coste tiene es el que se hace cargo de la victima o dicho de otra manera
            //El que mayor funcionn de evaluacionn tiene es el que se hace cargo de la victima
            int miEvaluacion = infoDecision.getMi_eval();
            if (miEvaluacion != -1) {
                coste = Integer.MAX_VALUE - miEvaluacion;
            } else {
                coste = miEvaluacion;    //SI EL COSTE EL -1 INDICARIA QUE SE HA HECHO CARGO PERO QUE NO PUEDE IR (NO TIENE RECURSOS)
            }
            //ACTUALIZAR ESTADISTICAS
            //Inicializar y recuperar la referencia al recurso de estadisticas        	
            long tiempoActual = System.currentTimeMillis();
            String refVictima = obj1.getobjectReferenceId();
            //      	 itfUsoRecursoEstadistica.escribeEstadisticaFicheroTextoPlanoTRealAsignacionVictimasRobots(tiempoActual, refVictima, nombreAgenteEmisor, coste);
            ////////////////////////////////////////////////////////
            //ENVIAR INFORMACION AL AGENTE CONTROLADOR DEL SIMULADOR           
            Object[] valoresParametrosAccion = new Object[4];
            valoresParametrosAccion[0] = tiempoActual;
            valoresParametrosAccion[1] = refVictima;
            valoresParametrosAccion[2] = nombreAgenteEmisor;
            valoresParametrosAccion[3] = miEvaluacion;
            InfoContEvtMsgAgteReactivo msg = new InfoContEvtMsgAgteReactivo("victimaAsignadaARobot", valoresParametrosAccion);
            this.getComunicator().enviarInfoAotroAgente(msg, VocabularioRosace.IdentAgteControladorSimulador);
            // verificamos que no se esta ayudando a esa victima. Comprobamos que el ident no esta en ninguno de los objetivos 
            // Se compara con los objetivos pendientes
            // Miramos si la cola de objetivos esta o no vacia 
            Objetivo nuevoObj = misObjs.getobjetivoMasPrioritario();
             ItfUsoMovimientoCtrl itfcompMov = (ItfUsoMovimientoCtrl) infoComMov.getitfAccesoComponente();
             while( nuevoObj != null&& nuevoObj.getPriority()>0 ){
                 if (nuevoObj.getState()== Objetivo.SOLVED){  
                                    nuevoObj.setPriority(-1);
                                    misObjs.cambiarPrioridad(obj1);
                                    nuevoObj=misObjs.getobjetivoMasPrioritario();
                             }
             }
             if ( nuevoObj == null||nuevoObj.getPriority()<0){// se pone el objetivo actual a solving y se da orden para que se empiece a mover
                obj1.setSolving();
                misObjs.addObjetivo(obj1);
//                focoActual.setFoco(obj1);
                itfcompMov.moverAdestino(obj1.getobjectReferenceId(), victima.getCoordinateVictim(), velocidadCruceroPordefecto);
                infoComMov.setitfAccesoComponente(itfcompMov);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(obj1);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
                trazas.aceptaNuevaTrazaEjecReglas(identAgente, "No hay objetivos anteriores Objetivo considerado : "+ obj1.toString()+ "Se ejecuta la tarea : " + identTarea + " Se actualiza el  foco al objetivo:  " + obj1 + "\n");
            
            }else{
                
        // comparamos prioridades
                if(obj1.getPriority()<= nuevoObj.getPriority()){ // tiene menor prioridad  encolamos el objetivo
                                    misObjs.addObjetivo(obj1);
                                    this.getEnvioHechos().actualizarHecho(misObjs);
                }
                else {// El objetivo actual tiene mayor prioridad
                    // se  mira si el robot se esta moviendo a rescatar la victima                      
                    victima = victimas.getVictimToRescue(nuevoObj.getobjectReferenceId());
                    if (infoComMov.getestadoComponente().equalsIgnoreCase(EstadoMovimientoRobot.RobotEnMovimiento.name()))   
                       itfcompMov.cambiaDestino(nuevoObj.getobjectReferenceId(), victima.getCoordinateVictim());
                       
                     else {}// no esta en movimiento 
                       obj1.setPending(); // interrumpimos la ejecución y la sustituimos por el nuevo objetivo
                       this.getEnvioHechos().actualizarHechoWithoutFireRules(obj1);
                       nuevoObj.setSolving();
                       itfcompMov.moverAdestino(nuevoObj.getobjectReferenceId(), victima.getCoordinateVictim(),velocidadCruceroPordefecto);
                       infoComMov.setitfAccesoComponente(itfcompMov);
//                       this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
                       focoActual.setFoco(nuevoObj);
                       trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Se pone a pending Objetivo1 : "+ obj1.toString()+ "Se ejecuta la tarea : " + identTarea + " Se actualiza el  foco al objetivo:  " + nuevoObj + "\n");
                }
            }

            
            System.out.println("\n" + identAgente + "Se ejecuta la tarea " + identTarea + " Se actualiza el  objetivo:  " + obj1 + "\n\n");
//            }
            if (infoDecision != null) {
                this.getEnvioHechos().eliminarHechoWithoutFireRules(infoDecision);
            }
//            this.getEnvioHechos().actualizarHecho(obj1);
           String estadoMovRobot= itfcompMov.getIdentEstadoMovRobot();
           infoComMov.setestadoComponente(estadoMovRobot);
            trazas.aceptaNuevaTrazaEjecReglas(identAgente,"Posicion Robot : "+itfcompMov.getCoordenadasActuales()+
                    "estado del Movimiento del Robot: "+estadoMovRobot+" Se da orden al comp Movimiento  para salvar a la victima :  "
                    + victima + " El foco actual es : "+ focoActual+ "\n");
            this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
//            this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
            focoActual.setfaseProcesoConsecObjetivos("DecisionAsignacionVictima");
            this.getEnvioHechos().actualizarHecho(focoActual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

