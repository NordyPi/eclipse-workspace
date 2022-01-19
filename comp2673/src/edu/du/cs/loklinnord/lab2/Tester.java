package edu.du.cs.loklinnord.lab2;

public class Tester {

	public static void main(String[] args) {
		
		ListIf<String> l = new LList<String>();
		
		l.add("hello");
		l.add("world");
		l.add("this");
		l.add("deleted");
		System.out.println(l);
		l.remove(3);
		
		System.out.println(l);
		l.set(1, "cjhanged");
		System.out.println(l);
		l.add("added");
		System.out.println(l.contains("this"));
		System.out.println(l.indexOf("this"));
		l.remove("this");
		System.out.println(l);
		System.out.println(l.size());

	}

}
