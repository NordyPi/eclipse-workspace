package edu.du.cs.loklinnord.lab6;

public class TreeTest {

	public static void main(String[] args) {
		AVL<Integer> tree = new AVL<Integer>();
		tree.displayTree();

		System.out.println("insert 3");
		tree.insert(3);
		tree.displayTree();
		System.out.println("");     

		System.out.println("insert 2");
		tree.insert(2);
		tree.displayTree();
		System.out.println("");

		// Right rotation
		System.out.println("insert 1");
		tree.insert(1);
		tree.displayTree();
		System.out.println("");     

		System.out.println("insert 4");
		tree.insert(4);
		tree.displayTree();
		System.out.println("");     

		// Left rotation
		System.out.println("insert 5");
		tree.insert(5);
		tree.displayTree();
		System.out.println("");     

		// Left rotation
		System.out.println("insert 6");
		tree.insert(6);
		tree.displayTree();
		System.out.println("");     

		// Left rotation
		System.out.println("insert 7");
		tree.insert(7);
		tree.displayTree();
		System.out.println("");     

		System.out.println("insert 17");
		tree.insert(17);
		tree.displayTree();
		System.out.println("");     

		// Right, left rotation
		System.out.println("insert 16");
		tree.insert(16);
		tree.displayTree();
		System.out.println("");     

		// Right, left rotation
		System.out.println("insert 15");
		tree.insert(15);
		tree.displayTree();
		System.out.println("");     

		// Left rotation
		System.out.println("insert 13");
		tree.insert(13);
		tree.displayTree();
		System.out.println("");     

		// Left, Right rotation
		System.out.println("insert 14");
		tree.insert(14);
		tree.displayTree();
		System.out.println("");
	}

}
