package projectcontaining.xmlparser;



import org.w3c.dom.Element;
import org.w3c.dom.Node;
import projectcontaining.DateTime;

/**
 * A data object to store all the information of a container
 * @author Hielke Hielkema
 */
public class ContainerData {
    
    //<editor-fold defaultstate="collapsed" desc="Private fields">
    
    // Container id
    private String _id;
    
    // Enter
    private Point3D _location;
    private Date _enterDate;
    private Time _enterTimeFrom;
    private Time _enterTimeTo;
    private String _enterTransportType;
    private String _enterCompany;
    
    // Owner
    private String _owner;
    private int _containerNumber;
    
    // Leave
    private Date _leaveDate;
    private Time _leaveTimeFrom;
    private Time _leaveTimeTo;
    private String _leaveTransportType;
    private String _leaveCompany;
    
    // Size
    private float _l; // Container lenght
    private float _w; // Container width
    private float _h; // Container height
    
    // Weight
    private int _weightEmpty;
    private int _weightFilled;
    
    // Content
    private String _contentName;
    private String _contentType;
    private String _contentDanger;
    
    // ISO
    private String _iso;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    
    /**
     * Empty constructor
     */
    public ContainerData() {

    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get functions">
    
    /**
     * Get the id of the container
     * @return the id of the container
     */
    public String getId() {
        return this._id;
    }
    
    /**
     * Get the location of the container on the ship
     * @return the location on the ship
     */
    public Point3D getLocation() {
        return this._location;
    }
    
    /**
     * Get the enter start time of the container
     * @return the enter time of the container start time
     */
    public Time getEnterTimeFrom() {
        return this._enterTimeFrom;
    }
    
    /**
     * Get the enter start DateTime of the container
     * @return the enter start DateTime
     */
    public DateTime getEnterDateTimeFrom() {
        return new DateTime(this._enterDate, this._enterTimeFrom);
    }
    
    /**
     * Get the enter end time of the container
     * @return the enter time of the container end time
     */
    public Time getEnterTimeTo() {
        return this._enterTimeTo;
    }
    
    /**
     * Get the enter start DateTime of the container
     * @return the enter start DateTime
     */
    public DateTime getEnterDateTimeTo() {
        return new DateTime(this._enterDate, this._enterTimeTo);
    }
    
    /**
     * Get the enter transport type
     * @return
     */
    public String getEnterTransportType() {
        return this._enterTransportType;
    }
    
    /**
     * Get the enter company
     * @return the enter company
     */
    public String getEnterCompany() {
        return this._enterCompany;
    }
    
    /**
     * Get the owner of the container
     * @return the owner of the container
     */
    public String getOwner() {
        return this._owner;
    }
    
    /**
     * Get the container number
     * @return the container number
     */
    public int getContainerNumber() {
        return this._containerNumber;
    }
    
    /**
     * Get the leave start time of the container
     * @return the leave time of the container start time
     */
    public Time getLeaveTimeFrom() {
        return this._leaveTimeFrom;
    }
    
    /**
     * Get the leave start DateTime of the container
     * @return the leave start DateTime
     */
    public DateTime getLeaveDateTimeFrom() {
        return new DateTime(this._leaveDate, this._leaveTimeFrom);
    }
    
    /**
     * Get the leave end time of the container
     * @return the leave time of the container end time
     */
    public Time getLeaveTimeTo() {
        return this._leaveTimeTo;
    }
    
    /**
     * Get the leave end DateTime of the container
     * @return the leave end DateTime
     */
    public DateTime getLeaveDateTimeTo() {
        return new DateTime(this._leaveDate, this._leaveTimeTo);
    }
    
    /**
     * Get the leave transport type
     * @return
     */
    public String getLeaveTransportType() {
        return this._leaveTransportType;
    }
    
    /**
     * Get the leave company
     * @return the leave company
     */
    public String getLeaveCompany() {
        return this._leaveCompany;
    }
    
    /**
     * Get the lenght of the container
     * @return the lenght of the container
     */
    public float getLenght() {
        return this._l;
    }
    
    /**
     * Get the height of the container
     * @return the height of the container
     */
    public float getHeight() {
        return this._h;
    }
    
    /**
     * Get the width of the container
     * @return the width of the container
     */
    public float getWidth() {
        return this._w;
    }
    
    /**
     * Get the weight of the container when it's empty
     * @return the weight of the container if it's empty
     */
    public int getWeightEmpty() {
        return this._weightEmpty;
    }
    
    /**
     * Get the weight of the container when it's filled
     * @return the weight of the container if it's filled
     */
    public int getWeightsFilled() {
        return this._weightFilled;
    }
    
    /**
     * Get the name of the container content
     * @return the name of the container content
     */
    public String getContentName() {
        return this._contentName;
    }
    
    /**
     * Get the type of the container content
     * @return the type of the container content
     */
    public String getContentType() {
        return this._contentType;
    }
    
    /**
     * Get the danger level of the container content
     * @return the danger level of the container content
     */
    public String getContentDanger() {
        return this._contentDanger;
    }
    
    /**
     * Get the iso value
     * @return the iso value
     */
    public String getIso() {
        return this._iso;
    }
    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Set functions">
    
    /**
     * Set the id of the container
     * @param id
     */
    public void setId(String id) {
        this._id = id;
    }
    
    /**
     * Set the location of the container on the ship
     * @param location the location of the container on the ship
     */
    public void setLocation(Point3D location) {
        this._location = location;
    }
    
    /**
     * Set the date when the container entered
     * @param date the date when the container entered
     */
    public void setEnterDate(Date date) {
        this._enterDate = date;
    }
    
    /**
     * Set the enter start time
     * @param time the enter start time
     */
    public void setEnterTimeFrom(Time time) {
        this._enterTimeFrom = time;
    }
    
    /**
     * Set the enter end time
     * @param time the enter start time
     */
    public void setEnterTimeTo(Time time) {
        this._enterTimeTo = time;
    }
    
    /**
     * Set the enter transport type
     * @param type the transport type
     */
    public void setEnterTransportType(String type) {
        this._enterTransportType = type;
    }
    
    /**
     * Set the enter company
     * @param company the company
     */
    public void setEnterCompany(String company) {
        this._enterCompany = company;
    }
    
    /**
     * Set the owner
     * @param owner the owner
     */
    public void setOwner(String owner) {
        this._owner = owner;
    }
    
    /**
     * Set the container number
     * @param number the container number
     */
    public void setContainerNumber(int number) {
        this._containerNumber = number;
    }
    
    /**
     * Set the date when the container left
     * @param date the date when the container left
     */
    public void setLeaveDate(Date date) {
        this._leaveDate = date;
    }
    
    /**
     * Set the leave start time
     * @param time the leave start time
     */
    public void setLeaveTimeFrom(Time time) {
        this._leaveTimeFrom = time;
    }
    
    /**
     * Set the leave end time
     * @param time the leave start time
     */
    public void setLeaveTimeTo(Time time) {
        this._leaveTimeTo = time;
    }
    
    /**
     * Set the leave transport type
     * @param type the type of transport
     */
    public void setLeaveTransportType(String type) {
        this._leaveTransportType = type;
    }
    
    /**
     * Set the leave company
     * @param company the company
     */
    public void setLeaveCompany(String company) {
        this._leaveCompany = company;
    }
    
    /**
     * Set the side of the container
     * @param lenght the lenght of the container
     * @param width the width of the container
     * @param height the height of the container
     */
    public void setSize(int lenght, int width, int height) {
        this.setLength(lenght);
        this.setWidth(width);
        this.setHeight(height);
    }
    
    /**
     * Set the length of the container
     * @param length the new length of the container
     */
    public void setLength(float length) {
        if (length > 0f) {
            this._l = length;
        }
    }
    
    /**
     * Set the width of the container
     * @param width the new width of the container
     */
    public void setWidth(float width) {
        if (width > 0f) {
            this._w = width;
        }
    }
    
    /**
     * Set the height of the container
     * @param height the new height of the container
     */
    public void setHeight(float height) {
        if (height > 0f) {
            this._h = height;
        }
    }
    
    /**
     * Set the height of the container when it's empty
     * @param weight the weight
     */
    public void setWeigthEmpty(int weight) {
        if (weight >= 0) {
            this._weightEmpty = weight;
        }
    }
    
    /**
     * Set the height of the container when it's filled
     * @param weight the weight
     */
    public void setWeigthFilled(int weight) {
        if (weight >= 0) {
            this._weightFilled = weight;
        }
    }
    
    /**
     * Set the name of the container content
     * @param name the name of the content
     */
    public void setContentName(String name) {
        this._contentName = name;
    }
    
    /**
     * Set the type of the container content
     * @param type the content type
     */
    public void setContentType(String type) {
        this._contentType = type;
    }
    
    /**
     * Set the danger level of the container content
     * @param danger the danger level
     */
    public void setContentDanger(String danger) {
        this._contentDanger = danger;
    }
    
    /**
     * Set the iso
     * @param iso the iso
     */
    public void setIso(String iso) {
        this._iso = iso;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Parsing">
    
    /**
     * Get a ContainerData from a xml Node
     * @param xml the xml Node to parse into a ContainerData
     * @return if succes: A Container data parsed from xml, if failed:null
     */
    public static ContainerData fromXml(Node xml) {
        
        // Create a new ContainerData
        ContainerData data = new ContainerData();
        
        // Try to parse the xml
        if(data.parseXml(xml)) {
            return data; // Parsing succeeded
        }
        else {
            return null; // Error parsing
        }
    }
    
    /**
     * Parse the container data from the xml Node
     * @param xml the xml Node to parse
     * @return a boolean indicating if the process succeeded
     */
    private boolean parseXml(Node xml) {
       
        // Set the id
        String id = ((Element)xml).getAttribute("id");
        this.setId(id);
        
        // Get the element count
        int lenght = xml.getChildNodes().getLength();

        // Loop though the elements, parse them and add them to the list
        for(int i = 0; i < lenght; i++) {
            
            // Get the node
            Node node = xml.getChildNodes().item(i);
            
            // Check the node type
            if (!node.getNodeName().equals("#text")) {
                if(!this.parseXmlNode(node)) {
                    return false; // error
                }
            }
        }
        
        // Succes
        return true;
    }
    
    /**
     * Parse a subnode from the root node of the container
     * @param node the subnode to parse
     * @return a boolean indicating if the process succeeded
     */
    private boolean parseXmlNode(Node node) {
        switch(node.getNodeName())
        {
            case "aankomst":
            {
                //<editor-fold defaultstate="collapsed" desc="aankomst">
                // Loop trough the subitems
                int lenght = node.getChildNodes().getLength();
                for(int i = 0; i < lenght; i++) {
                    Node sNode = node.getChildNodes().item(i);
                    if (!sNode.getNodeName().equals("#text")) {
                        switch(sNode.getNodeName()) {
                            case "datum":
                            {
                                //<editor-fold defaultstate="collapsed" desc="datum">
                                
                                int d = -1;
                                int m = -1;
                                int y = -1;

                                // Loop trough the subitems
                                int slenght = sNode.getChildNodes().getLength();
                                for(int x = 0; x < slenght; x++) {
                                    Node ssNode = sNode.getChildNodes().item(x);
                                    if (!ssNode.getNodeName().equals("#text")) {
                                        switch(ssNode.getNodeName()) {
                                            case "d":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    d = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "m":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    m = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "j":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    y = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            default:
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                }
                                
                                if (d == -1 || m == -1 || y == -1) {
                                    return false; // invalid date
                                }
                                
                                this.setEnterDate(new Date(d, m, y));

                                break;
                                //</editor-fold>
                            }
                                
                            case "tijd":
                            {
                                //<editor-fold defaultstate="collapsed" desc="tijd">
                                // Loop trough the subitems
                                int slenght = sNode.getChildNodes().getLength();
                                for(int x = 0; x < slenght; x++) {
                                    Node ssNode = sNode.getChildNodes().item(x);
                                    if (!ssNode.getNodeName().equals("#text")) {
                                        switch(ssNode.getNodeName()) {
                                            case "van":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseFloat(sValue)) {
                                                    return false; // Error parsing float
                                                }
                                                else {
                                                    // Parse the valye
                                                    float value = Float.parseFloat(sValue);

                                                    // Set the value
                                                    this.setEnterTimeFrom(new Time(value));

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "tot":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseFloat(sValue)) {
                                                    return false; // Error parsing float
                                                }
                                                else {
                                                    // Parse the valye
                                                    float value = Float.parseFloat(sValue);

                                                    // Set the value
                                                    this.setEnterTimeTo(new Time(value));

                                                    // Break
                                                    break;
                                                }
                                            }

                                            default:
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                }

                                break;
                                //</editor-fold>
                            }
                          
                            case "soort_vervoer":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setEnterTransportType(sNode.getChildNodes().item(0).getNodeValue());
                                
                                
                                // Break
                                break;
                            }
                                
                            case "bedrijf":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setEnterCompany(sNode.getChildNodes().item(0).getNodeValue());                                
                                
                                // Break
                                break;
                            }
                                
                            case "positie":
                            {
                                //<editor-fold defaultstate="collapsed" desc="positie">
                                
                                int px = -1;
                                int py = -1;
                                int pz = -1;

                                // Loop trough the subitems
                                int slenght = sNode.getChildNodes().getLength();
                                for(int x = 0; x < slenght; x++) {
                                    Node ssNode = sNode.getChildNodes().item(x);
                                    if (!ssNode.getNodeName().equals("#text")) {
                                        switch(ssNode.getNodeName()) {
                                            case "x":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    px = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "y":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    py = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "z":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    pz = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            default:
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                }
                                
                                if (px == -1 || py == -1 || pz == -1) {
                                    return false; // invalid date
                                }
                                
                                this.setLocation(new Point3D(px, py, pz));

                                break;
                                //</editor-fold>
                            }
                                  
                            default:
                            {
                                return false;
                            }
                        }
                    }
                }
                
                break;
                //</editor-fold>
            }

            case "eigenaar":
            {
                //<editor-fold defaultstate="collapsed" desc="eigenaar">
                // Loop trough the subitems
                int lenght = node.getChildNodes().getLength();
                for(int i = 0; i < lenght; i++) {
                    Node sNode = node.getChildNodes().item(i);
                    if (!sNode.getNodeName().equals("#text")) {
                        switch(sNode.getNodeName()) {
                            case "naam":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setOwner(sNode.getChildNodes().item(0).getNodeValue());
                                
                                // Break
                                break;
                            }
                                
                            case "containernr":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Try to parse the value
                                String sValue = sNode.getChildNodes().item(0).getNodeValue();
                                if (!this.tryParseInt(sValue)) {
                                    return false; // Error parsing int
                                }
                                else {
                                    // Parse the valye
                                    int value = Integer.parseInt(sValue);
                                    
                                    // Set the value
                                    this.setContainerNumber(value);
                                    
                                    // Break
                                    break;
                                }
                            }
                                
                            default:
                            {
                                return false;
                            }
                        }
                    }
                }
                
                break;
                //</editor-fold>
            }
                
            case "vertrek":
            {
                //<editor-fold defaultstate="collapsed" desc="vertrek">
                // Loop trough the subitems
                int lenght = node.getChildNodes().getLength();
                for(int i = 0; i < lenght; i++) {
                    Node sNode = node.getChildNodes().item(i);
                    if (!sNode.getNodeName().equals("#text")) {
                        switch(sNode.getNodeName()) {
                            case "datum":
                            {
                                //<editor-fold defaultstate="collapsed" desc="datum">
                                
                                int d = -1;
                                int m = -1;
                                int y = -1;

                                // Loop trough the subitems
                                int slenght = sNode.getChildNodes().getLength();
                                for(int x = 0; x < slenght; x++) {
                                    Node ssNode = sNode.getChildNodes().item(x);
                                    if (!ssNode.getNodeName().equals("#text")) {
                                        switch(ssNode.getNodeName()) {
                                            case "d":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    d = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "m":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    m = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "j":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseInt(sValue)) {
                                                    return false; // Error parsing int
                                                }
                                                else {
                                                    // Parse the valye
                                                    int value = Integer.parseInt(sValue);

                                                    // Set the value
                                                    y = value;

                                                    // Break
                                                    break;
                                                }
                                            }

                                            default:
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                }
                                
                                if (d == -1 || m == -1 || y == -1) {
                                    return false; // invalid date
                                }
                                
                                this.setLeaveDate(new Date(d, m, y));

                                break;
                                //</editor-fold>
                            }
                                
                            case "tijd":
                            {
                                //<editor-fold defaultstate="collapsed" desc="tijd">
                                // Loop trough the subitems
                                int slenght = sNode.getChildNodes().getLength();
                                for(int x = 0; x < slenght; x++) {
                                    Node ssNode = sNode.getChildNodes().item(x);
                                    if (!ssNode.getNodeName().equals("#text")) {
                                        switch(ssNode.getNodeName()) {
                                            case "van":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseFloat(sValue)) {
                                                    return false; // Error parsing float
                                                }
                                                else {
                                                    // Parse the valye
                                                    float value = Float.parseFloat(sValue);

                                                    // Set the value
                                                    this.setLeaveTimeFrom(new Time(value));

                                                    // Break
                                                    break;
                                                }
                                            }

                                            case "tot":
                                            {
                                                // Check if the subitem count and type are valid
                                                if (ssNode.getChildNodes().getLength() != 1 || !ssNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                                    return false;
                                                }

                                                // Try to parse the value
                                                String sValue = ssNode.getChildNodes().item(0).getNodeValue();
                                                if (!this.tryParseFloat(sValue)) {
                                                    return false; // Error parsing float
                                                }
                                                else {
                                                    // Parse the valye
                                                    float value = Float.parseFloat(sValue);

                                                    // Set the value
                                                    this.setLeaveTimeTo(new Time(value));

                                                    // Break
                                                    break;
                                                }
                                            }

                                            default:
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                }

                                break;
                                //</editor-fold>
                            }
                          
                            case "soort_vervoer":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setLeaveTransportType(sNode.getChildNodes().item(0).getNodeValue());
                                
                                // Break
                                break;
                            }
                                
                            case "bedrijf":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setLeaveCompany(sNode.getChildNodes().item(0).getNodeValue());                                
                                
                                // Break
                                break;
                            }
                                  
                            default:
                            {
                                return false;
                            }
                        }
                    }
                }
                
                break;
                //</editor-fold>
            }
                
            case "afmetingen":
            {
                //<editor-fold defaultstate="collapsed" desc="afmetingen">
                // Loop trough the subitems
                int lenght = node.getChildNodes().getLength();
                for(int i = 0; i < lenght; i++) {
                    Node sNode = node.getChildNodes().item(i);
                    if (!sNode.getNodeName().equals("#text")) {
                        switch(sNode.getNodeName()) {
                            case "l":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Try to parse the value
                                float value = this.parseLenght(sNode.getChildNodes().item(0).getNodeValue());
                                
                                if (value == -1) {
                                    return false; // Parse error
                                }
                                
                                // Set the value
                                this.setLength(value);
                                
                                // Break
                                break;
                            }
                                
                            case "b":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Try to parse the value
                                float value = this.parseLenght(sNode.getChildNodes().item(0).getNodeValue());
                                
                                if (value == -1) {
                                    return false; // Parse error
                                }
                                
                                // Set the value
                                this.setWidth(value);
                                
                                // Break
                                break;
                            }
                                
                            case "h":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Try to parse the value
                                float value = this.parseLenght(sNode.getChildNodes().item(0).getNodeValue());
                                
                                if (value == -1) {
                                    return false; // Parse error
                                }
                                
                                // Set the value
                                this.setHeight(value);
                                
                                // Break
                                break;
                            }
                                
                            default:
                            {
                                return false;
                            }
                        }
                    }
                }
                
                break;
                //</editor-fold>
            }
                
            case "gewicht":
            {
                //<editor-fold defaultstate="collapsed" desc="gewicht">
                // Loop trough the subitems
                int lenght = node.getChildNodes().getLength();
                for(int i = 0; i < lenght; i++) {
                    Node sNode = node.getChildNodes().item(i);
                    if (!sNode.getNodeName().equals("#text")) {
                        switch(sNode.getNodeName()) {
                            case "leeg":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Try to parse the value
                                String sValue = sNode.getChildNodes().item(0).getNodeValue();
                                if (!this.tryParseInt(sValue)) {
                                    return false; // Error parsing int
                                }
                                else {
                                    // Parse the valye
                                    int value = Integer.parseInt(sValue);
                                    
                                    // Set the value
                                    this.setWeigthEmpty(value);
                                    
                                    // Break
                                    break;
                                }
                            }
                                
                            case "inhoud":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Try to parse the value
                                String sValue = sNode.getChildNodes().item(0).getNodeValue();
                                if (!this.tryParseInt(sValue)) {
                                    return false; // Error parsing int
                                }
                                else {
                                    // Parse the valye
                                    int value = Integer.parseInt(sValue);
                                    
                                    // Set the value
                                    this.setWeigthFilled(value);
                                    
                                    // Break
                                    break;
                                }
                            }
                                
                            default:
                            {
                                return false;
                            }
                        }
                    }
                }
                
                break;
                //</editor-fold>
            }
                
            case "inhoud":
            {
                //<editor-fold defaultstate="collapsed" desc="Inhoud">
                // Loop trough the subitems
                int lenght = node.getChildNodes().getLength();
                for(int i = 0; i < lenght; i++) {
                    Node sNode = node.getChildNodes().item(i);
                    if (!sNode.getNodeName().equals("#text")) {
                        switch(sNode.getNodeName()) {
                            case "naam":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setContentName(sNode.getChildNodes().item(0).getNodeValue());
                                
                                // Break
                                break;
                            }
                                
                            case "soort":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setContentType(sNode.getChildNodes().item(0).getNodeValue());
                                
                                // Break
                                break;
                            }
                                
                            case "gevaar":
                            {
                                // Check if the subitem count and type are valid
                                if (sNode.getChildNodes().getLength() != 1 || !sNode.getChildNodes().item(0).getNodeName().equals("#text")) {
                                    return false;
                                }
                                
                                // Set the value
                                this.setContentDanger(sNode.getChildNodes().item(0).getNodeValue());
                                
                                // Break
                                break;
                            }
                                
                            default:
                            {
                                return false;
                            }
                        }
                    }
                }
                break;
                //</editor-fold>
            }
                
            case "ISO":
            {
                // Check if the subitem count and type are valid
                if (node.getChildNodes().getLength() != 1 || !node.getChildNodes().item(0).getNodeName().equals("#text")) {
                    return false;
                }
                
                // Set the value
                this.setIso(node.getChildNodes().item(0).getNodeValue());
                
                // Break
                break;
            }
            
            default:
            {
                System.out.print("Unknown node");
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Check if a given String can be parsed to an int
     * @param value the string to test
     * @return a bool indicating if parsing is possible
     */
    private boolean tryParseInt(String value) {  
       try  {  
          Integer.parseInt(value);  
          return true;  
       } 
       catch(NumberFormatException nfe) {  
          return false;  
       }  
    }
    
    /**
     * Check if a given String can be parsed to a float
     * @param value the string to test
     * @return a bool indicating if parsing is possible
     */
    private boolean tryParseFloat(String value) {  
       try  {  
          Float.parseFloat(value); 
          return true;  
       } 
       catch(NumberFormatException nfe) {  
          return false;  
       }  
    }
    
    
    private int parseLenght(String str) {
        return 5; // TODO
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Export">
    
    /**
     * Build a query to add the container to the database
     * @return a query
     */
    public String getQuery() {
        return ""; // TODO
    }
    
    //</editor-fold>
    
}
