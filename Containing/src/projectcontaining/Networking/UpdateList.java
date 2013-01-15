/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import projectcontaining.SimulationStates;

/**
 *
 * @author Kuchinawa
 */
public class UpdateList {
    
    List<List<SimulationStates>> syncList;
    
    public UpdateList()
    {
         syncList = Collections.synchronizedList(new ArrayList<List<SimulationStates>>());
    }
    
    public synchronized void add(SimulationStates[] coordList)
    {
        List<SimulationStates> addList = Collections.synchronizedList(Arrays.asList(coordList));
        syncList.add(addList);
    }
    
    public synchronized List<List<SimulationStates>> getUpdate(int updatesRecieved)
    {
        List<List<SimulationStates>> returnList = new ArrayList<>();
        
        if(!syncList.isEmpty())
        {
            if(syncList.size() > updatesRecieved)
                returnList = new ArrayList<>(syncList.subList(updatesRecieved, syncList.size()));
        }
        
        return returnList;
    }
}