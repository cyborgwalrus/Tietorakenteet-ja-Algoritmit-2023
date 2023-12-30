package oy.interact.tira.student;

public interface TreeVisitable <K extends Comparable<K>,V>{
    void accept(TreeVisitor<K,V> visitor) throws Exception;
}
