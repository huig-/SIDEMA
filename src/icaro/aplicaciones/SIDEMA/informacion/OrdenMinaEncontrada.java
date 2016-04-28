package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class OrdenMinaEncontrada extends InformacionDeAgentes implements Serializable {

	public OrdenMinaEncontrada( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgPeticionMinaEncontrada);
 }
    public OrdenMinaEncontrada(String identCCEmisor) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionMinaEncontrada;
        justificacion = null;

 }
    public OrdenMinaEncontrada(String identCCEmisor, Object justificat) {
        identEmisor= identCCEmisor;
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
