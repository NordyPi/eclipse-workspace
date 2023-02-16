package nordlab15;

public class Lab15Driver {

	public static void main(String[] args) {
		Gallery gal = new Gallery();
		Art one = new Art("y i k e s", "Luke");
		Art two = new Art("bussin", "Benton");
		Art three = new Art("mono blue mill", "Ethan");
		Art four = new Art("coding go brrr", "Loklin");
		
		one.addTag("modern");
		one.addTag("drugs");
		two.addTag("modern");
		two.addTag("chill");
		three.addTag("mtg");
		three.addTag("abstract");
		four.addTag("society");
		four.addTag("abstract");
		
		gal.addPiece(one);
		gal.addPiece(two);
		gal.addPiece(three);
		gal.addPiece(four);
		
		gal.printCollection();
		System.out.println(gal.numberBy("Loklin"));
		System.out.println(gal.numberMatching("modern"));
		System.out.println(gal.numberMatching("society"));
	}

}
