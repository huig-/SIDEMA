/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Rosace.informacion;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author FGarijo
 */
public class VictimsToRescue {
    private Map<String, Victim> victims2Rescue;
    private Victim lastVictimToRescue;

public VictimsToRescue(){
       victims2Rescue = new HashMap <String, Victim>();
} 
public synchronized void addVictimToRescue (Victim victim){
    String idVictim = victim.getName();
    if (!victims2Rescue.containsKey(idVictim)){
        victims2Rescue.put(idVictim, victim);
        lastVictimToRescue= victim;
    }
}
public synchronized Victim getVictimToRescue (String victimId){
   return  victims2Rescue.get(victimId);
}
public synchronized Map<String, Victim> getvictims2Rescue(){
       return this.victims2Rescue;
   }
public synchronized boolean existsVictim2Rescue(String victimId){
       return this.victims2Rescue.containsKey(victimId);
   }
public synchronized void addEstimatedCostVictim2Rescue(String victimId,Integer estimatedCost ){
    Victim victim = victims2Rescue.get(victimId);
    if (victim!= null )victim.setEstimatedCost(estimatedCost);
    
   }
public synchronized void setlastVictimToRescue (Victim victim){
    lastVictimToRescue = victim;
}
public synchronized Victim getlastVictimToRescue (){
   return  lastVictimToRescue;
}
}
