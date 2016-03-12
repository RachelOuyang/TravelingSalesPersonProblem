package travelingsalespersonproblemshow;

import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * KML template.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class XMLTemplate {
  
    private static final String head = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
            + "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n"
            + "<Document>\n"
            + "<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n"
            + "<LineStyle>\n"
            + "<color>73FF0000</color>\n"
            + "<width>5</width>\n"
            + "</LineStyle>\n"
            + "</Style>\n"
            + "<Style id=\"style5\">\n"
            + "<LineStyle>\n"
            + "<color>507800F0</color>\n"
            + "<width>5</width>\n"
            + "</LineStyle>\n"
            + "</Style>\n";
    String xml;
    
    /**
     * Construct kML file head.
     */
    public XMLTemplate() {
        xml = head;
    }
    /**
     * add additional place mark into KML file.
     * @param coordinates
     * @param type 
     */
    public void addPlacemark(String[] coordinates,String type){
        String placemark = createPlacemark(coordinates,type);
        xml += placemark;
    }
    
    /**
     * add the end to KML file.
     */
    public void addEnd(){
        xml += "</Document>\n" +
"</kml>";
    }
    
    /**
     * create place mark with given coordinates and type of TSP or optimal TSP.
     * @param coordinates an array of coordinates used to  create place mark.
     * @param type is TSP or optimal TSP.
     * @return a string representation of place mark.
     */
    private String createPlacemark(String[] coordinates,String type) {
        String allCoordinates = "";
        for(String coordinate:coordinates){
            allCoordinates += coordinate+",0.000000\n";
        }
        String path ="";
        String style = "";
        if(type.equals("Optimal")){
            path = "Optimal Path";
            style = "#style5";
        }
        else{
            path = "TSP Path";
            style = "#style6";
        }
        String placemark = "<Placemark>\n"
                + "<name>"+path+"</name>\n"
                + "<description>"+path+"</description>\n"
                + "<styleUrl>"+style+"</styleUrl>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n"
                + "<coordinates>\n"
                + allCoordinates
//                + "-79.93653583,40.44013011,0.000000\n"
//                + "-79.93085611,40.46389868,0.000000\n"
//                + "-79.94764804,40.46107271,0.000000\n"
//                + "-79.96743325,40.43864462,0.000000\n"
//                + "-79.96064401,40.4166985,0.000000\n"
//                + "-79.93653583,40.44013011,0.000000\n"
                + "</coordinates>\n"
                + "</LineString>\n"
                + "</Placemark>";
        return placemark;
    }
    
    /**
     * get the string representation of the xml.
     * @return the string representation of the xml.
     */
    @Override
    public String toString() {
        return xml;
    }

}
