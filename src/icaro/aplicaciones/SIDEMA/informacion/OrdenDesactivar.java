package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class OrdenDesactivar extends OrdenAgente implements Serializable {

	public OrdenDesactivar( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgPeticionDesactivar);
 }
    public OrdenDesactivar(String identCCEmisor) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionDesactivar;
        justificacion = null;

 }
    public OrdenDesactivar(String identCCEmisor, Object justificat) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionDesactivar;
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
