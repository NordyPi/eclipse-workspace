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

        if (b.equals(new BitField(a.size()))) {
            return null;
        }

        // Return both the quotient and the remainder
        BitField[] out = new BitField[2];
        out[0] = new BitField(a.size()); // quotient
        out[1] = new BitField(a.size()); // remainder

        BitField quotient = new BitField(a.size() * 2);
        BitField remainder = new BitField(a.size() * 2);
        BitField divisor = new BitField(a.size() * 2);

        BitField aCopy = null;
        BitField bCopy = null;

        boolean remainderFlag = false;
        boolean quotientFlag = false;

        // the next sequence of if statements takes the absolute value of the bit
        // sequences and marks them as flipped if done so
        if (a.getMSB()) {
            aCopy = flipSign(a);
            remainderFlag = true;
        } else {
            aCopy = a.copy();
        }
        if (b.getMSB()) {
            bCopy = flipSign(b);
        } else {
            bCopy = b.copy();
        }

        // sets the lower bits of the size doubled divisor and remainder
        for (int i = 0; i < b.size(); i++) {
            divisor.set(i, bCopy.get(i));
        }
        for (int i = 0; i < b.size(); i++) {
            remainder.set(i, aCopy.get(i));
        }

        if (a.getMSB() != b.getMSB()) {
            quotientFlag = true;
        }

        for (int i = 0; i < aCopy.size(); i++) {
            divisor = LSL(divisor);
        }

        for (int i = 0; i <= a.size(); i++) {
            // subtract the divisor from the remainder register and place the result in the
            // remainder register
            remainder = subtract(remainder, divisor);
            // is the remainder > or < 0

            // >= shift the quotient register to the left, setting the new rightmost bit to
            // 1
            if (!remainder.getMSB()) {
                quotient = LSL(quotient);
                quotient.set(0, true);
            } else {
                // <0 restore the register to the original value by adding the divisor register
                // to the remainder register and placing the sum in the remainder register. Also
                // shift the quotient register to the left, setting the new LSB to 0
                remainder = add(remainder, divisor);
                quotient = LSL(quotient);
                quotient.set(0, false);
            }

            // shift the divisor register right 1 bit
            divisor = LSR(divisor);
        }

        // repeat until at the end of the bit sequence

        // flips the sign if the marker was turned on at the beginning of the method
        if (remainderFlag) {
            remainder = flipSign(remainder);
        }
        if (quotientFlag) {
            quotient = flipSign(quotient);
        }

        out[0] = halfBits(quotient);
        out[1] = halfBits(remainder);

        return out;
    }
    public static BitField halfBits(BitField b) {

		BitField out = new BitField(b.size() / 2);

		for (int i = 0; i < b.size() / 2; i++) {

			out.set(i, b.get(i));
		}

		return out;
    }
	 // written in class
	 	public static BitField flipSign(BitField a) {
	 		BitField temp = twosComplement(a);
	 		BitField one = new BitField(a.size());
	 		one.set(0, true);
	
	 		return add(temp, one);
	 	}
}