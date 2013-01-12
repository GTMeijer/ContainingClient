package projectcontaining.xmlparser;



/**
 * A data object to store a date
 * @author Hielke Hielkema
 */
public class Date {
    
    // Private fields
    private int _d;
    private int _m;
    private int _y;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    
    /**
     * Empty constructor
     */
    public Date() {
        this._d = 1;
        this._m = 1;
        this._y = 2012;
    }
    
    /**
     * Constructor
     * @param d day
     * @param m month
     * @param y year
     */
    public Date(int d, int m, int y) {
        this._d = d;
        this._m = m;
        this._y = y;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get functions">
    
    /**
     * Get the day
     * @return the day
     */
    public int getDay() {
        return this._d;
    }
    
    /**
     * Get the month
     * @return the month
     */
    public int getMonth() {
        return this._m;
    }
    
    /**
     * Get the year
     * @return the year
     */
    public int getYear() {
        return this._y;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Set functions">
    
    /**
     * Set the day
     * @param d the day
     */
    public void setDay(int d) {
        if (d > 0 && d < 32) {
            this._d = d;
        }
    }
    
    /**
     * Set the month
     * @param m the month
     */
    public void setMonth(int m) {
        if (m > 0 && m < 13) {
            this._m = m;
        }
    }
    
    /**
     * Set the year
     * @param y the year
     */
    public void setYear(int y) {
        if (y > 0) {
            
            if (y > 99) { // 2012
                this._y = y;
            }
            else { // 12
                this._y = 2000 + y;
            } 
        }
    }
    
    //</editor-fold>
    
    /**
     * Compare the date to an other date
     * @param date the date to compare to
     * @return -1 = lower, 0 = equal, 1 = higher
     */
    public int compareTo(Date date) {
        if (this._y == date.getYear()) {
            if (this._m == date.getMonth()) {
                if (this._d == date.getDay()) {
                    return 0; // =
                }
                else if (this._d > date.getDay()) {
                    return 1; // >
                }
                else { // this._d < date.getDay()
                    return -1; // <
                }
            }
            else if (this._m > date.getMonth()) {
                return 1; // >
            }
            else { // this._m < date.getMonth()
                return -1; // <
            }
        }
        else if (this._y > date.getYear()) {
            return 1; // >
        }
        else { // this._y < date.getYear()
            return -1; // <
        }
    }
    
    /**
     * Check if two dates are the same
     * @param date the date to compare to
     * @return a boolean indicating if the dates are the same
     */
    public boolean equals(Date date) {
        return this._d == date.getDay() &&
               this._m == date.getMonth() &&
               this._y == date.getMonth();
    }
    
}
