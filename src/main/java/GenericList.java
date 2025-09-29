// File: GenericList.java
// Author: Lakshmi Sanjana Challagundla,lchal3,lchal3@uic.edu
// Description: The class provides the foundational structure for a generic singly-linked list

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class GenericList<T> implements Iterable<T> {

    //The first node in list
    private Node<T> head;
    //Total num of nodes in list
    private int length;

    //Inner Node class
    protected static class Node<T> {
        T data;        //Value stored in the node
        int code;      //Optional
        Node<T> next;  //Next node

        //node with just data
        Node(T data) {
            this.data = data;
            this.code = 0;
            this.next = null;
        }

        //node with data and a code
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

    //Print out all items in the list, If there are none -> print "Empty List"
    public void print() {
        if (length == 0) {
            System.out.println("Empty List");
            return;
        }

        Node<T> curr = head;

        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
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
    public int getLength() {
        return length;
    }


    protected void setLength(int length) {
        this.length = length;
    }


    protected Node<T> getHead() {
        return head;
    }


    protected void setHead(Node<T> head) {
        this.head = head;
    }




    @Override
    public Iterator<T> iterator() {
        return new GLLIterator();
    }

    //Returns backwards iterator
    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator();
    }

    //The iterator that goes forward
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

    //Reverse Iterator
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