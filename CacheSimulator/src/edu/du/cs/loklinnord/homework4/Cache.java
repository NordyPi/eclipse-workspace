package edu.du.cs.loklinnord.homework4;


/**
 * Class implementing a basic cache with a configurable size and associativity.
 * 
 * The cache is backed-up by a "Memory" object which actually stores stores the values -- on a cache miss, the Memory should be accessed.
 * 
 */
public class Cache implements ReadOnlyCache
{
	private Memory m_memory;

	/**
	 * Constructor
	 * @param memory - An object implementing the Memory functionality.  This should be accessed on a cache miss
	 * @param blockCount - The number of blocks in the cache.
	 * @param bytesPerBlock - The number of bytes per block in the cache.
	 * @param associativity - The associativity of the cache.  1 means direct mapped, and a value of "blockCount" means fully-associative.
	 */
	public Cache(Memory memory, int blockCount, int bytesPerBlock, int associativity)
	{
		m_memory = memory;
	}

	/**
	 * Method to retrieve the value of the specified memory location.
	 * 
	 * @param address - The address of the byte to be retrieved.
	 * @return The value at the specified address.
	 */
	public byte load(int address)
	{
		// TODO: implement the logic to perform the specified load
		// using caching logic.  This implementation does not do any caching,
		// it just immediately accesses memory, and is not correct.
		return m_memory.read(address, 1)[0];
	}
}
