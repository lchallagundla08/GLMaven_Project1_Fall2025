// File: GenericList.java
// Author: Lakshmi Sanjana Challagundla,lchal3,lchal3@uic.edu
// Description: Abstract generic singly linked list providing core functionality
// for CS 342 Project #1 Fall 2025.
//

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class GenericList<T> implements Iterable<T> {

    //The first node in listg
    private Node<T> head;
    //Total num of nodes in list
    private int length;

    //Inner Node class
    protected static class Node<T> {
        T data;        // the value stored in the node
        int code;      // an optional number you can use
        Node<T> next;  // the next node in the list

        // Make a node with just data
        Node(T data) {
            this.data = data;
            this.code = 0;
            this.next = null;
        }

        // Make a node with data and a code
        Node(T data, int code) {
            this.data = data;
            this.code = code;
            this.next = null;
        }
    }

    //list constructor
    protected GenericList() {
        head = null;
        length = 0;
    }

    //Print out all items in the list, If there are none, print "Empty List"
    // Print out all the items in the list without using the iterator
    public void print() {
        if (length == 0) {
            System.out.println("Empty List");
            return;
        }

        Node<T> current = head;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }


    //Abstract add and delete
    public abstract void add(T data);
    public abstract T delete();

    //Copy all items into an ArrayList and return it
    public ArrayList<T> dumpList() {
        ArrayList<T> list = new ArrayList<>(length);
        Node<T> curr = head;

        while (curr != null) {
            list.add(curr.data);
            curr= curr.next;
        }
        return list; // give back the ArrayList
    }

    //  Get the item at a certain index
    public T get(int index) {
        if (index < 0 || index >= length) return null;
        Node<T> curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    //Set the item at a certain index to a new value, Return the old value
    public T set(int index, T element) {
        if (index < 0 || index >= length) return null;
        Node<T> curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        T prevVal = curr.data;

        curr.data = element;
        return prevVal;
    }

    //Getters and setters
    //Get how many items are in the list
    public int getLength() {
        return length;
    }

    //Change how many items are in the list (used inside the class)
    protected void setLength(int length) {
        this.length = length;
    }

    //Get the first node in the list
    protected Node<T> getHead() {
        return head;
    }

    //Change the first node in the list
    protected void setHead(Node<T> head) {
        this.head = head;
    }



    //This makes the list work with "for-each" loops
    @Override
    public Iterator<T> iterator() {
        return new GLLIterator();
    }

    //Return an iterator that goes backwards (last to first)
    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator();
    }

    //Iterator that goes forward (first to last)
    private class GLLIterator implements Iterator<T> {
        private Node<T> curr = head;

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T data = curr.data;

            curr = curr.next;
            return data;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }


    }

    //Iterator that goes backward (last to first)
    private class ReverseGLLIterator implements Iterator<T> {
        private final ArrayList<T> reversed;
        private int cursor;

        ReverseGLLIterator() {
            reversed = dumpList();
            cursor = reversed.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return reversed.get(cursor--);
        }
    }

}