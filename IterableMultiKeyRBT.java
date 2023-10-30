import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>> implements IterableMultiKeySortedCollectionInterface<T> {


    Comparable<T> iteratorStart = null;
    int numKeys = 0;
    @Override
    public boolean insertSingleKey(T key) {
        if (key == null)
            throw new NullPointerException("Cannot insert null key into the tree.");

        // If the tree is empty, create a new KeyList with the key and insert it into the tree
        if (isEmpty()) {
            KeyListInterface<T> keyList = new KeyList<>(key);
            Node<KeyListInterface<T>> newNode = new Node<>(keyList);
            boolean inserted = insertHelper(newNode);
            if (inserted) {
                this.numKeys++;
                return true;
            }
            return false;
        }

        // Find the node with duplicates of the new key in the tree
        Node<KeyListInterface<T>> currentNode =  root;
        while (currentNode != null) {
            int compare = key.compareTo(currentNode.data.iterator().next());

            if (compare == 0) {
                // Node with the same key found, add the key to its KeyList
                KeyListInterface<T> keyList = currentNode.data;
                keyList.addKey(key);

                return true;
            } else if (compare < 0) {
                // Move to the left subtree
                currentNode =  currentNode.down[0];
            } else {
                // Move to the right subtree
                currentNode = currentNode.down[1];
            }
        }

        // If no node with the same key exists, create a new KeyList with the key and insert it into the tree
        KeyListInterface<T> keyList = new KeyList<>(key);
        Node<KeyListInterface<T>> newNode = new Node<>(keyList);
        boolean inserted = insertHelper(newNode);
        if (inserted) {
            this.numKeys++;
            return true;
        }
        return false;
    }






    @Override
    public int numKeys() {
        int count = 0;

        for (T key : this) {
            count++;
        }
        return count;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Stack<Node<KeyListInterface<T>>> stackOfNodes = getStartStack();
            private Iterator<T> currentIterator = null;

            @Override
            public boolean hasNext() {
                if (currentIterator != null && currentIterator.hasNext()) {
                    return true;
                }

                while (!stackOfNodes.isEmpty()) {
                    Node<KeyListInterface<T>> node = stackOfNodes.pop();
                    currentIterator = node.data.iterator();

                    Node<KeyListInterface<T>> rightChildNode = node.down[1]; // Move to right child
                    while (rightChildNode != null) {
                        stackOfNodes.push(rightChildNode);
                        rightChildNode = rightChildNode.down[0]; // Move left
                    }

                    if (currentIterator.hasNext()) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }



    @Override
    public void setIterationStartPoint(Comparable<T> startPoint) {
        this.iteratorStart = startPoint;
    }

    @Override
    public void clear(){
        super.clear();
        this.numKeys = 0;
    }



    protected Stack<Node<KeyListInterface<T>>> getStartStack() {
        Stack<Node<KeyListInterface<T>>> stack = new Stack<>();
        Node<KeyListInterface<T>> currNode = root;
        if (iteratorStart == null) {
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.down[0];
            }
        } else {
            Node<KeyListInterface<T>> startNode = null;
            Node<KeyListInterface<T>> closestNode = null;
            while (currNode != null) {
                int compare = iteratorStart.compareTo(currNode.data.iterator().next());

                if (compare < 0) {
                    closestNode = currNode;
                    stack.push(currNode);
                    currNode = currNode.down[0];
                } else if (compare == 0) {
                    startNode = currNode;
                    stack.push(currNode);
                    break;
                } else {
                    currNode = currNode.down[1];
                }
            }
        }
        return stack;
    }





    @Test
    public void testInsertSingleKey() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        // Test inserting a single key and check if it returns true
        Assertions.assertTrue(tree.insertSingleKey(5));
    }

    @Test
    public void testNumKeys() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Test the number of keys after inserting multiple values with the same key
        tree.insertSingleKey(10);
        tree.insertSingleKey(10);
        tree.insertSingleKey(15);
        Assertions.assertEquals(3, tree.numKeys());
    }

    @Test
    public void testIterator() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Test iterating over the tree and ensure the values are in the correct order
        tree.insertSingleKey(5);
        tree.insertSingleKey(10);
        tree.insertSingleKey(3);


        tree.setIterationStartPoint(null);

        Iterator<Integer> iterator = tree.iterator();
        Assertions.assertEquals(3, iterator.next().intValue());
        Assertions.assertEquals(5, iterator.next().intValue());
        Assertions.assertEquals(10, iterator.next().intValue());
    }
}
