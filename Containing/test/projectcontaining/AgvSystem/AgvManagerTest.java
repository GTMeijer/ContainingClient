/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.AgvSystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectcontaining.DateTime;

/**
 *
 * @author hielkehielkema93
 */
public class AgvManagerTest {
    
    public AgvManagerTest() {
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
     * Test of addRequest method, of class AgvManager.
     */
    @Test
    public void testAddRequest() {
        System.out.println("addRequest");
        AgvRequest request = null;
        AgvManager instance = new AgvManager();
        instance.addRequest(request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class AgvManager.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        AgvManager instance = new AgvManager();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkQueue method, of class AgvManager.
     */
    @Test
    public void testCheckQueue() {
        System.out.println("checkQueue");
        AgvManager instance = new AgvManager();
        //instance.checkQueue();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class AgvManager.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        DateTime time = null;
        DateTime interval = null;
        AgvManager instance = new AgvManager();
        instance.update(time, interval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
