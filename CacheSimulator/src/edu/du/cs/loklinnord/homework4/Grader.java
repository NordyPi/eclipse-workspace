package edu.du.cs.loklinnord.homework4;


import java.util.Random;

public class Grader
{
    // static variables
    private final static int BYTES_PER_KB = 1024;
    private final static int MEMORY_SIZE = 512*BYTES_PER_KB;
    private final static Random s_rand = new Random(0);
    private final static boolean s_doDirectMapped = true;
    private final static boolean s_doFullyAssociative = true;
    private final static boolean s_doSetAssociative = true;

    // instance variables
    private TestMemory m_memory;
    private int m_nAssertions = 0;
    private int m_nFailedAssertions = 0;

    public static void main(String[] args)
    {
	new Grader().run();
    }

    private ReadOnlyCache newInstance(int blockCount, int bytesPerBlock, int associativity)
    {
	// new memory for each new Cache object.
	m_memory = new TestMemory(s_rand, MEMORY_SIZE);
	return new Cache(m_memory, blockCount, bytesPerBlock, associativity);
    }

    private void run()
    {
	if(s_doDirectMapped){
	    testDirectMapped(8,32);
	    testDirectMapped(32,8);
	    testDirectMapped(16,4);
	    testDirectMapped(64, 2);
	    testDirectMapped(32, 2);
	}
	int dmAssertions = m_nAssertions, dmFailedAssertions = m_nFailedAssertions;
	m_nAssertions = m_nFailedAssertions = 0;
	if(s_doFullyAssociative){
	    testFullyAssociative(2,4);
	    testFullyAssociative(4,2);
	    testFullyAssociative(8,32);
	    testFullyAssociative(32,8);
	    testFullyAssociative(32,64);
	    testFullyAssociative(64,32);
	}
	int faAssertions = m_nAssertions, faFailedAssertions = m_nFailedAssertions;
	m_nAssertions = m_nFailedAssertions = 0;
	if(s_doSetAssociative){
	    testSetAssociative(4, 4, 2);
	    testSetAssociative(8, 8, 4);
	    testSetAssociative(16, 8, 8);
	    testSetAssociative(16, 16, 2);
	    testSetAssociative(512, 8, 256);
	}
	int saAssertions = m_nAssertions, saFailedAssertions = m_nFailedAssertions;
	m_nAssertions = m_nFailedAssertions = 0;

	// Direct Mapped - 40% of grade, Fully associative - 30%, Set Associative - 30% of grade
	int dmPassedAssertions = (dmAssertions-dmFailedAssertions);
	System.err.println(String.format("DIRECT MAPPED: PASSED %d/%d = %f%%",
					 dmPassedAssertions, dmAssertions,
					 (100.0*dmPassedAssertions)/((double)dmAssertions)));
	int faPassedAssertions = (faAssertions-faFailedAssertions);
	System.err.println(String.format("FULLY ASSOCIATIVE: PASSED %d/%d = %f%%",
					 faPassedAssertions, faAssertions,
					 (100.0*faPassedAssertions)/((double)faAssertions)));
	int saPassedAssertions = (saAssertions-saFailedAssertions);
	System.err.println(String.format("SET ASSOCIATIVE: PASSED %d/%d = %f%%",
					 saPassedAssertions, saAssertions,
					 (100.0*saPassedAssertions)/((double)saAssertions)));
	
	System.err.println(String.format("OVERALL SCORE (weighted) = %f%%",
					 100.0*( 0.40*dmPassedAssertions/((double)dmAssertions) + 0.30*faPassedAssertions/((double)faAssertions) + 0.30*saPassedAssertions/((double)saAssertions) ))); 
    }

    private void testFullyAssociative(int nBlocks, int nBytesPerBlock)
    {
	final String THIS_METHOD = new Object(){}.getClass().getEnclosingMethod().getName();

	// fully associative (associativity == # blocks)
	ReadOnlyCache cache = newInstance(nBlocks, nBytesPerBlock, nBlocks);
	assertEquals(THIS_METHOD, "checking zero reads from memory initially.", 0, m_memory.getReadCalls() );

	// access 1st block
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "1 memory reads after accessing first block", 1, m_memory.getReadCalls());

	// access 2nd block
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "2 memory reads after accessing 2nd block", 2, m_memory.getReadCalls());

	// access first block again -- this should update the LRU of the 1st block
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "2 memory reads after accessing first block again", 2, m_memory.getReadCalls());
		
	// access 3rd block up to n to fill up the cache
	for(int b = 2; b < nBlocks; b++){
	    for(int n=0; n<nBytesPerBlock; n++){
		int i = b*nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
		assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	    }
	    assertEquals(THIS_METHOD,  "another memory reads after accessing next", (b+1), m_memory.getReadCalls());

	}
		
	// now access the very next block a bunch of times
	for(int n=0; n<nBytesPerBlock; n++){
	    int i=nBlocks*nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "after access overflow block", nBlocks+1, m_memory.getReadCalls());

	// access first block again, should not have been kicked out.
	for(int n=0; n<nBytesPerBlock; n++){
	    int i=s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "after accessing 1st block again", nBlocks+1, m_memory.getReadCalls());
	
	// access 2nd block again, this should have been kicked out as lru, so accessing it again
	// will require another memory access.
	for(int n=0; n<nBytesPerBlock; n++){
	    int i=nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "after accessing 2rd block again", nBlocks+2, m_memory.getReadCalls());

    }

    private void testSetAssociative(int nBlocks, int nBytesPerBlock, int associativity)
    {	final String THIS_METHOD = new Object(){}.getClass().getEnclosingMethod().getName();

	// 4 blocks, 4 bytes/block, 2-associative (4/2 = 2) so there are two sets:
	// Set 0: (0,1,2,3)		(8,9,10,11)		(16,17,18,19) ...
	// Set 1: (4,5,6,7)		(12,13,14,15)	(20,21,22,23) ...
	ReadOnlyCache cache = newInstance(nBlocks, nBytesPerBlock, associativity);
	assertEquals(THIS_METHOD,  "checking zero reads from memory initially.", 0, m_memory.getReadCalls());
		
	int nSets = nBlocks / associativity;
		
	// access all the blocks in the first set
	for(int a=0; a<associativity; a++){
	    for(int n=0; n<nBytesPerBlock; n++){
		int i=a*nSets*nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
		assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	    }
	    assertEquals(THIS_METHOD,  "another memory reads after next set", a+1, m_memory.getReadCalls());
	}
		
	int expectedReads = m_memory.getReadCalls(); // save the number of memory access at this point
	// now go back through those blocks again
	for(int a=0; a<associativity; a++){
	    for(int n=0; n<nBytesPerBlock; n++){
		int i=a*nSets*nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
		assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	    }
	    assertEquals(THIS_METHOD,  "no more reads after accessing first set again", expectedReads, m_memory.getReadCalls());
	}
		

	// access the 2nd block, this should go in the other set and generate one more memory access
	expectedReads++;
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "additional memory read after accessing 2nd block", expectedReads, m_memory.getReadCalls());

	// access first block -- should not have been kicked out
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "no additional memory read after accessing 1st block", expectedReads, m_memory.getReadCalls());

	// at this point we have the following:
	//     set 0 is full 
	//     set 1 has only its 1st entry filled
		
	// access set 0 again with a new different block, and the 2nd block in set 0
	// should have been kicked out
	expectedReads++;
	for(int n=0; n<nBytesPerBlock; n++){
	    int i=associativity*nSets*nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "one more memory read after accessing set 0", expectedReads, m_memory.getReadCalls());

	// access first block -- should not have been kicked out
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "no additional memory read after accessing 1st block for a 3rd time", expectedReads, m_memory.getReadCalls());
		 
	// access 2nd set -- should not have been kicked out
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "no additional memory read after accessing 2st block again", expectedReads, m_memory.getReadCalls());
		
	// access 2nd block in 1st set, this should require another memory access
	expectedReads++;
	for(int n=0; n<nBytesPerBlock; n++){
	    int i = 1*nSets*nBytesPerBlock + s_rand.nextInt(nBytesPerBlock);
	    assertEquals(THIS_METHOD,  String.format("Mem[%d] value", i), m_memory.peek(i), cache.load(i), true);
	}
	assertEquals(THIS_METHOD,  "no additional memory read after accessing 2st block again", expectedReads, m_memory.getReadCalls());
    }

    private void testDirectMapped(int nBlocks, int nBytesPerBlock)
    {
	final String THIS_METHOD = String.format("%s(%d,%d)", 
						 new Object(){}.getClass().getEnclosingMethod().getName(), nBlocks, nBytesPerBlock);
	// set up some constants for this method
	final int associativity = 1;

	int nElements = nBlocks * nBytesPerBlock;
	ReadOnlyCache cache = newInstance(nBlocks, nBytesPerBlock, associativity);

	// pick a random start point -- guaranteed to be a multiple of nBytesPerBlock
	int base = nBytesPerBlock*(1+s_rand.nextInt(1024));

	// do this a bunch of times, should only require one read per block, read sequentially through a chunk of memory
	for(int t=0; t<3; t++){
	    for(int i=0; i<nElements; i++){
		int address = base + i;

		// check that the cache provides the right values
		assertEquals(THIS_METHOD, "sequential value test", m_memory.peek(address), cache.load(address), true);
	    }
	}
		
	// now do some random accesses within the range
	for(int t=0; t<10; t++){
	    int address = base + s_rand.nextInt(nElements);
	    assertEquals(THIS_METHOD, "random value test", m_memory.peek(address), cache.load(address));
	}
		
	// after all of that this should have just required one read per block, and each address should have been read exactly once
	assertEquals(THIS_METHOD, "total reads", nBlocks, m_memory.getReadCalls());
	for(int i=0; i<nElements; i++){
	    assertEquals(THIS_METHOD, "per-address reads", 1, m_memory.getReadCount(base+i));
	}
		
	// go a specific address in the next chunk of memory, guaranteed to not have been loaded already
	int element2 = s_rand.nextInt(nElements);
	int address2 = base + nElements + element2;
	// go to some random offset
	assertEquals(THIS_METHOD, "2nd chunk", m_memory.peek(address2), cache.load(address2));
	assertEquals(THIS_METHOD, "2nd chunk reads", nBlocks+1, m_memory.getReadCalls());
	// each of the addresses in this chunk should have been read exactly once
	for(int i=0; i<nBytesPerBlock; i++){
	    int address = base + nElements + (element2 - (element2 % nBytesPerBlock)) + i;
	    assertEquals(THIS_METHOD, "2nd chunk per-address read counts", 1, m_memory.getReadCount(address));
	}
	// each of the addresses in the original block should not have been read any more
	for(int i=0; i<nElements; i++){
	    assertEquals(THIS_METHOD, "per-address reads post 2nd chunk", 1, m_memory.getReadCount(base+i));
	}
		
	// now load the same element from the original chunk, should result in that previous block being read again
	int address3 = base + element2;
	assertEquals(THIS_METHOD, "back to original", m_memory.peek(address3), cache.load(address3));
	assertEquals(THIS_METHOD, "back to original reads", nBlocks+2, m_memory.getReadCalls());
	for(int i=0; i<nBytesPerBlock; i++){
	    int address = base + (element2 - (element2 % nBytesPerBlock)) + i;
	    assertEquals(THIS_METHOD, "1st chunk per-address read counts post 2nd chunk", 2, m_memory.getReadCount(address));
	}

    }
    
    private void assertEquals(String method, String message, int expected, int actual)
    {
	assertEquals(method, message, expected, actual, false);
    }

    private void assertEquals(String method, String message, int expected, int actual, boolean ignore)
    {
	if(!ignore){
	    m_nAssertions++;
	}
	if(expected != actual){
	    System.err.println(String.format("ERROR: assertion failure: %s: %s: expected <%d> but was <%d>",
					     method, message, expected, actual));
	    if(!ignore){
		m_nFailedAssertions++;
	    }
	}
    }
}
