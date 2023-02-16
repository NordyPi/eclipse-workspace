package edu.du.cs.loklinnord.homework4;



import java.util.Random;

public class SimpleCacheTest
{
	private static String s_currentMethod = null;
	private static Random s_rand = new Random(0); // set the seed to zero for repeatability, but shouldn't matter

	public static void main(String[] args)
	{
		testDirectMapped();
		testFullyAssociative();
		testSetAssociative();
	}

	private static void testDirectMapped()
	{	s_currentMethod = new Object(){}.getClass().getEnclosingMethod().getName();
		TestMemory memory = new TestMemory(s_rand, 32);

		/* uncomment if you want to see the 32 memory value
		for(int i=0; i<32; i++){
			System.out.println(String.format("Memory[%d] = %d", i, memory.peek(i)));
		}
		 */

		// 2 blocks, 4 bytes/block, direct mapped
		ReadOnlyCache cache = new Cache(memory, 2, 4, 1); // direct mapped cache
		checkEqual(0, memory.getReadCalls(), "checking zero reads from memory initially.");

		// get value at address 1 this will load block (0,1,2,3)
		checkEqual(memory.peek(1), cache.load(1), "Mem[1] value");
		checkEqual(1, memory.getReadCalls(), "one read after first load");

		// get values at in (0,1,2,3) again, should not hit memory a second time.
		for(int i : new int[] { 0, 2, 3 }){
			checkEqual(memory.peek(i), cache.load(i), "Mem[" + i + "] value");
		}
		checkEqual(1, memory.getReadCalls(), "1 memory read after accessing 0, 2 and 3");

		// access the next block (4,5,6,7)
		checkEqual(memory.peek(7), cache.load(7), "Mem[7] value");
		checkEqual(2, memory.getReadCalls(), "2 memory reads after accessing 7");
		for(int i : new int[] { 4, 6, 5 }){
			checkEqual(memory.peek(i), cache.load(i), "Mem[" + i + "] value");
		}
		checkEqual(2, memory.getReadCalls(), "2 memory reads after accessing 4, 5, 6");

		// go back to the first block and make sure there are no more memory 
		checkEqual(memory.peek(1), cache.load(1), "Mem[1] value");
		checkEqual(2, memory.getReadCalls(), "2 memory reads after accessing 1 again");

		// the next block (8,9,10,11) should replace (0,1,2,3) ...
		checkEqual(memory.peek(10), cache.load(10), "Mem[10] value");
		checkEqual(3, memory.getReadCalls(), "3 memory reads after accessing 10");

		for(int i : new int[] { 8, 9, 10, 11 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(3, memory.getReadCalls(), "3 memory reads after accessing (8,9,10,11)");

		// check that (4,5,6,7) is still in the cache and does not require a memory access
		for(int i : new int[] { 4, 5, 6, 7 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(3, memory.getReadCalls(), "3 memory reads after accessing (4,5,6,7)");

		// now access (0,1,2,3) again and make sure there was another memory access
		for(int i : new int[] { 2, 3, 0, 1, 2, 3 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(4, memory.getReadCalls(), "4 memory reads after accessing (0,1,2,3) again");
	}
	
	private static void testSetAssociative()
	{	s_currentMethod = new Object(){}.getClass().getEnclosingMethod().getName();

		TestMemory memory = new TestMemory(s_rand, 32);
	
		// 4 blocks, 4 bytes/block, 2-associative (4/2 = 2 so there are two sets:
		// Set 0: (0,1,2,3)		(8,9,10,11)		(16,17,18,19) ...
		// Set 1: (4,5,6,7)		(12,13,14,15)	(20,21,22,23) ...
		ReadOnlyCache cache = new Cache(memory, 4, 4, 2);
		checkEqual(0, memory.getReadCalls(), "checking zero reads from memory initially.");
		
		// access first block (0,1,2,3) -- this is set 0
		for(int i : new int[] { 2, 3, 0, 1, 2, 3 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(1, memory.getReadCalls(), "1 memory reads after accessing (0,1,2,3)");

		// access another block (8,9,10,11) -- this is set 0
		for(int i : new int[] { 8, 9, 8, 10, 11 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(2, memory.getReadCalls(), "2 memory reads after accessing (8,9,10,11)");

		// access (0,1,2,3) -- this is set 0
		for(int i : new int[] { 2, 1, 3, 0 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(2, memory.getReadCalls(), "2 memory reads after accessing (0,1,2,3) again");
		
		// access a different block (4,5,6,7), this should go in the other set
		for(int i : new int[] { 7, 6, 5, 4 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(3, memory.getReadCalls(), "3 memory reads after accessing (4,5,6,7)");
	
		// access first block again, should not have been kicked out.
		for(int i : new int[] { 1, 3, 0 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(3, memory.getReadCalls(), "3 memory reads after accessing (0,1,2,3) again");
		
		// at this point we have the following:
		//     set 0 is full with (0,1,2,3) and (8,9,10,11),
		//     set 1 is half full with (4,5,6,7)
		
		// access set 0 again with different block, and (8,9,10,11) should have been kicked out
		for(int i : new int[] { 16, 18, 17, 19 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(4, memory.getReadCalls(), "4 memory reads after accessing set 0 (16,17,18,19) ");
		
		// check that (0,1,2,3) doesn't require another memory access
		for(int i : new int[] { 2, 0, 3, 1 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(4, memory.getReadCalls(), "4 memory reads after accessing (0,1,2,3) again");

		// check that (8,9,10,11) requires another memory access
		for(int i : new int[] { 9, 10 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(5, memory.getReadCalls(), "5 memory reads after accessing (8,9,10,11) again");
	}


	private static void testFullyAssociative()
	{	s_currentMethod = new Object(){}.getClass().getEnclosingMethod().getName();

		TestMemory memory = new TestMemory(s_rand, 32);

		// 2 blocks, 4 bytes/block, fully associative (associativity == # blocks)
		ReadOnlyCache cache = new Cache(memory, 2, 4, 2);
		checkEqual(0, memory.getReadCalls(), "checking zero reads from memory initially.");

		// access first block
		for(int i : new int[] { 2, 3, 0, 1, 2, 3 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(1, memory.getReadCalls(), "1 memory reads after accessing (0,1,2,3) again");

		// access another block
		for(int i : new int[] { 8, 9, 8, 11 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(2, memory.getReadCalls(), "2 memory reads after accessing (8,9,10,11)");

		// access first block again
		for(int i : new int[] { 0 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(2, memory.getReadCalls(), "2 memory reads after accessing (0,2,3,4) again");

		// access a different block (4,5,6,7), this should kick out (8,9,10,11) due to lru
		for(int i : new int[] { 7, 6, 5, 4 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(3, memory.getReadCalls(), "3 memory reads after accessing (4,5,6,7)");

		// access first block again, should not have been kicked out.
		for(int i : new int[] { 1, 3, 0 }){
			checkEqual(memory.peek(i), cache.load(i), String.format("Mem[%d] value", i));
		}
		checkEqual(3, memory.getReadCalls(), "3 memory reads after accessing (0,2,3,4) again");
	}

	private static void checkEqual(int expected, int actual, String message)
	{
		if(expected != actual){
			System.out.println(String.format("FAIL(%s): expected <%d> but was <%d>: %s", 
					s_currentMethod, expected, actual, message));
			System.exit(1);
		}
		else {
			System.out.println(String.format("PASS(%s): %s", s_currentMethod, message));
		}
	}

}
