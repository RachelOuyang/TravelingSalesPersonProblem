/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblemshow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * main program to ask user's prompt to get crime records and calculate
 * the TSP and optimal traveling sales person problem, and create a KML file to show the location.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class TravelingSalespersonProblemShow {
    public static final String FILE_NAME = "PGHCrimes.kml";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //prompt user to enter two index to get corresponding position of crime records
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start index:");
        int startIndex = sc.nextInt();
        System.out.println("Enter end index:");
        int endIndex = sc.nextInt();
        
        //get records from the file through method:getCrimeRecords(int startIndex,int endIndex)
        //process the records to a graph. 
        //create graph
        Graph graph = new Graph(endIndex-startIndex+1);
        String[] records = getCrimeRecords(startIndex,endIndex);
        //add records to graph
        for(int i=0;i<records.length;i++){
            CrimeRecords crimeRecord = new CrimeRecords(records[i]);
            graph.setLabel(i, crimeRecord);
        }
        //add distance to graph
        for(int i=0;i<graph.size();i++){
           for(int j=0;j<graph.size();j++){
               graph.addEdge(i, j);
           }
        }
        System.out.println("Crime Records Processed:");
        System.out.println();
        //graph.displayGraph();
        for(String record:records){
            System.out.println(record);
        }
        System.out.println();
        
        double[][] cost = graph.getDistances();
        Approx_TSP_Tour tsp = new Approx_TSP_Tour(graph,cost);
        System.out.print("Hamiltonan Cycle(not necessarily optimum):");
        tsp.printCycle();
        System.out.print("Length of Cycle:");
        System.out.println(tsp.getCycleLength());
        
        LinkedList preOrderTree = tsp.getPreOrderTree();
        String[] coordinate1 = getCooridnate(graph,preOrderTree);
        String[] changedCoordinate1 = changeCoordinate(coordinate1);
        
        
        System.out.println();
        Optimal_TSP_Tour optimal_tsp = new Optimal_TSP_Tour(graph,cost);
        System.out.print("Hamiltonan Cycle(minimum):");
        optimal_tsp.printOptimalPreOrderTree();
        System.out.print("Length of Cycle:");
        System.out.println(optimal_tsp.getOptimalCycleLength());
        
        LinkedList optimalPreOrderTree = optimal_tsp.getOptimalPreOrderTree();
        String[] coordinate2 = getCooridnate(graph,optimalPreOrderTree);
       // String[] changedCoordinate2 = changeCoordinate(coordinate2);
        
        String xml = generateXML(changedCoordinate1,coordinate2);
        writeFile(xml);
    }
    
    /**
     * get an array of string representation of the coordinates in the graph with given linked list of labels.
     * @param g the original graph.
     * @param labels linked list of number represent vertex in the graph.
     * @return get an array of string representation of the coordinates.
     */
    private static String[] getCooridnate(Graph g, LinkedList labels){
        String[] result = new String[g.size()];

        for(int i=0;i<labels.size();i++){
            int label = (Integer)labels.get(i);
            CrimeRecords record = g.getLabel(label);
            String longitude = ""+record.getLongitude();
            String latitude = ""+record.getLatitude();
            result[i]=longitude+","+latitude;
        }
        return result;
    }
    /**
     * in order to show the two cycle clearly, change a little bit of coordinate longitude.
     * @param coordinates
     * @return 
     */
    private static String[] changeCoordinate(String[] coordinates){
        String[] result = new String[coordinates.length];
        for(int i=0;i<coordinates.length;i++){
            String[] location = coordinates[i].split(",");
            double tmp = Double.valueOf(location[0])+0.001;
            String longitude = ""+tmp;
            String latitude = location[1];
            result[i] = longitude+","+latitude;
        }
        return result;
    }
   /**
    * write the KML file.
    * @param xml
    * @throws IOException 
    */
    private static void writeFile(String xml) throws IOException {
        File file = new File(FILE_NAME);
        try (FileWriter fw = new FileWriter(file, false) //true to append, false to overwrite
                ) {
            fw.write(xml);
        }
    }

    /**
     * generate a string of xml of two arrays of coordinates.
     * @param coordinate1
     * @param coordinate2
     * @return 
     */
    private static String generateXML(String[] coordinate1,String[] coordinate2) {
        //using XMLTemplate to create 
        String result;
        XMLTemplate xml = new XMLTemplate();
        xml.addPlacemark(coordinate1, "TSP");
        xml.addPlacemark(coordinate2, "Optimal");
        xml.addEnd();
        result = xml.toString();
        return result;
    }
    
    /**
     * read records with given start index and end index from csv file
     * @param startIndex start index
     * @param endIndex end index
     * @return a string of records.
     * @throws FileNotFoundException 
     */
    public static String[] getCrimeRecords(int startIndex,int endIndex) throws FileNotFoundException{
        String[] records = new String[endIndex-startIndex+1];
        File crimeRecordsFile = new File("CrimeLatLonXY1990.csv");
        Scanner sc = new Scanner(crimeRecordsFile);
        for(int i=0;i<=startIndex;i++){
            sc.nextLine();
        }
        int index = 0;
        for(int j = startIndex+1;j<=endIndex+1;j++){
            records[index] = sc.nextLine();
            index++;
            
        }
        return records;
    }
}
