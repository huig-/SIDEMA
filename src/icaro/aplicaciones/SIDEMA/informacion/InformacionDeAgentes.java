package icaro.aplicaciones.SIDEMA.informacion;

public class InformacionDeAgentes {
   // Mensajes validos en las propuestas : Ver vocabulario de la aplicacion
    
    public String identEmisor;
    public String mensajeOrden;
    //public Object justificacion;

  public InformacionDeAgentes( ) {
        
 }
    public InformacionDeAgentes(String identCCEmisor) {
        identEmisor= identCCEmisor;
        mensajeOrden =null;
        //justificacion = null;

 }
    public InformacionDeAgentes(String identCCEmisor, String msgOrden, Object justificat) {
        identEmisor= identCCEmisor;
        mensajeOrden = msgOrden;
        //justificacion = justificat;

 }
  public void   setMensajeOrden(String msgOrden){
      mensajeOrden =msgOrden;
 }
 public String   getMensajeOrden(){
      return mensajeOrden;
 }

 public String   getIdentEmisor(){
      return identEmisor;
 }
/*public void   setJustificacion(Object contJustificacion){
      justificacion =contJustificacion;
 }*/

/*  public Object   getJustificacion(){
      return justificacion;
 }*/
  @Override
     public String toString(){
        if ( mensajeOrden == null )
            return "Agente Emisor :"+identEmisor+ " MensajeOrden :+" + mensajeOrden+"\n" ;
        else 
            return "Agente Emisor :"+identEmisor+ " MensajeOrden :+" + mensajeOrden+"\n" ;
    }
}
