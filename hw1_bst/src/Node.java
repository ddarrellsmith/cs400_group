/**
 * Class representing a node in a binary tree
 * @author Darrell Smith
 * Created on: 8/9/25
 * Revised on: 8/9/25
 */
public class Node<T extends Comparable<T>> {
	private T value;
	private Node<T> left; //left subtree
	private Node<T> right; //right subtree
    private Node<T> parent;
	/**
	 * Constructor - creates a new node in a binary tree
	 * @param element
	 */
	public Node(T element) {
		this.value = element;	
		this.left = null;
		this.right = null;
	}

    /**
     * Creates a new subnode containing the provided argument and inserts it into the 
     * appropriate path of the tree (treating this node as the root). 
     * @param value The element to insert
     */
    public void insert(T value){    
        if (this.getValue() == null){
            this.value = value;
            return;
        }

        int compare = this.compareTo(value);
        
        if (compare == 0){
            throw new IllegalArgumentException("BST values must be unique");
        }
        else if (compare > 0){
            if (this.hasRight()){
                this.right().insert(value);
            }
            else this.setRight(new Node<T>(value));
        }
        else{
            if (this.hasLeft()){
                this.left().insert(value);
            }
            else this.setLeft(new Node<T>(value));
        }
    }

    /**
     * Tests if a value is contained within the heirarchy of a node
     * @param value the value to search for
     * @return      true if the value is contained in the heirarchy of this node, 0 otherwise
     */
	public boolean contains(T value){
        int compare = this.compareTo(value);

        if (compare == 0){
            return true;
        }
        else if (compare > 0){
            return this.right().contains(value);
        }
        else{
            return this.left().contains(value);
        }
	}

    public void remove(T value){
        int compare = this.compareTo(value);

        if (compare == 0){
            this.delete();
        }
        else if (compare > 0 && this.hasRight()){
            this.right().remove(value);
        }
        else if (compare < 0 && this.hasLeft()){
            this.left().remove(value);
        }
        else{
            throw new RuntimeException("Error: could not find element " + value);
        }
    }

    private void delete(){
        // If this is a tree with only one node, remove value
        if (this.isRoot() && this.isLeaf()){
            this.value = null;
        }
        // If leaf, remove references to node
        else if (this.isLeaf()){
            if (this.isLeft()){
                this.getParent().setLeft(null);
                return;
            }
            else if (this.isRight()){
                this.getParent().setRight(null);
                return;
            }
            else throw new RuntimeException("Huh???");
        }
        // If branch, replace with next largest/smallest value
        if (this.hasRight()){
            T replacementValue = this.right().min();
            this.remove(replacementValue);
            this.value = replacementValue;
        }
        else{
            T replacementValue = this.left().max();
            this.remove(replacementValue);
            this.value = replacementValue;
        }
    }

    public boolean isEmpty(){
        return (this.isRoot()) && (this.getValue() == null);
    }

	//#region - Misc Methods

    /**
     * @return  The length of the longest chain in the tree
     */
    public int depth(){
        int leftDepth = 0,
            rightDepth = 0;
        if (this.hasLeft()){
            leftDepth = this.left().depth();
        }
        if (this.hasRight()){
            rightDepth = this.right().depth();
        }
        return Math.max(leftDepth, rightDepth)+1;
    }
    /**
     * @return the maximum value in the tree
     */
	public T max(){
        return this.maxNode().getValue();
    }

    /**
     * @return minimum value of the tree
     */
    public T min(){
        return this.minNode().getValue();
    }

    /**
     * @return number of nodes in the tree
     */
    public int countNodes(){
        int nodeCount = 1;
        if (this.hasLeft()) nodeCount += this.left().countNodes();
        if (this.hasRight()) nodeCount += this.right().countNodes();
        return nodeCount;
    }

    /**
     * @return the leftmost node
     */
    private Node<T> minNode(){
        if (this.hasLeft()){
            return this.left().minNode();
        }
        else return this;
    }

    /**
     * @return the rightmost node
     */
    private Node<T> maxNode(){
        if (this.hasRight()){
            return this.right().maxNode();
        }
        else return this;
    }

    public Node<T> getRoot(){
        if (this.isRoot()){
            return this;
        }
        else return this.getParent().getRoot();
    }

	//#endregion - Misc Methods
	//#region - Boolean Methods

    public boolean isRoot(){
        return this.getParent() == null;
    }
    /**
     * @return true if this node has a left (smaller) child node
     */
    public boolean hasLeft(){
        return this.left() != null;
    }

    /**
     * @return true if this node has a right (larger) child node
     */
    public boolean hasRight(){
        return this.right() != null;
    }

    /**
     * @return true if this node has no children
     */
    public boolean isLeaf(){
        return !(this.hasLeft() || this.hasRight());
    }

    public boolean isRight(){
        return this.getParent().right() == this;
    }

    public boolean isLeft(){
        return this.getParent().left() == this;
    }
    
	//#endregion - Boolean Methods
	//#region - Attribute Getters/Setters
    public void setValue(T element){
        if (this.getValue() != null){
            throw new RuntimeException("Nodes are immutable");
        }
        else this.value = element;
    }

	public T getValue() {
		return value;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> left() {
		return left;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
	public Node<T> right() {
		return right;
	}
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
    public Node<T> getParent() {
        return parent;
    }
	//#endregion - Attribute Getters/Setters
	//#region - Interfaces
    /**
     * @param value
     * @return 1 if this is bigger than value,
     *         -1 if this is smaller than value
     *         0 if this equalTo value
     */
    public int compareTo(T value){
        return -value.compareTo(this.getValue());
    }

    public int compareTo(Node<T> otherNode){
        return this.compareTo(otherNode.getValue());
    }
	//#endregion - Interfaces
}