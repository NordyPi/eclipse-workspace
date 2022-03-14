package edu.du.cs.loklinnord.homework4;

/**
 * Class implementing a basic cache with a configurable size and associativity.
 * 
 * The cache is backed-up by a "Memory" object which actually stores stores the values -- on a cache miss, the Memory should be accessed.
 * 
 */
public class Cache implements ReadOnlyCache
{
	// class instance variables
	private Memory m_memory;
	private int blockCount;
	private int bytesPerBlock;
	private int associativity;
	private int indexBits;
	private int offsetBits;
	// data structures for cache arrays
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
		// assign all of the instance variables to the passe in data
		m_memory = memory;
		this.blockCount = blockCount;
		this.bytesPerBlock = bytesPerBlock;
		this.associativity = associativity;
		// perform log calculations for the cache dimensions
		this.indexBits = (int) (Math.log(blockCount) / Math.log(2));
		this.offsetBits = (int) (Math.log(bytesPerBlock) / Math.log(2));
		// sets all of valids data to false to start
		valid = new boolean[blockCount];
		for (int i = 0; i < blockCount; i++) {
			valid[i] = false;
		}
		// sets up the cache data structures
		tag = new int[blockCount];
		data = new byte[blockCount][bytesPerBlock];
	}

	/**
	 * Method to retrieve the value of the specified memory location.
	 * 
	 * @param address - The address of the byte to be retrieved.
	 * @return The value at the specified address.
	 */
	public byte load(int address)
	{
		// calculates some address relevant info I will reference later on
		int addyIndex = index(address);
		int addyOffset = offset(address);
		int addyTag = tag(address);
		
		// if valid bit at index and tag at index match, the data is in the cache already
		if (valid[addyIndex] && (tag[addyIndex] == addyTag)) {
			 return data[addyIndex][addyOffset];
		} else {
			// if the data isn't in the cache, figure out where the block would start, and load it into the cache
			valid[addyIndex] = true;
			tag[addyIndex] = addyTag;
			int blockStart = blockAddress(address);
			byte[] memBytes = m_memory.read(blockStart, bytesPerBlock);
			for (int i = 0; i < bytesPerBlock; i++) {
				data[addyIndex][i] = memBytes[i];
			}
		}
		// displayCache(); - i used this to troubleshoot and display the cache when testing
		// this returns the data after it is loaded into the cache
		return data[addyIndex][addyOffset];
	}
	
	// helper methods
	// converts a decimal int into an array of bits																																																									.
	
	public int[] toBinary(int decimal) {
		int[] binary = new int[16];
		int index = 0;
		while (decimal > 0) {
			binary[index++] = decimal % 2;
			decimal = decimal / 2;
		}
		return binary;
	}
	// converts an array of bits into a decimal int
	public int toDecimal(int[] num) {
		int decimal = 0;
		for (int i = 0; i < num.length; i++) {
			decimal += Math.pow(2, i) * num[i];
		}
		return decimal;
	}
	// calculates the index of the passed in decimal address
	public int index(int address) {
		int[] binary = toBinary(address);
		int[] indexBinary = new int[indexBits];
		for (int i = 0; i < indexBits; i++) {
			indexBinary[i] = binary[i+offsetBits];
		}
		return toDecimal(indexBinary);
	}
	// calculates the tag of the passed in decimal address
	public int tag(int address) {
		int[] binary = toBinary(address);
		int[] tagBinary = new int[binary.length - (indexBits + offsetBits)];
		for (int i = 0; i < tagBinary.length; i++) {
			tagBinary[i] = binary[i + indexBits + offsetBits];
		}
		return toDecimal(tagBinary);
	}
	// calculates the offset of the passed in decimal address
	public int offset(int address) {
		int[] binary = toBinary(address);
		int[] offsetBinary = new int[offsetBits];
		for (int i = 0; i < offsetBinary.length; i++) {
			offsetBinary[i] = binary[i];
		}
		return toDecimal(offsetBinary);
	}
	// calculates the beginning of the block address based on decimal address
	public int blockAddress(int address) {
		return (address - offset(address));
	}
	// for debugging purposes, prints out an array of bits
	public String printInt(int[] arr) {
		String out = "";
		for (int i = arr.length-1; i >= 0; i--) {
			out = out + arr[i];
		}
		return out;
	}
	// for debugging purposes, displays the cache in the console in a visually readable way
	public void displayCache() {
		for (int r = 0; r < blockCount; r++) {
			System.out.print("[" + valid[r] + "]  [" + tag[r] + "]  " + "[");
			for (int c = 0; c < bytesPerBlock-1; c ++) {
				System.out.print(data[r][c] + ", ");
			}
			System.out.println(data[r][bytesPerBlock-1]+"]");
		}
	}
}
