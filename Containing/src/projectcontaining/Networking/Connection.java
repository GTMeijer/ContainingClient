/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * This class handles a single client thread.
 * @author Kuchinawa
 */
public class Connection implements Runnable
{
    
    
    private int ID;
    Socket connection;
    
    UpdateList updateList;
    
    Connection(Socket connection, int ID, UpdateList updateList)
    {
        this.connection = connection;
        this.ID = ID;
        this.updateList = updateList;
    }
    
    @Override
    public void run() 
    {
        try
        {
            //Get send message
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            
            //Main loop that keeps checking for a client request
            while(true)
            {
                //Get Input
                int recieved = (Integer)in.readObject();
                
                Date currentDateTime = new Date();
                
                System.out.println(new Timestamp(currentDateTime.getTime()) + " Request from client: " + this.ID);
                
                //Send Output
                List<List<Float>> returnList = getUpdate(recieved);
                
                oos.writeObject(returnList);
                
                
                System.out.println(new Timestamp(currentDateTime.getTime()) + " Reply sent to client: " + this.ID);
                
                //Thread.sleep(100); // Test stuff

                
                
                oos.flush();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch(IOException e)
            {
            }
        }
    }
    
    private List<List<Float>> getUpdate(int updatesRecieved)
    {
        //Copy List for thread safety
        List<List<Float>> returnList = updateList.getUpdate(updatesRecieved);
        return returnList;
    }
}
