package projectcontaining.xmlparser;



/**
 * A data object to store point in a 3d space.
 * @author Hielke Hielkema
 */
public class Point3D {
    
    // Private fields
    private int _x;
    private int _y;
    private int _z;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    
    /**
     * Constructor
     * @param x the x of the point
     * @param y the y of the point
     * @param z the z of the point
     */
    public Point3D(int x, int y, int z) {
        this._x = x;
        this._y = y;
        this._z = z;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get functions">
    
    /**
     * Get the x of the point
     * @return the x
     */
    public int getX() {
        return this._x;
    }
    
    /**
     * Get the y of the point
     * @return the y
     */
    public int getY() {
        return this._y;
    }
    
    /**
     * Get the z of the point
     * @return the z
     */
    public int getZ() {
        return this._z;
    }
    
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Set functions">
    
    /**
     * Set the x of the point
     * @param x the x
     */
    public void setX(int x) {
        this._x = x;
    }
    
    /**
     * Set the y of the point
     * @param y the y
     */
    public void setY(int y) {
        this._y = y;
    }
    
    /**
     * Set the z of the point
     * @param z the z
     */
    public void setZ(int z) {
        this._z = z;
    }
    
    //</editor-fold>
        
}
