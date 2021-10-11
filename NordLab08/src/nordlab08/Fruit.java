package nordlab08;

public class Fruit {

	public Fruit() {
		System.out.println("No-argument constructor for Fruit called.");
	}

	public static void main(String[] args) {
		System.out.println("Questions 1 and 2");
		System.out.println("fruit instances below");
		Fruit fruit = new GoldenDelicious();
		System.out.println();
		System.out.println("orange instances below");
		Orange orange = new Orange();
		System.out.println();
		System.out.println("apple instances below");
		Apple apple = new Macintosh();
		
		System.out.println();
		System.out.println("Questions 3");
		System.out.println("Error");
		System.out.println();
		
		System.out.println("Question 5");
		apple.makeAppleCider();
		System.out.println();
		
		System.out.println("Question 6");
		orange.makeOrangeJuice();
		System.out.println("fruit.makeOrangeJuice() throws NO METHOD FOUND");
	}

}

class Apple extends Fruit{
	public Apple() {
		System.out.println("No-argument constructor for Apple called.");
	}
	public void makeAppleCider() {
		System.out.println("Made cider! (apple)");
	}
	
}

class Orange extends Fruit {
	public Orange() {
		System.out.println("No-argument constructor for Orange called."); 
	}
	
	public void makeOrangeJuice() {
		System.out.println("Made orange juice!");
	}
}

class Macintosh extends Apple {
	public Macintosh() {
		System.out.println("No-argument constructor for Macintosh called."); 
	}
	public void makeAppleCider() {
		System.out.println("Made cider! (macintosh)");
	}
}

class GoldenDelicious extends Apple {
	public GoldenDelicious() {
		System.out.println("No-argument constructor for GoldenDelicious called."); 
	}
	public void makeAppleCider() {
		System.out.println("Made cider! (golden)");
	}
}
