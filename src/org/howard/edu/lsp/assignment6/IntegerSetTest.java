package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * JUnit test cases for the IntegerSet class.
 * Tests all public methods with various scenarios including edge cases.
 */
class IntegerSetTest {
    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;

    @BeforeEach
    void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        emptySet = new IntegerSet();
        
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
    }

    @Test
    @DisplayName("Test clear method")
    void testClear() {
        assertFalse(set1.isEmpty());
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test length method")
    void testLength() {
        assertEquals(3, set1.length());
        set1.add(4);
        assertEquals(4, set1.length());
        set1.remove(1);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        IntegerSet set3 = new IntegerSet();
        set3.add(1);
        set3.add(2);
        set3.add(3);
        
        assertTrue(set1.equals(set3));
        assertFalse(set1.equals(set2));
        
        // Test with different order
        IntegerSet set4 = new IntegerSet();
        set4.add(3);
        set4.add(1);
        set4.add(2);
        assertTrue(set1.equals(set4));
        
        // Test with null
        assertFalse(set1.equals(null));
        
        // Test with different class
        assertFalse(set1.equals("not a set"));
    }

    @Test
    @DisplayName("Test contains method")
    void testContains() {
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(4));
        assertFalse(emptySet.contains(1));
    }

    @Test
    @DisplayName("Test largest method")
    void testLargest() {
        assertEquals(3, set1.largest());
        
        set1.add(5);
        assertEquals(5, set1.largest());
        
        // Test exception for empty set
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.largest();
        });
        assertEquals("Set is empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test smallest method")
    void testSmallest() {
        assertEquals(1, set1.smallest());
        
        set1.add(0);
        assertEquals(0, set1.smallest());
        
        // Test exception for empty set
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.smallest();
        });
        assertEquals("Set is empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test add method")
    void testAdd() {
        int initialLength = set1.length();
        set1.add(4);
        assertEquals(initialLength + 1, set1.length());
        assertTrue(set1.contains(4));
        
        // Test duplicate addition
        set1.add(4);
        assertEquals(initialLength + 1, set1.length()); // Length should not change
    }

    @Test
    @DisplayName("Test remove method")
    void testRemove() {
        assertTrue(set1.contains(1));
        set1.remove(1);
        assertFalse(set1.contains(1));
        
        // Remove non-existent element
        int initialLength = set1.length();
        set1.remove(999);
        assertEquals(initialLength, set1.length()); // Length should not change
    }

    @Test
    @DisplayName("Test union method")
    void testUnion() {
        set1.union(set2);
        assertEquals(4, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        
        // Test union with empty set
        IntegerSet testSet = new IntegerSet();
        testSet.add(1);
        testSet.add(2);
        testSet.union(emptySet);
        assertEquals(2, testSet.length());
        
        // Test union with self
        int lengthBefore = set1.length();
        set1.union(set1);
        assertEquals(lengthBefore, set1.length());
    }

    @Test
    @DisplayName("Test intersect method")
    void testIntersect() {
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        
        // Test intersect with empty set
        set1.intersect(emptySet);
        assertTrue(set1.isEmpty());
        
        // Test intersect with disjoint sets
        IntegerSet disjointSet = new IntegerSet();
        disjointSet.add(5);
        disjointSet.add(6);
        set1.intersect(disjointSet);
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test diff method")
    void testDiff() {
        set1.diff(set2);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(1));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(3));

        IntegerSet testSet = new IntegerSet();
        testSet.add(1);
        testSet.add(2);
        IntegerSet emptySet = new IntegerSet();
        testSet.diff(emptySet);
        assertEquals(2, testSet.length());
        assertTrue(testSet.contains(1));
        assertTrue(testSet.contains(2));

        testSet.diff(testSet);
        assertTrue(testSet.isEmpty());
    }

    @Test
    @DisplayName("Test complement method")
    void testComplement() {
        set1.complement(set2);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(4));

        IntegerSet testSet = new IntegerSet();
        testSet.add(1);
        testSet.add(2);
        IntegerSet emptySet = new IntegerSet();
        testSet.complement(emptySet);
        assertTrue(testSet.isEmpty());

        IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(2);
        IntegerSet setB = new IntegerSet();
        setB.add(1);
        setA.complement(setB);
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty method")
    void testIsEmpty() {
        assertFalse(set1.isEmpty());
        assertTrue(emptySet.isEmpty());
        
        set1.clear();
        assertTrue(set1.isEmpty());
        
        emptySet.add(1);
        assertFalse(emptySet.isEmpty());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        IntegerSet testSet = new IntegerSet();
        assertEquals("[]", testSet.toString());
        
        testSet.add(1);
        assertTrue(testSet.toString().contains("1"));
        
        testSet.add(2);
        testSet.add(3);
        String result = testSet.toString();
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }

    @Test
    @DisplayName("Test edge cases with duplicates")
    void testDuplicateHandling() {
        IntegerSet testSet = new IntegerSet();
        testSet.add(1);
        testSet.add(1);
        testSet.add(1);
        assertEquals(1, testSet.length());
        
        testSet.add(2);
        testSet.add(2);
        assertEquals(2, testSet.length());
    }

    @Test
    @DisplayName("Test operations with negative numbers")
    void testNegativeNumbers() {
        IntegerSet negativeSet = new IntegerSet();
        negativeSet.add(-1);
        negativeSet.add(-5);
        negativeSet.add(-3);
        
        assertEquals(-5, negativeSet.smallest());
        assertEquals(-1, negativeSet.largest());
        
        negativeSet.add(0);
        assertEquals(-5, negativeSet.smallest());
        assertEquals(0, negativeSet.largest());
    }
}