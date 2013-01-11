/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bdn;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kuchinawa
 */
public class MultipleSocketServer implements Runnable
{

    
    public static void main(String[] args)
    {
        int port = 9999;
        int count = 0;
        
        try
        {
            ServerSocket socket1 = new ServerSocket(port);
            System.out.println("MultipleSocketServer Initialized");
            
            while(true)
            {
                //Setup new socket thread when a new connection is created
                Socket connection = socket1.accept();
                Runnable runnable = new MultipleSocketServer(connection, ++count);
                System.out.println("New Request");
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }
        catch(Exception ex)
        {
            
        }
    }

    public void run()
    {
        try
        {
            //Get send message
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            
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
            
            //Thread.sleep(10000); // Test stuff
            System.out.println(this.ID);
            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            oos.writeObject(returnValue);
            oos.reset();
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
            }
            catch(IOException e)
            {
            }
        }
    }
    
    public List<String> getUpdate()
    {
        List<String> returnList = new ArrayList<String>();
        returnList.add( "1" );
        
        return returnList;
    }
} 
