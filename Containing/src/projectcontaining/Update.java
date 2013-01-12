package projectcontaining;

/**
 * An interface for updates
 * @author hielkehielkema93
 */
public interface Update {
    
    /**
     * A method called when the object needs to update
     * @param time the current time
     * @param interval the interval since the last update
     */
    public void update(DateTime time, DateTime interval);
}
