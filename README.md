# Binary Tree Implementation in Java

This repository contains a Java implementation of a **Binary Tree**, supporting various operations like insertion, deletion, traversal, and structural checks. The program also includes methods for specialized operations such as Morris traversal and checking if the tree is a Binary Search Tree (BST).

## Features

1. **Tree Operations**:
   - Insertion
   - Deletion of specific nodes or the entire tree
   - Search for nodes by value
   - Finding siblings of a node
   - Height and depth calculation

2. **Traversals**:
   - Inorder, Preorder, Postorder
   - Level-order traversal using a custom queue
   - Morris traversal (Inorder, Preorder, Postorder)

3. **Structural Checks**:
   - Full Binary Tree
   - Complete Binary Tree
   - Binary Search Tree (BST)

4. **Visualization**:
   - Printing the tree structure with indentation to show depth.

5. **Utility Functions**:
   - Create a dummy tree
   - Reverse paths for postorder traversal

## Menu:
1. Create a dummy tree
2. Insert a node
3. Delete the entire tree
4. Delete a specific node
5. Search for a node
6. Traverse the tree
7. Find siblings of a node in the tree
8. Calculate the height of the tree
9. Calculate the depth of a node
10. Check if the tree is a full binary tree
11. Check if the tree is a complete binary tree
12. Check if the tree is a Binary Search Tree (BST)
13. Exit   

## Class Overview

- **Queue**: A custom queue implementation using a linked list for level-order traversal.
- **TreeNode**: Represents a single node in the binary tree.
- **BinaryTree**: Implements the binary tree with methods for various operations.

## Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/BrothaaEww/Trees
2. Compile the program:
   ```bash
   javac com/demo/binary/BinaryTree.java
3. Run the program:
   ```bash
   java com.demo.binary.BinaryTree
4. Dummy Tree example:
```bash
         10
       /    \
     20      30
    /  \    /
   40   50  60
  /  \
70   80
```
## Requirements:
   Java: JDK 8 or later.
## File Structure
  ```bash
src/
├── com/demo/binary/
│   ├── BinaryTree.java  # Main implementation
```
## Contributing:
   Feel free to contribute by submitting pull requests or reporting issues. Please ensure your code adheres to the repository's coding standards.



