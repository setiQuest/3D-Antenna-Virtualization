/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package globals;

/**
 *
 * @author dick
 */
public class Functions {
    public static float map(float oldValue, float oldMin, float oldMax, float newMin, float newMax) {
        return (((oldValue - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;   
    }
    
    // Converts a value from Google Elevation from meters to 0 to 255
    // lowest level considered is sealevel (disregarding oceans for now)
    // highest level is Everest' tip.
    public static float convertHeight(float oldValue) {  
        return ((oldValue)* (255)) / (8850);   
    }
}
