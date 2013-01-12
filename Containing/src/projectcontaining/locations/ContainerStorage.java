package projectcontaining.locations;

import java.util.ArrayList;
import java.util.List;
import projectcontaining.AgvSystem.Agv;
import projectcontaining.DateTime;
import projectcontaining.Update;
import projectcontaining.locations.ContainerStack.ContainerStack;
import projectcontaining.locations.ContainerStack.ContainerStorageRequestQueue;
import projectcontaining.locations.ContainerStack.StackIsFullException;
import projectcontaining.locations.ContainerStack.StoredContainerRequest;
import projectcontaining.locations.Cranes.Crane;
import projectcontaining.locations.Cranes.CraneOwner;
import projectcontaining.xmlparser.ContainerData;

/**
 * A class representing the container storage place
 * @author Hielke Hielkema
 */
public class ContainerStorage implements Location, Update, CraneOwner {
    
    // Private fields
    private ContainerStack[][][] _stacks;            // 3d-array of container stacks
    private List<ContainerData> _containers;         // List of all containers
    private Crane[] _cranes;                         // Array for the 6 cranes
    private ContainerStorageRequestQueue _queue;     // The crane request queue
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    // Constants
    
    final int STRIPS = 10;
    final int WIDTH = 6; // in containers (also crane count)
    final int LENGTH = 20; // in containers
    final int CONTAINER_WIDTH = 5; // in m
    final int CONTAINER_LENGTH = 14; // in m
    final int DISTANCE_BETWEEN_STACKS_X = 10; // in m
    final int DISTANCE_BETWEEN_STACKS_Y = 10; // in m
    final float CRANE_SPEED_LOADED = 1f; // in m/s
    final float CRANE_SPEED_NOTLOADED = 1f; // in m/s
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    /**
     * Constructor
     */
    public ContainerStorage() {
        // Load the stacks
        this._stacks = new ContainerStack[STRIPS][WIDTH][LENGTH];
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < LENGTH; y++) {
                for(int s = 0; s < STRIPS; s++) {
                    // Calculate the x and y for the stack
                    float stackX = 0f;
                    float stackY = 0f;

                    // Create the stack
                    this._stacks[s][x][y] = new ContainerStack(stackX, stackY);
                }
            }
        }
        
        // Load the cranes
        this._cranes = new Crane[WIDTH];
        for(int i = 0; i < STRIPS; i++) {
            // Calculate the x and y for the crane
            float craneX = 0f;
            float craneY = 0f;
            
            // Create the crane
            this._cranes[i] = new Crane(this, craneX, craneY, CRANE_SPEED_LOADED, CRANE_SPEED_NOTLOADED);
        }
        
        // Init the request queue
        this._queue = new ContainerStorageRequestQueue();
        
        // Create the container list
        this._containers = new ArrayList<>();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Move Container orders">
    /**
     * Get a container from the storage place
     * @param sender
     * @param container
     */
    @Override
    public void getContainer(Agv sender, ContainerData container) {
        // Step 1. find the row of the container
        int strip = this.getStripForContainer(container);
        if (strip != -1) {
            if (this._cranes[strip].getActive()) {
                this._queue.pushGetRequest(sender, strip);
            }
            else {
                // Find the stack
                for(int y = 0; y < LENGTH; y++) {
                    for(int x = 0; x < WIDTH; x++) {
                        if (this._stacks[strip][x][y].peek() == container) {
                            this._cranes[strip].getContainer(sender, this._stacks[strip][x][y].getLocation());
                            this._stacks[strip][x][y].setActiveCrane(this._cranes[strip]);
                            return;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Find the row of a given container
     * @param container
     * @return 
     */
    private int getStripForContainer(ContainerData container) {
        for(int s = 0; s < STRIPS; s++) {
            for(int y = 0; y < LENGTH; y++) {
                for(int x = 0; x < WIDTH; x++) {
                    if (this._stacks[s][x][y].peek() == container) {
                        return s;
                    }
                }
            }
        }
        return -1;
    }
    
    /**
     * Put a container on the storageplace
     * @param sender
     * @param container
     */
    @Override
    public void setContainer(Agv sender, ContainerData container) {
        // Find a stack for the container
        for(int s = 0; s < STRIPS; s++) {
            if (!this._cranes[s].getActive()) {
                for(int x = 0; x < WIDTH; x++) {
                    for(int y = 0; y < LENGTH; y++) {
                        if (this._stacks[s][x][y].canPush(container)) {
                            this._cranes[s].setContainer(sender, this._stacks[s][x][y].getLocation());
                            this._stacks[s][x][y].setActiveCrane(this._cranes[s]);
                            return;
                        }
                    }
                }
            }
        }
        
        // No place to put the container, put it in the queue
        this._queue.pushSetRequest(sender);
    }
    
    @Override
    public void OnContainerPickUp(Crane crane, ContainerData container) {
         // Get the stack for the crane
        ContainerStack stack = this.getStackFromCrane(crane);
        
        // Check if the stack was found
        if (stack != null) {
            if(stack.pop() != container) {
                // Error
            }
        }
    }
    
    @Override
    public void OnContainerPutDown(Crane crane, ContainerData container) {
        // Get the stack for the crane
        ContainerStack stack = this.getStackFromCrane(crane);
        
        // Check if the stack was found
        if (stack != null) {
            try {
                // Push the container on the stack
                stack.push(container);
            }
            catch(StackIsFullException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    @Override
    public void OnCraneDone(Crane crane) {
        // Look for requests in the queue
        int strip = this.getStripForCrane(crane);
        StoredContainerRequest request = this._queue.getTopGetRequestForStrip(strip);
        
        // Check if there was a get request
        if (request != null) {
            // Find the stack
            for(int x = 0; x < WIDTH; x++) {
                for(int y = 0; y < LENGTH; y++) {
                    if (this._stacks[strip][x][y].peek() == request.getAgv().getContainer()) {
                        this._cranes[strip].getContainer(request.getAgv(), this._stacks[strip][x][y].getLocation());
                        this._stacks[strip][x][y].setActiveCrane(this._cranes[strip]);
                        return;
                    }
                }
            }
            
            // Push the request back because the container is not on top
            this._queue.pushGetRequest(request.getAgv(), strip);
        }
        
        // No get requests found, get a set request from the stack
        request = this._queue.getTopSetRequest();
        
        // Prevent endless loop
        int runs = this._queue.getSize();
        
        // Loop until the crane finds a container to set
        while(request != null && runs-- > 0) {
            // Check if there was a set request
            if (request != null) {
                for(int x = 0; x < WIDTH; x++) {
                    for(int y = 0; y < LENGTH; y++) {
                        if (this._stacks[strip][x][y].canPush(request.getAgv().getContainer())) {
                            this._cranes[strip].setContainer(request.getAgv(), this._stacks[strip][x][y].getLocation());
                            this._stacks[strip][x][y].setActiveCrane(this._cranes[strip]);
                            return;
                        }
                    }
                }
                
                // Push the request back because the container is not on top
                this._queue.pushSetRequest(request.getAgv());
                
                // Get a new request
                request = this._queue.getTopSetRequest();
            }
        }
    }
    
    private ContainerStack getStackFromCrane(Crane crane) {
        for(int s = 0; s < STRIPS; s++) {
            if (this._cranes[s] == crane)
            {
                for(int x = 0; x < WIDTH; x++) {
                    for(int y = 0; y < LENGTH; y++) {
                        if (this._stacks[s][x][y].getActiveCrane() == crane) {
                            return this._stacks[s][x][y];
                        }
                    }
                }
                break;
            }
        }
        return null; // not found
    }
    
    private int getStripForCrane(Crane crane) {
        for(int s = 0; s < STRIPS; s++) {
            if (this._cranes[s] == crane) {
                return s;
            }
        }
        return -1;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Other methods">
    /**
     * Reset the container storage system
     */
    @Override
    public void reset() {
        // Reset stacks
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < LENGTH; y++) {
                for(int s = 0; s < STRIPS; y++) {
                    this._stacks[s][x][y].reset();
                }
            }
        }
        
        // Reset container list
        this._containers.clear();
        
        // Reset the cranes
        for(int x = 0; x < STRIPS; x++) {
            this._cranes[x].reset();
        }
        
        // Reset the queue
        this._queue.reset();
    }
    
    /**
     * Update
     * @param time current time
     * @param interval time since last update
     */
    @Override
    public void update(DateTime time, DateTime interval) {
        
        // Update the cranes
        for(int i = 0; i < STRIPS; i++) {
            this._cranes[i].update(time, interval);
        }
    }
    
    /**
     * Get all containers on the storage place
     * @return
     */
    @Override
    public List<ContainerData> getContainers() {
        return this._containers;
    }
    
    // NOT USED IN THIS LOCATION
    @Override
    public void pushArrivedContainers(List<ContainerData> containers) { }
    
    /**
     * Get the location of the storage place
     * @return
     */
    @Override
    public String GetMapLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>

}