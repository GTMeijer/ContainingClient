package projectcontaining.locations.ContainerStack;

/**
 *
 * @author Gebruiker
 */
public class StackIsFullException extends Exception {
    
    public StackIsFullException() {
        super("Container can not be pushed because the stack is full.");
    }
    
}
