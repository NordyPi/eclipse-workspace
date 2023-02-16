package edu.du.cs.loklinnord.homework4;



import java.util.Random;

public class TestMemory implements Memory
{
	private byte[] m_memory;
	private int[] m_readCount;
	private int m_readCalls = 0;
	
	public TestMemory(Random rand, int nBytes)
	{
		// allocate the requested amount of memory
		m_memory = new byte [ nBytes ];
		m_readCount = new int [ nBytes ];
		
		// fill it with random values
		rand.nextBytes(m_memory);
	}
	
	// method used by the test program to check the returned value.
	// You should not (be able to) call this method in your code.
	public byte peek(int address)
	{
		return m_memory[address];
	}
	
	public int getReadCount(int address)
	{
		return m_readCount[address]; 
	}
	
	public int getReadCalls()
	{
		return m_readCalls;
	}
	
	public byte[] read(int address, int nBytes)
	{
		m_readCalls++;
		byte[] out = new byte [ nBytes ];
		for(int i=0; i<nBytes; i++){
			out[i] = m_memory[address+i];
			m_readCount[address+i]++;
		}
		return out;
	}
}
