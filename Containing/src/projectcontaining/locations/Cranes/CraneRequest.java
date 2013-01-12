/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.locations.Cranes;

import projectcontaining.AgvSystem.Agv;
import projectcontaining.xmlparser.ContainerData;

/**
 * A object to store the crane requests
 * @author Hielke Hielkema
 */
public class CraneRequest {
    
    // Private fields
    private Agv _agv;
    private ContainerData _container;
    private boolean _getRequest;
    
    /**
     * Constructor
     * @param agv
     * @param container 
     */
    public CraneRequest(Agv agv, ContainerData container, boolean getRequest) {
        this._agv = agv;
        this._container = container;
        this._getRequest = getRequest;
    }
    
    /**
     * Get the agv
     * @return the agv
     */
    public Agv getAgv() {
        return this._agv;
    }
    
    /**
     * Get the container
     * @return the container
     */
    public ContainerData getContainer() {
        return this._container;
    }
    
    /**
     * Get if the request is a get request
     * @return if the request is a get request
     */
    public boolean getGetRequest() {
       return this._getRequest; 
    }
}
