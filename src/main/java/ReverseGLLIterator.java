//Name: Neha Kamat, nkama4, nkama4@uic.edu
// File: ReverseGLLIterator.java
// Author: CS 342 Project #1 Fall 2025
// Description: Iterator for GenericList that traverses from tail to head

import java.util.Iterator;
import java.util.NoSuchElementException;

//Iterator for GenericList that reverses backwards from tail to head
public class ReverseGLLIterator<T> implements Iterator<T> {

    // Reference to the GenericList being iterated
    private final GenericList<T> listRef;
    // Current index position in the reverse iteration (starts from end)
    private int index;

    //Constructor for ReverseGLLIterator
    public ReverseGLLIterator(GenericList<T> srcList) {
        this.listRef = srcList;

        // Start from the last element if list exists, otherwise -1
        if (listRef == null || listRef.getLength() == 0) {
            index = -1;
        } else {
            index = listRef.getLength() - 1;
        }
    }

    //Checks if there are more elements to iterate over in reverse
    public boolean hasNext() {
        return index >= 0;
    }

    //Returns the next element in the reverse iteration
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the reverse iteration");
        }
        T value = listRef.get(index);
        index--;
        return value;
    }

    //Resets the iterator to the end of the list
    public void reset() {
        if (listRef == null || listRef.getLength() == 0) {
            this.index = -1;
        } else {
            this.index = listRef.getLength() - 1;
        }
    }

    //Gets the current position in the reverse iteration
    public int getCurrentIndex() {
        return index;
    }

    //Checks if the iterator has a previous element so if the next forward element exists
    public boolean hasPrevious() {
        if (listRef == null) {
            return false;
        }
        return index < (listRef.getLength() - 1);
    }

    //Returns the previous element in reverse iteration context
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException("No previous elements in the reverse iteration");
        }
        index++;
        return listRef.get(index);
    }

    //Checks if we're at the beginning of the reverse iteration
    public boolean isAtStart() {
        if (listRef == null || listRef.getLength() == 0) {
            return true;
        }
        return index == (listRef.getLength() - 1);
    }

    //Checks if we're at the end of the reverse iteration
    public boolean isAtEnd() {
        return index < 0;
    }
}
