package projectcontaining.locations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import projectcontaining.AgvSystem.Agv;
import projectcontaining.DateTime;
import projectcontaining.Update;
import projectcontaining.locations.Cranes.Crane;
import projectcontaining.locations.Cranes.CraneOwner;
import projectcontaining.locations.Cranes.CraneRequest;
import projectcontaining.xmlparser.ContainerData;
import projectcontaining.xmlparser.Point3D;

/**
 *
 * @author hielkehielkema93
 */
public class SeaShipPlatform implements Location, Update, CraneOwner {
    
    // Private fields
    private List<ContainerData> _containers;
    private Queue<CraneRequest> _craneRequests;
    private List<Crane> _cranes;
    
    // Constants
    final float CRANE_SPEED_LOADED = 1f;
    final float CRANE_SPEED_NOTLOADED = 1f;
    
    /**
     * Constructor
     */
    public SeaShipPlatform() {
        _containers = new ArrayList();
        _craneRequests = new LinkedList<>();
        _cranes = new ArrayList();
        
        // 10 kranen
        
        // Add the cranes
        _cranes.add(new Crane(this, 0, 0, CRANE_SPEED_LOADED, CRANE_SPEED_NOTLOADED)); // todo
    }
    
    @Override
    public void getContainer(Agv sender, ContainerData container) {
        this.addRequest(sender, container, true);
    }

    @Override
    public void setContainer(Agv sender, ContainerData container) {
        this.addRequest(sender, container, false);
    }
    
    /**
     * Add a crane request
     * @param sender the agv that requested the crane
     * @param container the container to move
     * @param getRequest if it's a get or set request
     */
    private void addRequest(Agv sender, ContainerData container, boolean getRequest) {
        // Get a crane
        Crane crane = this.getInactiveCrane();

        // Check if there is a crane free to use
        if (crane != null) {
            // Activate the crane
            if (getRequest) {
                crane.getContainer(sender, this.getContainerLocation(container));
                this._containers.remove(container);
            }
            else {
                crane.setContainer(sender, this.getContainerLocation(container));
                this._containers.add(container);
            }
        }
        else {
            // Push the request to the queue
            this._craneRequests.add(new CraneRequest(sender, container, getRequest));
        }
    }
    
    /**
     * Get inactive crane
     * @return A free crane of null if all cranes are active
     */
    private Crane getInactiveCrane() {
        for(Crane crane : this._cranes) {
            if (!crane.getActive()) {
                return crane;
            }
        }
        return null;
    }

    /**
     * Get the simulation location of the container
     * @return the location of the container
     */
    private Point3D getContainerLocation(ContainerData container) {
       return container.getLocation(); // TODO
    }
    
    @Override
    public void reset() {
        this._containers.clear();
        this._containers.clear();
        this._craneRequests.clear();
    }

    @Override
    public void update(DateTime time, DateTime interval) {
        // Update the cranes
        for(Crane crane : this._cranes) {
            crane.update(time, interval);
        }
        
        // Process the crane requests queue
        Crane freeCrane = getInactiveCrane();
        while(freeCrane != null && this._craneRequests.peek() != null) {
            // Get the request
            CraneRequest request = this._craneRequests.poll();

            // Execute the request
            if (request.getGetRequest()) {
                freeCrane.getContainer(request.getAgv(), this.getContainerLocation(request.getContainer()));
                this._containers.remove(request.getContainer());
            }
            else {
                freeCrane.setContainer(request.getAgv(), this.getContainerLocation(request.getContainer()));
                this._containers.add(request.getContainer());
            }
            
            // Get the new free crane
            freeCrane = getInactiveCrane();
        }
    }

    @Override
    public List<ContainerData> getContainers() {
        return this._containers;
    }

    @Override
    public void pushArrivedContainers(List<ContainerData> containers) {
        this._containers.addAll(containers);
    }
    
    @Override
    public String GetMapLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void OnContainerPickUp(Crane crane, ContainerData container) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void OnContainerPutDown(Crane crane, ContainerData container) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void OnCraneDone(Crane crane) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
