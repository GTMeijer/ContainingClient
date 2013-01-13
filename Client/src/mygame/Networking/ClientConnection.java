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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class handles all the Network Communication 
 * between the Client and the Server.
 * @author Kuchinawa
 */
public class ClientConnection
{
    //IP and port of the server
    final String host = "127.0.0.1";
    final int port = 9999;
    Socket connection;
    InetAddress address;
    
    //I/O Streams
    ObjectOutputStream out;
    ObjectInputStream ois;
    
    public ClientConnection()
    {
        try
        {
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
    
    public List<List<Float>> getServerUpdate(int updatesRecieved)
    {
        try
        {
            //Send update count
            out.writeObject(updatesRecieved);
            
            //Get input and cast it to the right type
            List<List<Float>> updatedList = (List<List<Float>>)ois.readObject();

            Date currentDateTime = new Date();
            
            System.out.println(new Timestamp(currentDateTime.getTime()) + "New update list recieved.");
            
            return updatedList;
        }
        catch(IOException ioEx)
        {
            System.out.println(ioEx);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return new ArrayList<List<Float>>();
    }
}
