package com.demo.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * BinaryTree Implementation in Java
 * 
 * This program provides a comprehensive implementation of a Binary Tree, 
 * featuring essential operations like insertion, deletion, and traversal, 
 * along with advanced techniques such as Morris traversal and structural 
 * validations (e.g., checking for Binary Search Tree, Full Binary Tree, etc.).
 * 
 * <p><strong>Key Features:</strong></p>
 * <ul>
 *   <li>Dynamic node insertion and deletion</li>
 *   <li>Search operations for specific nodes</li>
 *   <li>Tree traversal methods:
 *     <ul>
 *       <li>Inorder, Preorder, Postorder (traditional)</li>
 *       <li>Level-order traversal using a custom queue</li>
 *       <li>Morris Traversal (Inorder, Preorder, Postorder)</li>
 *     </ul>
 *   </li>
 *   <li>Tree structural checks:
 *     <ul>
 *       <li>Full Binary Tree</li>
 *       <li>Complete Binary Tree</li>
 *       <li>Binary Search Tree (BST)</li>
 *     </ul>
 *   </li>
 *   <li>Utility methods for height and depth calculation</li>
 *   <li>Visualization support with indentation-based tree printing</li>
 * </ul>
 * 
 * <p><strong>Example Dummy Tree:</strong></p>
 * The `createDummy()` method generates the following tree structure:
 * <pre>
 *          10
 *        /    \
 *      20      30
 *     /  \    /
 *   40   50  60
 *  /  \
 * 70   80
 * </pre>
 * 
 * 
 * <p><strong>Menu Options:</strong></p>
 * The program offers an interactive menu-driven interface to perform operations:
 * <ol>
 *   <li>Create a dummy tree</li>
 *   <li>Insert a node</li>
 *   <li>Delete the entire tree</li>
 *   <li>Delete a specific node</li>
 *   <li>Search for a node</li>
 *   <li>Traverse the tree</li>
 *   <li>Find siblings of a node</li>
 *   <li>Calculate the height of the tree</li>
 *   <li>Calculate the depth of a node</li>
 *   <li>Check if the tree is a full binary tree</li>
 *   <li>Check if the tree is a complete binary tree</li>
 *   <li>Check if the tree is a Binary Search Tree (BST)</li>
 *   <li>Exit</li>
 * </ol>
 * 
 * <p><strong>How to Use:</strong></p>
 * 1. Compile the program:
 *    <pre>javac com/demo/binary/BinaryTree.java</pre>
 * 2. Run the program:
 *    <pre>java com.demo.binary.BinaryTree</pre>
 * 3. Follow the menu instructions to perform operations.
 * 
 * <p><strong>Author:</strong> Soumyajit Paul</p>
 * <p><strong>Date:</strong> 2024</p>
 */

/**
 * Class representing a node in the queue.
 */
class QueueNode {
	TreeNode node;
	QueueNode next;

	/**
	 * Constructor to initialize a new queue node with given tree node.
	 *
	 * @param node The tree node to be stored in the queue node.
	 */
	QueueNode(TreeNode node) {
		this.node = node;
		next = null;
	}
}

/**
 * Class representing a queue implemented using a linked list.
 */
class Queue {

	QueueNode front;
	QueueNode rear;

	/**
	 * Constructor to initialize an empty queue.
	 */
	Queue() {
		front = null;
		rear = null;
	}

	/**
	 * Method to add a tree node to the queue.
	 *
	 * @param node The tree node to be added to the queue.
	 */
	void enqueue(TreeNode node) {
		QueueNode newNode = new QueueNode(node);
		if (rear == null) {
			front = rear = newNode;
			return;
		}
		rear.next = newNode;
		rear = newNode;
	}

	/**
	 * Method to remove and return a tree node from the queue.
	 *
	 * @return The tree node removed from the queue, or null if the queue is empty.
	 */
	TreeNode dequeue() {
		if (front == null) {
			return null;
		}
		TreeNode node = front.node;
		front = front.next;
		if (front == null) {
			rear = null;
		}
		return node;
	}

	/**
	 * Method to check if the queue is empty.
	 *
	 * @return true if the queue is empty, false otherwise.
	 */
	boolean isEmpty() {
		return this.front == null;
	}
}

/**
 * Class representing a node in a binary tree.
 */
class TreeNode {
	Integer data;
	TreeNode left;
	TreeNode right;

	/**
	 * Constructor to initialize a new node with given data.
	 *
	 * @param data The value to be stored in the node.
	 */
	TreeNode(Integer data) {
		this.data = data;
		left = null;
		right = null;
	}
}

/**
 * Class representing a binary tree with basic operations such as insertion,
 * traversal, search, and depth calculation.
 */
public class BinaryTree {

	TreeNode root;

	/**
	 * Constructor to initialize an empty binary tree.
	 */
	BinaryTree() {
		root = null;
	}

	/**
	 * Method to create a dummy tree for utilization
	 * 
	 * @return root of the dummy tree
	 */

	public static TreeNode createDummy() {

		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(20);
		root.right = new TreeNode(30);
		root.left.left = new TreeNode(40);
		root.left.left.left = new TreeNode(70);
		root.left.left.right = new TreeNode(80);
		root.left.right = new TreeNode(50);
		root.right.left = new TreeNode(60);
		System.out.println("Dummy tree Generated");
		System.out.println("----------------------------------------------------------------------------------");
		return root;
	}

	/**
	 * Method to insert a node into the binary tree.
	 *
	 * @param tree The binary tree object.
	 * @param data The data to insert.
	 * @param in   BufferedReader for user input.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void insert(BinaryTree tree, Integer data, BufferedReader in) throws IOException {
		tree.root = insertNode(tree.root, data, in);
	}

	/**
	 * Helper method to insert a node into the binary tree.
	 *
	 * @param root The root node of the tree.
	 * @param data The data to insert.
	 * @param in   BufferedReader for user input.
	 * @return The updated root node.
	 * @throws IOException If an I/O error occurs.
	 */
	public static TreeNode insertNode(TreeNode root, Integer data, BufferedReader in) throws IOException {
		if (root == null) {
			root = new TreeNode(data); // Insert at the current position if null
			return root;
		} else {
			System.out.println("Would you like to insert the data at the left or right of node " + root.data + "?");
			String choice = in.readLine();
			if (choice.equals("left")) {
				if (root.left != null) {
					System.out.println(
							"A node already exists at the left of node " + root.data + ". Overwrite? (yes/no)");
					String overwriteChoice = in.readLine();
					if (overwriteChoice.equals("yes")) {
						root.left.data = data; // Overwrite the existing left node
					} else {
						root.left = insertNode(root.left, data, in); // Recursively insert in the left subtree
					}
				} else {
					root.left = new TreeNode(data); // Insert new node at left
				}
			} else if (choice.equals("right")) {
				if (root.right != null) {
					System.out.println(
							"A node already exists at the right of node " + root.data + ". Overwrite? (yes/no)");
					String overwriteChoice = in.readLine();
					if (overwriteChoice.equals("yes")) {
						root.right.data = data; // Overwrite the existing right node
					} else {
						root.right = insertNode(root.right, data, in); // Recursively insert in the right subtree
					}
				} else {
					root.right = new TreeNode(data); // Insert new node at right
				}
			}
			return root;
		}
	}

	/**
	 * Helper method to find the parent of a node in the binary tree.
	 *
	 * @param root       The root node of the tree.
	 * @param searchData The data of the node whose parent is to be found.
	 * @return The parent node, or null if no parent is found.
	 */
	public static TreeNode findParent(TreeNode root, int searchData) {
		if (root == null || (root.left == null && root.right == null)) {
			return null;
		}

		if ((root.left != null && root.left.data == searchData)
				|| (root.right != null && root.right.data == searchData)) {
			return root;
		}

		TreeNode parent = findParent(root.left, searchData);
		if (parent != null) {
			return parent;
		}
		return findParent(root.right, searchData);
	}

	/**
	 * Method to delete a node from the binary tree.
	 *
	 * @param tree The binary tree object.
	 * @param data The data of the node to delete.
	 * @param in   BufferedReader for user input.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void deleteNode(BinaryTree tree, int data, BufferedReader in) throws IOException {
		if (tree.root == null) {
			System.out.println("The tree is empty.");
			return;
		}

		// Check if the node to delete is the root node
		if (Objects.equals(tree.root.data, data)) {
			if (tree.root.left == null && tree.root.right == null) {
				tree.root = null; // Deleting the root node if it is a leaf
				System.out.println("Node " + data + " was a leaf and has been deleted.");
			} else {
				System.out.println("The node " + data
						+ " is the root and has children. Are you sure you want to delete the entire tree? (yes/no)");
				String choice = in.readLine();
				if (choice.equals("yes")) {
					tree.root = null; // Deleting the entire tree
					System.gc();
					System.out.println("The tree has been deleted.");
				} else {
					System.out.println("Deletion aborted.");
				}
			}
			return;
		}

		// Find the parent of the node to be deleted
		TreeNode parent = findParent(tree.root, data);
		if (parent == null) {
			System.out.println("Node " + data + " not found in the tree.");
			return;
		}

		// Determine the child node to be deleted
		TreeNode nodeToDelete;
		if (parent.left != null && Objects.equals(parent.left.data, data)) {
			nodeToDelete = parent.left;
		} else {
			nodeToDelete = parent.right;
		}

		// Case 1: Node to delete is a leaf node
		if (nodeToDelete.left == null && nodeToDelete.right == null) {
			if (parent.left == nodeToDelete) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			System.out.println("Node " + data + " was a leaf and has been deleted.");
		}
		// Case 2: Node to delete has children
		else {
			System.out.println("The node " + data
					+ " has children. Are you sure you want to delete it along with its subtrees? (yes/no)");
			String choice = in.readLine();
			if (choice.equals("yes")) {
				if (parent.left == nodeToDelete) {
					parent.left = null;
				} else {
					parent.right = null;
				}
				System.out.println("Node " + data + " and its subtrees have been deleted.");
			} else {
				System.out.println("Deletion aborted.");
			}
		}
	}

	/**
	 * Method to search for a node in the binary tree using pre-order traversal.
	 *
	 * @param root       The root node of the tree.
	 * @param searchData The data to search for.
	 * @return true if the node is found, false otherwise.
	 */
	public static boolean searchNode(TreeNode root, Integer searchData) {
		if (root == null) {
			return false;
		}
		if (Objects.equals(root.data, searchData)) {
			return true;
		}
		boolean foundInLeftSubtree = searchNode(root.left, searchData); // Search in the left subtree
		if (foundInLeftSubtree) {
			return true;
		}
		return searchNode(root.right, searchData); // Search in the right subtree
	}

	/**
	 * Method to handle various tree traversal operations based on user input.
	 *
	 * @param root The root node of the tree.
	 * @param in   BufferedReader for user input.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void traversal(TreeNode root, BufferedReader in) throws IOException {
		if (root == null) {
			System.out.println("The binary tree is currently empty.");
			return;
		}

		// Loop to continuously prompt the user for traversal options
		while (true) {
			System.out.println("Select a traversal method:");
			System.out.println("1. Inorder Traversal");
			System.out.println("2. Preorder Traversal");
			System.out.println("3. Postorder Traversal");
			System.out.println("4. Level Order Traversal");
			System.out.println("5. Print the tree structure");
			System.out.println("6. Moriss Traversal List");
			System.out.println("7. Return to Main Menu");

			System.out.print("Enter your choice: ");
			Integer choice = Integer.parseInt(in.readLine());
			System.out.println("----------------------------------------------------------------------------------");
			switch (choice) {
			case 1:
				inOrder(root);
				System.out.println();
				break;
			case 2:
				preOrder(root);
				System.out.println();
				break;
			case 3:
				postOrder(root);
				System.out.println();
				break;
			case 4:
				levelOrder(root);
				System.out.println();
				break;
			case 5:
				treePrint(root, "");
				System.out.println();
				break;
			case 6:
				moriss(root, in);
				System.out.println();
				break;
			case 7:
				System.out.println("Returning to the main menu.");
				return;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
			System.out.println("----------------------------------------------------------------------------------");
		}
	}

	/**
	 * Method for in-order traversal of the binary tree.
	 *
	 * @param root The root node of the tree.
	 */
	public static void inOrder(TreeNode root) {
		if (root != null) {
			inOrder(root.left); // Traverse the left subtree
			System.out.print(root.data + " "); // Visit the root node
			inOrder(root.right); // Traverse the right subtree
		}
	}

	/**
	 * Method for pre-order traversal of the binary tree.
	 *
	 * @param root The root node of the tree.
	 */
	public static void preOrder(TreeNode root) {
		if (root != null) {
			System.out.print(root.data + " "); // Visit the root node
			preOrder(root.left); // Traverse the left subtree
			preOrder(root.right); // Traverse the right subtree
		}
	}

	/**
	 * Method for post-order traversal of the binary tree.
	 *
	 * @param root The root node of the tree.
	 */
	public static void postOrder(TreeNode root) {
		if (root != null) {
			postOrder(root.left); // Traverse the left subtree
			postOrder(root.right); // Traverse the right subtree
			System.out.print(root.data + " "); // Visit the root node
		}
	}

	/**
	 * Method for level order traversal of the binary tree using a custom queue.
	 *
	 * @param root The root node of the tree.
	 */
	public static void levelOrder(TreeNode root) {
		if (root == null) {
			System.out.println("The binary tree is currently empty.");
			return;
		}

		Queue queue = new Queue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.dequeue();
			System.out.print(current.data + " ");

			if (current.left != null) {
				queue.enqueue(current.left);
			}
			if (current.right != null) {
				queue.enqueue(current.right);
			}
		}
		System.out.println();
	}

	/**
	 * 
	 * @param root   The root node of the tree.
	 * @param prefix indicates the depth of tree(default value is empty String)
	 */
	public static void treePrint(TreeNode root, String prefix) {
		if (root != null) {
			System.out.println(prefix + root.data + " "); // Visit the root node
			treePrint(root.left, prefix + "|-"); // Traverse the left subtree
			treePrint(root.right, prefix + "|-"); // Traverse the right subtree
		}
	}

	/**
	 * Method to handle various moriss-traversal operations based on user input.
	 * 
	 * @param root The root node of the tree.
	 * @param in   BufferedReader for user input.
	 * @throws IOException If an I/O error occurs.
	 */

	public static void moriss(TreeNode root, BufferedReader in) throws IOException {
		if (root == null) {
			System.out.println("The binary tree is currently empty.");
			return;
		}

		// Loop to continuously prompt the user for traversal options
		while (true) {
			System.out.println("Select a traversal method:");
			System.out.println("1. Inorder Moriss Traversal");
			System.out.println("2. Preorder Moriss Traversal");
			System.out.println("3. Postorder Moriss Traversal");
			System.out.println("4. Return to Traversal Menu");

			System.out.print("Enter your choice: ");
			Integer choice = Integer.parseInt(in.readLine());
			System.out.println("----------------------------------------------------------------------------------");
			switch (choice) {
			case 1:
				morissTraversalInOrder(root);
				System.out.println();
				break;
			case 2:
				morrisTraversalPreOrder(root);
				System.out.println();
				break;
			case 3:
				morrisTraversalPostOrder(root);
				System.out.println();
				break;
			case 4:
				System.out.println("Returning to the traversal menu.");
				return;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
			System.out.println("----------------------------------------------------------------------------------");
		}
	}

	/**
	 * Method for in-order traversal of the binary tree using moriss-traversal.
	 * 
	 * @param root The root node of the tree.
	 */

	public static void morissTraversalInOrder(TreeNode root) {
		TreeNode current = root;
		while (current != null) {
			if (current.left == null) {
				System.out.print(current.data + " ");
				current = current.right;
			} else {
				TreeNode predecessor = current.left;
				while (predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				} else {
					predecessor.right = null;
					System.out.print(current.data + " ");
					current = current.right;
				}
			}
		}
	}

	/**
	 * Method for pre-order traversal of the binary tree using moriss-traversal.
	 * 
	 * @param root The root node of the tree.
	 */

	public static void morrisTraversalPreOrder(TreeNode root) {
		TreeNode current = root;

		while (current != null) {
			if (current.left == null) {
				System.out.print(current.data + " ");
				current = current.right;
			} else {
				TreeNode predecessor = current.left;
				while (predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					System.out.print(current.data + " "); // Print before creating the thread
					predecessor.right = current;
					current = current.left;
				} else {
					predecessor.right = null;
					current = current.right;
				}
			}
		}
	}

	/**
	 * Method for post-order traversal of the binary tree using moriss-traversal.
	 * 
	 * @param root
	 */
	public static void morrisTraversalPostOrder(TreeNode root) {
		TreeNode dummyRoot = new TreeNode(0);
		dummyRoot.left = root;
		TreeNode current = dummyRoot;

		while (current != null) {
			if (current.left == null) {
				current = current.right;
			} else {
				TreeNode predecessor = current.left;
				// Find the rightmost node in the left subtree or the link back to the current
				// node
				while (predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}

				if (predecessor.right == null) {
					// Create a thread back to the current node
					predecessor.right = current;
					current = current.left;
				} else {
					// Reverse the path from predecessor to current
					printReverse(current.left, predecessor);
					predecessor.right = null;
					current = current.right;
				}
			}
		}
	}

	/**
	 * Helper function to print the nodes from `from` to `to` in reverse order
	 * 
	 * @param reverse index
	 * @param to      reverse index
	 */
	public static void printReverse(TreeNode from, TreeNode to) {
		reversePath(from, to);

		TreeNode current = to;
		while (true) {
			System.out.print(current.data + " ");
			if (current == from)
				break;
			current = current.right;
		}

		// Restore the path back to original
		reversePath(to, from);
	}

	/**
	 * Helper function to reverse the right pointers in the path from `from` to `to`
	 * 
	 * @param from reverse index
	 * @param to   reverse index
	 */
	public static void reversePath(TreeNode from, TreeNode to) {
		if (from == to)
			return;

		TreeNode prev = null;
		TreeNode current = from;
		TreeNode next;

		while (current != to) {
			next = current.right;
			current.right = prev;
			prev = current;
			current = next;
		}
		current.right = prev;
	}

	/**
	 * Method to find the siblings of a node in the binary tree.
	 *
	 * @param root       The root node of the tree.
	 * @param searchData The data of the node whose siblings are to be found.
	 * @param parent     The parent node of the current node (initially null).
	 */
	public static void findSiblings(TreeNode root, Integer searchData, TreeNode parent) {
		if (root == null) {
			System.out.println("The binary tree is currently empty.");
			return;
		}

		if (Objects.equals(root.data, searchData)) {
			if (parent == null) {
				System.out.println("The node " + searchData + " is the root node and has no siblings.");
			} else {
				if (parent.left != null && !Objects.equals(parent.left.data, searchData)) {
					System.out.println("Sibling of node " + searchData + ": " + parent.left.data);
				}
				if (parent.right != null && !Objects.equals(parent.right.data, searchData)) {
					System.out.println("Sibling of node " + searchData + ": " + parent.right.data);
				}
			}
		} else {
			findSiblings(root.left, searchData, root); // Search in the left subtree
			findSiblings(root.right, searchData, root); // Search in the right subtree
		}
	}

	/**
	 * Method to calculate the height of the binary tree.
	 *
	 * @param root The root node of the tree.
	 * @return The height of the tree.
	 */
	public static int calculateHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = calculateHeight(root.left);
		int rightHeight = calculateHeight(root.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}

	/**
	 * Method to calculate the depth of a node in the binary tree.
	 *
	 * @param root       The root node of the tree.
	 * @param searchData The data of the node whose depth is to be calculated.
	 * @param depth      The current depth (initially 0).
	 * @return The depth of the node, or -1 if the node is not found.
	 */
	public static int calculateDepth(TreeNode root, Integer searchData, int depth) {
		if (root == null) {
			return -1;
		}
		if (Objects.equals(root.data, searchData)) {
			return depth;
		}
		int leftDepth = calculateDepth(root.left, searchData, depth + 1); // Calculate depth in left subtree
		if (leftDepth != -1) {
			return leftDepth;
		}
		return calculateDepth(root.right, searchData, depth + 1); // Calculate depth in right subtree
	}

	/**
	 * Method to check if the binary tree is a full binary tree.
	 *
	 * @param root The root node of the tree.
	 * @return true if the tree is a full binary tree, false otherwise.
	 */
	public static boolean isFullBinaryTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true; // A leaf node
		}
		if (root.left != null && root.right != null) {
			return isFullBinaryTree(root.left) && isFullBinaryTree(root.right); // Both children exist
		}
		return false;
	}

	/**
	 * Method to check if the binary tree is a complete binary tree.
	 *
	 * @param root The root node of the tree.
	 * @return true if the tree is a complete binary tree, false otherwise.
	 */
	public static boolean isCompleteBinaryTree(TreeNode root) {
		if (root == null) {
			return true;
		}

		Queue queue = new Queue();
		queue.enqueue(root);
		boolean flag = false;

		while (!queue.isEmpty()) {
			TreeNode currentNode = queue.dequeue();

			if (currentNode.left != null) {
				if (flag) {
					return false;
				}
				queue.enqueue(currentNode.left);
			} else {
				flag = true;
			}

			if (currentNode.right != null) {
				if (flag) {
					return false;
				}
				queue.enqueue(currentNode.right);
			} else {
				flag = true;
			}
		}
		return true;
	}

	/**
	 * Method to check if the tree is a BST
	 * 
	 * @param root The root node of the tree.
	 * @return true if the tree is BST, false otherwise
	 */
	public static boolean isBinarySearchTree(TreeNode root) {
		return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * helper method to check if the tree is BST
	 * 
	 * @param node    current node of the tree
	 * @param minimum of the sub tree
	 * @param maximum of the subtree
	 * @return true if both the sub trees are BSTs, false otherwise
	 */
	public static boolean isBSTHelper(TreeNode node, int min, int max) {
		if (node == null)
			return true;

		// If the current node's data
		// is not in the valid range, return false
		if (node.data < min || node.data > max)
			return false;

		// Recursively check the left and
		// right subtrees with updated ranges
		return isBSTHelper(node.left, min, node.data - 1) && isBSTHelper(node.right, node.data + 1, max);
	}

	/**
	 * Main method to interact with the user and perform various binary tree
	 * operations.
	 *
	 * @param args Command-line arguments (not used).
	 * @throws IOException If an I/O error occurs.
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BinaryTree tree = new BinaryTree();

		while (true) {
			System.out.println("Please choose an option:");
			System.out.println("0.  Create a dummy tree");
			System.out.println("1.  Insert a node");
			System.out.println("2.  Delete the entire tree");
			System.out.println("3.  Delete a specific node");
			System.out.println("4.  Search for a node");
			System.out.println("5.  Traverse the tree");
			System.out.println("6.  Find siblings of a node in the tree");
			System.out.println("7.  Calculate the height of the tree");
			System.out.println("8.  Calculate the depth of a node");
			System.out.println("9.  Check if the tree is a full binary tree");
			System.out.println("10. Check if the tree is a complete binary tree");
			System.out.println("11. Check if the tree is a BST");
			System.out.println("12. Exit");
			System.out.print("Enter your choice: ");
			Integer choice = Integer.parseInt(in.readLine());
			System.out.println("----------------------------------------------------------------------------------");

			switch (choice) {
			case 0:
				if (Objects.equals(tree.root, null)) {
					tree.root = createDummy();
				} else
					System.out.println("A tree already exists");
				break;
			case 1:
				System.out.print("Enter the value to insert: ");
				Integer data = Integer.parseInt(in.readLine());
				insert(tree, data, in);
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			case 2:
				tree.root = null; // Set the root to null, making the tree eligible for garbage collection
				System.gc(); // Suggest the garbage collector to reclaim memory
				System.out.println("The binary tree has been deleted.");
				System.out
						.println("----------------------------------------------------------------------------------");
				break;

			case 3:
				System.out.print("Enter the value to delete: ");
				int deleteData = Integer.parseInt(in.readLine());
				deleteNode(tree, deleteData, in);
				System.out
						.println("----------------------------------------------------------------------------------");
				break;

			case 4:
				System.out.print("Enter the value to search: ");
				Integer searchData = Integer.parseInt(in.readLine());
				boolean found = searchNode(tree.root, searchData);
				if (found) {
					System.out.println("Node " + searchData + " found in the tree.");
				} else {
					System.out.println("Node " + searchData + " not found in the tree.");
				}
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			case 5:
				traversal(tree.root, in);
				System.out
						.println("----------------------------------------------------------------------------------");
				break;

			case 6:
				System.out.print("Enter the value to find siblings of: ");
				Integer siblingData = Integer.parseInt(in.readLine());
				findSiblings(tree.root, siblingData, null);
				System.out
						.println("----------------------------------------------------------------------------------");
				break;

			case 7:
				int height = calculateHeight(tree.root);
				System.out.println("Height of the binary tree: " + height);
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			case 8:
				System.out.print("Enter the value to find the depth of: ");
				Integer depthData = Integer.parseInt(in.readLine());
				int depth = calculateDepth(tree.root, depthData, 0);
				if (depth != -1) {
					System.out.println("Depth of node " + depthData + ": " + depth);
				} else {
					System.out.println("Node " + depthData + " not found in the tree.");
				}
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			case 9:
				boolean isFull = isFullBinaryTree(tree.root);
				if (isFull) {
					System.out.println("The binary tree is a full binary tree.");
				} else {
					System.out.println("The binary tree is not a full binary tree.");
				}
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			case 10:
				boolean isComplete = isCompleteBinaryTree(tree.root);
				if (isComplete) {
					System.out.println("The binary tree is a complete binary tree.");
				} else {
					System.out.println("The binary tree is not a complete binary tree.");
				}
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			case 11:
				boolean isBST = isBinarySearchTree(tree.root);
				if (isBST) {
					System.out.println("The binary tree is a Binary Search Tree.");
				} else {
					System.out.println("The binary tree is not a Binary Search Tree.");
				}
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			case 12:
				System.out.println("Thank you for using the binary tree program. Goodbye.");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
				System.out
						.println("----------------------------------------------------------------------------------");
				break;
			}
		}
	}
}