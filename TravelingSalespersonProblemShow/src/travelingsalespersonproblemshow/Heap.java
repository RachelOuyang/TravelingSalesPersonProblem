/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalespersonproblemshow;

/**
 * Heap class that contains an array of Node. This is a Min binary heap stored
 * as an array where the first non-empty node in the heap is the smallest node
 * in the heap and the parent node is larger than its left and right child node.
 *
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 11/04/2015
 */
public class Heap {

    private Node[] h;
    private int numElement;
    private int size;

    /**
     * create a heap of size N in in private array h[] of length N+1 with h[0]
     * unused and heap in h[1] through h[N]
     *
     * @param n the size of heap
     */
    public Heap(int n) {
        size = n;
        h = new Node[n + 1];
    }

    /**
     * pre: Node d cannot be empty. h[0] unused and use heap in h[1] through
     * h[N]. 
     * Add a new Node to the heap.
     *
     * @param d the new Node d to be added.
     */
    public void add(Node d) {
        numElement++;
        int i = 1;
        while (h[i] != null) {
            i++;
        }
        h[i] = d;
        //check if violate min Heap's definition and make corresponding changes.
        reheapifyBottomUp(i);
    }

    /**
     * if the heap order is violated because a node's value becomes smaller than
     * that node's parents value, then we can make progress toward fixing the
     * violation by exchanging the node with its parent. After the exchange the
     * node is smaller than both its children(one is the old parent, and the
     * other is bigger than the old parent because it was a child of that node)
     * but if the node may still be smaller than its parent,then fix the
     * violation again, until we reach a node with the smaller key or the root.
     *
     * @param k the index of the node in the heap that needs to check if violate
     * Heap's definition and make changes.
     */
    private void reheapifyBottomUp(int k) {
        while (k > 1 && less(k, k / 2)) {
            exchange(k, k / 2);
            k = k / 2;
        }

    }

    /**
     * check if the node with index i in this heap is smaller than the node with
     * index j in this heap.
     *
     * @param i the index of the first node to be compared.
     * @param j the index of the second node to be compared.
     * @return true if for this heap h[], h[i]<h[j], and false if not.
     */
    private boolean less(int i, int j) {
        return h[i].compareTo(h[j]) == -1;
    }

    /**
     * exchange the position of the node with index i in this heap with the node
     * with index j in this heap.
     *
     * @param i the index of the first node to be exchanged.
     * @param j the index of the second node to be exchanged.
     */
    private void exchange(int i, int j) {
        Node tmp = new Node();
        tmp = h[i];
        h[i] = h[j];
        h[j] = tmp;
    }

    /**
     * delete the min node,i.e. the root node starts with index 1 from the heap.
     * Exchange the min node with the last node in the heap, delete the last
     * node after the exchange and make changes if after deletion it violates
     * the heap's definition. 
     * Pre: the heap is not empty.
     *
     * @return the label value of the delete node.
     */
    public int deleteMin() {
        Node delete = h[1];
        exchange(1, numElement);
        h[numElement] = null;

        numElement--;
        reheapifyTopDown(1);

        return delete.getLabel();
    }

    /**
     *  if the heap order violated because a node's value becomes larger than
     * one or both of that node's children's keys,then we can make progress
     * toward fixing the violation by exchanging the node with the smaller
     * of its two children. This swith may cause a violation at the child,
     * if so, then fix that violation in the same way,and so forth,moving
     * down the heap until we reach a node with both children larger, or the
     * bottom.
     *
     * @param k the index of the node in the heap that needs to check if violate
     * Heap's definition and make changes.
     */
    private void reheapifyTopDown(int k) {
        while (2 * k <= numElement) {
            int j = 2 * k;
            if (j < numElement && less(j + 1, j)) {
                j++;
            }
            if (less(k, j)) {
                break;
            }
            exchange(k, j);
            k = j;
        }

    }
   /**
    * update the distance value of a node with label v. 
    * @param v the label value used to find the node to be updated.
    * @param value the new value to be set for the node.
    */
    public void update(int v, double value) {
        for (int i = 1; i <= numElement; i++) {
            if (h[i].getLabel() == v) {
                h[i].setDistance(value);
                reheapifyBottomUp(i);
                break;
            }
        }
    }
    
    /**
     * get number of existing nodes in the heap.
     * @return number of element in the heap.
     */
    public int numOfElement() {
        return numElement;
    }
    /**
     * get size of the heap.
     * @return size of the heap.
     */
    public int size() {
        return size;
    }
    
    /**
     * check if the heap is empty.
     * @return true if the heap has no element and false if not.
     */
    public boolean isEmpty() {
        return numElement == 0;
    }

    /**
     * check if the heap contains a node with label v
     * @param v the label of the node 
     * @return true if the heap contains a node with label v and false if not.
     */
    public boolean contains(int v) {
        for (int i = 1; i <= numElement; i++) {
            if (h[i].getLabel() == v) {
                return true;
            }
        }
        return false;
    }
    /**
     * print the heap result.
     */
    public void printHeap() {
        if (isEmpty()) {
            System.out.println("Now it is Empty");
        }
        for (int i = 1; i <= numElement; i++) {
            System.out.println(h[i].getDistance());
        }
    }

    /**
     * test main Node comes in as: 5.0 2.0 3.0 1.0 4.0 Result Heap: 1.0 2.0 3.0
     * 5.0 4.0
     *
     * @param args
     */
    public static void main(String[] args) {
        Heap testHeap = new Heap(5);
        testHeap.add(new Node(0, 5.0));
        testHeap.add(new Node(1, 2.0));
        testHeap.add(new Node(2, 3.0));
        testHeap.add(new Node(3, 1.0));
        testHeap.add(new Node(4, 4.0));
        testHeap.printHeap();
        System.out.println("----------");

        System.out.println("delete: " + testHeap.deleteMin());
        testHeap.printHeap();
        System.out.println("----------");

        System.out.println("delete: " + testHeap.deleteMin());
        testHeap.printHeap();
        System.out.println("----------");

        System.out.println("delete: " + testHeap.deleteMin());
        testHeap.printHeap();
        System.out.println("----------");

        System.out.println("delete: " + testHeap.deleteMin());
        testHeap.printHeap();
        System.out.println("----------");

        System.out.println("delete: " + testHeap.deleteMin());
        testHeap.printHeap();
    }

}
