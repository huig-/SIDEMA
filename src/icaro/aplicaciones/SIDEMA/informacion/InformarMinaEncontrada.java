package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class InformarMinaEncontrada extends InformacionAgente implements Serializable {

	public InformarMinaEncontrada( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgPeticionMinaEncontrada);
 }
    public InformarMinaEncontrada(String identAgteEmisor) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionMinaEncontrada;
        justificacion = null;

 }
    public InformarMinaEncontrada(String identAgteEmisor, Object justificat) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionMinaEncontrada;
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
