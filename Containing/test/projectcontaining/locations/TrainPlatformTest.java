/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.locations;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectcontaining.AgvSystem.Agv;
import projectcontaining.DateTime;
import projectcontaining.xmlparser.ContainerData;

/**
 *
 * @author hielkehielkema93
 */
public class TrainPlatformTest {
    
    public TrainPlatformTest() {
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
     * Test of getContainer method, of class TrainPlatform.
     */
    @Test
    public void testGetContainer() {
        System.out.println("getContainer");
        Agv sender = null;
        ContainerData container = null;
        TrainPlatform instance = new TrainPlatform();
        instance.getContainer(sender, container);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContainer method, of class TrainPlatform.
     */
    @Test
    public void testSetContainer() {
        System.out.println("setContainer");
        Agv sender = null;
        ContainerData container = null;
        TrainPlatform instance = new TrainPlatform();
        instance.setContainer(sender, container);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class TrainPlatform.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        TrainPlatform instance = new TrainPlatform();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TrainPlatform.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        DateTime time = null;
        DateTime interval = null;
        TrainPlatform instance = new TrainPlatform();
        instance.update(time, interval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContainers method, of class TrainPlatform.
     */
    @Test
    public void testGetContainers() {
        System.out.println("getContainers");
        TrainPlatform instance = new TrainPlatform();
        List expResult = null;
        List result = instance.getContainers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushArrivedContainers method, of class TrainPlatform.
     */
    @Test
    public void testPushArrivedContainers() {
        System.out.println("pushArrivedContainers");
        List<ContainerData> containers = null;
        TrainPlatform instance = new TrainPlatform();
        instance.pushArrivedContainers(containers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
