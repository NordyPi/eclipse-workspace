package nordlab14;

public class EncoderDriver {

	public static void main(String[] args) {
		ShiftCipher shift = new ShiftCipher(3);
		System.out.println("facetiously with shift of 3");
		System.out.println(shift.encode("facetiously"));
		
		ShuffleCipher shuffle = new ShuffleCipher(3);
		System.out.println("facetiously with suffle of 3");
		System.out.println(shuffle.encode("facetiously"));
	}

}
