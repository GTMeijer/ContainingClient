/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcontaining.xmlparser;

/**
 * A object to store a Time
 * TODO: javadoc
 * @author Hielke Hielkema
 */
public class Time {
    
    // Private fields
    private int _h;
    private int _m;
    private int _s;
    private int _ms;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    
    /**
     * Empty constructor
     */
    public Time() { }
    
    public Time(float time) {
        // parse the float
        this._h = (int)time;
        this._m = (int)(time - (float)this._h);
    }
    
    /**
     * Constructor
     * @param h hour
     * @param m minute
     * @param s second
     */
    public Time(int h, int m, int s) {
        this._h = h;
        this._m = m;
        this._s = s;
        this._ms = 0;
    }
    
    /**
     * Constructor
     * @param h hour
     * @param m minute
     * @param s second
     * @param ms milliseconds
     */
    public Time(int h, int m, int s, int ms) {
        this._h = h;
        this._m = m;
        this._s = s;
        this._ms = ms;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get functions">
    /**
     *
     * @return
     */
    public int getHour() {
        return this._h;
    }
    
    /**
     *
     * @return
     */
    public int getMinute() {
        return this._m;
    }
    
    /**
     *
     * @return
     */
    public int getSecond() {
        return this._s;
    }
    
    /**
     *
     * @return
     */
    public int getMilliSeconds() {
        return this._ms;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Set functions">
    /**
     *
     * @param h
     */
    public void setHour(int h) {
        this._h = h;
    }
    
    /**
     *
     * @param h
     */
    public void setMinute(int m) {
        this._m = m;
    }
    
    /**
     *
     * @param s
     */
    public void setSecond(int s) {
        this._s = s;
    }
    
    /**
     *
     * @param ms
     */
    public void setMilliSecond(int ms) {
        this._ms = ms;
    }
    //</editor-fold>
}
