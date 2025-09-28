// File: HMTest.java
//Name: Neha Kamat, nkama4, nkama4@uic.edu
// Author: CS 342 Project #1 Fall 2025
// Description: Test cases for MyHashMap class and HMIterator

import java.util.ArrayList;
import java.util.Iterator;

 //Test class for MyHashMap functionality
 //Tests all required methods per PDF: constructor, put, get, contains, size, isEmpty, replace
 //Tests HMIterator and for-each loop functionality
 //Tests collision handling
public class HMTest {

    public static void main(String[] args) {
        System.out.println("Running MyHashMap Tests - CS 342 Project #1");
        System.out.println("============================================");

        // Test constructor (PDF requirement: test constructors)
        testConstructor();

        // Test all required methods (PDF requirement: minimum 1 test per method)
        testPutMethod();
        testGetMethod();
        testContainsMethod();
        testSizeMethod();
        testIsEmptyMethod();
        testReplaceMethod();

        // Test collision handling with specific examples from PDF
        testCollisionHandling();

        // Test iterator functionality (PDF requirement: test all iterators)
        testHMIterator();

        // Test for-each loop (PDF requirement: test forEach loop implementation)
        testForEachLoop();

        // Test getMap method for accessing underlying structure
        testGetMapMethod();

        System.out.println("\n============================================");
        System.out.println("All MyHashMap tests completed");
    }

    //Test MyHashMap constructor
    private static void testConstructor() {
        System.out.println("\n=== Testing MyHashMap Constructor ===");

        try {
            // Test basic constructor functionality
            MyHashMap<String> hashMap = new MyHashMap<>("testKey", "testValue");

            // Verify initial state
            boolean constructorWorks = hashMap.size() == 1 &&
                    "testValue".equals(hashMap.get("testKey")) &&
                    hashMap.contains("testKey") &&
                    !hashMap.isEmpty();

            // Verify ArrayList is initialized to size 10 (PDF requirement)
            ArrayList<GenericQueue<String>> map = hashMap.getMap();
            boolean correctMapSize = map.size() == 10;

            if (constructorWorks && correctMapSize) {
                System.out.println("✓ Constructor test PASSED");
                System.out.println("  - Initial key-value pair stored correctly");
                System.out.println("  - ArrayList initialized to size 10");
                System.out.println("  - Size: " + hashMap.size());
                System.out.println("  - IsEmpty: " + hashMap.isEmpty());
            } else {
                System.out.println("✗ Constructor test FAILED");
            }
        } catch (Exception e) {
            System.out.println("✗ Constructor test FAILED with exception: " + e.getMessage());
        }
    }

    //Test put method
    private static void testPutMethod() {
        System.out.println("\n=== Testing Put Method ===");

        try {
            MyHashMap<Integer> hashMap = new MyHashMap<>("key1", 100);

            // Add more key-value pairs
            hashMap.put("key2", 200);
            hashMap.put("key3", 300);
            hashMap.put("key4", 400);

            // Test that all values were added correctly
            boolean allAdded = hashMap.size() == 4 &&
                    hashMap.get("key1").equals(100) &&
                    hashMap.get("key2").equals(200) &&
                    hashMap.get("key3").equals(300) &&
                    hashMap.get("key4").equals(400);

            // Test updating existing key (should not increase size)
            hashMap.put("key1", 999);
            boolean updateWorks = hashMap.size() == 4 && hashMap.get("key1").equals(999);

            if (allAdded && updateWorks) {
                System.out.println("✓ Put method test PASSED");
                System.out.println("  - Added 4 key-value pairs");
                System.out.println("  - Updated existing key correctly");
                System.out.println("  - Final size: " + hashMap.size());
            } else {
                System.out.println("✗ Put method test FAILED");
            }
        } catch (Exception e) {
            System.out.println("✗ Put method test FAILED with exception: " + e.getMessage());
        }
    }

    //Test get method
    private static void testGetMethod() {
        System.out.println("\n=== Testing Get Method ===");

        try {
            MyHashMap<String> hashMap = new MyHashMap<>("fruit", "apple");
            hashMap.put("color", "red");
            hashMap.put("number", "42");

            // Test getting existing keys
            boolean existingKeysWork = "apple".equals(hashMap.get("fruit")) &&
                    "red".equals(hashMap.get("color")) &&
                    "42".equals(hashMap.get("number"));

            // Test getting non-existent key (should return null)
            boolean nonExistentWorks = hashMap.get("nonexistent") == null;

            if (existingKeysWork && nonExistentWorks) {
                System.out.println("✓ Get method test PASSED");
                System.out.println("  - Retrieved all existing keys correctly");
                System.out.println("  - Non-existent key returns null");
            } else {
                System.out.println("✗ Get method test FAILED");
            }
        } catch (Exception e) {
            System.out.println("✗ Get method test FAILED with exception: " + e.getMessage());
        }
    }


    private static void testContainsMethod() {

        try {
            MyHashMap<Double> hashMap = new MyHashMap<>("pi", 3.14159);
            hashMap.put("e", 2.71828);
            hashMap.put("golden", 1.61803);

            // Test existing keys
            boolean existingKeysWork = hashMap.contains("pi") &&
                    hashMap.contains("e") &&
                    hashMap.contains("golden");

            // Test non-existing key
            boolean nonExistingWorks = !hashMap.contains("nonexistent");

            if (existingKeysWork && nonExistingWorks) {
                System.out.println("✓ Contains method test PASSED");
                System.out.println("  - All existing keys found");
                System.out.println("  - Non-existent key correctly returns false");
            } else {
                System.out.println("✗ Contains method test FAILED");
            }
        } catch (Exception e) {
            System.out.println("✗ Contains method test FAILED with exception: " + e.getMessage());
        }
    }

//size method
    private static void testSizeMethod() {
        System.out.println("\n=== Testing Size Method ===");

        try {
            MyHashMap<String> hashMap = new MyHashMap<>("first", "1st");
            System.out.println("  After constructor: size = " + hashMap.size());

            hashMap.put("second", "2nd");
            System.out.println("  After adding second: size = " + hashMap.size());

            hashMap.put("third", "3rd");
            System.out.println("  After adding third: size = " + hashMap.size());

            hashMap.put("first", "FIRST");
            System.out.println("  After updating existing key: size = " + hashMap.size());

            boolean sizeWorks = hashMap.size() == 3;

            if (sizeWorks) {
                System.out.println("Size method test PASSED");
                System.out.println("Size correctly tracks number of key-value mappings");
            } else {
                System.out.println("Size method test FAILED");
            }
        } catch (Exception e) {
            System.out.println("Size method test FAILED with exception: " + e.getMessage());
        }
    }

    //Test isEmpty method
    private static void testIsEmptyMethod() {
        System.out.println("\n=== Testing IsEmpty Method ===");

        try {
            MyHashMap<String> hashMap = new MyHashMap<>("key", "value");

            // HashMap starts with one element from constructor, so should not be empty
            boolean isEmptyWorks = !hashMap.isEmpty();

            if (isEmptyWorks) {
                System.out.println("IsEmpty method test PASSED");
                System.out.println("HashMap with elements correctly returns false for isEmpty()");
                System.out.println("IsEmpty result: " + hashMap.isEmpty());
            } else {
                System.out.println("IsEmpty method test FAILED");
            }
        } catch (Exception e) {
            System.out.println("IsEmpty method test FAILED with exception: " + e.getMessage());
        }
    }

    //Test replace method
    private static void testReplaceMethod() {

        try {
            MyHashMap<String> hashMap = new MyHashMap<>("replaceKey", "originalValue");

            // Test replacing existing key
            String oldValue = hashMap.replace("replaceKey", "newValue");
            String currentValue = hashMap.get("replaceKey");
            boolean replaceExistingWorks = "originalValue".equals(oldValue) &&
                    "newValue".equals(currentValue);

            // Test replacing should return null
            String nullResult = hashMap.replace("nonExistentKey", "someValue");
            boolean replaceNonExistentWorks = nullResult == null;

            if (replaceExistingWorks && replaceNonExistentWorks) {
                System.out.println("✓ Replace method test PASSED");
                System.out.println("  - Existing key replacement works correctly");
                System.out.println("  - Non-existent key replacement returns null");
            } else {
                System.out.println("✗ Replace method test FAILED");
            }
        } catch (Exception e) {
            System.out.println("✗ Replace method test FAILED with exception: " + e.getMessage());
        }
    }

    //Test collision handling
    private static void testCollisionHandling() {
        System.out.println("\n=== Testing Collision Handling (PDF Example) ===");

        try {
            // Test the specific collision example from PDF requirements
            MyHashMap<String> hashMap = new MyHashMap<>("Cozmo", "Dog");
            hashMap.put("omzoC", "Cat");

            // Calculate and display hash information as shown in PDF
            int cozmoHashCode = "Cozmo".hashCode();
            int omzoCHashCode = "omzoC".hashCode();
            int cozmoIndex = cozmoHashCode & 9;
            int omzoCIndex = omzoCHashCode & 9;

            System.out.println("  PDF Hash Analysis:");
            System.out.println("  - 'Cozmo' hashCode: " + cozmoHashCode + " -> index: " + cozmoIndex);
            System.out.println("  - 'omzoC' hashCode: " + omzoCHashCode + " -> index: " + omzoCIndex);

            // Both keys should be accessible despite potential collision
            boolean collisionHandled = hashMap.size() == 2 &&
                    "Dog".equals(hashMap.get("Cozmo")) &&
                    "Cat".equals(hashMap.get("omzoC")) &&
                    hashMap.contains("Cozmo") &&
                    hashMap.contains("omzoC");

            if (collisionHandled) {
                System.out.println("✓ Collision handling test PASSED");
                if (cozmoIndex == omzoCIndex) {
                    System.out.println("  - Collision detected and handled at index " + cozmoIndex);
                } else {
                    System.out.println("  - Keys hash to different indices (no collision)");
                }
                System.out.println("  - Both values retrievable: Cozmo=" + hashMap.get("Cozmo") +
                        ", omzoC=" + hashMap.get("omzoC"));
            } else {
                System.out.println("✗ Collision handling test FAILED");
            }
        } catch (Exception e) {
            System.out.println("✗ Collision handling test FAILED with exception: " + e.getMessage());
        }
    }

    //Test HMIterator functionality
    private static void testHMIterator() {
        System.out.println("\n=== Testing HMIterator ===");

        try {
            MyHashMap<String> hashMap = new MyHashMap<>("alpha", "A");
            hashMap.put("beta", "B");
            hashMap.put("gamma", "C");

            System.out.println("  Testing iterator directly:");
            Iterator<String> iterator = hashMap.iterator();
            int count = 0;
            while (iterator.hasNext()) {
                String value = iterator.next();
                System.out.println("    Iterator value " + (count + 1) + ": " + value);
                count++;
            }

            boolean iteratorWorks = count == 3;

            if (iteratorWorks) {
                System.out.println("✓ HMIterator test PASSED");
                System.out.println("  - Iterator traversed all " + count + " values");
            } else {
                System.out.println("✗ HMIterator test FAILED - expected 3, got " + count);
            }
        } catch (Exception e) {
            System.out.println("✗ HMIterator test FAILED with exception: " + e.getMessage());
        }
    }

    //Test for-each loop functionality
    private static void testForEachLoop() {
        System.out.println("\n=== Testing For-Each Loop Implementation ===");

        try {
            MyHashMap<Integer> hashMap = new MyHashMap<>("one", 1);
            hashMap.put("two", 2);
            hashMap.put("three", 3);
            hashMap.put("four", 4);

            System.out.println("  Testing for-each loop:");
            int sum = 0;
            int count = 0;

            // This tests the Iterable<T>
            for (Integer value : hashMap) {
                System.out.println("    For-each value " + (count + 1) + ": " + value);
                sum += value;
                count++;
            }

            boolean forEachWorks = count == 4 && sum == 10;

            if (forEachWorks) {
                System.out.println("✓ For-Each Loop test PASSED");
                System.out.println("  - Processed " + count + " values");
                System.out.println("  - Sum of values: " + sum);
            } else {
                System.out.println("✗ For-Each Loop test FAILED");
                System.out.println("  - Expected: count=4, sum=10");
                System.out.println("  - Got: count=" + count + ", sum=" + sum);
            }
        } catch (Exception e) {
            System.out.println("✗ For-Each Loop test FAILED with exception: " + e.getMessage());
        }
    }

    //Test getMap method for accessing underlying ArrayList structure
    private static void testGetMapMethod() {
        System.out.println("\n=== Testing GetMap Method ===");

        try {
            MyHashMap<String> hashMap = new MyHashMap<>("test", "value");
            ArrayList<GenericQueue<String>> map = hashMap.getMap();

            boolean mapValid = map != null && map.size() == 10;

            // Count non-null buckets
            int nonNullBuckets = 0;
            for (int i = 0; i < map.size(); i++) {
                if (map.get(i) != null) {
                    nonNullBuckets++;
                    System.out.println("  - Bucket " + i + " has " + map.get(i).getLength() + " element(s)");
                }
            }

            if (mapValid) {
                System.out.println("GetMap method test PASSED");
                System.out.println("ArrayList size: " + map.size());
                System.out.println("Non-null buckets: " + nonNullBuckets);
            } else {
                System.out.println("GetMap method test FAILED");
            }
        } catch (Exception e) {
            System.out.println("GetMap method test FAILED with exception: " + e.getMessage());
        }
    }
}
