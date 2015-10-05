/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Rosace.tareasComunes;

import icaro.aplicaciones.Rosace.informacion.Victim;
import icaro.aplicaciones.Rosace.informacion.VictimsToRescue;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaAsincrona;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

/**
 *
 * @author FGarijo
 */
public class MoverseAsalvarVictima extends TareaSincrona {
    // private Victim victim;
int velocidadCruceroPordefecto = 5;// metros por segundo
    @Override
    public void ejecutar(Object... params) {
    
            MisObjetivos misObjs = (MisObjetivos) params[0];
            Focus focoActual = (Focus) params[1];
            Victim victima = (Victim) params[2];
            InfoCompMovimiento infoComMov = (InfoCompMovimiento) params[3];
            VictimsToRescue victimasArescatar = (VictimsToRescue) params[4];
            String identAgente =this.getIdentAgente();
            String identTarea= this.getIdentTarea();
            Objetivo objConseguido = focoActual.getFoco();
            if (objConseguido!= null){
                objConseguido.setPriority(-1);
                misObjs.cambiarPrioridad(objConseguido);
            }
            Objetivo nuevoObj = misObjs.getobjetivoMasPrioritario();
            if (nuevoObj.getPriority()>0 ){
//            if (!objActual.getobjectReferenceId().equals(victima.getName()))// no se hace nada y se indica un error pq el objetivo debe ser el que esta como mas prioritario  
//            {
//                trazas.trazar(this.getIdentAgente(), "Se ejecuta la tarea " + this.getIdentTarea()
//                        + " El ident de la victima debe coindidir con el del objetivo mas prioritario :  " + victima + "\n", InfoTraza.NivelTraza.error);
//            } else {
//                 Objetivo    nuevoObj = misObjs.getobjetivoMasPrioritario();
//                 if (nuevoObj != null) {      
                victima = victimasArescatar.getVictimToRescue(nuevoObj.getobjectReferenceId());
                ItfUsoMovimientoCtrl itfcompMov = (ItfUsoMovimientoCtrl) infoComMov.getitfAccesoComponente();
                itfcompMov.moverAdestino(nuevoObj.getobjectReferenceId(), victima.getCoordinateVictim(), velocidadCruceroPordefecto); // se pondra la verlocidad por defecto 
                infoComMov.setitfAccesoComponente(itfcompMov);
// se elimina el objetivo y se obtiene el siguiente en la cola
//                this.getEnvioHechos().eliminarHechoWithoutFireRules(objActual);
//                 }
                    //          Se cambia el foco a este objetivo
                    nuevoObj.setSolving();
//                    focoActual.setFoco(nuevoObj);
                    this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
                    this.getEnvioHechos().actualizarHechoWithoutFireRules(nuevoObj);
                    
//                    this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
                    
                    // se da orden de iniciar el movimiento hacia la nueva victima
                    trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Se ejecuta la tarea " + identTarea
                            + " Se inicia el movimiento  para salvar a la victima :  " + victima + "\n"+
                            "Se cambia la prioridad a -1 al obj  " + objConseguido
                            + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n");
//                    trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se elimina el objetivo  " + objActual
//                            + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n");
                    System.out.println("\n" + identAgente + "Se ejecuta la tarea " + getIdentTarea() + " Se inicia el movimiento  para salvar a la victima :  " + victima + "\n\n");
                } // no hay objetivos para salvar victimas el foco se pone a null
            else nuevoObj = null;    
                focoActual.setFoco(nuevoObj);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
                this.getEnvioHechos().actualizarHecho(focoActual);
        }
}
