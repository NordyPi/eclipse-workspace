package edu.du.cs.loklinnord.lab1;

public class Artist implements Comparable <Artist> {
	// class variables
	private String lastName;
	private String firstName;
	// constructor
	public Artist(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}
	// toString returns firstname space lastname
	public String toString() {
		return firstName + " " + lastName;
	}
	// checks values of itself against other object to see if equal
	public boolean equals(Artist o) {
		if(this.firstName.equals(o.firstName) && this.lastName.equals(o.lastName)) {
			return true;
		} else {
			return false;
		}
	}
	public int compareTo(Artist o) {
		// sets up compare int, compares lastNames
		int result = this.lastName.compareTo(o.lastName);
		// if lastnames are the same, above line returns 0 and then compares firstNames
		if (result == 0) {
			result = this.firstName.compareTo(o.firstName);
		}
		return result;
	}
}
