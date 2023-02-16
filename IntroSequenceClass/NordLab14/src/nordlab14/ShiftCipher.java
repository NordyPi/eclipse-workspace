package nordlab14;

public class ShiftCipher implements MessageEncoder {
	int shift;
	public ShiftCipher(int shift) {
		this.shift = shift;
	}
	
	public String encode(String plaintext) {
		char[] chars = plaintext.toCharArray();
		char[] shifted = new char[chars.length];
		for (int i = 0; i < chars.length; i ++) {
			shifted[i] = (char) ((chars[i] + shift) % 128);
		}
		
		return new String(shifted);
	}
}
