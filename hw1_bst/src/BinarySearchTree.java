public class BinarySearchTree<T extends Comparable<T>> implements SortedCollection<T> {
    protected BinaryNode<T> root;

    public BinarySearchTree(){}

    //#region SortedCollection
    /**
     * Inserts a new data value into the sorted collection.
     * @param data the new value being inserted
     * @throws NullPointerException if data argument is null, we do not allow
     *         null values to be stored within a SortedCollection, so an
     *         exception is thrown
     */
    public void insert(T data) throws NullPointerException{
        if (this.isEmpty()){
            this.root = new BinaryNode<T>(data);
        }
        else{
            insertHelper(new BinaryNode<T>(data), this.root);
        }
    }

    /**
     * Check whether data is stored in the tree.    
     * @param data the value to check for in the collection
     * @return true if the collection contains data one or more times, 
     *         and false otherwise
     * @throws NullPointerException if data argument is null
     */
    @Override
    public boolean contains(T data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException();
        }
        if (this.isEmpty()){
            return false;
        }
        return this.root.contains(data);
    }
    /**
     * Counts the 
        }
        ;
    }number of values in the collection, with each duplicate value
     * being counted separately within the value returned.
     * @return the number of values in the collection, including duplicates
     */
    public int size(){
        if (this.isEmpty()){
            return 0;
        }
        else return this.root.size();
    }

    /**
     * Checks if the collection is empty.
     * @return true if the collection contains 0 values, false otherwise
     */
    public boolean isEmpty(){
        return this.root == null;
    }

    /**
     * Removes all values and duplicates from the collection.
     */
    public void clear(){
        this.root = null;
    }
    //#endregion SortedCollection
    //#region Helpers
     /**
     * Performs the naive binary search tree insert algorithm to recursively
     * insert the provided newNode (which has already been initialized with a
     * data value) into the provided tree/subtree. When the provided subtree
     * is null, this method does nothing. 
     * 
     * Duplicate values are stored to the left
     */
    protected void insertHelper(BinaryNode<T> newNode, BinaryNode<T> subtree) {
        // TODO: define and make use of this method in your BinarySearchTree class
        int compare = newNode.getData().compareTo(subtree.getData());

        if (compare <= 0){
            if (subtree.hasLeft()){
                insertHelper(newNode, subtree.getLeft());
            }
            else{
                subtree.setLeft(newNode);
            }
        }
        else{
            if (subtree.hasRight()){
                insertHelper(newNode, subtree.getRight());
            }
            else{
                subtree.setRight(newNode);
            }
        }
    }
    //#region Helpers

}
