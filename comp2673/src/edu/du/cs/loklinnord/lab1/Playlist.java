package edu.du.cs.loklinnord.lab1;

import java.util.*;

public class Playlist {
	//class variables
	private String name;
	private List<Song> songs;
	//constructor
	public Playlist(String name) {
		this.name = name;
		this.songs = new ArrayList<Song>();
	}
	// method to add new songs to list
	public void addSong(Song s) {
		this.songs.add(s);
	}
	// calls collections sort to sort song list
	public void sortBySong() {
		Collections.sort(songs);
	}
	// builds toString for output, calls toString on song
	public String toString() {
		String output = this.name + " [";
		int i = 0;
		while(i < songs.size()-1) {
			output = output + songs.get(i).toString() +", ";
			i ++;
		}
		output = output + songs.get(songs.size()-1).toString() +"]";
		return output;
	}
	// adds up total running time of song list
	public int runningTime() {
		int totalTime = 0;
		//loops through and adds to totalTime
		for (Song s : songs) {
			totalTime += s.getRunTime();
		}
		return totalTime;
	}
	// returns if contains the song
	public boolean contains(Song s) {
		//loops through our song list
		for (Song check: songs) {
			//compares each one to check for a match
			if(check.equals(s)) {
				return true;
			}
		}
		//if no match found return false
		return false;
	}
}
