/**
 * Course: CS400 - Fall 2025
 * Program: BinarySearchTree.java for P101
 * Name: Darrell Smith
 * Wisc Email: ddsmith8@wisc.edu
 */
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
        if (data==null){
            throw new NullPointerException();
        }

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
        return containsHelper(data, this.root);
    }
    /**
     * Counts the number of values in the collection, with each duplicate value
     * being counted separately within the value returned.
     * @return the number of values in the collection, including duplicates
     */
    public int size(){
        if (this.isEmpty()){
            return 0;
        }
        return sizeHelper(this.root);
    }

    /**
     * Checks if the collection is empty.
     * @return true if the collection contains 0 values, false otherwise
     */
    public boolean isEmpty(){
        return (this.root == null);
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
        int compare = newNode.getData().compareTo(subtree.getData());

        if (compare <= 0){ //Insert to Left if new smaller is equal to or smaller than subtree node
            if (subtree.getLeft() != null){
                insertHelper(newNode, subtree.getLeft());
            }
            else{
                subtree.setLeft(newNode);
            }
        }
        else{ //Insert to Right
            if (subtree.getRight() != null){
                insertHelper(newNode, subtree.getRight());
            }
            else{
                subtree.setRight(newNode);
            }
        }
    }
    /**
     * Performs the naive binary search tree insert algorithm to recursively
     * determine if a node is contained within the tree
     * @return true if value is contained within subtree, false if not
     */
    protected boolean containsHelper(T value, BinaryNode<T> subtree) {
        int compare = value.compareTo(subtree.getData());

        if (compare == 0){
            return true;
        }
        else if (compare < 0){ //Insert to Left if new smaller is equal to or smaller than subtree node
            if (subtree.getLeft() != null){
                return containsHelper(value, subtree.getLeft());
            }
            else return false;
        }
        else{ //Check Right
            if (subtree.getRight() != null){
                return containsHelper(value, subtree.getRight());
            }
            else return false;
        }
    }

    /**
     * Calculates the size of a subtree
     * @param subtree   subtree of a BST
     * @return  the number of nodes in subtree, including the "root" node of the subtree.
     */
    protected int sizeHelper(BinaryNode<T> subtree){
        int size = 1;

        if (subtree.getLeft() != null){
            size += sizeHelper(subtree.getLeft());
        }

        if (subtree.getRight() != null){
            size += sizeHelper(subtree.getRight());
        }

        return size;
    }



    //#region Helpers
    //#region Tests
    public static void main(){
        runTests();
    }
    public static void runTests(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        if (bst.test1()){
            System.out.println("Test #1 Passed");
        }
        else{
            System.out.println("Test #1 Failed");
        }
        
        if (bst.test2()){
            System.out.println("Test #2 Passed");
        }
        else{
            System.out.println("Test #2 Failed");
        }
        
        if (bst.test3()){
            System.out.println("Test #3 Passed");
        }
        else{
            System.out.println("Test #3 Failed");
        }
        
        if (bst.test4()){
            System.out.println("Test #4 Passed");
        }
        else{
            System.out.println("Test #4 Failed");
        }
        
        if (bst.test5()){
            System.out.println("Test #5 Passed");
        }
        else{
            System.out.println("Test #5 Failed");
        }
        
        if (bst.test6()){
            System.out.println("Test #6 Passed");
        }
        else{
            System.out.println("Test #6 Failed");
        }
        
        if (bst.test7()){
            System.out.println("Test #7 Passed");
        }
        else{
            System.out.println("Test #7 Failed");
        }
        
        if (bst.test8()){
            System.out.println("Test #8 Passed");
        }
        else{
            System.out.println("Test #8 Failed");
        }
    }
    /**
     * Left insertion on root
     * @return True if the test passed, false otherwise
     */
    public boolean test1(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(Integer.valueOf(20));
        bst.insert(Integer.valueOf(10));
        return bst.root.getLeft().getData().equals(10);
    }
    /**
     * Right insertion on root
     * @return True if the test passed, false otherwise
     */
    public boolean test2(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(Integer.valueOf(20));
        bst.insert(Integer.valueOf(30));
        return bst.root.getRight().getData().equals(30);
    }
    /**
     * Duplicate insertion on root
     * @return True if the test passed, false otherwise
     */
    public boolean test3(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(Integer.valueOf(20));
        bst.insert(Integer.valueOf(20));
        return bst.root.getLeft().getData().equals(20);
    }
    /**
     * Double Left Insertion
     * @return True if the test passed, false otherwise
     */
    public boolean test4(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(Integer.valueOf(20));
        bst.insert(Integer.valueOf(10));
        bst.insert(Integer.valueOf(5));
        return bst.root.getLeft().getLeft().getData().equals(5);
    }
    /**
     * Double Right Insertion
     * @return True if the test passed, false otherwise
     */
    public boolean test5(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(Integer.valueOf(20));
        bst.insert(Integer.valueOf(30));
        bst.insert(Integer.valueOf(40));
        return bst.root.getRight().getRight().getData().equals(40);
    }
    /**
     * Root Left Right insertion
     * @return True if the test passed, false otherwise
     */
    public boolean test6(){
        BinarySearchTree<String> bst = new BinarySearchTree<String>();
        bst.insert("D");
        bst.insert("B");
        bst.insert("C");
        return bst.root.getLeft().getRight().getData().equals("C");
    }
    /**
     * Test size()
     * @return True if the test passed, false otherwise
     */
    public boolean test7(){
        BinarySearchTree<String> bst = new BinarySearchTree<String>();
        bst.insert("D"); //1
        bst.insert("B"); //2
        bst.insert("C"); //3
        bst.insert("C"); //4
        bst.insert("E"); //5
        bst.insert("Z"); //6
        return bst.size() == 6;
    }
    /**
     * Test Clear
     * @return True if the test passed, false otherwise
     */
    public boolean test8(){
        BinarySearchTree<String> bst = new BinarySearchTree<String>();
        bst.insert("Z");
        bst.insert("A");
        bst.insert("A");
        bst.insert("B");
        bst.insert("B");
        bst.insert("A");
        bst.insert("C");
        bst.insert("C");
        bst.clear();
        
        bst.insert("D");
        bst.insert("B");
        bst.insert("C");
        return (bst.root.getLeft().getRight().getData().equals("C")
                && (bst.size()==3)
                );
    }


    //#endregion Tests

}
