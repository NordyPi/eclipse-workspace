package edu.du.cs.loklinnord.lab3;

public class QueueTest {
	  public static void main(String[] args) {

	    //QueueIf<String> queue1 = new CircularArrayQueue<String>(5);
	    QueueIf<String> queue1 = new LinkedListQueue<String>(5);

	    System.out.println(queue1);

	    queue1.enqueue("Parker");
	    queue1.enqueue("Stark");
	    queue1.enqueue("Romanoff");
	    System.out.println(queue1);
	    System.out.println("front of non-empty queue1: " + queue1.peek());

	    System.out.println("popped queue1: " + queue1.dequeue());
	    System.out.println("popped queue1: " + queue1.dequeue());
	    System.out.println(queue1);

	    System.out.println("popped queue1: " + queue1.dequeue());
	    System.out.println("isEmpty: " + queue1.isEmpty());
	    System.out.println(queue1);

	    queue1.enqueue("Banner");
	    queue1.enqueue("Rodgers");
	    queue1.enqueue("Danvers");
	    queue1.enqueue("T'Challa");
	    queue1.enqueue("Odinson");
	    System.out.println(queue1);
	    System.out.println("isFull: " + queue1.isFull());

	    System.out.println("popped queue1: " + queue1.dequeue());
	    System.out.println("popped queue1: " + queue1.dequeue());
	    System.out.println(queue1);


	    //QueueIf<Integer> queue2 = new CircularArrayQueue<Integer>(10);
	    QueueIf<Integer> queue2 = new LinkedListQueue<Integer>(10);

	    for(int i=0; i<10; i++) {
	      queue2.enqueue(i);
	    }
	    System.out.println(queue2);

	    // Try to force too many things onto the queue
	    queue2.enqueue(10);
	    System.out.println(queue2);
	  }
}
