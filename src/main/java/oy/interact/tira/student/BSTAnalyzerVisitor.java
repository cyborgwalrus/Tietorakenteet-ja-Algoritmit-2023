package oy.interact.tira.student;

import oy.interact.tira.util.Visitable;
import oy.interact.tira.util.Visitor;

public class BSTAnalyzerVisitor<K extends Comparable<K>, V> implements Visitor<K, V> {
    private int numberOfElements;
    private int numberOfLeafNodes;
    private int minimumDepth;
    private int maximumDepth;
    private int averageDepth;
    private int idealDepth;

    public BSTAnalyzerVisitor() {
        this.numberOfElements = 0;
        this.numberOfLeafNodes = 0;
        this.minimumDepth = 0;
        this.maximumDepth = 0;
        this.averageDepth = 0;
        this.idealDepth = 0;
    }

    @Override
    public void visit(Visitable<K, V> visitable) throws Exception {
        if(visitable != null){
            visitable.accept(this);
        }
    }

    public void visit(BinarySearchTreeContainer<K, V> binarySearchTree) throws Exception {
        if (binarySearchTree != null && binarySearchTree.root != null) {
            this.numberOfElements = binarySearchTree.root.numberOfChildren + 1;
            binarySearchTree.root.accept(this);
        }

    }

    public void visit(TreeNode<K, V> node) throws Exception {
        if(node != null){
            
        }
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

}
