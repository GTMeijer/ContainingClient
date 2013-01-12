package projectcontaining;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * An unit-test for DateTime
 * @author Hielke Hielkema
 */
public class DateTimeTest {
    
    /**
     * Constructor
     */
    public DateTimeTest() { }
    
    /**
     * Test of compareDateTo method, of class DateTime.
     */
    @Test
    public void testCompareDateTo() {
        // Print current text
        System.out.println("compareDateTo");
        
        // Case 1
        if((new DateTime(0, 0, 0, 0, 5, 7, 2012)).compareDateTo(
            new DateTime(0, 0, 0,  0, 6, 8, 2012)) != -1) {

            fail("compareDateTo does not return the excepted value. (case 1).");
        }
        
        // Case 2
        if((new DateTime(0, 0, 0, 0, 5, 7, 2012)).compareDateTo(
            new DateTime(0, 0, 0, 0, 5, 7, 2012)) != 0) {

            fail("compareDateTo does not return the excepted value. (case 2).");
        }
        
        // Case 3
        if((new DateTime(0, 0, 0, 0, 5, 7, 2012)).compareDateTo(
            new DateTime(0, 0, 0, 0, 6, 8, 2011)) != 1) {

            fail("compareDateTo does not return the excepted value. (case 3).");
        }
    }

    /**
     * Test of compareTimeTo method, of class DateTime.
     */
    @Test
    public void testCompareTimeTo() {
        // Print current text
        System.out.println("compareTimeTo");
        
        // Case 1
        if((new DateTime(5, 0, 0, 0, 0, 0, 2012)).compareTimeTo(
            new DateTime(6, 0, 0, 0, 0, 0, 2012)) != -1) {

            fail("compareTimeTo does not return the excepted value. (case 1).");
        }
        
        // Case 2
        if((new DateTime(16, 30, 6, 5, 0, 0, 2012)).compareTimeTo(
            new DateTime(16, 30, 6, 5, 0, 0, 2012)) != 0) {

            fail("compareTimeTo does not return the excepted value. (case 2).");
        }
        
        // Case 3
        if((new DateTime(6, 7, 0, 0, 5, 7, 2012)).compareTimeTo(
            new DateTime(6, 6, 0, 0, 6, 8, 2012)) != 1) {

            fail("compareTimeTo does not return the excepted value. (case 3).");
        }
    }

    /**
     * Test of compareTo method, of class DateTime.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        
        // Case 1
        if((new DateTime(5, 7, 1, 5, 8, 5, 2012)).compareTo(
            new DateTime(6, 4, 2, 3, 8, 8, 2012)) != -1) {

            fail("compareTo does not return the excepted value. (case 1).");
        }
        
        // Case 2
        if((new DateTime(16, 30, 6, 5, 0, 0, 2012)).compareTo(
            new DateTime(16, 30, 6, 5, 0, 0, 2012)) != 0) {

            fail("compareTo does not return the excepted value. (case 2).");
        }
        
        // Case 3
        if((new DateTime(6, 7, 0, 0, 5, 9, 2012)).compareTo(
            new DateTime(6, 6, 0, 0, 6, 8, 2012)) != 1) {

            fail("compareTo does not return the excepted value. (case 3).");
        }
    }

    /**
     * Test of equals method, of class DateTime.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        // Case 1
        if(!(new DateTime(6, 4, 1, 5, 8, 5, 2012)).equals(
             new DateTime(6, 4, 1, 5, 8, 5, 2012))) {

            fail("compareTo does not return the excepted value. (case 1).");
        }
        
        // Case 2
        if((new DateTime(6, 4, 1, 5, 8, 5, 2012)).equals(
            new DateTime(6, 4, 5, 5, 8, 5, 2012))) {

            fail("compareTo does not return the excepted value. (case 2).");
        }
    }

    /**
     * Test of sub method, of class DateTime.
     */
    @Test
    public void testSub() {
        System.out.println("sub");
        
        // Initialize
        DateTime time1 = new DateTime(5, 30, 7, 0, 5, 11, 1993);
        DateTime time2 = new DateTime(5, 23, 5, 8, 3, 2, 0);
        
        // Get result
        DateTime result = time1.sub(time2);
        
        // Create expected
        DateTime expected = new DateTime(0, 7, 1, 992, 2, 9, 1993);
        
        // Compare
        if (!result.equals(expected)) {
            System.out.println(result.toString() + ", " + expected.toString());
            fail("add does not return the excepted value. (case 1).");
        }
    }

    /**
     * Test of add method, of class DateTime.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        // Initialize
        DateTime time1 = new DateTime(5, 30, 0, 0, 5, 11, 1993);
        DateTime time2 = new DateTime(5, 35, 5, 8, 3, 2, 0);
        
        // Get result
        DateTime result = time1.add(time2);
        
        // Create expected
        DateTime expected = new DateTime(11, 5, 5, 8, 8, 1, 1994);
        
        // Compare
        if (!result.equals(expected)) {
            System.out.println(result.toString() + ", " + expected.toString());
            fail("add does not return the excepted value. (case 1).");
            
        }
    }

    /**
     * Test of toString method, of class DateTime.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        // Initialize
        final DateTime time = new DateTime(6, 45, 1, 0, 5, 7, 2012);
        
        // Run the method
        final String result = time.toString();
        
        // Get expected
        final String expected = "[5-7-2012 at 6:45:1.0]";
        
        // Compare values
        if (!result.equals(expected)) {
            fail("toString does not return the excepted value. (case 1).");
        }
    }
}
