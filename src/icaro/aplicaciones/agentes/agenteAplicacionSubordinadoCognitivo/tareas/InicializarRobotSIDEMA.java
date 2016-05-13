package icaro.aplicaciones.agentes.agenteAplicacionSubordinadoCognitivo.tareas;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import icaro.aplicaciones.Rosace.utils.AccesoPropiedadesGlobalesRosace;
import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.CentroControl;
import icaro.aplicaciones.SIDEMA.informacion.Explorador;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;
import icaro.aplicaciones.SIDEMA.informacion.Neutralizador;
import icaro.aplicaciones.SIDEMA.informacion.Robot;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class InicializarRobotSIDEMA extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		String miIdentAgte = this.getIdentAgente();
        //Lectura del fichero de robots. Aprovechamos para tener en memoria la configuracion de robots.
             
        String rutaFicheroRobotsTest = AccesoPropiedadesGlobalesRosace.getRutaFicheroRobotsTest();
        try {
        	File inputFile = new File(rutaFicheroRobotsTest);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("robot");
            boolean found = false;
            for (int i = 0; i < nList.getLength() && !found; i++) {
            	Node nNode = nList.item(i);
            	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            		Element eElement = (Element) nNode;
            		//String id = ((Node)eElement.getElementsByTagName("id").item(0)).getTextContent();
            		String id = ((Node)eElement.getElementsByTagName("id").item(0)).getTextContent();
            		if (id.equals(miIdentAgte)) {
            			found = true;
            			String type = eElement.getAttribute("type");
	            		int energy = Integer.parseInt(((Node)eElement.getElementsByTagName("energy").item(0)).getTextContent());
	            		Element celda = (Element)eElement.getElementsByTagName("celdaActual").item(0);
	            		int x = Integer.parseInt(((Node)celda.getElementsByTagName("x").item(0)).getTextContent());
	            		int y = Integer.parseInt(((Node)celda.getElementsByTagName("y").item(0)).getTextContent());
	            		String leader = ((Node)eElement.getElementsByTagName("nameCC").item(0)).getTextContent();
	            		Robot robot;
	            		if(type.equalsIgnoreCase("Explorador")){
	            	        Mapa m = (Mapa)params[0];
	            			int timeMov = Integer.parseInt(((Node)eElement.getElementsByTagName("movementTime").item(0)).getTextContent());
	            			int enerMov = Integer.parseInt(((Node)eElement.getElementsByTagName("movementEnergy").item(0)).getTextContent());
	            			int timeExp = Integer.parseInt(((Node)eElement.getElementsByTagName("explorationTime").item(0)).getTextContent());
	            			int enerExp = Integer.parseInt(((Node)eElement.getElementsByTagName("explorationEnergy").item(0)).getTextContent());
	            			robot = new Explorador(id,x,y, energy,leader,timeMov,enerMov,timeExp,enerExp);
	            	  		this.getEnvioHechos().insertarHecho(robot);
		            		System.out.println(robot.toString()); //cambiar por trazas?
	            
	            		}else if(type.equalsIgnoreCase("Neutralizador")){
	            	        Mapa m = (Mapa)params[0];
	            			int timeMov = Integer.parseInt(((Node)eElement.getElementsByTagName("movementTime").item(0)).getTextContent());
	            			int enerMov = Integer.parseInt(((Node)eElement.getElementsByTagName("movementEnergy").item(0)).getTextContent());
	            			int timeExp = Integer.parseInt(((Node)eElement.getElementsByTagName("desactivationTime").item(0)).getTextContent());
	            			int enerExp = Integer.parseInt(((Node)eElement.getElementsByTagName("desactivationEnergy").item(0)).getTextContent());
	            			robot = new Neutralizador(id,x,y, energy,leader,timeMov,enerMov,timeExp,enerExp);
	            	  		this.getEnvioHechos().insertarHecho(robot);
		            		System.out.println(robot.toString()); //cambiar por trazas?
	            
	            		}
	            		else if(type.equalsIgnoreCase("CentroControl")){
	            			robot = new CentroControl(id,x,y, energy,leader);
	            	  		this.getEnvioHechos().insertarHecho(robot);
		            		System.out.println(robot.toString()); //cambiar por trazas?
	            		}
	            		else{
	                    	this.trazas.trazar(miIdentAgte, "No se ha reconocido el tipo del agente", InfoTraza.NivelTraza.error);

	            		}
	          		}
            	}
            }
            if (!found)
            	this.trazas.trazar(miIdentAgte, "No se ha encontrado el fichero de inicializacion de Estatus", InfoTraza.NivelTraza.error);
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
}
