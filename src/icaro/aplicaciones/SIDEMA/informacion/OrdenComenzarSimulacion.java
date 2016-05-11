package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class OrdenComenzarSimulacion extends OrdenAgente implements Serializable {

	public OrdenComenzarSimulacion( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgPeticionComenzarSimulacion);
 }
    public OrdenComenzarSimulacion(String identCCEmisor) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionComenzarSimulacion;
        justificacion = null;

 }
    public OrdenComenzarSimulacion(String identCCEmisor, Object justificat) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionComenzarSimulacion;
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
