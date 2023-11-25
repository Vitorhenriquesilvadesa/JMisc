package com.swlo.tree;

public abstract class GenericTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public abstract void add(T element);

    public abstract void remove(T element);

    public abstract TreeNode<T> search(T element);

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }
}