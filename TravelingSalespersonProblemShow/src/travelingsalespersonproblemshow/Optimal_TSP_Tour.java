/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblemshow;

import java.util.LinkedList;

/**
 * calculate the optimal traveling sales person problem.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class Optimal_TSP_Tour {

    public static final double FEET_TO_MILES = 0.00018939;
    private double[][] cost;
    private Graph g;
    private LinkedList<String> allPossibleTour;
    private LinkedList optimalPreOrderTree;
    private double optimalCycleLength;
    
    /**
     * Constructor initialize all possible tours and calculate optimal cycle.
     * @param g the original graph.
     * @param cost the distance between vertex of graph.
     */
    public Optimal_TSP_Tour(Graph g, double[][] cost) {
        this.cost = cost;
        this.g = g;
        allPossibleTour = new LinkedList();
        optimalPreOrderTree = new LinkedList();
        optimalCycleLength = 0.0;
        calculateAllPossibleTour();
        calculateOptimalCycle();
    }
    /**
     * get all possible tour traveling all vertex of the graph.
     */
    private void calculateAllPossibleTour() {
        int[] nodes = new int[g.size()];
        for (int i = 0; i < g.size(); i++) {
            nodes[i] = i;
        }
        Arrange(nodes, 0, nodes.length - 1);

    }
    
    /**
     * permutation with given array of number.
     * @param array an array of number.
     * @param start start point.
     * @param end end point.
     */
    private void Arrange(int[] array, int start, int end) {
        if (start == end) {
            String result = "";
            for (int i = 0; i < array.length; i++) {
                result += array[i] + ",";
            }
            allPossibleTour.add(result);
        } else {
            for (int i = start; i <= end; i++) {
                Swap(array, start, i);
                Arrange(array, start + 1, end);
                Swap(array, i, start);
            }
        }

    }
    
    /**
     * swap the number in position i and the number in position j in the array.
     * @param array
     * @param i
     * @param j 
     */
    private void Swap(int[] array, int i, int j) {

        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    /**
     * get the optimal cycle by calculating all possible tours length and choose the minimal one.
     */
    private void calculateOptimalCycle() {

        for (int i = 0; i < allPossibleTour.size(); i++) {

            String[] possibleTour = allPossibleTour.get(i).split(",");
            LinkedList currentPreOrderTree = new LinkedList();
            for (int j = 0; j < possibleTour.length; j++) {
                currentPreOrderTree.add(Integer.valueOf(possibleTour[j]));
            }

            double currentCycleLength = calculateCycleLength(currentPreOrderTree);
            if (optimalCycleLength <= 0.0000000000001 || optimalCycleLength > currentCycleLength) {
                optimalPreOrderTree = currentPreOrderTree;
                optimalCycleLength = currentCycleLength;
            }

        }
    }
    /**
     * print the optimal pre order tree.
     */
    public void printOptimalPreOrderTree() {
        for (int i = 0; i < optimalPreOrderTree.size(); i++) {
            System.out.print(optimalPreOrderTree.get(i) + ",");
        }
        System.out.println(optimalPreOrderTree.get(0));
    }
    
    /**
     * get the optimal cycle length.
     * @return the optimal cycle length.
     */
    public double getOptimalCycleLength() {
        return optimalCycleLength;
    }
   /**
    * calculate the optimal cycle length.
    */
    public double calculateCycleLength(LinkedList preOrderTree) {
        double result = 0;
        //LinkedList preOrderTree = generatePreOrderTree();
        int first = 0;
        int next = 0;
        for (int i = 0; i < preOrderTree.size() - 1; i++) {
            first = (Integer) preOrderTree.get(i);
            next = (Integer) preOrderTree.get(i + 1);
            result += cost[first][next];
        }
        result += cost[next][(Integer) preOrderTree.get(0)];
        return result * FEET_TO_MILES;
    }

    /**
     * get the optimal pre order tree.
     */
    public LinkedList getOptimalPreOrderTree() {
        return optimalPreOrderTree;
    }

}
