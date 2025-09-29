// File: MyHashMap.java
// Author: Lakshmi Sanjana Challagundla,lchal3,lchal3@uic.edu
// Description: The class implements a hash map data structure using separate chaining with a fixed capacity of 10 (indices 0-9),
// where each bucket in the underlying ArrayList is a GenericQueue<T> that stores the values, and the hash code of the
// String key is used as an optional code within the queue's nodes to uniquely identify and manage key-value pairs.

import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T> {

    // An arraylist of queues.
    private ArrayList<GenericQueue<T>> map;

    private int size; //How many key-value pairs are in the map.

    // Constructor
    public MyHashMap(String key, T value) {
        map = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            map.add(null);
        }

        size = 0;// 0 items to start

        //Add the very first key-value
        put(key, value);
    }

    // Adds a key-value pair to the HashMap.
    public void put(String key, T value) {
        int hashValue = key.hashCode() & 9;


        if (map.get(hashValue) == null) {

            GenericQueue<T> newQueue = new GenericQueue<>(value);


            newQueue.getHead().code = key.hashCode();


            map.set(hashValue, newQueue);
            size++; // Increase the count of items.
        } else {

            GenericQueue<T> existingQueue = map.get(hashValue);


            GenericList.Node<T> curr = existingQueue.getHead();
            boolean keyExists = false;

            while (curr != null) {

                if (curr.code == key.hashCode()) {

                    curr.data = value;
                    keyExists = true;
                    break;
                }
                curr = curr.next;
            }


            if (!keyExists) {
                existingQueue.add(value, key.hashCode());
                size++;
            }
        }
    }


    public boolean contains(String key) {


        int hashValue = key.hashCode() & 9;


        GenericQueue<T> queue = map.get(hashValue);


        if (queue == null) {
            return false;
        }

        // Search through the queue to find a matching hash code.
        GenericList.Node<T> curr = queue.getHead();
        while (curr != null) {
            if (curr.code == key.hashCode()) {
                return true;
            }
            curr = curr.next;
        }

        return false; //eld of queue, key not found.
    }

    // Retrieves the value associated with a specific key.
    public T get(String key) {
        int hashValue = key.hashCode() & 9;

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) {
            return null;
        }

        // Search through the chain/queue for the matching hash code.
        GenericList.Node<T> curr = queue.getHead();
        while (curr != null) {
            if (curr.code == key.hashCode()) {

                return (T) curr.data;
            }
            curr = curr.next;
        }

        return null;
    }

    // How many key-value pairs are stored in the map.
    public int size() {
        return size;
    }

    // Checks if the map is completely empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // Finds an existing key and changes its value.
    public T replace(String key, T value) {

        int hashValue = key.hashCode() & 9;

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) {
            return null;
        }

        GenericList.Node<T> curr = queue.getHead();
        while (curr != null) {
            if (curr.code == key.hashCode()) {

                T oldValue = (T) curr.data;

                curr.data = value; // Set the new value.
                return oldValue; // Return the original value.
            }
            curr = curr.next;
        }

        return null;
    }


    @Override
    public Iterator<T> iterator() {
        return new HMIterator<>(map);
    }


    public ArrayList<GenericQueue<T>> getMap() {
        return map;
    }
}