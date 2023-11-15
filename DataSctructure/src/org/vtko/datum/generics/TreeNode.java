package org.vtko.datum.generics;

public abstract class TreeNode<T> {
    protected T element;
    protected TreeNode<T> left;
    protected TreeNode<T> right;

    public TreeNode(T value){
        this.element = value;
    }

    public void setRight(TreeNode<T> node) {
        right = node;
    }

    public void setLeft(TreeNode<T> node) {
        left = node;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public TreeNode<T> getRight() {
        return  right;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public T getElement() {
        return element;
    }

}
