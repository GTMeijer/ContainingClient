package projectcontaining.locations.ContainerStack;

import java.util.LinkedList;
import java.util.List;
import projectcontaining.AgvSystem.Agv;

/**
 * An advanced request queue system
 * @author Hielke Hielkema
 */
public class ContainerStorageRequestQueue {
    
    // Private fields
    private List<StoredContainerRequest> _requests;
    
    /**
     * Constructor
     */
    public ContainerStorageRequestQueue() {
        this._requests = new LinkedList<>();
    }
    
    
    public void pushGetRequest(Agv agv, int row) {
        this._requests.add(new StoredContainerRequest(agv, true, row));
    }
    
    public void pushSetRequest(Agv agv) {
        this._requests.add(new StoredContainerRequest(agv, false));
    }
    
    public StoredContainerRequest getTopSetRequest() {
        int max = this._requests.size() - 1;
        for(int i = max; i >= 0; i--) {
            StoredContainerRequest request = this._requests.get(i);
            if (!request.getGetRequest()) {
                this._requests.remove(i);
                return request;
            }
        }
        return null; // not found
    }
    
     public StoredContainerRequest getTopGetRequestForStrip(int strip) {
        int max = this._requests.size() - 1;
        for(int i = max; i >= 0; i--) {
            StoredContainerRequest request = this._requests.get(i);
            if (request.getGetRequest() && request.getStrip() == strip) {
                this._requests.remove(i);
                return request;
            }
        }
        return null; // not found
    }
     
     public int getSize() {
         return this._requests.size();
     }
     
     /**
      * Reset the queue
      */
     public void reset() {
         this._requests.clear();
     }
}
