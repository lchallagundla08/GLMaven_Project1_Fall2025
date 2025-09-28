//Name: Neha Kamat
//File:ReverseGLLiterator

// File: ReverseGLLIterator.java
// Author: CS 342 Project #1 Fall 2025
// Description: Iterator for GenericList that traverses from tail to head

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for GenericList that provides reverse traversal (tail to head)
 * Implements the Iterator interface for standard iteration behavior
 * This is a standalone iterator class that can be used externally
 * @param <T> the type of elements returned by this iterator
 */
public class ReverseGLLIterator<T> implements Iterator<T> {

    // Reference to the GenericList being iterated
    private final GenericList<T> listRef;
    // Current index position in the reverse iteration (starts from end)
    private int index;

    /**
     * Constructor for ReverseGLLIterator
     * @param srcList the GenericList to iterate over in reverse
     */
    public ReverseGLLIterator(GenericList<T> srcList) {
        this.listRef = srcList;

        // Start from the last element if list exists, otherwise -1
        if (listRef == null || listRef.getLength() == 0) {
            index = -1;
        } else {
            index = listRef.getLength() - 1;
        }
    }

    /**
     * Checks if there are more elements to iterate over in reverse
     * @return true if there are more elements, false otherwise
     */
    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    /**
     * Returns the next element in the reverse iteration
     * @return the next element going backwards
     * @throws NoSuchElementException if there are no more elements
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the reverse iteration");
        }
        T value = listRef.get(index);
        index--;
        return value;
    }

    /**
     * Resets the iterator to the end of the list (for reverse iteration)
     * This is a convenience method not in the standard Iterator interface
     */
    public void reset() {
        if (listRef == null || listRef.getLength() == 0) {
            this.index = -1;
        } else {
            this.index = listRef.getLength() - 1;
        }
    }

    /**
     * Gets the current position in the reverse iteration
     * @return the current index
     */
    public int getCurrentIndex() {
        return index;
    }

    /**
     * Checks if the iterator has a previous element (in reverse context, this means forward)
     * @return true if there is a previous element
     */
    public boolean hasPrevious() {
        if (listRef == null) {
            return false;
        }
        return index < (listRef.getLength() - 1);
    }

    /**
     * Returns the previous element in reverse iteration context (moves forward in the list)
     * @return the previous element
     * @throws NoSuchElementException if there are no previous elements
     */
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException("No previous elements in the reverse iteration");
        }
        index++;
        return listRef.get(index);
    }

    /**
     * Checks if we're at the beginning of the reverse iteration (last element of list)
     * @return true if at the start of reverse iteration
     */
    public boolean isAtStart() {
        if (listRef == null || listRef.getLength() == 0) {
            return true;
        }
        return index == (listRef.getLength() - 1);
    }

    /**
     * Checks if we're at the end of the reverse iteration (first element of list)
     * @return true if at the end of reverse iteration
     */
    public boolean isAtEnd() {
        return index < 0;
    }
}
