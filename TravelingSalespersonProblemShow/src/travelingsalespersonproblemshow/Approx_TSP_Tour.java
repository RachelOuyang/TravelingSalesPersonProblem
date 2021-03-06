/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblemshow;

import java.util.LinkedList;

/**
 * 
 * calculate the traveling salesperson problem.
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class Approx_TSP_Tour {

    public static final double FEET_TO_MILES = 0.00018939;
    private int[] pi;
    private double[][] cost;
    private int root;
    private LinkedList preOrderTree;
    
    /**
     * Constructor initialize graph cost, root, and preOrderTree..etc.
     * @param g the original graph.
     * @param cost the distance between vertex of the graph.
     */
    public Approx_TSP_Tour(Graph g, double[][] cost) {
        preOrderTree = new LinkedList();
        this.cost = cost;
        //step1:select vertex r:0 to be a root vertex
        root = 0;
        //Step2:compute minimum spanning tree:MST-Prime(g,cost,0)
        MST_Prim mst = new MST_Prim(g, cost, root);
        pi = mst.getPi();
        //Step3:get preorder walk of T
        generatePreOrderTree();
        //Step4:return hamiltonian cycle: call method printCycle();
    }
    
    /**
     * get preOrder walk of the minimum spanning tree
     */
    private void generatePreOrderTree() {
        LinkedList[] order = generateOrderList();
        int start = root;
        preOrderTree.addLast(start);
        while (true) {
            if (start == root && order[start].isEmpty()) {
                break;
            }
            if (order[start].peekFirst() != null) {
                int next = (Integer) order[start].pollFirst();
                preOrderTree.addLast(next);
                start = next;
            } else {
                start = pi[start];
            }
        }  
    }
    
    /**
     * get an LinkedList that stores each vertex and its neighbor vertex in the MST tree.
     * @return an LinkedList that stores each vertex and its neighbor vertex in the MST tree.
     */
    private LinkedList[] generateOrderList() {
        LinkedList[] order = new LinkedList[pi.length];
        //initialize the linked list in the order array
        for (int j = 0; j < order.length; j++) {
            order[j] = new LinkedList();
        }
        for (int index = 0; index < pi.length; index++) {
            for (int i = 0; i < pi.length; i++) {
                if (pi[i] == index) {
                    order[index].addLast(i);
                }
            }

        }
        return order;
    }
    /**
     * get the hamiltonian cycle length.
     * @return the hamiltonian cycle length.
     */
    public double getCycleLength() {
        double result = 0;
        int first = 0;
        int next = 0;
        for (int i = 0; i < preOrderTree.size() - 1; i++) {
            first = (Integer) preOrderTree.get(i);
            next = (Integer) preOrderTree.get(i + 1);
            result += cost[first][next];
        }
        result += cost[next][root];
        return result * FEET_TO_MILES;
    }
    
    /**
     * print the result cycle of the traveling sales person problem.
     */
    public void printCycle() {
        for (int i = 0; i < preOrderTree.size(); i++) {
            System.out.print(preOrderTree.get(i) + ",");
        }
        System.out.println(root);

    }
    
    /**
     * the pre order tree generated by the graph.
     * @return the pre order tree generated by the graph.
     */
    public LinkedList getPreOrderTree() {
        return preOrderTree;
    }

}
