// File: HMTest.java
// Name: Neha Kamat, nkama4, nkama4@uic.edu
// Author: CS 342 Project #1 Fall 2025
// Description: JUnit 5 test cases for MyHashMap class and HMIterator

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;


//JUnit 5 test class for MyHashMap functionality
//Tests all required methods: constructor, put, get, contains, size, isEmpty, replace
//Tests HMIterator and for-each loop functionality
//Tests collision handling

public class HMTest {

    @Test
    //Constructor initializes with one key-value
    public void testConstructor() {
        MyHashMap<String> hashMap = new MyHashMap<>("testKey", "testValue");

        assertEquals(1, hashMap.size(), "Constructor should initialize with size 1");
        assertEquals("testValue", hashMap.get("testKey"), "Constructor should store initial key-value pair");
        assertTrue(hashMap.contains("testKey"), "Constructor should make initial key findable");
        assertFalse(hashMap.isEmpty(), "Constructor should create non-empty map");

        ArrayList<GenericQueue<String>> map = hashMap.getMap();
        assertEquals(10, map.size(), "Constructor should initialize ArrayList to size 10");
    }

    @Test
    //Put method adds new key-value
    public void testPutNewKeys() {
        MyHashMap<Integer> hashMap = new MyHashMap<>("key1", 100);
        hashMap.put("key2", 200);
        hashMap.put("key3", 300);
        hashMap.put("key4", 400);

        assertEquals(4, hashMap.size(), "Put should increase size for new keys");
        assertEquals(100, hashMap.get("key1"), "Put should store key1 correctly");
        assertEquals(200, hashMap.get("key2"), "Put should store key2 correctly");
        assertEquals(300, hashMap.get("key3"), "Put should store key3 correctly");
        assertEquals(400, hashMap.get("key4"), "Put should store key4 correctly");
    }

    @Test
    //Put method updates existing key
    public void testPutUpdateExistingKey() {
        MyHashMap<Integer> hashMap = new MyHashMap<>("key1", 100);
        hashMap.put("key2", 200);

        hashMap.put("key1", 999);

        assertEquals(2, hashMap.size(), "Put should not increase size when updating existing key");
        assertEquals(999, hashMap.get("key1"), "Put should update value for existing key");
    }

    @Test
    //Get method retrieves correct value
    public void testGetExistingKeys() {
        MyHashMap<String> hashMap = new MyHashMap<>("fruit", "apple");
        hashMap.put("color", "red");
        hashMap.put("number", "42");

        assertEquals("apple", hashMap.get("fruit"), "Get should return correct value for fruit");
        assertEquals("red", hashMap.get("color"), "Get should return correct value for color");
        assertEquals("42", hashMap.get("number"), "Get should return correct value for number");
    }

    @Test
    //Get method returns null
    public void testGetNonExistentKey() {
        MyHashMap<String> hashMap = new MyHashMap<>("fruit", "apple");

        assertNull(hashMap.get("nonexistent"), "Get should return null for non-existent key");
    }

    @Test
    //Contains method returns true
    public void testContainsExistingKeys() {
        MyHashMap<Double> hashMap = new MyHashMap<>("pi", 3.14159);
        hashMap.put("e", 2.71828);
        hashMap.put("golden", 1.61803);

        assertTrue(hashMap.contains("pi"), "Contains should return true for pi");
        assertTrue(hashMap.contains("e"), "Contains should return true for e");
        assertTrue(hashMap.contains("golden"), "Contains should return true for golden");
    }

    @Test
    //Contains method returns false
    public void testContainsNonExistentKey() {
        MyHashMap<Double> hashMap = new MyHashMap<>("pi", 3.14159);

        assertFalse(hashMap.contains("nonexistent"), "Contains should return false for non-existent key");
    }

    @Test
    //Size method tracks number of key-value pairs correctly
    public void testSizeTracksCorrectly() {
        MyHashMap<String> hashMap = new MyHashMap<>("first", "1st");
        assertEquals(1, hashMap.size(), "Size should be 1 after constructor");

        hashMap.put("second", "2nd");
        assertEquals(2, hashMap.size(), "Size should be 2 after adding second element");

        hashMap.put("third", "3rd");
        assertEquals(3, hashMap.size(), "Size should be 3 after adding third element");
    }

    @Test
    //Size method does not change when updating existing key
    public void testSizeDoesNotChangeOnUpdate() {
        MyHashMap<String> hashMap = new MyHashMap<>("first", "1st");
        hashMap.put("second", "2nd");
        hashMap.put("third", "3rd");

        hashMap.put("first", "FIRST");

        assertEquals(3, hashMap.size(), "Size should remain 3 after updating existing key");
    }

    @Test
    //IsEmpty method returns false for non-empty HashMap
    public void testIsEmptyReturnsFalse() {
        MyHashMap<String> hashMap = new MyHashMap<>("key", "value");

        assertFalse(hashMap.isEmpty(), "IsEmpty should return false for HashMap with elements");
    }

    @Test
    //Replace method updates existing key and returns old value
    public void testReplaceExistingKey() {
        MyHashMap<String> hashMap = new MyHashMap<>("replaceKey", "originalValue");

        String oldValue = hashMap.replace("replaceKey", "newValue");

        assertEquals("originalValue", oldValue, "Replace should return old value");
        assertEquals("newValue", hashMap.get("replaceKey"), "Replace should update to new value");
    }

    @Test
    //Replace method returns null for non-existent key
    public void testReplaceNonExistentKey() {
        MyHashMap<String> hashMap = new MyHashMap<>("replaceKey", "originalValue");

        String result = hashMap.replace("nonExistentKey", "someValue");

        assertNull(result, "Replace should return null for non-existent key");
    }

    @Test
    //Collision handling
    public void testCollisionHandling() {
        MyHashMap<String> hashMap = new MyHashMap<>("Cozmo", "Dog");
        hashMap.put("omzoC", "Cat");

        assertEquals(2, hashMap.size(), "Size should be 2 with collision handling");
        assertEquals("Dog", hashMap.get("Cozmo"), "Get should retrieve correct value for Cozmo");
        assertEquals("Cat", hashMap.get("omzoC"), "Get should retrieve correct value for omzoC");
        assertTrue(hashMap.contains("Cozmo"), "Contains should find Cozmo");
        assertTrue(hashMap.contains("omzoC"), "Contains should find omzoC");
    }

    @Test
    //HMIterator traverses all values
    public void testIterator() {
        MyHashMap<String> hashMap = new MyHashMap<>("alpha", "A");
        hashMap.put("beta", "B");
        hashMap.put("gamma", "C");

        Iterator<String> iterator = hashMap.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            String value = iterator.next();
            assertNotNull(value, "Iterator should return non-null values");
            count++;
        }

        assertEquals(3, count, "Iterator should traverse all 3 values");
    }

    @Test
    //For-each loop iterates through them all
    public void testForEachLoop() {
        MyHashMap<Integer> hashMap = new MyHashMap<>("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);

        int sum = 0;
        int count = 0;

        for (Integer value : hashMap) {
            assertNotNull(value, "For-each should return non-null values");
            sum += value;
            count++;
        }

        assertEquals(4, count, "For-each should process all 4 values");
        assertEquals(10, sum, "Sum of values should be 10");
    }

    @Test
    //GetMap returns ArrayList
    public void testGetMap() {
        MyHashMap<String> hashMap = new MyHashMap<>("test", "value");
        ArrayList<GenericQueue<String>> map = hashMap.getMap();

        assertNotNull(map, "GetMap should return non-null ArrayList");
        assertEquals(10, map.size(), "GetMap should return ArrayList of size 10");

        int nonNullBuckets = 0;
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i) != null) {
                nonNullBuckets++;
                assertTrue(map.get(i).getLength() >= 0, "Non-null buckets should have valid length");
            }
        }

        assertTrue(nonNullBuckets > 0, "At least one bucket should be non-null");
    }

    @Test
    //Multiple operations work correctly together
    public void testMultipleOperations() {
        MyHashMap<String> hashMap = new MyHashMap<>("initial", "value");

        hashMap.put("second", "2nd");
        hashMap.put("third", "3rd");
        assertEquals(3, hashMap.size(), "Size should be 3 after adding entries");

        hashMap.replace("second", "SECOND");
        assertEquals("SECOND", hashMap.get("second"), "Replace should update value");
        assertEquals(3, hashMap.size(), "Size should remain 3 after replace");

        assertTrue(hashMap.contains("initial"), "Should contain initial key");
        assertTrue(hashMap.contains("second"), "Should contain second key");
        assertTrue(hashMap.contains("third"), "Should contain third key");
        assertFalse(hashMap.contains("fourth"), "Should not contain non-existent key");
    }
}