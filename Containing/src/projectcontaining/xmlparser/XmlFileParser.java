package projectcontaining.xmlparser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Gebruiker
 */
public class XmlFileParser {
    
    public static List<ContainerData> parseXmlFile(String path) {
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            
            // Create a list to store the container data
            List<ContainerData> containers = new ArrayList<>();

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            Document  doc = db.parse(path);

            // Get the root element
            Element root  = doc.getDocumentElement();
            
            // Get the element count
            int lenght = root.getChildNodes().getLength();
            
            // Loop though the elements, parse them and add them to the list
            for(int i = 0; i < lenght; i++) {
                
                // Check the element type
                if(root.getChildNodes().item(i).getNodeName().equals("record")) {
    
                    // Try to parse the container
                    ContainerData container = ContainerData.fromXml(root.getChildNodes().item(i));

                    // Add the container if the parsing succeeded 
                    if (container != null) {
                        containers.add(container);
                    }
                }
            }

            // Return the list
            return containers;
                
        }
        catch(Exception ex) {
            print("Parsing error: " + ex.getMessage());
            return null;
        }
    }    
    
    private static void print(String str) {
        System.out.print(str);
    }
}
