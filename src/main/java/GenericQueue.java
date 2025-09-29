// File: GenericQueue.java
// Author: Lakshmi Sanjana Challagundla,lchal3,lchal3@uic.edu
// Description: The class is a generic queue using the singly-linked list structure inherited from GenericList,

public class GenericQueue<T> extends GenericList<T> {

    //The last node in the list
    private Node<T> tail;

    //Default
    public GenericQueue() {
        this.tail = null;
    }

    //Constructor
    public GenericQueue(T data) {
        Node<T> firstNode = new Node<>(data);

        setHead(firstNode);
        tail = firstNode;
        setLength(1);
    }

    //Add a new node at the end of the queue
    @Override
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (getLength() == 0) {
            setHead(newNode);
        } else {
            tail.next = newNode;
        }
        tail = newNode;

        setLength(getLength() + 1);
    }

    public void add(T data, int code) {
        Node<T> newNode = new Node<>(data, code);

        if (getLength() == 0) {
            setHead(newNode);
        } else {
            tail.next = newNode;
        }
        tail = newNode;

        setLength(getLength() + 1);
    }


    //Delete the last node in the queue then return its value
    @Override
    public T delete() {
        if (getLength() == 0) {
            return null;
        }

        T data = getHead().data;
        setHead(getHead().next);

        if (getHead() == null) {
            tail = null;
        }
        setLength(getLength() - 1);

        return data;
    }

    //Enqueue
    public void enqueue(T data) {
        add(data);
    }

    //Dequeue
    public T dequeue() {
        return delete();
    }

}