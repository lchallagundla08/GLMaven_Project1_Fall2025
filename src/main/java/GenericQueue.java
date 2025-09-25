// File: GenericQueue.java
// Author: Lakshmi Sanjana Challagundla,lchal3,lchal3@uic.edu
// Description:
//

public class GenericQueue<T> extends GenericList<T> {

    // The last node in the list (tail of the queue)
    private Node<T> tail;

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
            return null; // nothing to delete
        }

        // If there is only one node, reset head and tail
        if (getLength() == 1) {
            T data = getHead().data;

            setHead(null);
            tail = null;
            setLength(0);
            return data;
        }

        // Otherwise, walk the list to find the node before the tail
        Node<T> curr = getHead();
        while (curr.next != tail) {
            curr = curr.next;
        }

        // Now curr is the node before tail
        T data = tail.data;
        tail = curr;
        tail.next = null;

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