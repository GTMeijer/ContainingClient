package projectcontaining.locations.ContainerStack;

import projectcontaining.AgvSystem.Agv;

/**
 * A class to store get or set requests for the container storage
 * @author Hielke Hielkema
 */
public class StoredContainerRequest {
    
    // Private fields
    private Agv _agv;
    private boolean _getRequest;
    private int _strip;
    
    /**
     * Constructor
     * @param agv agv that does the request
     * @param getRequest true = get request, false = set request
     */
    public StoredContainerRequest(Agv agv, boolean getRequest) {
        this._agv = agv;
        this._getRequest = getRequest;
    }
    
    /**
     * Alternative constructor
     * @param agv agv that does the request
     * @param getRequest true = get request, false = set request
     * @param strip the strip of the container in storage
     */
    public StoredContainerRequest(Agv agv, boolean getRequest, int strip) {
        this._agv = agv;
        this._getRequest = getRequest;
        this._strip = strip;
    }
    
    /**
     * Get the agv of the request
     * @return the agv
     */
    public Agv getAgv() {
        return this._agv;
    }
    
    /**
     * Get the request type
     * @return true = get request, false = set request
     */
    public boolean getGetRequest() {
        return this._getRequest;
    }
    
    /**
     * Get the strip of the container in storage
     * @return 
     */
    public int getStrip() {
        return this._strip;
    }
}
