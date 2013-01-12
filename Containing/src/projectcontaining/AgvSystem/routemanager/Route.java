package projectcontaining.AgvSystem.routemanager;

import java.util.ArrayList;

/**
 *
 * @author Jasper
 */
public class Route {
    
    // Private fields
    private ArrayList<Point> route = new ArrayList<>();
    private int routeLength;
    
    public void add(Point point){
        if(route.isEmpty()) {
            route.add(point);
        }
        else{
            addDistance(getEndPoint(), point);
            route.add(point);
        }
    }
    
    public Point getEndPoint(){
        return route.get(route.size()-1);
    }
    
    public Point[] getRoute(){
        Point[] routeArray = new Point[route.size()];
        int i = 0;
        for(Point punt: route)
        {
            routeArray[i++] = punt;
        }
        return routeArray;
    }
    /**
     * methode to add a distance to the routelength
     * @param firstPoint
     * @param secondPoint 
     */
    public void addDistance(Point firstPoint, Point secondPoint){
        routeLength += calculateDistance(firstPoint.getPosition(), secondPoint.getPosition());
    }
    /**
     * Pythagoras to calculate the distance from one point to another
     * @param positionA
     * @param positionB
     * @return 
     */
    public int calculateDistance(int[] positionA, int[] positionB){
        return (int)Math.sqrt(Math.pow(positionA[0] - positionB[0], 2) + 
                Math.pow(positionA[1] - positionB[1],2));
    }
    /**
     * get the distance of a route
     * @return
     */
    public int getDistance(){
        return routeLength;
    }
    
    /**
     * to string method of the route, all points are written in a string
     */
    @Override
    public String toString(){
        String returnString = "";
        for(Point point: route)
        {
            returnString += point.getName();
        }
        return returnString;
    }
    
    /**
     * methode to clear the route
     */
    public void clear(){
        route.clear();  
        routeLength = 0;
    }
    /**
     * method to copy a whole route
     * @return 
     */
    public Route Copy(){
        Route copy = new Route();
        for(Point point: route) {
            copy.add(point);
        }
        return copy;
    }
    
    /**
     * 
     * @param aString
     * @return 
     */
    public boolean contains(String aString){
        for(Point point: route)
        {
            if(aString.equals(point.getName())) {
                return true;
            }
        }
        return false;
    }
}
