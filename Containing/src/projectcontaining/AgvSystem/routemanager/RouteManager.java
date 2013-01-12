/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.AgvSystem.routemanager;

import java.util.ArrayList;
import projectcontaining.AgvSystem.AgvManager;

/**
 *
 * @author Jasper + Hielke
 */
public final class RouteManager {
    
    // Private fields
    private ArrayList<Route> _routeLijst;
    private ArrayList<Point> _puntenLijst;
    private AgvManager _agvManager;         // The parent agv manager
    private Point _parkingPoint;            // The point to park agv's
    
    /**
     * Constructor
     * @param agvManager the owner
     */
    public RouteManager(AgvManager agvManager) {
        this._agvManager = agvManager;
        this._routeLijst = new ArrayList<>();
        this._puntenLijst = new ArrayList<>();
        
        addPoint("A", new int[]{0,200}, new String[]{"B"});
        addPoint("B", new int[]{500,200}, new String[]{"A","C","D","E"});
        addPoint("D", new int[]{500,0}, new String[]{"B"});
        addPoint("C", new int[]{500,500}, new String[]{"B","F"});
        addPoint("E", new int[]{1100,200}, new String[]{"B","H"});
        addPoint("F", new int[]{900,500}, new String[]{"C","H","G"});
        addPoint("H", new int[]{1100,500}, new String[]{"F","E"});
        addPoint("G", new int[]{900,700}, new String[]{"F"});
        
        setParkingPoint(this._puntenLijst.get(0));
        
        try{
            getShortestRoute(this._puntenLijst.get(0), this._puntenLijst.get(5));
        }
        catch(Exception ex){
            System.out.println("FOUTMELDING: " + ex);
        }
    }
    
    /**
     * Add a point
     * @param name name of the point
     * @param position the position of the point
     * @param neighbours the neighbour points
     */
    public void addPoint(String name, int[] position, String[] neighbours) {
        this._puntenLijst.add(new Point(name, position, neighbours));
    }
    
    /**
     * Set the parking point
     * @param point 
     */
    public void setParkingPoint(Point point) {
        this._parkingPoint = point;
    }
    
    /**
     * get the point where the agv's are stored
     * @return 
     */
    public Point getParkingPoint() {
        return this._parkingPoint;
    }

    /**
     * get the closest point from a given position by using pythagoras
     * @param position
     * @return 
     */
    public Point getClosestPoint(int[] position){
        int nearestDistance = 999999; 
        int closePoint = 0;
        for(int i = 0; i < this._puntenLijst.size(); i++)
        {
            int distance = this._agvManager.calculateDistance(position, this._puntenLijst.get(i).getPosition());
            if(distance < 0) {
                distance *=-1;
            }
            
            if(distance < nearestDistance){   
                nearestDistance = distance;
                closePoint = i;
            }
        }
        return this._puntenLijst.get(closePoint);
    }
    
    /**
     * methode om tussen een beginpunt en een eindpunt
     * de korste route terug te geven in een point[]
     */
    public Point[] getShortestRoute(Point beginPunt, Point eindPunt) throws Exception {
        this._routeLijst = new ArrayList<>();
        if(beginPunt == null || eindPunt == null) {
            throw new Exception("BeginPunt en/of eindpunt kloppen niet");
        }
        
        if(beginPunt.getName().equals(eindPunt.getName())){
            return new Point[]{eindPunt}; 
        }
        
        ArrayList<Point> endPointsInList = new ArrayList<>();
        //the number of routes is the same as PointList.size() - 1(begin element);
        String[] neighboursString = beginPunt.getNeighbours();
        Point[] neighbours = stringToPointList(neighboursString);
        Route route = new Route();
        
        for(int i=0; i < neighbours.length; i++)
        {
            route.clear();
            route.add(beginPunt);
            route.add(neighbours[i]);
            this._routeLijst.add(route.Copy());
            endPointsInList.add(neighbours[i]);
        }
        
        doDijkstra(endPointsInList);
        
        for(Route oneRoute : this._routeLijst){
            if(oneRoute.getEndPoint() == eindPunt) {
                return oneRoute.getRoute();
            }
        }
        throw new Exception("Route kon niet gevonden worden!");
    }
    
    /**
     * complete the list by using the dijkstra Algorithm. Check the list
     * and put a neighbour to the routes in the list
     * then check again for the new routes and do the same.
     * Only add a new route when there are no routes in the list with the same
     * endpoint. Or if the route's length is shorter(then it replace the route with
     * the new route
     * continue doing this till there's a list which has the same length of the 
     * pointlist minus 1.
     * @param endPointsInList 
     */
    public void doDijkstra(ArrayList<Point> endPointsInList) {
        int pointer = 0;
        String endStringOfList;
        Point[] currentNeighbours;
        Route routeCopy;
        
        while(this._routeLijst.size() < this._puntenLijst.size() - 1)
        {
            currentNeighbours = new Point[this._puntenLijst.size()]; //new string;
            //get endpoint of currentcheck
            endStringOfList = this._routeLijst.get(pointer).getEndPoint().getName();
            //get neighbours
            for(Point tmpPoint : this._puntenLijst)
            {
                if(tmpPoint.getName().equals(endStringOfList)){
                    currentNeighbours = stringToPointList(tmpPoint.getNeighbours());
                    break;
                }
            }
            
            for(Point neighbour : currentNeighbours){
                if(!this._routeLijst.get(pointer).contains(neighbour.getName()))
                {
                    //alleen toevoegen wanneer het eindpunt nog niet bestaat of korter route is
                    if(!endPointsInList.contains(neighbour)){
                        routeCopy = (this._routeLijst.get(pointer)).Copy();
                        routeCopy.add(neighbour);
                        endPointsInList.add(neighbour);
                        this._routeLijst.add(routeCopy);
                    }
                    else{ //check of er een kortere route is met hetzelfde eindpunt
                        for(Route r : this._routeLijst){
                            if(r.getEndPoint().getName().equals(neighbour.getName())){
                                routeCopy = (this._routeLijst.get(pointer)).Copy();
                                int newRouteLength = routeCopy.getDistance() + routeCopy.calculateDistance(routeCopy.getEndPoint().getPosition(), neighbour.getPosition());
                                if(r.getDistance() > newRouteLength){
                                    routeCopy.add(neighbour);
                                    r = routeCopy.Copy();
                                }
                            }
                        }
                    }
                }
            }
            pointer++;
        }
    }
    
    /**
     * Convert a string[] to a point[]. Nessesary to convert the neighbours to
     * a point array
     * @param stringList
     * @return 
     */
    public Point[] stringToPointList(String[] stringList) {
        Point[] returnList = new Point[stringList.length];
        int i = 0;
        for(String string : stringList){
            for(Point punt: this._puntenLijst){
                if(string.equals(punt.getName())) {
                    returnList[i++] = punt;
                }     
            }
               
        } 
        return returnList;
    }
    
    /**
     * Convert the name of a point to a point
     * @param name
     * @return 
     */
    public Point nameToPoint(String name) {
       for(Point p : this._puntenLijst) {
           if (p.getName().equals((name))) {
               return p;
           }
       } 
       return null;
    }
    
    /**
     * returns a list of points
     * @return 
     */
    public ArrayList<Point> getPoints() {
        return this._puntenLijst;
    }
}
