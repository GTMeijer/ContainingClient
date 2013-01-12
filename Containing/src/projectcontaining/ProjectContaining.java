package projectcontaining;

import projectcontaining.Networking.SocketServer;

/**
 *
 * @author hielkehielkema93
 */
public class ProjectContaining {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Runnable serverThread = new SocketServer();
        Thread server = new Thread(serverThread);
        server.start();
        
        Controller contr = new Controller();
        
        contr.test();
    }
}
