package nordlab17;
//Worked with Luke

public class Driver {
	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.enqueue("pizza");
		queue.enqueue(10);
		System.out.println(queue.dequeue());
		queue.enqueue(true);
		System.out.println(queue.isEmpty());
		queue.enqueue(10.2);
		queue.enqueue("luke");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.isEmpty());
		System.out.println();
		
		Stack stack = new Stack();
		stack.push("pizza");
		stack.push(10);
		System.out.println(stack.pop());
		stack.push(true);
		System.out.println(stack.isEmpty());
		stack.push(10.2);
		stack.push("luke");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.isEmpty());
		System.out.println();
		
		PriorityQueue pq = new PriorityQueue();
		pq.enqueue(7);
		pq.enqueue(10);
		pq.enqueue(4);
		System.out.println(pq.dequeue());
		pq.enqueue(8);
		System.out.println(pq.dequeue());
		System.out.println(pq.dequeue());
		System.out.println(pq.dequeue());
	}
}
