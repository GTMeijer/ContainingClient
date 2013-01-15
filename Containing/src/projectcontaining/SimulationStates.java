package projectcontaining;

/**
 *
 * @author Hielke Hielkema
 */
public class SimulationStates {

    /**
     * i = index
     * [i][0] = current x
     * [i][1] = current y
     * [i][2] = destination x
     * [i][3] = destination y
     * [i][4] = 1=loaded, 0=not loaded
     */
    public float[][] AgvLocations;
    
    /**
     * i = index
     * [i][0] = x
     * [i][1] = y
     */
    public float[][] StoredContainers;
    
    /** size = [ship count][container for that ship][2]
     * s = ship index
     * c = container index
     * [s][c][0] = container x
     * [s][c][1] = container y
     */
    public float[][][] SeaShipContainers;
    
    /** size = [ship count][container for that ship][2]
     * s = ship index
     * c = container index
     * [s][c][0] = container x
     * [s][c][1] = container y
     */
    public float[][][] RiverBoatContainers;
    
    /**
     * i = index
     * [i][0] = container x
     * [i][1] = container y
     */
    public float[][] TruckContainers;
    
    /**
     * i = index
     * [i] = container x
     */
    public float[][] TrainContainers;
    
    /**
     * i = index
     * [i][0] = current x
     * [i][1] = current y
     * [i][2] = destination x
     * [i][3] = destination y
     * [i][4] = 1=loaded, 0=not loaded
     */
    public float[][] StorageCranes;
    
    /**
     * i = index
     * [i][0] = current x
     * [i][1] = current y
     * [i][2] = destination x
     * [i][3] = destination y
     * [i][4] = 1=loaded, 0=not loaded
     */
    public float[][] TrainCranes;
    
    /**
     * i = index
     * [i][0] = current x
     * [i][1] = current y
     * [i][2] = destination x
     * [i][3] = destination y
     * [i][4] = 1=loaded, 0=not loaded
     */
    public float[][] SeaShipCranes;
    
    /**
     * i = index
     * [i][0] = current x
     * [i][1] = current y
     * [i][2] = destination x
     * [i][3] = destination y
     * [i][4] = 1=loaded, 0=not loaded
     */
    public float[][] TruckCranes;
    
    /**
     * i = index
     * [i][0] = current x
     * [i][1] = current y
     * [i][2] = destination x
     * [i][3] = destination y
     * [i][4] = 1=loaded, 0=not loaded
     */
    public float[][] RiverboatCranes;
}