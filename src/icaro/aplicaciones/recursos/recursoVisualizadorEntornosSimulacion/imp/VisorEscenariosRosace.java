package icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.imp.ClaseGeneradoraRepositorioInterfaces;

import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.utils.AccesoPropiedadesGlobalesRosace;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp.ReadXMLTestRobots;
import icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion.imp.ReadXMLTestSequence;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class VisorEscenariosRosace extends JFrame {

    private String tituloVentanaVisor = "ROSACE Scenario Visor";
    private String rutassrc = "src/";   //poner "src/main/java" si el proyecto de icaro se monta en un proyecto maven
    private String rutapaqueteConstructorEscenariosROSACE = "utilsDiseniaEscenariosRosace/";
    private String directorioTrabajo;
    private int excesoY = 20;
    private int excesoX = 10;
    private int dimensionVerticalTextArea = 20;
    private int dimensionHorizontalJFrame = excesoX + 1100;                                //width  -- ancho  1110 es el valor del ancho del JPanel del editor de escenarios
    private int dimensionVerticalJFrame = excesoY + dimensionVerticalTextArea + 700;     //height -- alto   700  es el valor del alto  del JPanel del editor de escenarios
    private int posicionXInicialJFrame = 0;
    private int posicionYInicialJFrame = 0;
    private JPanel contentPaneRoot;
    private Map<String, JLabel> robotslabel;      //La clave es el indice del robot, es decir, 1, 2, 3, .... El contenido es el JLabel
    private Map<String, JLabel> victimaslabel;    //La clave es el indice de la victima, es decir, 1, 2, 3, .... El contenido es el JLabel 
    private String imageniconoHombre = "Hombre.png";
    private String imageniconoMujer = "Mujer.png";
    private String imageniconoMujerRescatada = "MujerRescatada.png";
    private String imageniconoHombreRescatado = "HombreRescatado.png";
    private String imageniconoRobot = "Robot.png";
    private JTextArea textAreaMensaje;
    private JPanel panelVisor;

    //Variables para hacer pruebas en local sin lanzar un descripcion de la organizacion
    //String rutaFicheroRobotsTest = "src/utils/Esc_Igualitario_8Robots_001.xml";
    //String rutaFicheroVictimasTest = "src/utils/Esc_16Victimas_0IP_001.xml";
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
        String rutaFichero;
            public void run() {
                try {

                   VisorEscenariosRosace visor = new VisorEscenariosRosace(rutaFichero);
                    visor.setVisible(true);

                    //Ejemplos para cambiar de posicion, en el visor del escenario, un robot cuando se mueve
                    //******************************************************************************************
                    visor.cambiarPosicionRobot("robotIgualitario2", 800, 600);

                    visor.cambiarPosicionRobot("robotIgualitario3", 1070, 650);
                    //visor.cambiarPosicionRobot("robotIgualitario3", 500, 700);  //en este caso pinta fuera del panel y no se ve el robot

                    for (int i = 0; i <= 8; i++) {
                        if (i % 2 == 0) {
                            visor.cambiarPosicionRobot("robotIgualitario7", 484, 401);  //484,401   //Se coloca sobre la victima 10
                        } else {
                            visor.cambiarPosicionRobot("robotIgualitario7", 640, 600);
                        }
                    }

                    //Ejemplos para cambiar el icono de una victima cuando la victima es rescatada
                    //******************************************************************************************
                    visor.cambiarIconoVictimaARescatada("Victim.3");
                    visor.cambiarIconoVictimaARescatada("Victim.6");
                    visor.cambiarIconoVictimaARescatada("Victim.12");
                    visor.cambiarIconoVictimaARescatada("Victim.15");
                    visor.cambiarIconoVictimaARescatada("Victim.16");


                    visor.escribirEnAreaTexto("hola\n");
                    visor.escribirEnAreaTexto("hola que tal\n");
                    visor.escribirEnAreaTexto("La Victim.1 ha sido rescatada\n");
                    visor.escribirEnAreaTexto("La Victim.7 ha sido rescatada\n");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VisorEscenariosRosace(String rutaFicheroRobotsTest) throws Exception {
        
    }
    public VisorEscenariosRosace() throws Exception {

        robotslabel = new HashMap<String, JLabel>();
        victimaslabel = new HashMap<String, JLabel>();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 649, 409);
        contentPaneRoot = new JPanel();
        contentPaneRoot.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPaneRoot);

        //CONFIGURANDO EL VISOR
        //---------------------------------------------------------------------------
        this.setTitle(tituloVentanaVisor);

        //Desactivar el boton de maximizar
        this.setResizable(false);

        //Fijar las dimensiones del JFrame
        Dimension dimension = new Dimension();
        dimension.setSize(dimensionHorizontalJFrame, dimensionVerticalJFrame);
        this.setSize(dimension);

        //Posicionar el JFrame en la esquina superior izquierda de la pantalla
        this.setLocation(posicionXInicialJFrame, posicionYInicialJFrame);

        //this.setVisible(true);


        //this.setExtendedState(JFrame.ICONIFIED);  //Mostrar el JFrame minimizado


        //contentPaneRoot.setBackground(Color.WHITE);   //Poner en blanco el color del fondo del JPanel
        contentPaneRoot.setLayout(null);


        //Colocar un JSplitPane en el componente raiz
        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  //Los componentes se colocan verticalmente en el JSplitPane
        splitPane.setSize(dimensionHorizontalJFrame, dimensionVerticalJFrame - excesoY);
        contentPaneRoot.add(splitPane);

        //Poner en la parte superior del JSplitPane un JPanel para mostrar los elementos del escenarios
        //---------------------------------------------------------------------------------------------
        //JPanel panelVisor = new JPanel();
        panelVisor = new JPanel();
//        panelVisor.addMouseListener(new MouseAdapter() {
//        	@Override
//        	public void mouseClicked(MouseEvent e) {        		
//				int X = e.getX();
//				int Y = e.getY();
//              System.out.println("Clic en X->" + X + " , Y->" + Y);		
//        	}
//        });

        panelVisor.setBackground(Color.WHITE);
        panelVisor.setSize(dimensionHorizontalJFrame, dimensionVerticalJFrame);
        panelVisor.setLayout(null);
        splitPane.setLeftComponent(panelVisor);


        System.out.println("Tamanio del visor -> " + panelVisor.size());

        //Poner en la parte inferior del JSplitPane un JTextArea para mostrar mensajes
        //---------------------------------------------------------------------------------------------
        //JTextArea textAreaMensaje = new JTextArea();
        //textAreaMensaje lo he declarado como variable global para que sea muy sencillo ofrecer un metodo que nos permita escribir texto en el JTextArea
        textAreaMensaje = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaMensaje,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(dimensionHorizontalJFrame, dimensionVerticalTextArea);
        scrollPane.setAutoscrolls(true);
        splitPane.setRightComponent(scrollPane);


        //Colocar la posicion del divisor
        splitPane.setDividerLocation(dimensionVerticalJFrame - dimensionVerticalTextArea - 3 * excesoY);

        splitPane.setOneTouchExpandable(false);
        splitPane.setEnabled(false);              //No permitir al usuario cambiar de tamanio el JSplitPane

        //System.out.println("Tamanio del splitPane ->" + splitPane.getSize().width + " , " +  
        //        splitPane.getSize().height);

        //System.out.println("Tamanio del scrollPane (incluye TextArea)->" + scrollPane.getSize().width + " , " +  
        //		                                         scrollPane.getSize().height);


        //Leo la ruta de los ficheros
        directorioTrabajo = System.getProperty("user.dir");  //Obtener directorio de trabajo


        //--------------------------------------------
        // Leer xml simulacion localizacion de robots
        //--------------------------------------------
//        String rutaFicheroRobotsTest = AccesoPropiedadesGlobalesRosace.getRutaFicheroRobotsTest();
//        ReadXMLTestRobots rXMLTRobots = new ReadXMLTestRobots(rutaFicheroRobotsTest);

//        Document docRobots = rXMLTRobots.getDocument(rXMLTRobots.gettestFilePaht());
//        NodeList nodeLstRobots = rXMLTRobots.getRobotsXMLStructure(docRobots, "robot");   //Obtain all the robots		
//        int nroRobots = rXMLTRobots.getNumberOfRobots(nodeLstRobots);

        //----------------------------------------------
        // Leer xml simulacion localizacion de victimas
        //----------------------------------------------

        //Recuperar la ruta del fichero de victimas del escenario
//        ItfUsoRepositorioInterfaces itfUsoRepositorioInterfaces = ClaseGeneradoraRepositorioInterfaces.instance();
        ItfUsoConfiguracion itfconfig = (ItfUsoConfiguracion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.NOMBRE_ITF_USO_CONFIGURACION);
        String rutaFicheroVictimasTest = itfconfig.getValorPropiedadGlobal(VocabularioRosace.rutaFicheroVictimasTest);
        String rutaFicheroRobotsTest = itfconfig.getValorPropiedadGlobal(VocabularioRosace.rutaFicheroRobotsTest);
        ReadXMLTestSequence rXMLTSeq = new ReadXMLTestSequence(rutaFicheroVictimasTest);
        ReadXMLTestRobots rXMLTRobots = new ReadXMLTestRobots(rutaFicheroRobotsTest);
        Document docRobots = rXMLTRobots.getDocument(rXMLTRobots.gettestFilePaht());
        NodeList nodeLstRobots = rXMLTRobots.getRobotsXMLStructure(docRobots, "robot");   //Obtain all the robots		
        int nroRobots = rXMLTRobots.getNumberOfRobots(nodeLstRobots);
        Document docVictimas = rXMLTSeq.getDocument(rXMLTSeq.gettestFilePaht());
        NodeList nodeLstVictimas = rXMLTSeq.getVictimsXMLStructure(docVictimas, "victim");   //Obtain all the victims
        int nroVictimas = rXMLTSeq.getNumberOfVictimsInSequence(nodeLstVictimas);

        System.out.println("Escenario actual simulado con " + nroRobots + " robots y " + nroVictimas + " victimas");
        System.out.println("Los elementos estan localizados en el escenario como sigue ......\n");

        //*********************************************************************************************
        //Aniadir al panel panelVisor los componentes label que representan los robots leidos del xml
        //*********************************************************************************************
        for (int j = 0; j < nroRobots; j++) {
            Element info = rXMLTRobots.getRobotElement(nodeLstRobots, j);
            String valueid = rXMLTRobots.getRobotIDValue(info, "id");
            Coordinate valueInitialCoordinate = rXMLTRobots.getRobotCoordinate(info);
            int coordinateX = (int) valueInitialCoordinate.x;
            int coordinateY = (int) valueInitialCoordinate.y;
            //coordinateX = Math.abs(coordinateX);
            //coordinateY = Math.abs(coordinateY);

            //crear el label y posicionarlo en el JPanel
            JLabel label = new JLabel("");
            String rutaIconoRobot = directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + imageniconoRobot;

            //System.out.println("Ruta->" + rutaIconoRobot);

            label.setIcon(new javax.swing.ImageIcon(rutaIconoRobot));

            //El texto que se pone en el label NO es el nombre completo del robot, solo ponemos el numero. 
            //Por ejemplo, de robotIgualitario2 nos quedaria 2, y 2 sería el texto que ponemos en el label
            int index = utilsCadenaComponente.getNumberStartIndex(valueid);
            String idNumero = utilsCadenaComponente.getNumber(valueid, index);
            label.setText(idNumero);
            label.setEnabled(true);
            label.setVisible(true);

            Dimension size = label.getPreferredSize();
            label.setBounds(coordinateX, coordinateY, size.width, size.height);
            panelVisor.add(label);

            robotslabel.put(idNumero, label);   //Lo anoto en el Map: la clave es el numero del robot y contenido es el label creado

            System.out.println("Localizacion del robot " + label.getText() + "-> (" + label.getLocation().x + "," + label.getLocation().y + ")");
        }

        System.out.println("");


        //*********************************************************************************************        
        //Aniadir al panel panelVisor los componentes label que representan las victimas leidas del xml
        //*********************************************************************************************                
        for (int j = 0; j < nroVictimas; j++) {
            //Obtain info about first victim located at the test sequence 
            Element info = rXMLTSeq.getVictimElement(nodeLstVictimas, j);
            String valueid = rXMLTSeq.getVictimIDValue(info, "id");
            Coordinate valueInitialCoordinate = rXMLTSeq.getVictimCoordinate(info);
            int coordinateX = (int) valueInitialCoordinate.x;
            int coordinateY = (int) valueInitialCoordinate.y;
            //coordinateX = Math.abs(coordinateX);
            //coordinateY = Math.abs(coordinateY);

            int index = utilsCadenaComponente.getNumberStartIndex(valueid);
            String idNumero = utilsCadenaComponente.getNumber(valueid, index);

            //System.out.println("idNumero->" + idNumero);

            int indexVictima;

            if (idNumero.equals("")) {
                indexVictima = 0;
                idNumero = "0";
                //System.out.println("El valor de idNumero es vacio");
            } else {
                int aux = utilsCadenaComponente.getNumberStartIndexPrimerDigitoDistintoCero(idNumero);
                idNumero = utilsCadenaComponente.getNumberSinCerosAlaIzquierda(idNumero, aux);
                //System.out.println("El valor de idNumero ahora es " + idNumero);           	
                indexVictima = Integer.parseInt(idNumero);
            }


            //Las victimas con identificador IMPAR se representaran con el icono de mujer. Por ejemplo, Victim.1, Victim.3, Victim.5, ....
            //Las victimas con identificador PAR se representaran con el icono de hombre.  Por ejemplo, Victim.2, Victim.4, Victim.6, ....
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            int numero = indexVictima % 2;
            String rutaIconoVictima = "";
            if (numero == 0) {
                rutaIconoVictima = directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + imageniconoHombre;
            } else {
                rutaIconoVictima = directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + imageniconoMujer;
            }

            //crear el label para la victima y posicionarlo
            JLabel label = new JLabel("");
            label.setIcon(new javax.swing.ImageIcon(rutaIconoVictima));
            //El texto que se pone en el label NO es el nombre completo de la victima, solo ponemos el numero. 
            //Por ejemplo, de Victim.3 nos quedaria 3, y 3 sería el texto que ponemos en el label
            label.setText(idNumero);
            Dimension size = label.getPreferredSize();
            label.setBounds(coordinateX, coordinateY, size.width, size.height);
            panelVisor.add(label);

            victimaslabel.put(idNumero, label);   //Lo anoto en el Map: la clave es el numero de la victima y contenido es el label creado

            System.out.println("Localizacion de la victima " + label.getText() + "-> (" + label.getLocation().x + "," + label.getLocation().y + ")");
        }
    }

    public synchronized void cambiarPosicionRobot(String valor_idRobot, int nueva_coordx, int nueva_coordy) {

        String numeroRobot = getNumeroRobot(valor_idRobot);

        //JLabel jlabelRobot = new JLabel();

        JLabel jlabelRobot = robotslabel.get(numeroRobot);

        if (jlabelRobot != null) {
//            JOptionPane.showMessageDialog(panelVisor, "Se llama idRobot:"+valor_idRobot+" X:"+nueva_coordx+ "Y:"+nueva_coordy);
//            jlabelRobot.setBounds(jlabelRobot.getX()+10, jlabelRobot.getY()+10, jlabelRobot.getWidth(), jlabelRobot.getHeight());
            jlabelRobot.setBounds(nueva_coordx, nueva_coordy, jlabelRobot.getWidth(), jlabelRobot.getHeight());
            this.notifyAll();

            //Eliminar de la visualizacion
//            jlabelRobot.setVisible(false);
//            panelVisor.remove(jlabelRobot);
            //Eliminar de la variable mapa que almacena identificadores y posiciones de los robots
//            robotslabel.remove(numeroRobot);

            //Crear una nueva label en la nueva posicion
            //crear el label y posicionarlo en el JPanel
//            JLabel label = new JLabel("");
//            String rutaIconoRobot = directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + imageniconoRobot;
//
//            label.setIcon(new javax.swing.ImageIcon(rutaIconoRobot));
//
//            label.setText(numeroRobot);
//            label.setEnabled(true);
//            label.setVisible(true);
//
//            Dimension size = label.getPreferredSize();
//            label.setBounds(nueva_coordx, nueva_coordy, size.width, size.height);
//
//            panelVisor.add(label);
//
//            robotslabel.put(numeroRobot, label);   //Lo anoto en el Map: la clave es el numero del robot y contenido es el label creado

//            System.out.println("NUEVA Localizacion del robot " + label.getText() + "-> (" + label.getLocation().x + "," + label.getLocation().y + ")");
        } else {
            System.out.println("jlabel nulo");
        }


        System.out.println("Localizacion del robot " + jlabelRobot.getText() + "-> " + jlabelRobot.getBounds());
    }

    public void cambiarIconoVictimaARescatada(String valor_idVictima) {

        String numeroVictima = getNumeroVictima(valor_idVictima);

        int numeroIdVictima = Integer.parseInt(numeroVictima);

        JLabel jlabelVictima = new JLabel();

        jlabelVictima = victimaslabel.get(numeroVictima);

        if (jlabelVictima != null) {

            //String rutaAbsolutaIconoVictima = jlabelVictima.getIcon().toString();			
            //System.out.println("victima " + numeroVictima + "  , " + jlabelVictima.getIcon().toString());

            if (numeroIdVictima % 2 == 0) {
                jlabelVictima.setIcon(new javax.swing.ImageIcon(directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + "HombreRescatado.png"));
                //System.out.println("Es un hombre");
            } else {
                jlabelVictima.setIcon(new javax.swing.ImageIcon(directorioTrabajo + "/" + rutassrc + rutapaqueteConstructorEscenariosROSACE + "MujerRescatada.png"));
                //System.out.println("Es una mujer");
            }

        } else {
            System.out.println("jlabelVictima nulo");
        }
    }

    public void escribirEnAreaTexto(String texto) {

        textAreaMensaje.append(texto);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //  Metodos auxiliares para obtener los valores numericos de los robots y las victimas   //
    //  Por ejemplo getNumeroRobot("robotIgualitario12") devuelve 12                         //
    //              getNumeroVictima("Victim.14") devuelve 14                                //
    ///////////////////////////////////////////////////////////////////////////////////////////
    private String getNumeroRobot(String valor_idRobot) {

        int index = utilsCadenaComponente.getNumberStartIndex(valor_idRobot);
        String idNumero = utilsCadenaComponente.getNumber(valor_idRobot, index);

        return idNumero;
    }

    private String getNumeroVictima(String valor_idVictima) {

        int index = utilsCadenaComponente.getNumberStartIndex(valor_idVictima);
        String idNumero = utilsCadenaComponente.getNumber(valor_idVictima, index);

        return idNumero;
    }
}
