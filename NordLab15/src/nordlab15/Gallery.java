package nordlab15;

import java.util.ArrayList;

public class Gallery {
	private ArrayList<Art> artwork;
	
	public Gallery() {
		artwork = new ArrayList<Art>();
	}
	
	public void addPiece(Art a) {
		artwork.add(a);
	}
	
	public void printCollection() {
		for (int i = 0; i < artwork.size(); i++) {
			System.out.println(artwork.get(i).getName() + ", by " + artwork.get(i).getArtist());
		}
	}
	
	public int numberBy(String n) {
		int count = 0;
		for (int i = 0; i < artwork.size(); i++) {
			if (artwork.get(i).getArtist().equals(n)) {
				count ++;
			}
		}
		return count;
	}
	
	public int numberMatching(String t) {
		int count = 0;
		for (int i = 0; i < artwork.size(); i++) {
			if (artwork.get(i).matches(t)) {
				count ++;
			}
		}
		return count;
	}
}
