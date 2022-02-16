package edu.du.cs.loklinnord.lab5;
public class TreeTest {

	public static void main(String[] args) {
		deleteTest();
	}
	
	public static void deleteTest() {
		
		// Delete normal leaf node
		BST<Integer> tree = loadTree();
		System.out.println("Tree:");
		tree.displayTree();
		tree.delete(40);
		System.out.println("\nTree after deleting 40:");
		tree.displayTree();
		
		
		// Delete interior node with one child
		tree = loadTree();
		System.out.println("\n\nTree:");
		tree.displayTree();
		tree.delete(15);
		System.out.println("\nTree after deleting 15:");
		tree.displayTree();
		
		
		// Delete interior node with two children
		tree = loadTree();
		System.out.println("\n\s:");
		tree.displayTree();
		tree.delete(25);
		System.out.println("\nTree after deleting 25:");
		tree.displayTree();
		
		
		// Delete a different interior node with two children
		tree = loadTree();
		System.out.println("\n\nTree:");
		tree.displayTree();
		tree.delete(35);
		System.out.println("\nTree after deleting 35:");
		tree.displayTree();

	}
	
	private static BST<Integer> loadTree() {
		/* create this tree:   
		 *                     50 
		 *                    /     
		 *                   25 
		 *                 /    \   
		 *               15     35  
		 *              /      /  \
		 *             5      30  40
		 *                     \
		 *                     33
		 *                    /  \
		 *                   31  34
		 */
		BST<Integer> tree = new BST<Integer>();
		tree.insert(50);
		tree.insert(25);
		tree.insert(15);
		tree.insert(35);
		tree.insert(5);
		tree.insert(30);
		tree.insert(40);
		tree.insert(33);
		tree.insert(31);
		tree.insert(34);
		return tree;	
	}
}
