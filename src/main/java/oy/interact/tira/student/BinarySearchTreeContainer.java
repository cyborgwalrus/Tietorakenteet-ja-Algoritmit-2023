package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;
import oy.interact.tira.util.Visitable;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

class TreeNode<K extends Comparable<K>, V> implements Visitable<K,V> {
    K key;
    V value;
    int numberOfChildren;

    TreeNode<K, V> left;
    TreeNode<K, V> right;
    TreeNode<K, V> parent;

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.numberOfChildren = 0;
    }

    public void accept(Visitor<K, V> visitor) throws Exception {
       visitor.visit(this);
    }
}

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    TreeNode<K, V> root;

    private Comparator<K> comparator;
    final int LOWEST_INDEX = 0;

    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null)
            throw new IllegalArgumentException();

        TreeNode<K, V> node = new TreeNode<K, V>(key, value);
        TreeNode<K, V> treeNodePtr = root;
        TreeNode<K, V> parent = root;

        // Find the in-order place for the new node
        while (treeNodePtr != null) {
            parent = treeNodePtr;

            // If the value of the current node is the same as the new node, replace it with
            // the new node values
            if (treeNodePtr.value.equals(node.value)) {
                treeNodePtr.key = node.key;
                treeNodePtr.value = node.value;
                return;
            }

            int comparatorValue = comparator.compare(node.key, treeNodePtr.key);
            if (comparatorValue < 0) {
                treeNodePtr = treeNodePtr.left;
            } else if (comparatorValue > 0) {
                treeNodePtr = treeNodePtr.right;
            } else {
                // Node is already in the tree
                parent = null;
                return;
            }

        }

        if (root == null) {
            root = node;
            return;
        }
        if (parent == null)
            return;

        node.parent = parent;
        if (comparator.compare(node.key, node.parent.key) < 0) {
            node.parent.left = node;
        } else if (comparator.compare(node.key, node.parent.key) > 0) {
            node.parent.right = node;
        } else
            return;

        // Update numberOfchildren
        treeNodePtr = node;
        while (treeNodePtr != root) {
            treeNodePtr = treeNodePtr.parent;
            treeNodePtr.numberOfChildren++;
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
            if (comparator.compare(treeNodePtr.key, key) == 0)
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
        // not implemented
        return null;
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
        if(root != null)
            return root.numberOfChildren + 1;
        else return 0;
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
    class RecursiveArrayInOrderFiller {
        private int currentIndex = 0;
        private Pair<K, V>[] array;

        public RecursiveArrayInOrderFiller(int arraySize) {
            this.array = (Pair<K, V>[]) new Pair[arraySize];
        }

        void fillArrayInOrder(TreeNode<K, V> currentNode) {
            if (currentNode != null) {
                fillArrayInOrder(currentNode.left);
                array[currentIndex] = new Pair<K, V>(currentNode.key, currentNode.value);
                currentIndex++;
                fillArrayInOrder(currentNode.right);
            }
        }

        Pair<K, V>[] getArray() {
            return this.array;
        }
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        if (root == null)
            return null;
        RecursiveArrayInOrderFiller arrayFiller = new RecursiveArrayInOrderFiller(root.numberOfChildren + 1);
        arrayFiller.fillArrayInOrder(root);
        return arrayFiller.getArray();
    }
    // -------------------------------------------------------------------------------

    // indexOf -----------------------------------------------------------

    private class RecursiveIndexOfFinder {
        private int index;
        K itemKey;
        AtomicInteger atomicIndex;

        public RecursiveIndexOfFinder(K itemKey) {
            this.itemKey = itemKey;
            this.index = -1;
            this.atomicIndex = new AtomicInteger(0);
        }

        void findIndexRecursive(TreeNode<K, V> currentNode){
            if(currentNode != null && index == -1){
            findIndexRecursive(currentNode.left);
            if(comparator.compare(itemKey, currentNode.key) == 0){
                this.index = atomicIndex.get();
            }
            atomicIndex.getAndIncrement();
            findIndexRecursive(currentNode.right);
            }
        }

        int getIndex() {
            return this.index;
        }

    }

    @Override
    public int indexOf(K itemKey) {
        if (root == null)
            return -1;

        RecursiveIndexOfFinder indexFinder = new RecursiveIndexOfFinder(itemKey);
        indexFinder.findIndexRecursive(root);

        return indexFinder.getIndex();
    }
    // --------------------------------------------------------------------------

    // getIndex ---------------------------------------------------------------
    
    private class RecursiveGetIndexFinder {
        private boolean targetFound;
        private int targetIndex;
        private AtomicInteger atomicIndex;
        private Pair<K, V> pair;

        public RecursiveGetIndexFinder(int index) {
            this.targetIndex = index;
            this.targetFound = false;
            this.atomicIndex = new AtomicInteger(0);
        }

        void findIndexRecursive(TreeNode<K, V> currentNode){
            if(currentNode != null && targetFound == false){
            findIndexRecursive(currentNode.left);
            if(atomicIndex.get() == targetIndex){
                targetFound = true;
                pair = new Pair<K, V>(currentNode.key, currentNode.value);
            }
            atomicIndex.getAndIncrement();
            findIndexRecursive(currentNode.right);
            }
        }

        Pair<K, V> getPair() {
            return this.pair;
        }

    }
    
    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        if (root == null)
            return null;
        if (index < 0 || index > root.numberOfChildren + 1)
            throw new IndexOutOfBoundsException();

        RecursiveGetIndexFinder indexFinder = new RecursiveGetIndexFinder(index);
        indexFinder.findIndexRecursive(root);
        return indexFinder.getPair();
    }
    // -------------------------------------------------------------------------

    // findIndex --------------------------------------------------------------

    private class RecursiveIndexFinder {
        private int index;
        Predicate<V> searcher;
        AtomicInteger atomicIndex;

        public RecursiveIndexFinder(Predicate<V> searcher) {
            this.searcher = searcher;
            this.index = -1;
            atomicIndex = new AtomicInteger(0);
        }

        void findIndexRecursive(TreeNode<K, V> currentNode){
        if(currentNode != null && index == -1){
            findIndexRecursive(currentNode.left);
            if(searcher.test(currentNode.value)){
                this.index = atomicIndex.get();
            }
            atomicIndex.getAndIncrement();
            findIndexRecursive(currentNode.right);

        }
    }

        int getIndex() {
            return this.index;
        }

    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        if (root == null)
            return -1;
        RecursiveIndexFinder indexFinder = new RecursiveIndexFinder(searcher);
        indexFinder.findIndexRecursive(root);
        return indexFinder.getIndex();
    }
    // -------------------------------------------------------------------------

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
       visitor.visit(this);
    }

}