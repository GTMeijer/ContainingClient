/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Gert
 */
public class SocketServerTest {
    
    public SocketServerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class SocketServer.
     */
    @Test
    public void testRun() throws InterruptedException {
        System.out.println("run");
        
        UpdateList testList = new UpdateList();
        
        //Instantiate class and thread
        SocketServer instance = new SocketServer(testList);
        Thread testThread = new Thread(instance);
        testThread.start();
        
        try
        {
            //Connect with the SocketServer instance
            Socket connection = new Socket("127.0.0.1", 9999);
        } 
        catch (UnknownHostException ex) 
        {
            fail(ex.getMessage());
        } 
        catch (IOException ex) 
        {
            fail(ex.getMessage());
        }

        //Sleep so the thread can update
        Thread.sleep(100);
        
        //Check if the connection was created by comparing the connection count with 1
        assertEquals(1, SocketServer.count);
    }
}
