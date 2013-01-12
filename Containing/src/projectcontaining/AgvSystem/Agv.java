/* TODO:
 * adjust getCloser method to speed
 */
package projectcontaining.AgvSystem;

import projectcontaining.AgvSystem.routemanager.Point;
import projectcontaining.DateTime;
import projectcontaining.Update;
import projectcontaining.xmlparser.ContainerData;

/**
 *
 * @author Jasper + Hielke
 */
public class Agv implements Update{
    
    // Private fields
    private int[] _currentPosition = new int[2];   // Current position [0]=X, [1]=Y
    private int[] _initPosition = new int[2];      // Init position for the agv (for reset())
    private Point[] _unloadedRoute;                // Route when unloaded
    private Point[] _loadedRoute;                  // Route when loaded
    private Point[] _parkingRoute;                 // Route to parking place
    private Point _targetPoint;                    // The target point
    private int _routepoint = 0;                   // The index of the point in the route
    private boolean _active = false;               // If the agv is active
    private boolean _parked = true;                // If the agv is parked
    private boolean _loaded = false;               // If the agv is loaded with a container
    private AgvManager _agvManager;                // The agv manager (for route calculating)
    private ContainerData _container;              // The container on the agv
    
    // Constant
    private final double LOADED_SPEED = 20/3.6;    // The speed of the agv when it's loaded in m/s
    private final double UNLOADED_SPEED = 40/3.6;  // The speed of the agv when it's not loaded in m/s
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    /**
     * Constructor
     * @param position
     * @param agvManager
     */
    public Agv(int[] position, AgvManager agvManager){
        this._agvManager = agvManager;
        this._targetPoint = agvManager.getRouteManager().getParkingPoint();
        this._currentPosition[0] = position[0];
        this._currentPosition[1] = position[1];
        this._initPosition[0] =  position[0];
        this._initPosition[1] =  position[1];
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get Functions">
    /**
     * methode to get the current position of an agv
     * @return
     */
    public int[] getPosition(){
        return _currentPosition;
    }
    
    /**
     * Get the current point (for route planning)
     * @return
     */
    public Point getCurrentPoint(){
        return _targetPoint;
    }
    
    /**
     * Methode to get the current speed of a Agv. Depends on loaded or unloaded
     * @return
     */
    public double getSpeed(){
        return this._loaded ? LOADED_SPEED:UNLOADED_SPEED;
    }
    
    /**
     * Get the container
     * @return
     */
    public ContainerData getContainer() {
        return this._container;
    }
    
    /**
     * Get if the agv is loaded
     * @return
     */
    public boolean getLoaded(){
        return _loaded;
    }
    
    /**
     * methode to check if the Agv is activated.
     * @return 
     */
    public boolean getActive(){
        return _active;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Set functions (for the routes)">
    /**
     * set the positions where the Agv should go
     * @param position
     */
    public void setParkingRoute(Point[] route) {
        this._parkingRoute = route;
    }
    
    /**
     * Set the load route
     * @param route
     */
    public void setloadedRoute(Point[] route) {
        this._loadedRoute = route;
    }
    
    /**
     * Set the unload route
     * @param route
     */
    public void setunloadedRoute(Point[] route) {
        this._unloadedRoute = route;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Update position">
    /**
     * methode from the interface Update. When called, decide if the Agv should move,
     * and where the Agv should move
     */
    @Override
    public void update(DateTime time, DateTime interval){
        Point[] _route;
        if(!_parked){ //if not parked
            if(_active){
                _route = (_loaded)? _loadedRoute : _unloadedRoute;
            }else{
                _route = _parkingRoute;
            }
            
            if(_currentPosition[0] != _targetPoint.getPosition()[0] || _currentPosition[1] != _targetPoint.getPosition()[1]){
                getCloser(_currentPosition, _targetPoint.getPosition());
            }
            else if(_routepoint < _route.length -1){
                _targetPoint = _route[++_routepoint];
            }
            else {
                _routepoint = 0;
                if(_route == _unloadedRoute){ //load the agv
                    this._loaded = true;
                    
                    
                    
                    //call method to load
                    
                    
                    
                }
                else if(_route == _loadedRoute){ //unload the agv
                    this._loaded = false;
                    
                    
                    //call method to unload
                    
                    
                    
                    //if queue is empty go back to parking spot, else get new route from current position
                    if(!_agvManager.queueIsEmpty()){
                        _agvManager.getNewRoute(this);
                    }
                    else{
                        _active = false;
                        _agvManager.decreaseNumberOfInUse();
                    }
                    
                }
                else{
                    _parked = true;
                }
            }
        }
    }
    
    /**
     * methode to get closer. Position to go is position1, position current is position2
     * should be adjusted to speed. Not possible yet.
     * @param Position1
     * @param Position2
     */
    public void getCloser(int[] positionToGo, int[] currentPosition){
        int xDistance = currentPosition[0] - positionToGo[0];//if positive go to the right
        int yDistance = currentPosition[1] - positionToGo[1];//if positive go up
        
        if(xDistance > 0) {
            _currentPosition[0]++;
        }
        if(xDistance < 0) {
            _currentPosition[0]--;
        }
        if(yDistance > 0) {
            _currentPosition[1]++;
        }
        if(yDistance < 0) {
            _currentPosition[1]--;
        }
    }
    //</editor-fold>
    
    /**
     * methode to activate a Agv
     */
    public void ActivateAGV(ContainerData container){
        this._active = true;
        this._parked = false;
        this._loaded = false;
        this._routepoint = 0; //routepoint start with 0 again. IMPORTANT!
        this._container = container;
    }
    
    /**
     * Call this when the agv is loaded
     */
    public void agvLoaded() {
        this._loaded = true;
    }
    
    /**
     * Call this when the agv is unloaded
     */
    public void agvUnloaded() {
        this.resetData();
    }
    
    /**
     * Reset the agv data
     */
    public void resetData() {
        this._active = false;
        this._loaded = false;
        this._parked = true;
        this._container = null;
        this._currentPosition[0] = this._initPosition[0];
        this._currentPosition[1] = this._initPosition[1];
    }
}
