package edu.du.cs.loklinnord.lab6;

import java.util.LinkedList;
import java.util.Queue;

// Binary Search Tree implementation
public class AVL<T extends Comparable<T>> {

	// Nested class to hold tree nodes
	class TNode {
		private T nodeValue;
		private TNode left;
		private TNode right;
		private TNode parent;
		private int bf;

		public TNode(T item) {
			nodeValue = item;
			left = null;
			right = null;
			parent = null;
			bf = 0; 
		}
		
		public String toString() {
			return nodeValue.toString();
		}
	}

	private TNode root;

	// Construct an empty BST
	public AVL() {
		root = null;
	}

	public boolean contains(T t) {
		boolean result;

		// Start at the root
		TNode current = root;

		// Keep going until we reach the end of the tree (null) or
		// until we find a node that is equal to the target
		while ((current != null) && (!current.nodeValue.equals(t))) {

			if (current.nodeValue.compareTo(t) > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		if (current == null) {
			result = false;
		} else {
			result = true;
		}

		return result;
	}

	public void insert(T t) {
		TNode newNode = new TNode(t);
		
		if (root == null) {
			root = newNode;
		} else {
			TNode current = root;
			boolean done = false;
			while (!done) {	
				// all i added here was changing the weight of the current value on placement
				if (current.nodeValue.compareTo(t) > 0) {
					if (current.left == null) {
						current.left = newNode;
						newNode.parent = current;
						done = true;
						current.bf += 1;
					} else {
						current = current.left;
					}

				} else {
					if (current.right == null) {
						current.right = newNode;
						newNode.parent = current;
						done = true;
						current.bf -= 1;
					} else {
						current = current.right;
					}
				}
			}
			// calls rebalance after new object has been added to tree
			insertRebalance(current);
		}
	}
	
	public void insertRebalance(TNode n) {
		//System.out.println("before rebalancing of node: " + n.nodeValue);
		//displayTree();
		//System.out.println();
		// checks to see if tree is out of balance
		if (n.bf == -1 || n.bf == 1) {
			// tree might not have parent so do nothing on error
			try {
				if(n.nodeValue.compareTo(n.parent.nodeValue) > 0) {
					n.parent.bf -= 1;
				} else {
					n.parent.bf += 1;
				}
				// continues by calling itself on the parent
				insertRebalance(n.parent);	
			} catch(Exception e) {
				
			}
		} else if (n.bf == 2) {
			// left left case
			if(n.left.bf == 1) {
				rightRotation(n);
			} else {
				// left right case
				leftRotation(n.left);
				rightRotation(n);
			}
		} else if (n.bf == -2) {
			// right right case
			if(n.right.bf == -1) {
				leftRotation(n);
			} else {
				rightRotation(n.right);
				leftRotation(n);
			}
		}
	}
	
	public void leftRotation(TNode n) {
		TNode oldParent = n.parent;
		n.parent = n.right;
		n.right = n.parent.left;
		if (n.parent.left != null) {
			n.parent.left.parent = n;
		}
		n.parent.left = n;
		n.parent.parent = oldParent;
		// if n was the root, then we need to change the root. if not, configure the N's new parent
		if (oldParent == null) {
			root = n.parent;
		} else {
			// figure out if B should be placed to right or left of oldParent
			if (oldParent.nodeValue.compareTo(n.parent.nodeValue) > 0) {
				oldParent.left = n.parent;
			} else {
				oldParent.right = n.parent;
			}
		}
		int oldBFB = n.parent.bf;
		n.bf = n.bf + 1 - Math.min(0, oldBFB);
		n.parent.bf = oldBFB + 1 + Math.max(0, n.bf);
	}
	
	public void rightRotation(TNode n) {
		TNode oldParent = n.parent;
		n.parent = n.left;
		n.left = n.parent.right;
		if (n.parent.right != null) {
			n.parent.right.parent = n;
		}
		n.parent.right = n;
		n.parent.parent = oldParent;
		// if n was the root, then we need to change the root. if not, configure the N's new parent
		if (oldParent == null) {
			root = n.parent;
		} else {
			// figure out if B should be placed to right or left of oldParent
			if (oldParent.nodeValue.compareTo(n.parent.nodeValue) > 0) {
				oldParent.left = n.parent;
			} else {
				oldParent.right = n.parent;
			}
		}
		int oldBFB = n.parent.bf;
		n.bf = n.bf - 1 - Math.max(0, oldBFB);
		n.parent.bf = oldBFB - 1 + Math.min(0, n.bf);
	}

	public void delete(T t) {
		TNode current = root;
		// -1 means we it is right of parent, 1 means it is left of parent
		boolean fromRight = false;
		
		while (!current.nodeValue.equals(t)) {
			// if current node is less than target value, branch right. if it is greater, branch left
			int direction = current.nodeValue.compareTo(t);
			if (direction < 0) {
				current = current.right;
				fromRight = true;
			} else {
				current = current.left;
				fromRight = false;
			}
		}
		// found our node, move on to reordering
		// if node with no children
		if (current.left == null && current.right == null) {
			if (fromRight) {
				current.parent.right = null;
			} else {
				current.parent.left = null;
			}
		}
		// if node with two children
		else if (current.left != null && current.right != null) {
			TNode parentt = current.parent;
			TNode leftSide = current.left;
			TNode rightSide = current.right;
			TNode successor = rightSide;
			//finds the successor node
			while (successor.left != null) {
				successor = successor.left;
			}
			//replaces successor with its right child tree (only if it has children)
			TNode successorChild = successor.right;
			if (successorChild != null) {
				successorChild.parent = successor.parent;
				rightSide.left = successorChild;
				successor.right = rightSide;
			}
			successor.left = leftSide;
			//replaces the deleted node with successor
			if (fromRight) {
				parentt.right = successor;
			} else { 
				parentt.left = successor;
			}
			
		}
		// if node with one child (either child node is null)
		else if (current.left == null || current.right == null) {
			if (current.right == null) {
				current.parent.left = current.left;
				current.left.parent = current.parent;
			} else {
				current.parent.right = current.right;
				current.right.parent = current.parent;
			}
		}
	}
	

	// This is a debugging utility.
	// It simply displays all the nodes at each level, but doesn't show which ones
	// are corrected to which parents.  You will need to use the debugger to inspect
	// that information if needed for debugging.
	public void displayTree() {
		// Doesn't show links between parent and child nodes
		if (root == null) {
			System.out.println("Tree is empty");
		} else {
			Queue<TNode> q = new LinkedList<TNode>();
			Queue<Integer> levelQ = new LinkedList<Integer>();
			Queue<Integer> tabQ = new LinkedList<Integer>();
			q.add(root);
			levelQ.add(0);
			tabQ.add(10);
			int currentLevel = 0;
			int currentTab = 0;
			
			while (!q.isEmpty()) {
				TNode current = q.remove();
				int level = levelQ.remove();
				int tab = tabQ.remove();
				
				if (currentLevel != level) {
					System.out.print("\n");
					currentLevel++;
					currentTab = 0;
					
					
					// Display the connectors....
				
					// First make a copy of the data
					Queue<TNode> qCopy = new LinkedList<TNode>(q);
					Queue<Integer> levelQCopy = new LinkedList<Integer>(levelQ);
					Queue<Integer> tabQCopy = new LinkedList<Integer>(tabQ);
					
					// Then run through all the elements until the next level hits
					int currentLevelCopy = currentLevel;
					int currentTabCopy = currentTab;				
					
					TNode currentCopy = current;
					int levelCopy = level;
					int tabCopy = tab;		
				
					while (currentLevelCopy == levelCopy) {
						while (currentTabCopy<tabCopy) {
							System.out.print("   ");
							currentTabCopy++;
						}					
						if (currentCopy.parent.left == currentCopy) {
							System.out.print(" /");
						} else {
							System.out.print("\\");
						}
						
							
						if (!qCopy.isEmpty()) {
							currentCopy = qCopy.remove();
							levelCopy = levelQCopy.remove();
							tabCopy = tabQCopy.remove();
						} else {
							levelCopy = -1;  // We hit the end of the queue before the next level started
						}
					}
					System.out.println("\n");
					// Done displaying connectors
					
				}
				while (currentTab<tab) {
					System.out.print("   ");
					currentTab++;
				}
				
				System.out.print(current.nodeValue);
				//System.out.print(current.nodeValue + "<" + current.bf + ">");
				
				// if a left child exists, insert it in the queue
				if (current.left != null) {
					q.add(current.left);
					levelQ.add(level+1);
					tabQ.add(tab-1);
				}
				// if a right child exists, insert next to its sibling
				if (current.right != null) {
					q.add(current.right);
					levelQ.add(level+1);
					tabQ.add(tab+1);
				}
			}
		}
		System.out.print("\n");
	}

}
