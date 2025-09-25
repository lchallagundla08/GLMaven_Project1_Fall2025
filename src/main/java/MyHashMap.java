//import java.util.ArrayList;
//import java.util.Iterator;
//
///**
// * Custom HashMap implementation using ArrayList and GenericQueue for collision handling
// * Uses chaining to resolve hash collisions
// *
// * @param <T> the type of values stored in this map
// */
//public class MyHashMap<T> implements Iterable<T> {
//
//    // ArrayList to store GenericQueues for collision handling
//    private ArrayList<GenericQueue<T>> map;
//    private int size; // Number of key-value mappings
//
//    /**
//     * Constructor that initializes the HashMap with the first key-value pair
//     * @param key the initial key
//     * @param value the initial value
//     */
//    public MyHashMap(String key, T value) {
//        // Initialize ArrayList to size 10
//        map = new ArrayList<>(10);
//
//        // Initialize all positions to null
//        for (int i = 0; i < 10; i++) {
//            map.add(null);
//        }
//
//        size = 0;
//
//        // Add the first key-value pair
//        put(key, value);
//    }
//
//    /**
//     * Adds a key-value pair to the HashMap
//     * @param key the key to add
//     * @param value the value to associate with the key
//     */
//    public void put(String key, T value) {
//        // Generate hash code
//        int hashCode = key.hashCode();
//
//        // Generate hash value (index) using bitwise AND with 9 (size - 1)
//        int hashValue = hashCode & 9;
//
//        // Check if there's already a GenericQueue at this index
//        if (map.get(hashValue) == null) {
//            // No collision - create new GenericQueue with the value
//            GenericQueue<T> newQueue = new GenericQueue<>(value);
//            // Set the code field to the hash code
//            newQueue.getHead().code = hashCode;
//            map.set(hashValue, newQueue);
//            size++;
//        } else {
//            // Collision - check if key already exists
//            GenericQueue<T> existingQueue = map.get(hashValue);
//
//            // Search through the queue to see if key already exists
//            GenericList.Node<T> current = existingQueue.getHead();
//            boolean keyExists = false;
//
//            while (current != null) {
//                if (current.code == hashCode) {
//                    // Key already exists, update the value
//                    current.data = value;
//                    keyExists = true;
//                    break;
//                }
//                current = current.next;
//            }
//
//            // If key doesn't exist, add new node to the queue
//            if (!keyExists) {
//                existingQueue.add(value, hashCode);
//                size++;
//            }
//        }
//    }
//
//    /**
//     * Checks if the HashMap contains the specified key
//     * @param key the key to search for
//     * @return true if the key exists, false otherwise
//     */
//    public boolean contains(String key) {
//        int hashCode = key.hashCode();
//        int hashValue = hashCode & 9;
//
//        GenericQueue<T> queue = map.get(hashValue);
//        if (queue == null) {
//            return false;
//        }
//
//        // Search through the queue for the key
//        GenericList<T>.Node current = queue.getHead();
//        while (current != null) {
//            if (current.code == hashCode) {
//                return true;
//            }
//            current = current.next;
//        }
//
//        return false;
//    }
//
//    /**
//     * Gets the value associated with the specified key
//     * @param key the key to search for
//     * @return the value associated with the key, or null if not found
//     */
//    public T get(String key) {
//        int hashCode = key.hashCode();
//        int hashValue = hashCode & 9;
//
//        GenericQueue<T> queue = map.get(hashValue);
//        if (queue == null) {
//            return null;
//        }
//
//        // Search through the queue for the key
//        GenericList.Node<T> current = queue.getHead();
//        while (current != null) {
//            if (current.code == hashCode) {
//                return current.data;
//            }
//            current = current.next;
//        }
//
//        return null;
//    }
//
//    /**
//     * Returns the number of key-value mappings in the HashMap
//     * @return the number of mappings
//     */
//    public int size() {
//        return size;
//    }
//
//    /**
//     * Checks if the HashMap is empty
//     * @return true if the HashMap contains no key-value mappings
//     */
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//
//    public T replace(String key, T value) {
//        int hashCode = key.hashCode();
//        int hashValue = hashCode & 9;
//
//        GenericQueue<T> queue = map.get(hashValue);
//        if (queue == null) {
//            return null; // Key not found
//        }
//
//        // Search through the queue for the key
//        GenericList.Node<T> current = queue.getHead();
//        while (current != null) {
//            if (current.code == hashCode) {
//                T oldValue = current.data;
//                current.data = value;
//                return oldValue;
//            }
//            current = current.next;
//        }
//
//        return null; // Key not found
//    }
//
//    /**
//     * Returns an iterator over the values in this HashMap
//     * @return iterator for traversing values
//     */
//    @Override
//    public Iterator<T> iterator() {
//        // TODO: Implement after creating HMIterator class
//        throw new UnsupportedOperationException("HMIterator not implemented yet");
//    }
//
//    /**
//     * Gets the underlying ArrayList (for testing purposes)
//     * @return the map ArrayList
//     */
//    public ArrayList<GenericQueue<T>> getMap() {
//        return map;
//    }
//}