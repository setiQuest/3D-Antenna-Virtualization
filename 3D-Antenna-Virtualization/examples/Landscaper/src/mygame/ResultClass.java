/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.terrain.heightmap.RawHeightMap;

/**
 *
 * @author dick
 */
public class ResultClass {
    public String _status;
    public Results[] _results;
	
    public String getStatus() { return _status; }
    public Results[] getResults() { return _results; }
	
    public void setStatus(String s) { _status = s; }
    public void setResults(Results[] r) { _results = r; }

    // returns the results from Google as a string showing
    // elevation, longitiude, latitude 
    public String getText() {
        int i;
        String returnValue = "";
        if (hasResults()) {
            for(i = 0; i < _results.length; i++){ 
                returnValue += _results[i].marshall();
                if(i + 1 < _results.length) {
                    returnValue += "|";
                }
            }
        } else {
            returnValue = "Nothing to display.";
        }
        return returnValue;
    }
    
    // returns an array of floats suitable for creating a TerrainBlock
    public float[] getRawHeightMap() {
        float[] returnValue;
         if (hasResults()) {
             returnValue = new float[_results.length];
        } else {
             returnValue = new float[] {
                1,2,3,4,
                2,1,2,3,
                3,2,1,2,
                4,3,2,1
                }; // lifted straight out of the tutorial
         }
        return returnValue;
    }
    
    // dummy function
    public float[] D_getRawHeightMap() {
        RawHeightMap returnValue;
        float[] temp = new float[] {
            150, 255, 200, 255, 200,
            150, 200, 250, 255, 200,
            100, 100, 150, 200, 100,
            100, 100, 000, 050, 150,
            000, 050, 000, 000, 000
        }; // lifted straight out of the tutorial
        float[] temp2 = new float[5130];
        int i;
        for(i = 0; i <= 5129; i++) {
            temp2[i] = i%62;
        }
        returnValue = new RawHeightMap(temp);
        return temp2;
    }
    
    private boolean hasResults() {
        if (_results.length == 0 || _results == null) {
            return false;
        }
        return true;
    }
    public static class Results {
        public Double _elevation; 
        public Location _location;
		
        public Double getElevation() { return _elevation; }
        public Location getLocation() { return _location; }
		
        public void setElevation(Double d) { _elevation = d; }
        public void setLocation(Location l) { _location = l; }
                
                
        public String marshall() {
            return _elevation + " " + _location._lat + " " + _location._lng;
        }
    }
	
    public static class Location  {
        public Double _lat;
        public Double _lng;
		
        public Double getLat() { return _lat; }
        public Double getLng() { return _lng; }
        public void setLng(Double d) { _lng = d; }
        public void setLat(Double d) { _lat = d; }
    }
}
