package intro;
import java.util.ArrayList;

public class Tester {
	
	public static void main(String[] args) {
		
		MyList<Student> a = new MyArrayList<Student>();
		System.out.println(a.size());
		a.add(new Student("Bob", 82));
		a.add(new Student("Kevin", 75));
		a.add(new Student("Stuart", 98));
		System.out.println(a.size());
		System.out.println(a);
		
		a.remove(1);
		System.out.println(a);
		
		System.out.println(a.contains(new Student("Stuart", 98)));
		/*
		MyList<Integer> a2 = new MyArrayList<Integer>(100);
		a2.add(5);
		System.out.println(a2);
		*/
	}
}
