import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {


    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;

        public RBTNode(T data) {
            super(data);
        }

        public RBTNode<T> getUp() {
            return (RBTNode<T>) this.up;
        }

        public RBTNode<T> getDownLeft() {
            return (RBTNode<T>) this.down[0];
        }

        public RBTNode<T> getDownRight() {
            return (RBTNode<T>) this.down[1];
        }
    }

    private void enforceRBTreePropertiesAfterInsert (RBTNode <T> newNode){
        // Get the parent of the newly inserted node.
        RBTNode<T> parent = newNode.getUp();
        // Determine the grandparent of the newly inserted node.
        RBTNode<T> grandparent = (parent != null) ? parent.getUp() : null;

        // If the new node is the root
        if(parent == null){
            newNode.blackHeight = 1;
            return;
        }

        // the parent node is already black
        if(parent.blackHeight == 1){
            return; //There is no red-black tree violation here
        }

        // The parent and the aunt nodes are red
        RBTNode<T> aunt = (parent == grandparent.getDownRight()) ? grandparent.getDownLeft() : grandparent.getDownRight();

        if(aunt != null && aunt.blackHeight == 0){
            parent.blackHeight = 1;
            aunt.blackHeight = 1;
            grandparent.blackHeight = 0;

            enforceRBTreePropertiesAfterInsert(grandparent);
            return;
        }

        // parent node is red, aunt node is black and the inserted node is the inner grandchild

        if(parent == grandparent.getDownLeft() && newNode == parent.getDownRight()){
            rotate(newNode, parent);
            newNode = parent;
            parent = newNode.getUp();  //update the newNode +  the parent

        }
        else if (parent == grandparent.getDownRight() && newNode == parent.getDownLeft()){
            rotate(newNode, parent);
            newNode = parent;
            parent = newNode.getUp(); // upadate the newNode +  the parent


        }

        // Parent node is red, aunt node is black and inserted node is the outer child

        if(parent == grandparent.getDownLeft()){
            rotate(parent,grandparent);
        }
        else {
            rotate(parent, grandparent);
        }
        parent.blackHeight = 1;
        grandparent.blackHeight = 0;

    }





    @Override
    public boolean insert(T data) throws NullPointerException {
        RBTNode<T> newNode = new RBTNode<>(data);
        boolean inserted = insertHelper(newNode);

        if (inserted) {
            enforceRBTreePropertiesAfterInsert(newNode);

        }
        return true;
    }




    @Test
    public void test1RBT() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        // Checks if root node is black

        assertTrue(tree.insert(5));
        assertTrue(tree.insert(3));
        assertTrue(tree.insert(8));
        assertTrue(tree.insert(2));
        assertTrue(tree.insert(4));

        RBTNode<Integer> rootNode = (RBTNode<Integer>) tree.root ;

        // Check that the root is black
        assertEquals(rootNode.blackHeight, 1);

    }

    @Test
    public void test2RBT() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        // Case when red node child is red node and has red aunt

        assertTrue(tree.insert(10)); // Insert a black node
        assertTrue(tree.insert(5));  // Insert a red node (left child of 10)
        assertTrue(tree.insert(15));// Insert a red node (right child of 10)
        assertTrue(tree.insert(4)); // Insert a red node (left child of 5)
        RBTNode<T> rootNode = (RBTNode<T>) tree.root ;

        // Check that after inserting 4 (red) as a child of 5 (red), the tree is corrected
        RBTNode<T> leftChild = rootNode.getDownLeft();
        RBTNode<T> rightChild = rootNode.getDownRight();

        assertEquals(leftChild.blackHeight, 1);
        assertEquals(rightChild.blackHeight, 1);
    }

    @Test
    public void test3RBT(){
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        // Case when red node child is red node and has black aunt

        assertTrue(tree.insert(10)); // Insert a black node
        assertTrue(tree.insert(5));  // Insert a red node (left child of 10)
        assertTrue(tree.insert(15));// Insert a red node (right child of 10)
        assertTrue(tree.insert(4)); // Insert a red node (left child of 5)
        assertTrue(tree.insert(14)); // Insert a red node (left child of 15)
        assertTrue(tree.insert(13));
        RBTNode<Integer> rootNode = (RBTNode<Integer>) tree.root ;

        // Checks if the rotation is successful
        assertEquals(((RBTNode<Integer>) rootNode.down[1]), tree.findNode(14));
        assertEquals(((RBTNode<Integer>) rootNode.down[1].down[1]), tree.findNode(15));
        assertEquals(((RBTNode<Integer>) rootNode.down[1].down[0]), tree.findNode(13));

        // Check if the color swap happened
        assertEquals(((RBTNode<Integer>) rootNode.down[1]).blackHeight, 1);
        assertEquals(((RBTNode<Integer>) rootNode.down[1].down[1]).blackHeight, 0);
    }
}
