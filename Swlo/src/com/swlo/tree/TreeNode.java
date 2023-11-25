package com.swlo.tree;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {

    private T element;
    private TreeNode<T> parent;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;

    public TreeNode(T element) {
        this.element = element;
    }

    public TreeNode(T element, TreeNode<T> parent) {
        this.element = element;
        this.parent = parent;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public int hasChild() {
        if (getRightChild() == null && getRightChild() == null) {
            return -1;
        }
        if (getRightChild() !=null && getRightChild() == null) {
            return 0;
        }
        if (getRightChild() == null && getRightChild() != null) {
            return 1;
        }
        if (getLeftChild() != null && getRightChild() != null) {
            return 2;
        }
        return -1;

    }


    @Override
    public int compareTo(TreeNode<T> o) {
        return 0;
    }
}