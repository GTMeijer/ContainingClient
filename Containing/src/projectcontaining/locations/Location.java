package projectcontaining.locations;

import java.util.List;
import projectcontaining.AgvSystem.Agv;
import projectcontaining.xmlparser.ContainerData;

/**
 * A interface for location that can contain containers
 * @author Hielke Hielkema
 */
public interface Location {
    /**
     * Asks the Platform to put the container on the agv and call agv.agvLoaded() when done
     * @param sender the agv that does the request
     * @param container the container to put on the agv
     */
    public void getContainer(Agv sender, ContainerData container);
    
    /**
     * Asks the Platform to pick-up the container from the agv and call agv.agvUnloaded() when done
     * @param sender the agv that does the request
     * @param container the container to pick-up from the agv
     */
    public void setContainer(Agv sender, ContainerData container);

    /**
     * push containers to the locations that are arrived
     * @param containers the containers that arrive
     */
    public void pushArrivedContainers(List<ContainerData> containers);
    
    /**
     * read the containers from the location
     * @return a list containing all containers on the location
     */
    public List<ContainerData> getContainers();
    
    /**
     * Reset all data
     */
    public void reset();
    
    /**
     * Get the name of the point for the location
     * @return 
     */
    public String GetMapLocation();
}