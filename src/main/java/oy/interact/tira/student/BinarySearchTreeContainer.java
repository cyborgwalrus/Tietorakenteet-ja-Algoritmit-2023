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

    public V findRecursively(Predicate<V> searcher, TreeNode<K, V> currentNode) {
        if (currentNode == null)
            return null;
        if (searcher.test(currentNode.value))
            return currentNode.value;

        V leftValue = findRecursively(searcher, currentNode.left);
        if (leftValue != null)
            return leftValue;
        V rightValue = findRecursively(searcher, currentNode.right);
        if (rightValue != null)
            return rightValue;

        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        return findRecursively(searcher, root);
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
        int index = 1;
        fillArrayInOrder(array, root, index);
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

        int index = 1;
        return indexOfInOrder(itemKey, root, index);
    }
    // --------------------------------------------------------------------------

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIndex'");
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findIndex'");
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

}