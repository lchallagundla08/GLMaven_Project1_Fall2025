//Name: Neha Kamat
//File: HMIterator
/*
import java.util.ArrayList;

public class HMIterator {
    private ArrayList buckets;
    private int bucketIndex = -1;
    private Iterator count = null;

    public HMIterator(ArrayList bucketList){
        if(bucketList == null){
            buckets = new ArrayList();
        }
        else{
            buckets = bucketList;
        }
        nextBucket();
    }

    private void nextBucket(){
        int total =  buckets.size();

        if(bucketIndex == -1){
            bucketIndex = 0;
        }
        else{
            bucketIndex = bucketIndex + 1;
        }
        count = null;
        while(bucketIndex != total){
            Object first =  buckets.get(bucketIndex);
            if(first instanceOf GenericQueue){
                java.util.ArrayList temp = (java.util.ArrayList)buckets;
                buckets = (java.util.ArrayList)temp;
                if(temp != null && temp.hasNext()){
                    count = first;
                    return;
                }
            }
            bucketIndex = bucketIndex + 1;
        }
    }

    public boolean hasNext(){
        if(count == null){
            return false;
        }
        if(count.hasNext()){
            return true;
        }
        nextBucket();
        return count != null && count.hasNext();
    }

    public Object next(){
        if(!hasNext()){
            return null;
        }
        return count.next();
    }

}

*/

// File: HMIterator.java
// Author: CS 342 Project #1 Fall 2025
// Description: Iterator for MyHashMap that traverses through all values

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for MyHashMap that provides traversal through all values
 * Iterates through each bucket and then through each queue in the bucket
 * This is a standalone iterator class that can be used externally
 * @param <T> the type of elements returned by this iterator
 */
public class HMIterator<T> implements Iterator<T> {

    // List of all buckets in the hash map
    private final ArrayList<GenericQueue<T>> buckets;
    // Current bucket index being examined
    private int bucketIndex;
    // Current iterator for the queue in the current bucket
    private Iterator<T> currentQueueIterator;
    // Total number of elements iterated so far
    private int elementsIterated;
    // Total size of the hash map
    private int totalSize;

    /**
     * Constructor for HMIterator
     * @param bucketList the ArrayList of GenericQueues from MyHashMap
     */
    public HMIterator(ArrayList<GenericQueue<T>> bucketList) {
        if (bucketList == null) {
            this.buckets = new ArrayList<>();
        } else {
            this.buckets = bucketList;
        }

        this.bucketIndex = -1;
        this.currentQueueIterator = null;
        this.elementsIterated = 0;
        this.totalSize = calculateTotalSize();

        // Move to the first non-empty bucket
        moveToNextBucket();
    }

    /**
     * Calculate the total number of elements in all buckets
     * @return total number of elements
     */
    private int calculateTotalSize() {
        int size = 0;
        for (GenericQueue<T> bucket : buckets) {
            if (bucket != null) {
                size += bucket.getLength();
            }
        }
        return size;
    }

    /**
     * Move to the next bucket that contains a non-empty queue
     */
    private void moveToNextBucket() {
        // Move to next bucket
        bucketIndex++;
        currentQueueIterator = null;

        // Find the next bucket with a non-empty queue
        while (bucketIndex < buckets.size()) {
            GenericQueue<T> currentBucket = buckets.get(bucketIndex);

            // If bucket is not null and has elements, get its iterator
            if (currentBucket != null && currentBucket.getLength() > 0) {
                currentQueueIterator = currentBucket.iterator();

                // If the iterator has elements, we're ready
                if (currentQueueIterator.hasNext()) {
                    return;
                }
            }

            // Move to next bucket if current one is empty
            bucketIndex++;
        }

        // No more buckets with elements
        currentQueueIterator = null;
    }

    /**
     * Checks if there are more elements to iterate over
     * @return true if there are more elements, false otherwise
     */
    @Override
    public boolean hasNext() {
        // If we don't have a current iterator, no more elements
        if (currentQueueIterator == null) {
            return false;
        }

        // If current iterator has elements, return true
        if (currentQueueIterator.hasNext()) {
            return true;
        }

        // Current iterator is exhausted, try to move to next bucket
        moveToNextBucket();

        // Check if we found another bucket with elements
        return currentQueueIterator != null && currentQueueIterator.hasNext();
    }

    /**
     * Returns the next element in the iteration
     * @return the next element
     * @throws NoSuchElementException if there are no more elements
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the hash map iteration");
        }

        T value = currentQueueIterator.next();
        elementsIterated++;
        return value;
    }

    /**
     * Gets the current bucket index being processed
     * @return current bucket index, or -1 if not started
     */
    public int getCurrentBucketIndex() {
        return bucketIndex;
    }

    /**
     * Gets the number of elements iterated so far
     * @return number of elements processed
     */
    public int getElementsIterated() {
        return elementsIterated;
    }

    /**
     * Gets the total size of elements in the hash map
     * @return total number of elements
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * Checks if there are more elements remaining
     * @return number of elements remaining to iterate
     */
    public int getRemainingElements() {
        return totalSize - elementsIterated;
    }

    /**
     * Resets the iterator to the beginning
     * This is a convenience method not in the standard Iterator interface
     */
    public void reset() {
        this.bucketIndex = -1;
        this.currentQueueIterator = null;
        this.elementsIterated = 0;
        this.totalSize = calculateTotalSize();
        moveToNextBucket();
    }

    /**
     * Checks if the iterator has processed all elements
     * @return true if all elements have been processed
     */
    public boolean isComplete() {
        return elementsIterated >= totalSize;
    }

    /**
     * Gets information about the current state of iteration
     * @return string describing current iteration state
     */
    public String getIterationStatus() {
        return String.format("Bucket: %d/%d, Elements: %d/%d",
                bucketIndex + 1, buckets.size(),
                elementsIterated, totalSize);
    }
}