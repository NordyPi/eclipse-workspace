package edu.du.cs.loklinnord.lab4;

public interface HashTable<T> {
	// Adds element to the hash table
	// Returns false if the hash table is full
	public boolean put(T element);
	
	// Tells if the element is stored in the hash table
	public boolean contains(T element);
	
	// Removes the element from the hash table if it exists (returns true)
	// and returns false if it doesn't exist
	public boolean remove(T element);
}
