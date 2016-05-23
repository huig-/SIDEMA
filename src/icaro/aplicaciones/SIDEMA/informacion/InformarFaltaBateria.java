package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

public class InformarFaltaBateria extends InformacionAgente implements Serializable {

	public InformarFaltaBateria( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgInformarFaltaBateria);
 }
    public InformarFaltaBateria(String identAgteEmisor) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarFaltaBateria;
        justificacion = null;

 }
    public InformarFaltaBateria(String identAgteEmisor, Object justificat) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarFaltaBateria;
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
