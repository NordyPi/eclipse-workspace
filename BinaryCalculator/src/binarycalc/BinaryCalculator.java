package binarycalc;

/**
 * Class with methods to implement the basic arithmetic operations by
 * operating on bit fields.
 *
 * This is the skeleton code form COMP2691 Assignment #2.
 */
public class BinaryCalculator
{
    public static BitField add(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}
	return new BitField(a.size());
    }

    public static BitField subtract(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}
	return new BitField(a.size());
    }

    public static BitField multiply(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}
	return new BitField(a.size());
    }

    public static BitField[] divide(BitField a, BitField b)
    {
	if(null == a || null == b || a.size() != b.size()){
	    throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
	}

	// Return both the quotient and the remainder
	BitField[] out = new BitField [ 2 ];
	out[0] = new BitField(a.size()); // quotient
	out[1] = new BitField(a.size()); // remainder
	return out;
    }
}