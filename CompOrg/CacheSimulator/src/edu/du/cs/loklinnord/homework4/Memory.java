package edu.du.cs.loklinnord.homework4;


public interface Memory
{
	/**
	 * Basic read interface to memory -- request to read a certain number of bytes starting at an address.
	 * 
	 * @param address
	 * @param nBytes
	 * @return
	 */
	public byte[] read(int address, int nBytes);
}
