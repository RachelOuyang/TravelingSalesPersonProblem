/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblem;

/**
 * This class records crimes, include the crime information such as street date
 * and location.
 * A format of crime records would be like follows:
 * X,         Y      ,Time,Street,        Offense,Date     ,Tract,Lat,Long
 * 1348656.471,399538.5342,32874,100 BONIFAY ST,ROBBERY,1/1/90,160600,40.40865518,-79.9760891
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class CrimeRecords {

    private double x;
    private double y;
    private int time;
    private String street;
    private String offence;
    private String date;
    private int tract;
    private double latitude;
    private double longitude;

    /**
     * get the x location of the crime
     * @return a double number represent the x location of crime.
     */
    public double getX() {
        return x;
    }
    /**
     * get the y location of the crime
     * @return a double number represent the y location of crime.
     */
    public double getY() {
        return y;
    }
    /**
     * Construct a crime records based on the string representation of a record.
     * @param records string representation of a crime record.
     */
    public CrimeRecords(String records){
        String[] segments = records.split(",");
        x = Double.valueOf(segments[0]);
        y = Double.valueOf(segments[1]);
        time = Integer.valueOf(segments[2]);
        street = segments[3];
        offence = segments[4];
        date = segments[5];
        tract = Integer.valueOf(segments[6]);
        latitude = Double.valueOf(segments[7]);
        longitude = Double.valueOf(segments[8]);
        
    }
    /**
     * get the latitude of the crime
     * @return a double number represent the latitude of crime.
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * get the longitude of the crime
     * @return a double number represent the latitude of crime.
     */
    public double getLongitude() {
        return longitude;
    }
    
    
}
