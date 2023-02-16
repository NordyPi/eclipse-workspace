package intro;

public interface MyList<T extends Comparable<T>> {
	public void add(T value);
	public T get(int index);
	public void remove(int index);
	public int size();
	public boolean contains(T value);
	public void sort();
	
}
