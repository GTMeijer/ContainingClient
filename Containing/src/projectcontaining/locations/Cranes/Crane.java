package projectcontaining.locations.Cranes;

import projectcontaining.AgvSystem.Agv;
import projectcontaining.DateTime;
import projectcontaining.xmlparser.Point3D;

/**
 *
 * @author Hielke Hielkema
 */
public class Crane {
        
    // Private fields
    private float _x;
    private float _y;
    private float _startX;
    private float _startY;
    private float _conX;
    private float _conY;
    private float _agvX;
    private float _agvY;
    private Agv _agv;
    private float _craneSpeedLoaded; // in m/s
    private float _craneSpeedNotLoaded; // in m/s
    private CraneOwner _owner;
    
    // The state of the crane
    private int _state;
    // 0 = inactive
    // 1 = go to container, then -> 2
    // 2 = pick up container, then -> 3
    // 3 = bring container to agv location -> 4
    // 4 = put container on agv -> 0
    // 5 = go to agv. then -> 6
    // 6 = pick up container from agv -> 7
    // 7 = bring container to location -> 8
    // 8 = put container on position -> 0

    

    /**
     * Constructor
     * @param startX the location of the crane
     * @param startY the location of the crane
     */
    public Crane(CraneOwner owner, float startX, float startY, float craneSpeedLoaded, float craneSpeedNotLoaded) {
        this._owner = owner;
        this._startX = startX;
        this._startY = startY;
        this._craneSpeedLoaded = craneSpeedLoaded;
        this._craneSpeedNotLoaded = craneSpeedNotLoaded;
        this.reset();
    }
    
    /**
     * Gets if the crane is active
     * @return if the crane is active
     */
    public boolean getActive() {
        return this._state != 0;
    }
    
    /**
     * Get is the crane is loaded with a container
     * @return if the crane is loaded
     */
    private boolean isLoaded() {
        return this._state == 3 || this._state == 7;
    }
    
    /**
     * 
     * @param sender
     * @param container
     * @param x
     * @param y
     * @param z 
     */
    public void getContainer(Agv sender, Point3D location) {
        // Agv location
        this._agvX = sender.getPosition()[0]; // X
        this._agvY = sender.getPosition()[1]; // Y
        
        // Container location
        this._conX = location.getX();
        this._conY = location.getY();
        
        // Set the state
        this._state = 1; // Go to location
    }

    /**
     * 
     * @param sender
     * @param container
     * @param x
     * @param y
     * @param z 
     */
    public void setContainer(Agv sender, Point3D location) {
        // Agv location
        this._agvX = sender.getPosition()[0]; // X
        this._agvY = sender.getPosition()[1]; // Y
        
        // Container location
        this._conX = location.getX();
        this._conY = location.getY();
        
        // Set the state
        this._state = 5; // Go to agv
    }

    final public void reset() {
         this._state = 0; // inactive
         this._x = this._startX;
         this._y = this._startY;
         this._conX = 0;
         this._conY = 0;
         this._agvX = 0;
         this._agvY = 0;
         this._agv = null;
    }

    public void update(DateTime time, DateTime interval) {
        // Calculate the max moving distance
        final float craneSpeed = this.isLoaded() ? this._craneSpeedLoaded:this._craneSpeedNotLoaded;
        final float moveDis = (craneSpeed * (float)time.getTotalMilliSeconds()) / 1000f;
        
        // State machine for the crane's movement
        switch(this._state) {
            case 0: { // Inactive
                // move to init position or keep at same position
                break;
            }
            case 1: { // Move to the container position
                if (this._x == this._conX && this._y == this._conY) {
                    this._state = 2;
                    
                    // DON'T BREAK HERE
                } 
                else {
                    // Move x
                    if (this._x < this._conX) {
                        this._x += this.min2(moveDis, this._conX - this._x);
                    }
                    else if(this._x > this._conX) {
                        this._x -= this.min2(moveDis, this._x - this._conX);
                    }

                    // Move y
                    if (this._y < this._conY) {
                        this._y += this.min2(moveDis, this._conY - this._y);
                    }
                    else if(this._y > this._conY) {
                        this._y -= this.min2(moveDis, this._y - this._conY);
                    }
                    
                    // Break
                    break;
                }
            }
            case 2: { // Pick up container
                // Set state
                this._state = 3;

                // Signal the owner of the crane
                this._owner.OnContainerPickUp(this, this._agv.getContainer());
                
                // DON'T BREAK HERE
            }    
            case 3: { // bring container to agv location
                // move to agv location
                if (this._x == this._agvX && this._y == this._agvY) {
                    this._state = 4;
                    
                    // DON'T BREAK HERE
                } 
                else {
                    // Move x
                    if (this._x < this._agvX) {
                        this._x += this.min2(moveDis, this._agvX - this._x);
                    }
                    else if(this._x > this._agvX) {
                        this._x -= this.min2(moveDis, this._x - this._agvX);
                    }

                    // Move y
                    if (this._y < this._agvY) {
                        this._y += this.min2(moveDis, this._agvY - this._y);
                    }
                    else if(this._y > this._agvY) {
                        this._y -= this.min2(moveDis, this._y - this._agvY);
                    }
                    
                    // Break
                    break;
                }
            }
            case 4: { // put container on agv
                // Signal agv
                _agv.agvLoaded();

                // Go back to start state
                this._state = 0; // inactive
                
                // Break
                break;
            }
               
            case 5: { // go to agv location
                // move to agv location
                if (this._x == this._agvX && this._y == this._agvY) {
                    this._state = 6;
                    
                    // DON'T BREAK HERE
                } 
                else {
                    // Move x
                    if (this._x < this._agvX) {
                        this._x += this.min2(moveDis, this._agvX - this._x);
                    }
                    else if(this._x > this._agvX) {
                        this._x -= this.min2(moveDis, this._x - this._agvX);
                    }

                    // Move y
                    if (this._y < this._agvY) {
                        this._y += this.min2(moveDis, this._agvY - this._y);
                    }
                    else if(this._y > this._agvY) {
                        this._y -= this.min2(moveDis, this._y - this._agvY);
                    }
                    
                    // Break
                    break;
                }
            }
            case 6: { // pick up container from agv
                // Set state
                this._state = 7;

                // Signal agv
                this._agv.agvUnloaded();
                
                // DON'T BREAK HERE
            }
            case 7: { // bring container to location
                if (this._x == this._conX && this._y == this._conY) {
                    this._state = 2;
                    
                    // DON'T BREAK HERE
                } 
                else {
                    // Move x
                    if (this._x < this._conX) {
                        this._x += this.min2(moveDis, this._conX - this._x);
                    }
                    else if(this._x > this._conX) {
                        this._x -= this.min2(moveDis, this._x - this._conX);
                    }

                    // Move y
                    if (this._y < this._conY) {
                        this._y += this.min2(moveDis, this._conY - this._y);
                    }
                    else if(this._y > this._conY) {
                        this._y -= this.min2(moveDis, this._y - this._conY);
                    }
                    
                    // Break
                    break;
                }
            }
            case 8: { // put container on position
                // Go back to start state
                this._state = 0; // inactive
                
                // Signal the owner of the crane
                this._owner.OnContainerPutDown(this, this._agv.getContainer());
                
                // Break
                break;
            }
        }        
    }
    
    /**
     * Get the min of two values
     * @param v1 first value
     * @param v2 second value
     * @return the minimum of the two values
     */
    private float min2(float v1, float v2) {
        return (v1<v2) ? v1:v2;
    }
    
}
