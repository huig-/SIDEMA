package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class InformarPosicionActual extends InformacionAgente implements Serializable {

	public InformarPosicionActual( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgInformarPosicionActual);
 }
    public InformarPosicionActual(String identAgteEmisor) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarPosicionActual;
        justificacion = null;

 }
    public InformarPosicionActual(String identAgteEmisor, Object justificat) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarPosicionActual;
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
