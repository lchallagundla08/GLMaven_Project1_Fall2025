// File: GenericQueue.java
// Author: Lakshmi Sanjana Challagundla,lchal3,lchal3@uic.edu
// Description:
//

public class GenericQueue<T> extends GenericList<T> {

    // The last node in the list (tail of the queue)
    private Node<T> tail;

    //default
    public GenericQueue() {
        this.tail = null;
    }

    // Constructor
    public GenericQueue(T data) {
        Node<T> firstNode = new Node<>(data);

        setHead(firstNode);
        tail = firstNode;
        setLength(1);
    }

    // Add a new node at the end of the queue
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


    // Delete the last node in the queue and return its value
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

    // Enqueue = add to the back of the queue
    public void enqueue(T data) {
        add(data);
    }

    // // Dequeue = delete->removes the last node
    public T dequeue() {
        return delete();
    }

}