/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages all the client connections.
 * When a new client tries to connect to the server
 * it will makes a new thread on which the network requests run.
 * @author Kuchinawa
 */
public class SocketServer implements Runnable
{
    final int port = 9999;
    ServerSocket socket1;
    static int count = 0;
    
    public SocketServer()
    {
        try
        {
            socket1 = new ServerSocket(port);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try 
            {
                //Setup new socket thread when a new connection is created
                Socket connection = socket1.accept();
                Runnable runnable = new Connection(connection);
                SocketServer.count++;
                System.out.println("New Request");
                Thread thread = new Thread(runnable);
                thread.start();
                
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    

}
