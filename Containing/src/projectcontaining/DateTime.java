package projectcontaining;

import projectcontaining.xmlparser.Date;
import projectcontaining.xmlparser.Time;

/**
 * Object to store DateTimes
 * @author Hielke Hielkema
 */
public class DateTime implements Comparable<DateTime> {
    
    // Private fields
    private int _year;
    private int _month;
    private int _day;
    private int _h;
    private int _m;
    private int _s;
    private int _ms; // ms = s/1000
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    /**
     * Empty constructor
     */
    public DateTime() {}
    
    /**
     * Constructor
     * @param date date
     * @param time time
     */
    public DateTime(Date date, Time time) {
        this._year = date.getYear();
        this._month = date.getMonth();
        this._day = date.getDay();
        this._h = time.getHour();
        this._m = time.getMinute();
        this._s = time.getSecond();
        this._ms = time.getMilliSeconds();
    }
    
    /**
     * Constructor
     * @param h hours
     * @param m minutes
     * @param s seconds
     * @param ms milliseconds
     */
    public DateTime(int h, int m, int s, int ms) {
        this._h = h;
        this._m = m;
        this._s = s;
        this._ms = ms;
    }
    
    /**
     * Constructor
     * @param h hours
     * @param m minutes
     * @param s seconds
     */
    public DateTime(int h, int m, int s) {
        this._h = h;
        this._m = m;
        this._s = s;
        this._ms = 0;
    }
    
    
    /**
     * Constructor
     * @param h hours
     * @param m minutes
     * @param s seconds
     * @param day day
     * @param month month
     * @param year  year
     */
    public DateTime(int h, int m, int s, int day, int month, int year) {
        this._h = h;
        this._m = m;
        this._s = s;
        this._ms = 0;
        
        this._day = day;
        this._month = month;
        this._year = year;
    }
    
    /**
     * Constructor
     * @param h hours
     * @param m minutes
     * @param s seconds
     * @param ms milliseconds
     * @param day day
     * @param month month
     * @param year  year
     */
    public DateTime(int h, int m, int s, int ms, int day, int month, int year) {
        this._h = h;
        this._m = m;
        this._s = s;
        this._ms = ms;
        
        this._day = day;
        this._month = month;
        this._year = year;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get functions">
    /**
     * Get the year
     * @return the year
     */
    public int getYear() {
        return this._year;
    }
    
    /**
     * Get the month
     * @return the month
     */
    public int getMonth() {
        return this._month;
    }
        
    /**
     * Get the day
     * @return the day
     */
    public int getDay() {
        return this._day;
    }

    /**
     * Get the hour
     * @return the hour
     */
    public int getHour() {
        return this._h;
    }
    
    /**
     * Get the minute
     * @return the minute
     */
    public int getMinute() {
        return this._m;
    }
    
    /**
     * Get the total amount of minutes
     * @return total amount of minutes
     */
    public int getTotalMinutes() {
        return this._m + (this._h * 60);
    }
    
    /**
     * Get the seconds
     * @return the seconds
     */
    public int getSeconds() {
        return this._s;
    }
    
    /**
     * Get the total amount of seconds
     * @return total amount of seconds
     */
    public int getTotalSeconds() {
        return this._s + (this.getTotalMinutes() * 60);
    }
    
    /**
     * Get the milliseconds
     * @return the milliseconds
     */
    public int getMilliSeconds() {
        return this._ms;
    }
    
    /**
     * Get the total amout of milliseconds
     * @return total amount of millieseconds
     */
    public int getTotalMilliSeconds() {
        return this._ms + (this.getTotalSeconds() * 1000);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Set functions">
    
    /**
     * Set the day
     * @param day the new value for day
     */
    public void setDay(int day) {
        this._day = day;
    }

    /**
     * Set the month
     * @param day the new value for month
     */
    public void setMonth(int month) {
        this._month = month;
    }
    
    /**
     * Set the year
     * @param day the new value for year
     */
    public void setYear(int year) {
        this._year = year;
    }
    
    /**
     * Set the hour
     * @param day the new value for hour
     */
    public void setHour(int h) {
        this._h = h;
    }
    
    /**
     * Set the minute
     * @param day the new value for minute
     */
    public void setMinute(int m) {
        this._m = m;
    }
    
    /**
     * Set the seconds
     * @param day the new value for seconds
     */
    public void setSeconds(int s) {
        this._s = s;
    }
    
    /**
     * Set the milliseconds
     * @param day the new value for milliseconds
     */
    public void setMilliSeconds(int ms) {
        this._ms = ms;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Compare functions">
    /**
     * Compare two dates (Day, Month and Year)
     * @param date
     * @return -1 = smaller, 0 = equal, 1 = greater
     */
    public int compareDateTo(DateTime date) {
        if (this._year == date.getYear()) {
            if (this._month == date.getMonth()) {
                if (this._day == date.getDay()) {
                    return 0; // equal
                }
                else if (this._day > date.getDay()) {
                    return 1; // greater
                }
                else {
                    return -1; // smaller
                }
            }
            else if (this._month > date.getMonth()) {
                return 1; // greater
            }
            else {
                return -1; // smaller
            }
        }
        else if (this._year > date.getYear()) {
            return 1; // greater
        }
        else {
            return -1; // smaller
        }
    }
    
    /**
     * Compare two dates (Milli Seconds, Seconds, Minutes and Hours)
     * @param time
     * @return -1 = smaller, 0 = equal, 1 = greater
     */
    public int compareTimeTo(DateTime time) {
        // Get the miliseconds count for both times
        int msTime1 = this.getTotalMilliSeconds();
        int msTime2 = time.getTotalMilliSeconds();
        
        // Compare the values
        return (msTime1 == msTime2) ? 0:((msTime1 > msTime2) ? 1:-1);
    }
    
    /**
     * Comapre two DateTimes
     * @param date
     * @return
     */
    @Override
    public int compareTo(DateTime date) {
        // Compare the dates
        int result = this.compareDateTo(date);
        
        // Compare the times if the dates equal
        if (result == 0) {
            result = compareTimeTo(date);
        }
        
        // Return the result
        return result;
    }
    
    /**
     * 
     * @param date
     * @return 
     */
    public boolean equals(DateTime date) {
        return this.compareTo(date) == 0;
    }
    
    //</editor-fold>
    
    /**
     * Subtract a DateTime from an other DateTime
     * @param dateTime
     * @return DateTime = this - dateTime
     */
    public DateTime sub(DateTime dateTime) {
        
        int ms    = this._ms    - dateTime.getMilliSeconds();
        int s     = this._s     - dateTime.getSeconds();
        int m     = this._m     - dateTime.getMinute();
        int h     = this._h     - dateTime.getHour();
        int day   = this._day   - dateTime.getDay();
        int month = this._month - dateTime.getMonth();
        int year  = this._year  - dateTime.getYear();
        
        // Fix underflows
        while (ms < 0) { s--; ms = 1000 + ms; }
        while (s < 0) { m--; s = 60 + s; }
        while (m < 0) { h--; m = 60 + m; }
        while (h < 0) { day--; h = 24 + h; }
        while (day < 0) { month--; day = 31 + day; }
        while (month < 0) { year--; month = 12 + month; }
        
        // Return new DateTime with values
        return new DateTime(h, m, s, ms, day, month, year);
    }
    
    /**
     * Add a DateTime to an other DateTime
     * @param dateTime
     * @return DateTime = this + dateTime
     */
    public DateTime add(DateTime dateTime) {
        int year  = this._year  + dateTime.getYear();
        int month = this._month + dateTime.getMonth();
        int day   = this._day   + dateTime.getDay();
        int h     = this._h     + dateTime.getHour();
        int m     = this._m     + dateTime.getMinute();
        int s     = this._s     + dateTime.getSeconds();
        int ms    = this._ms    + dateTime.getMilliSeconds();
        
        // fix overflows
        while(ms >= 1000) { ms -= 1000; s++; }
        while(s >= 60) { s -= 60; m++; }
        while(m >= 60) { m -= 60; h++; }
        while(h >= 24) { h -= 24; day++; }
        while(day >= 32) { day -= 32; month++; }
        while(month > 12) { month -= 12; year++; }
        
        // Return new DateTime with values
        return new DateTime(h, m, s, ms, day, month, year);
    }
    
    /**
     * Create a string to represent the DateTime
     * @return a string representing the data in DateTime
     */
    @Override
    public String toString() {
        return String.format("[%d-%d-%d at %d:%d:%d.%d]", this._day, this._month, this._year, this._h, this._m, this._s, this._ms); 
    }
}
