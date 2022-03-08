package edu.du.cs.loklinnord.lab8;

public interface QueueIf<T> {
    public boolean isEmpty();  // true if queue is empty
    public boolean isFull();   // true if queue is full
    public int getSize();      // return the number of items in the queue
    public T peek();           // return first item in queue without removing it
    public T dequeue();        // remove and return first item in queue
    public void enqueue(T o);  // add a new item to the end of the queue
}