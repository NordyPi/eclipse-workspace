package edu.du.cs.loklinnord.lab1;

public class Lab1 {

	public static void main(String[] args) {

		// Create some fantastic artists
		Artist a1 = new Artist("Storm", "Johnny");
		Artist a2 = new Artist("Storm", "Susan");
		Artist a3 = new Artist("Richards", "Reed");
		Artist a4 = new Artist("Grimm", "Ben");
		
		// Create some songs sung by the artists
		Song s1 = new Song("I'm On Fire", a1, 400);  // the long version
		Song s2 = new Song("I'm On Fire", a1, 200);  
		Song s3 = new Song("All Four One", a1, 300);	
		Song s4 = new Song("Invisible", a2, 200);
		Song s5 = new Song("All Four One", a2, 300);
		Song s6 = new Song("All Four One", a3, 300);
		Song s7 = new Song("All Four One", a4, 300);
		
		// Create a playlist
		Playlist pl1 = new Playlist("MyPlaylist");
		pl1.addSong(s1);
		pl1.addSong(s2);
		pl1.addSong(s3);
		pl1.addSong(s4);
		pl1.addSong(s5);
		pl1.addSong(s6);
		pl1.addSong(s7);

		// Display the original playlist
		System.out.println(pl1);
		
		// Ask some questions
		System.out.println(pl1.contains(s2));
		System.out.println(pl1.contains(new Song("I'm On Fire", a1, 200)));
		System.out.println(pl1.contains(new Song("I'm On Fire", new Artist("Storm", "Johnny"), 200)));
		
		System.out.println(pl1.runningTime());
		
		// Sort the playlist and display the result
		pl1.sortBySong();
		System.out.println(pl1);
	}
	
	/* Output should be: 
	MyPlaylist [I'm On Fire (400) by Johnny Storm, I'm On Fire (200) by Johnny Storm, All Four One (300) by Johnny Storm, Invisible (200) by Susan Storm, All Four One (300) by Susan Storm, All Four One (300) by Reed Richards, All Four One (300) by Ben Grimm]
	true
	true
	true
	2000
	MyPlaylist [All Four One (300) by Ben Grimm, All Four One (300) by Reed Richards, All Four One (300) by Johnny Storm, All Four One (300) by Susan Storm, I'm On Fire (200) by Johnny Storm, I'm On Fire (400) by Johnny Storm, Invisible (200) by Susan Storm]
	*/

}
