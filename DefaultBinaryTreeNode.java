package datastructures;

/**
 * This class constructs a binary tree node
 * 
 * @author Ching2 Huang
 *
 * @param data
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> {

	// the left and right children
	private DefaultBinaryTreeNode<T> left, right;
	// the data for the node
	private T data;

	public DefaultBinaryTreeNode() {
		//the data
		this.data = null;
	}
	
	/**
	 * Constructor sets the data
	 * @param data
	 */
	public DefaultBinaryTreeNode(T data) {
		//the data
		this.data = data;
	}

	/**
	 * Get the data stored at this node.
	 * 
	 * @return Object data.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data stored at this node.
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get the left child.
	 * 
	 * @return BinaryTreeNode that is left child, or null if no child.
	 */
	public BinaryTreeNode<T> getLeftChild() {
		if (left == null) {
			return null;
		} else {
			return left;
		}
	}

	/**
	 * Get the right child.
	 * 
	 * @return BinaryTreeNode that is right child, or null if no child.
	 */
	public BinaryTreeNode<T> getRightChild() {
		if (right == null) {
			return null;
		} else {
			return right;
		}
	}

	/**
	 * Set the left child.
	 */
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.left = (DefaultBinaryTreeNode<T>) left;
	}

	/**
	 * Set the right child.
	 */
	public void setRightChild(BinaryTreeNode<T> right) {
		this.right = (DefaultBinaryTreeNode<T>) right;
	}

	/**
	 * Tests if this node is a leaf (has no children).
	 * 
	 * @return true if leaf node.
	 */
	public boolean isLeaf() {
		return left == null && right == null;
	}
}
