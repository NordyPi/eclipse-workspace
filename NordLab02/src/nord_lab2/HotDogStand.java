package nord_lab2;

/*
 * Loklin Nord
 * 9/15/21
 * Collaborated with Luke
 */

public class HotDogStand {
	
	//Define class variables
	private String standName;
	private int standID;
	private int hotDogsSold;
	
	private static int idGenerator = 0;
	private static int totalHotDogsSold = 0;
	
	public HotDogStand(String name) {
		//Set up standID and increase the count
		this.standID = idGenerator;
		idGenerator += 1;
		this.standName = name;
		this.hotDogsSold = 0;
	}
	
	//Define justSold method and overload
	public void justSold() {
		this.hotDogsSold += 1;
		totalHotDogsSold += 1;
	}
	public void justSold (int count) {
		this.hotDogsSold += count;
		totalHotDogsSold += count;
	}
	
	public String getStandName() {
		return this.standName;
	}
	public int getStandID() {
		return this.standID;
	}
	public int getHotDogsSold() {
		return this.hotDogsSold;
	}
	public static int getTotalHotDogsSold() {
		return totalHotDogsSold;
	}
}
