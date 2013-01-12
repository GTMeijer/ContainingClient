package projectcontaining.locations.ContainerStack;

import projectcontaining.locations.Cranes.Crane;
import projectcontaining.xmlparser.ContainerData;
import projectcontaining.xmlparser.Point3D;

/**
 * 
 * @author Hielke Hielkema
 */
public class ContainerStack {
   
    // Private fields
    private ContainerData[] _containers;
    private int _size;
    private float _x;
    private float _y;
    private Crane _activeCrane;
    
    // Constants
    final int MAX_HEIGHT = 6;
    
    /**
     * Constructor
     */
    public ContainerStack(float x, float y) {
        // Alloc the container array
        this._containers = new ContainerData[MAX_HEIGHT];
        
        // Set the start size to 0
        this._size = 0;

        // The stack is not waiting for the crane
        this._activeCrane = null;
        
        // Set the location
        this._x = x;
        this._y = y;
    }
    
    /**
     * Check if the given container can be placed ontop of the stack
     * @param container the container to check
     * @return 
     */
    public boolean canPush(ContainerData container) {
        if (_size < MAX_HEIGHT) {
            // Get the top container
            ContainerData topContainer = this.peek();
            
            // Check if the stack is empty or if the time of the top container is greater than the time of the new container
            return _size == 0 || // Empty stack
                   topContainer.getLeaveDateTimeFrom().compareTo(container.getLeaveDateTimeFrom()) == 1; // time top container > time container
        }
        else {
            // Stack is full
            return false;
        }
    }

    /**
     * Push a container to the stack
     * @param container the container to push
     * @throws StackIsFullException when the stack is already full
     */
    public void push(ContainerData container) throws StackIsFullException {
        if (this.canPush(container)) {
            this._containers[this._size++] = container;
        }
        else {
            throw new StackIsFullException();
        }
    }

    /**
     * Check if the stack contains a given container
     * @param container container to check
     * @return true = container found, false = container not found
     */
    public boolean Contains(ContainerData container) {
        for(int i = 0; i < this._size; i++) {
            if (this._containers[i] == container) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get the size of the stack
     * @return the size of the stack
     */
    public int getSize() {
        return this._size;
    }
    
    /**
     * Pop the top value of the stack
     * @return the top value
     */
    public ContainerData pop() {
        if(this._size <= 0) {
            return null;
        }
        else {
            return this._containers[--this._size];
        }
    }
    
    /**
     * Peek the top value of the stack
     * @return the top value
     */
    public ContainerData peek() {
        if(this._size <= 0) {
            return null;
        }
        else {
            return this._containers[this._size-1];
        }
    }
    
    /**
     * Reset the stack
     */
    public void reset() {
        // Set the stack size to 0
        this._size = 0;
        
        // The stack is not waiting for the crane
        this._activeCrane = null;
    }
    
    /**
     * Return the location of the container stack
     * @return 
     */
    public Point3D getLocation() {
        return new Point3D((int)this._x, (int)this._y, 0);
    }
    
    /**
     * Get the crane that is working on the stack
     * @return 
     */
    public Crane getActiveCrane() {
        return this._activeCrane;
    }
    
    /**
     * 
     * @param crane 
     */
    public void setActiveCrane(Crane crane) {
        this._activeCrane = crane;
    }
}
