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
        List<Float> expected = Collections.synchronizedList(new ArrayList<Float>());
        expected.add(0.0f);
        
        //Instantiate test list and add it to the UpdateList through the add method
        Float[] coordList = { 0.0f };
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
        int updatesRecieved = 2;
        
        UpdateList instance = new UpdateList();
        instance.add( new Float[] { 0.0f , 1.0f, 2.0f } );
        instance.add( new Float[] { 2.0f , 3.0f, 4.0f } );
        instance.add( new Float[] { 5.0f , 2.0f, 1.0f } );
        
        //List<List<Float>> syncList;
        List expResult = Collections.synchronizedList(new ArrayList<List<Float>>());
        List<Float> addList = Collections.synchronizedList(new ArrayList<Float>());
        addList.add(5.0f);
        addList.add(2.0f);
        addList.add(1.0f);
        
        expResult.add(addList);
        
        
        List result = instance.getUpdate(updatesRecieved);
        assertEquals(expResult, result);
    }
}
