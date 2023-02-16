package nordlab07;

public class Lab7Driver {

	public static void main(String[] args) {
		HockeyPlayer blank = new HockeyPlayer();
		HockeyPlayer one = new HockeyPlayer();
		HockeyPlayer two = new HockeyPlayer();
		HockeyPlayer three = new HockeyPlayer("benton", 21, HockeyPlayer.Positions.GOALIE);
		HockeyPlayer four = new HockeyPlayer(three);
		
		System.out.println(blank);
		
		one.setName("luke");
		System.out.println(one);
		
		two.setPlayer("ben", 22, HockeyPlayer.Positions.DEFENDER);
		System.out.println(two);
		
		System.out.println(three);
		System.out.println(four);
		System.out.println(three.equals(four));
		System.out.println(three.equals(two));
		
	}

}
