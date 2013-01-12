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

/**
 * This class handles a single client thread.
 * @author Kuchinawa
 */
public class Connection implements Runnable{
    
    
    private int ID;
    Socket connection;
    
    Connection(Socket connection)
    {
        this.connection = connection;
    }
    
    
    @Override
    public void run() {
        try
        {
            //Get send message
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            
            //Main loop that keeps checking for a client request
            while(true)
            {
                //Set called Class and Method
                String className = (String)in.readObject();
                String methodName = (String)in.readObject();

                //Parameters
                Class[] methodParamTypes = (Class[])in.readObject();
                Object[] methodParams = (Object[])in.readObject();

                //Call the Class and Method
                Class cl = Class.forName(className);

                Method meth;
                Object returnValue;

                if(methodParamTypes != null)
                {
                    meth = cl.getDeclaredMethod(methodName, methodParamTypes);
                    //Store the returnValue
                    returnValue = meth.invoke(this, methodParams);
                }
                else
                {
                    meth = cl.getDeclaredMethod(methodName);
                    //Store the returnValue
                    returnValue = meth.invoke(this);
                }

                Thread.sleep(100); // Test stuff

                System.out.println(this.ID);
                

                oos.writeObject(returnValue);
                oos.flush();
            }
        }
        catch(ClassNotFoundException | NoSuchMethodException | IOException | IllegalAccessException | InvocationTargetException ex)
        {
            System.out.println(ex);
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
                SocketServer.count--;
            }
            catch(IOException e)
            {
            }
        }
    }
    
    
    /* TEST */
    public List<String> getUpdate()
    {
        List<String> returnList = new ArrayList<String>();
        returnList.add( "1" );
        
        return returnList;
    }
}
