package binarycalc;

/**
 * Class with methods to implement the basic arithmetic operations by operating
 * on bit fields.
 *
 * This is the skeleton code form COMP2691 Assignment #2.
 */
public class BinaryCalculator {
	
	public static BitField flipBits(BitField flip) {
		BitField temp = new BitField(flip.size());
		for(int i = 0; i < flip.size(); i++) {
			if(flip.get(i) == false) {
				temp.setTrue(i);
			}
			if(flip.get(i) == true) {
				temp.setFalse(i);
			}
		}
		return temp;
	}
	
	public static BitField twosComplement(BitField pos) {

		BitField temp = new BitField(pos.size());
		temp.setTrue(0);
		temp = add(flipBits(pos), temp);
		return temp;
	}
	
	public static BitField LSL(BitField shift) {
		BitField temp = new BitField(shift.size());
		temp.setAllFalse();
		for (int i = 0; i < shift.size() - 1; i++) {
			temp.set(i+1, shift.get(i));
		}
		return temp;
	}
	
	public static BitField LSR(BitField shift) {
		BitField temp = new BitField(shift.size());
		temp.setAllFalse();
		for (int i = 1; i < shift.size(); i++) {
			temp.set(i, shift.get(i));
		}
		return temp;
	}
	
	public static BitField add(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		boolean carry = false;
		BitField output = new BitField(a.size());
		
		for (int i = 0; i < a.size(); i ++) {
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

	public static BitField subtract(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		
		return add(a, twosComplement(b));
	}

	public static BitField multiply(BitField a, BitField b) {
		if (null == a || null == b || a.size() != b.size()) {
			throw new IllegalArgumentException(
					"BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		BitField temp = new BitField(a.size());
		for (int i = 0; i < a.size(); i ++) {
			boolean temp2 = b.get(i);
			if(temp2) {
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

		// Return both the quotient and the remainder
		BitField[] out = new BitField[2];
		out[0] = new BitField(a.size()); // quotient
		out[1] = new BitField(a.size()); // remainder
		return out;
	}
}