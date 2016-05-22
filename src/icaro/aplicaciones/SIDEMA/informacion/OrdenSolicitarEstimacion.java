package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class OrdenSolicitarEstimacion extends OrdenAgente implements Serializable {

	public OrdenSolicitarEstimacion( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgPeticionEstimacionActual);
 }
    public OrdenSolicitarEstimacion(String identCCEmisor) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionEstimacionActual;
        justificacion = null;

 }
    public OrdenSolicitarEstimacion(String identCCEmisor, Object justificat) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionEstimacionActual;
        justificacion = justificat;

 }
 
  @Override
     public String toString(){
        if ( justificacion == null )
            return "Agente Emisor :"+identEmisor+ " MensajeOrden :+" + mensajeOrden+ "  Justificacion: null "+"\n ";
        else 
            return "Agente Emisor :"+identEmisor+ " MensajeOrden :+" + mensajeOrden+ "  Justificacion: "+justificacion.toString() +"\n ";
    }
}
