package binarycalc;

public class BinaryCalculator {
	//simple true false flip
	public static BitField flipBits(BitField flip) {
		BitField temp = new BitField(flip.size());
		for (int i = 0; i < flip.size(); i++) {
			if (flip.get(i) == false) {
				temp.setTrue(i);
			}
			if (flip.get(i) == true) {
				temp.setFalse(i);
			}
		}
		return temp;
	}
	// flips bits and adds one
	public static BitField twosComplement(BitField pos) {
		BitField temp = new BitField(pos.size());
		temp.setTrue(0);
		temp = add(flipBits(pos), temp);
		return temp;
	}
	// shifts the bits once left
	public static BitField LSL(BitField shift) {
		BitField temp = new BitField(shift.size());
		temp.setAllFalse();
		for (int i = 0; i < shift.size() - 1; i++) {
			temp.set(i + 1, shift.get(i));
		}
		return temp;
	}
	// shifts the bit right once
	public static BitField LSR(BitField shift) {
		BitField temp = new BitField(shift.size());
		temp.setAllFalse();
		for (int i = 1; i < shift.size(); i++) {
			temp.set(i - 1, shift.get(i));
		}
		return temp;
	}
	// adds two binary numbers together
	public static BitField add(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		boolean carry = false;
		BitField output = new BitField(a.size());
		// truth table for all possible values. carry boolean keeps track of carry bits
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) == false && b.get(i) == false && carry == false) { // no true values
				output.setFalse(i);
				carry = false;
			} else if (a.get(i) == false && b.get(i) == false && carry == true) { // carry bit true only
				output.setTrue(i);
				carry = false;
			} else if (a.get(i) == false && b.get(i) == true && carry == false) { // only b true
				output.setTrue(i);
				carry = false;
			} else if (a.get(i) == false && b.get(i) == true && carry == true) { // b and carry true
				output.setFalse(i);
				carry = true;
			} else if (a.get(i) == true && b.get(i) == false && carry == false) { // only a true
				output.setTrue(i);
				carry = false;
			} else if (a.get(i) == true && b.get(i) == false && carry == true) { // a and carry true
				output.setFalse(i);
				carry = true;
			} else if (a.get(i) == true && b.get(i) == true && carry == false) { // a and b true
				output.setFalse(i);
				carry = true;
			} else if (a.get(i) == true && b.get(i) == true && carry == true) { // all are true
				output.setTrue(i);
				carry = true;
			}
		}
		return output;
	}
	// subtracts b from a
	public static BitField subtract(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		//this is simple as we flip the second binary num and add the two
		return add(a, twosComplement(b));
	}
	// multiplies a by b
	public static BitField multiply(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		// runs through multiplication algorithm in book
		BitField temp = new BitField(a.size());
		for (int i = 0; i < a.size(); i++) {
			boolean temp2 = b.get(i);
			if (temp2) {
				temp = add(temp, a);
			}
			a = LSL(a);
		}
		return temp;
	}

	public static BitField[] divide(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		// set up our remainder by placing in the right side of double sized field
		BitField remainder = new BitField(a.size()*2);
		for (int i = 0; i < a.size(); i ++) {
			remainder.set(i, a.get(i));
		}
		// sets up our divisor by placing in left side of double sized field
		BitField divisor = new BitField(b.size()*2);
		for (int i = 0; i < b.size(); i ++) {
			divisor.set(i + b.size(), b.get(i));
		}
		BitField quotient = new BitField(a.size());
		// runs through algorithm from book. somehow worked on example case, 7 / 2 but i dont think subtracting is working
		for(int i = 0; i < a.size() + 1; i++) {
			remainder = subtract(remainder, divisor);
			System.out.println(remainder);
			if(remainder.get(remainder.size() - 1) == true) {
				remainder = add(remainder, divisor);
				quotient = LSL(quotient);
				quotient.set(0, false);
			} else {
				quotient = LSL(quotient);
				quotient.set(0, true);
			}
			divisor = LSR(divisor);
		}
		// set remainder back to original size for grading
		BitField newRemainder = new BitField(a.size());
		for (int i = 0; i < a.size(); i ++) {
			newRemainder.set(i, remainder.get(i));
		}
		
		// Return both the quotient and the remainder
		BitField[] out = new BitField[2];
		out[0] = quotient; // quotient
		out[1] = newRemainder; // remainder
		return out;
	}
}