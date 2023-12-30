package oy.interact.tira.student;

import oy.interact.tira.util.Visitable;
import oy.interact.tira.util.Visitor;

public class BSTAnalyzerVisitor<K extends Comparable<K>, V> implements Visitor<K, V> {
    private int numberOfElements;
    private int numberOfLeafNodes;
    private int minimumDepth;
    private int maximumDepth;
    private int sumOfDepths;
    private int averageDepth;
    private int idealDepth;
    private int currentDepth;

    public BSTAnalyzerVisitor() {
        this.numberOfElements = 0;
        this.numberOfLeafNodes = 0;
        this.minimumDepth = Integer.MAX_VALUE;
        this.maximumDepth = 0;
        this.sumOfDepths = 0;
        this.averageDepth = 0;
        this.idealDepth = 0;
    }

    @Override
    public void visit(Visitable<K, V> visitable) throws Exception {
        if (visitable != null) {
            visitable.accept(this);
        }
    }

    @Override
    public void visit(BinarySearchTreeContainer<K, V> binarySearchTree) throws Exception {
        if (binarySearchTree != null && binarySearchTree.root != null) {
            numberOfElements = binarySearchTree.root.numberOfChildren + 1;
            binarySearchTree.root.accept(this);
            // BST visit complete, processing remaining variables
            averageDepth = sumOfDepths / numberOfLeafNodes;
            // Division is needed to convert from log base e to log base 2 
            idealDepth = (int) Math.floor(Math.log(numberOfElements) / Math.log(2));
        }

    }

    @Override
    public void visit(TreeNode<K, V> node) throws Exception {
        if (node.left == null && node.right == null) {
            // Both child nodes were null, meaning we reached the end of a branch
            numberOfLeafNodes++;
            sumOfDepths += currentDepth;
            if (currentDepth < minimumDepth)
                minimumDepth = currentDepth;
            if (currentDepth > maximumDepth)
                maximumDepth = currentDepth;
        }
        currentDepth++;
        if (node.left != null) {
            node.left.accept(this);
            currentDepth--;
        }
        if (node.right != null) {
            node.right.accept(this);
            currentDepth--;
        }

    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

    @Override
    public String toString() {
        String string = "Tree analytics:\n";
        string += String.format("- Elements: %d\n", numberOfElements);
        string += String.format("- Leaf nodes: %d\n", numberOfLeafNodes);
        string += String.format("- min depth: %d\n", minimumDepth);
        string += String.format("- max depth: %d\n", maximumDepth);
        string += String.format("- average depth: %d\n", averageDepth);
        string += String.format("- Ideal depth for balanced BST would be: %d\n", idealDepth);

        return string;
    }

}
