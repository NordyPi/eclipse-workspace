package edu.du.cs.loklinnord.lab3;

public class ArrayStack<T> implements StackIf<T> {

	private T[] stack = null;
	private int top = 0;
	public ArrayStack(int maxSize) {
		stack = (T[])(new Object[maxSize]);
	}   
	 
	@Override
	public boolean isEmpty() {
		// if empty, the bottom index would be null
		if (stack[0] == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		// if full, then the current index should equal stack length - 1
		if (top == stack.length - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getSize() {
		// returns the size, which is index of top + 1
		return top + 1;
	}

	@Override
	public T peek() {
		// returns the top element
		return stack[top];
	}

	@Override
	public T pop() {
		// sets top object to null, decreases top index, and returns the object
		if (isEmpty()) {
			throw new RuntimeException("Attempted to pop from an empty stack");
		} else {
			T popped = stack[top];
			stack[top] = null;
			if (top != 0) {
				top --;
			}
			return popped;
		}
	}

	@Override
	public void push(T o) {
		// increases top index and sets the object
		// if stack is empty, don't increase the top index
		if (isFull()) {
			throw new RuntimeException("Attempted to add to a full stack");
		} else {
			if (isEmpty()) {
				stack[top] = o;
			} else {
				top ++;
				stack[top] = o;
			}
		}
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < top; i++) {
			output += stack[i] + ", ";
		}
		output += stack[top];
		return output;
	}

}
