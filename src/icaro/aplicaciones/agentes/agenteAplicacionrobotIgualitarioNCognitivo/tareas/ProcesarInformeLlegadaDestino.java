/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.tareas;
import icaro.aplicaciones.Rosace.informacion.EvaluacionAgente;
import icaro.aplicaciones.Rosace.informacion.VictimsToRescue;
import icaro.aplicaciones.agentes.agenteAplicacionrobotIgualitarioNCognitivo.informacion.InfoParaDecidirQuienVa;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Informe;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

/**
 *
 * @author Francisco J Garijo
 */
public class ProcesarInformeLlegadaDestino extends TareaSincrona{
        
  @Override
  public void ejecutar(Object... params) {
		try {
//Suponemos que cuando llega al destino se salva la victima
 // habría que actualizar las victimas, los objetivos, el estado del movimiento  y cambiar el foco                   
             MisObjetivos misObjs = (MisObjetivos)params[0];
             VictimsToRescue victims = (VictimsToRescue) params[1];
             Focus focoActual = (Focus) params[2];
             InfoCompMovimiento infoCompMov = (InfoCompMovimiento) params[3];
             Informe informeRecibido = (Informe) params[4];
             trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Se Ejecuta la Tarea :"+ this.identTarea , InfoTraza.NivelTraza.info));
             trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Se Procesa el informe   recibido por el agente :"+ informeRecibido.referenciaContexto +" Cuyo contenido:"+informeRecibido.contenidoInforme  , InfoTraza.NivelTraza.debug));
            // se actualiza el coste de la  vicitima salvada
             String victimaRescatadaId = informeRecibido.getReferenciaContexto();
             victims.addEstimatedCostVictim2Rescue(victimaRescatadaId, 0);
             this.getEnvioHechos().eliminarHecho(informeRecibido);
             // Se actualizan los objetivos, se da por conseguido el objetivo salvar a la victima
             // se supone que este objetivo era el mas prioritario, si no lo era hay un problema
              Objetivo objetivoConseguido = misObjs.getobjetivoMasPrioritario();
              if (victimaRescatadaId.equals(objetivoConseguido.getobjectReferenceId())){
                  objetivoConseguido.setSolved();
                  objetivoConseguido.setPriority(-1);
                  misObjs.cambiarPrioridad(objetivoConseguido);
                  // Se actualiza el componente movimiento
                  String estadoComponente = infoCompMov.itfAccesoComponente.getIdentEstadoMovRobot();
                  infoCompMov.setestadoComponente(estadoComponente);
             // Verificamos el foco si tiene un objetivo solved ponemos el foco en un objetivo pendiente de mis objetivos
                  if(focoActual.getFoco().getState()==Objetivo.SOLVED) focoActual.setFoco(objetivoConseguido);
              // Se envian los cambios al motor   
                  
                  this.getEnvioHechos().actualizarHechoWithoutFireRules(victims);
                  this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
                  this.getEnvioHechos().actualizarHechoWithoutFireRules(infoCompMov);
                  this.getEnvioHechos().actualizarHechoWithoutFireRules(objetivoConseguido);
                  this.getEnvioHechos().actualizarHecho(focoActual);
                  trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Foco actual  :"+ focoActual, InfoTraza.NivelTraza.debug));
              }else
                   trazas.aceptaNuevaTraza(new InfoTraza(this.identAgente, "Se Ejecuta la Tarea :"+ this.identTarea +
                           "El identificador de la victima  :"+ victimaRescatadaId + " y el del ultimo objetivo : "+objetivoConseguido.getobjectReferenceId()+"no coinciden " , InfoTraza.NivelTraza.error));
        } catch (Exception e) {
			   e.printStackTrace();
        }
}


}
