package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

public class InformarCandidatosAExplorar extends InformacionAgente implements Serializable{

	public InformarCandidatosAExplorar( ) {
        super.setMensajeOrden(VocabularioSIDEMA.MsgInformarCandidatos);
 }
    public InformarCandidatosAExplorar(String identAgteEmisor) {
        identEmisor = identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarCandidatos;
        justificacion = null;

 }
    public InformarCandidatosAExplorar(String identAgteEmisor, Object justificat) {
        identEmisor= identAgteEmisor;
        mensajeOrden = VocabularioSIDEMA.MsgInformarCandidatos;
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
