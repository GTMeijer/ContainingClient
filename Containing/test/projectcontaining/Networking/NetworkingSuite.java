/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.Networking;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Gert
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({projectcontaining.Networking.UpdateListTest.class, projectcontaining.Networking.SocketServerTest.class, projectcontaining.Networking.ConnectionTest.class})
public class NetworkingSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
