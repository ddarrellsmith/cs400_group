/**
 * Class representing a binary search tree. Mostly just 
 * 	used as a wrapper around the root Node of a tree.
 * @author Darrell Smith
 * Created on: 8/9/25
 * Revised on: 8/9/25
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * The BST class
 * 
 * @author:
 * @Created: 8/6/2025
 * @Revised:
 */

// import java.util.*;
public class BST<T extends Comparable<T>> implements Iterable<T>,SortedCollection<T> {
	protected Node<T> root;

	/**
	 * Constructor, create a BST with only one root node using the given element
	 */
	public BST(T element) {
		Node<T> newNode = new Node<T>(element);
		this.root = newNode;
	}

	public BST(){}

	//#region SortedCollection
	/**
	 * Inserts an element into an array. 
	 * 
	 * @param element The element to insert into the tree
	 */
	public void insert(T element){
        if (this.root == null){
            Node<T> newNode = new Node<T>(element);
            this.root = newNode;
        }
		else this.root.insert(element);
	}

	/**
	 * Tests if a value is in the tree. 
	 * 
	 * @param element The element to look for
	 * @return true if the element is in the array
	 */
	@Override
	public boolean contains(T element){
		return this.root.contains(element);
	}

	public void remove(T element){
		this.root.remove(element);
	}

	public void clear(){
		this.root = null;
	}

	public int size(){
		return this.root.countNodes();
	}

	public boolean isEmpty(){
		if (this.root == null){
			return true;
		}
		else if (this.root.isEmpty()){
			return true;
		}
		else return false;
	}
	
	//#endregion SortedCollection
	
	/**
	 * Returns an iterator that iterates over the tree elements in ascending order 
	 * @return
	 */
	public Iterator<T> InOrder() {
		return this.iterator();
	}

	/**
	 * Converts the BST into a sorted ArrayList
	 * @return	Sorted list of BST elements
	 */
	public ArrayList<T> toArray() {
		ArrayList<T> list = new ArrayList<T>();

		for (T element : this) {
			list.add(element);
		}
		return list;
	}

	// #region - Iterator
	public Iterator<T> iterator() {
		return new InOrderIterator(this.root);
	}

	public class InOrderIterator implements Iterator<T> {
		private Stack<Node<T>> nodeStack;
		private Stack<Dir> path;

		public InOrderIterator(Node<T> root) {
			nodeStack = new Stack<Node<T>>();
			path = new Stack<Dir>();
			nodeStack.push(root);
			path.push(Dir.ROOT);
			this.goToSmallestChild(root);
		}

		public boolean hasNext() {
			return !this.nodeStack.empty();
		}

		public T next() {
			Node<T> thisNode = this.nodeStack.peek();
			goToNextNode();
			return thisNode.getValue();
		}

		private void goToNextNode() {
			Node<T> thisNode = this.nodeStack.peek();

			// Case 1: We're on a branch. Go right, then get the smallest node
			if (thisNode.hasRight()) {
				this.nodeStack.push(thisNode.right());
				this.path.push(Dir.RIGHT);
				this.goToSmallestChild(thisNode.right());
			}

			// Special Case: we're on the root and there's no right child
			else if (this.path.peek() == Dir.ROOT) {
				this.path.pop();
				this.nodeStack.pop();
			}

			// Case 2: we're on a left child node - just go up one
			else if (this.path.peek() == Dir.LEFT) {
				this.path.pop();
				this.nodeStack.pop();
			}

			// Case 1b: we're on a right leaf. Go up until the next largest value
			else
				do {
					this.nodeStack.pop();
				} while (this.path.pop() == Dir.RIGHT);
		}

		private void goToSmallestChild(Node<T> node) {
			while (node.hasLeft()) {
				node = node.left();
				path.push(Dir.LEFT);
				nodeStack.push(node);
			}
		}

		private enum Dir {
			LEFT,
			RIGHT,
			ROOT;
		}
	}
	// #endregion - Iterator
}
