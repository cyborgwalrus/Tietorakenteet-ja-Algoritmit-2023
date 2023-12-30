package oy.interact.tira.student;

import oy.interact.tira.util.Visitor;
import oy.interact.tira.util.Visitable;

public class TreeNode<K extends Comparable<K>, V> implements Visitable<K,V> {
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