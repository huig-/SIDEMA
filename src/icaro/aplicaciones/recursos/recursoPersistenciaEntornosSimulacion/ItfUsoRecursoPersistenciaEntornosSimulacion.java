package icaro.aplicaciones.recursos.recursoPersistenciaEntornosSimulacion;

import icaro.aplicaciones.Rosace.informacion.*;
import icaro.aplicaciones.recursos.recursoVisualizadorEntornosSimulacion.imp.EscenarioSimulacionRobtsVictms;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;
import java.util.ArrayList;
import java.util.HashSet;

//Other imports used by this Resource
//#start_nodeuseItfSpecialImports:useItfSpecialImports <--useItfSpecialImports-- DO NOT REMOVE THIS
 
//#end_nodeuseItfSpecialImports:useItfSpecialImports <--useItfSpecialImports-- DO NOT REMOVE THIS


   public interface ItfUsoRecursoPersistenciaEntornosSimulacion extends ItfUsoRecursoSimple {


    public ArrayList<Victim> getVictimasArescatar ()throws Exception;
    public void guardarInfoCasoSimulacion (InfoCasoSimulacion infoCaso)throws Exception;
    public InfoCasoSimulacion obtenerInfoCasoSimulacion (String idCaso)throws Exception;
    public void guardarInfoAsignacionVictima (InfoAsignacionVictima infoAsignVictima)throws Exception;
    public ArrayList<InfoAsignacionVictima> obtenerInfoAsignacionVictimas ()throws Exception;
    public boolean guardarSerieResultadosSimulacion(InfoSerieResultadosSimulacion infoSerieResultados)throws Exception;
    public void finSimulacion()throws Exception;
    public String getIdentEscenarioSimulacion()throws Exception;
    public RobotStatus getRobotStatus ( String robotId)throws Exception;
    public EscenarioSimulacionRobtsVictms obtenerInfoEscenarioSimulacion(String rutaFicheroEscenario)throws Exception;
    public boolean  eliminarEscenarioSimulacion(String rutaFicheroInfoPersistencia)throws Exception;
    public boolean guardarInfoEscenarioSimulacion(EscenarioSimulacionRobtsVictms escenario)throws Exception;
    public HashSet obtenerIdentsEscenarioSimulacion()throws Exception;
}