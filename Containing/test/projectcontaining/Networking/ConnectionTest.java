/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gert
 */
public class ConnectionTest {
    
    public ConnectionTest() {
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
     * Test of run method, of class Connection.
     */
    @Test
    public void testRun() throws InterruptedException {
        System.out.println("run");
        
        UpdateList instanceList = new UpdateList();
        instanceList.add(new Float[] { 1.0f } );
        
        List<List<Float>> testList = new ArrayList<List<Float>>();;

        Connection instance;
        
        try
        {
            instance = new Connection(new Socket("127.0.0.1", 9999), 1, instanceList);
            Thread thread = new Thread(instance);
            thread.start();
            
            Thread.sleep(100);
            
            //Connect with the SocketServer instance
            Socket testConnection = new Socket("127.0.0.1", 9999);
            
            ObjectOutputStream oos = new ObjectOutputStream(testConnection.getOutputStream());
            ObjectInputStream oin = new ObjectInputStream(testConnection.getInputStream());
            
            oos.writeObject(0);
            //Get input and cast it to the right type
            List<List<Float>> newEntries = (List<List<Float>>)oin.readObject();
            testList.addAll(newEntries);
            
        }
        catch (IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(ConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("exception");
        }
        
        assertTrue("Values did not successfully add", testList.size() == 1);
    }
}
