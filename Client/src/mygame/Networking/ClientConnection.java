/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * This class handles all the Network Communication 
 * between the Client and the Server.
 * 
 * @author Kuchinawa
 */
public class ClientConnection implements Runnable
{
    //IP and port of the server
    final String host = "127.0.0.1";
    final int port = 9999;
    Socket connection;
    InetAddress address;
    
    List<List<Float>> updateList;
    
    //I/O Streams
    ObjectOutputStream out;
    ObjectInputStream ois;
    
    public ClientConnection(List<List<Float>> updateList)
    {
        try
        {
            this.updateList = updateList;
            
            //Get the IP adress
            address = InetAddress.getByName(host);
            
            //Establish a socket connection
            connection = new Socket(address, port);
            connection.setSoTimeout(5000);
            
            out = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(connection.getInputStream());
            
            System.out.println("Connection Initialized");
        }
        catch(IOException ioEx)
        {
            System.out.println(ioEx);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public void run() {
        while(true)
        {
            try
            {
                //Send update count, synch for thread safety
                synchronized(updateList)
                {
                    out.writeObject(updateList.size());
                }
                
                //Get input and cast it to the right type
                List<List<Float>> newEntries = (List<List<Float>>)ois.readObject();
                
                //Add new entries to the updateList, synch for thread safety
                if(!newEntries.isEmpty())
                {
                    synchronized(updateList)
                    {
                        updateList.addAll(newEntries);
                    }
                }
                
                Date currentDateTime = new Date();
                System.out.println(new Timestamp(currentDateTime.getTime()) + "New update list recieved.");
                
                Thread.sleep(1000);
            }
            catch(IOException ioEx)
            {
                System.out.println(ioEx);
            }
            catch(Exception ex)
            {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
    }
}
