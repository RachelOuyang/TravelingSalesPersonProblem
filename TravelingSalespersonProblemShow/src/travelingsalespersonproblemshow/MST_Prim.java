/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblemshow;

/**
 *
 * get a MST tree with prim algorithm with given graph, distance, and start vertex.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class MST_Prim {
    private double[] key;
    private int[] pi;
    
    /**
     * Construct a MST tree with prim algorithm with given graph, distance, and start vertex.
     * @param g the original graph
     * @param w the distance cost between vertex of the graph.
     * @param r the start vertex of the MST tree.
     */
    public MST_Prim(Graph g,double[][] w,int r){
         //initialize a min heap and put all vertex in graph g into the heap.
        Heap heap = new Heap(g.size());

        //key[v]  is the minimum weight of any edge connecting v to a vertex in the tree we are building.
        key = new double[g.size()];
        //initialize each vertex's key to "infinity"
        for(int i =0;i<key.length;i++){
            key[i] = Double.MAX_VALUE;
        }
        key[r] = 0;
        
        for(int i=0;i<key.length;i++){
            heap.add(new Node(i,key[i]));
        }
        //pi[v] names the parent of v in the tree we are building.
        pi = new int[g.size()];
        pi[r] = -1;
 
        while(!heap.isEmpty()){
            int u = heap.deleteMin();
            //find light edge; u=r first time through
            for(int v:g.getNeighbors(u)){
                if(heap.contains(v) && (w[u][v]<key[v])){
                    //then
                    pi[v] = u;
                    key[v] = w[u][v];
                    //update heap
                    heap.update(v, key[v]);
                }
            }
        }
        
    }
    
    /**
     * get the minimum weight of any edge connecting v to a vertex in the tree we are building.
     * @return the minimum weight of any edge connecting v to a vertex in the tree we are building.
     */
    public double[] getKey() {
        return key;
    }
    /**
     * get an array of the parent of each vertex in the tree we are building.
     * @return an array of the parent of each vertex in the tree we are building.
     */
    public int[] getPi() {
        return pi;
    }
    
}
