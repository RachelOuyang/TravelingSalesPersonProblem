/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblem;

/**
 * Node class that contains an int label and double distance.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class Node {

    private int label;
    private double distance;

    /**
     * Constructor with no parameters
     */
    public Node() {

    }

    /**
     * Construct a node with given label.
     *
     * @param label the label of the node.
     */
    public Node(int label) {
        this.label = label;
    }

    /**
     * Construct a node with given label and distance.
     *
     * @param label the label of the node.
     * @param distance the distance data of the node.
     */
    public Node(int label, double distance) {
        this.label = label;
        this.distance = distance;
    }

    /**
     * Compare this Node with given Node d.
     *
     * @param d the given Node d to be compared.
     * @return return -1 when this Node's distance data is smaller than given
     * Node's distance. return 0 when when this Node's distance data is equal to
     * given Node's distance. return 1 when when this Node's distance data is
     * largen than given Node's distance.
     */
    public int compareTo(Node d) {
        if (this.getDistance() < d.getDistance()) {
            return -1;
        } else if (this.getDistance() == d.getDistance()) {
            return 0;
        }
        return 1;
    }

    /**
     * get the label value of this Node.
     *
     * @return an int label
     */
    public int getLabel() {
        return label;
    }

    /**
     * set the label value of this Node.
     *
     * @param label the int value to be given to this Node.
     */
    public void setLabel(int label) {
        this.label = label;
    }

    /**
     * get the distance data of this Node.
     *
     * @return the double value of the distance of this Node.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * set the distance value of this Node.
     *
     * @param distance the double value to be given to this Node.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

}
