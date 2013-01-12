package projectcontaining.xmlparser;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * An unit-test for the XmlFileParser
 * @author Hielke Hielkema
 */
public class XmlFileParserTest {
    
    /**
     * Constructor
     */
    public XmlFileParserTest() { }

    /**
     * Test of parseXmlFile method, of class XmlFileParser.
     */
    @Test
    public void testParseXmlFile() {
        System.out.println("parseXmlFile");
        
        // Create a path for the xml file
        final String path = System.getProperty("user.dir") + "\\XML\\xml7.xml";

        // An int to store the expected size of the result collection
        final int expectedSize = 0;
        
        // Get the result
        final List result = XmlFileParser.parseXmlFile(path);
        
        // Check if null
        if(result == null) {
            fail("Parsing \"" + path + "\" failed");
        }
        
        // Check the size
        if(result.size() != expectedSize) {
            fail("The size of the result from parseXmlFile does not match the expected size.");
        }
    }
}
