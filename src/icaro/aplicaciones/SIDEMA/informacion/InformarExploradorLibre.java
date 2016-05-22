package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

public class InformarExploradorLibre extends InformacionAgente implements Serializable{

	public InformarExploradorLibre( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgInformarExploradorLibre);
 }
    public InformarExploradorLibre(String identAgteEmisor) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarExploradorLibre;
        justificacion = null;

 }
    public InformarExploradorLibre(String identAgteEmisor, Object justificat) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarExploradorLibre;
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
