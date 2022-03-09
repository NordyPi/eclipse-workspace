package edu.du.cs.loklinnord.homework4;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implementing a basic cache with a configurable size and associativity.
 * 
 * The cache is backed-up by a "Memory" object which actually stores stores the values -- on a cache miss, the Memory should be accessed.
 * 
 */
public class Cache implements ReadOnlyCache
{
	private Memory m_memory;
	private int blockCount;
	private int bytesPerBlock;
	private int associativity;
	private int indexBits;
	private int offsetBits;
	
	private boolean[] valid;
	private int[] tag;
	private byte[][] data;
	
	
	/**
	 * Constructor
	 * @param memory - An object implementing the Memory functionality.  This should be accessed on a cache miss
	 * @param blockCount - The number of blocks in the cache.
	 * @param bytesPerBlock - The number of bytes per block in the cache.
	 * @param associativity ;- The associativity of the cache.  1 means direct mapped, and a value of "blockCount" means fully-associative.
	 */
	public Cache(Memory memory, int blockCount, int bytesPerBlock, int associativity)
	{
		m_memory = memory;
		this.blockCount = blockCount;
		this.bytesPerBlock = bytesPerBlock;
		this.associativity = associativity;
		this.indexBits = (int) (Math.log(blockCount) / Math.log(2));
		this.offsetBits = (int) (Math.log(bytesPerBlock) / Math.log(2));
		System.out.println(blockCount);
		System.out.println(bytesPerBlock);
		
		valid = new boolean[blockCount];
		for (int i = 0; i < blockCount; i++) {
			valid[i] = false;
		}
		tag = new int[blockCount];
		data = new byte[blockCount][bytesPerBlock];
		
		//System.out.println(valid);
		//System.out.println(tag);
		//System.out.println(data);
		//int[] test = toBinary(10);
		//System.out.println("decimal to binary 10: " + printInt(test));
		//System.out.println("binary to decimal 1010: " + toDecimal(test));
	}

	/**
	 * Method to retrieve the value of the specified memory location.
	 * 
	 * @param address - The address of the byte to be retrieved.
	 * @return The value at the specified address.
	 */
	public byte load(int address)
	{
		// check valid, then tag
		System.out.println("address: " + address);
		if (valid[index(address)] && (tag[index(address)] == tag(address))) {
			 // just return the value
		} else {
			// load the block into the cache cuz it wasn't there
		}
		 
		// figure out if hit or miss
		// on hit return the value from the cache
		// on miss load missing block from memory into cache
		// TODO: implement the logic to perform the specified load
		// using caching logic.  This implementation does not do any caching,
		// it just immediately accesses memory, and is not correct.
		//System.out.println(m_memory.read(address, 1)[0]);
		return m_memory.read(address, 1)[0];
	}
	
	// helper methods
	public int[] toBinary(int decimal) {
		int[] binary = new int[16];
		int index = 0;
		while (decimal > 0) {
			binary[index++] = decimal % 2;
			decimal = decimal / 2;
		}
		printInt(binary);
		return binary;
	}
	public int toDecimal(int[] num) {
		int decimal = 0;
		for (int i = 0; i < num.length; i++) {
			decimal += Math.pow(2, i) * num[i];
		}
		return decimal;
	}
	public int index(int address) {
		int[] binary = toBinary(address);
		int[] indexBinary = new int[indexBits];
		for (int i = 0; i < indexBits; i++) {
			indexBinary[i] = binary[i+offsetBits];
		}
		System.out.println("index: " + toDecimal(indexBinary));
		return toDecimal(indexBinary);
	}
	public int tag(int address) {
		int[] binary = toBinary(address);
		int[] tagBinary = new int[binary.length - (indexBits + offsetBits)];
		for (int i = 0; i < tagBinary.length; i++) {
			tagBinary[i] = binary[i + indexBits + offsetBits];
		}
		return toDecimal(tagBinary);
	}
	public int offset(int address) {
		int[] binary = toBinary(address);
		int[] offsetBinary = new int[offsetBits];
		for (int i = 0; i < offsetBinary.length; i++) {
			offsetBinary[i] = binary[i];
		}
		return toDecimal(offsetBinary);
	}
	public int blockAddress(int address) {
		return (address - offset(address));
	}
	public String printInt(int[] arr) {
		String out = "";
		for (int i = arr.length-1; i >= 0; i--) {
			out = out + arr[i];
		}
		return out;
	}
}
