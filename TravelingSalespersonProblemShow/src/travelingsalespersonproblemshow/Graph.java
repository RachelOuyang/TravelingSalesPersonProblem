/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblemshow;

/**
 *
 * Indirected graph with each vertex labeled from number 0 to V. The graph is
 * implemented as a one dimensional array of crime records along with a
 * two-dimensional array of doubles-holding the communicated distance between each
 * pair.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class Graph {

    private double[][] distances;
    private CrimeRecords[] labels;
    private int V;//number of vertex
    private int E;//number of edges

    /**
     * Pre: Enough memory to create graph. Post: Constructor that built a graph
     * with given number of vertex.
     *
     * @param V number of vertex
     */
    public Graph(int V) {
        distances = new double[V][V];
        labels = new CrimeRecords[V];

        this.E = 0;
        this.V = V;

    }

    /**
     * set the label of the graph's vertex. the vertex object is a CrimeRecord.
     *
     * @param v the label for the CrimeRecords object vertex.
     * @param n the CrimeRecords object vertex.
     */
    public void setLabel(int v, CrimeRecords n) {
        labels[v] = n;
    }

    /**
     * Pre: v and w are existing vertex label in graph. Post: add an edge
     * connect v and w together.
     *
     * @param v - one vertex with label v in the graph
     * @param w - the other vertex with label w in the graph
     */
    public void addEdge(int v, int w) {
        E++;
        CrimeRecords obj1 = labels[v];
        CrimeRecords obj2 = labels[w];
        double distance;
        if (v == w) {
            distance = 0;
        } else {
            distance = Math.sqrt(Math.pow(obj1.getX() - obj2.getX(), 2) + Math.pow(obj1.getY() - obj2.getY(), 2));
        }

        distances[v][w] = distance;
        distances[w][v] = distance;
    }

    /**
     * get all the vertex that directly connects with given vertex with label v.
     *
     * @param v v is the label for the vertex we are looking for.
     * @return an array of labels that connects with given vertex with label v.
     */
    public int[] getNeighbors(int v) {
        int count;
        int[] answer;
        count = 0;
        for (int i = 0; i < labels.length; i++) {
            if (distances[v][i] != 0) {
                count++;
            }
        }
        answer = new int[count];
        count = 0;
        for (int j = 0; j < labels.length; j++) {
            if (distances[v][j] != 0) {
                answer[count++] = j;
            }
        }
        return answer;
    }

    /**
     * Pre: v and w are existing vertex labels in graph. Post: find out if the
     * graph has edge between 2 vertex.
     *
     * @param v-one vertex label
     * @param w-the other vertex label
     * @return true if the graph has edge between vertex whose labels are v and
     * w, and false if not.
     */
    public boolean contains(int v, int w) {
        return distances[v][w] == 0;
    }

    /**
     * get all the CrimeRecords of the graph.
     *
     * @return
     */
    public CrimeRecords[] getAllLabel() {
        return labels;
    }

    /**
     * get the CrimeRecords with given label v.
     *
     * @param v
     * @return
     */
    public CrimeRecords getLabel(int v) {
        return labels[v];
    }

    /**
     * get the vertex number of graph.
     *
     * @return
     */
    public int size() {
        return labels.length;
    }

    /**
     * get all the distances value of the graph.
     *
     * @return
     */
    public double[][] getDistances() {
        return distances;
    }

}
