/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import projectcontaining.SimulationStates;

/**
 *
 * @author Gert
 */
public class UpdateListTest {
    
    public UpdateListTest() {
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
     * Test of add method, of class UpdateList.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        //Instantiate the expected list and add a value
        List<SimulationStates> expected = Collections.synchronizedList(new ArrayList<SimulationStates>());
        
        SimulationStates expectedState = new SimulationStates();
        expectedState.AgvLocations[0][0] = 1.0f;
        
        expected.add(expectedState);
        
        //Instantiate test list and add it to the UpdateList through the add method
        SimulationStates[] coordList = { expectedState };
        UpdateList instance = new UpdateList();
        instance.add(coordList);
        
        //Check if the adding was succesfull by comparing the lists
        assertArrayEquals(expected.toArray(), instance.syncList.get(0).toArray());
    }

    /**
     * Test of getUpdate method, of class UpdateList.
     */
    @Test
    public void testGetUpdate() {
        
        System.out.println("getUpdate");
        int updatesRecieved = 0;
        
        UpdateList instance = new UpdateList();

        //Create test data
        SimulationStates testState1 = new SimulationStates();
        testState1.AgvLocations[0][0] = 0.0f;
        SimulationStates testState2 = new SimulationStates();
        testState1.AgvLocations[0][0] = 2.0f;
        SimulationStates testState3 = new SimulationStates();
        testState1.AgvLocations[0][0] = 5.0f;
        
        //Add data to array
        SimulationStates[] expectedStates = new SimulationStates[] { testState1 , testState2, testState3};
        
        //Add with add method
        instance.add(expectedStates);
        
        //Instantiate expected list
        List expResult = Collections.synchronizedList(new ArrayList<List<SimulationStates>>());
        List<SimulationStates> addList = Collections.synchronizedList(new ArrayList<SimulationStates>());

        //Add test data to the expected list
        addList.add(testState1);
        addList.add(testState2);
        addList.add(testState3);
        
        expResult.add(addList);
        
        //Get result and compare with expected
        List result = instance.getUpdate(updatesRecieved);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
}
