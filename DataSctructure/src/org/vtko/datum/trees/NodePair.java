package org.vtko.datum.trees;

public class NodePair<T extends Comparable<T>> {

    private TreeNode<T> parent;

    private TreeNode<T> child;

    public NodePair(TreeNode<T> parent, TreeNode<T> child){
        this.parent = parent;
        this.child = child;
    }

    public TreeNode<T> getChild() {
        return child;
    }

    public TreeNode<T> getParent() {
        return parent;
    }
}
