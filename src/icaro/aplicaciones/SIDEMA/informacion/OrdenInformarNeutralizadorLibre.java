package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class OrdenInformarNeutralizadorLibre extends OrdenAgente implements Serializable {

	public OrdenInformarNeutralizadorLibre( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgInformarNeutralizadorLibre);
 }
    public OrdenInformarNeutralizadorLibre(String identCCEmisor) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarNeutralizadorLibre;
        justificacion = null;

 }
    public OrdenInformarNeutralizadorLibre(String identCCEmisor, Object justificat) {
        identEmisor= identCCEmisor;
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
