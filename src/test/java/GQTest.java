// File: GenericQueueTest.java
// Author: Your Name
// Description: JUnit test cases for GenericQueue class

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class GQTest {

    @Test
    void testConstructorWithParameter() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        assertEquals(1, queue.getLength());
        assertEquals(10, queue.getHead().data);
    }

    @Test
    void testDefaultConstructor() {
        GenericQueue<String> queue = new GenericQueue<>();
        assertEquals(0, queue.getLength());
        assertNull(queue.getHead());
    }

    @Test
    void testAdd() {
        GenericQueue<Integer> queue = new GenericQueue<>(1);
        queue.add(2);
        queue.add(3);
        assertEquals(3, queue.getLength());
        assertEquals(1, queue.getHead().data); // still starts with 1
    }

    @Test
    void testAddWithCode() {
        GenericQueue<String> queue = new GenericQueue<>("first");
        queue.add("second", 99);
        assertEquals(2, queue.getLength());
        assertEquals("first", queue.getHead().data);
    }

    @Test
    void testDelete() {
        GenericQueue<Integer> queue = new GenericQueue<>(1);
        queue.add(2);
        queue.add(3);
        Integer deleted = queue.delete();
        assertEquals(1, deleted);
        assertEquals(2, queue.getHead().data);
        assertEquals(2, queue.getLength());
    }

    @Test
    void testEnqueue() {
        GenericQueue<Integer> queue = new GenericQueue<>(5);
        queue.enqueue(6);
        assertEquals(2, queue.getLength());
    }

    @Test
    void testDequeue() {
        GenericQueue<Integer> queue = new GenericQueue<>(100);
        queue.enqueue(200);
        Integer removed = queue.dequeue();
        assertEquals(100, removed);
        assertEquals(1, queue.getLength());
    }

    @Test
    void testForEachLoop() {
        GenericQueue<Integer> queue = new GenericQueue<>(1);
        queue.add(2);
        queue.add(3);

        List<Integer> values = new ArrayList<>();
        for (Integer val : queue) {
            values.add(val);
        }

        assertEquals(List.of(1, 2, 3), values);
    }
}
