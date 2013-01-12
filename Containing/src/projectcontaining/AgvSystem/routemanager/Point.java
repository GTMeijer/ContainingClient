/*
 * Point data structure.
 */
package projectcontaining.AgvSystem.routemanager;

/**
 * A point in the agv route web
 * @author Jasper + Hielke
 */
public class Point {
    
    // Private fields
    private String _name;
    private String[] _neighbours;
    private int[] _position;
    
    /**
     * Constructor
     * @param name name of the point
     * @param position position of the point
     * @param neighbours neighbour points
     */
    public Point(String name, int[] position, String[] neighbours){
        this._name = name;
        this._position = position;
        this._neighbours = neighbours;
    }
    
    /**
     * Get the neighbour points
     * @return 
     */
    public String[] getNeighbours(){
        return this._neighbours;
    }
    
    /**
     * Get the name
     * @return 
     */
    public String getName(){
        return this._name;
    }
    
    /**
     * Get the position
     * @return 
     */
    public int[] getPosition(){
        return this._position;
    }
}
