package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

class TreeNode<K, V> {
    K key;
    V value;
    int numberOfChildren;

    TreeNode<K, V> left;
    TreeNode<K, V> right;
    TreeNode<K, V> parent;

    public TreeNode() {
        this.numberOfChildren = 0;
    }
}

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    TreeNode<K, V> root;
    // int size; not needed, use root.numberOfChildren instead

    private Comparator<K> comparator;
    private final int lowestIndex = 1;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        TreeNode<K, V> node = new TreeNode<K, V>();
        TreeNode<K, V> treeNodePtr = root;

        // Find the in-order place for the new node
        while (treeNodePtr != null) {
            node.parent = treeNodePtr;

            if (comparator.compare(node.key, treeNodePtr.key) < 0) {
                treeNodePtr.numberOfChildren++;
                treeNodePtr = treeNodePtr.left;
            } else if (comparator.compare(node.key, treeNodePtr.key) > 0) {
                treeNodePtr.numberOfChildren++;
                treeNodePtr = treeNodePtr.right;
            } else
                break;
        }

        if (root == null) {
            root = node;
        } else if (comparator.compare(node.key, node.parent.key) < 0) {
            node.parent.left = node;
        } else {
            node.parent.right = node;
        }

    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null)
            throw new IllegalArgumentException();

        if (root == null)
            return null;

        TreeNode<K, V> treeNodePtr = root;
        while (treeNodePtr != null) {
            if (treeNodePtr.key == key)
                return treeNodePtr.value;

            if (comparator.compare(key, treeNodePtr.key) < 0)
                treeNodePtr = treeNodePtr.left;
            else if (comparator.compare(key, treeNodePtr.key) > 0)
                treeNodePtr = treeNodePtr.right;
            else
                break;
        }
        return null;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    // Find -------------------------------------------------------------------

    V findInOrder(Predicate<V> searcher, TreeNode<K, V> currentNode) {
        if (currentNode == null)
            return null;
        if (searcher.test(currentNode.value))
            return currentNode.value;

        V leftValue = findInOrder(searcher, currentNode.left);
        if (leftValue != null)
            return leftValue;
        V rightValue = findInOrder(searcher, currentNode.right);
        if (rightValue != null)
            return rightValue;

        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        return findInOrder(searcher, root);
    }
    // ------------------------------------------------------------------------

    @Override
    public int size() {
        return root.numberOfChildren;
    }

    @Override
    public int capacity() {
        return size();
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        // Not needed for Binary Search Trees
    }

    @Override
    public void clear() {
        root = null;
    }

    // toArray ------------------------------------------------------------------

    void fillArrayInOrder(Pair<K, V>[] array, TreeNode<K, V> currentNode, int index) {
        if (currentNode != null) {
            fillArrayInOrder(array, currentNode.left, index);
            array[index] = new Pair<K, V>(currentNode.key, currentNode.value);
            index++;
            fillArrayInOrder(array, currentNode.right, index);
        }
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        if (root == null)
            return null;
        Pair<K, V>[] array = null;
        fillArrayInOrder(array, root, lowestIndex);
        return array;
    }
    // -------------------------------------------------------------------------------

    // indexOf -----------------------------------------------------------

    int indexOfInOrder(K itemKey, TreeNode<K, V> currentNode, int index) {
        if (currentNode != null) {
            indexOfInOrder(itemKey, currentNode.left, index);
            if (comparator.compare(currentNode.key, itemKey) == 0)
                return index;
            index++;
            indexOfInOrder(itemKey, currentNode.right, index);
        }
        return -1;
    }

    @Override
    public int indexOf(K itemKey) {
        if (root == null)
            return -1;

        return indexOfInOrder(itemKey, root, lowestIndex);
    }
    // --------------------------------------------------------------------------

    // getIndex ---------------------------------------------------------------

    Pair<K, V> getIndexInOrder(int index, TreeNode<K, V> currentNode, int currentIndex) {
        if (currentNode != null) {
            getIndexInOrder(index, currentNode.left, currentIndex);
            if (index == currentIndex)
                return new Pair<K, V>(currentNode.key, currentNode.value);
            index++;
            getIndexInOrder(index, currentNode.right, currentIndex);
        }
        return null;
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        if (root == null)
            return null;
        if (index < 0 || index > root.numberOfChildren)
            throw new IndexOutOfBoundsException();

        return getIndexInOrder(index, root, lowestIndex);
    }
    // -------------------------------------------------------------------------

    // findIndex --------------------------------------------------------------

    int findIndexInOrder(Predicate<V> searcher, TreeNode<K, V> currentNode, int index) {
        if (currentNode == null)
            return -1;
        
        findInOrder(searcher, currentNode.left);
        if (searcher.test(currentNode.value))
            return index;
        findInOrder(searcher, currentNode.right);

        return -1;
    }
    @Override
    public int findIndex(Predicate<V> searcher) {
        if(root == null)
            return -1;
        
        return findIndexInOrder(searcher, root, lowestIndex);
    }
    // -------------------------------------------------------------------------

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

}