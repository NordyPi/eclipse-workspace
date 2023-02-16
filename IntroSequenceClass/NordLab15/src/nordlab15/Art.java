package nordlab15;

import java.util.ArrayList;

public class Art {
	private String name;
	private String artist;
	private ArrayList<String> tags;
	
	public Art(String n, String a) {
		this.name = n;
		this.artist = a;
		this.tags = new ArrayList<String>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public void addTag(String t) {
		tags.add(t);
	}
	
	public boolean matches(String t) {
		return tags.contains(t);
	}
}

