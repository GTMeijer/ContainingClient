package projectcontaining;

import javax.xml.bind.annotation.XmlElementDecl;
import org.omg.CORBA.TIMEOUT;
import projectcontaining.Networking.SocketServer;
import projectcontaining.Networking.UpdateList;

/**
 *
 * @author hielkehielkema93
 */
public class ProjectContaining {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        UpdateList updateList = new UpdateList();
        
        Runnable serverThread = new SocketServer(updateList);
        Thread server = new Thread(serverThread);
        server.start();
        
        Controller contr = new Controller(updateList);
        
        contr.test();
    }
}