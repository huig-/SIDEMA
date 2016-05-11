package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class InformarNeutralizadorLibre extends InformacionAgente implements Serializable {

	public InformarNeutralizadorLibre( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgInformarNeutralizadorLibre);
 }
    public InformarNeutralizadorLibre(String identAgteEmisor) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarNeutralizadorLibre;
        justificacion = null;

 }
    public InformarNeutralizadorLibre(String identAgteEmisor, Object justificat) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarNeutralizadorLibre;
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
