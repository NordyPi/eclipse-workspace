package nordlab14;

public class ShuffleCipher implements MessageEncoder {
	int n;
	public ShuffleCipher(int n) {
		this.n = n;
	}
	
	public String encode(String plaintext) {
		String temp = plaintext;
		for (int i = 0; i < n; i ++) {
			char[] shuffled = new char[temp.length()];
			char[] halfOne = temp.substring(0, (temp.length() / 2) + (temp.length() % 2)).toCharArray();
			char[] halfTwo = temp.substring((temp.length() / 2) + (temp.length() % 2)).toCharArray();
			int index = 0;
			for (int r = 0; r < temp.length(); r ++) {
				if (r % 2 == 0) {
					shuffled[r] = halfOne[index];
				}
				else {
					shuffled[r] = halfTwo[index];
					index++;
				}
				
			}
			temp = new String(shuffled);
		}
		
		
		
		return new String(temp);
	}
}
