package binarycalc;

import java.util.Arrays;

/**
 * Class representing a bit-field of arbitrary (greater than zero)
 * size.  There are a number of constructors for creating BitField
 * objects from various other types.  There are a number of toXXX()
 * methods for getting the value in different types.
 */
public class BitField
{
	private final static int BITS_PER_BYTE = 8;
    private final boolean[] m_bits;

    /**
     * Constructor to create a bit-field of the specified
     * size, with all bits set to zero.
     *
     * @param size value greater than zero indicating the number of
     * bits to represent.
     */
    public BitField(int size)
    {
	if(size <= 0){
	    throw new IllegalArgumentException(String.format("BitField.BitField(%d) is not allowed.", size));
	}
	m_bits = new boolean [ size ];
    }

    /**
     * Constructor to create a bit field out of a String containing
     * '1' and '0' characters.  The MSB is assumed to be on the left
     * side of the string (index zero), with the LSB on the right side
     * of the string (length - 1).
     *
     * In the parsing logic, '0' characters are 'false' and all other
     * characters are 'true'.
     *
     * @param value non-null, non-empty string to be converted to a
     * byte array.
     */
    public BitField(String value)
    {
	this((null == value ? 0 : value.length()), value);
    }

    /** Constructor that allows the size to be set along with the
     * string representation.
     * @param size Sets the overall size of the bit field.
     * @param value Cannot be null.  Must be less than or equal to the
     * size of the bit field.
     */
    public BitField(int size, String value)
    {
	if(size <= 0){
	    throw new IllegalArgumentException(String.format("BitField.BitField(%d) is not allowed.", size));
	}
	if(null == value){
	    throw new IllegalArgumentException("BitField.BitField(int,(String)null) is not allowed.");
	}
	if(value.length() > size){
	    throw new IllegalArgumentException(String.format("BitField.BitField(%d,\"%s\") not allowed.", size, value));
	}
	m_bits = new boolean [ size ];
	// change the order
	for(int i=value.length()-1, idx = 0; i >= 0; i--, idx++){
	    m_bits[idx] = !(value.charAt(i) == '0');
	}

    }

    /**
     * Constructor to create a bit-field from a byte array.  In the
     * argument byte array, the bytes are organized in big-endian
     * order (i.e., the most significant byte is at the lowest index).
     *
     * <pre>{ 0xA1, 0xB2 } leads to MSB 1010 0001 1011 0010 MSB</pre>
     *
     * @param data non-null, non-zero-length byte array that is
     * converted into a bit field.
     */
    public BitField(byte[] data)
    {
	this((null == data ? 0 : data.length*BITS_PER_BYTE), data);
    }
    
    public BitField(int size, byte[] data)
    {
	if(0 == size){
	    throw new IllegalArgumentException("BitField.BitField(0, (byte[])) is not allowed.");
	}
	if(null == data){
	    throw new IllegalArgumentException("BitField.BitField(int,(byte[])null) is not allowed.");
	}
	if(data.length == 0){
	    throw new IllegalArgumentException("BitField.BitField(int,(byte[]){}) is not allowed.");
	}
	if(data.length*BITS_PER_BYTE > size){
	    throw new IllegalArgumentException(String.format("BitField.BitField(int,(byte[]) data length (%d) is too big for specified bits (%d)", (data.length*BITS_PER_BYTE), size));
	}
	m_bits = new boolean [ size ];
	int idx = 0;
	for(int i=(data.length-1); i>=0; i--){
	    int bits = data[i];
	    int mask = 0x1;
	    for(int j=0; j<BITS_PER_BYTE; j++){
		m_bits[idx++] = (bits & mask) != 0;
		mask <<= 1;
	    }
	}
    }

    /** @return A deep copy of the BitField object.
     */
    public BitField copy()
    {
	BitField out = new BitField(m_bits.length);
	for(int i=0; i<m_bits.length; i++){
	    out.m_bits[i] = m_bits[i];
	}
	return out;
    }

    /**
     * Method to compare two BitField objects.
     *
     * @param other Object to compare this object to
     *
     * @return true if the other object is a BitField object with the
     * same number of bits, and all bits have the same value.  False
     * otherwise.
     */
    public boolean equals(Object other)
    {
	// checks type consistency and not-null:
	if(!(other instanceof BitField)){
	    return false;
	}
	BitField that = (BitField)other;
	return Arrays.equals(m_bits, that.m_bits);
    }

    /**
     * @param index value between (0,lenth-1)
     * @return the bit value at the specified index.
     */
    public boolean get(int index)
    {
	return m_bits[index];
    }

    public boolean getLSB()
    {
	return m_bits[0];
    }

    public boolean getMSB()
    {
	return m_bits[m_bits.length-1];
    }

    /**
     * Sets the bit value at the specified index.
     * @param index value between (0,lenth-1)
     * @param value boolean value
     */
    public void set(int index, boolean value)
    {
	m_bits[index] = value;
    }

    /**
     * Sets all the bits to the specified value.
     * @param value the boolean value to be applied to all bits.
     */
    public void setAll(boolean value)
    {
	for(int i=0; i<m_bits.length; i++){
	    m_bits[i] = value;
	}
    }

    /** Sets all the bits to true.
     */
    public void setAllTrue()
    {
	setAll(true);
    }

    /** Sets all the bits to false.
     */
    public void setAllFalse()
    {
	setAll(false);
    }

    /**
     * Sets the bit at the specified index to false.
     * @param index value between (0,length-1)
     */
    public void setFalse(int index)
    {
	m_bits[index] = false;
    }

    /**
     * Sets the bit at the specified index to true.
     * @param index value between (0,lenth-1)
     */
    public void setTrue(int index)
    {
	m_bits[index] = true;
    }

    /**
     * @return the number of bits in the BitField.
     */
    public int size()
    {
	return m_bits.length;
    }

    /** @return the string representation of the bit field. This is a
     * debug string, and cannot be passed into the constructor that
     * takes a String argument.
     */
    public String toString()
    {
	StringBuilder sb = new StringBuilder();
	for(boolean b : m_bits){
	    // put the character at the beginning
	    sb.insert(0, (b ? '1' : '0'));
	}
	return sb.toString();
    }

    /** @return the decimal value of the bit field, as an unsigned
     * value.
     * @throws RuntimeException if the underlying bit field is longer
     * than Long.SIZE (i.e., 64-bits).
     */
    public long toLongSigned()
    {
	if(m_bits.length > Long.SIZE){
	    throw new RuntimeException(String.format("BitField.toLongSigned() called on a %d-bit field.", m_bits.length));
	}
	long out = 0;
	long value = 1;
	for(int i=0; i<(m_bits.length-1); i++){
	    if(m_bits[i]){
		out += value;
	    }
	    value += value;
	}
	if(m_bits[m_bits.length-1]){
	    out -= value;
	}
	return out;
    }

    /** @return the decimal value of the bit field, as an unsigned
     * value.
     * @throws RuntimeException if the underlying bit field is longer
     * than Integer.SIZE (i.e., 32-bits).
     */
    public int toIntSigned()
    {
	if(m_bits.length > Integer.SIZE){
	    throw new RuntimeException(String.format("BitField.toIntSigned() called on a %d-bit field.", m_bits.length));
	}
	int out = 0;
	int value = 1;
	for(int i=0; i<(m_bits.length-1); i++){
	    if(m_bits[i]){
		out += value;
	    }
	    value += value;
	}
	if(m_bits[m_bits.length-1]){
	    out -= value;
	}
	return out;
    }
}