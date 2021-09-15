package nord_lab2;

public class HotDogStandDriver {

	public static void main(String[] args) {
		HotDogStand jerry = new HotDogStand("Jerry");
		HotDogStand bob = new HotDogStand("Bob");
		HotDogStand larry = new HotDogStand("Larry");
		
		jerry.justSold(7);
		bob.justSold();
		bob.justSold();
		bob.justSold();
		bob.justSold();
		larry.justSold(3);
		larry.justSold();
		System.out.println("Stand name    StandID     Number Sold");
		System.out.println(jerry.getStandName() +"   "+jerry.getStandID() + "    " + jerry.getHotDogsSold());
		System.out.println(bob.getStandName() +"   " + bob.getStandID() + "    " + bob.getHotDogsSold());
		System.out.println(larry.getStandName() +"   "+ larry.getStandID() + "    " + larry.getHotDogsSold());
	}
}
