package icaro.aplicaciones.SIDEMA.informacion;

public class VocabularioSIDEMA {
	
	//En nuestro caso es el agente Centro de control
	static public final String IdentAgteDistribuidorTareas = "agenteCC";
	//Mensajes de Input de la maquina de estados del agente reactivo	
	//static public  final String peticionSimulacionVictima = "peticionSimulacionVictima";
    //static public  final String peticionPararSimulacion = "peticionPararSimulacion";
   // static public  final String peticionSimulacionSecuenciaVictimas = "sendSequenceOfSimulatedVictimsToRobotTeam";
    static public  final String peticionMostrarEscenarioActualSimulado = "mostrarEscenarioActualSimulado";
    static public  final String peticionTerminarSimulacion = "TerminarSimulacion";
    static public final String botonSimularPulsado = "botonSimulacionPulsado";
    static public final String botonTerminarPulsado = "botonCancelarPulsado";
    static public final String informarEscenarioSeleccionado = "escenarioSeleccionadoUsuario";
    static public final String informarEscenarioValido = "escenarioDefinidoValido";
    //static public  final String peticionTerminarSimulacionUsuario = "peticionTerminarSimulacion";
    //static public  final String peticionPararSimulacionUsuario ="peticionPararSimulacionUsuario" ;
    //static public  final String informacionVictimaAsignadaARobot = "victimaAsignadaARobot";
    static public  final String informacionFinSimulacion = "finSimulacion";
    /*static public  final String NombreFicheroSerieInfoAsignacionVictimas = "serieInfoAsignacionVictimas";
    static public  final String NombreFicheroSeriePeticionVictimas = "seriePeticionVictimas";
    static public  final String NombreFicheroSerieLlegadaYasignacion = "serieLlegadaYasignacionVictimas";
    static public  final String NombreFicheroSerieAsignacion = "serieAsignacionVictimas";
    static public  final String NombreFicheroEscenarioSimulacion = "identFicheroEscenarioSimulacion";*/
    static public  enum PeticionAgteControlSimul{TerminarSimulacion,mostrarEscenarioActualSimulado,enviarIdentsEquipo};

	
	public static final String MsgPeticionExplorar = "explora terreno";
	public static final String MsgPeticionMinaEncontrada = "mina encontrada";
	public static final String MsgPeticionDesactivar = "desactiva mina";
	public static final String MsgPeticionRegresar = "regresa a base";
	public static final String MsgPeticionPosicionActual = "dime tu posicion";
	public static final String MsgInformarPosicionActual = "te envio mi posicion";
	public static final String MsgInformarNeutralizadorLibre = "ya he desactivado la mina";
	public static final String MsgInformarExploradorLibre = "ya he explorado la celda";
	public static final String CSV_EmptyCell = "E";
	public static final String CSV_BombCell = "O";
	public static final String CSV_ObstacleCell = "X";
	public static final String CSV_SplitBy = " ";
	public static final String MsgPeticionComenzarSimulacion = "comienza la simulacion";
	public static final String MsgInformarCandidatos = "informar candidatos";
	public static final String MsgPeticionEstimacionActual = "dame estimacion actual";
}
