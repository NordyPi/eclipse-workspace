package nordhw11;

public class Driver {
	public boolean circleEquals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
	}
	public static void main(String[] args) {
		Integer int1 = new Integer(7);
		Integer int2 = 9;
		int m = 11;
		int1 = m;
		m = int1;
		m = int2.intValue();
	}

}
