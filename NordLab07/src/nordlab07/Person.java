package nordlab07;

public class Person {
	private String name;

	public Person() {
		this.name = "No Name";
	}

	public Person(String n) {
		if (n != null) {
			this.name = n;
		} else {
			this.name = "No name";
		}
	}
	
	//Copy constructor
	public Person(Person other) {
		this.name = other.name;
	}

	public void setName(String n) {
		if (n != null) {
			this.name = n;
		} else {
			System.out.println("Fatal error in Person class - attempt to set name to a null String");
			System.exit(0);
		}
	}

	public String toString() {
		return "Name: " + this.name;
	}
	
	public boolean equals(Person other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		if(this.name == other.name) {
			return true;
		} else {
			return false;
		}
	}
}	