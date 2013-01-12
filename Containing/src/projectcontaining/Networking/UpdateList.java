/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kuchinawa
 */
public class UpdateList {
    
    List<String> syncList;
    
    public UpdateList()
    {
         syncList = Collections.synchronizedList(new ArrayList<String>());
    }
    
    public synchronized void add(String data)
    {
        syncList.add(data);
    }
    
    public synchronized List<String> getUpdate()
    {
        return syncList;
    }
}