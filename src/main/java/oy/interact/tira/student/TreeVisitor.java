package oy.interact.tira.student;

public interface TreeVisitor <K extends Comparable<K>,V>{
    void visitTree(BinarySearchTreeContainer<K, V> binarySearchTree) throws Exception;
    void visitNode(TreeNode<K, V> node) throws Exception;
}
