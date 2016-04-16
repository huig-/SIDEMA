package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

import icaro.aplicaciones.Rosace.informacion.OrdenAgente;

public class OrdenExplorar extends OrdenAgente implements Serializable {

	public OrdenExplorar( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgPeticionExplorar);
 }
    public OrdenExplorar(String identCCEmisor) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionExplorar;
        justificacion = null;

 }
    public OrdenExplorar(String identCCEmisor, Object justificat) {
        identEmisor= identCCEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgPeticionExplorar;
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
