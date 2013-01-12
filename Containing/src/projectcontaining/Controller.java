package projectcontaining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import projectcontaining.AgvSystem.AgvManager;
import projectcontaining.AgvSystem.AgvRequest;
import projectcontaining.locations.*;
import projectcontaining.xmlparser.ContainerData;
import projectcontaining.xmlparser.XmlFileParser;

/**
 * The controller
 * @author Hielke Hielkema
 */
public class Controller {
    
    // The locations
    private Location _seaShipPlatform;
    private Location _trainPlatform;
    private Location _riverBoatPlatform;
    private Location _truckPlatform;
    private ContainerStorage _containerStorage;
    
    // The container list
    private List<ContainerData> _containers;
    private List<ContainerData> _containersNotArrived;
    
    // The agv system
    private AgvManager _agvManager;
    
    // Timing
    DateTime _currentDateTime;
    DateTime _lastDateTime;
    Timer _updateTimer;
    private Stack<DateTime> _arriveTimeStack;
    
    /**
     * Constructor
     */
    public Controller() {
        // Initalize parts
        this._seaShipPlatform = new SeaShipPlatform();
        this._trainPlatform = new TrainPlatform();
        this._riverBoatPlatform = new RiverBoatPlatform();
        this._truckPlatform = new TruckPlatform();
        this._containerStorage = new ContainerStorage();
        
        // agv system
        this._agvManager = new AgvManager();

        // Initialize date and time
        _currentDateTime = new DateTime();
    }
    
    /**
     * temp test method
     */
    public void test() {

        String filePath = "C:\\Users\\Gebruiker\\Desktop\\xml7.xml";
        
        if(this.loadXmlFile(filePath)) {
            try {
                this.startSimulation();
            }
            catch (Exception ex) { 
                System.out.println(ex.getMessage()); 
            }
            System.out.print("done");
        }
        else {
            System.out.print("Error");
        }
    }
    
    /**
     * 
     * @param filePath 
     */
    public boolean loadXmlFile(String filePath) {
        // Reset all variables
        this.reset();
        
        // Try to load the containers from the xml file
        this._containers = XmlFileParser.parseXmlFile(filePath);
        
        // Check if the loading worked
        if (this._containers == null) {
            return false;
        }
        
        // Sort the list
        this.sortContainers(this._containers);
        
        // Copy the containers list
        this._containersNotArrived = new ArrayList(this._containers);
        
        // Init the arrive time stack
        _arriveTimeStack = new Stack<>();
        List<DateTime> times = this.getSortedArriveDateTimeList(this._containers);
        
        // Set the start time
        this._currentDateTime = times.get(0);
        this._lastDateTime = this._currentDateTime;
        
        // Push the times on the stack
        Collections.reverse(times);
        for(DateTime time : times) {
            this._arriveTimeStack.push(time);
        }
        
        // Done
        return true;
    }
    
    /**
     * Start the simulation
     */
    public void startSimulation() throws InterruptedException {
        while(true) {
            // Copy the current time to the last time
            this._lastDateTime = this._currentDateTime;
            
            // The update time
            final int inc_hours = 1;
            final int inc_minutes = 0;
            final int inc_seconds = 0;
            
            // Increase the current time
            this._currentDateTime = this._currentDateTime.add(new DateTime(inc_hours, inc_minutes, inc_seconds,0));
            
            // Calculate the interval
            final DateTime interval = this._currentDateTime.sub(this._lastDateTime);
            
            // Update and chcek if it's done
            if(!this.update(interval)) {
                break;
            }
               
            // Sleep
            Thread.sleep(100);
        }
    }
    
    /**
     * Stop the simulation
     */
    public void stopSimulation() {
        
    }
    
    /**
     * Update all parts of the system
     * @param time current time
     * @param interval interval since last update
     */
    private boolean update(DateTime interval) {
        
        // End condition
        if (this._arriveTimeStack.empty()) {
            return false; // end
        }
        
        // Check for new arrived boats, trains etc
        if(!this._arriveTimeStack.empty() && this._currentDateTime.compareTo(this._arriveTimeStack.peek()) >= 0) {
            this._arriveTimeStack.pop();
            this.updateArrived();
        }
        
        // Update agv's
        this._agvManager.update(this._currentDateTime, interval);
        
        // Update locations
        ((Update)this._seaShipPlatform)  .update(this._currentDateTime, interval);
        ((Update)this._riverBoatPlatform).update(this._currentDateTime, interval);
        ((Update)this._trainPlatform)    .update(this._currentDateTime, interval);
        ((Update)this._truckPlatform)    .update(this._currentDateTime, interval);
        
        // Update storage
        ((Update)this._containerStorage).update(this._currentDateTime, interval);
        
        // Check the storage
        checkStorageContainerDate();
        
        // Continue
        return true;
    }
    
    /**
     * Check for containers that are arrived
     */
    private void updateArrived() {
        
        // Create lists for the locations (storage not)
        final List<ContainerData> seaShipArrived = new ArrayList<>();
        final List<ContainerData> trainArrived = new ArrayList<>();
        final List<ContainerData> riverBoatArrived = new ArrayList<>();
        final List<ContainerData> truckArrived = new ArrayList<>();
        
        // Search for arrived containers and sort them
        for(int i = 0; i < this._containersNotArrived.size(); i++) {
            ContainerData container = this._containersNotArrived.get(i);
            if(container.getEnterDateTimeFrom().compareTo(this._currentDateTime) < 1) {
                switch(container.getEnterTransportType()) {
                    case "zeeschip":
                        seaShipArrived.add(container);
                        break;
                    case "trein":
                        trainArrived.add(container);
                        break;
                    case "binnenschip":
                        riverBoatArrived.add(container);
                        break;
                    case "vrachtauto":
                        truckArrived.add(container);
                        break;
                    default:
                        break;
                }
                this._containersNotArrived.remove(i--);
            }  
        }
        
        System.out.print(String.format("Containers arrived: sea:%d, train:%d, riverboar:%d, truck:%d\n", seaShipArrived.size(), trainArrived.size(), riverBoatArrived.size(), truckArrived.size()));
        
        // Push the arrived containers
        if (seaShipArrived.size()   > 0) { this._seaShipPlatform.pushArrivedContainers(seaShipArrived);     }
        if (trainArrived.size()     > 0) { this._trainPlatform.pushArrivedContainers(trainArrived);         }
        if (riverBoatArrived.size() > 0) { this._riverBoatPlatform.pushArrivedContainers(riverBoatArrived); }
        if (truckArrived.size()     > 0) { this._truckPlatform.pushArrivedContainers(truckArrived);         }
        
        // Process the containers
        this.processContainers(seaShipArrived);
        this.processContainers(trainArrived);
        this.processContainers(riverBoatArrived);
        this.processContainers(truckArrived);
    }
    
    /**
     * Process a list of containers
     * @param containers 
     */
    private void processContainers(List<ContainerData> containers) {
        // Process all containers in the list
        for(ContainerData container : containers) {
            // Get the leave DateTime
            DateTime leaveDateTime = container.getLeaveDateTimeFrom();
          
            // Compare the leave DateTime and the current DateTime
            int comp = leaveDateTime.compareTo(this._currentDateTime);
            
            // Check if the date is now or after now
            if (comp < 1) { 
                // Move container to the destination
                
                // Get the locations
                Location start = this.getLocationFromString(container.getEnterTransportType());
                Location end = this.getLocationFromString(container.getLeaveTransportType());
                
                // Add the agv request
                AgvRequest request = new AgvRequest(start, end, container, this._agvManager);
                this._agvManager.addRequest(request);
            }
            else { 
                // Store the container
                
                // Get the locations
                Location start = this.getLocationFromString(container.getEnterTransportType());
                Location end = this._containerStorage;
                
                // Add the agv request
                AgvRequest request = new AgvRequest(start, end, container, this._agvManager);
                this._agvManager.addRequest(request);
            }
        }
    }
    
    /**
     * Sort the containers depending on there position and leave time
     * @param containers 
     */
    private void sortContainers(List<ContainerData> containers) {
        ContainerOrderSorter.sortContainers(containers);
    }
  
     /**
      * Get a sorted list of all arrive times
      * @param containers the containers to get the times from
      * @return 
      */
     private List<DateTime> getSortedArriveDateTimeList(List<ContainerData> containers) {
        // Create 
        List<DateTime> dateTimes = new ArrayList();
        
        // Find all unique DateTimes
        for(ContainerData container : containers) {
            DateTime dateTime = container.getEnterDateTimeFrom();
            boolean found = false;
            int len = dateTimes.size();
            for(int i = 0; i < len; i++) {
                if (dateTimes.get(i).compareTo(dateTime) == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                dateTimes.add(dateTime);
            }
        }
        
        // Sort the list
        Collections.sort(dateTimes);
        
        // Return the result
        return dateTimes;
    }
    
    /**
     * Reset the system
     */
    public void reset() {
        // Reset all agv's
        this._agvManager.reset();
        
        // Reset all locations
        this._seaShipPlatform.reset();
        this._trainPlatform.reset();
        this._riverBoatPlatform.reset();
        this._truckPlatform.reset();
        this._containerStorage.reset();
    }
    
    /**
     * Check for containers that need to be moved from the storage
     */
    private void checkStorageContainerDate() {
        // Get the stored containers
        List<ContainerData> storedContainers = this._containerStorage.getContainers();
        
        // Check all containers in storage
        int len = storedContainers.size();
        for(int i = 0; i < len; i++) {
            // Get the leave date and time
            DateTime leaveDateTime = storedContainers.get(i).getLeaveDateTimeFrom();
            
            // Compare the DateTimes
            int comp = leaveDateTime.compareTo(this._currentDateTime);    
          
            // Check if the date is now or after now
            if (comp <= 0) {
                // Get the end location
                Location endLocation = this.getLocationFromString(storedContainers.get(i).getLeaveTransportType());
                
                // Create and add the request
                AgvRequest request = new AgvRequest(this._containerStorage, endLocation, storedContainers.get(i), this._agvManager);
                this._agvManager.addRequest(request);
            }
        }
    }
    
    /**
     * Get the location for a location string
     * @param s trein | vrachtauto | binnenschip | zeeschip
     * @return 
     */
    private Location getLocationFromString(String s) {
        switch(s) {
            case "trein":
                return this._trainPlatform;
            
            case "vrachtauto":
                return this._truckPlatform;
            
            case "binnenschip":
                return this._riverBoatPlatform;
                
            case "zeeschip":
                return this._seaShipPlatform;
                
            default:
                return null;
        }
    }
}
