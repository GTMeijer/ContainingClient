package projectcontaining.AgvSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import projectcontaining.AgvSystem.routemanager.Point;
import projectcontaining.AgvSystem.routemanager.RouteManager;
import projectcontaining.DateTime;
import projectcontaining.Update;

/**
 * The main controller to manage all the agv's
 * @author Jasper + Hielke
 */
public class AgvManager implements Update {

    // Private fields
    private ArrayList<Agv> _AGVlist;          // A list of all agv's
    private RouteManager _routeManager;       // The route manager
    private int _numberInUse = 0;             // Number of agv's in use
    private Queue<AgvRequest> _requestsQueue; // Queue of agv requests
    private Point _parkingPoint;              // The parking position for agv's
    
    // Constant
    private final int MAX_AGV_COUNT = 100;    // Max amount of AGV's
        
    /**
     * Constructor
     */
    public AgvManager(){
        // Init the route manager
        this._routeManager = new RouteManager(this);
        
        // Init the request queue
        this._requestsQueue = new LinkedList<>();
        
        // Init the list of agv's
        this._AGVlist = new ArrayList<>();
        for(int i = 0; i < this.MAX_AGV_COUNT; i++) {
            _AGVlist.add(new Agv(new int[]{2,i*8}, this));
        }
        
        // Get the parking point from the route manager
        this._parkingPoint = this._routeManager.getParkingPoint();
    }
    
    /**
     * request an agv with an agvRequest, if there are no actives left
     * put the request on a queue.
     * @param request 
     */
    public void addRequest(AgvRequest request){
        if(nonActivesLeft()){
            // Send the agv
            sendAGVTo(request);
        }
        else{
            // Add the request to the request queue
            this._requestsQueue.add(request);
        }
    }
    
    /*
     * check if the queue is empy or not. if empty it returns true
     */
    public boolean queueIsEmpty(){
        return this._requestsQueue.isEmpty();
    }
    
    /**
     * check of there are nonActives left 
     * @return 
     */
    public boolean nonActivesLeft(){
        return this._numberInUse < this.MAX_AGV_COUNT;
    }
    
    /**
     * Give the nearest agv which is not in use the nessecary routes
     * @param position 
     */
    private void sendAGVTo(AgvRequest request){
        // Get the start and end point from the request
        Point startPoint = request.getStartPoint();
        Point endPoint = request.getEndPoint();
        
        // Get the nearest agv
        Agv nearestAGV = getClosedAGV(startPoint);
        
        // Get the current point of the agv
        Point currentPoint = nearestAGV.getCurrentPoint();
        
        // Activate the agv
        nearestAGV.ActivateAGV(request.getContainer());
        
        // Increase the number in use counter
        this._numberInUse++;
        
        try {
            // Set the routes for the agv
            nearestAGV.setunloadedRoute(this._routeManager.getShortestRoute(currentPoint, startPoint));
            nearestAGV.setloadedRoute(this._routeManager.getShortestRoute(startPoint, endPoint));
            nearestAGV.setParkingRoute(this._routeManager.getShortestRoute(endPoint, this._parkingPoint));
        }
        catch(Exception ex){
            System.out.println("Foutmelding bij het vinden van de kortste route:" + ex);
        }
    }
    
    /**
     * A possibility for the agv to get new routes when there are request on the 
     * queue and all agv's are active
     * @param agv 
     */
    public void getNewRoute(Agv agv){
        // Poll a request from the queue
        AgvRequest request = this._requestsQueue.poll();

        // Get the start and end point
        Point startPoint = request.getStartPoint();
        Point endPoint = request.getEndPoint();

        try {
            // Set the routes for the agv
            agv.setunloadedRoute(this._routeManager.getShortestRoute(agv.getCurrentPoint(), startPoint));
            agv.setloadedRoute(this._routeManager.getShortestRoute(startPoint, endPoint));
            agv.setParkingRoute(this._routeManager.getShortestRoute(endPoint, this._parkingPoint));
        }
        catch(Exception ex){
            System.out.println("(GiveNewRoute) Foutmelding bij het vinden van de kortste route:" + ex);
        }
    }
    
    /**
     * get the closest agv to a given point by using pythagoras
     * @param point
     * @return 
     */
    public Agv getClosedAGV(Point point){
        int nearestAGV = 0;
        int nearestDistance = 999999;
        for(int i = 0; i < _AGVlist.size(); i++)
        {
            //only one which are not in use
            if(!_AGVlist.get(i).getActive()){
                int distance = calculateDistance(point.getPosition(), _AGVlist.get(i).getPosition());
                if(distance < 0) {
                    distance *=-1;
                }
                if(distance < nearestDistance)
                {   
                    nearestDistance = distance;
                    nearestAGV = i;
                }
            }
        }
        return _AGVlist.get(nearestAGV);
    }
    
    /**
     * Pythagoras to calculate the distance from one position to another
     * @param positionA
     * @param positionB
     * @return 
     */
    public int calculateDistance(int[] positionA, int[] positionB){
        return (int)Math.sqrt(Math.pow(positionA[0] - positionB[0], 2) + 
                Math.pow(positionA[1] - positionB[1],2));
    }
    
    /**
     * methode to return the agv list
     * @return the agv list
     */
    public ArrayList<Agv> getAGVList(){
        return _AGVlist;
    }

    /**
     * Gets the RouteManager
     * @return the RouteManager
     */
    public RouteManager getRouteManager(){
        return this._routeManager;
    }
    /**
     * decrease the number of active agv's 
     */
    public void decreaseNumberOfInUse(){
        this._numberInUse--;
    }

    /**
     * Update the agv manager
     * @param time the current time
     * @param interval time since last update
     */
    @Override
    public void update(DateTime time, DateTime interval) {
        // Update the agv's
        for(int i = 0; i < this.MAX_AGV_COUNT; i++){
             this._AGVlist.get(i).update(time, interval);
        }
    }
    
    /**
     * Reload the agv manager
     */
    public void reset()
    {
        // Reset the agv's
        for(int i = 0; i < this.MAX_AGV_COUNT; i++){
             this._AGVlist.get(i).resetData();
        }
        
        // Reset in use counter
        this._numberInUse = 0;
        
        // Clear the request queue
        this._requestsQueue.clear();
    }
    
}

