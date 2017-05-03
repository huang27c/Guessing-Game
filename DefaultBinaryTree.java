package datastructures;


/**
 * This class creates a binary tree
 * 
 * @author Ching2 Huang
 *
 * @param data
 */
public class DefaultBinaryTree<T> implements BinaryTree<T> {

	// the root of the tree
	private DefaultBinaryTreeNode<T> root;

	/**
	 * Constructor initializes the root null
	 */
	public DefaultBinaryTree() {
		// root is null
		root = null;
	}

	/**
	 * main method creates a tree with 7 dwarfs
	 * 
	 * @param arguments
	 */
	public static void main(String[] args) {
		// a tree for seven dwarfs
		DefaultBinaryTree<String> tree = new DefaultBinaryTree<String>();
		// happy dwarf
		DefaultBinaryTreeNode<String> happy = new DefaultBinaryTreeNode<String>(
				"Happy");
		// doc dwarf
		DefaultBinaryTreeNode<String> doc = new DefaultBinaryTreeNode<String>(
				"Doc");
		// bashful dwarf
		DefaultBinaryTreeNode<String> bashful = new DefaultBinaryTreeNode<String>(
				"Bashful");
		// grumpy dwarf
		DefaultBinaryTreeNode<String> grumpy = new DefaultBinaryTreeNode<String>(
				"Grumpy");
		// sleepy dwarf
		DefaultBinaryTreeNode<String> sleepy = new DefaultBinaryTreeNode<String>(
				"Sleepy");
		// sneezy dwarf
		DefaultBinaryTreeNode<String> sneezy = new DefaultBinaryTreeNode<String>(
				"Sneezy");
		// set happy dwarf to be the root of the tree
		tree.setRoot(happy);
		// doc is the first left subtree
		happy.setLeftChild(doc);
		// set bashful as the left child of doc
		doc.setLeftChild(bashful);
		// set grumpy as the right child of doc
		doc.setRightChild(grumpy);
		// set sleep to be the first right subtree
		happy.setRightChild(sleepy);
		// set sneezy to be the left child of sleepy
		sleepy.setRightChild(sneezy);
	}

	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	@Override
	public BinaryTreeNode<T> getRoot() {
		// if the root doesn't exist
		if (root == null) {
			// return null
			return null;
		} else {
			// otherwise, return the root
			return root;
		}
	}

	/**
	 * Set the root node for this tree.
	 */
	@Override
	public void setRoot(BinaryTreeNode<T> root) {
		// set root
		this.root = (DefaultBinaryTreeNode<T>) root;
	}

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	@Override
	public boolean isEmpty() {
		// check whether the root exist
		return root == null;
	}

	/**
	 * Get the data of this tree using inorder traversal. left -- data -- right
	 * 
	 * @return inorder List.
	 */
	@Override
	public LinkedList<T> inorderTraversal() {
		// create a list to store data
		LinkedList<T> list = new LinkedList<T>();
		// call inorder traversal method to organize the data
		inorderTraversal(root, list);
		// return the list with data
		return list;
	}

	/**
	 * Get the data of this tree using inorder traversal. left -- data -- right
	 * 
	 * @param root
	 * @param list
	 *            to store data
	 */
	private void inorderTraversal(BinaryTreeNode<T> node, LinkedList<T> list) {
		// if node doesn't exist
		if (node == null) {
			// return here
			return;
			// if the node has no children
		} else if (node.isLeaf()) {
			// add data to the list
			list.insertLast(node.getData());
		} else {
			// otherwise, get the left child
			inorderTraversal(node.getLeftChild(), list);
			// then get the data
			list.insertLast(node.getData());
			// finally, get the right child
			inorderTraversal(node.getRightChild(), list);
		}
	}

	/**
	 * Get the data of this tree using preorder traversal. data -- left -- right
	 * 
	 * @return preorder List.
	 */
	@Override
	public LinkedList<T> preorderTraversal() {
		// create a list to store data
		LinkedList<T> list = new LinkedList<T>();
		// call preorder method to organize the data and add them to the list
		preorderTraversal(root, list);
		// return a list with data
		return list;
	}

	/**
	 * Get the data of this tree using preorder traversal. data -- left -- right
	 * 
	 * @param root
	 * @param data
	 *            list
	 */
	private void preorderTraversal(BinaryTreeNode<T> node, LinkedList<T> list) {
		// if the node doens't exist
		if (node == null) {
			// return here
			return;
			// if the node has no children
		} else if (node.isLeaf()) {
			// add the data to the list
			list.insertLast(node.getData());
		} else {
			// otherwise, get the data of the node
			list.insertLast(node.getData());
			// keep checking from the left child
			preorderTraversal(node.getLeftChild(), list);
			// keep checking from the rigth child
			preorderTraversal(node.getRightChild(), list);

		}
	}

	/**
	 * Get the data of this tree using postorder traversal. left -- right --
	 * data
	 * 
	 * @return postorder List.
	 */
	@Override
	public LinkedList<T> postorderTraversal() {
		LinkedList<T> list = new LinkedList<T>();
		postorderTraversal(root, list);
		return list;
	}

	/**
	 * Get the data of this tree using postorder traversal. left -- right --
	 * data
	 * 
	 * @param root
	 * @param list
	 */
	private void postorderTraversal(BinaryTreeNode<T> node, LinkedList<T> list) {
		// if the node doesn't exist
		if (node == null) {
			// return here
			return;
			// if the node has no children
		} else if (node.isLeaf()) {
			// add the data of the node to the list
			list.insertLast(node.getData());
		} else {
			// otherwise, get the left child first
			postorderTraversal(node.getLeftChild(), list);
			// then get the right child
			postorderTraversal(node.getRightChild(), list);
			// finally, get the data
			list.insertLast(node.getData());
		}
	}

	public String printLeaves(BinaryTreeNode<T> node) {
		LinkedList<T> list = new LinkedList<T>();
		printLeaves(node, list);
		return list.toString();
	}

	private void printLeaves(BinaryTreeNode<T> node, LinkedList<T> list) {
		// if node doesn't exist
		if (node == null) {
			// return here
			return;
			// if the node has no children
		} else if (node.isLeaf()) {
			list.insertLast(node.getData());
		} else {
			printLeaves(node.getLeftChild(), list);
			printLeaves(node.getRightChild(), list);
		}
	}
}