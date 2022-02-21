package binarycalc;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class BinaryCalculatorGrader
{
    private static final boolean DO_ADD = true;
    private static final boolean DO_SUB = true;
    private static final boolean DO_MUL = true;
    private static final boolean DO_DIV = true;
    private static final int NUM_ITERATIONS = 10000;
    private static final int MAX_BITS = 62;
    private static Random RAND = new Random(1);

    public static void main(String[] args) throws Exception
    {
    BitField test = new BitField(8);
    test.setAllTrue();
    //System.out.println(BinaryCalculator.LSL(test));
    System.out.println(BinaryCalculator.LSR(test));
    
	long n_tests = 0;
	long n_correct = 0;
	for(int i=0; i<NUM_ITERATIONS; i++){
	    // pick the number of bits to use.
	    int n_bits = RAND.nextInt(MAX_BITS)+1; // add 1 because zero bits is not valid
	    
	    BitField arg1 = randomBitField(n_bits);
	    BitField arg2 = randomBitField(n_bits);

	    if(DO_ADD){
		n_tests++;
		BitField res = BinaryCalculator.add(arg1.copy(), arg2.copy());
		BitField exp = computeResult(arg1, "+", arg2)[0];
		if(!res.equals(exp)){
		    System.out.println(String.format("FAIL: <%s> + <%s> expected <%s> but was <%s>",
						     arg1, arg2, exp, res));
		}
		else {
		    n_correct++;
		}
	    }
	    if(DO_SUB){
		n_tests++;
		BitField res = BinaryCalculator.subtract(arg1.copy(), arg2.copy());
		BitField exp = computeResult(arg1, "-", arg2)[0];
		if(!res.equals(exp)){
		    System.out.println(String.format("FAIL: <%s> - <%s> expected <%s> but was <%s>",
						     arg1, arg2, exp, res));
		}
		else {
		    n_correct++;
		}
	    }
	    if(DO_MUL){
		n_tests++;
		BitField res = BinaryCalculator.multiply(arg1.copy(), arg2.copy());
		BitField exp = computeResult(arg1, "*", arg2)[0];
		if(!res.equals(exp)){
		    System.out.println(String.format("FAIL: <%s> * <%s> expected <%s> but was <%s>",
						     arg1, arg2, exp, res));
		}
		else {
		    n_correct++;
		}
	    }
	    if(DO_DIV){
		n_tests++;
		BitField[] res = BinaryCalculator.divide(arg1.copy(), arg2.copy());
		BitField[] exp = computeResult(arg1, "/", arg2);

		// divide by zero
		if(exp == null){
		    if(res != null){
			System.out.println(String.format("FAIL: <%s> / <%s> expected <null> but was <%s remainder %s>",
							 arg1, arg2, res[1], res[1]));
		    }
		    else {
			n_correct++;
		    }
		}
		else {
		    if(!res[0].equals(exp[0]) || !res[1].equals(exp[1])){
			System.out.println(String.format("FAIL: <%s> / <%s> expected <%s remainder %s> but was <%s remainder %s>",
							 arg1, arg2, exp[0], exp[1], res[0], res[1]));
		    }
		    else {
			n_correct++;
		    }
		}
	    }
	}
	System.out.println(String.format("FINAL SCORE: %4.2f (%d out of %d correct)",
					 100.0*((double)n_correct)/((double)n_tests),
					 n_correct, n_tests));
    }

    private static BitField[] computeResult(BitField arg1, String op, BitField arg2)
	{
	    long l1 = arg1.toLongSigned();
	    long l2 = arg2.toLongSigned();
	    long l3, r = 0;
	    BitField[] out = new BitField [ 2 ];
	    switch(op){
	    case "+":
		l3 = l1 + l2;
		break;
	    case "-":
		l3 = l1 - l2;
		break;
	    case "*":
		l3 = l1 * l2;
		break;
	    default:
		// division
		if(l2 == 0){
		    return null;
		}
		l3 = l1 / l2;
		r = l1 % l2;
	    }
	    out[0] = trimBits(l3, arg1.size());
	    out[1] = trimBits(r, arg1.size());
	    return out;
	}

	private static BitField trimBits(long l, int length)
	{
	    BitField out = new BitField(length);
	    for(int i=0; i<length; i++){
		if((l & 0x1) == 0x1){
		    out.setTrue(i);
		}
		l = l >>> 1;
	    }
	    return out;
	}

	private static BitField randomBitField(int n_bits)
	{
	    BitField out = new BitField(n_bits);
	    for(int i=0; i<n_bits; i++){
		out.set(i, RAND.nextBoolean());
	    }
	    return out;
	}
}