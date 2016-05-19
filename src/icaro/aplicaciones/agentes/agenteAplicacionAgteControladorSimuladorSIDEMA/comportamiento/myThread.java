package icaro.aplicaciones.agentes.agenteAplicacionAgteControladorSimuladorSIDEMA.comportamiento;

import java.util.ArrayList;

import icaro.aplicaciones.SIDEMA.informacion.OrdenComenzarSimulacion;
import icaro.infraestructura.entidadesBasicas.comunicacion.ComunicacionAgentes;

public class myThread extends Thread{
	 int nMM; // numeroMaximoDeMensajes a  enviar										
	 int interv;
	 String centroControl;
	 ComunicacionAgentes comunicator;
	 ArrayList<String> exploradores;
	 ArrayList<String> neutralizadores;
	 myThread(int nMM, int interv, String cc, ArrayList<String> exploradores, ArrayList<String> neutralizadores, ComunicacionAgentes com){
		 this.nMM = nMM;
		 this.interv = interv;
		 this.centroControl = cc;
		 this.comunicator = com;
		 this.exploradores = exploradores;
		 this.neutralizadores = neutralizadores;
	 }
	 
    public void run() {
    	boolean stop = false;
        while ((stop == false)) {
           try {
        	   ArrayList<ArrayList<String>> v = new ArrayList<ArrayList<String>>();
        	   v.add(this.exploradores);
        	   v.add(this.neutralizadores);
        	   OrdenComenzarSimulacion ccOrder = new OrdenComenzarSimulacion("ReactivoRosace",v);
                comunicator.enviarInfoAotroAgente(ccOrder, centroControl);
                stop = true;
                
                for(String neut : this.neutralizadores){
                	ccOrder = new OrdenComenzarSimulacion("ReactivoRosace");
                	comunicator.enviarInfoAotroAgente(ccOrder, neut);
                }
                
                for(String expl : this.exploradores){
                	ccOrder = new OrdenComenzarSimulacion("ReactivoRosace");
                	comunicator.enviarInfoAotroAgente(ccOrder, expl);
                }
            }
        catch (Exception e) {
            e.printStackTrace();
        }
            try {
                this.sleep(interv);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }// fin del while

        // Se han enviado todas las victimas
        // Cerrar el fichero de estadistica en el fichero de llegada de victimas

    }
}
