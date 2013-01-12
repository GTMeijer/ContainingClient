package projectcontaining.AgvSystem;

import projectcontaining.AgvSystem.routemanager.Point;
import projectcontaining.locations.Location;
import projectcontaining.xmlparser.ContainerData;

/**
 * Object to store information about an agv transport request
 * @author Jasper + Hielke
 */
public class AgvRequest {
 
    // Private fields
    private Location _startLocation;
    private Location _endLocation;
    private ContainerData _container;
    private AgvManager _agvManager;
    
    /**
     * Constructor
     * @param startLocation the first location to get the container from
     * @param endLocation the end location to bring the container to
     * @param container container to transport
     * @param agvManager the parent agv manager used for route finding
     */
    public AgvRequest(Location startLocation, Location endLocation, ContainerData container, AgvManager agvManager) {
        this._startLocation = startLocation;
        this._endLocation = endLocation;
        this._container = container;
        this._agvManager = agvManager;
    }
    
    /**
     * Get the container to transport
     * @return container to transport
     */
    public ContainerData getContainer(){
        return _container;
    }
    
    /**
     * Get the the first location to get the container from
     * @return the first location to get the container from
     */
    public Location getStartLocation(){
        return this._startLocation;
    }
    
    /**
     * Get the the end location to bring the container to
     * @return the end location to bring the container to
     */
    public Location getEndLocation(){
        return this._endLocation;
    }

    /**
     * Get the the first location to get the container from as a Point
     * @return the first location to get the container from as a Point
     */
    public Point getStartPoint() {
        return this._agvManager.getRouteManager().nameToPoint(this._startLocation.GetMapLocation());
    }
    
    /**
     * Get the the end location to bring the container to as a Point
     * @return the end location to bring the container to as a Point
     */
    public Point getEndPoint() {
        return this._agvManager.getRouteManager().nameToPoint(this._endLocation.GetMapLocation());
    }
}
