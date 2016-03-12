/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblemoptimal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * main program to ask user's prompt to get crime records and calculate
 * the optimal traveling sales person problem.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class TravelingSalespersonProblemOptimal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        //prompt user to enter two index to get corresponding position of crime records
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start index:");
        int startIndex = sc.nextInt();
        System.out.println("Enter end index:");
        int endIndex = sc.nextInt();
        
        //get records from the file through method:getCrimeRecords(int startIndex,int endIndex)
        //process the records to a graph. The graph is implemented as a one dimensional 
        //array of crime records along with a two-dimensional array of doubles-holding 
        //the comuted distance between each pair.
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
        Optimal_TSP_Tour tsp = new Optimal_TSP_Tour(graph,cost);
        System.out.print("Hamiltonan Cycle(minimal):");
        tsp.printOptimalPreOrderTree();
       // tsp.printCycle();
        System.out.print("Length of Cycle:");
        
       System.out.println(tsp.getOptimalCycleLength());
        
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
