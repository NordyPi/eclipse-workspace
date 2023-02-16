package intro;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
	
	private Node first;
	
	private class Node {
		private T element;
		private Node next;
		
		public Node(T element) {
			this.element = element;
			this.next = null;
		}
		
		public String toString() {
			return "( " + element + " ";
		}
	}
	
	public MyLinkedList() {
		first = null;
	}

	@Override
	public void add(T value) {
		Node nn = new Node(value);
		
		Node p = first;
		while (p != null) {
			p = p.next;
		}
		p.next = nn;
	}

	@Override
	public T get(int index) {
		Node p = first;
		
		for (int i=0; i<index; i++) {
			p = p.next;
		}
		
		return p.element;
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		int s = 0;
		Node p = first;
		while (p != null) {
			s++;
			p = p.next;
		}
		return s;
	}

	@Override
	public boolean contains(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		String result = "[";
		Node p = first;
		
		while (p != null) {
			result += p.element + ", ";
			p = p.next;
		}
		
		result += "]";
		
		return result;
		
		
	}

}
