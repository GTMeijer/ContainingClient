/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectcontaining.Networking.UpdateList;

/**
 *
 * @author Hielke Hielkema
 */
public class ControllerTest {
    
    /**
     * Constructor
     */
    public ControllerTest() { }
    
    /**
     * Test of loadXmlFile method, of class Controller.
     */
    @Test
    public void testLoadXmlFile() {
        System.out.println("loadXmlFile");
        String filePath = "";
        
        UpdateList updateList = new UpdateList();
        
        Controller instance = new Controller(updateList);
        boolean expResult = true;
        boolean result = instance.loadXmlFile(filePath);
        assertEquals(expResult, result);
    }

    /**
     * Test of reset method, of class Controller.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        
        UpdateList updateList = new UpdateList();
        
        Controller instance = new Controller(updateList);
        instance.reset();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
