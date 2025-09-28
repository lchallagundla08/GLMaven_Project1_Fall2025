//Name: Neha Kamat, nkama4, nkama4@uic.edu
// File: HMIterator.java
// Author: CS 342 Project #1 Fall 2025
// Description: Iterator for MyHashMap that traverses through all values

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Iterator for MyHashMap that traverses through all the values and then iterates through each bucket
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

    //Constructor for HMIterator
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

    //Calculates the total number of elements in all buckets
    private int calculateTotalSize() {
        int size = 0;
        for (GenericQueue<T> bucket : buckets) {
            if (bucket != null) {
                size += bucket.getLength();
            }
        }
        return size;
    }

    //Moves to the next bucket that contains a non-empty queue
    private void moveToNextBucket() {
        bucketIndex++;
        currentQueueIterator = null;

        while (bucketIndex < buckets.size()) {
            GenericQueue<T> currentBucket = buckets.get(bucketIndex);

            // If bucket is not null and has elements, then its iterated through
            if (currentBucket != null && currentBucket.getLength() > 0) {
                currentQueueIterator = currentBucket.iterator();

                //ietrates through if it has already existing elemnts
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

    //Checks if there are more elements to iterate over
    public boolean hasNext() {
        // If we don't have a current iterator, no more elements
        if (currentQueueIterator == null) {
            return false;
        }

        if (currentQueueIterator.hasNext()) {
            return true;
        }
        moveToNextBucket();

        // Check if we found another bucket with elements
        return currentQueueIterator != null && currentQueueIterator.hasNext();
    }

    //Returns the next element in the iteration
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the hash map iteration");
        }

        T value = currentQueueIterator.next();
        elementsIterated++;
        return value;
    }

    //Gets the current bucket index being processed
    public int getCurrentBucketIndex() {
        return bucketIndex;
    }

    //Gets the number of elements iterated so far
    public int getElementsIterated() {
        return elementsIterated;
    }

    //Gets the total size of elements in the hash map
    public int getTotalSize() {
        return totalSize;
    }

    //Checks if there are more elements remaining
    public int getRemainingElements() {
        return totalSize - elementsIterated;
    }

    //Resets the iterator to the beginning
    public void reset() {
        this.bucketIndex = -1;
        this.currentQueueIterator = null;
        this.elementsIterated = 0;
        this.totalSize = calculateTotalSize();
        moveToNextBucket();
    }

    //Checks if the iterator has processed all elements
    public boolean isComplete() {
        return elementsIterated >= totalSize;
    }

    //Gets information about the current state of iteration
    public String getIterationStatus() {
        return String.format("Bucket: %d/%d, Elements: %d/%d",
                bucketIndex + 1, buckets.size(),
                elementsIterated, totalSize);
    }
}