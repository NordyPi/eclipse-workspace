package nordlab07;

public class HockeyPlayer extends Person {
	//instance variables
	private int jerseyNumber;
	private Positions position;
	public enum Positions{
		CENTER,
		RIGHT_WING,
		LEFT_WING,
		DEFENDER,
		GOALIE,
		NO_POSITION_ASSIGNED
	}

	//default constructor
	public HockeyPlayer() {
		super();
		this.jerseyNumber = -1;
		this.position = Positions.NO_POSITION_ASSIGNED;
	}
	//Overloaded constructor
	public HockeyPlayer(String name, int num, Positions pos) {
		super(name);
		this.jerseyNumber = num;
		this.position = pos;
	}
	
	//Copy constructor
	public HockeyPlayer(HockeyPlayer other) {
		super(other);
		this.jerseyNumber = other.jerseyNumber;
		this.position = other.position;
	}

	//Setter
	public void setPlayer(String name, int num, Positions pos) {
		super.setName(name);
		this.jerseyNumber = num;
		this.position = pos;
	}
	//To string method
	public String toString() {
		return super.toString() + "; Jersey: " + this.jerseyNumber + "; Position: " +  this.position;
	}
	
	public boolean equals(HockeyPlayer other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		HockeyPlayer o = (HockeyPlayer) other;
		if(this.jerseyNumber == o.jerseyNumber && this.position == o.position && super.equals(o)) {
			return true;
		}
		else {
			return false;
		}
	}
}
