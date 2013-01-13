/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kuchinawa
 */
public class UpdateList {
    
    List<List<Float>> syncList;
    
    public UpdateList()
    {
         syncList = Collections.synchronizedList(new ArrayList<List<Float>>());
    }
    
    public synchronized void add(Float[] coordList)
    {
        List<Float> addList = Collections.synchronizedList(Arrays.asList(coordList));
        syncList.add(addList);
    }
    
    public synchronized List<List<Float>> getUpdate(int updatesRecieved)
    {
        List<List<Float>> returnList = new ArrayList<>();
        
        if(!syncList.isEmpty())
        {
            if(syncList.size() > updatesRecieved)
                returnList = new ArrayList<>(syncList.subList(updatesRecieved, syncList.size()));
        }
        
        return returnList;
    }
}