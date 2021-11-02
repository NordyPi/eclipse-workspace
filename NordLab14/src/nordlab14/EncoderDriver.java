package nordlab14;

public class EncoderDriver {

	public static void main(String[] args) {
		ShiftCipher shift = new ShiftCipher(3);
		System.out.println(shift.encode("facetiously"));
		ShiftCipher decodeShift = new ShiftCipher(-3);
		System.out.println(decodeShift.encode("idfhwlrxvo|"));
		
		ShuffleCipher shuffle = new ShuffleCipher(1);
		System.out.println(shuffle.encode("abcdefghi"));
	}

}
