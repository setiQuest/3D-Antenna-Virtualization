/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author dick
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import java.net.URL;

public class DataGetter {
    private URL url;
    private InputStream is;
    private InputStreamReader isReader;
    private BufferedReader reader;
    private String line;
    private String elevationData = "";
    // locations are added to the BaseURL in the form of Long,Lat|Long,Lat
    // Terminate the BaseURL with &sensor=false
    private String BaseURL = "http://maps.googleapis.com/maps/api/elevation/json?locations=";
    private String newURL;
    // for a single location
    public DataGetter(String Location) throws IOException {    
        newURL = BaseURL + Location + "&sensor=false";
        _openURLReader();
    }
	
    // for multiple locations
    public DataGetter(String[] Locations) throws IOException {
        int i;       
        newURL = BaseURL;
        for(i = 0; i < Locations.length; i++ ) {
            newURL += Locations[i];
            if(i + 1 < Locations.length) {
                newURL += "|";
            }
        }
        newURL = newURL + "&sensor=false";
        _openURLReader();
    }
    
    private void _openURLReader() throws MalformedURLException, IOException {
        System.out.println("Retrieving URL: " + newURL);
        url = new URL(newURL);
        is = url.openStream();
        isReader = new InputStreamReader(is);
        reader = new BufferedReader(isReader);
    }
    
    public void retrieveData() throws IOException {
        while ((line = reader.readLine()) != null) {
            elevationData += line;
        }
    }

    public void writeData(String location) throws IOException {
        FileWriter fstream = new FileWriter(location);
        BufferedWriter output = new BufferedWriter(fstream);
        output.write(elevationData);
        output.close();
        fstream.close();
    }	
	
    public String getElevationData() {
        System.out.println("elevation data: " + elevationData);
        return elevationData;
    }
}
