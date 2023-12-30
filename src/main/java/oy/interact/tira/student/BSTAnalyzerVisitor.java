package oy.interact.tira.student;

public class BSTAnalyzerVisitor<K extends Comparable<K>, V> implements TreeVisitor<K, V> {
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
        this.minimumDepth = 0;
        this.maximumDepth = 0;
        this.sumOfDepths = 0;
        this.averageDepth = 0;
        this.idealDepth = 0;
    }

    public void visitTree(BinarySearchTreeContainer<K, V> binarySearchTree) throws Exception {
        if (binarySearchTree != null && binarySearchTree.root != null) {
            numberOfElements = binarySearchTree.root.numberOfChildren + 1;
            binarySearchTree.root.accept(this);
            // BST visit complete, processing remaining variables
            averageDepth = sumOfDepths / numberOfElements;
            idealDepth = (int) Math.log(numberOfElements);
        }

    }

    public void visitNode(TreeNode<K, V> node) throws Exception {
        if (node != null) {
            currentDepth++;
            // Check for leaf nodes
            if (node.left == null && node.right == null)
                numberOfLeafNodes++;
            // visit child nodes
            node.left.accept(this);
            node.right.accept(this);

        } else {
            // node == null, meaning we reached the end of a branch
            sumOfDepths += currentDepth;
            if (currentDepth < minimumDepth)
                minimumDepth = currentDepth;
            if (currentDepth > maximumDepth)
                maximumDepth = currentDepth;
        }
    }

    @Override
    public String toString() {
        String string = "Tree analytics:\n";
        string += String.format("- Elements: %d\n", numberOfElements);
        string += String.format("- Leaf nodes: %d\n", numberOfLeafNodes);
        string += String.format("- min depth: %d\n", minimumDepth);
        string += String.format("- max depth: %d\n", maximumDepth);
        string += String.format("- average depth: %d\n", averageDepth);
        string += String.format("- average depth: %d\n", averageDepth);
        string += String.format("- Ideal depth for balanced BST would be: %d\n", idealDepth);

        return string;
    }

}
