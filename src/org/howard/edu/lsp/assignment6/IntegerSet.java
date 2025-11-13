package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mathematical set of integers with standard set operations.
 * A set cannot contain duplicate Integer values and supports operations like union, intersection, difference, and complement.
 */
public class IntegerSet  {
  private List<Integer> set = new ArrayList<Integer>();

  /* 
   * Clears the internal representation of the set.
   */
  public void clear() {
	  set.clear();
  }

  /* 
   * Returns the number of elements in the set.
   * @return the size of the set
  */
  public int length() {
	  return set.size();
  }

  /*
   * Returns true if the 2 sets are equal, false otherwise;
   * Two sets are equal if they contain all of the same values in ANY order.
   * This overrides the equals method from the Object class.
   * @param o the object being compared to this
   * @return true if the sets are equal, false if not
   */
  @Override
  public boolean equals(Object o) {
	  if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      
      IntegerSet other = (IntegerSet) o;
      if (this.length() != other.length()) return false;
      
      for (Integer val : set) {
          if (!other.contains(val)) {
              return false;
          }
      }
      return true;
  }

  /* 
   * Returns true if the set contains the value, otherwise false.
   * @param value the value being checked for membership
   * @return true if set contains value, false if not
   */
  public boolean contains(int value) {
	  return set.contains(value);
  }

  /*
   * Returns the largest item in the set (throws IllegalStateException if empty).
   * @return the largest integer found in set
   * @throws IllegalStateException if the set is empty
   */
  public int largest()  {
	  if (isEmpty()) {
          throw new IllegalStateException("Set is empty");
      }
      
      int max = set.get(0);
      for (int value : set) {
          if (value > max) {
              max = value;
          }
      }
      return max;
  }

  /*
   * Returns the smallest item in the set (throws IllegalStateException if empty).
   * @return the smallest integer in set
   * @throws IllegalStateException if the set is empty
   */
  public int smallest()  {
	  if (isEmpty()) {
          throw new IllegalStateException("Set is empty");
      }
      
      int min = set.get(0);
      for (int value : set) {
          if (value < min) {
              min = value;
          }
      }
      return min;
  }

  /* 
   * Adds an item to the set or does nothing if already present.
   * @param item the integer to be added to the set
   */
  public void add(int item) {
	  if (!contains(item)) 
		  set.add(Integer.valueOf(item));
  }

  /*
   * Removes an item from the set or does nothing if not there.
   * @param item the integer to be removed from the set
   */
  public void remove(int item) {
	  if (contains(item)) 
		  set.remove(Integer.valueOf(item));
  }

  /*
   * Set union: modifies this to contain all unique elements in this or other.
   * @param other the other IntegerSet to perform the union operation with
   */
  public void union(IntegerSet other) {
	  for (int i = 0; i < other.length(); i++) {
		  int val = other.set.get(i);
		  this.add(val);
	  }
  }

  /*
   * Set intersection: modifies this to contain only elements in both sets.
   * @param other the other IntegerSet to perform the intersection operation with
   */
  public void intersect(IntegerSet other) {
	  List<Integer> intersection = new ArrayList<>();
	  for (Integer val : set) {
		  if (other.contains(val))
			  intersection.add(val);
	  }
	  set = intersection;
  }

  /*
   * Set difference (this \ other): modifies this to remove elements found in other.
   * @param other the other IntegerSet to perform the difference operation with
   */
  public void diff(IntegerSet other) {
	  for (int i = 0; i < other.length(); i++) {
          int value = other.set.get(i);
          this.remove(value);
      }
  }

  /*
   * Set complement: modifies this to become (other \ this).
   * @param other the other IntegerSet to perform the complement operation with
   */
  public void complement(IntegerSet other) {
	  List<Integer> complement = new ArrayList<>();
      for (int i = 0; i < other.length(); i++) {
          int val = other.set.get(i);
          if (!this.contains(val)) {
              complement.add(val);
          }
      }
      set = complement;
  }

  /*
   * Returns true if the set is empty, false otherwise.
   * @return true if set has no elements, false if it has at least on element
   */
  public boolean isEmpty() {
	  return set.isEmpty();
  }

  /*
   * Returns a String representation; overrides Object.toString().
   * @return a string representation of the set in format [1, 2, 3]
   */
  @Override
  public String toString() {
	  return set.toString();
  }
}

