package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

public class IntegerSet  {
  private List<Integer> set = new ArrayList<Integer>();

  // Clears the internal representation of the set.
  public void clear() {
	  
  }

  // Returns the number of elements in the set.
  public int length() {
	  
  }

  /*
   * Returns true if the 2 sets are equal, false otherwise;
   * Two sets are equal if they contain all of the same values in ANY order.
   * This overrides the equals method from the Object class.
   */
  @Override
  public boolean equals(Object o) {
	  
  }

  // Returns true if the set contains the value, otherwise false.
  public boolean contains(int value) {
	  
  }

  // Returns the largest item in the set (throws IllegalStateException if empty).
  public int largest()  {
	  
  }

  // Returns the smallest item in the set (throws IllegalStateException if empty).
  public int smallest()  {
	  
  }

  // Adds an item to the set or does nothing if already present.
  public void add(int item) {
	  
  }

  // Removes an item from the set or does nothing if not there.
  public void remove(int item) {
	  
  }

  // Set union: modifies this to contain all unique elements in this or other.
  public void union(IntegerSet other) {
	  
  }

  // Set intersection: modifies this to contain only elements in both sets.
  public void intersect(IntegerSet other) {
	  
  }

  // Set difference (this \ other): modifies this to remove elements found in other.
  public void diff(IntegerSet other) {
	  
  }

  // Set complement: modifies this to become (other \ this).
  public void complement(IntegerSet other) {
	  
  }

  // Returns true if the set is empty, false otherwise.
  public boolean isEmpty() {
	  
  }

  // Returns a String representation; overrides Object.toString().
  @Override
  public String toString() {
	  
  }
}

