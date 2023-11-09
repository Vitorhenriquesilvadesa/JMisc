package org.vtko.datum.trees;

import java.util.Stack;

public abstract class GenericTree<T extends Comparable<T>> implements Iterable<T> {

    protected TreeNode<T> root;
    private Stack<TreeNode<T>> stack;
    protected int size;
    protected int height;

    public GenericTree() {
    }

    protected GenericTree<T> root(TreeNode<T> root) {
        this.root = root;
        return this;
    }

    public int size() {
        return this.size;
    }

    private void populateStack(TreeNode<T> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeftChild();
        }
    }

    public abstract T get(T element);

    protected abstract NodePair<T> getNode(T element);

    public abstract void insert(T element);

    protected abstract void insert(TreeNode<T> node, T element);

    public abstract void delete(T element);

    public abstract void foreach(ForeachCallback<T> callback);
}
