package edu.du.cs.loklinnord.lab2;

public interface ListIf<T> {
	//Copy pasted from canvas assignment
    public void add(T o); // add to the end 
    public void add(int index, T o); // add at index 
    public T get(int index); // get the value at index 
    public void set(int index, T o); // change the value at index 
    public T remove(int index); // remove value at index and return it 
    public boolean remove(T o); // remove the object if found (return true if found, false if not)
    public int size(); // returns the size
    public boolean contains(T o); // returns true if the list contains the object    
    public int indexOf(T o); // returns the index of the object, -1 if it doesn't exist
}
