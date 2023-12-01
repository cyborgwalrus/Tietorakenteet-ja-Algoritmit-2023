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
    int index;
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
    int size;

    private Comparator<K> comparator;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        TreeNode<K, V> node = new TreeNode<K, V>();
        TreeNode<K, V> treeNodePtr = root;

        while (treeNodePtr != null) {
            node.parent = treeNodePtr;

            if (comparator.compare(node.key, treeNodePtr.key) < 0)
                treeNodePtr = treeNodePtr.left;
            else if (comparator.compare(node.key, treeNodePtr.key) > 0)
                treeNodePtr = treeNodePtr.right;
            else
                break;
        }

        if (root == null)
            root = node;
        else if (comparator.compare(node.key, node.parent.key) < 0)
            node.parent.left = node;
        else
            node.parent.right = node;
    }

    // TODO: tee loppuun
    @Override
    public V get(K key) throws IllegalArgumentException {
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
            else break;
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public V find(Predicate<V> searcher) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public int capacity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capacity'");
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ensureCapacity'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public int indexOf(K itemKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

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