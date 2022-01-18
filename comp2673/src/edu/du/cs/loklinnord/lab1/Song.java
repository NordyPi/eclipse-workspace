package edu.du.cs.loklinnord.lab1;

public class Song implements Comparable <Song> {
	//Implements comparable 
	// class variables
	private String title;
	private Artist artist;
	private int runningTime; // in sec
	//constructor
	public Song(String title, Artist artist, int time) {
		this.title = title;
		this.artist = artist;
		this.runningTime = time;
	}
	// returns song details in specified format
	public String toString() {
		return title +" (" + runningTime + ") by " + artist.toString();
	}
	// returns its running time
	public int getRunTime() {
		return runningTime;
	}
	// checks values of itself against other object to see if equal
	public boolean equals(Song o) {
		if(this.title.equals(o.title) && this.artist.equals(o.artist) && this.runningTime == o.runningTime) {
			return true;
		} else {
			return false;
		}
	}
	//compares song 
	public int compareTo(Song s) {
		// compares title first
		int result = this.title.compareTo(s.title);
		// if titles are the same and returns 0, compare running time through math
		if (result == 0) {
			result = (this.runningTime - s.runningTime);
			// if running times are the same and result in 0, compare artist last
			if (result == 0) {
				result = this.artist.compareTo(s.artist);
			}
		}
		return result;
	}
}
