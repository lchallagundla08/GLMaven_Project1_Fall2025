//Name: Neha Kamat
//File: GLLIterator
// Author: CS 342 Project #1 Fall 2025
// Description: Iterator for GenericList that traverses from head to tail

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for GenericList that provides forward traversal (head to tail)
 * Implements the Iterator interface for standard iteration behavior
 * This is a standalone iterator class that can be used externally
 * @param <T> the type of elements returned by this iterator
 */
public class GLLIterator<T> implements Iterator<T> {

    // Reference to the GenericList being iterated
    private final GenericList<T> listRef;
    // Current index position in the iteration
    private int index;

    //Constructor for GLLIterator
    public GLLIterator(GenericList<T> srcList) {
        this.listRef = srcList;
        this.index = 0;
    }

    //Checks if there are more elements to iterate over
    @Override
    public boolean hasNext() {
        if (listRef == null) {
            return false;
        }
        return index < listRef.getLength();
    }

    //Returns the next element in the iteration
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the iteration");
        }
        T value = listRef.get(index);
        index++;
        return value;
    }

    //Resets the iterator to the beginning of the list
    public void reset() {
        this.index = 0;
    }

    //Gets the current position in the iteration
    public int getCurrentIndex() {
        return index;
    }

    //Checks if the iterator has a previous element
    public boolean hasPrevious() {
        return index > 0;
    }

    //Returns the previous element and moves the iterator backward
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException("No previous elements in the iteration");
        }
        index--;
        return listRef.get(index);
    }
}
