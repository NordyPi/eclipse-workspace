package edu.du.cs.loklinnord.lab3;

public interface StackIf<T> {
	public boolean isEmpty();  // true if stack is empty
    public boolean isFull();   // true if stack is full
    public int getSize();      // return the number of items in the stack
    public T peek();           // return top item in stack without removing it
    public T pop();            // remove and return top item in stack
    public void push(T o);     // add a new item to the top of the stack
}