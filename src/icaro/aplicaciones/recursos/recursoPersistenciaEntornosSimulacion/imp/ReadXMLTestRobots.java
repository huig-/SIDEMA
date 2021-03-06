package icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp;

import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.imp.ClaseGeneradoraRepositorioInterfaces;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import icaro.aplicaciones.Rosace.informacion.Coordinate;

public  class ReadXMLTestRobots {

	private String sequenceTestPath;
	
	public  ReadXMLTestRobots(String testFilePath){
		this.sequenceTestPath = testFilePath;
	}

	public String gettestFilePaht(){
		return this.sequenceTestPath;
	}
	
	public synchronized Document getDocument(String testFilePath){
		Document doc=null;		
        try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  doc = dBuilder.parse(new File(testFilePath));
			  doc.getDocumentElement().normalize();
			  return doc;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return doc; //this return will be not executed
	}
	
	//tag parameter should be equal to "robot"
	public synchronized NodeList getRobotsXMLStructure(Document doc, String tag){
	  NodeList nodeLst = doc.getElementsByTagName(tag);
	  return nodeLst;	
	}

	//nodeLst is the value returned by getRobotsXMLStructure method
    public int getNumberOfRobots(NodeList nodeLst){
    	return nodeLst.getLength();
    }

	//nodeLst is the value returned by getRobotsXMLStructure method
    //sequencePosition start with 0
    public synchronized Element getRobotElement(NodeList nodeLst, int sequencePosition){
    	  Element info=null;
		  Node fstNode = nodeLst.item(sequencePosition);
		  if (fstNode.getNodeType() == Node.ELEMENT_NODE){
			  info = (Element) fstNode;
		  }		      	
		  return info;
    }

	//Obtain information about name for the current robot (contained in Element info)
    //tag parameter should be equal to "id"
    public synchronized String getRobotIDValue(Element info, String tag){
		  NodeList idNmElmntLst = info.getElementsByTagName(tag);
		  Element idNmElmnt = (Element) idNmElmntLst.item(0);
		  NodeList idNm = idNmElmnt.getChildNodes();					  
		  String valueid = ((Node)idNm.item(0)).getNodeValue();					  
    	  return valueid;
    }

	//Obtain information about initial energy for the current robot (contained in Element info)
    //tag parameter should be equal to "initialenergy"    
    public synchronized int getRobotInitialEnergy(Element info, String tag){
		  NodeList initialenergyNmElmntLst = info.getElementsByTagName(tag);
		  Element initialenergyNmElmnt = (Element) initialenergyNmElmntLst.item(0);
		  NodeList initialenergyNm = initialenergyNmElmnt.getChildNodes();					  
		  String initialenergy = ((Node)initialenergyNm.item(0)).getNodeValue();					  
		  return Integer.parseInt(initialenergy);
    }


	//Obtain information about x coordinate for the current robot  (contained in Element info)
    //tagcoordinate parameter should be equal to "initialcoordinate"
    //tagdimension parameter should be equal to "x"
    private synchronized String getRobotCoordinateX(Element info, String tagcoordinate, String tagdimension){
          String valuex = "";          
		  NodeList coordinateNmElmntLst = info.getElementsByTagName(tagcoordinate);
		  Node coordinateNode = coordinateNmElmntLst.item(0);
		  if (coordinateNode.getNodeType() == Node.ELEMENT_NODE){
			  Element coorInfo = (Element) coordinateNode;			  
			  //Obtain information about x coordinate for the current robot 
			  NodeList xNmElmntLst = coorInfo.getElementsByTagName(tagdimension);
			  Element xNmElmnt = (Element) xNmElmntLst.item(0);
			  NodeList xNm = xNmElmnt.getChildNodes();					  
			  valuex = ((Node)xNm.item(0)).getNodeValue();				  
		  }
		  return valuex;
    }

	//Obtain information about y coordinate for the current robot  (contained in Element info)
    //tagcoordinate parameter should be equal to "initialcoordinate"
    //tagdimension parameter should be equal to "y"    
    private synchronized String getRobotCoordinateY(Element info, String tagcoordinate, String tagdimension){
          String valuey = "";        
		  NodeList coordinateNmElmntLst = info.getElementsByTagName(tagcoordinate);
		  Node coordinateNode = coordinateNmElmntLst.item(0);
		  if (coordinateNode.getNodeType() == Node.ELEMENT_NODE){
			  Element coorInfo = (Element) coordinateNode;			  			  
			  //Obtain information about y coordinate for the current robot 
			  NodeList yNmElmntLst = coorInfo.getElementsByTagName(tagdimension);
			  Element yNmElmnt = (Element) yNmElmntLst.item(0);
			  NodeList yNm = yNmElmnt.getChildNodes();					  
			  valuey = ((Node)yNm.item(0)).getNodeValue();					  
		  }
		  return valuey;
    }    

	//Obtain information about z coordinate for the current robot  (contained in Element info)
    //tagcoordinate parameter should be equal to "initialcoordinate"
    //tagdimension parameter should be equal to "z"    
    private String getRobotCoordinateZ(Element info, String tagcoordinate, String tagdimension){
          String valuez = "";        
		  NodeList coordinateNmElmntLst = info.getElementsByTagName(tagcoordinate);
		  Node coordinateNode = coordinateNmElmntLst.item(0);
		  if (coordinateNode.getNodeType() == Node.ELEMENT_NODE){
			  Element coorInfo = (Element) coordinateNode;			  			  
			  //Obtain information about z coordinate for the current robot 
			  NodeList zNmElmntLst = coorInfo.getElementsByTagName(tagdimension);
			  Element zNmElmnt = (Element) zNmElmntLst.item(0);
			  NodeList zNm = zNmElmnt.getChildNodes();					  
			  valuez = ((Node)zNm.item(0)).getNodeValue();				  
		  }
		  return valuez;
    }    

	//Obtain information about coordinates for the current robot  (contained in Element info)    
    public synchronized Coordinate getRobotCoordinate(Element info){
    	
    	Coordinate robotCoordinate = new Coordinate(Double.parseDouble(this.getRobotCoordinateX(info,"initialcoordinate","x")),
    												 Double.parseDouble(this.getRobotCoordinateY(info,"initialcoordinate","y")),
    												 Double.parseDouble(this.getRobotCoordinateZ(info,"initialcoordinate","z"))
    												);    	
    	return robotCoordinate;
    }

	//Obtain information about heal range for the current robot (contained in Element info)
    //tag parameter should be equal to "healrange"
    public synchronized float getRobotHealRange(Element info, String tag){
		  NodeList healrangeNmElmntLst = info.getElementsByTagName(tag);
		  Element healrangeNmElmnt = (Element) healrangeNmElmntLst.item(0);
		  NodeList healrangeNm = healrangeNmElmnt.getChildNodes();					  
		  String valuehealrange = ((Node)healrangeNm.item(0)).getNodeValue();		  		  
    	  return Float.parseFloat(valuehealrange);
    }
    
    
    public synchronized Coordinate getRobotCoordinate(String robotName){
    	Coordinate c=null;

    	ItfUsoRepositorioInterfaces itfUsoRepositorioInterfaces;
    	ItfUsoConfiguracion itfconfig;
    	String rutaFicheroRobotTest="";
    	
		//Recuperar la ruta del fichero de robots del escenario
    	try{    	
    		itfUsoRepositorioInterfaces = ClaseGeneradoraRepositorioInterfaces.instance();
    		itfconfig = (ItfUsoConfiguracion)itfUsoRepositorioInterfaces.obtenerInterfaz(NombresPredefinidos.NOMBRE_ITF_USO_CONFIGURACION);
    		rutaFicheroRobotTest = itfconfig.getValorPropiedadGlobal(VocabularioRosace.rutaFicheroRobotsTest); 
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}

//    	ReadXMLTestRobots rXMLTRobots = new ReadXMLTestRobots(Constantes.rutasFicheroRobots);

    	//Leer la coordenada del robot examinando el fichero de robots obtenido
    	ReadXMLTestRobots rXMLTRobots = new ReadXMLTestRobots(rutaFicheroRobotTest);    	
		Document doc = rXMLTRobots.getDocument(rXMLTRobots.gettestFilePaht());
		//Obtain all the robots
		NodeList nodeLst = rXMLTRobots.getRobotsXMLStructure(doc, "robot");		
        for(int j=0; j<rXMLTRobots.getNumberOfRobots(nodeLst);j++){
  		    //Obtain info about robot located at the test        	
        	Element info = rXMLTRobots.getRobotElement(nodeLst, j);         		        				
		    String valueid = rXMLTRobots.getRobotIDValue(info,"id");
		    
		    if (valueid.equals(robotName)){
			    Coordinate valueInitialCoordinate = rXMLTRobots.getRobotCoordinate(info);
			    System.out.println("coordinate (Coordinate(double,double,double))-> " + valueInitialCoordinate);
			    return valueInitialCoordinate;
		    }		    
        }    	
    	return c;
    }



}
