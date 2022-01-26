package edu.du.cs.loklinnord.lab3;

public class StackTest {

	public static void main(String[] args) {
		StackIf<String> stack = new ArrayStack<String>(10);

	    stack.push("Loki");
	    stack.push("Thanos");
	    stack.push("Ultron");
	    System.out.println(stack.toString());
	    System.out.println("top of non-empty stack: " + stack.peek());

	    System.out.println("popped stack: " + stack.pop());  
	    System.out.println("popped stack: " + stack.pop());
	    System.out.println(stack.toString());
	    System.out.println("isFull: " + stack.isFull());
	    System.out.println("isEmpty: " + stack.isEmpty());

	    System.out.println("popped stack: " + stack.pop());
	    System.out.println(stack.toString());
	    System.out.println("isEmpty: " + stack.isEmpty());
	 
	    for(int i=0; i<10; i++) {
	      stack.push("" + i);
	      System.out.println(stack.toString());
	    }
	    System.out.println("isFull: " + stack.isFull());

	    // deliberately adding one too many objects to the stack
	    stack.push("" + 10);
	}

}
